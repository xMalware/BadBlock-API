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
 * La classe principale de l'API. Elle permet de r�cup�rer la plupart de ses
 * composants (sauf ceux disponibles en statics) ainsi que de la configurer.<br>
 * Les principaux �l�ments � configurer sont :
 * <ul>
 * <li>Les kits, car il est tr�s utile de garder une base commune � tous les
 * jeux, n�anmoins la gestion des items est diff�rentes entre les jeux (ex :
 * splatoon, les armes ne sont pas dans la configuration). C'est pourquoi il
 * faut d�finir la classe permettant de load les items.</li>
 * <li>Les donn�es de joueur InGame, car il est inutile de cr�er une Map
 * uniquement pour ces donn�es, et que les donn�es entre les jeux peuvent �tre
 * tr�s diff�rentes.</li>
 * <li>ect ...</li>
 * </ul>
 * <br>
 * Le principe de l'API r�side donc sur deux points : elle est � la fois une
 * base � tous les jeux, pour qu'ils se ressemblent (et soient plus rapidement
 * mettables � jours) ainsi qu'une longue liste d'utilitaires vari�s, pour
 * simplifier le d�veloppement.<br>
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

	/**
	 * R�cup�re le syst�me d'internationalisation (pour �viter de r�cup�rer
	 * l'instance).
	 * 
	 * @return Le syst�me d'i18n
	 */
	public static I18n i18n() {
		return API.getI18n();
	}
	
	/**
	 * Log un message 'normal' (plus rapide que de r�cup�rrer le logger)
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
	 * appel�)
	 * 
	 * @param message
	 *            Le message color�
	 */
	public static void logColor(String message) {
		Bukkit.getConsoleSender().sendMessage(i18n().replaceColors(message));
	}

	/**
	 * Log un message d'erreur (plus rapide que de r�cup�rrer le logger)
	 * 
	 * @param message
	 *            Le message
	 */
	public static void logError(String message) {
		Bukkit.getLogger().log(Level.SEVERE, message);
	}

	/**
	 * Log un message d'alerte (plus rapide que de r�cup�rrer le logger)
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
			return type + " n�" + splitted[1];
		}
	}
	
	public static String getServerName(){
		return Bukkit.getServer().getServerName();
	}
	
	/**
	 * R�cup�re l'ensemble des joueurs en ligne
	 * @return Une liste non modifiable
	 */
	public abstract List<BadblockPlayer> getOnlinePlayers();

	/**
	 * R�cup�re l'ensemble des joueurs en ligne n'�tant pas en mode spectateur
	 * @return Une liste non modifiable
	 */
	public abstract List<BadblockPlayer> getRealOnlinePlayers();
	
	/**
	 * Permet d'�quilibrer les teams
	 * 
	 * @param sameSize
	 *            Si il faut que toutes les teams ai le m�me nombre de joueurs
	 */
	public abstract void balanceTeams(boolean sameSize);

	/**
	 * Cr�� un nouveau objective custom
	 * 
	 * @param name
	 *            Le nom (interne) de l'objective
	 * @return Le CustomObjective cr��.
	 */
	public abstract CustomObjective buildCustomObjective(String name);

	/**
	 * Cr�� un nouvel inventaire custom
	 * 
	 * @param line
	 *            Le nombre de ligne (et non pas de cases !)
	 * @param displayName
	 *            Le nom d'affichage de l'inventaire custom
	 * @return Le nouvel inventaire
	 */
	public abstract CustomInventory createCustomInventory(int line, String displayName);

	/**
	 * Cr�e un nouveau extra pour un item
	 * 
	 * @param itemStack
	 *            L'item stack auquel ajout� l'extra
	 * @return Un nouvel extra ou celui existant si d�j� register pour la
	 *         l'item.
	 */
	public abstract ItemStackExtra createItemStackExtra(ItemStack itemStack);

	/**
	 * Cr�e une nouvelle factory d'items
	 * 
	 * @return La factory d'items
	 */
	public abstract ItemStackFactory createItemStackFactory();

	/**
	 * Cr�� une nouvelle factory d'items
	 * 
	 * @param item
	 *            L'item de base qui sera utilis� pour le param�trage de la
	 *            factory
	 * @return La factory
	 */
	public abstract ItemStackFactory createItemStackFactory(ItemStack item);

	/**
	 * Cr�e un packet � partir de l'interface le repr�sentant.
	 * 
	 * @param clazz
	 *            La classe de l'interface du packet.
	 * @return Une nouvelle instance du packet.
	 */
	public abstract <T extends BadblockOutPacket> T createPacket(Class<T> clazz);

	/**
	 * Cr�� une particule. Envoyable gr�ce �
	 * {@link BadblockPlayer#sendParticle(Location, ParticleEffect)}
	 * 
	 * @param type
	 *            Le type de la particule
	 * @return La particule
	 */
	public abstract ParticleEffect createParticleEffect(ParticleEffectType type);

	/**
	 * Cr�e un datawatcher � partir de l'interface le repr�sentant.
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
	 * Active le formattage du chat par l'API
	 * 
	 * @param format
	 *            Si le formattage est activ�
	 * @param doTeamChat
	 *            Si (lorsque formattage activ�) le les messages pr�c�d�s de $
	 *            sont pour la team
	 */
	public abstract void formatChat(boolean format, boolean doTeamChat);

	/**
	 * Active le formattage du chat par l'API
	 * 
	 * @param format
	 *            Si le formattage est activ�
	 * @param doTeamChat
	 *            Si (lorsque formattage activ�) le les messages pr�c�d�s de $
	 *            sont pour la team
	 * @param custom
	 *            Si le chat est diff�rent d'autres serveurs, le nom custom
	 */
	public abstract void formatChat(boolean format, boolean doTeamChat, String custom);

	/**
	 * R�cup�re le BadblockScoreboard commun � tous les joueurs permettant
	 * d'afficher la vie, les noms de teams, les votes, ...
	 */
	public abstract BadblockScoreboard getBadblockScoreboard();

	/**
	 * R�cup�re l'objet permettant de g�n�rer des coffres al�atoires
	 * 
	 * @return L'objet
	 */
	public abstract ChestGenerator getChestGenerator();

	/**
	 * Cr�� un inventaire de marchant custom
	 * 
	 * @return Le marchant
	 */
	public abstract CustomMerchantInventory getCustomMerchantInventory();

	/**
	 * R�cup�re le syst�me de gestion des statuts
	 * 
	 * @return Le syst�me de gestion des statuts
	 */
	public abstract GameServer getGameServer();

	/**
	 * R�cup�re le syst�me de messaging et internationalisation
	 * 
	 * @return Le syst�me
	 */
	public abstract I18n getI18n();

	/**
	 * R�cup�re l'instance de la configuration des items de join
	 * 
	 * @return L'instance
	 */
	public abstract JoinItems getJoinItems();

	/**
	 * R�cup�re le manager de contenu (r�cup�ration / give des items) des kits,
	 * pour le jeu charger. Par d�faut, charge simplement un inventaire
	 * 
	 * @return Le manager
	 */
	public abstract PlayerKitContentManager getKitContentManager();

	/**
	 * R�cup�re la classe permettant de communiquer avec Ladder
	 * 
	 * @return La classe permettant de communiquer avec Ladder
	 */
	public abstract LadderSpeaker getLadderDatabase();

	/**
	 * R�cup�re la liste des portails charg�s
	 * 
	 * @return La liste des portails
	 */
	public abstract Collection<Portal> getLoadedPortals();

	/**
	 * R�cup�re la classe prot�geant la map. Par d�faut une protection tr�s
	 * permissive (ne change rien :p).
	 * 
	 * @return La classe
	 */
	public abstract MapProtector getMapProtector();

	/**
	 * R�cup�re les donn�es d'un joueur ayant d�connecter apr�s le d�but de la
	 * partie, si le jeu a demand� la sauvegarde
	 * 
	 * @param name
	 *            Le joueur
	 * @return Les donn�es joueurs (ou null)
	 */
	public abstract BadblockOfflinePlayer getOfflinePlayer(String name);

	/**
	 * R�cup�re un portail par une location
	 * 
	 * @param location
	 *            La location
	 * @return Le portail
	 */
	public abstract Portal getPortal(Location location);

	/**
	 * R�cup�re un portail par son nom
	 * 
	 * @param name
	 *            Le nom
	 * @return Le portail
	 */
	public abstract Portal getPortal(String name);

	/**
	 * R�cup�re la classe permettant de communiquer avec RabbitMQ
	 * 
	 * @return La classe permettant de communiquer avec RabbitMQ
	 * @author xMalware
	 */
	public abstract RabbitSpeaker getRabbitSpeaker();

	/**
	 * R�cup�re le type de partie
	 * 
	 * @return Le type
	 */
	public abstract RunType getRunType();

	/**
	 * Retourne une position ou le joueur peut �tre t�l�port� sans crainte.
	 * 
	 * @param location
	 *            La position initiale
	 * @return La position modifi�e (ou null en cas de probl�me)
	 */
	public abstract Location getSafeLocation(Location location);

	/**
	 * R�cup�re le bonus �venementiel en badcoins
	 * 
	 * @return Le bonus
	 */
	public abstract double getServerBadcoinsBonus();

	/**
	 * R�cup�re le bonus �venementiel en xp
	 * 
	 * @return Le bonus
	 */
	public abstract double getServerXpBonus();

	/**
	 * R�cup�re le syst�me de gestion de panneau i18n
	 * 
	 * @return Le syst�me
	 */
	public abstract SignManager getSignManager();

	/**
	 * R�cup�re la base de donn�e SQl
	 * 
	 * @return La base de donn�e
	 */
	public abstract SQLDatabase getSqlDatabase();

	/**
	 * R�cup�re une team � partir de son nom interne (voir
	 * {@link #getTeamsKey()}}.
	 * 
	 * @param key
	 *            Le nom interne
	 * @return La team (ou null si elle n'existe pas).
	 */
	public abstract BadblockTeam getTeam(String key);

	/**
	 * R�cup�re les teams registers gr�ce �
	 * {@link #registerTeams(int, Class, ConfigurationSection)}
	 * 
	 * @return Une collection de team
	 */
	public abstract Collection<BadblockTeam> getTeams();

	/**
	 * Retourne les noms internes des teams, registers gr�ce �
	 * {@link #registerTeams(int, Class, ConfigurationSection)}
	 * 
	 * @return Une collection de string
	 */
	public abstract Collection<String> getTeamsKey();

	/**
	 * R�cup�re les joueurs dans la whitelist
	 * 
	 * @return Les joueurs
	 */
	public abstract Collection<String> getWhitelistedPlayers();

	/**
	 * R�cup�re si la whitelist est on ou non
	 * 
	 * @return Le statut
	 */
	public abstract boolean getWhitelistStatus();

	/**
	 * R�cup�re si le compas s�lectionne la cible la plus proche au clique droit
	 * 
	 * @return Un boolean
	 */
	public abstract boolean isCompassSelectNearestTarget();

	/**
	 * V�rifie si le joueur est whitelist�
	 * 
	 * @param player
	 *            Le joueur
	 * @return Si il est whitelist�
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
	 * R�cup�re une liste de kit via un nom de jeu
	 * 
	 * @param game
	 *            Le nom du jeu
	 * @return Les kits, en fonction de leur nom interne
	 */
	public abstract Map<String, PlayerKit> loadKits(String game);

	/**
	 * Manage les portails, en les chargeants / sauvegardants avec le dossier
	 * donn�
	 * 
	 * @param folder
	 *            Le dossier
	 */
	public abstract void managePortals(File folder);

	/**
	 * Manage les shops, en les chargeants / sauvegardants avec le dossier donn�
	 * 
	 * @param folder
	 *            Le dossier
	 */
	public abstract void manageShops(File folder);

	/**
	 * Register les teams (ne peut �tre appel� qu'une fois !) � partir d'une
	 * configuration.
	 * 
	 * @param maxPlayers
	 *            Le nombre maximum de joueurs par teams.
	 * @param clazz
	 *            La classe repr�sentant les donn�es in-game d'une team.
	 * @param configuration
	 *            La configuration des teams.
	 */
	public abstract void registerTeams(int maxPlayers, ConfigurationSection configuration);

	/**
	 * Fait en sorte que le compas s�lectionne la cible la plus proche au clique
	 * droit
	 * 
	 * @param selectNearestTarget
	 *            Un boolean
	 */
	public abstract void setCompassSelectNearestTarget(boolean selectNearestTarget);

	/**
	 * Red�finit le manager de kit par d�faut, o� changer la param�tre allowDrop
	 * (de base � true)
	 * 
	 * @param allowDrop
	 *            Si les items du kit peuvent �tre drop par le joueur ou � la
	 *            mort
	 */
	public abstract void setDefaultKitContentManager(boolean allowDrop);

	/**
	 * D�finit le manager de contenu (r�cup�ration / give des items) des kits,
	 * pour le jeu charger. Par d�faut, charge simplement un inventaire
	 * 
	 * @param manager
	 *            Le manager
	 */
	public abstract void setKitContentManager(PlayerKitContentManager manager);

	/**
	 * Change la classe prot�geant la map
	 * 
	 * @param protector
	 *            La classe
	 */
	public abstract void setMapProtector(MapProtector protector);

	/**
	 * D�finit si la whitelist est on ou non
	 * 
	 * @param on
	 *            Si elle est on
	 */
	public abstract void setWhitelistStatus(boolean on);

	/**
	 * Fait spawn une entit� custom
	 * 
	 * @param location
	 *            La position de l'entit�
	 * @param type
	 *            Le type de l'entit�
	 * @return L'entit�
	 */
	public abstract CustomCreature spawnCustomEntity(Location location, EntityType type);

	/**
	 * Fait spawn une fausse armor stand
	 * 
	 * @param location
	 *            Sa position
	 * @return L'entit�
	 */
	public abstract FakeEntity<WatcherArmorStand> spawnFakeArmorStand(Location location);

	/**
	 * Fait spawn un faux player
	 * 
	 * @param location
	 *            Sa position
	 * @param infos Les informations sur le joueur
	 * @return L'entit�
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
	 * @return L'entit�
	 */
	public abstract FakeEntity<WatcherEntity> spawnFakeFallingBlock(Location location, Material type, byte data);

	/**
	 * Cr�e une nouvelle fausse entit�.
	 * 
	 * @param location
	 *            Sa position
	 * @param type
	 *            Le type de l'entit�
	 * @param clazz
	 *            La classe de ses DataWatchers
	 * @return La fausse entit�
	 */
	public abstract <T extends WatcherEntity> FakeEntity<T> spawnFakeLivingEntity(Location location, EntityType type,
			Class<T> clazz);

	/**
	 * Supprime une Team ayant perdue
	 * 
	 * @param team
	 *            La Team � supprimer.
	 */
	public abstract void unregisterTeam(BadblockTeam team);

	/**
	 * Enl�ve un joueur temporairement (jusqu'au red�marrage) de la whitelist
	 * 
	 * @param player
	 *            Le joueur
	 */
	public abstract void unwhitelistPlayer(String player);

	/**
	 * Met temporairement (jusqu'au red�marrage) un joueur dans la whitelist
	 * 
	 * @param player
	 *            Le joueur
	 */
	public abstract void whitelistPlayer(String player);
	
	public abstract void setEmptyChunks(CuboidSelection selection, boolean exclusion);
	
	public abstract void setLightChunks(CuboidSelection selection, boolean exclusion);
	
	/**
	 * Charge une zone en un certains nombre de ticks serveurs (de mani�re synchrone)
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
}
