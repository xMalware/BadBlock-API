package fr.badblock.gameapi;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import fr.badblock.gameapi.configuration.BadConfiguration;
import fr.badblock.gameapi.databases.LadderSpeaker;
import fr.badblock.gameapi.databases.SQLDatabase;
import fr.badblock.gameapi.fakeentities.FakeEntity;
import fr.badblock.gameapi.game.GameServer;
import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.packets.GlobalPacketListener;
import fr.badblock.gameapi.packets.InPacketListener;
import fr.badblock.gameapi.packets.OutPacketListener;
import fr.badblock.gameapi.packets.out.play.PlayPlayerInfo.PlayerInfo;
import fr.badblock.gameapi.packets.watchers.WatcherArmorStand;
import fr.badblock.gameapi.packets.watchers.WatcherEntity;
import fr.badblock.gameapi.packets.watchers.WatcherLivingEntity;
import fr.badblock.gameapi.particles.ParticleEffect;
import fr.badblock.gameapi.particles.ParticleEffectType;
import fr.badblock.gameapi.players.BadblockOfflinePlayer;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.BadblockTeam;
import fr.badblock.gameapi.players.kits.PlayerKit;
import fr.badblock.gameapi.players.kits.PlayerKitContentManager;
import fr.badblock.gameapi.players.scoreboard.BadblockScoreboard;
import fr.badblock.gameapi.players.scoreboard.CustomObjective;
import fr.badblock.gameapi.portal.Portal;
import fr.badblock.gameapi.run.RunType;
import fr.badblock.gameapi.servers.ChestGenerator;
import fr.badblock.gameapi.servers.JoinItems;
import fr.badblock.gameapi.servers.MapProtector;
import fr.badblock.gameapi.signs.SignManager;
import fr.badblock.gameapi.technologies.RabbitSpeaker;
import fr.badblock.gameapi.utils.ServerProperties;
import fr.badblock.gameapi.utils.entities.CustomCreature;
import fr.badblock.gameapi.utils.general.StringUtils;
import fr.badblock.gameapi.utils.i18n.I18n;
import fr.badblock.gameapi.utils.itemstack.CustomInventory;
import fr.badblock.gameapi.utils.itemstack.ItemStackExtra;
import fr.badblock.gameapi.utils.itemstack.ItemStackFactory;
import fr.badblock.gameapi.utils.merchants.CustomMerchantInventory;
import fr.badblock.gameapi.utils.selections.CuboidSelection;
import lombok.Getter;
import lombok.Setter;

/**
 * La classe principale de l'API. Elle permet de rďż˝cupďż˝rer la plupart de ses
 * composants (sauf ceux disponibles en statics) ainsi que de la configurer.<br>
 * Les principaux ďż˝lďż˝ments ďż˝ configurer sont :
 * <ul>
 * <li>Les kits, car il est trďż˝s utile de garder une base commune ďż˝ tous les
 * jeux, nďż˝anmoins la gestion des items est diffďż˝rentes entre les jeux (ex :
 * splatoon, les armes ne sont pas dans la configuration). C'est pourquoi il
 * faut dďż˝finir la classe permettant de load les items.</li>
 * <li>Les donnďż˝es de joueur InGame, car il est inutile de crďż˝er une Map
 * uniquement pour ces donnďż˝es, et que les donnďż˝es entre les jeux peuvent ďż˝tre
 * trďż˝s diffďż˝rentes.</li>
 * <li>ect ...</li>
 * </ul>
 * <br>
 * Le principe de l'API rďż˝side donc sur deux points : elle est ďż˝ la fois une
 * base ďż˝ tous les jeux, pour qu'ils se ressemblent (et soient plus rapidement
 * mettables ďż˝ jours) ainsi qu'une longue liste d'utilitaires variďż˝s, pour
 * simplifier le dďż˝veloppement.<br>
 *
 * @author LeLanN
 */
public abstract class GameAPI extends JavaPlugin {

	public static final boolean TEST_MODE;


