package fr.badblock.gameapi.players;

import java.util.Collection;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.game.result.Result;
import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.particles.ParticleEffect;
import fr.badblock.gameapi.players.scoreboard.CustomObjective;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import fr.badblock.gameapi.utils.selections.CuboidSelection;
import lombok.Getter;

/**
 * Classe ajoutant des méthodes (BadBlock et utilitaires) à la classe Player.<br>
 * Pour l'obtenir il suffit de caster le Player.
 * 
 * @author LeLanN
 */
public interface BadblockPlayer extends Player, BadblockPlayerData {
	/**
	 * Vérifie si le joueur est invulnérable (si il ne peut pas prendre de dégats).
	 * @return Si le joueur est invulnérable
	 */
	public boolean isInvulnerable();
	
	/**
	 * Définit si le joueur est invulnérable (si il ne peut pas prendre de dégats).<br>
	 * Attention, un changement de gamemode l'annule.
	 * @param invulnerable Si le joueur est invulnérable
	 */
	public void setInvulnerable(boolean invulnerable);
	
	/**
	 * Vérifie si le joueur peut build instantanément
	 * @return Si le joueur peut build instantanément
	 */
	public boolean canInstantlyBuild();
	
	/**
	 * Dzéfinit si le joueur peut build instantanément
	 * @param instantlyBuild Si le joueur peut build instantanément
	 */
	public void setCanInstantlyBuild(boolean instantlyBuild);
	
	/**
	 * Vérifie si le joueur peut build
	 * @return Si le joueur peut build
	 */
	public boolean canBuild();
	
	/**
	 * Définit si le joueur peut build
	 * @param canBuild Si le joueur peut build
	 */
	public void setCanBuild(boolean canBuild);
	
	/**
	 * Définit si le joueur a ou non des informations de débug (F3) réduites (par exemple plus les coordonnées).<br>
	 * Bien entendu, cela est fait côté client, ce n'est donc pas fiable (le client peut être moddé).
	 * @param reducedDebugInfo Si le joueur a des informations de débug réduites
	 */
	public void setReducedDebugInfo(boolean reducedDebugInfo);
	
	/**
	 * Force le client à voir de la pluie/neige (bien que cela ne soit pas réellement le cas côté serveur).
	 * @param rain Si le joueur voit de la pluie
	 */
	public void playRain(boolean rain);
	
	/**
	 * Fait voir l'image de 'démo' de MineCraft au joueur. Utile uniquement si le serveur est doté d'un resource pack.<br>
	 * Le joueur doit être en GameMode 3
	 */
	public void showDemoScreen();
	
	/**
	 * Soigne le joueur (vie, faim, feu et effets négatifs de potions)
	 */
	public void heal();
	
	/**
	 * Remplit la barre de faim du joueur
	 */
	public void feed();
	
	/**
	 * Clear l'inventaire du joueur (dont l'armure)
	 */
	public void clearInventory();
	
	/**
	 * Enlève tous les effets (potions)
	 */
	public void removePotionEffects();
	
	/**
	 * Enlève les effets négatifs (potions)
	 */
	public void removeBadPotionEffects();
	
	/**
	 * Récupère le ping du joueur
	 * @return
	 */
	public int getPing();

	/**
	 * Fait jouer un son au joueur avec des paramètres par défaut
	 * @param sound Le son à jouer
	 */
	public void playSound(Sound sound);
	
	/**
	 * Fait jouer un son au joueur avec des paramètres par défaut
	 * @param location L'origine du son
	 * @param sound Le son à jouer
	 */
	public void playSound(Location location, Sound sound);
	
	/**
	 * Récupère un message traduit dans la langue du joueur
	 * @param key La key du message
	 * @param args Les arguments
	 * @return Le message
	 */
	public String[] getTranslatedMessage(String key, Object... args);
	
	/**
	 * Envoit un message traduit au joueur. Pour plus d'informations voir {@link fr.badblock.gameapi.utils.i18n.I18n}
	 * @param key La key du message
	 * @param args Les arguments
	 */
	public void sendTranslatedMessage(String key, Object... args);
	
	/**
	 * Envoit une action bar au joueur
	 * @param message L'action bar
	 */
	public void sendActionBar(String message);
	
	/**
	 * Envoit une action bar traduite au joueur. Pour plus d'informations voir {@link fr.badblock.gameapi.utils.i18n.I18n}
	 * @param key La key du message
	 * @param args Les arguments
	 */
	public void sendTranslatedActionBar(String key, Object... args);

