package com.lightcraftmc.fusebox.util.update.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.lightcraftmc.fusebox.util.update.UpdateType;

public class UpdateEvent extends Event
{
  private static final HandlerList handlers = new HandlerList();
  private UpdateType _type;

  public UpdateEvent(UpdateType example)
  {
    this._type = example;
  }

  public UpdateType getType()
  {
    return this._type;
  }

  public HandlerList getHandlers()
  {
    return handlers;
  }

  public static HandlerList getHandlerList()
  {
    return handlers;
  }
}