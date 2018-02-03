package fr.badblock.gameapi.players;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.disguise.Disguise;
import fr.badblock.gameapi.game.result.Result;
import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.packets.out.play.PlayChat;
import fr.badblock.gameapi.particles.ParticleEffect;
import fr.badblock.gameapi.players.bossbars.BossBarColor;
import fr.badblock.gameapi.players.bossbars.BossBarStyle;
import fr.badblock.gameapi.players.scoreboard.CustomObjective;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import fr.badblock.gameapi.utils.selections.CuboidSelection;
import lombok.Getter;
import net.md_5.bungee.api.chat.BaseComponent;

/**

 * Classe ajoutant des mï¿½thodes (BadBlock et utilitaires) ï¿½ la classe

 * Classe ajoutant des mÃ©thodes (BadBlock et utilitaires) Å• la classe

 * Player.<br>
 * Pour l'obtenir il suffit de caster le Player.
 * 
 * @author LeLanN
 */
public interface BadblockPlayer extends Player, BadblockPlayerData {
	public static final int VERSION_1_8    = 47;
	public static final int VERSION_1_9    = 107;
	public static final int VERSION_1_9_1  = 108;
	public static final int VERSION_1_9_2  = 109;
	public static final int VERSION_1_10   = 210;
	
	/**

	 * Reprï¿½sente les diffï¿½rents modes de jeux possibles pour un joueur Badblock

	 * ReprÃ©sente les diffÃ©rents modes de jeux possibles pour un joueur Badblock

	 * 
	 * @author LeLanN
	 */
	public static enum BadblockMode {
		/**

		 * Reprï¿½sente un joueur 'normal' (entrain de jouer ou au lobby

		 * ReprÃ©sente un joueur 'normal' (entrain de jouer ou au lobby

		 * d'attente)
		 */
		PLAYER(),
		/**

		 * Reprï¿½sente un joueur attendant en spectateur d'ï¿½tre respawn

		 * ReprÃ©sente un joueur attendant en spectateur d'Ä™tre respawn

		 */
		RESPAWNING(),
		/**

		 * Reprï¿½sente un joueur observant la partie sans y interfï¿½rer

		 * ReprÃ©sente un joueur observant la partie sans y interfÃ©rer

		 */
		SPECTATOR();
	}

	/**

	 * Reprï¿½sente les permissions basiques des MiniJeux, pour une gestion plus

	 * ReprÃ©sente les permissions basiques des MiniJeux, pour une gestion plus

	 * simple.
	 * 
	 * @author LeLanN
	 */
	public static enum GamePermission {
		PLAYER(null), 
		VIP("badblock.vip"),
		MODERATOR("badblock.modo"),
		BMODERATOR("badblock.modo+"),
		ADMIN("badblock.admin");

		@Getter
		private final String permission;

		private GamePermission(String permission) {
			this.permission = permission;
		}
	}

	/**

	 * Vï¿½rifie si le joueur peut build

	 * VÃ©rifie si le joueur peut build

	 * 
	 * @return Si le joueur peut build
	 */
	public boolean canBuild();

	/**

	 * Vï¿½rifie si le joueur peut build instantanï¿½ment

	 * VÃ©rifie si le joueur peut build instantanÃ©ment

	 * 

	 * @return Si le joueur peut build instantanï¿½ment

	 * @return Si le joueur peut build instantanÃ©ment

	 */
	public boolean canInstantlyBuild();

	/**

	 * Change la dimension affichï¿½e par le joueur sans le changer rï¿½ellement de

	 * Change la dimension affichÃ©e par le joueur sans le changer rÃ©ellement de

	 * monde
	 * 
	 * @param world
	 *            La nouvelle dimension
	 */
	public void changePlayerDimension(World.Environment world);

	/**
	 * Clear l'inventaire du joueur (dont l'armure)
	 */
	public void clearInventory();

	/**

	 * Enlï¿½ve le title de l'ï¿½cran du joueur

	 * EnlÄ�ve le title de l'Ã©cran du joueur

	 */
	public void clearTitle();

