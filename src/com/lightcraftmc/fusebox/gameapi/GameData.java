package com.lightcraftmc.fusebox.gameapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;

import com.lightcraftmc.fusebox.utils.DataSet;

public class GameData {

	private static ArrayList<DataSet> mapData = new ArrayList<DataSet>();
	
	public static void init(){
		StringWriter writer = new StringWriter();
		try {
			IOUtils.copy(new FileInputStream(new File(Bukkit.getWorlds().get(0).getName() + "/world-data.txt")), writer, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String theText = writer.toString();
		
		for(String s : theText.split(";")){
			DataSet d2 = new DataSet(s.split(":")[0], s.split(":")[1]);
			mapData.add(d2);
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
	
}
