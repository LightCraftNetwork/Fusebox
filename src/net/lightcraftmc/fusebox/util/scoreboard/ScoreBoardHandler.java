package net.lightcraftmc.fusebox.util.scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

@SuppressWarnings("deprecation")
public class ScoreBoardHandler
{
	private final String boardName;
	private final String mainObjectiveName;
	private final Scoreboard scoreBoard;
	private Objective mainObjective;
	private final List<String> players = new ArrayList<String>();
	private DisplaySlot slot;

	public ScoreBoardHandler(String scoreBoardName, String mainObjectiveName, DisplaySlot slot)
	{
		this(scoreBoardName, mainObjectiveName, slot, null);
	}

	public ScoreBoardHandler(String scoreBoardName, String mainObjectiveName, DisplaySlot slot, String[] lines)
	{
		this.boardName = scoreBoardName;
		this.mainObjectiveName = mainObjectiveName;
		this.slot = slot;
		this.scoreBoard = Bukkit.getScoreboardManager().getNewScoreboard();
		this.mainObjective = this.scoreBoard.registerNewObjective("main", 
				ObjectiveCriteria.DUMMY.getName());

		this.mainObjective.setDisplayName(mainObjectiveName);
		this.mainObjective.setDisplaySlot(slot);
		if (lines != null) {
			for (int i = 0; i < lines.length; i++) {
				addObjectiveScore(lines[i], i);
			}
		}
	}

	public Scoreboard getScoreBoard()
	{
		return this.scoreBoard;
	}

	public String getBoardName()
	{
		return this.boardName;
	}

	public String getMainObjectiveName()
	{
		return this.mainObjectiveName;
	}
	
	public Objective getMainObjective(){
		return this.mainObjective;
	}

	public void setScoreboardName(String name)
	{
		this.mainObjective.setDisplayName(name);
		updatePlayersOnScoreboardNameChange();
	}

	public void changeCriteria(ObjectiveCriteria criteria)
	{
		this.mainObjective.unregister();
		this.mainObjective = null;
		this.mainObjective = this.scoreBoard.registerNewObjective("main", 
				criteria.getName());
		this.mainObjective.setDisplayName(this.mainObjectiveName);
		this.mainObjective.setDisplaySlot(this.slot);
	}

	public void changeCriteria(Stat criteria, int id)
	{
		this.mainObjective.unregister();
		this.mainObjective = null;
		this.mainObjective = this.scoreBoard.registerNewObjective("main", 
				Stat.getStatWithID(criteria, id));
		this.mainObjective.setDisplayName(this.mainObjectiveName);
		this.mainObjective.setDisplaySlot(this.slot);
	}

	public DisplaySlot getDisplaySlot()
	{
		return this.slot;
	}

	public void setDisplaySlot(DisplaySlot slot)
	{
		this.slot = slot;
	}

	private void updatePlayersOnScoreboardNameChange()
	{
		for (String playerName : this.players)
		{
			Player player = Bukkit.getPlayer(playerName);
			if (player != null) {
				player.setScoreboard(this.scoreBoard);
			}
		}
	}

	public void checkPlayers()
	{
		for (String playerName : this.players)
		{
			Player player = Bukkit.getPlayer(playerName);
			if (player != null)
			{
				if (!player.getScoreboard().equals(this.scoreBoard)) {
					this.players.remove(playerName);
				}
			}
			else {
				this.players.remove(playerName);
			}
		}
	}

	public List<String> getPlayers()
	{
		checkPlayers();
		return this.players;
	}

	public Set<Team> getTeams()
	{
		return this.scoreBoard.getTeams();
	}

	public Team getTeam(String teamName)
	{
		return this.scoreBoard.getTeam(teamName);
	}

	public void registerTeam(String teamName)
	{
		this.scoreBoard.registerNewTeam(teamName);
	}

	public Set<Objective> getObjectives()
	{
		return this.scoreBoard.getObjectives();
	}

	public Objective getObjective(String objectiveName)
	{
		return this.scoreBoard.getObjective(objectiveName);
	}