	public void disguise(Disguise disguise);

	/**
	 * Remplit la barre de faim du joueur
	 */
	public void feed();

	public Object getHandle();
	
	/**

	 * Rï¿½cupï¿½re les groupes secondaires du joueur (par exemple emeraude et
	 * modo). Peut ï¿½tre vide.

	 * RÃ©cupÄ�re les groupes secondaires du joueur (par exemple emeraude et
	 * modo). Peut Ä™tre vide.

	 * 
	 * @return Les groupes secondaires
	 */
	public Collection<String> getAlternateGroups();

	/**

	 * Rï¿½cupï¿½re le mode actuel de jeu du joueur

	 * RÃ©cupÄ�re le mode actuel de jeu du joueur

	 * 
	 * @return Le mode
	 */
	public BadblockMode getBadblockMode();

	/**

	 * Rï¿½cupï¿½re le CustomObjective vu par le joueur

	 * RÃ©cupÄ�re le CustomObjective vu par le joueur

	 * 

	 * @return Le CustomObjective (null si non dï¿½finit)

	 * @return Le CustomObjective (null si non dÃ©finit)

	 */
	public CustomObjective getCustomObjective();

	/**

	 * Rï¿½cupï¿½re le groupe principal du joueur (par exemple admin)

	 * RÃ©cupÄ�re le groupe principal du joueur (par exemple admin)

	 * 
	 * @return Le groupe principal
	 */
	public String getMainGroup();
	
	/**

	 * Met ï¿½ jour le nombre de points boutique du joueur
	 * en rï¿½cupï¿½rant du site sur la mï¿½thode getShopPoints

	 * Met Å• jour le nombre de points boutique du joueur
	 * en rÃ©cupÃ©rant du site sur la mÃ©thode getShopPoints

	 */
	public void refreshShopPoints();

	/**

	 * Rï¿½cupï¿½re le nombre de points boutique

	 * RÃ©cupÄ�re le nombre de points boutique

	 * (attention: il est en cache !)

	 * Ne pas oublier ï¿½ le refresh (refreshShopPoints)

	 * Ne pas oublier Å• le refresh (refreshShopPoints)

	 * @return
	 */
	public int getShopPoints();
	
	public void removeShopPoints(int shopPointsToRemove);
	
	public void addShopPoints(int shopPointsToAdd);
	
	/**

	 * Rï¿½cupï¿½re le ping du joueur

	 * RÃ©cupÄ�re le ping du joueur

	 * 
	 * @return
	 */
	public int getPing();

	/**

	 * Rï¿½cupï¿½re la sï¿½lï¿½ction dï¿½finie par le joueur via un baton de blaze. Peut
	 * retourner null si non dï¿½finie.

	 * RÃ©cupÄ�re la sÃ©lÃ©ction dÃ©finie par le joueur via un baton de blaze. Peut
	 * retourner null si non dÃ©finie.

	 * 

	 * @return La sï¿½lï¿½ction ou null

	 * @return La sÃ©lÃ©ction ou null

	 */
	public CuboidSelection getSelection();

	/**

	 * Rï¿½cupï¿½re un message traduit dans la langue du joueur

	 * RÃ©cupÄ�re un message traduit dans la langue du joueur

	 * 
	 * @param key
	 *            La key du message
	 * @param args
	 *            Les arguments
	 * @return Le message
	 */
	public String[] getTranslatedMessage(String key, Object... args);

	/**
	 * Si le joueur est en mode bypass (pour casser les blocs ect)
	 * 
	 * @return Un boolean
	 */
	public boolean hasAdminMode();
	
	/**

	 * Rï¿½cupï¿½re la version du protocol du joueur

	 * RÃ©cupÄ�re la version du protocol du joueur

	 * @return La version
	 */
	public int getProtocolVersion();

	/**

	 * Vï¿½rifie si le joueur a une des permissions basiques des mini-jeux.

	 * VÃ©rifie si le joueur a une des permissions basiques des mini-jeux.

	 * 
	 * @param permission
	 *            La permission
	 * @return Si le joueur a la permission
	 */
	public boolean hasPermission(GamePermission permission);

