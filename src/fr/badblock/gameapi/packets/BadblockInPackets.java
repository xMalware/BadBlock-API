package fr.badblock.gameapi.packets;

import fr.badblock.gameapi.packets.in.Handshake;
import fr.badblock.gameapi.packets.in.LoginStart;
import fr.badblock.gameapi.packets.in.StatusPing;
import fr.badblock.gameapi.packets.in.StatusRequest;
import fr.badblock.gameapi.packets.in.play.PlayInAbilities;
import fr.badblock.gameapi.packets.in.play.PlayInArmAnimation;
import fr.badblock.gameapi.packets.in.play.PlayInBlockDig;
import fr.badblock.gameapi.packets.in.play.PlayInBlockPlace;
import fr.badblock.gameapi.packets.in.play.PlayInChat;
import fr.badblock.gameapi.packets.in.play.PlayInClientCommand;
import fr.badblock.gameapi.packets.in.play.PlayInCloseWindow;
import fr.badblock.gameapi.packets.in.play.PlayInCustomPayload;
import fr.badblock.gameapi.packets.in.play.PlayInEnchantItem;
import fr.badblock.gameapi.packets.in.play.PlayInEntityAction;
import fr.badblock.gameapi.packets.in.play.PlayInHeldItemSlot;
import fr.badblock.gameapi.packets.in.play.PlayInKeepAlive;
import fr.badblock.gameapi.packets.in.play.PlayInLook;
import fr.badblock.gameapi.packets.in.play.PlayInPosition;
import fr.badblock.gameapi.packets.in.play.PlayInPositionLook;
import fr.badblock.gameapi.packets.in.play.PlayInResourcePackStatus;
import fr.badblock.gameapi.packets.in.play.PlayInSetCreativeSlot;
import fr.badblock.gameapi.packets.in.play.PlayInSettings;
import fr.badblock.gameapi.packets.in.play.PlayInSpectate;
import fr.badblock.gameapi.packets.in.play.PlayInSteerVehicle;
import fr.badblock.gameapi.packets.in.play.PlayInTabComplete;
import fr.badblock.gameapi.packets.in.play.PlayInTransaction;
import fr.badblock.gameapi.packets.in.play.PlayInUpdateSign;
import fr.badblock.gameapi.packets.in.play.PlayInUseEntity;
import fr.badblock.gameapi.packets.in.play.PlayInWindowClick;
import lombok.Getter;

@Getter public enum BadblockInPackets {
	HANDSHAKE(Handshake.class),
	LOGIN_START(LoginStart.class),
	PLAY_ABILITIES(PlayInAbilities.class),
	PLAY_ARM_ANIMATION(PlayInArmAnimation.class),
	PLAY_BLOCK_DIG(PlayInBlockDig.class),
	PLAY_BLOCK_PLACE(PlayInBlockPlace.class),
	PLAY_CHAT(PlayInChat.class),
	PLAY_CLIENT_COMMAND(PlayInClientCommand.class),
	PLAY_CLOSE_WINDOW(PlayInCloseWindow.class),
	PLAY_CUSTOM_PAYLOAD(PlayInCustomPayload.class),
	PLAY_ENCHANT_ITEM(PlayInEnchantItem.class),
	PLAY_ENTITY_ACTION(PlayInEntityAction.class),
	PLAY_HELD_ITEM_SLOT(PlayInHeldItemSlot.class),
	PLAY_KEEPALIVE(PlayInKeepAlive.class),
	PLAY_LOOK(PlayInLook.class),
	PLAY_POSITION(PlayInPosition.class),
	PLAY_POSITION_LOOK(PlayInPositionLook.class),
	PLAY_RESOURCEPACK_STATUS(PlayInResourcePackStatus.class),
	PLAY_SET_CREATIVE_SLOT(PlayInSetCreativeSlot.class),
	PLAY_SETTINGS(PlayInSettings.class),
	PLAY_SPECCTATE(PlayInSpectate.class),
	PLAY_STEER_VEHICLE(PlayInSteerVehicle.class),
	PLAY_TAB_COMPLETE(PlayInTabComplete.class),
	PLAY_TRANSACTION(PlayInTransaction.class),
	PLAY_UPDATE_SIGN(PlayInUpdateSign.class),
	PLAY_USE_ENTITY(PlayInUseEntity.class),
	PLAY_WINDOW_CLICK(PlayInWindowClick.class),
	STATUS_PING(StatusPing.class),
	STATUS_REQUEST(StatusRequest.class);
	
	private Class<? extends BadblockInPacket> clazz;
	
	BadblockInPackets(Class<? extends BadblockInPacket> clazz){
		this.clazz     = clazz;
	}
}
