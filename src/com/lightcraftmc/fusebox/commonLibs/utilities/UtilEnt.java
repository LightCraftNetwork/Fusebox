package com.lightcraftmc.fusebox.commonLibs.utilities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.server.v1_8_R1.AttributeInstance;
import net.minecraft.server.v1_8_R1.EntityArmorStand;
import net.minecraft.server.v1_8_R1.EntityCreature;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.EntityItem;
import net.minecraft.server.v1_8_R1.GenericAttributes;
import net.minecraft.server.v1_8_R1.Navigation;
import net.minecraft.server.v1_8_R1.PathEntity;
import net.minecraft.server.v1_8_R1.PathfinderGoalSelector;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.lightcraftmc.fusebox.Core;

public class UtilEnt
implements Listener
{
	private static HashMap<org.bukkit.entity.Entity, String> _nameMap = new HashMap();
	private static HashMap<String, EntityType> creatureMap = new HashMap();
	private static Field _goalSelector;
	public static HashMap<Player, ArrayList<org.bukkit.entity.Entity>> flyingEntities = new HashMap();

	public static HashMap<org.bukkit.entity.Entity, String> GetEntityNames()
	{
		return _nameMap;
	}

	public static void dropItemToRemove(Location loc, org.bukkit.inventory.ItemStack i, int number, int removeTime, String Metadata, boolean playEffect)
	{
		for (int k = 0; k < number; k++) {
			EntityItem ei = new EntityItem(
					((CraftWorld)loc.getWorld()).getHandle(), 
					loc.getX(), 
					loc.getY(), 
					loc.getZ(), 
					CraftItemStack.asNMSCopy(i))
			{
				public boolean a(EntityItem entityitem) {
					return false;
				}
			};
			ei.pickupDelay = 20;
			((Item)ei.getBukkitEntity()).setVelocity(new Vector(UtilityMath.randomRange(-0.2000000029802322D, 0.2000000029802322D), 0.5D, UtilityMath.randomRange(-0.2000000029802322D, 0.2000000029802322D)));
			((CraftWorld)loc.getWorld()).getHandle().addEntity(ei);
			((Item)ei.getBukkitEntity()).setMetadata(Metadata, new FixedMetadataValue(
					Core.getInstance(), Metadata));
			UtilItem.EntityToRemove(ei.getBukkitEntity(), removeTime, playEffect);
		}
	}

	public static void removeGoalSelectors(org.bukkit.entity.Entity entity)
	{
		try
		{
			if (_goalSelector == null)
			{
				_goalSelector = EntityInsentient.class.getDeclaredField("goalSelector");
				_goalSelector.setAccessible(true);
			}

			if ((((CraftEntity)entity).getHandle() instanceof EntityInsentient))
			{
				EntityInsentient creature = (EntityInsentient)((CraftEntity)entity).getHandle();

				PathfinderGoalSelector goalSelector = new PathfinderGoalSelector(((CraftWorld)entity.getWorld()).getHandle().methodProfiler);

				_goalSelector.set(creature, goalSelector);
			}
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchFieldException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
	}

	public static void populate()
	{
		if (creatureMap.isEmpty())
		{
			creatureMap.put("Bat", EntityType.BAT);
			creatureMap.put("Blaze", EntityType.BLAZE);
			creatureMap.put("Arrow", EntityType.ARROW);
			creatureMap.put("Cave Spider", EntityType.CAVE_SPIDER);
			creatureMap.put("Chicken", EntityType.CHICKEN);
			creatureMap.put("Cow", EntityType.COW);
			creatureMap.put("Creeper", EntityType.CREEPER);
			creatureMap.put("Ender Dragon", EntityType.ENDER_DRAGON);
			creatureMap.put("Enderman", EntityType.ENDERMAN);
			creatureMap.put("Ghast", EntityType.GHAST);
			creatureMap.put("Giant", EntityType.GIANT);
			creatureMap.put("Horse", EntityType.HORSE);
			creatureMap.put("Iron Golem", EntityType.IRON_GOLEM);
			creatureMap.put("Item", EntityType.DROPPED_ITEM);
			creatureMap.put("Magma Cube", EntityType.MAGMA_CUBE);
			creatureMap.put("Mooshroom", EntityType.MUSHROOM_COW);
			creatureMap.put("Ocelot", EntityType.OCELOT);
			creatureMap.put("Pig", EntityType.PIG);
			creatureMap.put("Pig Zombie", EntityType.PIG_ZOMBIE);
			creatureMap.put("Sheep", EntityType.SHEEP);
			creatureMap.put("Silverfish", EntityType.SILVERFISH);
			creatureMap.put("Skeleton", EntityType.SKELETON);
			creatureMap.put("Slime", EntityType.SLIME);
			creatureMap.put("Snowman", EntityType.SNOWMAN);
			creatureMap.put("Spider", EntityType.SPIDER);
			creatureMap.put("Squid", EntityType.SQUID);
			creatureMap.put("Villager", EntityType.VILLAGER);
			creatureMap.put("Witch", EntityType.WITCH);
			creatureMap.put("Wither", EntityType.WITHER);
			creatureMap.put("WitherSkull", EntityType.WITHER_SKULL);
			creatureMap.put("Wolf", EntityType.WOLF);
			creatureMap.put("Zombie", EntityType.ZOMBIE);

			creatureMap.put("Item", EntityType.DROPPED_ITEM);
		}
	}

	public static String getName(org.bukkit.entity.Entity ent)
	{
		if (ent == null) {
			return "Null";
		}
		if (ent.getType() == EntityType.PLAYER) {
			return ((Player)ent).getName();
		}
		if (GetEntityNames().containsKey(ent)) {
			return (String)GetEntityNames().get(ent);
		}
		if ((ent instanceof LivingEntity))
		{
			LivingEntity le = (LivingEntity)ent;
			if (le.getCustomName() != null) {
				return le.getCustomName();
			}
		}
		return getName(ent.getType());
	}

	public static String getName(EntityType type)
	{
		for (String cur : creatureMap.keySet()) {
			if (creatureMap.get(cur) == type)
				return cur;
		}
		return type.getName();
	}

	public static boolean hitBox(Location loc, LivingEntity ent, double mult)
	{
		if (UtilMath.offset(loc, ent.getLocation().add(0.0D, 0.4D, 0.0D)) < 0.6D * mult) {
			return true;
		}

		if ((ent instanceof Player))
		{
			Player player = (Player)ent;

			if (UtilMath.offset(loc, player.getEyeLocation()) < 0.4D * mult)
			{
				return true;
			}
			if (UtilMath.offset2d(loc, player.getLocation()) < 0.6D * mult)
			{
				if ((loc.getY() > player.getLocation().getY()) && (loc.getY() < player.getEyeLocation().getY()))
				{
					return true;
				}

			}

		}
		else if ((ent instanceof Giant))
		{
			if ((loc.getY() > ent.getLocation().getY()) && (loc.getY() < ent.getLocation().getY() + 12.0D) && 
					(UtilMath.offset2d(loc, ent.getLocation()) < 4.0D)) {
				return true;
			}

		}
		else if ((loc.getY() > ent.getLocation().getY()) && (loc.getY() < ent.getLocation().getY() + 2.0D) && 
				(UtilMath.offset2d(loc, ent.getLocation()) < 0.5D * mult)) {
			return true;
		}

		return false;
	}

	public static boolean isGrounded(org.bukkit.entity.Entity ent)
	{
		if ((ent instanceof CraftEntity)) {
			return ((CraftEntity)ent).getHandle().onGround;
		}
		return UtilityBlock.solid(ent.getLocation().getBlock().getRelative(BlockFace.DOWN));
	}

	public static void PlayDamageSound(LivingEntity damagee)
	{
		Sound sound = Sound.HURT_FLESH;

		if (damagee.getType() == EntityType.BAT) sound = Sound.BAT_HURT;
		else if (damagee.getType() == EntityType.BLAZE) sound = Sound.BLAZE_HIT;
		else if (damagee.getType() == EntityType.CAVE_SPIDER) sound = Sound.SPIDER_IDLE;
		else if (damagee.getType() == EntityType.CHICKEN) sound = Sound.CHICKEN_HURT;
		else if (damagee.getType() == EntityType.COW) sound = Sound.COW_HURT;
		else if (damagee.getType() == EntityType.CREEPER) sound = Sound.CREEPER_HISS;
		else if (damagee.getType() == EntityType.ENDER_DRAGON) sound = Sound.ENDERDRAGON_GROWL;
		else if (damagee.getType() == EntityType.ENDERMAN) sound = Sound.ENDERMAN_HIT;
		else if (damagee.getType() == EntityType.GHAST) sound = Sound.GHAST_SCREAM;
		else if (damagee.getType() == EntityType.GIANT) sound = Sound.ZOMBIE_HURT;
		else if (damagee.getType() == EntityType.IRON_GOLEM) sound = Sound.IRONGOLEM_HIT;
		else if (damagee.getType() == EntityType.MAGMA_CUBE) sound = Sound.MAGMACUBE_JUMP;
		else if (damagee.getType() == EntityType.MUSHROOM_COW) sound = Sound.COW_HURT;
		else if (damagee.getType() == EntityType.OCELOT) sound = Sound.CAT_MEOW;
		else if (damagee.getType() == EntityType.PIG) sound = Sound.PIG_IDLE;
		else if (damagee.getType() == EntityType.PIG_ZOMBIE) sound = Sound.ZOMBIE_HURT;
		else if (damagee.getType() == EntityType.SHEEP) sound = Sound.SHEEP_IDLE;
		else if (damagee.getType() == EntityType.SILVERFISH) sound = Sound.SILVERFISH_HIT;
		else if (damagee.getType() == EntityType.SKELETON) sound = Sound.SKELETON_HURT;
		else if (damagee.getType() == EntityType.SLIME) sound = Sound.SLIME_ATTACK;
		else if (damagee.getType() == EntityType.SNOWMAN) sound = Sound.STEP_SNOW;
		else if (damagee.getType() == EntityType.SPIDER) sound = Sound.SPIDER_IDLE;
		else if (damagee.getType() == EntityType.WITHER) sound = Sound.WITHER_HURT;
		else if (damagee.getType() == EntityType.WOLF) sound = Sound.WOLF_HURT;
		else if (damagee.getType() == EntityType.ZOMBIE) sound = Sound.ZOMBIE_HURT;

		damagee.getWorld().playSound(damagee.getLocation(), sound, 1.5F + (float)(0.5D * Math.random()), 0.8F + (float)(0.4000000059604645D * Math.random()));
	}

	public static boolean onBlock(Player player)
	{
		double xMod = player.getLocation().getX() % 1.0D;
		if (player.getLocation().getX() < 0.0D) {
			xMod += 1.0D;
		}
		double zMod = player.getLocation().getZ() % 1.0D;
		if (player.getLocation().getZ() < 0.0D) {
			zMod += 1.0D;
		}
		int xMin = 0;
		int xMax = 0;
		int zMin = 0;
		int zMax = 0;

		if (xMod < 0.3D) xMin = -1;
		if (xMod > 0.7D) xMax = 1;

		if (zMod < 0.3D) zMin = -1;
		if (zMod > 0.7D) zMax = 1;

		for (int x = xMin; x <= xMax; x++)
		{
			for (int z = zMin; z <= zMax; z++)
			{
				if ((player.getLocation().add(x, -0.5D, z).getBlock().getType() != Material.AIR) && (!player.getLocation().add(x, -0.5D, z).getBlock().isLiquid())) {
					return true;
				}

				Material beneath = player.getLocation().add(x, -1.5D, z).getBlock().getType();
				if ((player.getLocation().getY() % 0.5D == 0.0D) && (
						(beneath == Material.FENCE) || 
						(beneath == Material.NETHER_FENCE) || 
						(beneath == Material.COBBLE_WALL))) {
					return true;
				}
			}
		}
		return false;
	}

	public static void CreatureMove(org.bukkit.entity.Entity ent, Location target, float speed)
	{
		if (!(ent instanceof Creature)) {
			return;
		}
		if (UtilMath.offset(ent.getLocation(), target) < 0.1D) {
			return;
		}
		EntityCreature ec = ((CraftCreature)ent).getHandle();
		Navigation nav = (Navigation) ec.getNavigation();

		if (UtilMath.offset(ent.getLocation(), target) > 24.0D)
		{
			Location newTarget = ent.getLocation();

			newTarget.add(UtilAlg.getTrajectory(ent.getLocation(), target).multiply(24));

			nav.a(newTarget.getX(), newTarget.getY(), newTarget.getZ(), speed);
		}
		else
		{
			nav.a(target.getX(), target.getY(), target.getZ(), speed);
		}
	}

	public static boolean CreatureMoveFast(org.bukkit.entity.Entity ent, Location target, float speed)
	{
		if (!(ent instanceof Creature)) {
			return false;
		}
		if (UtilMath.offset(ent.getLocation(), target) < 0.1D) {
			return false;
		}
		if (UtilMath.offset(ent.getLocation(), target) < 2.0D) {
			speed = Math.min(speed, 1.0F);
		}
		EntityCreature ec = ((CraftCreature)ent).getHandle();
		ec.getControllerMove().a(target.getX(), target.getY(), target.getZ(), speed);

		return true;
	}

	public static org.bukkit.entity.Entity[] getNearbyEntities(Location l, int radius)
	{
		int chunkRadius = radius < 16 ? 1 : (radius - radius % 16) / 16;
		HashSet radiusEntities = new HashSet();
		for (int chX = 0 - chunkRadius; chX <= chunkRadius; chX++) {
			for (int chZ = 0 - chunkRadius; chZ <= chunkRadius; chZ++) {
				int x = (int)l.getX(); int y = (int)l.getY(); int z = (int)l.getZ();
				for (org.bukkit.entity.Entity e : new Location(l.getWorld(), x + chX * 16, y, z + chZ * 16).getChunk().getEntities()) {
					if ((e.getLocation().distance(l) <= radius) && (e.getLocation().getBlock() != l.getBlock())) radiusEntities.add(e);
				}
			}
		}
		return (org.bukkit.entity.Entity[])radiusEntities.toArray(new org.bukkit.entity.Entity[radiusEntities.size()]);
	}

	public static void setHelmet(LivingEntity e, org.bukkit.inventory.ItemStack helmet)
	{
		EntityEquipment ee = e.getEquipment();
		ee.setHelmet(helmet);
	}

	public static void setItemInHand(LivingEntity e, org.bukkit.inventory.ItemStack itemInHand)
	{
		EntityEquipment ee = e.getEquipment();
		ee.setItemInHand(itemInHand);
	}

	public static void RemoveHelmet(LivingEntity e)
	{
		EntityEquipment ee = e.getEquipment();
		ee.setHelmet(null);
	}

	public static void spawnFlyingItemToRemove(Player p, Location l, Material m, byte data)
	{
		if (!flyingEntities.containsKey(p)) {
			flyingEntities.put(p, new ArrayList());
		}

		WitherSkull skull = (WitherSkull)l.getWorld().spawn(
				l.add(0.5D, 0.0D, 0.5D), WitherSkull.class);
		skull.setDirection(new Vector(0, 0, 0));
		skull.setVelocity(new Vector(0, 0, 0));
		EntityItem ei = new EntityItem(
				((CraftWorld)l.getWorld()).getHandle(), 
				l.getX(), 
				l.getY(), 
				l.getZ(), 
				CraftItemStack.asNMSCopy(UtilItem.create(m, 1, data)))
		{
			public boolean a(EntityItem entityitem) {
				return false;
			}
		};
		ei.pickupDelay = 200000;
		((CraftWorld)l.getWorld()).getHandle().addEntity(ei);

		skull.setPassenger(ei.getBukkitEntity());
		ei.getBukkitEntity().setMetadata("unpickable", new FixedMetadataValue(
				Core.getInstance(), "unpickable"));
		((ArrayList)flyingEntities.get(p)).add(skull);
		((ArrayList)flyingEntities.get(p)).add(ei.getBukkitEntity());
	}

	public static void spawnArmourStandItem(Player p, Location l, Material m, byte data, String display){
		if (!flyingEntities.containsKey(p)) {
			flyingEntities.put(p, new ArrayList());
		}
		EntityArmorStand eas = new EntityArmorStand(((CraftWorld)l.getWorld()).getHandle(), 
				l.getX() + 0.5, 
				l.getY(), 
				l.getZ() + 0.5);
		eas.setInvisible(true);
		eas.setSmall(true);
		eas.setArms(false);
		eas.setGravity(true);
		eas.setBasePlate(true);
		eas.setCustomName(display);
		eas.setCustomNameVisible(true);
		EntityItem ei = new EntityItem(
				((CraftWorld)l.getWorld()).getHandle(), 
				l.getX(), 
				l.getY(), 
				l.getZ(), 
				CraftItemStack.asNMSCopy(UtilItem.create(m, 1, data)))
		{
			public boolean a(EntityItem entityitem) {
				return false;
			}
		};
		ei.pickupDelay = 200000;
		((CraftWorld)l.getWorld()).getHandle().addEntity(eas);
		((CraftWorld)l.getWorld()).getHandle().addEntity(ei);
		//ei.setPassengerOf(eas);
		ei.getBukkitEntity().setMetadata("unpickable", new FixedMetadataValue(
				Core.getInstance(), "unpickable"));
		((ArrayList)flyingEntities.get(p)).add(eas.getBukkitEntity());
		((ArrayList)flyingEntities.get(p)).add(ei.getBukkitEntity());
	}

	public static void spawnFlyingEntity(Player p, Location l, String text)
	{
		if (!flyingEntities.containsKey(p)) {
			flyingEntities.put(p, new ArrayList());
		}

		Bat b = (Bat)l.getWorld().spawn(
				l, Bat.class);
		b.setCustomName(text);
		b.setMetadata("nodamagetext", new FixedMetadataValue(
				Core.getInstance(), "nodamagetext"));
		UtilEntity.setNoAI(b, true);
		((ArrayList)flyingEntities.get(p)).add(b);
	}

	@EventHandler
	public void skullDamage(EntityDamageEvent event)
	{
		org.bukkit.entity.Entity e = event.getEntity();

		if (((e instanceof WitherSkull)) || ((e instanceof org.bukkit.entity.Entity)))
		{
			if (e.hasMetadata("nodamagetext"))
				event.setCancelled(true);
		}
	}

	public void setPetFollow(final Player player , final Entity pet , final double speed){
		new BukkitRunnable(){
			public void run(){
				if ((!pet.isValid() || (!player.isOnline()))){
					this.cancel();
				}
				net.minecraft.server.v1_8_R1.Entity pett = ((CraftEntity) pet).getHandle();
				((EntityInsentient) pett).getNavigation().a(2);
				Object petf = ((CraftEntity) pet).getHandle();
				Location targetLocation = player.getLocation();
				PathEntity path;
				path = ((EntityInsentient) petf).getNavigation().a(targetLocation.getX() + 1, targetLocation.getY(), targetLocation.getZ() + 1);
				if (path != null) {
					((EntityInsentient) petf).getNavigation().a(path, 1.0D);
					((EntityInsentient) petf).getNavigation().a(2.0D);
				}
				int distance = (int) Bukkit.getPlayer(player.getName()).getLocation().distance(pet.getLocation());
				if (distance > 10 && !pet.isDead() && player.isOnGround()) {
					pet.teleport(player.getLocation());
				}
				AttributeInstance attributes = ((EntityInsentient)((CraftEntity)pet).getHandle()).getAttributeInstance(GenericAttributes.d);
				attributes.setValue(speed);
			}
		}.runTaskTimer(Core.getInstance(), 0L, 20L);
	}
}
