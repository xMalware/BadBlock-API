package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

public interface PlayScoreboardObjective extends BadblockOutPacket {
	public enum ObjectiveMode {
		CREATE((byte) 0), REMOVE((byte) 1), UPDATE((byte) 2);

		public static ObjectiveMode getByValue(byte value) {
			for (ObjectiveMode c : values())
				if (c.getData() == value)
					return c;
			return null;
		}

		@Getter
		private byte data;

		ObjectiveMode(byte data) {
			this.data = data;
		}
	}

	public enum ObjectiveType {
		INTEGER, HEARTS;
	}

	public String getDisplayName();

	public ObjectiveMode getMode();

	public String getObjectiveName();

	public ObjectiveType getObjectiveType();

	public PlayScoreboardObjective setDisplayName(String displayName);

	public PlayScoreboardObjective setMode(ObjectiveMode mode);

	public PlayScoreboardObjective setObjectiveName(String objectiveName);

	public PlayScoreboardObjective setObjectiveType(ObjectiveType type);
}
