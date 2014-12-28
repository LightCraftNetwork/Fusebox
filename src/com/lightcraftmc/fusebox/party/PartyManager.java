package com.lightcraftmc.fusebox.party;

import java.util.HashSet;

import org.bukkit.OfflinePlayer;

public class PartyManager {

	private static PartyManager instance;

	private final HashSet<Party> parties;

	// Why does this suck?!
	public PartyManager() {
		parties = new HashSet<Party>();
		instance = this;
	}

	public static PartyManager getInstance() {
		return instance;
	}

	public boolean isInParty(OfflinePlayer player) {
		if (parties.size() == 0)
			return false;
		for (Party p : parties) {
			if (p.hasPlayer(player))
				return true;
		}
		return false;
	}

	public void addParty(Party party) {
		this.parties.add(party);
	}

	public Party getByName(String name) {
		if (parties.size() == 0)
			return null;
		for (Party p : parties) {
			if (p.name.equals(name))
				return p;
		}
		return null;
	}

	public Party getByPlayer(OfflinePlayer player) {
		if (parties.size() == 0)
			return null;
		for (Party p : parties) {
			if (p.hasPlayer(player))
				return p;
		}
		return null;
	}

	public void deleteParty(Party party) {
		party.wipeData();
		parties.remove(party);
	}
}
