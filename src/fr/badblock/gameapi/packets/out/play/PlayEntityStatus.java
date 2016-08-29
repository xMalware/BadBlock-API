package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

public interface PlayEntityStatus extends BadblockOutPacket {
	public enum EntityStatus {
		UNUSED((byte) 1), ENTITY_HURT((byte) 2), ENTITY_DEAD((byte) 3), GOLEM_THROW_UP_ARMS((byte) 4), MOB_HEART(
				(byte) 5), MOB_SMOKE((byte) 6), WOLF_SHAKING((byte) 7), EATING_ACCEPTED((byte) 8), SHEEP_EATING_GRASS(
						(byte) 9), TNT_IGNITE((byte) 10), GOLEM_ROSE((byte) 11), VILLAGER_MATING(
								(byte) 12), VILLAGER_ANGRY((byte) 13), VILLAGER_HAPPY((byte) 14), WITCH_ANIMATION(
										(byte) 15), ZOMBIE_CONVERTING((byte) 16), FIREWORK_EXPLODE(
												(byte) 17), ANIMAL_IN_LOVE((byte) 18), SQUID_ROTATION_RESET(
														(byte) 19), SPAWN_EXPLOSION_PARTICLE(
																(byte) 20), PLAY_GUARDIAN_SOUND(
																		(byte) 21), REDUCED_DEBUG_ENABLE(
																				(byte) 22), REDUCED_DEBUG_DISABLE(
																						(byte) 23);

		public static EntityStatus getByValue(byte value) {
			for (EntityStatus c : values())
				if (c.getValue() == value)
					return c;
			return null;
		}

		@Getter
		private final byte value;

		EntityStatus(byte value) {
			this.value = value;
		}
	}

	public int getEntityId();

	public EntityStatus getStatus();

	public PlayEntityStatus setEntityId(int entityId);

	public PlayEntityStatus setStatus(EntityStatus status);
}
