package fr.badblock.gameapi.particles;

import org.bukkit.Material;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Représente les données de certains effets, c'est ŕ dire :
 * <ul>
 * <li>{@link ParticleEffectType#BLOCK_CRACK}
 * <li>{@link ParticleEffectType#BLOCK_DUST}
 * <li>{@link ParticleEffectType#ITEM_CRACK}
 * </ul>
 * 
 * @author LeLanN
 */
@Data
@AllArgsConstructor
public abstract class ParticleData {
	/**
	 * Utilisé pour {@link ParticleEffectType#BLOCK_DUST} et
	 * {@link ParticleEffectType#BLOCK_CRACK}
	 * 
	 * @author LeLanN
	 */
	public static final class BlockData extends ParticleData {
		public BlockData(int[] data) {
			super(data);
		}

		@SuppressWarnings("deprecation")
		public BlockData(Material material, byte data) throws IllegalArgumentException {
			super(material, data, new int[] { material.getId() + (data << 12) });

			if (!material.isBlock()) {
				throw new IllegalArgumentException("The material is not a block");
			}
		}
	}
	/**
	 * Utilisé pour {@link ParticleEffectType#ITEM_CRACK}
	 * 
	 * @author LeLanN
	 */
	public static final class ItemData extends ParticleData {
		@SuppressWarnings("deprecation")
		public ItemData(Material material, byte data) {
			super(material, data, new int[] { material.getId(), data });
		}
	}
	@SuppressWarnings("deprecation")
	public static ParticleData load(int[] data) {
		if (data == null || data.length == 0)
			return null;

		if (data.length == 2) {
			return new ItemData(Material.getMaterial(data[0]), (byte) data[2]);
		} else if (data.length == 1) {
			return new BlockData(data);
		} else
			return null;
	}

	private final Material material;

	private final byte data;

	private final int[] packetData;

	protected ParticleData(int[] data) {
		this.material = Material.GRASS;
		this.data = 0;
		this.packetData = data;
	}

	public String getPacketDataString() {
		return "_" + packetData[0] + "_" + packetData[1];
	}
}
