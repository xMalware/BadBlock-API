package fr.badblock.gameapi.technologies;

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