	public void registerObjective(String objective, ObjectiveCriteria criteria)
	{
		this.scoreBoard.registerNewObjective(objective, criteria.getName());
	}

	public Objective getObjectiveInSlot(DisplaySlot slot)
	{
		return this.scoreBoard.getObjective(slot);
	}

	public void setScoreboard(Player player)
	{
		player.setScoreboard(this.scoreBoard);
		this.players.add(player.getName());
	}

	public void setScoreboard(Player player, Scoreboard otherScoreboard)
	{
		player.setScoreboard(otherScoreboard);
		this.players.remove(player.getName());
	}

	public void removeScoreboard(Player player, boolean blank)
	{
		if (blank) {
			player.setScoreboard(Bukkit.getScoreboardManager()
					.getNewScoreboard());
		} else {
			player.setScoreboard(Bukkit.getScoreboardManager()
					.getMainScoreboard());
		}
	}

	public void addObjectiveScore(String scoreName, int score)
	{
		this.mainObjective.getScore(Bukkit.getOfflinePlayer(scoreName)).setScore(
				score);
	}

	public void addObjectiveScore(Objective objective, String scoreName, int score)
	{
		objective.getScore(Bukkit.getOfflinePlayer(scoreName)).setScore(score);
	}

	public Score getObjectiveScore(String score)
	{
		return this.mainObjective.getScore(Bukkit.getOfflinePlayer(score));
	}

	public Score getObjectiveScore(Objective objective, String score)
	{
		return objective.getScore(Bukkit.getOfflinePlayer(score));
	}

	public void setObjectiveScore(String scoreName, int setScore)
	{
		getObjectiveScore(scoreName).setScore(setScore);
	}

	public void setObjectiveScore(Objective objective, String scoreName, int setScore)
	{
		getObjectiveScore(objective, scoreName).setScore(setScore);
	}

	public Set<Score> getScores(String score)
	{
		return this.scoreBoard.getScores(Bukkit.getOfflinePlayer(score));
	}

