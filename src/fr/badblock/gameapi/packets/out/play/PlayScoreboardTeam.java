package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

public interface PlayScoreboardTeam extends BadblockOutPacket {
	public enum CollisionRule {
		always, pushOtherTeams, pushOwnTeam, never;
	}

	public enum TeamFriendlyFire {
		OFF(0), FRIENDLYFIRE(0 | 0x1), SEE_INVISIBLE(0 | 0x2), BOTH(0 | 0x1 | 0x2);

		public static TeamFriendlyFire getByValue(byte value) {
			for (TeamFriendlyFire c : values())
				if (c.getData() == value)
					return c;
			return null;
		}

		@Getter
		private int data;

		TeamFriendlyFire(int data) {
			this.data = data;
		}
	}

	public enum TeamMode {
		CREATE((byte) 0), REMOVE((byte) 1), UPDATE((byte) 2), ADD_PLAYERS((byte) 3), REMOVE_PLAYERS((byte) 4);

		public static TeamMode getByValue(byte value) {
			for (TeamMode c : values())
				if (c.getData() == value)
					return c;
			return null;
		}

		@Getter
		private byte data;

		TeamMode(byte data) {
			this.data = data;
		}
	}

	public enum TeamNameTag {
		always, hideForOtherTeams, hideForOwnTeam, never;
	}

	public byte getColor();

	public String getDisplayName();

	public TeamFriendlyFire getFriendlyFire();

	public TeamMode getMode();

	public TeamNameTag getNametagVisibility();

	public String[] getPlayers();

	public String getPrefix();

	public String getSuffix();

	public String getTeamName();

	public PlayScoreboardTeam setColor(byte color);

	public PlayScoreboardTeam setDisplayName(String displayName);

	public PlayScoreboardTeam setFriendlyFire(TeamFriendlyFire friendlyFire);

	public PlayScoreboardTeam setMode(TeamMode mode);

	public PlayScoreboardTeam setNametagVisibility(TeamNameTag nameTag);

	public PlayScoreboardTeam setPlayers(String[] players);

	public PlayScoreboardTeam setPrefix(String prefix);

	public PlayScoreboardTeam setSuffix(String suffix);

	public PlayScoreboardTeam setTeamName(String teamName);
}