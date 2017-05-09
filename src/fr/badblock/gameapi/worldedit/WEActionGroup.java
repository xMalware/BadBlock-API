package fr.badblock.gameapi.worldedit;

import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Queues;

import fr.badblock.gameapi.GameAPI;
import lombok.Getter;

/**
 * Représente un groupe d'action WorldEdit sous la forme d'une action unique
 * @author LeLanN
 */
public class WEActionGroup implements WEAction {
	private final Queue<WEAction> pendingActions;
	private WEAction currentAction;
	@Getter
	private boolean closed = false;
	
	public WEActionGroup()
	{
		pendingActions = Queues.newLinkedBlockingDeque();
	}
	
	public WEActionGroup(Iterable<WEAction> actions)
	{
		pendingActions = Queues.newLinkedBlockingDeque();
		
		for(WEAction action : actions)
			pendingActions.add(action);
	}
	
	public WEActionGroup(WEAction... actions)
	{
		pendingActions = Queues.newLinkedBlockingDeque();
		addActions(actions);
	}
	
	/**
	 * Ajoute des actions à la liste d'attente
	 * @param actions Les actions
	 */
	public void addActions(Iterable<WEAction> actions)
	{
		if(closed) return;
		
		for(WEAction action : actions)
			pendingActions.add(action);
	}
	
	/**
	 * Ajoute des actions à la liste d'attente
	 * @param actions Les actions
	 */
	public void addActions(WEAction... actions)
	{
		if(closed) return;
		
		for(WEAction action : actions)
			pendingActions.add(action);
	}
	
	/**
	 * Récupère l'action actuelle
	 * @return Une action
	 */
	public WEAction getCurrentAction()
	{
		if(currentAction == null || !currentAction.hasNext())
		{
			stopCurrentAction();
			
			currentAction = (pendingActions.size() == 0 ? null : pendingActions.poll());
		
			if(currentAction != null)
				currentAction.notifyStart();
		}
	
		return currentAction;
	}
	
	/**
	 * Force l'arrêt de l'action actuelle
	 */
	public void stopCurrentAction()
	{
		if(currentAction != null)
		{
			currentAction.notifyEnd();
			
			if(currentAction.getApplicant() != null)
				GameAPI.i18n().sendMessage(currentAction.getApplicant(), "commands.worldedit.actionfinished");
		}
		
		currentAction = null;
	}

	/**
	 * Récupère toutes les actions en attente
	 * @return Les actions en attente
	 */
	public Set<WEAction> getPendingActions()
	{
		return pendingActions.stream().collect(Collectors.toSet());
	}
	
	/**
	 * Supprime toutes les actions en attente de la file
	 */
	public void clearPendingActions()
	{
		pendingActions.clear();
	}

	
	@Override
	public long getTotalIterationCount() {
		WEAction action = getCurrentAction();
		return action == null ? 0 : action.getTotalIterationCount();
	}

	@Override
	public long getIterationCount() {
		WEAction action = getCurrentAction();
		return action == null ? 0 : action.getIterationCount();
	}
	
	@Override
	public CommandSender getApplicant() {
		WEAction action = getCurrentAction();
		return action == null ? null : action.getApplicant();
	}

	@Override
	public boolean hasNext() {
		WEAction action = getCurrentAction();
		return action == null ? false : action.hasNext();
	}

	@Override
	public void next() {
		WEAction action = getCurrentAction();

		if(action != null)
			action.next();
	}

	@Override public void notifyStart() {	}

	@Override
	public void notifyEnd() {
		closed = true;
		
		currentAction.notifyEnd();
		
		while(pendingActions.size() != 0)
			pendingActions.peek().notifyEnd();
	}
}