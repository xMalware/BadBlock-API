package fr.badblock.gameapi.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.BadblockPlayer.BadblockMode;
import fr.badblock.gameapi.players.BadblockPlayer.GamePermission;
import fr.badblock.gameapi.run.RunType;
import fr.badblock.gameapi.utils.BukkitUtils;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import fr.badblock.gameapi.utils.i18n.messages.CommandMessages;
import fr.badblock.gameapi.utils.reflection.Reflector;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

/**
 * Classe abstraite permettantt de simplifier l'utilisation des commandes. Par
 * exemple, elles ne devront pas être situées dans le plugin.yml
 * 
 * @author LeLanN
 */
@Getter
public abstract class AbstractCommand implements TabExecutor {
	private static final int MAX_TAB_RETURN = 20;

	private String command;
	private TranslatableString usage;
	private String lobbyPermission;
	private String miniGameSpectatorPermission;
	private String miniGamePermission;

	private String[] aliases;

	private boolean allowConsole = true;

	/**
	 * Crée une nouvelle commande
	 * 
	 * @param command Le nom de la commande
	 * @param usage Le message d'erreur si la commande est mal utilisée
	 * @param permission La permission nécessaire
	 * @param aliases Les aliases éventuels de la commande
	 */
	public AbstractCommand(String command, TranslatableString usage, GamePermission perm, String... aliases) {
		this(command, usage, perm.getPermission(), aliases);
	}
	
	/**
	 * Crée une nouvelle commande
	 * 
	 * @param command Le nom de la commande
	 * @param usage Le message d'erreur si la commande est mal utilisée
	 * @param permission La permission nécessaire
	 * @param aliases Les aliases éventuels de la commande
	 */
	public AbstractCommand(String command, TranslatableString usage, String perm, String... aliases) {
		this(command, usage, perm, perm, perm, aliases);
	}
	
	/**
	 * Crée une nouvelle commande
	 * 
	 * @param command
	 *            Le nom de la commande
	 * @param usage
	 *            Le message d'erreur si la commande est mal utilisée
	 * @param lobbyPermission La permission nécessaire au lobby
	 * @param miniGameSpectatorPermission La permission nécessaire en jeu (spectateur)
	 * @param miniGamePermission La permisison nécessaire en jeu (joueur)
	 * @param aliases
	 *            Les aliases éventuels de la commande
	 */
	public AbstractCommand(String command, TranslatableString usage, GamePermission lobbyPermission, GamePermission miniGameSpectatorPermission, GamePermission miniGamePermission, String... aliases) {
		this(command, usage, lobbyPermission.getPermission(), miniGameSpectatorPermission.getPermission(), miniGamePermission.getPermission(), aliases);
	}

	/**
	 * Crée une nouvelle commande
	 * 
	 * @param command
	 *            Le nom de la commande
	 * @param usage
	 *            Le message d'erreur si la commande est mal utilisée
	 * @param lobbyPermission La permission nécessaire au lobby
	 * @param miniGameSpectatorPermission La permission nécessaire en jeu (spectateur)
	 * @param miniGamePermission La permisison nécessaire en jeu (joueur)
	 * @param aliases
	 *            Les aliases éventuels de la commande
	 */
	public AbstractCommand(String command, TranslatableString usage, String lobbyPermission, String miniGameSpectatorPermission, String miniGamePermission, String... aliases) {
		this.command = command;
		this.usage = usage;
		this.lobbyPermission = lobbyPermission;
		this.miniGameSpectatorPermission = miniGameSpectatorPermission;
		this.miniGamePermission = miniGamePermission;
		this.aliases = aliases;

		ReflectCommand result = new ReflectCommand(command);

		result.setAliases(Arrays.asList(aliases));
		result.setExecutor(this);

		getCommandMap().register("gameapi", result);
	}

	/**
	 * Permet de dire si la console peut utiliser la commande. Par défaut à
	 * false.
	 * 
	 * @param console
	 *            Un boolean
	 */
	public void allowConsole(boolean console) {
		this.allowConsole = console;
	}

