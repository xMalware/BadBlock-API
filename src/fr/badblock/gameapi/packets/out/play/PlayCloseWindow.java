package fr.badblock.gameapi.packets.out.play;

import org.bukkit.entity.Player;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé par le serveur pour forcer le client ŕ fermer un
 * inventaire.<br>
 * Aucune méthode ajouté car {@link Player#closeInventory()} et
 * {@link #setCancelled(boolean)} suffisent.
 * 
 * @author LeLanN
 */
public interface PlayCloseWindow extends BadblockOutPacket {

}
