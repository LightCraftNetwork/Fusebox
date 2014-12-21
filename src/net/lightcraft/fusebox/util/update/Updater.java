package net.lightcraft.fusebox.util.update;

import net.lightcraft.fusebox.util.update.event.UpdateEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class Updater
  implements Runnable
{
  private JavaPlugin _plugin;

  public Updater(JavaPlugin plugin)
  {
    this._plugin = plugin;
    this._plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this._plugin, this, 0L, 1L);
  }

  public void run()
  {
    for (UpdateType updateType : (UpdateType[])UpdateType.class.getEnumConstants())
      if (updateType.Elapsed())
        this._plugin.getServer().getPluginManager().callEvent(new UpdateEvent(updateType));
  }
}