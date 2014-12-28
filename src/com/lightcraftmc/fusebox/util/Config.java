package com.lightcraftmc.fusebox.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.lightcraftmc.fusebox.Core;

public class Config {

	private final File saveDirectory, saveFile;
	private YamlConfiguration config;

	private static Config instance;

	private Config() {
		this.saveDirectory = new File(Core.getInstance().getDataFolder(), "Mounts");
		this.saveDirectory.mkdirs();
		this.saveFile = new File(saveDirectory, "Players");
		if (!saveFile.exists()) 
			try {
				saveFile.createNewFile();
			} catch (Exception exc) {
			}
		config = YamlConfiguration.loadConfiguration(saveFile);
	}

	public static Config getInstance() {
		if (instance == null)
			instance = new Config();
		return instance;
	}

	public void unlockMount(OfflinePlayer player, String mount) {
		final String name = Base64Coder.encodeString(player.getUniqueId()
				.toString());
		String prior = config.getString(name);
		String sb = "";
		if (prior != null)
			sb += (prior + "@");
		String s = Base64Coder.encodeString(mount);
		config.set(name, sb + s);
		save();
	}

	private void save() {

		try {
			config.save(saveFile);
		} catch (IOException e) {
		}
	}

	public String[] getUnlockedMounts(OfflinePlayer player) {
		String key = Base64Coder.encodeString(player.getUniqueId().toString());
		if (config.getString(key) == null)
			return new String[0];
		String[] temp = config.getString(key).split("@");
		String[] ret = new String[temp.length];
		for (int i = 0; i < temp.length; i++)
			ret[i] = Base64Coder.decodeString(temp[i]);
		save();
		return ret;
	}

	public void lockMount(OfflinePlayer player, String mount) {
		String key = Base64Coder.encodeString(player.getUniqueId().toString());
		if (config.getString(key) == null)
			return;
		String[] temp = config.getString(key).split("@");
		String[] ret = new String[temp.length];
		for (int i = 0; i < temp.length; i++)
			ret[i] = Base64Coder.decodeString(temp[i]);
		List<String> list = Arrays.asList(ret);
		list.remove(mount);
		String s = "";
		for (int i = 0; i < list.size(); i++)
			s += ("@" + Base64Coder.encodeString(list.get(i)));
		config.set(key, s.substring(0, s.length() - 1));
		save();
	}

}
