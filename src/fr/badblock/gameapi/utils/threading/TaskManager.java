package fr.badblock.gameapi.utils.threading;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import fr.badblock.gameapi.GameAPI;

/**
 * Gestion des tasks
 * 
 * @author xMalware
 */
public class TaskManager {

	public static HashMap<String, Double> tasksTime = new HashMap<>();
	public static HashMap<String, Integer> taskList = new HashMap<>();
	public static BukkitScheduler scheduler = Bukkit.getScheduler();
	static Plugin plugin = GameAPI.getAPI();
	private static String currentThreadName = Thread.currentThread().getName();

	/**
	 * Rajout une task dans la list
	 * 
	 * @param name
	 * @param id
	 */
	public static void addTask(String name, int id) {
		taskList.put(name, id);
	}

	// Cancel all task
	public static void cancelAllTask() {
		for (int taskId : taskList.values()) {
			scheduler.cancelTask(taskId);
		}
		taskList.clear();
	}

	// Annule une tĂ˘che par l'ID
	public static void cancelTaskById(int id) {
		scheduler.cancelTask(id);
	}

	// Cancel de la task by name
	public static boolean cancelTaskByName(String taskName) {
		if (taskExist(taskName)) {
			int taskId = getTaskId(taskName);
			taskList.remove(taskName);
			scheduler.cancelTask(taskId);
			return true;
		}
		return false;
	}

	// RĂŠcupĂŠration de l'id
	public static int getTaskId(String taskName) {
		if (taskExist(taskName)) {
			return taskList.get(taskName);
		}
		return 0;
	}

	/**
	 * CrĂŠer un nom de tĂ˘che unique basĂŠ sur un nom de tĂ˘che
	 * 
	 * @param string
	 * @return
	 */
	public static String getTaskName(String string) {
		String taskName = string + "_" + new Random().nextInt(99999);
		while (taskExist(taskName)) {
			taskName = string + "_" + new Random().nextInt(99999);
		}
		return taskName;
	}

	/**
	 * Tente de rĂŠcupĂŠrer le nom de la task par l'id si elle existe encore et
	 * qu'elle a ĂŠtĂŠ dĂŠclarĂŠ dans ce manager
	 * 
	 * @param id
	 *            de la task
	 * @return null si non trouvĂŠ
	 */
	public static String getTaskNameById(int id) {
		for (Entry<String, Integer> entry : taskList.entrySet()) {
			if (entry.getValue() == id)
				return entry.getKey();
		}
		return null;
	}

	/**
	 * RĂŠcupĂŠrer l'utilisation de la tĂ˘che en millisecondes
	 * 
	 * @param task
	 * @return
	 */
	public static double getUsageTask(BukkitTask task) {
		String taskName = getTaskNameById(task.getTaskId());
		return !tasksTime.containsKey(taskName) ? -1D : tasksTime.get(taskName);
	}

	/**
	 * RĂŠcupĂŠrer l'utilisation de toutes les tĂ˘ches en millisecondes
	 * 
	 * @return
	 */
	public static Map<String, Double> getUsageTasks() {
		return tasksTime;
	}

	/**
	 * Charger le runnable
	 * 
	 * @param name
	 * @param runnable
	 * @return
	 */
	private static Runnable loadRunnable(final String name, final Runnable runnable) {
		return new Runnable() {
			@Override
			public void run() {
				long timeExecute = System.nanoTime();
				runnable.run();
				boolean sync = currentThreadName.equals(Thread.currentThread().getName());
				if (sync) {
					double time = (System.nanoTime() - timeExecute) / 1_000_000D;
					tasksTime.put(name, time);
				}
			}
		};
	}

	public static void removeTaskByName(String taskName) {
		taskList.remove(taskName);
	}

	// Run async task later
	public static BukkitTask runAsyncTaskLater(Runnable runnable, int tick) {
		return scheduler.runTaskLaterAsynchronously(plugin,
				loadRunnable("laterTask_" + tick + "_" + (new Random().nextInt(655555)), runnable), tick);
	}

