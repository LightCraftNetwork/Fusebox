package com.lightcraftmc.fusebox.commands;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lightcraftmc.fusebox.commonLibs.parties.PartyCommand;

public class LCCommand implements CommandExecutor {

	private static LCCommand instance;
	private ArrayList<LCSubCommand> commands;

	public LCCommand() {
		commands = new ArrayList<LCSubCommand>(Arrays.asList(new PartyCommand()));
		instance = this;
	}

	public static LCCommand getInstance() {
		return instance;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		LCSubCommand cmd = getByName(arg1.getName());
		if (cmd == null) {
			arg0.sendMessage("An error seems to have occured.");
			return false;
		}
		if (cmd.playerOnly() && arg0 instanceof Player == false) {
			arg0.sendMessage("This is a player-only command.");
			return false;
		}
		cmd.onCommand(arg0, arg3);
		return true;
	}

	private LCSubCommand getByName(String name) {
		for (LCSubCommand c : commands) {
			if (c.getKey().equalsIgnoreCase(name))
				return c;
		}
		return null;
	}

}