	/**

	 * Soigne le joueur (vie, faim, feu et effets nï¿½gatifs de potions)

	 * Soigne le joueur (vie, faim, feu et effets nÃ©gatifs de potions)

	 */
	public void heal();

	public boolean isDisguised();

	/**

	 * Vï¿½rifie si le joueur est invulnï¿½rable (si il ne peut pas prendre de
	 * dï¿½gats).

	 * VÃ©rifie si le joueur est invulnÃ©rable (si il ne peut pas prendre de
	 * dÃ©gats).

	 * 

	 * @return Si le joueur est invulnï¿½rable

	 * @return Si le joueur est invulnÃ©rable

	 */
	public boolean isInvulnerable();

	/**

	 * Permet de savoir si le joueur a ï¿½tï¿½ bloquï¿½ avec

	 * Permet de savoir si le joueur a Ã©tÃ© bloquÃ© avec

	 * {@link #jailPlayerAt(Location)}.
	 * 

	 * @return Si il est bloquï¿½

	 * @return Si il est bloquÃ©

	 */
	public boolean isJailed();

	/**

	 * Permet de vï¿½rifier si le joueur est confinï¿½ dans une zone

	 * Permet de vÃ©rifier si le joueur est confinÃ© dans une zone

	 * 

	 * @return Si il est bloquï¿½

	 * @return Si il est bloquÃ©

	 */
	public boolean isPseudoJailed();

	/**

	 * Permet de faire que le joueur ne puisse pas du tout bouger ï¿½ la position
	 * donnï¿½e.<br>

	 * Permet de faire que le joueur ne puisse pas du tout bouger Å• la position
	 * donnÃ©e.<br>

	 * * Si null, le joueur pourra de nouveau bouger.
	 * 
	 * @param location

	 *            La position oï¿½ il doit rester.

	 *            La position oÅ¯ il doit rester.

	 */
	public void jailPlayerAt(Location location);

	/**

	 * Lance un projectile custom, qui appelera une mï¿½thode lorsqu'il touchera un block ou une entitï¿½

	 * Lance un projectile custom, qui appelera une mÃ©thode lorsqu'il touchera un block ou une entitÃ©

	 * @param projectile La classe du projectile
	 * @param action L'action
	 * @return Le projectile
	 */
	public <T extends Projectile> T launchProjectile(Class<T> projectile, BiConsumer<Block, Entity> action);
	
	/**

	 * Lance un projectile custom, qui appelera une mï¿½thode lorsqu'il touchera un block ou une entitï¿½

	 * Lance un projectile custom, qui appelera une mÃ©thode lorsqu'il touchera un block ou une entitÃ©

	 * @param projectile La classe du projectile
	 * @param action L'action

	 * @param range Gbï¿½h

	 * @param range GbÃ©h

	 * @return Le projectile
	 */
	public <T extends Projectile> T launchProjectile(Class<T> projectile, BiConsumer<Block, Entity> action, int range);

	
	/**
	 * Joue l'animation de l'ouverture ou fermeture d'un coffre au joueur
	 * 
	 * @param block

	 *            Le coffre ï¿½ 'ouvrir/fermer'

	 *            Le coffre Å• 'ouvrir/fermer'

	 * @param open
	 *            Si le coffre est ouvert
	 */
	public void playChestAnimation(Block block, boolean open);

	/**

	 * Force le client ï¿½ voir de la pluie/neige (bien que cela ne soit pas
	 * rï¿½ellement le cas cï¿½tï¿½ serveur).

	 * Force le client Å• voir de la pluie/neige (bien que cela ne soit pas
	 * rÃ©ellement le cas cÃ´tÃ© serveur).

	 * 
	 * @param rain
	 *            Si le joueur voit de la pluie
	 */
	public void playRain(boolean rain);

