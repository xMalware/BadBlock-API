package fr.badblock.gameapi.packets.out.play;

import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldType;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé pour faire changer le joueur de dimension (si le joueur est
 * mort, il respawnera)<br>
 * 
 * @author LeLanN
 */
public interface PlayRespawn extends BadblockOutPacket {
	public PlayRespawn setDifficulty(Difficulty difficulty);

	public PlayRespawn setDimension(World.Environment dimension);

	public PlayRespawn setGameMode(GameMode gamemode);

	public PlayRespawn setWorldType(WorldType type);
}