	/**
	 * Change le message de la 'Boss Bar' du joueur
	 * @param message Le message
	 */
	public void sendBossBar(String message);
	
	/**
	 * Enlève le message de la 'Boss Bar' du joueur (si il y en a un)
	 */
	public void removeBossBar();
	
	/**
	 * Envoit une BossBar traduite au joueur. Pour plus d'informations voir {@link fr.badblock.gameapi.utils.i18n.I18n}
	 * @param key La key du message
	 * @param args Les arguments
	 */
	public void sendTranslatedBossBar(String key, Object... args);
	
	/**
	 * Envoit un title au joueur
	 * @param title Le title
	 * @param subtitle Le subtitle
	 */
	public void sendTitle(String title, String subtitle);
	
	/**
	 * Envoit un title traduit au joueur (les deux premiers messages de l'array seront prit). Pour plus d'informations voir {@link fr.badblock.gameapi.utils.i18n.I18n}
	 * @param key La key du message
	 * @param args Les arguments
	 */
	public void sendTranslatedTitle(String key, Object...args);
	
	/**
	 * Enlève le title de l'écran du joueur
	 */
	public void clearTitle();
	
	/**
	 * Envoit les timings (du title/action bar) au joueur
	 * @param fadeIn Le temps que le message met à s'afficher
	 * @param stay Le temps où le message reste
	 * @param fadeOut Le temps que le message met à disparaître
	 */
	public void sendTimings(long fadeIn, long stay, long fadeOut);
	
	/**
	 * Envoit le header et le footer de la tablist au joueur. Utiliser le symbole {@literal \n} pour revenir à la ligne.
	 * @param header Le header
	 * @param footer Le footer
	 */
	public void sendTabHeader(String header, String footer);
	
	/**
	 * Envoit le header et le footer de la tablist au joueur.
	 * @param header Le header
	 * @param footer Le footer
	 */
	public void sendTranslatedTabHeader(TranslatableString header, TranslatableString footer);
	
	/**
	 * Affiche un texte 'volant' au joueur.
	 * @param text Le texte à afficher.
	 * @param location La position du texte (si null, juste devant ses yeux)
	 * @param lifeTime La durée de vie du texte (en ticks)
	 * @param offset Le déplacement que peut subir le texte par rapport à la position initiale (random). Aucun si 0.
	 */
	public void showFloatingText(String text, Location location, long lifeTime, double offset);
	
	/**
	 * Affiche un texte 'volant' traduit au joueur.
	 * @param location La position du texte (si null, juste devant ses yeux)
	 * @param lifeTime La durée de vie du texte (en ticks)
	 * @param offset Le déplacement que peut subir le texte par rapport à la position initiale (random). Aucun si 0.
	 * @param key La key du message
	 * @param args Les arguments
	 */
	public void showTranslatedFloatingText(Location location, long lifeTime, double offset, String key, Object... args);
	
	/**
	 * Permet de faire que le joueur ne puisse pas du tout bouger à la position donnée.<br>*
	 * Si null, le joueur pourra de nouveau bouger.
	 * @param location La position où il doit rester.
	 */
	public void jailPlayerAt(Location location);
	
	/**
	 * Permet de faire que le joueur ne puisse pas bouger en dehors d'une certaine zone<br>
	 * Si null, le joueur pourra de nouveau aller partout.
	 * @param location La position où il doit rester
	 * @param radius Le rayon autour duquel il peut se déplacer
	 */
	public void pseudoJail(Location location, double radius);
	
	/**
	 * Permet de savoir si le joueur a été bloqué avec {@link #jailPlayerAt(Location)}.
	 * @return Si il est bloqué
	 */
	public boolean isJailed();
	
	/**
	 * Permet de vérifier si le joueur est confiné dans une zone
	 * @return Si il est bloqué
	 */
	public boolean isPseudoJailed();
	
	/**
	 * Joue l'animation de l'ouverture ou fermeture d'un coffre au joueur
	 * @param block Le coffre à 'ouvrir/fermer'
	 * @param open  Si le coffre est ouvert
	 */
	public void playChestAnimation(Block block, boolean open);
	
	/**
	 * Active ou désactive la collision entre le joueur et les entités
	 * @param collision Si les collisions sont activés
	 */
	public void setEntityCollision(boolean collision);
	
	/**
	 * Change le nombre de flèches dans le corps du joueurs.
	 * @param amount Le nombe de flèches.
	 */
	public void setArrowsInBody(byte amount);
	
