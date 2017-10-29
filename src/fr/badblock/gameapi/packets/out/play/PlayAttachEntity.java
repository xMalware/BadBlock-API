package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé quand une entité est 'attachée' ŕ une autre (véhicule ou
 * laisse).
 * 
 * @author LeLanN
 */
public interface PlayAttachEntity extends BadblockOutPacket {
	/**
	 * Récupčre l'ID de l'entité attachée
	 * 
	 * @return L'ID
	 */
	public int getEntityId();

	/**
	 * Récupčre l'ID du véhicule (si -1, l'entité descendra du véhicle)
	 * 
	 * @return L'ID
	 */
	public int getVehicleId();

	/**
	 * Si il s'agit d'une laisse
	 * 
	 * @return Un boolean
	 */
	public boolean isLeashes();

	/**
	 * Définit l'ID de l'entité attachée
	 * 
	 * @param entityId
	 *            L'ID
	 * @return Le packet
	 */
	public PlayAttachEntity setEntityId(int entityId);

	/**
	 * Si il s'agit d'une laisse
	 * 
	 * @param leashes
	 *            Si true, c'est une laisse
	 * @return Le packet
	 */
	public PlayAttachEntity setLeashes(boolean leashes);

	/**
	 * Définit l'ID du véhicule (si -1, l'entité descendra du véhicule)
	 * 
	 * @param vehicleId
	 *            L'ID
	 * @return Le packet
	 */
	public PlayAttachEntity setVehicleId(int vehicleId);
}
