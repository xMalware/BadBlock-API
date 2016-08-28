package fr.badblock.gameapi.packets.out.play;

import org.bukkit.entity.Player;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé pour donner le resource pack du serveur. Aucune méthode ajouté
 * car {@link Player#setResourcePack(String)} et {@link #setCancelled(boolean)}
 * suffisent.
 * 
 * @author LeLanN
 */
public interface PlayResourcePackSend extends BadblockOutPacket {

}