	public static enum ObjectiveCriteria
	{
		DUMMY("dummy"),  DEATH_COUNT("deathCount"),  HEALTH("health"),  PLAYER_KILL_COUNT("playerKillCount"),
		TOTAL_KILL_COUNT("totalKillCount"),  achievement_MAKE_BREAD("achievement.makeBread"),
		achievement_BAKE_CAKE("achievement.bakeCake"),  achievement_DIAMONDS_TO_YOU("achievement.diamondsToYou"),
		achievement_KILL_COW("achievement.killCow"),  achievement_PORTAL("achievement.portal"),
		achievement_BUILD_FURNACE("achievement.buildFurnace"),  achievement_BUILD_SWORD("achievement.buildSword"),
		achievement_COOK_FISH("achievement.cookFish"),  achievement_ENCHANTMENTS("achievement.enchantments"),
		achievement_MINE_WOOD(
																								"achievement.mineWood"),  achievement_OPEN_INVENTORY(
																										"achievement.openInventory"),  achievement_EXPLORE_ALL_BIOMES(
																												"achievement.exploreAllBiomes"),  achievement_BUILD_WORKBENCH(
																														"achievement.buildWorkBench"),  achievement_THE_END(
																																"achievement.theEnd"),  achievement_BLAZE_ROD(
																																		"achievement.blazeRod"),  achievement_SPAWN_WITHER(
																																				"achievement.spawnWither"),  achievement_BUILD_BETTER_PICKAXE(
																																						"achievement.buildBetterPickaxe"),  achievement_ACQUIRE_IRON(
																																								"achievement.acquireIron"),  achievement_THE_END2(
																																										"achievement.theEnd2"),  achievement_BOOKCASE(
																																												"achievement.bookcase"),  achievement_FLYING_PIG(
																																														"achievement.flyPig"),  achievement_GHAST("achievement.ghast"),  achievement_SNIPE_SKELETON(
																																																"achievement.snipeSkeleton"),  achievement_DIAMONDS(
																																																		"achievement.diamonds"),  achievement_KILL_WITHER(
																																																				"achievement.killWither"),  achievement_FULL_BEACON(
																																																						"achievement.fullBeacon"),  achievement_BUILD_HOE(
																																																								"achievement.buildHoe"),  achievement_BREED_COW(
																																																										"achievement.breedCow"),  achievement_ON_A_RAIL(
																																																												"achievement.onARail"),  achievement_OVERKILL(
																																																														"achievement.overkill"),  achievement_KILL_ENEMY(
																																																																"achievement.killEnemy"),  achievement_POTION(
																																																																		"achievement.potion"),  achievement_BUILD_PICKAXE(
																																																																				"achievement.buildPickaxe"),  stat_DAMAGE_DEALT("stat.damageDealt"),  stat_DAMAGE_TAKE(
																																																																						"stat.damageTaken"),  stat_LEAVE_GAME("stat.leaveGame"),  stat_MINECART_ONE_CM(
																																																																								"stat.minecartOneCm"),  stat_SWIM_ONE_CM("stat.swimOneCm"),  stat_WALK_ONE_CM(
																																																																										"stat.walkOneCm"),  stat_HORSE_ONE_CM("stat.horseOneCm"),  stat_PIG_ONE_CM(
																																																																												"stat.pigOneCm"),  stat_FLY_ONE_CM("stat.flyOneCm"),  stat_BOAT_ONE_CM(
																																																																														"stat.boatOneCm"),  stat_FALL_ONE_CM("stat.fallOneCm"),  stat_CLIMB_ONE_CM(
																																																																																"stat.climbOneCm"),  stat_DIVE_ONE_CM("stat.diveOneCm"),  stat_FISH_CAUGHT(
																																																																																		"stat.fishCaught"),  stat_JUNK_FISHED("stat.junkFished"),  stat_TREASURE_FISHED(
																																																																																				"stat.treasureFished"),  stat_PLAY_ONE_MINUE(
																																																																																						"stat.playOneMinute"),  stat_PLAYER_KILLS("stat.playerKills"),  stat_MOB_KILLS(
																																																																																								"stat.mobKills"),  stat_ANIMALS_BRED("stat.animalsBred"),  stat_JUMP(
																																																																																										"stat.jump"),  stat_DROP("stat.drop"),  stat_DEATHS("stat.deaths"),  stat_killEntity_SILVERFISH("stat.killEntity.Silverfish"),  stat_killEntity_ZOMBIE(
																																																																																												"stat.killEntity.Zombie"),  stat_killEntity_BLAZE(
																																																																																														"stat.killEntity.Blaze"),  stat_killEntity_PIG(
																																																																																																"stat.killEntity.Pig"),  stat_killEntity_CREEPER(
																																																																																																		"stat.killEntity.Creeper"),  stat_killEntity_COW(
																																																																																																				"stat.killEntity.Cow"),  stat_killEntity_GHAS(
																																																																																																						"stat.killEntity.Ghast"),  stat_killEntity_WHICH(
																																																																																																								"stat.killEntity.Witch"),  stat_killEntity_SQUID(
																																																																																																										"stat.killEntity.Squid"),  stat_killEntity_SPIDER(
																																																																																																												"stat.killEntity.Spider"),  stat_killEntity_VILLATER(
																																																																																																														"stat.killEntity.Villager"),  stat_killEntity_ENDERMAN(
																																																																																																																"stat.killEntity.Enderman"),  stat_killEntity_LAVA_SLIME(
																																																																																																																		"stat.killEntity.LavaSlime"),  stat_killEntity_PIG_ZOMBIE(
																																																																																																																				"stat.killEntity.PigZombie"),  stat_killEntity_WOLF(
																																																																																																																						"stat.killEntity.Wolf"),  stat_killEntity_SHEEP(
																																																																																																																								"stat.killEntity.Sheep"),  stat_killEntity_CHIKEN(
																																																																																																																										"stat.killEntity.Chicken"),  stat_killEntity_SLIME(
																																																																																																																												"stat.killEntity.Slime"),  stat_killEntity_SLELETON(
																																																																																																																														"stat.killEntity.Skeleton"),  stat_killEntity_BAT(
																																																																																																																																"stat.killEntity.Bat"),  stat_killEntity_MUSHROOM_COW(
																																																																																																																																		"stat.killEntity.MushroomCow"),  stat_killEntity_CAVE_SPIDER(
																																																																																																																																				"stat.killEntity.CaveSpider"),  stat_killEntity_HORSE(
																																																																																																																																						"stat.killEntity.EntityHorse"),  stat_killEntity_OCELOT("stat.killEntity.Ozelot"),  stat_entityKilledBy_WOLF("stat.entityKilledBy.Wolf"),  stat_entityKilledBy_ENDERMAN(
																																																																																																																																								"stat.entityKilledBy.Enderman"),  stat_entityKilledBy_SLIME(
																																																																																																																																										"stat.entityKilledBy.Slime"),  stat_entityKilledBy_LAVA_SLIME(
																																																																																																																																												"stat.entityKilledBy.LavaSlime"),  stat_entityKilledBy_SPIDER(
																																																																																																																																														"stat.entityKilledBy.Spider"),  stat_entityKilledBy_CREEPER(
																																																																																																																																																"stat.entityKilledBy.Creeper"),  stat_entityKilledBy_BAT(
																																																																																																																																																		"stat.entityKilledBy.Bat"),  stat_entityKilledBy_SQUID(
																																																																																																																																																				"stat.entityKilledBy.Squid"),  stat_entityKilledBy_PIG_ZOMBIE(
																																																																																																																																																						"stat.entityKilledBy.PigZombie"),  stat_entityKilledBy_SILVERHIST(
																																																																																																																																																								"stat.entityKilledBy.Silverfish"),  stat_entityKilledBy_SKELETON(
																																																																																																																																																										"stat.entityKilledBy.Skeleton"),  stat_entityKilledBy_WHICH(
																																																																																																																																																												"stat.entityKilledBy.Witch"),  stat_entityKilledBy_PIG(
																																																																																																																																																														"stat.entityKilledBy.Pig"),  stat_entityKilledBy_BLAZE(
																																																																																																																																																																"stat.entityKilledBy.Blaze"),  stat_entityKilledBy_SHEEP(
																																																																																																																																																																		"stat.entityKilledBy.Sheep"),  stat_entityKilledBy_MUSHROOM_COW(
																																																																																																																																																																				"stat.entityKilledBy.MushroomCow"),  stat_entityKilledBy_CAVE_SPIDER(
																																																																																																																																																																						"stat.entityKilledBy.CaveSpider"),  stat_entityKilledBy_VILLAGER(
																																																																																																																																																																								"stat.entityKilledBy.Villager"),  stat_entityKilledBy_ZOMBIE(
																																																																																																																																																																										"stat.entityKilledBy.Zombie"),  stat_entityKilledBy_CHICKEN(
																																																																																																																																																																												"stat.entityKilledBy.Chicken"),  stat_entityKilledBy_COW(
																																																																																																																																																																														"stat.entityKilledBy.Cow"),  stat_entityKilledBy_GHAST(
																																																																																																																																																																																"stat.entityKilledBy.Ghast"),  stat_entityKilledBy_HORSE(
																																																																																																																																																																																		"stat.entityKilledBy.EntityHorse"),  stat_entityKilledBy_OCELOT("stat.entityKilledBy.Ozelot");

		private final String name;

		private ObjectiveCriteria(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return this.name;
		}

		public static String getCriteriaWithID(ObjectiveCriteria crit, int id)
		{
			return crit.getName() + "." + id;
		}
	}

	public static enum Stat
	{
		CRAFT_ITEM("stat.craftItem"),  USE_ITEM("stat.useItem"),  BREAK_ITEM(
				"stat.breakItem"),  MINE_BLOCK("stat.mineBlock");

		private final String name;

		private Stat(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return this.name;
		}

		public static String getStatWithID(Stat stat, int id)
		{
			return stat.getName() + "." + id;
		}
	}
}
