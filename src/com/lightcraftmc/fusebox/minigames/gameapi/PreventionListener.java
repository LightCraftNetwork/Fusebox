package com.lightcraftmc.fusebox.minigames.gameapi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Copyright Elliott Olson (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar brandings
 * are the sole property of Elliott Olson. Distribution, reproduction, taking snippits, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 */
public class PreventionListener implements Listener{

    PreventionSet set = new PreventionSet();

    @EventHandler
    public void onBreak(BlockBreakEvent event){

        event.setCancelled(set.isDestroy());

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){

        e.setCancelled(set.isBuild());

    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){

        e.setCancelled(set.isDropItem());

    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){

        e.setCancelled(set.isPickupItem());

    }

    @EventHandler
    public void onPvp(EntityDamageByEntityEvent e){

        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player){

            e.setCancelled(set.isPvp());

        }

    }

    @EventHandler
    public void onPve(EntityDamageByEntityEvent e){

        if (e.getDamager() instanceof Player){

            if (!(e.getEntity() instanceof Player)){

                e.setCancelled(set.isPve());

            }

        } else if (!(e.getDamager() instanceof Player)){

            if (e.getEntity() instanceof Player){

                e.setCancelled(set.isPve());

            }

        }

    }


}