	/**

	 * Fait jouer un son au joueur avec des paramï¿½tres par dï¿½faut

	 * Fait jouer un son au joueur avec des paramÄ�tres par dÃ©faut

	 * 
	 * @param location
	 *            L'origine du son
	 * @param sound

	 *            Le son ï¿½ jouer

	 *            Le son Å• jouer

	 */
	public void playSound(Location location, Sound sound);

	/**

	 * Fait jouer un son au joueur avec des paramï¿½tres par dï¿½faut

	 * Fait jouer un son au joueur avec des paramÄ�tres par dÃ©faut

	 * 
	 * @param sound

	 *            Le son ï¿½ jouer

	 *            Le son Å• jouer

	 */
	public void playSound(Sound sound);

	/**

	 * Upload un rï¿½sultat de partie pour le joueur. Le joueur en sera notifiï¿½.

	 * Upload un rÃ©sultat de partie pour le joueur. Le joueur en sera notifiÃ©.

	 * 
	 * @param result

	 *            Le rï¿½sultat

	 *            Le rÃ©sultat

	 */
	public void postResult(Result result);

	/**
	 * Permet de faire que le joueur ne puisse pas bouger en dehors d'une
	 * certaine zone<br>
	 * Si null, le joueur pourra de nouveau aller partout.
	 * 
	 * @param location

	 *            La position oï¿½ il doit rester

	 *            La position oÅ¯ il doit rester

	 * @param radius

	 *            Le rayon autour duquel il peut se dï¿½placer

	 *            Le rayon autour duquel il peut se dÃ©placer

	 */
	public void pseudoJail(Location location, double radius);

	/**
	 * Renvoit tous les chunks autour du joueur
	 */
	public void reloadMap();

	/**

	 * Enlï¿½ve les effets nï¿½gatifs (potions)

	 * EnlÄ�ve les effets nÃ©gatifs (potions)

	 */
	public void removeBadPotionEffects();

	/**

	 * Enlï¿½ve tous les effets (potions)

	 * EnlÄ�ve tous les effets (potions)

	 */
	public void removePotionEffects();

	/**

	 * Sauvegarde (envoit ï¿½ Ladder) les donnï¿½es joueurs

	 * Sauvegarde (envoit Å• Ladder) les donnÃ©es joueurs

	 */
	public void saveGameData();

	/**
	 * Envoit une action bar au joueur
	 * 
	 * @param message
	 *            L'action bar
	 */
	public void sendActionBar(String message);

	public void addBossBar(String key, String message, float life, BossBarColor color, BossBarStyle style);
	
	public void changeBossBar(String key, String message);
	
	public void changeBossBarStyle(String key, float life, BossBarColor color, BossBarStyle style);
	
	public void removeBossBar(String key);
	
	public void removeBossBars();
	
	/**

	 * Enlï¿½ve le message de la 'Boss Bar' du joueur (si il y en a un)

	 * EnlÄ�ve le message de la 'Boss Bar' du joueur (si il y en a un)

	 */
	public default void removeBossBar(){
		removeBossBar("defaultbar");
	}
	
	/**
	 * Change le message de la 'Boss Bar' du joueur
	 * 
	 * @param message
	 *            Le message
	 */
	public default void sendBossBar(String message){
		addBossBar("defaultbar", message, 1.0f, BossBarColor.PURPLE, BossBarStyle.SOLID);
	}

	/**

	 * Envoit un packet ï¿½ un joueur. Pour utiliser les packets voir

	 * Envoit un packet Å• un joueur. Pour utiliser les packets voir

	 * {@link fr.badblock.gameapi.GameAPI#createPacket(Class)}.
	 * 
	 * @param packet

	 *            Le packet ï¿½ envoyer.

	 *            Le packet Å• envoyer.

	 */
	public void sendPacket(BadblockOutPacket packet);

	/**
	 * Affiche une particule au joueur
	 * 
	 * @param location
	 *            La position de la particule
	 * @param effect

	 *            La particule (rï¿½cupï¿½rable avec

	 *            La particule (rÃ©cupÃ©rable avec

	 *            {@link GameAPI#createParticleEffect(fr.badblock.gameapi.particles.ParticleEffectType)}
	 */
	public void sendParticle(Location location, ParticleEffect effect);

