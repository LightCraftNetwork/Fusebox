package com.lightcraftmc.fusebox.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class UtilPacket {

	public static void sendPacketPlayOutOpenChest(Player player, Location location, int radius){
		try{
			Class<?> world = NMSUtils.getNMSClass("World");
			Object ws = NMSUtils.getHandle(location.getWorld());
			
			Class<?> bp = NMSUtils.getNMSClass("BlockPosition");
			Object bp1 = bp.getConstructor(int.class, int.class, int.class).newInstance(location.getBlockX(), location.getBlockY(), location.getBlockZ());
			
			Class<?> ppoba = NMSUtils.getNMSClass("PacketPlayOutBlockAction");
			Class<?> bl = NMSUtils.getNMSClass("Block");
			Object iblock = NMSUtils.getMethod(world, "getType", bp).invoke(ws, bp1);
			Class<?> iblockd = NMSUtils.getNMSClass("IBlockData");
			Object block = iblockd.getMethod("getBlock").invoke(iblock);
			Object packet = ppoba.getConstructor(bp, bl, int.class, int.class).newInstance(bp1, block, 1, 1);
			sendPacket(player, packet);
		    for (Entity e : player.getNearbyEntities(radius, radius, radius)) {
		      if(e instanceof Player){
		    	  sendPacket(player, packet);
		      }
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void sendPacket(Player player, Object packet){
		try{
			Object nmsPlayer = NMSUtils.getHandle(player);
			Field con_field = nmsPlayer.getClass().getField("playerConnection");
			con_field.setAccessible(true);
			Object con = con_field.get(nmsPlayer);
			Method sendToPlayer = con.getClass().getMethod("sendPacket", NMSUtils.getNMSClass("Packet"));
			sendToPlayer.invoke(con, packet);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
