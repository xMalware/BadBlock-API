package fr.badblock.gameapi.packets.out.play;

import org.bukkit.inventory.PlayerInventory;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé par le serveur pour faire changer le slot séléctionné par le
 * joueur. Aucune méthode ajouté car
 * {@link PlayerInventory#setHeldItemSlot(int)} et
 * {@link #setCancelled(boolean)} suffisent.
 * 
 * @author LeLanN
 */
public interface PlayHeldItemSlot extends BadblockOutPacket {

}