	/**

	 * Rï¿½cupï¿½re la valeur d'une perssion de type clï¿½/valeur
	 * @param key La clï¿½
	 * @param clazz Le type dmandï¿½

	 * RÃ©cupÄ�re la valeur d'une perssion de type clÃ©/valeur
	 * @param key La clÃ©
	 * @param clazz Le type dmandÃ©

	 * @return Le retour
	 */
	public <T> T getPermissionValue(String key, Class<T> clazz);
	
	/**
	 * Fait changer le joueur de serveur
	 * 
	 * @param server
	 *            Le nouveau serveur
	 */
	public void sendPlayer(String server);

	/**
	 * Envoit le header et le footer de la tablist au joueur. Utiliser le

	 * symbole {@literal \n} pour revenir ï¿½ la ligne.

	 * symbole {@literal \n} pour revenir Å• la ligne.

	 * 
	 * @param header
	 *            Le header
	 * @param footer
	 *            Le footer
	 */
	public void sendTabHeader(String header, String footer);

	/**
	 * Envoit les timings (du title/action bar) au joueur
	 * 
	 * @param fadeIn

	 *            Le temps que le message met ï¿½ s'afficher

	 *            Le temps que le message met Å• s'afficher

	 * @param stay

	 *            Le temps oï¿½ le message reste

	 *            Le temps oÅ¯ le message reste

	 * @param fadeOut

	 *            Le temps que le message met ï¿½ disparaï¿½tre

	 *            Le temps que le message met Å• disparaÃ®tre

	 */
	public void sendTimings(long fadeIn, long stay, long fadeOut);

	/**
	 * Envoit un title au joueur
	 * 
	 * @param title
	 *            Le title
	 * @param subtitle
	 *            Le subtitle
	 */
	public void sendTitle(String title, String subtitle);

	/**
	 * Envoit une action bar traduite au joueur. Pour plus d'informations voir
	 * {@link fr.badblock.gameapi.utils.i18n.I18n}
	 * 
	 * @param key
	 *            La key du message
	 * @param args
	 *            Les arguments
	 */
	public void sendTranslatedActionBar(String key, Object... args);

	/**
	 * Envoit une BossBar traduite au joueur. Pour plus d'informations voir
	 * {@link fr.badblock.gameapi.utils.i18n.I18n}
	 * 
	 * @param key
	 *            La key du message
	 * @param args
	 *            Les arguments
	 */
	public void sendTranslatedBossBar(String key, Object... args);

	/**
	 * Envoit un message traduit au joueur. Pour plus d'informations voir
	 * {@link fr.badblock.gameapi.utils.i18n.I18n}
	 * 
	 * @param key
	 *            La key du message
	 * @param args
	 *            Les arguments
	 */
	public void sendTranslatedMessage(String key, Object... args);
	
	public default void sendMessage(BaseComponent... components)
	{
		this.sendPacket(GameAPI.getAPI().createPacket(PlayChat.class).setContent(components));
	}

	/**
	 * Envoit le header et le footer de la tablist au joueur.
	 * 
	 * @param header
	 *            Le header
	 * @param footer
	 *            Le footer
	 */
	public void sendTranslatedTabHeader(TranslatableString header, TranslatableString footer);

	/**
	 * Envoit un title traduit au joueur (les deux premiers messages de l'array
	 * seront prit). Pour plus d'informations voir
	 * {@link fr.badblock.gameapi.utils.i18n.I18n}
	 * 
	 * @param key
	 *            La key du message
	 * @param args
	 *            Les arguments
	 */
	public void sendTranslatedTitle(String key, Object... args);

	/**

	 * Dï¿½finit si le joueur est en mode bypass (pour casser les blocs ect)

	 * DÃ©finit si le joueur est en mode bypass (pour casser les blocs ect)

	 * 
	 * @param adminMode
	 *            Un boolean
	 */
	public void setAdminMode(boolean adminMode);

