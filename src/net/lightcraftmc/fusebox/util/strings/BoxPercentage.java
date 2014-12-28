package net.lightcraftmc.fusebox.util.strings;

public class BoxPercentage {
	public static String generateBoxPercentage(double input){
		int working = (int) Math.round(input/10);
		String s = "";
		for(int i = 0; i < working; i++) s = s + "■";
		String color = "";
		if(working >= 5){
			color = "§a"; 
		}
		if(working < 5){
			color = "§6";
		}
		 if(working < 3){
			 color = "§c";
		 }
		s = color + s;
		int remainder = 10-working;
		for(int i = 0; i < remainder; i++) s = s + "§7■";
		return s;
	}
}