	/**
	 * Change la dimension affichée par le joueur sans le changer réellement de monde
	 * @param world La nouvelle dimension
	 */
	public void changePlayerDimension(World.Environment world);
	
	/**
	 * Renvoit tous les chunks autour du joueur
	 */
	public void reloadMap();
	
	/**
	 * Renvoit un chunk au joueur
	 * @param chunk Le chunk
	 */
	public void updateChunk(Chunk chunk);
	
	/**
	 * Envoit un packet à un joueur. Pour utiliser les packets voir {@link fr.badblock.gameapi.GameAPI#createPacket(Class)}.
	 * @param packet Le packet à envoyer.
	 */
	public void sendPacket(BadblockOutPacket packet);
	
	/**
	 * Affiche une particule au joueur
	 * @param location La position de la particule
	 * @param effect La particule (récupérable avec {@link GameAPI#createParticleEffect(fr.badblock.gameapi.particles.ParticleEffectType)}
	 */
	public void sendParticle(Location location, ParticleEffect effect);
	
	/**
	 * Récupère le CustomObjective vu par le joueur
	 * @return Le CustomObjective (null si non définit)
	 */
	public CustomObjective getCustomObjective();
	
	/**
	 * Affiche un CustomObjective au joueur
	 * @param objective L'objective
	 */
	public void showCustomObjective(CustomObjective objective);
	
	/**
	 * Vérifie si le joueur a une des permissions basiques des mini-jeux.
	 * @param permission La permission
	 * @return Si le joueur a la permission
	 */
	public boolean hasPermission(GamePermission permission);
	
	/**
	 * Récupère le groupe principal du joueur (par exemple admin)
	 * @return Le groupe principal
	 */
	public String getMainGroup();
	
	/**
	 * Récupère les groupes secondaires du joueur (par exemple emeraude et modo). Peut être vide.
	 * @return Les groupes secondaires
	 */
	public Collection<String> getAlternateGroups();
	
	/**
	 * Récupère le mode actuel de jeu du joueur
	 * @return Le mode
	 */
	public BadblockMode getBadblockMode();
	
	/**
	 * Change le mode de jeu du joueur (les protections et items seront gérés automatiquement)
	 * @param newMode Le mode
	 */
	public void setBadblockMode(BadblockMode newMode);
	
	/**
	 * Si le joueur est en mode bypass (pour casser les blocs ect)
	 * @return Un boolean
	 */
	public boolean hasAdminMode();
	
	/**
	 * Définit si le joueur est en mode bypass (pour casser les blocs ect)
	 * @param adminMode Un boolean
	 */
	public void setAdminMode(boolean adminMode);
	
	/**
	 * Récupère la séléction définie par le joueur via un baton de blaze. Peut retourner null si non définie.
	 * @return La séléction ou null
	 */
	public CuboidSelection getSelection();
	
	/**
	 * Fait changer le joueur de serveur
	 * @param server Le nouveau serveur
	 */
	public void sendPlayer(String server);
	
	/**
	 * Upload un résultat de partie pour le joueur. Le joueur en sera notifié.
	 * @param result Le résultat
	 */
	public void postResult(Result result);
	
	/**
	 * Sauvegarde (envoit à Ladder) les données joueurs
	 */
	public void saveGameData();
	
	/**
	 * Représente les permissions basiques des MiniJeux, pour une gestion plus simple.
	 * @author LeLanN
	 */
	public static enum GamePermission {
		PLAYER(null),
		VIP("badblock.vip"),
		MODERATOR("badblock.modo"),
		ADMIN("badblock.admin");
		
		@Getter private final String permission;
		
		private GamePermission(String permission){
			this.permission = permission;
		}
	}
	
	/**
	 * Représente les différents modes de jeux possibles pour un joueur Badblock
	 * @author LeLanN
	 */
	public static enum BadblockMode {
		/**
		 * Représente un joueur 'normal' (entrain de jouer ou au lobby d'attente)
		 */
		PLAYER(),
		/**
		 * Représente un joueur attendant en spectateur d'être respawn
		 */
		RESPAWNING(),
		/**
		 * Représente un joueur observant la partie sans y interférer
		 */
		SPECTATOR();
	}
	
	/**
	 * A ajouter :
	 * - toutes les statistiques BadBlock (achievements, points, ...)
	 * - Le scoreboard BadBlock
	 * - Des méthodes en plus pour la gestion du joueur (voir, en gros, BPlayer.class et EpicPlayer.class)
	 * - sendPacket, sendMessage/ActionBar/Title/...
	 * - ?
	 */
}
