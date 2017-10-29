package fr.badblock.gameapi.players;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;

import fr.badblock.gameapi.events.api.PlayerJoinTeamEvent.JoinReason;
import fr.badblock.gameapi.players.data.TeamData;
import fr.badblock.gameapi.utils.i18n.Locale;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import fr.badblock.gameapi.utils.itemstack.ItemStackExtra;

/**
 * Représente une Team utilisée pour un MiniJeu.<br>
 * Pour les utiliser les registers avec
 * {@link fr.badblock.gameapi.GameAPI#registerTeams(int, Class, org.bukkit.configuration.ConfigurationSection)}
 * 
 * @author LeLanN
 */
public interface BadblockTeam {
	/**
	 * Créé l'item de join pour la team
	 * 
	 * @param locale
	 *            La langue
	 * @return L'item
	 */
	public ItemStackExtra createJoinItem(Locale locale);

	/**
	 * Récupčre la couleur (autre) de l'équipe.
	 * 
	 * @return La couleur.
	 */
	public Color geNormalColor();

	/**
	 * Récupčre la string (i18n) du nom dans le chat (pour le mettrre dans une
	 * phrase) de la team
	 * 
	 * @return La string
	 */
	public TranslatableString getChatName();

	/**
	 * Récupčre la string (i18n) du préfixe dans le chat de la team (quand les
	 * joueurs parlent)
	 * 
	 * @return La string
	 */
	public TranslatableString getChatPrefix();

	/**
	 * Récupčre la couleur (chat) de l'équipe.
	 * 
	 * @return La couleur (chat).
	 */
	public ChatColor getColor();

	/**
	 * Récupčre la couleur (laine) de l'équipe.
	 * 
	 * @return La couleur (laine).
	 */
	public DyeColor getDyeColor();

	/**
	 * Récupčre le nom interne de la team
	 * 
	 * @return Le nom interne.
	 */
	public String getKey();

	/**
	 * Récupčre le nombre maximum de joueurs dans la team.
	 * 
	 * @return Le nombre maximum.
	 */
	public int getMaxPlayers();

	/**
	 * Récupčre les joueurs de la team étant connectés
	 * 
	 * @return Une collection
	 */
	public Collection<BadblockPlayer> getOnlinePlayers();

	/**
	 * Récupčre les noms des joueurs de la team étant connectés
	 * 
	 * @return Une collection
	 */
	public Collection<String> getOnlinePlayersName();

	/**
	 * Récupčre les joueurs présent lors du démarrage
	 * 
	 * @return Les joueurs
	 */
	public Collection<UUID> getPlayersAtStart();

	/**
	 * Récupčre les joueurs présent lors du démarrage
	 * 
	 * @return Les joueurs
	 */
	public Collection<String> getPlayersNameAtStart();

	/**
	 * Retourne la string (i18n) du préfixe dans la tabulation de la Team
	 * 
	 * @param color
	 *            La couleur (verte ou rouge) pour savoir si la team est alliée
	 *            ou non
	 * @return La string
	 */
	public TranslatableString getTabPrefix(ChatColor color);

	/**
	 * Permet ŕ un BadblockPlayer de rejoindre la team.
	 * 
	 * @param player
	 *            Le player
	 * @param reason
	 *            La raison du join
	 * @return Si l'opération est un succčs
	 */
	public boolean joinTeam(BadblockPlayer player, JoinReason reason);

	/**
	 * Fait quitter la team ŕ un BadblockPlayer.
	 * 
	 * @param player
	 *            Le player
	 */
	public void leaveTeam(BadblockPlayer player);

	/**
	 * Récupčre le nombre de joueurs dans la team connectés sur le serveur.
	 * 
	 * @return Le nombre de joueurs.
	 */
	public int playersCurrentlyOnline();

	/**
	 * Définit le nombre maximum de joueurs dans la team.
	 * 
	 * @param maxPlayers
	 *            Le nombre maximum.
	 */
	public void setMaxPlayers(int maxPlayers);

	/**
	 * Récupčre les données ingame de la team. Attention, la classe fournie doit
	 * ętre celle donnée dans le onEnable grâçe ŕ
	 * {@link fr.badblock.gameapi.GameAPI#registerTeams(int, Class, org.bukkit.configuration.ConfigurationSection)}
	 * 
	 * @param clazz
	 *            La classe implémentant TeamData
	 * @return Les données joueurs (ou null si la classe donnée n'est pas
	 *         correcte)
	 */
	public <T extends TeamData> T teamData(Class<T> clazz);
	
	/**
	 * Retourne si l'équipe ne peut plus ętre jouée (éliminiation..)
	 * @return
	 */
	public boolean isDead();
	
	/**
	 * "Tuer" une équipe
	 */
	public void die();
	
}
