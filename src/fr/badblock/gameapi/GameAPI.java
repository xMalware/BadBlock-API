package fr.badblock.gameapi;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.configuration.BadConfiguration;
import fr.badblock.gameapi.databases.LadderSpeaker;
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
import fr.badblock.gameapi.players.BadblockScoreboard;
import fr.badblock.gameapi.players.BadblockTeam;
import fr.badblock.gameapi.players.PlayerAchievement;
import fr.badblock.gameapi.players.kits.PlayerKit;
import fr.badblock.gameapi.players.kits.PlayerKitContentManager;
import fr.badblock.gameapi.servers.JoinItems;
import fr.badblock.gameapi.servers.MapProtector;
import fr.badblock.gameapi.technologies.RabbitSpeaker;
import fr.badblock.gameapi.utils.CustomObjective;
import fr.badblock.gameapi.utils.i18n.I18n;
import fr.badblock.gameapi.utils.itemstack.CustomInventory;
import fr.badblock.gameapi.utils.itemstack.DefaultItems;
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
	 * Récupère le système de gestion des statuts
	 * @return Le système de gestion des statuts
	 */
	public abstract GameServer getGameServer();
	
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
	 * Récupère les données d'un joueur ayant déconnecter après le début de la partie, si le jeu a demandé
	 * la sauvegarde
	 * @param uniqueId L'UUID du joueur
	 * @return Les données joueurs (ou null)
	 */
	public abstract BadblockOfflinePlayer getOfflinePlayer(UUID uniqueId);
	
	/**
	 * Récupère les jeux enregistrés possédant des achievements
	 * @return
	 */
	public abstract Collection<String> getAchievementsGames();
	
	/**
	 * Récupère les différents achievements (chargés) d'un jeu
	 * @return Une collection d'achievements (si aucun, simplement vide).
	 */
	public abstract Collection<PlayerAchievement> getAchievements(String game);
	
	/**
	 * Récupère un achievement via son non interne.
	 * @param key Le nom interne
	 * @return L'achievement (ou null si inexistant)
	 */
	public abstract PlayerAchievement getAchievement(String key);
	
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

	
	public abstract CustomMerchantInventory getCustomMarchantInventory();
	
	public abstract DefaultItems getDefaultItems();

	/*
	 * TODO IN IMPLEMENTATION :
	 * - register packets
	 * - register entities
	 * - register potions
	 * - load i18n
	 * - create itemstackfactory
	 */
}
