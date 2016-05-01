package fr.badblock.gameapi.packets.out.play;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Permet de téléporter le joueur<br>
 * Aucune méthode ajouté car {@link Player#teleport(Location)} et {@link #setCancelled(boolean)} suffisent.
 * @author LeLanN
 */
public interface PlayPosition extends BadblockOutPacket {

}
