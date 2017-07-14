package fr.badblock.gameapi.packets;

public enum EnumItemSlot {

	MAINHAND(EnumItemSlot.Function.HAND, 0, 0, "mainhand"), OFFHAND(EnumItemSlot.Function.HAND, 1, 5, "offhand"), FEET(EnumItemSlot.Function.ARMOR, 0, 1, "feet"), LEGS(EnumItemSlot.Function.ARMOR, 1, 2, "legs"), CHEST(EnumItemSlot.Function.ARMOR, 2, 3, "chest"), HEAD(EnumItemSlot.Function.ARMOR, 3, 4, "head");

	private final EnumItemSlot.Function g = null;

	private final int h = 0;

	private final int i = 0;

	private final String j = null;

	EnumItemSlot(EnumItemSlot.Function enumitemslot_function, int i, int j, String s) {
	}

	public EnumItemSlot.Function a() {
		return g;
	}

	public int b() {
		return h;
	}

	public int c() {
		return i;
	}

	public String d() {
		return j;
	}

	public static EnumItemSlot a(String s) {
		for (EnumItemSlot localEnumItemSlot : values()) {
			if (localEnumItemSlot.d().equals(s)) {
				return localEnumItemSlot;
			}
		}
		throw new IllegalArgumentException("Invalid slot '" + s + "'");
	}

	public static enum Function {

		HAND, ARMOR;

		private Function() {
		}
	}
}