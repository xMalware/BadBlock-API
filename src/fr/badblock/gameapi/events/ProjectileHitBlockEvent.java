package fr.badblock.gameapi.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;

import lombok.Getter;

/**
 * Appelé lorsque un projectile arrive sur un block.
 * 
 * @author LeLanN
 */
public class ProjectileHitBlockEvent extends BlockEvent {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Getter
	private Projectile projectile;

	public ProjectileHitBlockEvent(Projectile projectile, Block block) {
		super(block);
		this.projectile = projectile;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
