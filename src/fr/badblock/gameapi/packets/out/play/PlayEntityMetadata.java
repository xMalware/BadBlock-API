package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.packets.watchers.WatcherEntity;

public interface PlayEntityMetadata extends BadblockOutPacket {
	public int getEntityId();

	public WatcherEntity getWatcher();

	public PlayEntityMetadata setEntityId(int entityId);

	public PlayEntityMetadata setWatcher(WatcherEntity watcher);
}