	/**

	 * Change le nombre de flï¿½ches dans le corps du joueurs.

	 * Change le nombre de flÄ�ches dans le corps du joueurs.

	 * 
	 * @param amount

	 *            Le nombe de flï¿½ches.

	 *            Le nombe de flÄ�ches.

	 */
	public void setArrowsInBody(byte amount);

	/**

	 * Change le mode de jeu du joueur (les protections et items seront gï¿½rï¿½s

	 * Change le mode de jeu du joueur (les protections et items seront gÃ©rÃ©s

	 * automatiquement)
	 * 
	 * @param newMode
	 *            Le mode
	 */
	public void setBadblockMode(BadblockMode newMode);

	/**

	 * Dï¿½finit si le joueur peut build

	 * DÃ©finit si le joueur peut build

	 * 
	 * @param canBuild
	 *            Si le joueur peut build
	 */
	public void setCanBuild(boolean canBuild);

	/**

	 * Dzï¿½finit si le joueur peut build instantanï¿½ment

	 * DzÃ©finit si le joueur peut build instantanÃ©ment

	 * 
	 * @param instantlyBuild

	 *            Si le joueur peut build instantanï¿½ment

	 *            Si le joueur peut build instantanÃ©ment

	 */
	public void setCanInstantlyBuild(boolean instantlyBuild);

	/**

	 * Active ou dï¿½sactive la collision entre le joueur et les entitï¿½s

	 * Active ou dÃ©sactive la collision entre le joueur et les entitÃ©s

	 * 
	 * @param collision

	 *            Si les collisions sont activï¿½s

	 *            Si les collisions sont activÃ©s

	 */
	public void setEntityCollision(boolean collision);

	/**

	 * Dï¿½finit si le joueur est invulnï¿½rable (si il ne peut pas prendre de
	 * dï¿½gats).<br>

	 * DÃ©finit si le joueur est invulnÃ©rable (si il ne peut pas prendre de
	 * dÃ©gats).<br>

	 * Attention, un changement de gamemode l'annule.
	 * 
	 * @param invulnerable

	 *            Si le joueur est invulnï¿½rable

	 *            Si le joueur est invulnÃ©rable

	 */
	public void setInvulnerable(boolean invulnerable);

	/**

	 * Dï¿½finit si le joueur a ou non des informations de dï¿½bug (F3) rï¿½duites
	 * (par exemple plus les coordonnï¿½es).<br>
	 * Bien entendu, cela est fait cï¿½tï¿½ client, ce n'est donc pas fiable (le
	 * client peut ï¿½tre moddï¿½).

	 * DÃ©finit si le joueur a ou non des informations de dÃ©bug (F3) rÃ©duites
	 * (par exemple plus les coordonnÃ©es).<br>
	 * Bien entendu, cela est fait cÃ´tÃ© client, ce n'est donc pas fiable (le
	 * client peut Ä™tre moddÃ©).

	 * 
	 * @param reducedDebugInfo

	 *            Si le joueur a des informations de dï¿½bug rï¿½duites

	 *            Si le joueur a des informations de dÃ©bug rÃ©duites

	 */
	public void setReducedDebugInfo(boolean reducedDebugInfo);

	/**
	 * Affiche un CustomObjective au joueur
	 * 
	 * @param objective
	 *            L'objective
	 */
	public void showCustomObjective(CustomObjective objective);

	/**

	 * Fait voir l'image de 'dï¿½mo' de MineCraft au joueur. Utile uniquement si
	 * le serveur est dotï¿½ d'un resource pack.<br>
	 * Le joueur doit ï¿½tre en GameMode 3

	 * Fait voir l'image de 'dÃ©mo' de MineCraft au joueur. Utile uniquement si
	 * le serveur est dotÃ© d'un resource pack.<br>
	 * Le joueur doit Ä™tre en GameMode 3

	 */
	public void showDemoScreen();

