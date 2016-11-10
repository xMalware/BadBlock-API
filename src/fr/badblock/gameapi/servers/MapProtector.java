package fr.badblock.gameapi.servers;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.block.Action;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Classe permettant de faciliter l'utilisation des events Bukkit. Si une
 * méthode retourne 'true' rien ne sera fait (hormis {@link #destroyArrow()}).
 * Si une méthode retourne 'false' un ou des events seront annulés.<br>
 * 
 * Pour utiliser, définir le protecteur avec
 * {@link GameAPI#setMapProtector(MapProtector)}.
 * 
 * @author LeLanN
 */
public interface MapProtector {
	public boolean allowBlockFormChange(Block block);

	public boolean allowBlockPhysics(Block block);

	public boolean allowExplosion(Location location);

	public boolean allowFire(Block block);

	public boolean allowInteract(Entity entity);

	public boolean allowLeavesDecay(Block block);

	public boolean allowMelting(Block block);

	public boolean allowPistonMove(Block block);

	public boolean allowRaining();

	public boolean blockBreak(BadblockPlayer player, Block block);

	/*
	 * Player actions
	 */
	public boolean blockPlace(BadblockPlayer player, Block block);

	public boolean canBeingDamaged(BadblockPlayer player);

	/*
	 * Blocks
	 */
	public boolean canBlockDamage(Block block);

	public boolean canCombust(Entity entity);

	public boolean canCreatureSpawn(Entity creature, boolean isPlugin);

	public boolean canDrop(BadblockPlayer player);

	public boolean canEmptyBucket(BadblockPlayer player);

	public boolean canEnchant(BadblockPlayer player, Block table);

	public boolean canEntityBeingDamaged(Entity entity);

	public boolean canEntityBeingDamaged(Entity entity, BadblockPlayer badblockPlayer);

	public boolean canFillBucket(BadblockPlayer player);

	public boolean canInteract(BadblockPlayer player, Action action, Block block);

	public boolean canInteractArmorStand(BadblockPlayer player, ArmorStand entity);

	public boolean canInteractEntity(BadblockPlayer player, Entity entity);

	public boolean canItemDespawn(Item item);

	public boolean canItemSpawn(Item item);

	public boolean canLostFood(BadblockPlayer player);

	public boolean canPickup(BadblockPlayer player);

	public boolean canSoilChange(Block soil);

	/*
	 * Entities
	 */
	public boolean canSpawn(Entity entity);

	public boolean canUseBed(BadblockPlayer player, Block bed);

	public boolean canUsePortal(BadblockPlayer player);

	/**
	 * Si les flèches sont détruites dès qu'elles touchent le sol
	 * (optimisation).
	 * 
	 * @return False si l'on ne veut rien changer
	 */
	public boolean destroyArrow();

	public boolean healOnJoin(BadblockPlayer player);

	public boolean modifyItemFrame(BadblockPlayer player, Entity itemFrame);

	public boolean modifyItemFrame(Entity itemframe);
}
