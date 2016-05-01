package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

public interface PlayScoreboardTeam extends BadblockOutPacket {
	public String getTeamName();
	
	public PlayScoreboardTeam setTeamName(String teamName);
	
	public TeamMode getMode();
	
	public PlayScoreboardTeam setMode(TeamMode mode);

	public String getDisplayName();
	
	public PlayScoreboardTeam setDisplayName(String displayName);
	
	public String getPrefix();
	
	public PlayScoreboardTeam setPrefix(String prefix);
	
	public String getSuffix();
	
	public PlayScoreboardTeam setSuffix(String suffix);
	
	public TeamFriendlyFire getFriendlyFire();
	
	public PlayScoreboardTeam setFriendlyFire(TeamFriendlyFire friendlyFire);

	public TeamNameTag getNametagVisibility();
	
	public PlayScoreboardTeam setNametagVisibility(TeamNameTag nameTag);
	
	public byte getColor();
	
	public PlayScoreboardTeam setColor(byte color);
	
	public String[] getPlayers();
	
	public PlayScoreboardTeam setPlayers(String[] players);
	
	public enum TeamMode {
		CREATE((byte) 0),
		REMOVE((byte) 1),
		UPDATE((byte) 2),
		ADD_PLAYERS((byte) 3),
		REMOVE_PLAYERS((byte) 4);
		
		@Getter
		private byte data;
		
		TeamMode(byte data){
			this.data = data;
		}
		
		public static TeamMode getByValue(byte value){
			for(TeamMode c : values())
				if(c.getData() == value)
					return c;
			return null;
		}
	}
	
	public enum TeamFriendlyFire {
		OFF(0),
		FRIENDLYFIRE(0 | 0x1),
		SEE_INVISIBLE(0 | 0x2),
		BOTH(0 | 0x1 | 0x2);
		
		@Getter
		private int data;
		
		TeamFriendlyFire(int data){
			this.data = data;
		}
		
		public static TeamFriendlyFire getByValue(byte value){
			for(TeamFriendlyFire c : values())
				if(c.getData() == value)
					return c;
			return null;
		}
	}
	
	public enum TeamNameTag {
		always,
		hideForOtherTeams,
		hideForOwnTeam,
		never;
	}
}