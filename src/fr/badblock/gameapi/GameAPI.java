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
 * La classe principale de l'API. Elle permet de rÄ�Å¼Ë�cupÄ�Å¼Ë�rer la plupart de ses
 * composants (sauf ceux disponibles en statics) ainsi que de la configurer.<br>
 * Les principaux Ä�Å¼Ë�lÄ�Å¼Ë�ments Ä�Å¼Ë� configurer sont :
 * <ul>
 * <li>Les kits, car il est trÄ�Å¼Ë�s utile de garder une base commune Ä�Å¼Ë� tous les
 * jeux, nÄ�Å¼Ë�anmoins la gestion des items est diffÄ�Å¼Ë�rentes entre les jeux (ex :
 * splatoon, les armes ne sont pas dans la configuration). C'est pourquoi il
 * faut dÄ�Å¼Ë�finir la classe permettant de load les items.</li>
 * <li>Les donnÄ�Å¼Ë�es de joueur InGame, car il est inutile de crÄ�Å¼Ë�er une Map
 * uniquement pour ces donnÄ�Å¼Ë�es, et que les donnÄ�Å¼Ë�es entre les jeux peuvent Ä�Å¼Ë�tre
 * trÄ�Å¼Ë�s diffÄ�Å¼Ë�rentes.</li>
 * <li>ect ...</li>
 * </ul>
 * <br>
 * Le principe de l'API rÄ�Å¼Ë�side donc sur deux points : elle est Ä�Å¼Ë� la fois une
 * base Ä�Å¼Ë� tous les jeux, pour qu'ils se ressemblent (et soient plus rapidement
 * mettables Ä�Å¼Ë� jours) ainsi qu'une longue liste d'utilitaires variÄ�Å¼Ë�s, pour
 * simplifier le dÄ�Å¼Ë�veloppement.<br>
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
	protected static Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).serializeSpecialFloatingPointValues().serializeNulls().disableHtmlEscaping()
	.create();
	@Getter
	protected static Gson prettyGson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
	.disableHtmlEscaping().setPrettyPrinting().create();
	@Getter@Setter
	protected static boolean isJoinable = true;
	@Getter@Setter
	private boolean finished;

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le systÄ�Å¼Ë�me d'internationalisation (pour Ä�Å¼Ë�viter de rÄ�Å¼Ë�cupÄ�Å¼Ë�rer
	 * l'instance).
	 * 
	 * @return Le systÄ�Å¼Ë�me d'i18n
	 */
	public static I18n i18n() {
		return API.getI18n();
	}

	/**
	 * Log un message 'normal' (plus rapide que de rÄ�Å¼Ë�cupÄ�Å¼Ë�rrer le logger)
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
	 * appelÄ�Å¼Ë�)
	 * 
	 * @param message
	 *            Le message colorÄ�Å¼Ë�
	 */
	public static void logColor(String message) {
		Bukkit.getConsoleSender().sendMessage(i18n().replaceColors(message));
	}

	/**
	 * Log un message d'erreur (plus rapide que de rÄ�Å¼Ë�cupÄ�Å¼Ë�rrer le logger)
	 * 
	 * @param message
	 *            Le message
	 */
	public static void logError(String message) {
		Bukkit.getLogger().log(Level.SEVERE, message);
	}

	/**
	 * Log un message d'alerte (plus rapide que de rÄ�Å¼Ë�cupÄ�Å¼Ë�rrer le logger)
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
			return type + " nÄ�Å¼Ë�" + splitted[1];
		}
	}

	public static String getServerName(){
		return Bukkit.getServer().getServerName();
	}

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re l'ensemble des joueurs en ligne
	 * @return Une liste non modifiable
	 */
	public abstract List<BadblockPlayer> getOnlinePlayers();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re l'ensemble des joueurs en ligne n'Ä�Å¼Ë�tant pas en mode spectateur
	 * @return Une liste non modifiable
	 */
	public abstract List<BadblockPlayer> getRealOnlinePlayers();

	/**
	 * Permet d'Ä�Å¼Ë�quilibrer les teams
	 * 
	 * @param sameSize
	 *            Si il faut que toutes les teams ai le mÄ�Å¼Ë�me nombre de joueurs
	 */
	public abstract void balanceTeams(boolean sameSize);

	/**
	 * CrÄ�Å¼Ë�Ä�Å¼Ë� un nouveau objective custom
	 * 
	 * @param name
	 *            Le nom (interne) de l'objective
	 * @return Le CustomObjective crÄ�Å¼Ë�Ä�Å¼Ë�.
	 */
	public abstract CustomObjective buildCustomObjective(String name);

	/**
	 * CrÄ�Å¼Ë�Ä�Å¼Ë� un nouvel inventaire custom
	 * 
	 * @param line
	 *            Le nombre de ligne (et non pas de cases !)
	 * @param displayName
	 *            Le nom d'affichage de l'inventaire custom
	 * @return Le nouvel inventaire
	 */
	public abstract CustomInventory createCustomInventory(int line, String displayName);

	/**
	 * CrÄ�Å¼Ë�e un nouveau extra pour un item
	 * 
	 * @param itemStack
	 *            L'item stack auquel ajoutÄ�Å¼Ë� l'extra
	 * @return Un nouvel extra ou celui existant si dÄ�Å¼Ë�jÄ�Å¼Ë� register pour la
	 *         l'item.
	 */
	public abstract ItemStackExtra createItemStackExtra(ItemStack itemStack);

	/**
	 * CrÄ�Å¼Ë�e une nouvelle factory d'items
	 * 
	 * @return La factory d'items
	 */
	public abstract ItemStackFactory createItemStackFactory();

	/**
	 * CrÄ�Å¼Ë�Ä�Å¼Ë� une nouvelle factory d'items
	 * 
	 * @param item
	 *            L'item de base qui sera utilisÄ�Å¼Ë� pour le paramÄ�Å¼Ë�trage de la
	 *            factory
	 * @return La factory
	 */
	public abstract ItemStackFactory createItemStackFactory(ItemStack item);

	/**
	 * CrÄ�Å¼Ë�e un packet Ä�Å¼Ë� partir de l'interface le reprÄ�Å¼Ë�sentant.
	 * 
	 * @param clazz
	 *            La classe de l'interface du packet.
	 * @return Une nouvelle instance du packet.
	 */
	public abstract <T extends BadblockOutPacket> T createPacket(Class<T> clazz);

	/**
	 * CrÄ�Å¼Ë�Ä�Å¼Ë� une particule. Envoyable grÄ�Å¼Ë�ce Ä�Å¼Ë�
	 * {@link BadblockPlayer#sendParticle(Location, ParticleEffect)}
	 * 
	 * @param type
	 *            Le type de la particule
	 * @return La particule
	 */
	public abstract ParticleEffect createParticleEffect(ParticleEffectType type);

	/**
	 * CrÄ�Å¼Ë�e un datawatcher Ä�Å¼Ë� partir de l'interface le reprÄ�Å¼Ë�sentant.
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
	 *            Si le formattage est activÄ�Å¼Ë�
	 * @param doTeamChat
	 *            Si (lorsque formattage activÄ�Å¼Ë�) le les messages prÄ�Å¼Ë�cÄ�Å¼Ë�dÄ�Å¼Ë�s de $
	 *            sont pour la team
	 */
	public abstract void formatChat(boolean format, boolean doTeamChat);

	/**
	 * Active le formattage du chat par l'API
	 * 
	 * @param format
	 *            Si le formattage est activÄ�Å¼Ë�
	 * @param doTeamChat
	 *            Si (lorsque formattage activÄ�Å¼Ë�) le les messages prÄ�Å¼Ë�cÄ�Å¼Ë�dÄ�Å¼Ë�s de $
	 *            sont pour la team
	 * @param custom
	 *            Si le chat est diffÄ�Å¼Ë�rent d'autres serveurs, le nom custom
	 */
	public abstract void formatChat(boolean format, boolean doTeamChat, String custom);

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le BadblockScoreboard commun Ä�Å¼Ë� tous les joueurs permettant
	 * d'afficher la vie, les noms de teams, les votes, ...
	 */
	public abstract BadblockScoreboard getBadblockScoreboard();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re l'objet permettant de gÄ�Å¼Ë�nÄ�Å¼Ë�rer des coffres alÄ�Å¼Ë�atoires
	 * 
	 * @return L'objet
	 */
	public abstract ChestGenerator getChestGenerator();

	/**
	 * CrÄ�Å¼Ë�Ä�Å¼Ë� un inventaire de marchant custom
	 * 
	 * @return Le marchant
	 */
	public abstract CustomMerchantInventory getCustomMerchantInventory();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le systÄ�Å¼Ë�me de gestion des statuts
	 * 
	 * @return Le systÄ�Å¼Ë�me de gestion des statuts
	 */
	public abstract GameServer getGameServer();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le systÄ�Å¼Ë�me de messaging et internationalisation
	 * 
	 * @return Le systÄ�Å¼Ë�me
	 */
	public abstract I18n getI18n();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re l'instance de la configuration des items de join
	 * 
	 * @return L'instance
	 */
	public abstract JoinItems getJoinItems();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le manager de contenu (rÄ�Å¼Ë�cupÄ�Å¼Ë�ration / give des items) des kits,
	 * pour le jeu charger. Par dÄ�Å¼Ë�faut, charge simplement un inventaire
	 * 
	 * @return Le manager
	 */
	public abstract PlayerKitContentManager getKitContentManager();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re la classe permettant de communiquer avec Ladder
	 * 
	 * @return La classe permettant de communiquer avec Ladder
	 */
	public abstract LadderSpeaker getLadderDatabase();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re la liste des portails chargÄ�Å¼Ë�s
	 * 
	 * @return La liste des portails
	 */
	public abstract Collection<Portal> getLoadedPortals();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re la classe protÄ�Å¼Ë�geant la map. Par dÄ�Å¼Ë�faut une protection trÄ�Å¼Ë�s
	 * permissive (ne change rien :p).
	 * 
	 * @return La classe
	 */
	public abstract MapProtector getMapProtector();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re les donnÄ�Å¼Ë�es d'un joueur ayant dÄ�Å¼Ë�connecter aprÄ�Å¼Ë�s le dÄ�Å¼Ë�but de la
	 * partie, si le jeu a demandÄ�Å¼Ë� la sauvegarde
	 * 
	 * @param name
	 *            Le joueur
	 * @return Les donnÄ�Å¼Ë�es joueurs (ou null)
	 */
	public abstract BadblockOfflinePlayer getOfflinePlayer(String name);

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re un portail par une location
	 * 
	 * @param location
	 *            La location
	 * @return Le portail
	 */
	public abstract Portal getPortal(Location location);

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re un portail par son nom
	 * 
	 * @param name
	 *            Le nom
	 * @return Le portail
	 */
	public abstract Portal getPortal(String name);

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re la classe permettant de communiquer avec RabbitMQ
	 * 
	 * @return La classe permettant de communiquer avec RabbitMQ
	 * @author xMalware
	 */
	public abstract RabbitSpeaker getRabbitSpeaker();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le type de partie
	 * 
	 * @return Le type
	 */
	public abstract RunType getRunType();

	/**
	 * Retourne une position ou le joueur peut Ä�Å¼Ë�tre tÄ�Å¼Ë�lÄ�Å¼Ë�portÄ�Å¼Ë� sans crainte.
	 * 
	 * @param location
	 *            La position initiale
	 * @return La position modifiÄ�Å¼Ë�e (ou null en cas de problÄ�Å¼Ë�me)
	 */
	public abstract Location getSafeLocation(Location location);

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le bonus Ä�Å¼Ë�venementiel en badcoins
	 * 
	 * @return Le bonus
	 */
	public abstract double getServerBadcoinsBonus();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le bonus Ä�Å¼Ë�venementiel en xp
	 * 
	 * @return Le bonus
	 */
	public abstract double getServerXpBonus();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re le systÄ�Å¼Ë�me de gestion de panneau i18n
	 * 
	 * @return Le systÄ�Å¼Ë�me
	 */
	public abstract SignManager getSignManager();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re la base de donnÄ�Å¼Ë�e SQl
	 * 
	 * @return La base de donnÄ�Å¼Ë�e
	 */
	public abstract SQLDatabase getSqlDatabase();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re une team Ä�Å¼Ë� partir de son nom interne (voir
	 * {@link #getTeamsKey()}}.
	 * 
	 * @param key
	 *            Le nom interne
	 * @return La team (ou null si elle n'existe pas).
	 */
	public abstract BadblockTeam getTeam(String key);

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re les teams registers grÄ�Å¼Ë�ce Ä�Å¼Ë�
	 * {@link #registerTeams(int, Class, ConfigurationSection)}
	 * 
	 * @return Une collection de team
	 */
	public abstract Collection<BadblockTeam> getTeams();

	/**
	 * Retourne les noms internes des teams, registers grÄ�Å¼Ë�ce Ä�Å¼Ë�
	 * {@link #registerTeams(int, Class, ConfigurationSection)}
	 * 
	 * @return Une collection de string
	 */
	public abstract Collection<String> getTeamsKey();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re les joueurs dans la whitelist
	 * 
	 * @return Les joueurs
	 */
	public abstract Collection<String> getWhitelistedPlayers();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re si la whitelist est on ou non
	 * 
	 * @return Le statut
	 */
	public abstract boolean getWhitelistStatus();

	/**
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re si le compas sÄ�Å¼Ë�lectionne la cible la plus proche au clique droit
	 * 
	 * @return Un boolean
	 */
	public abstract boolean isCompassSelectNearestTarget();

	/**
	 * VÄ�Å¼Ë�rifie si le joueur est whitelistÄ�Å¼Ë�
	 * 
	 * @param player
	 *            Le joueur
	 * @return Si il est whitelistÄ�Å¼Ë�
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
	 * RÄ�Å¼Ë�cupÄ�Å¼Ë�re une liste de kit via un nom de jeu
	 * 
	 * @param game
	 *            Le nom du jeu
	 * @return Les kits, en fonction de leur nom interne
	 */
	public abstract Map<String, PlayerKit> loadKits(String game);

	/**
	 * Manage les portails, en les chargeants / sauvegardants avec le dossier
	 * donnÄ�Å¼Ë�
	 * 
	 * @param folder
	 *            Le dossier
	 */
	public abstract void managePortals(File folder);

	/**
	 * Manage les shops, en les chargeants / sauvegardants avec le dossier donnÄ�Å¼Ë�
	 * 
	 * @param folder
	 *            Le dossier
	 */
	public abstract void manageShops(File folder);

	/**
	 * Register les teams (ne peut Ä�Å¼Ë�tre appelÄ�Å¼Ë� qu'une fois !) Ä�Å¼Ë� partir d'une
	 * configuration.
	 * 
	 * @param maxPlayers
	 *            Le nombre maximum de joueurs par teams.
	 * @param clazz
	 *            La classe reprÄ�Å¼Ë�sentant les donnÄ�Å¼Ë�es in-game d'une team.
	 * @param configuration
	 *            La configuration des teams.
	 */
	public abstract void registerTeams(int maxPlayers, ConfigurationSection configuration);

	/**
	 * Fait en sorte que le compas sÄ�Å¼Ë�lectionne la cible la plus proche au clique
	 * droit
	 * 
	 * @param selectNearestTarget
	 *            Un boolean
	 */
	public abstract void setCompassSelectNearestTarget(boolean selectNearestTarget);

	/**
	 * RedÄ�Å¼Ë�finit le manager de kit par dÄ�Å¼Ë�faut, oÄ�Å¼Ë� changer la paramÄ�Å¼Ë�tre allowDrop
	 * (de base Ä�Å¼Ë� true)
	 * 
	 * @param allowDrop
	 *            Si les items du kit peuvent Ä�Å¼Ë�tre drop par le joueur ou Ä�Å¼Ë� la
	 *            mort
	 */
	public abstract void setDefaultKitContentManager(boolean allowDrop);

	/**
	 * DÄ�Å¼Ë�finit le manager de contenu (rÄ�Å¼Ë�cupÄ�Å¼Ë�ration / give des items) des kits,
	 * pour le jeu charger. Par dÄ�Å¼Ë�faut, charge simplement un inventaire
	 * 
	 * @param manager
	 *            Le manager
	 */
	public abstract void setKitContentManager(PlayerKitContentManager manager);

	/**
	 * Change la classe protÄ�Å¼Ë�geant la map
	 * 
	 * @param protector
	 *            La classe
	 */
	public abstract void setMapProtector(MapProtector protector);

	/**
	 * DÄ�Å¼Ë�finit si la whitelist est on ou non
	 * 
	 * @param on
	 *            Si elle est on
	 */
	public abstract void setWhitelistStatus(boolean on);

	/**
	 * Fait spawn une entitÄ�Å¼Ë� custom
	 * 
	 * @param location
	 *            La position de l'entitÄ�Å¼Ë�
	 * @param type
	 *            Le type de l'entitÄ�Å¼Ë�
	 * @return L'entitÄ�Å¼Ë�
	 */
	public abstract CustomCreature spawnCustomEntity(Location location, EntityType type);

	/**
	 * Fait spawn une fausse armor stand
	 * 
	 * @param location
	 *            Sa position
	 * @return L'entitÄ�Å¼Ë�
	 */
	public abstract FakeEntity<WatcherArmorStand> spawnFakeArmorStand(Location location);

	/**
	 * Fait spawn un faux player
	 * 
	 * @param location
	 *            Sa position
	 * @param infos Les informations sur le joueur
	 * @return L'entitÄ�Å¼Ë�
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
	 * @return L'entitÄ�Å¼Ë�
	 */
	public abstract FakeEntity<WatcherEntity> spawnFakeFallingBlock(Location location, Material type, byte data);

	/**
	 * CrÄ�Å¼Ë�e une nouvelle fausse entitÄ�Å¼Ë�.
	 * 
	 * @param location
	 *            Sa position
	 * @param type
	 *            Le type de l'entitÄ�Å¼Ë�
	 * @param clazz
	 *            La classe de ses DataWatchers
	 * @return La fausse entitÄ�Å¼Ë�
	 */
	public abstract <T extends WatcherEntity> FakeEntity<T> spawnFakeLivingEntity(Location location, EntityType type,
			Class<T> clazz);

	/**
	 * Supprime une Team ayant perdue
	 * 
	 * @param team
	 *            La Team Ä�Å¼Ë� supprimer.
	 */
	public abstract void unregisterTeam(BadblockTeam team);

	/**
	 * EnlÄ�Å¼Ë�ve un joueur temporairement (jusqu'au redÄ�Å¼Ë�marrage) de la whitelist
	 * 
	 * @param player
	 *            Le joueur
	 */
	public abstract void unwhitelistPlayer(String player);

	/**
	 * Met temporairement (jusqu'au redÄ�Å¼Ë�marrage) un joueur dans la whitelist
	 * 
	 * @param player
	 *            Le joueur
	 */
	public abstract void whitelistPlayer(String player);

	public abstract void setEmptyChunks(CuboidSelection selection, boolean exclusion);

	public abstract void setLightChunks(CuboidSelection selection, boolean exclusion);
	/**
	 * Charge une zone en un certains nombre de ticks serveurs (de maniÄ�Å¼Ë�re synchrone)
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