	/**
	 * Affiche un texte 'volant' au joueur.
	 * 
	 * @param text

	 *            Le texte ï¿½ afficher.

	 *            Le texte Å• afficher.

	 * @param location
	 *            La position du texte (si null, juste devant ses yeux)
	 * @param lifeTime

	 *            La durï¿½e de vie du texte (en ticks)

	 *            La durÃ©e de vie du texte (en ticks)

	 * @param offset

	 *            Le dï¿½placement que peut subir le texte par rapport ï¿½ la

	 *            Le dÃ©placement que peut subir le texte par rapport Å• la

	 *            position initiale (random). Aucun si 0.
	 */
	public void showFloatingText(String text, Location location, long lifeTime, double offset);

	/**
	 * Affiche un texte 'volant' traduit au joueur.
	 * 
	 * @param location
	 *            La position du texte (si null, juste devant ses yeux)
	 * @param lifeTime

	 *            La durï¿½e de vie du texte (en ticks)

	 *            La durÃ©e de vie du texte (en ticks)

	 * @param offset

	 *            Le dï¿½placement que peut subir le texte par rapport ï¿½ la

	 *            Le dÃ©placement que peut subir le texte par rapport Å• la

	 *            position initiale (random). Aucun si 0.
	 * @param key
	 *            La key du message
	 * @param args
	 *            Les arguments
	 */
	public void showTranslatedFloatingText(Location location, long lifeTime, double offset, String key, Object... args);

	public void undisguise();

	/**
	 * Renvoit un chunk au joueur
	 * 
	 * @param chunk
	 *            Le chunk
	 */
	public void updateChunk(Chunk chunk);
	
	/**
	 * Compte les items d'un certains type
	 * @param type Le material
	 * @param data La data
	 * @return Le nombre d'items
	 */
	public int countItems(Material type, byte data);
	
	/**

	 * Enlï¿½ve les items d'un certains type

	 * EnlÄ�ve les items d'un certains type

	 * @param type Le material
	 * @param data La data

	 * @param amount Le nombre d'items ï¿½ enlever (-1 = tous)
	 * @return Le nombre qui n'a pas pu ï¿½tre retirï¿½

	 * @param amount Le nombre d'items Å• enlever (-1 = tous)
	 * @return Le nombre qui n'a pas pu Ä™tre retirÃ©

	 */
	public int removeItems(Material type, byte data, int amount);

	/**

	 * Rï¿½cupï¿½rer le timestamp quand le joueur a utilisï¿½ une 
	 * fausse entitï¿½

	 * RÃ©cupÃ©rer le timestamp quand le joueur a utilisÃ© une 
	 * fausse entitÃ©

	 * @return un timestamp en millisecondes
	 */
	public long getLastFakeEntityUsedTime();
	
	/**

	 * Dire ï¿½ l'objet que l'on a utilisï¿½ une fausse entitï¿½
	 * rï¿½cemment afin de le comptabiliser

	 * Dire Å• l'objet que l'on a utilisÃ© une fausse entitÃ©
	 * rÃ©cemment afin de le comptabiliser

	 */
	public void useFakeEntity();
	
	public void setVisible(boolean visible);
	
	public void setVisible(boolean visible, Predicate<BadblockPlayer> applicable);
	
	/**
	 * @deprecated
	 */
	public boolean isVisible();

	public Predicate<BadblockPlayer> getVisiblePredicate();
	public Predicate<BadblockPlayer> getInvisiblePredicate();
	
	/**
	 * Si les data sont fetch
	 * @return
	 */
	public boolean isDataFetch();
	
	public int getVipLevel();
	
	public boolean hasVipLevel(int level, boolean showErrorMessage);

	public boolean canOnlyJoinWhileWaiting();
	
	public void setOnlyJoinWhileWaiting(long time);
	
	public void setLeaves(List<Long> leaves);
	
	public List<Long> getLeaves();
	
	public void setPlayerSkin(String skinUrl);
	
	public void setPlayerSkin(String skinUrl, String capeUrl);

	public void setTextureProperty(String value, String signature);
	
	public RankedPlayer getRanked();
	
}