	static {
		String testMode = System.getProperty("badblock.testmode");

		if(testMode == null){
			testMode = ServerProperties.getProperties().getProperty("badblock.testmode", "false");
		}

		boolean isTestMode = false;

		try {
			isTestMode = Boolean.parseBoolean(testMode);
		} catch(Exception e){
			isTestMode = false;
		} finally {
			TEST_MODE = isTestMode;
		}
	}

	@Getter
	protected static GameAPI API;
	@Getter
	@Setter
	protected static String gameName;
	@Getter
	@Setter
	protected static String internalGameName;
	@Getter
	protected static Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).disableHtmlEscaping()
	.create();
	@Getter
	protected static Gson prettyGson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
	.disableHtmlEscaping().setPrettyPrinting().create();
	@Getter@Setter
	protected static boolean isJoinable = true;
	@Getter@Setter
	private boolean finished;

	/**
	 * Rďż˝cupďż˝re le systďż˝me d'internationalisation (pour ďż˝viter de rďż˝cupďż˝rer
	 * l'instance).
	 * 
	 * @return Le systďż˝me d'i18n
	 */
	public static I18n i18n() {
		return API.getI18n();
	}

	/**
	 * Log un message 'normal' (plus rapide que de rďż˝cupďż˝rrer le logger)
	 * 
	 * @param message
	 *    
	 *         Le message
	 */
	public static void log(String message) {
		Bukkit.getLogger().log(Level.INFO, message);
	}

	/**
	 * Log un message avec des couleurs ({@link I18n#replaceColors(String)}
	 * appelďż˝)
	 * 
	 * @param message
	 *            Le message colorďż˝
	 */
	public static void logColor(String message) {
		Bukkit.getConsoleSender().sendMessage(i18n().replaceColors(message));
	}

	/**
	 * Log un message d'erreur (plus rapide que de rďż˝cupďż˝rrer le logger)
	 * 
	 * @param message
	 *            Le message
	 */
	public static void logError(String message) {
		Bukkit.getLogger().log(Level.SEVERE, message);
	}

	/**
	 * Log un message d'alerte (plus rapide que de rďż˝cupďż˝rrer le logger)
	 * 
	 * @param message
	 *            Le message
	 */
	public static void logWarning(String message) {
		Bukkit.getLogger().log(Level.WARNING, message);
	}

	public static String getPrettyServerName(){
		String[] splitted = getServerName().split("_");

		String type = StringUtils.getUpperFirstLetter(splitted[0].toLowerCase());

		if(splitted.length == 1){
			return type;
		} else {
			return type + " nďż˝" + splitted[1];
		}
	}

	public static String getServerName(){
		return Bukkit.getServer().getServerName();
	}

	/**
	 * Rďż˝cupďż˝re l'ensemble des joueurs en ligne
	 * @return Une liste non modifiable
	 */
	public abstract List<BadblockPlayer> getOnlinePlayers();

	/**
	 * Rďż˝cupďż˝re l'ensemble des joueurs en ligne n'ďż˝tant pas en mode spectateur
	 * @return Une liste non modifiable
	 */
	public abstract List<BadblockPlayer> getRealOnlinePlayers();

	/**
	 * Permet d'ďż˝quilibrer les teams
	 * 
	 * @param sameSize
	 *            Si il faut que toutes les teams ai le mďż˝me nombre de joueurs
	 */
	public abstract void balanceTeams(boolean sameSize);

	/**
	 * Crďż˝ďż˝ un nouveau objective custom
	 * 
	 * @param name
	 *            Le nom (interne) de l'objective
	 * @return Le CustomObjective crďż˝ďż˝.
	 */
	public abstract CustomObjective buildCustomObjective(String name);

	/**
	 * Crďż˝ďż˝ un nouvel inventaire custom
	 * 
	 * @param line
	 *            Le nombre de ligne (et non pas de cases !)
	 * @param displayName
	 *            Le nom d'affichage de l'inventaire custom
	 * @return Le nouvel inventaire
	 */
	public abstract CustomInventory createCustomInventory(int line, String displayName);

	/**
	 * Crďż˝e un nouveau extra pour un item
	 * 
	 * @param itemStack
	 *            L'item stack auquel ajoutďż˝ l'extra
	 * @return Un nouvel extra ou celui existant si dďż˝jďż˝ register pour la
	 *         l'item.
	 */
	public abstract ItemStackExtra createItemStackExtra(ItemStack itemStack);

	/**
	 * Crďż˝e une nouvelle factory d'items
	 * 
	 * @return La factory d'items
	 */
	public abstract ItemStackFactory createItemStackFactory();

	/**
	 * Crďż˝ďż˝ une nouvelle factory d'items
	 * 
	 * @param item
	 *            L'item de base qui sera utilisďż˝ pour le paramďż˝trage de la
	 *            factory
	 * @return La factory
	 */
	public abstract ItemStackFactory createItemStackFactory(ItemStack item);

	/**
	 * Crďż˝e un packet ďż˝ partir de l'interface le reprďż˝sentant.
	 * 
	 * @param clazz
	 *            La classe de l'interface du packet.
	 * @return Une nouvelle instance du packet.
	 */
	public abstract <T extends BadblockOutPacket> T createPacket(Class<T> clazz);

	/**
	 * Crďż˝ďż˝ une particule. Envoyable grďż˝ce ďż˝
	 * {@link BadblockPlayer#sendParticle(Location, ParticleEffect)}
	 * 
	 * @param type
	 *            Le type de la particule
	 * @return La particule
	 */
	public abstract ParticleEffect createParticleEffect(ParticleEffectType type);

	/**
	 * Crďż˝e un datawatcher ďż˝ partir de l'interface le reprďż˝sentant.
	 * 
	 * @param clazz
	 *            La classe de l'interface du DataWatcher.
	 * @return Une nouvelle instance du packet.
	 */
	public abstract <T extends WatcherEntity> T createWatcher(Class<T> clazz);

	/**
	 * Active l'anti-spawnkill
	 */
	public abstract void enableAntiSpawnKill();

	/**
	 * Active l'anti-spambow
	 */
	public abstract void enableAntiBowSpam(long milliseconds);

	/**
	 * Active le formattage du chat par l'API
	 * 
	 * @param format
	 *            Si le formattage est activďż˝
	 * @param doTeamChat
	 *            Si (lorsque formattage activďż˝) le les messages prďż˝cďż˝dďż˝s de $
	 *            sont pour la team
	 */
	public abstract void formatChat(boolean format, boolean doTeamChat);

	/**
	 * Active le formattage du chat par l'API
	 * 
	 * @param format
	 *            Si le formattage est activďż˝
	 * @param doTeamChat
	 *            Si (lorsque formattage activďż˝) le les messages prďż˝cďż˝dďż˝s de $
	 *            sont pour la team
	 * @param custom
	 *            Si le chat est diffďż˝rent d'autres serveurs, le nom custom
	 */
	public abstract void formatChat(boolean format, boolean doTeamChat, String custom);

	/**
	 * Rďż˝cupďż˝re le BadblockScoreboard commun ďż˝ tous les joueurs permettant
	 * d'afficher la vie, les noms de teams, les votes, ...
	 */
	public abstract BadblockScoreboard getBadblockScoreboard();

	/**
	 * Rďż˝cupďż˝re l'objet permettant de gďż˝nďż˝rer des coffres alďż˝atoires
	 * 
	 * @return L'objet
	 */
	public abstract ChestGenerator getChestGenerator();

	/**
	 * Crďż˝ďż˝ un inventaire de marchant custom
	 * 
	 * @return Le marchant
	 */
	public abstract CustomMerchantInventory getCustomMerchantInventory();

	/**
	 * Rďż˝cupďż˝re le systďż˝me de gestion des statuts
	 * 
	 * @return Le systďż˝me de gestion des statuts
	 */
	public abstract GameServer getGameServer();

	/**
	 * Rďż˝cupďż˝re le systďż˝me de messaging et internationalisation
	 * 
	 * @return Le systďż˝me
	 */
	public abstract I18n getI18n();

	/**
	 * Rďż˝cupďż˝re l'instance de la configuration des items de join
	 * 
	 * @return L'instance
	 */
	public abstract JoinItems getJoinItems();

	/**
	 * Rďż˝cupďż˝re le manager de contenu (rďż˝cupďż˝ration / give des items) des kits,
	 * pour le jeu charger. Par dďż˝faut, charge simplement un inventaire
	 * 
	 * @return Le manager
	 */
	public abstract PlayerKitContentManager getKitContentManager();

	/**
	 * Rďż˝cupďż˝re la classe permettant de communiquer avec Ladder
	 * 
	 * @return La classe permettant de communiquer avec Ladder
	 */
	public abstract LadderSpeaker getLadderDatabase();

	/**
	 * Rďż˝cupďż˝re la liste des portails chargďż˝s
	 * 
	 * @return La liste des portails
	 */
	public abstract Collection<Portal> getLoadedPortals();

	/**
	 * Rďż˝cupďż˝re la classe protďż˝geant la map. Par dďż˝faut une protection trďż˝s
	 * permissive (ne change rien :p).
	 * 
	 * @return La classe
	 */
	public abstract MapProtector getMapProtector();

	/**
	 * Rďż˝cupďż˝re les donnďż˝es d'un joueur ayant dďż˝connecter aprďż˝s le dďż˝but de la
	 * partie, si le jeu a demandďż˝ la sauvegarde
	 * 
	 * @param name
	 *            Le joueur
	 * @return Les donnďż˝es joueurs (ou null)
	 */
	public abstract BadblockOfflinePlayer getOfflinePlayer(String name);

	/**
	 * Rďż˝cupďż˝re un portail par une location
	 * 
	 * @param location
	 *            La location
	 * @return Le portail
	 */
	public abstract Portal getPortal(Location location);

	/**
	 * Rďż˝cupďż˝re un portail par son nom
	 * 
	 * @param name
	 *            Le nom
	 * @return Le portail
	 */
	public abstract Portal getPortal(String name);

	/**
	 * Rďż˝cupďż˝re la classe permettant de communiquer avec RabbitMQ
	 * 
	 * @return La classe permettant de communiquer avec RabbitMQ
	 * @author xMalware
	 */
	public abstract RabbitSpeaker getRabbitSpeaker();

	/**
	 * Rďż˝cupďż˝re le type de partie
	 * 
	 * @return Le type
	 */
	public abstract RunType getRunType();

	/**
	 * Retourne une position ou le joueur peut ďż˝tre tďż˝lďż˝portďż˝ sans crainte.
	 * 
	 * @param location
	 *            La position initiale
	 * @return La position modifiďż˝e (ou null en cas de problďż˝me)
	 */
	public abstract Location getSafeLocation(Location location);

	/**
	 * Rďż˝cupďż˝re le bonus ďż˝venementiel en badcoins
	 * 
	 * @return Le bonus
	 */
	public abstract double getServerBadcoinsBonus();

	/**
	 * Rďż˝cupďż˝re le bonus ďż˝venementiel en xp
	 * 
	 * @return Le bonus
	 */
	public abstract double getServerXpBonus();

	/**
	 * Rďż˝cupďż˝re le systďż˝me de gestion de panneau i18n
	 * 
	 * @return Le systďż˝me
	 */
	public abstract SignManager getSignManager();

	/**
	 * Rďż˝cupďż˝re la base de donnďż˝e SQl
	 * 
	 * @return La base de donnďż˝e
	 */
	public abstract SQLDatabase getSqlDatabase();

	/**
	 * Rďż˝cupďż˝re une team ďż˝ partir de son nom interne (voir
	 * {@link #getTeamsKey()}}.
	 * 
	 * @param key
	 *            Le nom interne
	 * @return La team (ou null si elle n'existe pas).
	 */
	public abstract BadblockTeam getTeam(String key);

	/**
	 * Rďż˝cupďż˝re les teams registers grďż˝ce ďż˝
	 * {@link #registerTeams(int, Class, ConfigurationSection)}
	 * 
	 * @return Une collection de team
	 */
	public abstract Collection<BadblockTeam> getTeams();

	/**
	 * Retourne les noms internes des teams, registers grďż˝ce ďż˝
	 * {@link #registerTeams(int, Class, ConfigurationSection)}
	 * 
	 * @return Une collection de string
	 */
	public abstract Collection<String> getTeamsKey();

	/**
	 * Rďż˝cupďż˝re les joueurs dans la whitelist
	 * 
	 * @return Les joueurs
	 */
	public abstract Collection<String> getWhitelistedPlayers();

	/**
	 * Rďż˝cupďż˝re si la whitelist est on ou non
	 * 
	 * @return Le statut
	 */
	public abstract boolean getWhitelistStatus();

	/**
	 * Rďż˝cupďż˝re si le compas sďż˝lectionne la cible la plus proche au clique droit
	 * 
	 * @return Un boolean
	 */
	public abstract boolean isCompassSelectNearestTarget();

	/**
	 * Vďż˝rifie si le joueur est whitelistďż˝
	 * 
	 * @param player
	 *            Le joueur
	 * @return Si il est whitelistďż˝
	 */
	public abstract boolean isWhitelisted(String player);

	/**
	 * Ecoute sur un packet entrant avec un listener
	 * 
	 * @param listener
	 *            Le listener
	 */
	public abstract <T extends BadblockInPacket> void listenAtPacket(InPacketListener<T> listener);

	/**
	 * Ecoute sur un packet sortant avec un listener
	 * 
	 * @param listener
	 *            Le listener
	 */
	public abstract <T extends BadblockOutPacket> void listenAtPacket(OutPacketListener<T> listener);

	/**
	 * Ecouter tous les packets
	 * 
	 * @param listener
	 *            Le listener
	 */
	public abstract void listenAllPackets(GlobalPacketListener listener);

	/**
	 * Charge une configuration JSON depuis un fichier
	 * 
	 * @param file
	 *            Le fichier
	 * @return La configuration
	 */
	public abstract BadConfiguration loadConfiguration(File file);

	/**
	 * Charge une configuration JSON depuis un objet JSON
	 * 
	 * @param content
	 *            L'object
	 * @return La configuration
	 */
	public abstract BadConfiguration loadConfiguration(JsonObject content);

	/**
	 * Rďż˝cupďż˝re une liste de kit via un nom de jeu
	 * 
	 * @param game
	 *            Le nom du jeu
	 * @return Les kits, en fonction de leur nom interne
	 */
	public abstract Map<String, PlayerKit> loadKits(String game);

	/**
	 * Manage les portails, en les chargeants / sauvegardants avec le dossier
	 * donnďż˝
	 * 
	 * @param folder
	 *            Le dossier
	 */
	public abstract void managePortals(File folder);

	/**
	 * Manage les shops, en les chargeants / sauvegardants avec le dossier donnďż˝
	 * 
	 * @param folder
	 *            Le dossier
	 */
	public abstract void manageShops(File folder);

	/**
	 * Register les teams (ne peut ďż˝tre appelďż˝ qu'une fois !) ďż˝ partir d'une
	 * configuration.
	 * 
	 * @param maxPlayers
	 *            Le nombre maximum de joueurs par teams.
	 * @param clazz
	 *            La classe reprďż˝sentant les donnďż˝es in-game d'une team.
	 * @param configuration
	 *            La configuration des teams.
	 */
	public abstract void registerTeams(int maxPlayers, ConfigurationSection configuration);

	/**
	 * Fait en sorte que le compas sďż˝lectionne la cible la plus proche au clique
	 * droit
	 * 
	 * @param selectNearestTarget
	 *            Un boolean
	 */
	public abstract void setCompassSelectNearestTarget(boolean selectNearestTarget);

	/**
	 * Redďż˝finit le manager de kit par dďż˝faut, oďż˝ changer la paramďż˝tre allowDrop
	 * (de base ďż˝ true)
	 * 
	 * @param allowDrop
	 *            Si les items du kit peuvent ďż˝tre drop par le joueur ou ďż˝ la
	 *            mort
	 */
	public abstract void setDefaultKitContentManager(boolean allowDrop);

	/**
	 * Dďż˝finit le manager de contenu (rďż˝cupďż˝ration / give des items) des kits,
	 * pour le jeu charger. Par dďż˝faut, charge simplement un inventaire
	 * 
	 * @param manager
	 *            Le manager
	 */
	public abstract void setKitContentManager(PlayerKitContentManager manager);

	/**
	 * Change la classe protďż˝geant la map
	 * 
	 * @param protector
	 *            La classe
	 */
	public abstract void setMapProtector(MapProtector protector);

	/**
	 * Dďż˝finit si la whitelist est on ou non
	 * 
	 * @param on
	 *            Si elle est on
	 */
	public abstract void setWhitelistStatus(boolean on);

	/**
	 * Fait spawn une entitďż˝ custom
	 * 
	 * @param location
	 *            La position de l'entitďż˝
	 * @param type
	 *            Le type de l'entitďż˝
	 * @return L'entitďż˝
	 */
	public abstract CustomCreature spawnCustomEntity(Location location, EntityType type);

	/**
	 * Fait spawn une fausse armor stand
	 * 
	 * @param location
	 *            Sa position
	 * @return L'entitďż˝
	 */
	public abstract FakeEntity<WatcherArmorStand> spawnFakeArmorStand(Location location);

	/**
	 * Fait spawn un faux player
	 * 
	 * @param location
	 *            Sa position
	 * @param infos Les informations sur le joueur
	 * @return L'entitďż˝
	 */
	public abstract FakeEntity<WatcherLivingEntity> spawnFakePlayer(Location location, PlayerInfo infos);


	/**
	 * Spawn un faux falling block
	 * 
	 * @param location
	 *            La position
	 * @param type
	 *            Le type de falling block
	 * @param data
	 *            Le 'data'
	 * @return L'entitďż˝
	 */
	public abstract FakeEntity<WatcherEntity> spawnFakeFallingBlock(Location location, Material type, byte data);

	/**
	 * Crďż˝e une nouvelle fausse entitďż˝.
	 * 
	 * @param location
	 *            Sa position
	 * @param type
	 *            Le type de l'entitďż˝
	 * @param clazz
	 *            La classe de ses DataWatchers
	 * @return La fausse entitďż˝
	 */
	public abstract <T extends WatcherEntity> FakeEntity<T> spawnFakeLivingEntity(Location location, EntityType type,
			Class<T> clazz);

	/**
	 * Supprime une Team ayant perdue
	 * 
	 * @param team
	 *            La Team ďż˝ supprimer.
	 */
	public abstract void unregisterTeam(BadblockTeam team);

	/**
	 * Enlďż˝ve un joueur temporairement (jusqu'au redďż˝marrage) de la whitelist
	 * 
	 * @param player
	 *            Le joueur
	 */
	public abstract void unwhitelistPlayer(String player);

	/**
	 * Met temporairement (jusqu'au redďż˝marrage) un joueur dans la whitelist
	 * 
	 * @param player
	 *            Le joueur
	 */
	public abstract void whitelistPlayer(String player);

	public abstract void setEmptyChunks(CuboidSelection selection, boolean exclusion);

	public abstract void setLightChunks(CuboidSelection selection, boolean exclusion);
	/**
	 * Charge une zone en un certains nombre de ticks serveurs (de maniďż˝re synchrone)
	 * @param selection La zone
	 * @param ticks Le nombre de ticks
	 */
	public abstract void loadChunks(CuboidSelection selection, int ticks);
	public void updateScoreboards(){
		getOnlinePlayers().stream().filter(player -> player.getCustomObjective() != null).forEach(player -> player.getCustomObjective().generate());
	}

	public abstract boolean isLeaverBusterEnabled();

	public abstract void setLeaverBusterEnabled(boolean enabled);

	public abstract void balancePlayers(BadblockPlayer leader, List<UUID> slaves);

	public abstract ItemStack generateQrCode(World world, String content);

	public abstract ItemStack generateGoogleAuthQrCode(BadblockPlayer player, String googleAuthKey, String image);

	public abstract void setAntiAfk(boolean enabled);

}
