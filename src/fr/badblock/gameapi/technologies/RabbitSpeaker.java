package fr.badblock.gameapi.technologies;

import java.util.UUID;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.game.GameState;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.general.Callback;

/**
 * Classe permettant de communiquer de manière simple avec RabbitMQ.
 * @author xMalware
 */
public interface RabbitSpeaker {

	public void sendAsyncUTF8Message(String queueName, String content, long ttl, boolean debug);
	
	public void sendAsyncUTF8Publisher(String queueName, String content, long ttl, boolean debug);

	public void sendSyncUTF8Message(String queueName, String content, long ttl, boolean debug);
	
	public void sendSyncUTF8Publisher(String queueName, String content, long ttl, boolean debug);
	
}
