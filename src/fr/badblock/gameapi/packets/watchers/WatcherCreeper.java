package fr.badblock.gameapi.packets.watchers;

import lombok.Getter;

public interface WatcherCreeper extends WatcherLivingEntity {
	public enum CreeperState {
		IDLE((byte) -1), FUSE((byte) 1),;

		@Getter
		private final byte value;

		CreeperState(byte value) {
			this.value = value;
		}
	}

	public WatcherCreeper setPowered(boolean powered);

	public WatcherCreeper setState(CreeperState state);
}
