package fr.badblock.gameapi.packets;

/**
 * Représente un packet envoyé par un joueur. Le packet est intercépté et
 * récupérable grâce aux listeners.
 * 
 * Si un packet n'est pas assez documenté, penser ŕ se documenter grâce ŕ
 * http://wiki.vg/Protocol<br>
 * Pour instancier un packet, voir
 * {@link fr.badblock.gameapi.GameAPI#createPacket(Class)}<br>
 * La classe n'est pas ŕ confondre avec {@link BadblockOutPacket}
 * 
 * @author LeLanN
 */
public interface BadblockInPacket extends BadblockPacket {
	/**
	 * Récupčre le type de packet
	 * 
	 * @return Le type de packet
	 */
	public BadblockInPackets getType();
}
