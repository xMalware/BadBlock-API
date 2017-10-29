package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayTitle extends BadblockOutPacket {
	public enum Action {
		/**
		 * Change le message du haut
		 */
		TITLE(),
		/**
		 * Change le message du bas
		 */
		SUBTITLE(),
		/**
		 * Change les timings (temps d'affichages, affichera le title ŕ l'écran)
		 */
		TIMES(),
		/**
		 * Fait disparaître le title de l'écran (utiliser TIMES pour le
		 * remettre)
		 */
		CLEAR(),
		/**
		 * Réinitialise tout le packet
		 */
		RESET();
	}

	public Action getAction();

	public String getContent();

	public long getFadeIn();

	public long getFadeOut();

	public long getStay();

	public PlayTitle setAction(Action action);

	public PlayTitle setContent(String content);

	public PlayTitle setFadeIn(long time);

	public PlayTitle setFadeOut(long time);

	public PlayTitle setStay(long time);
}