	/**
	 * Permet d'exécuter la commande
	 * 
	 * @param sender
	 *            Le sender
	 * @param args
	 *            Les arguments
	 * @return Si la commande est bien utilisée
	 */
	public abstract boolean executeCommand(CommandSender sender, String[] args);

	@Override
	public final boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!allowConsole && !(sender instanceof Player))
			sender.sendMessage(ChatColor.RED + "This command is only for players.");
		else if (hasPermission(sender)) {
			if (!executeCommand(sender, args) && usage != null) {
				sendUsage(sender);
			}
		}else CommandMessages.noPermission().send(sender);
		return true;
	}
	
	public boolean hasPermission(CommandSender sender){
		boolean spectator = sender instanceof Player && ((BadblockPlayer) sender).getBadblockMode() == BadblockMode.SPECTATOR;
		String permission = null;
		
		if(GameAPI.getAPI().getRunType() == RunType.GAME)
			permission = spectator ? miniGameSpectatorPermission : miniGamePermission;
		else permission = lobbyPermission;
		
		return permission == null || permission.isEmpty() || sender.hasPermission(permission);
	}

	public void sendUsage(CommandSender sender){
		usage.send(sender);
	}

	protected void sendTranslatedMessage(CommandSender sender, String key, Object... args) {
		GameAPI.i18n().sendMessage(sender, key, args);
	}

	private final CommandMap getCommandMap() {
		try {
			return (CommandMap) new Reflector(Bukkit.getServer()).getFieldValue("commandMap");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retourne une liste d'arguments qui seront triés ensuite
	 * @param sender Le sender
	 * @param args Les arguments
	 * @return La liste
	 */
	public Collection<String> doTab(CommandSender sender, String[] args){
		return Bukkit.getOnlinePlayers().stream().map(player -> { return player.getName(); }).collect(Collectors.toList());
	}

	public String[] changeArgs(CommandSender sender, String[] args){
		return args;
	}

	@Override
	public final List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (!allowConsole && !(sender instanceof Player))
			sender.sendMessage(ChatColor.RED + "This command is only for players.");
		else if ((GameAPI.getAPI().getRunType().equals(RunType.GAME) && miniGameSpectatorPermission != null && 
				!miniGameSpectatorPermission.isEmpty() && sender.hasPermission(miniGameSpectatorPermission) && 
				(((sender instanceof Player) && BukkitUtils.getPlayer(sender.getName()) != null && 
				BukkitUtils.getPlayer(sender.getName()).getBadblockMode().equals(BadblockMode.SPECTATOR)))) || (!(sender instanceof Player)) ||
				(GameAPI.getAPI().getRunType().equals(RunType.GAME) && miniGamePermission != null && !miniGamePermission.isEmpty() && 
				sender.hasPermission(miniGamePermission)) ||
				(GameAPI.getAPI().getRunType().equals(RunType.LOBBY) && lobbyPermission != null && !lobbyPermission.isEmpty() && sender.hasPermission(lobbyPermission))) {
			args = changeArgs(sender, args);
			String searched = (args.length == 0 ? "" : args[args.length - 1]).toLowerCase();

			return doTab(sender, args).stream().filter(arg -> {
				return searched.isEmpty() || arg.regionMatches(true, 0, searched, 0, searched.length());
			}).limit(MAX_TAB_RETURN).collect(Collectors.toList());
		}else CommandMessages.noPermission().send(sender);
		return new ArrayList<>();
	}

	private final class ReflectCommand extends Command {
		private AbstractCommand exe = null;

		protected ReflectCommand(String command) {
			super(command);
		}

		public void setExecutor(AbstractCommand exe) {
			this.exe = exe;
		}

		@Override
		public boolean execute(CommandSender sender, String commandLabel, String[] args) {
			if (exe != null) {
				return exe.onCommand(sender, this, commandLabel, args);
			}
			return false;
		}

		@Override
		public List<String> tabComplete(CommandSender sender, String alias, String[] args){
			return exe.onTabComplete(sender, this, alias, args);
		}
	}
}