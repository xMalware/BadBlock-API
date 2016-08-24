package fr.badblock.gameapi;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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
import fr.badblock.gameapi.packets.watchers.WatcherArmorStand;
import fr.badblock.gameapi.packets.watchers.WatcherEntity;
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
import fr.badblock.gameapi.utils.entities.CustomCreature;
import fr.badblock.gameapi.utils.i18n.I18n;
import fr.badblock.gameapi.utils.itemstack.CustomInventory;
import fr.badblock.gameapi.utils.itemstack.ItemStackExtra;
import fr.badblock.gameapi.utils.itemstack.ItemStackFactory;
import fr.badblock.gameapi.utils.merchants.CustomMerchantInventory;
import lombok.Getter;
import lombok.Setter;

/**
 * La classe principale de l'API. Elle permet de récupérer la plupart de ses composants (sauf ceux disponibles en statics) ainsi que de la configurer.<br>
 * Les principaux éléments à configurer sont :
 * <ul>
 * <li>Les kits, car il est très utile de garder une base commune à tous les jeux, néanmoins la gestion des items est différentes entre les jeux (ex : splatoon, les armes ne sont pas dans la configuration). C'est pourquoi il faut définir la classe permettant de load les items.</li>
 * <li>Les données de joueur InGame, car il est inutile de créer une Map uniquement pour ces données, et que les données entre les jeux peuvent être très différentes.</li>
 * <li>ect ...</li>
 * </ul>
 * <br>
 * Le principe de l'API réside donc sur deux points : elle est à la fois une base à tous les jeux, pour qu'ils se ressemblent (et soient plus rapidement mettables à jours) ainsi qu'une longue liste d'utilitaires variés, pour simplifier le développement.<br>
 *
 * @author LeLanN
 */
public abstract class GameAPI extends JavaPlugin {
	public static final boolean TEST_MODE = Boolean.parseBoolean(System.getProperty("badblock.testmode", "false"));

