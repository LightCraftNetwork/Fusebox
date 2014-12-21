package net.lightcraftmc.fusebox.util.sound;

import net.minecraft.server.v1_8_R1.PacketPlayOutNamedSoundEffect;
import net.minecraft.server.v1_8_R1.PlayerConnection;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class SoundTools {

	public static void customSound(Player player, String sound, float vol, float pitch){
		Player p = player;
		PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(sound, p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ(), vol, pitch);
		getConnection(player).sendPacket(packet);
	}
	public static PlayerConnection getConnection(Player p){
		return ((CraftPlayer)p).getHandle().playerConnection;
	}
	public static void customSound(Player player, String sound){
		customSound(player, sound, 1.0F, 1.0F);
	}
	
	public static void customSound(Player player, String sound, float pitch){
		customSound(player, sound, Float.MAX_VALUE, pitch);
	}
}
