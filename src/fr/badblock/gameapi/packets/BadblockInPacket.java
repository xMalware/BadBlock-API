package fr.badblock.gameapi.packets;

/**
 * Représente un packet envoyé par un joueur. Le packet est intercépté et récupérable grâce aux listeners.
 * 
 * Si un packet n'est pas assez documenté, penser à se documenter grâce à http://wiki.vg/Protocol<br>
 * Pour instancier un packet, voir {@link fr.badblock.gameapi.GameAPI#createPacket(Class)}<br>
 * La classe n'est pas à confondre avec {@link BadblockOutPacket}
 * 
 * @author LeLanN
 */
public interface BadblockInPacket extends BadblockPacket {
	/**
	 * Récupère le type de packet
	 * @return Le type de packet
	 */
	public BadblockInPackets getType();
}