	@Getter 	   protected static GameAPI API;
	@Getter@Setter protected static String	gameName;
	@Getter@Setter protected static String  internalGameName;
	@Getter		   protected static Gson	gson 			  = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
																				 .disableHtmlEscaping().create();
	@Getter		   protected static Gson	prettyGson 		  = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT)
																				 .disableHtmlEscaping()
																				 .setPrettyPrinting().create();

	/**
	 * Log un message 'normal' (plus rapide que de récupérrer le logger)
	 * @param message Le message
	 */
	public static void log(String message){
		Bukkit.getLogger().log(Level.INFO, message);
	}
	
	/**
	 * Log un message d'alerte (plus rapide que de récupérrer le logger)
	 * @param message Le message
	 */
	public static void logWarning(String message){
		Bukkit.getLogger().log(Level.WARNING, message);
	}
	
	/**
	 * Log un message d'erreur (plus rapide que de récupérrer le logger)
	 * @param message Le message
	 */
	public static void logError(String message){
		Bukkit.getLogger().log(Level.SEVERE, message);
	}
	
	/**
	 * Log un message avec des couleurs ({@link I18n#replaceColors(String)} appelé)
	 * @param message Le message coloré
	 */
	public static void logColor(String message){
		Bukkit.getConsoleSender().sendMessage(i18n().replaceColors(message));
	}
	
	/**
	 * Récupère le système d'internationalisation (pour éviter de récupérer l'instance).
	 * @return Le système d'i18n
	 */
	public static I18n i18n(){
		return API.getI18n();
	}
	
	/**
	 * Récupère le système de messaging et internationalisation
	 * @return Le système
	 */
	public abstract I18n getI18n();
	
	/**
	 * Récupère le système de gestion de panneau i18n
	 * @return Le système
	 */
	public abstract SignManager getSignManager();
	
	/**
	 * Récupère le système de gestion des statuts
	 * @return Le système de gestion des statuts
	 */
	public abstract GameServer getGameServer();
	
	/**
	 * Récupère le type de partie
	 * @return Le type
	 */
	public abstract RunType getRunType();
	
	/**
	 * Récupère la base de donnée SQl
	 * @return La base de donnée
	 */
	public abstract SQLDatabase getSqlDatabase();
	
	/**
	 * Récupère la classe permettant de communiquer avec Ladder
	 * @return La classe permettant de communiquer avec Ladder
	 */
	public abstract LadderSpeaker getLadderDatabase();
	
	/**
	 * Récupère la classe permettant de communiquer avec RabbitMQ
	 * @return La classe permettant de communiquer avec RabbitMQ
	 * @author xMalware
	 */
	public abstract RabbitSpeaker getRabbitSpeaker();
	
	/**
	 * Récupère le bonus évenementiel en badcoins
	 * @return Le bonus
	 */
	public abstract double getServerBadcoinsBonus();
	
	/**
	 * Récupère le bonus évenementiel en xp
	 * @return Le bonus
	 */
	public abstract double getServerXpBonus();
	
	/**
	 * Change la classe protégeant la map
	 * @param protector La classe
	 */
	public abstract void setMapProtector(MapProtector protector);
	
	/**
	 * Récupère la classe protégeant la map. Par défaut une protection très permissive (ne change rien :p).
	 * @return La classe
	 */
	public abstract MapProtector getMapProtector();
	
	/**
	 * Récupère l'objet permettant de générer des coffres aléatoires
	 * @return L'objet
	 */
	public abstract ChestGenerator getChestGenerator();
	
	/**
	 * Active l'anti-spawnkill
	 */
	public abstract void enableAntiSpawnKill();
	
	/**
	 * Crée une nouvelle factory d'items
	 * @return La factory d'items
	 */
	public abstract ItemStackFactory createItemStackFactory();
	
	/**
	 * Créé une nouvelle factory d'items
	 * @param item L'item de base qui sera utilisé pour le paramètrage de la factory
	 * @return La factory
	 */
	public abstract ItemStackFactory createItemStackFactory(ItemStack item);
	
	/**
	 * Crée un nouveau extra pour un item
	 * @param itemStack L'item stack auquel ajouté l'extra
	 * @return Un nouvel extra ou celui existant si déjà register pour la l'item.
	 */
	public abstract ItemStackExtra createItemStackExtra(ItemStack itemStack);
	
	/**
	 * Créé un nouvel inventaire custom
	 * @param line Le nombre de ligne (et non pas de cases !)
	 * @param displayName Le nom d'affichage de l'inventaire custom
	 * @return Le nouvel inventaire
	 */
	public abstract CustomInventory createCustomInventory(int line, String displayName);
	
	/**
	 * Récupère le manager de contenu (récupération / give des items) des kits, pour le jeu charger. Par défaut, charge simplement un inventaire
	 * @return Le manager
	 */
	public abstract PlayerKitContentManager getKitContentManager();
	
	/**
	 * Définit le manager de contenu (récupération / give des items) des kits, pour le jeu charger. Par défaut, charge simplement un inventaire
	 * @param manager Le manager
	 */
	public abstract void setKitContentManager(PlayerKitContentManager manager);

	/**
	 * Redéfinit le manager de kit par défaut, où changer la paramètre allowDrop (de base à true)
	 * @param allowDrop Si les items du kit peuvent être drop par le joueur ou à la mort
	 */
	public abstract void setDefaultKitContentManager(boolean allowDrop);
	
	/**
	 * Récupère une liste de kit via un nom de jeu
	 * @param game Le nom du jeu
	 * @return Les kits, en fonction de leur nom interne
	 */
	public abstract Map<String, PlayerKit> loadKits(String game);
	
	/**
	 * Récupère l'instance de la configuration des items de join
	 * @return L'instance
	 */
	public abstract JoinItems getJoinItems();
	
	/**
	 * Active le formattage du chat par l'API
	 * @param format Si le formattage est activé
	 * @param doTeamChat Si (lorsque formattage activé) le les messages précédés de $ sont pour la team
	 */
	public abstract void formatChat(boolean format, boolean doTeamChat);
	
	/**
	 * Active le formattage du chat par l'API
	 * @param format Si le formattage est activé
	 * @param doTeamChat Si (lorsque formattage activé) le les messages précédés de $ sont pour la team
	 * @param custom Si le chat est différent d'autres serveurs, le nom custom
	 */
	public abstract void formatChat(boolean format, boolean doTeamChat, String custom);
	
	/**
	 * Register les teams (ne peut être appelé qu'une fois !) à partir d'une configuration.
	 * @param maxPlayers Le nombre maximum de joueurs par teams.
	 * @param clazz La classe représentant les données in-game d'une team.
	 * @param configuration La configuration des teams.
	 */
	public abstract void registerTeams(int maxPlayers, ConfigurationSection configuration);
	
	/**
	 * Récupère les teams registers grâce à {@link #registerTeams(int, Class, ConfigurationSection)}
	 * @return Une collection de team
	 */
	public abstract Collection<BadblockTeam> getTeams();
	
	/**
	 * Retourne les noms internes des teams, registers grâce à {@link #registerTeams(int, Class, ConfigurationSection)}
	 * @return Une collection de string
	 */
	public abstract Collection<String> getTeamsKey();
	
	/**
	 * Récupère une team à partir de son nom interne (voir {@link #getTeamsKey()}}.
	 * @param key Le nom interne
	 * @return La team (ou null si elle n'existe pas).
	 */
	public abstract BadblockTeam getTeam(String key);
	
	/**
	 * Supprime une Team ayant perdue
	 * @param team La Team à supprimer.
	 */
	public abstract void unregisterTeam(BadblockTeam team);
	
	/**
	 * Permet d'équilibrer les teams
	 * @param sameSize Si il faut que toutes les teams ai le même nombre de joueurs
	 */
	public abstract void balanceTeams(boolean sameSize);
	
	/**
	 * Récupère les données d'un joueur ayant déconnecter après le début de la partie, si le jeu a demandé
	 * la sauvegarde
	 * @param name Le joueur
	 * @return Les données joueurs (ou null)
	 */
	public abstract BadblockOfflinePlayer getOfflinePlayer(String name);
	
	/**
	 * Créé un nouveau objective custom
	 * @param name Le nom (interne) de l'objective
	 * @return Le CustomObjective créé.
	 */
	public abstract CustomObjective buildCustomObjective(String name);
	
	/**
	 * Récupère le BadblockScoreboard commun à tous les joueurs permettant d'afficher la vie, les noms de teams, les votes, ...
	 */
	public abstract BadblockScoreboard getBadblockScoreboard();
	
	/**
	 * Crée un packet à partir de l'interface le représentant.
	 * @param clazz La classe de l'interface du packet.
	 * @return Une nouvelle instance du packet.
	 */
	public abstract <T extends BadblockOutPacket> T createPacket(Class<T> clazz);
	
	/**
	 * Ecoute sur un packet entrant avec un listener
	 * @param listener Le listener
	 */
	public abstract <T extends BadblockInPacket> void listenAtPacket(InPacketListener<T> listener);
	
	/**
	 * Ecoute sur un packet sortant avec un listener
	 * @param listener Le listener
	 */
	public abstract <T extends BadblockOutPacket> void listenAtPacket(OutPacketListener<T> listener);

	/**
	 * Crée un datawatcher à partir de l'interface le représentant.
	 * @param clazz La classe de l'interface du DataWatcher.
	 * @return Une nouvelle instance du packet.
	 */
	public abstract <T extends WatcherEntity> T createWatcher(Class<T> clazz);

	/**
	 * Crée une nouvelle fausse entité.
	 * @param location Sa position
	 * @param type Le type de l'entité
	 * @param clazz La classe de ses DataWatchers
	 * @return La fausse entité
	 */
	public abstract <T extends WatcherEntity> FakeEntity<T> spawnFakeLivingEntity(Location location, EntityType type, Class<T> clazz);
	
	/**
	 * Spawn un faux falling block
	 * @param location La position
	 * @param type Le type de falling block
	 * @param data Le 'data'
	 * @return L'entité
	 */
	public abstract FakeEntity<WatcherEntity> spawnFakeFallingBlock(Location location, Material type, byte data);
	
	/**
	 * Fait spawn une fausse armor stand
	 * @param location Sa position
	 * @return
	 */
	public abstract FakeEntity<WatcherArmorStand> spawnFakeArmorStand(Location location);
	
	/**
	 * Créé une particule. Envoyable grâce à {@link BadblockPlayer#sendParticle(Location, ParticleEffect)}
	 * @param type Le type de la particule
	 * @return La particule
	 */
	public abstract ParticleEffect createParticleEffect(ParticleEffectType type);
	
	/**
	 * Charge une configuration JSON depuis un fichier
	 * @param file Le fichier
	 * @return La configuration
	 */
	public abstract BadConfiguration loadConfiguration(File file);
	
	/**
	 * Charge une configuration JSON depuis un objet JSON
	 * @param content L'object
	 * @return La configuration
	 */
	public abstract BadConfiguration loadConfiguration(JsonObject content);
	
	/**
	 * Manage les shops, en les chargeants / sauvegardants avec le dossier donné
	 * @param folder Le dossier
	 */
	public abstract void manageShops(File folder);

	
	/**
	 * Manage les portails, en les chargeants / sauvegardants avec le dossier donné
	 * @param folder Le dossier
	 */
	public abstract void managePortals(File folder);
	
	/**
	 * Récupère la liste des portails chargés
	 * @return La liste des portails
	 */
	public abstract Collection<Portal> getLoadedPortals();
	
	/**
	 * Récupère un portail par son nom
	 * @param name Le nom
	 * @return Le portail
	 */
	public abstract Portal getPortal(String name);
	
	/**
	 * Récupère un portail par une location
	 * @param location La location
	 * @return Le portail
	 */
	public abstract Portal getPortal(Location location);

	/**
	 * Fait spawn une entité custom
	 * @param location La position de l'entité
	 * @param type Le type de l'entité
	 * @return L'entité
	 */
	public abstract CustomCreature spawnCustomEntity(Location location, EntityType type);
	
	/**
	 * Créé un inventaire de marchant custom
	 * @return Le marchant
	 */
	public abstract CustomMerchantInventory getCustomMerchantInventory();

	/**
	 * Récupère les joueurs dans la whitelist
	 * @return Les joueurs
	 */
	public abstract Collection<String> getWhitelistedPlayers();
	
	/**
	 * Met temporairement (jusqu'au redémarrage) un joueur dans la whitelist
	 * @param player Le joueur
	 */
	public abstract void whitelistPlayer(String player);

	/**
	 * Enlève un joueur temporairement (jusqu'au redémarrage) de la whitelist
	 * @param player Le joueur
	 */
	public abstract void unwhitelistPlayer(String player);
	
	/**
	 * Vérifie si le joueur est whitelisté
	 * @param player Le joueur
	 * @return Si il est whitelisté
	 */
	public abstract boolean isWhitelisted(String player);
	
	/**
	 * Récupère si la whitelist est on ou non
	 * @return Le statut
	 */
	public abstract boolean getWhitelistStatus();

	/**
	 * Définit si la whitelist est on ou non
	 * @param on Si elle est on
	 */
	public abstract void setWhitelistStatus(boolean on);
	
	/**
	 * Récupère si le compas sélectionne la cible la plus proche au clique droit
	 * @return Un boolean
	 */
	public abstract boolean isCompassSelectNearestTarget();
	
	/**
	 * Fait en sorte que le compas sélectionne la cible la plus proche au clique droit
	 * @param selectNearestTarget Un boolean
	 */
	public abstract void setCompassSelectNearestTarget(boolean selectNearestTarget);
}
