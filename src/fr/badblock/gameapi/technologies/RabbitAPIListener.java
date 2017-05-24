package fr.badblock.gameapi.technologies;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter public abstract class RabbitAPIListener {

	private String 			   queue;
	private RabbitListenerType type;
	private long			   ttl;
	private boolean			   debug;
	
	public abstract void onPacketReceiving(String body);
	
}
