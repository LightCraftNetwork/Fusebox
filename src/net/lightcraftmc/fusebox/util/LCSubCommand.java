package net.lightcraftmc.fusebox.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.CommandSender;

public abstract class LCSubCommand {

	private final String key;
	private final String[] aliases;

	public LCSubCommand(String key, String... aliases) {
		this.key = key;
		this.aliases = aliases;
	}

	protected boolean playerOnly() {
		return false;
	}

	public String getKey() {
		return key;
	}

	public String[] getAliases() {
		return aliases;
	}

	public abstract void onCommand(CommandSender sender, String[] args);

	public String[] dropArgument(String... input) {
		if (input.length <= 1)
			return new String[0];
		ArrayList<String> a = new ArrayList<String>(Arrays.asList(input));
		a.remove(0);
		return a.toArray(new String[a.size()]);
	}
}
