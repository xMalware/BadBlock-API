package fr.badblock.gameapi.utils.general;

public interface Callback<T> {
	public void done(T result, Throwable error);
}
