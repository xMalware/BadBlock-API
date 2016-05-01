package fr.badblock.gameapi.packets.watchers;

import org.bukkit.entity.Horse;

import lombok.Getter;

public interface WatcherHorse extends WatcherAgeable {
	public WatcherHorse addHorseFlag(HorseFlag flag);
	
	public WatcherHorse removeFlag(HorseFlag flag);
	
	public WatcherHorse setArmor(HorseArmor armor);
	
	public WatcherHorse setType(Horse.Variant horse);
	
	public WatcherHorse setColor(Horse.Color color, Horse.Style style);
	
	public enum HorseFlag {
		IS_TAME(0x02),
		HAS_SADDLE(0x04),
		HAS_CHEST(0x08),
		IS_BRED(0x10),
		IS_EATING(0x20),
		IS_REARING(0x40),
		MOUTH_OPEN(0x80);

		@Getter
		private final int value;

		HorseFlag(int value) {
			this.value = value;
		}
	}
	
	public enum HorseArmor {
		NO_ARMOR(0),
		IRON_ARMOR(1),
		GOLD_ARMOR(2),
		DIAMOND_ARMOR(3);

		@Getter
		private final int value;

		HorseArmor(int value) {
			this.value = value;
		}
	}
}
