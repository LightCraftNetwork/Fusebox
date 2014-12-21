package net.lightcraft.fusebox.util;

public abstract interface Callback<T>
{
  public abstract void run(T paramT);
}