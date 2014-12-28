package net.lightcraftmc.fusebox.util;


import net.lightcraftmc.fusebox.Core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class ProxiedParty {
	private static ProxiedParty instance;

	public ProxiedParty() {
		instance = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(Core.getInstance(), "BungeeCord");
	}

	public static ProxiedParty getInstance() {
		return instance;
	}

	public void requestPartyList(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:list");
		out.writeUTF(player.getName());
		player.sendPluginMessage(Core.getInstance(), "BungeeCord", out.toByteArray());
	}

	public void requestPartyHost(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:host");
		out.writeUTF(player.getName());
		player.sendPluginMessage(Core.getInstance(), "BungeeCord", out.toByteArray());
	}

	public void requestPartySize(Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("party:size");
		out.writeUTF(player.getName());
		player.sendPluginMessage(Core.getInstance(), "BungeeCord", out.toByteArray());
	}

}
