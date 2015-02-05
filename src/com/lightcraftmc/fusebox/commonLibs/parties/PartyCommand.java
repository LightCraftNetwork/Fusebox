package com.lightcraftmc.fusebox.commonLibs.parties;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lightcraftmc.fusebox.commands.LCSubCommand;

public class PartyCommand extends LCSubCommand {

	private enum Sub {
		create,
		join,
		delete,
		leave,
		kick,
		invite,
		open,
		closed;
	}

	public PartyCommand() {
		super("party");
	}

	@Override
	public boolean playerOnly() {
		return true;
	}

	private void sendUsage(Player p) {
		p.sendMessage(ChatColor.AQUA + "Usage: /Party <param>. The parameters are:");
		for (Sub s : Sub.values())
			p.sendMessage(ChatColor.AQUA + " - " + s.toString());
	}

	@Override
	public void onCommand(CommandSender sender, String[] args) {
		Player p = (Player) sender;
		if (args.length == 0) {
			sendUsage(p);
			return;
		}
		Sub s;
		try {
			s = Sub.valueOf(args[0]);
		} catch (Exception e) {
			sendUsage(p);
			return;
		}
		args = super.dropArgument(args);
		switch (s) {
		case create:
			if (args.length == 0) {
				p.sendMessage(ChatColor.AQUA + "Usage: /Party create <name>");
				return;
			}
			if (PartyManager.getInstance().isInParty(p)) {
				p.sendMessage(ChatColor.RED + "You are already in a party.");
				return;
			}
			createParty(args[0], p);
			addToParty(p, args[0]);
			p.sendMessage(ChatColor.AQUA + "Party created!");
			return;
		case join:
			if (args.length == 0) {
				p.sendMessage(ChatColor.AQUA + "Usage: /Party join <name>");
				return;
			}
			if (PartyManager.getInstance().isInParty(p)) {
				p.sendMessage(ChatColor.RED + "You are already in a party.");
				return;
			}
			if (!partyExists(args[0])) {
				p.sendMessage(ChatColor.RED + "A party by that name does not exist.");
				return;
			}
			{
				Party party = PartyManager.getInstance().getByName(args[0]);
				if (party.isLocked() && !party.isInvited(p)) {
					p.sendMessage(ChatColor.RED + "You have not been invited to this party. You must be invited to join.");
					return;
				}
				party.addToParty(p);
				p.sendMessage(ChatColor.AQUA + "Party joined!");
			}
			return;
		case delete:
			if (!PartyManager.getInstance().isInParty(p)) {
				p.sendMessage(ChatColor.RED + "You are not in a party.");
				return;
			}
			{
				Party party = PartyManager.getInstance().getByPlayer(p);
				if (!party.getHost().getUniqueId().equals(p.getUniqueId())) {
					p.sendMessage(ChatColor.RED + "You are not the party host.");
					return;
				}
				PartyManager.getInstance().deleteParty(party);
				p.sendMessage(ChatColor.AQUA + "Party deleted! D:");
			}
			return;
		case leave:
			if (!PartyManager.getInstance().isInParty(p)) {
				p.sendMessage(ChatColor.RED + "You are not in a party.");
				return;
			}
			if (PartyManager.getInstance().getByPlayer(p).getHost().getUniqueId().equals(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "You are the host of your party you must delete the party to leave.");
				return;
			}
			PartyManager.getInstance().getByPlayer(p).removePlayer(p);
			p.sendMessage(ChatColor.AQUA + "You left the party.");
			return;
		case kick:
			if (args.length == 0) {
				p.sendMessage(ChatColor.AQUA + "Usage: /Party kick <player>");
				return;
			}
			if (!PartyManager.getInstance().getByPlayer(p).getHost().getUniqueId().equals(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "You are not the host of this party!");
				return;
			}
			@SuppressWarnings("deprecation")
			OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
			PartyManager.getInstance().getByPlayer(p).removePlayer(target);
			p.sendMessage(ChatColor.AQUA + "You kicked " + args[0] + " from your party.");
			return;
		case open: {
			Party party = PartyManager.getInstance().getByPlayer(p);
			if (party == null) {
				p.sendMessage(ChatColor.RED + "You are not in a party.");
				return;
			}
			if (!party.getHost().getUniqueId().equals(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "You are not the party host.");
				return;
			}
			party.setLocked(false);
			p.sendMessage(ChatColor.AQUA + "Party has been opened.");
		}
			return;
		case closed: {
			Party party = PartyManager.getInstance().getByPlayer(p);
			if (party == null) {
				p.sendMessage(ChatColor.RED + "You are not in a party.");
				return;
			}
			if (!party.getHost().getUniqueId().equals(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "You are not the party host.");
				return;
			}
			party.setLocked(true);
			p.sendMessage(ChatColor.AQUA + "Party has been closed.");
		}
			return;
		case invite: {
			if (args.length == 0) {
				p.sendMessage(ChatColor.AQUA + "Usage: /Party invite <player>");
				return;
			}
			if (PartyManager.getInstance().getByPlayer(p) == null) {
				p.sendMessage(ChatColor.RED + "You are not in a party.");
				return;
			}
			if (!PartyManager.getInstance().getByPlayer(p).getHost().getUniqueId().equals(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + "You are not the host of this party!");
				return;
			}
			@SuppressWarnings("deprecation")
			OfflinePlayer invitee = Bukkit.getOfflinePlayer(args[0]);
			PartyManager.getInstance().getByPlayer(p).setInvited(invitee);
			p.sendMessage(ChatColor.AQUA + "You invited " + args[0] + " to your party.");
			return;
		}
		default:
			sendUsage(p);
			return;
		}
	}

	private void createParty(String name, OfflinePlayer player) {
		PartyManager.getInstance().addParty(new Party(name, player));
	}

	private boolean partyExists(String name) {
		return PartyManager.getInstance().getByName(name) != null;
	}

	private void addToParty(OfflinePlayer player, String key) {
		Party party = PartyManager.getInstance().getByName(key);
		if (party == null)
			return;
		party.addToParty(player);
	}
}
