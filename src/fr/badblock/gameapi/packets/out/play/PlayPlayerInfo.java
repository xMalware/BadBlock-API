package fr.badblock.gameapi.packets.out.play;

import java.util.Set;
import java.util.UUID;

import org.bukkit.GameMode;

import com.mojang.authlib.properties.PropertyMap;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Packet envoyé pour update la tablist du joueur
 * 
 * @author LeLanN
 */
public interface PlayPlayerInfo extends BadblockOutPacket {
	public TabAction getAction();

	public PlayPlayerInfo setAction(TabAction action);
	
	public PlayPlayerInfo addPlayer(UUID uniqueId, PlayerInfo info);
	
	public PlayPlayerInfo removePlayer(UUID uniqueId);
	
	public PlayerInfo getInfos(UUID uniqueId);
	
	public Set<UUID> getPlayers();
	
	public enum TabAction {
		ADD_PLAYER(0),
		UPDATE_GAME_MODE(1),
		UPDATE_LATENCY(2),
		UPDATE_DISPLAY_NAME(3),
		REMOVE_PLAYER(4);
		
		@Getter
		private int id;
		
		TabAction(int id){
			this.id = id;
		}
		
		public static TabAction get(int id){
			for(TabAction action : values())
				if(action.id == id)
					return action;
			
			return null;
		}
	}
	
	@AllArgsConstructor
	public class PlayerInfo {
		public UUID 				  uniqueId;
		public String 				  name;
		public PropertyMap		      profile;
		public GameMode 			  gamemode;
		public int 				      ping;
		public String 			      displayName;
	}
}
