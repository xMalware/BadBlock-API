package fr.badblock.gameapi.particles;

import fr.badblock.gameapi.particles.ParticleData.BlockData;
import fr.badblock.gameapi.particles.ParticleData.ItemData;
import lombok.Getter;

/**
 * Représente les différents type de particules possible
 * 
 * @author LeLanN
 */
@Getter
public enum ParticleEffectType {
	/**
	 * Une particule affichée lors de l'explosion d'une TNT ou d'un creeper
	 * <ul>
	 * <li>Cela ressemble ŕ un "nuage blanc"
	 * <li>La valeur de la speed value va influer sur la vitesse ŕ laquelle la
	 * particule va s'envoler
	 * </ul>
	 */
	EXPLOSION_NORMAL("explode", 0),
	/**
	 * Une particule affichée lors de l'explosion d'une fireball (ghast) ou d'un
	 * crâne de wither:
	 * <ul>
	 * <li>Ressemble ŕ une balle grise qui disparaît
	 * <li>La valeur de la speed value influe légčrement sur la taille de la
	 * particule
	 * </ul>
	 */
	EXPLOSION_LARGE("largeexplode", 1),
	/**
	 * Une particule affichée lors de l'explosion d'une TNT ou d'un creeper
	 * <ul>
	 * <li>Ressemble ŕ une multitude de balles grises qui disparaissent
	 * <li>La speed value n'a aucune influence
	 * </ul>
	 */
	EXPLOSION_HUGE("hugeexplosion", 2),
	/**
	 * Une particule affichée lors de l'utilisation d'un firework
	 * <ul>
	 * <li>Ressemble ŕ une étoile blanche
	 * <li>La speed value va influer sur la vitesse ŕ laquelle la particule va
	 * s'envoler
	 * </ul>
	 */
	FIREWORKS_SPARK("fireworksSpark", 3),
	/**
	 * Une particule affichée lorsque une entité nage dans l'eau ou lorsque une
	 * flčche y entre
	 * <ul>
	 * <li>Ressemble ŕ une bulle
	 * <li>La speed value va influer sur la vitesse ŕ laquelle la particule va
	 * s'envoler
	 * </ul>
	 */
	WATER_BUBBLE("bubble", 4, null, true),
	/**
	 * Une particule affichée lorsque une entité nage ou qu'un loup se secoue
	 * <ul>
	 * <li>Ressemble ŕ une goutte bleue
	 * <li>La speed value n'a aucune influence
	 * </ul>
	 */
	WATER_SPLASH("splash", 5),
	/**
	 * Une particule affichée lorsque l'on pęche
	 * <ul>
	 * <li>Ressemble ŕ une goutelette bleu
	 * <li>La speed value va influer sur la vitesse ŕ laquelle la particule va
	 * s'envoler
	 * </ul>
	 */
	WATER_WAKE("wake", 6),
	/**
	 * Une particule affichée par l'eau
	 * <ul>
	 * <li>Ressemble ŕ un petit carré bleu
	 * <li>La speed value n'a aucune influence
	 * </ul>
	 */
	SUSPENDED("suspended", 7, null, true),
	/**
	 * Une particule affichée lorsque l'on est pręt de la bedrock
	 * <ul>
	 * <li>Ressemble ŕ un petit carré gris
	 * <li>La speed value n'a aucune influence
	 * </ul>
	 */
	SUSPENDED_DEPTH("depthSuspend", 8),
	/**
	 * Une particule affichée lors d'un coup critique ou lorsque l'on reçoit une
	 * flčche
	 * <ul>
	 * <li>Ressemble ŕ une croix marron
	 * <li>La speed value va influer sur la vitesse ŕ laquelle la particule va
	 * s'envoler
	 * </ul>
	 */
	CRIT("crit", 9),
	/**
	 * Une particule affichée lors d'un coup avec une arme enchantée
	 * <ul>
	 * <li>Ressemble ŕ une étoile cyan
	 * <li>La speed value va influer sur la vitesse ŕ laquelle la particule va
	 * s'envoler
	 * </ul>
	 */
	CRIT_MAGIC("magicCrit", 10),
	/**
	 * Une particule affichée par les tnt allumées, les torches, les droppers,
	 * les dispensers, les portails de l'end et les brewing stands et les
	 * spawners
	 * <ul>
	 * <li>Ressemble ŕ un petit nuage gris
	 * <li>La speed value va influer sur la vitesse ŕ laquelle la particule va
	 * s'envoler
	 * </ul>
	 */
	SMOKE_NORMAL("smoke", 11),
	/**
	 * Une particule affichée par le feu, les fours sur minecart et les blazes
	 * <ul>
	 * <li>Ressemble ŕ un gros nuage gris
	 * <li>La speed value va influer sur la vitesse ŕ laquelle la particule va
	 * s'envoler
	 * </ul>
	 */
	SMOKE_LARGE("largesmoke", 12),
	/**
	 * Une particule affichée lorsque une potion jetable / xp bottle attérit
	 * <ul>
	 * <li>Ressemble ŕ un petit tourbillon blanc
	 * <li>Si ŕ 0, la particule n'ira que vers le haut
	 * </ul>
	 */
	SPELL("spell", 13),
	/**
	 * Une particule affichée lorsque une potion jetable attérit
	 * <ul>
	 * <li>Ressemble ŕ une croix blanche
	 * <li>Si ŕ 0, la particule n'ira que vers le haut
	 * </ul>
	 */
	SPELL_INSTANT("instantSpell", 14),
	/**
	 * Une particule affichée lorsque qu'un effet de potion est actif
	 * <ul>
	 * <li>Ressemble ŕ un petit tourbillon coloré
	 * <li>La speed value influe sur la luminosité de la particule (0 = noir, 1
	 * = brillant)
	 * </ul>
	 */
	SPELL_MOB("mobSpell", 15),
	/**
	 * Une particule affichée lorsque qu'un effet de potion est actif via un
	 * beacon
	 * <ul>
	 * <li>Ressemble ŕ un petit toubillon transparent coloré
	 * <li>La speed value influe sur la luminosité de la particule (0 = noir, 1
	 * = brillant)
	 * </ul>
	 */
	SPELL_MOB_AMBIENT("mobSpellAmbient", 16),
	/**
	 * Une particule affichée par les sorcičres
	 * <ul>
	 * <li>Ressemble ŕ une croix violette
	 * <li>Si ŕ 0, la particule n'ira que vers le haut
	 * </ul>
	 */
	SPELL_WITCH("witchMagic", 17),
	/**
	 * A particle effect which is displayed by blocks beneath a water source:
	 * <ul>
	 * <li>It looks like a blue drip
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	DRIP_WATER("dripWater", 18),
	/**
	 * A particle effect which is displayed by blocks beneath a lava source:
	 * <ul>
	 * <li>It looks like an orange drip
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	DRIP_LAVA("dripLava", 19),
	/**
	 * A particle effect which is displayed when attacking a villager in a
	 * village:
	 * <ul>
	 * <li>It looks like a cracked gray heart
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	VILLAGER_ANGRY("angryVillager", 20),
	/**
	 * A particle effect which is displayed when using bone meal and trading
	 * with a villager in a village:
	 * <ul>
	 * <li>It looks like a green star
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	VILLAGER_HAPPY("happyVillager", 21),
	/**
	 * A particle effect which is displayed by mycelium:
	 * <ul>
	 * <li>It looks like a tiny gray square
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	TOWN_AURA("townaura", 22),
	/**
	 * A particle effect which is displayed by note blocks:
	 * <ul>
	 * <li>It looks like a colored note
	 * <li>The speed value causes the particle to be colored green when set to 0
	 * </ul>
	 */
	NOTE("note", 23),
	/**
	 * A particle effect which is displayed by nether portals, endermen, ender
	 * pearls, eyes of ender, ender chests and dragon eggs:
	 * <ul>
	 * <li>It looks like a purple cloud
	 * <li>The speed value influences the spread of this particle effect
	 * </ul>
	 */
	PORTAL("portal", 24),
	/**
	 * A particle effect which is displayed by enchantment tables which are
	 * nearby bookshelves:
	 * <ul>
	 * <li>It looks like a cryptic white letter
	 * <li>The speed value influences the spread of this particle effect
	 * </ul>
	 */
	ENCHANTMENT_TABLE("enchantmenttable", 25),
	/**
	 * A particle effect which is displayed by torches, active furnaces, magma
	 * cubes and monster spawners:
	 * <ul>
	 * <li>It looks like a tiny flame
	 * <li>The speed value influences the velocity at which the particle flies
	 * off
	 * </ul>
	 */
	FLAME("flame", 26),
	/**
	 * A particle effect which is displayed by lava:
	 * <ul>
	 * <li>It looks like a spark
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	LAVA("lava", 27),
	/**
	 * A particle effect which is currently unused:
	 * <ul>
	 * <li>It looks like a transparent gray square
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	FOOTSTEP("footstep", 28),
	/**
	 * A particle effect which is displayed when a mob dies:
	 * <ul>
	 * <li>It looks like a large white cloud
	 * <li>The speed value influences the velocity at which the particle flies
	 * off
	 * </ul>
	 */
	CLOUD("cloud", 29),
	/**
	 * A particle effect which is displayed by redstone ore, powered redstone,
	 * redstone torches and redstone repeaters:
	 * <ul>
	 * <li>It looks like a tiny colored cloud
	 * <li>The speed value causes the particle to be colored red when set to 0
	 * </ul>
	 */
	REDSTONE("reddust", 30),
	/**
	 * A particle effect which is displayed when snowballs hit a block:
	 * <ul>
	 * <li>It looks like a little piece with the snowball texture
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	SNOWBALL("snowballpoof", 31),
	/**
	 * A particle effect which is currently unused:
	 * <ul>
	 * <li>It looks like a tiny white cloud
	 * <li>The speed value influences the velocity at which the particle flies
	 * off
	 * </ul>
	 */
	SNOW_SHOVEL("snowshovel", 32),
	/**
	 * A particle effect which is displayed by slimes:
	 * <ul>
	 * <li>It looks like a tiny part of the slimeball icon
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	SLIME("slime", 33),
	/**
	 * A particle effect which is displayed when breeding and taming animals:
	 * <ul>
	 * <li>It looks like a red heart
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	HEART("heart", 34),
	/**
	 * A particle effect which is displayed by barriers:
	 * <ul>
	 * <li>It looks like a red box with a slash through it
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	BARRIER("barrier", 35),
	/**
	 * A particle effect which is displayed when breaking a tool or eggs hit a
	 * block:
	 * <ul>
	 * <li>It looks like a little piece with an item texture
	 * </ul>
	 */
	ITEM_CRACK("iconcrack", 36, ItemData.class, false),
	/**
	 * A particle effect which is displayed when breaking blocks or sprinting:
	 * <ul>
	 * <li>It looks like a little piece with a block texture
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	BLOCK_CRACK("blockcrack", 37, BlockData.class, false),
	/**
	 * A particle effect which is displayed when falling:
	 * <ul>
	 * <li>It looks like a little piece with a block texture
	 * </ul>
	 */
	BLOCK_DUST("blockdust", 38, BlockData.class, false),
	/**
	 * A particle effect which is displayed when rain hits the ground:
	 * <ul>
	 * <li>It looks like a blue droplet
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	WATER_DROP("droplet", 39),
	/**
	 * A particle effect which is currently unused:
	 * <ul>
	 * <li>It has no visual effect
	 * </ul>
	 */
	ITEM_TAKE("take", 40),
	/**
	 * A particle effect which is displayed by elder guardians:
	 * <ul>
	 * <li>It looks like the shape of the elder guardian
	 * <li>The speed value has no influence on this particle effect
	 * </ul>
	 */
	MOB_APPEARANCE("mobappearance", 41);

	private String name;
	private int id;
	private Class<? extends ParticleData> data;
	private boolean needWater;

	ParticleEffectType(String name, int id) {
		this(name, id, null, false);
	}

	ParticleEffectType(String name, int id, Class<? extends ParticleData> data, boolean needWater) {
		this.name = name;
		this.id = id;
		this.data = data;
		this.needWater = needWater;
	}
}