	/**
	 * CrĂŠer et enregistre une task, se retire de la liste toute seule Ă 
	 * l'expiration, permet de l'annuler dans un plugin et ĂŠviter les mĂŠmory
	 * leaks Tourne en async
	 * 
	 * @param taskName
	 * @param task
	 * @param duration
	 */
	public static BukkitTask runAsyncTaskLater(final String taskName, Runnable task, int duration) {
		BukkitTask bukkitTask = scheduler.runTaskLaterAsynchronously(plugin, loadRunnable(taskName, task), duration);
		final int id = bukkitTask.getTaskId();
		TaskManager.addTask(taskName, id);
		runAsyncTaskLater(new Runnable() {
			@Override
			public void run() {
				// Toujours la mĂŞme task ID pour ĂŠviter la suppression de task
				// renouvelĂŠes
				if (taskList.get(taskName) != null && taskList.get(taskName) == id)
					taskList.remove(taskName);
			}
		}, duration);
		return bukkitTask;
	}

	// Run task now
	public static BukkitTask runTask(Runnable runnable) {
		return scheduler.runTask(plugin, loadRunnable("runTask_" + (new Random().nextInt(6555555)), runnable));
	}

	/**
	 * Run async task
	 * 
	 * @param runnable
	 * @return
	 */
	public static BukkitTask runTaskAsync(Runnable runnable) {
		return scheduler.runTaskAsynchronously(plugin,
				loadRunnable("runTask_" + (new Random().nextInt(6555555)), runnable));
	}

	/**
	 * Run task later
	 * 
	 * @param runnable
	 * @param tick
	 * @return
	 */
	public static BukkitTask runTaskLater(Runnable runnable, int tick) {
		return scheduler.runTaskLater(plugin,
				loadRunnable("laterTask_" + tick + "_" + (new Random().nextInt(655555)), runnable), tick);
	}

	/**
	 * CrĂŠer et enregistre une task, se retire de la liste toute seule Ă 
	 * l'expiration, permet de l'annuler dans un plugin et ĂŠviter les mĂŠmory
	 * leaks
	 * 
	 * @param taskName
	 * @param task
	 * @param duration
	 */
	public static BukkitTask runTaskLater(final String taskName, Runnable task, int duration) {
		BukkitTask bukkitTask = scheduler.runTaskLater(plugin, loadRunnable(taskName, task), duration);
		final int id = bukkitTask.getTaskId();
		TaskManager.addTask(taskName, id);
		runTaskLater(new Runnable() {
			@Override
			public void run() {
				// Toujours la mĂŞme task ID pour ĂŠviter la suppression de task
				// renouvelĂŠes
				if (taskList.get(taskName) != null && taskList.get(taskName) == id)
					taskList.remove(taskName);
			}
		}, duration);
		return bukkitTask;
	}

	/**
	 * Ajoute une tĂ˘che rĂŠpĂŠtitive en async Annule la prĂŠcĂŠdante du meme
	 * nom si existe.
	 * 
	 * @param runnable
	 * @param delay
	 * @param refresh
	 * @return
	 */
	public static BukkitTask scheduleAsyncRepeatingTask(String taskName, Runnable runnable, int delay, int refresh) {
		cancelTaskByName(taskName);
		BukkitTask task = scheduler.runTaskTimerAsynchronously(plugin, loadRunnable(taskName, runnable), delay,
				refresh);
		taskList.put(taskName, task.getTaskId());
		return task;
	}

	/**
	 * Ajoute une tĂ˘che rĂŠpĂŠtitive Annule la prĂŠcĂŠdante du meme nom si
	 * existe.
	 * 
	 * @param runnable
	 * @param delay
	 * @param refresh
	 * @return
	 */
	public static BukkitTask scheduleSyncRepeatingTask(String taskName, Runnable runnable, int delay, int refresh) {
		cancelTaskByName(taskName);
		BukkitTask task = scheduler.runTaskTimer(plugin, loadRunnable(taskName, runnable), delay, refresh);
		taskList.put(taskName, task.getTaskId());
		return task;
	}

	// La tĂ˘che existe ?
	public static boolean taskExist(String taskName) {
		if (taskList.containsKey(taskName)) {
			return true;
		}
		return false;
	}

	/**
	 * RecupĂ¨re la task
	 * 
	 * @param taskName
	 * @return
	 */
	public BukkitTask getTask(String taskName) {
		BukkitTask task = null;
		int id = getTaskId(taskName);
		if (id > 0) {
			for (BukkitTask pendingTask : scheduler.getPendingTasks()) {
				if (pendingTask.getTaskId() == id)
					return task;
			}
		}
		return null;
	}

}
