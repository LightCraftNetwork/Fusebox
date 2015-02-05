package com.lightcraftmc.fusebox.minigames.gamedata;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;

import com.lightcraftmc.fusebox.commonLibs.DataSet;

public class GameData {

	private static ArrayList<DataSet> mapData = new ArrayList<DataSet>();
	private static String str = "";
	
	public static void init(){
		StringWriter writer = new StringWriter();
		try {
			IOUtils.copy(new FileInputStream(new File(Bukkit.getWorlds().get(0).getWorldFolder().getPath().replace(".\\", "") + "/world-data.txt")), writer, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String theText = writer.toString();
		str = theText;
		
		for(String s : theText.split(";")){
			DataSet d2 = new DataSet(s.split(":")[0].toLowerCase(), s.split(":")[1]);
			mapData.add(d2);
		}
	}
	
	public static void save(){
		try {
			BufferedWriter writer =  new BufferedWriter (new FileWriter(Bukkit.getWorlds().get(0).getWorldFolder() + "/world-data.txt"));
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getData(String key){
		for(DataSet data : mapData){
			if(data.getKey().equals(key)){
				return data.getValue();
			}
		}
		return null;
	}
	
	public static String getRawData(){
		return str;
	}
	
	public static String addRawData(String s){
		str = str + s;
		return str;
	}
	
	public static String setRawData(String s){
		str = s;
		return str;
	}
	
}
