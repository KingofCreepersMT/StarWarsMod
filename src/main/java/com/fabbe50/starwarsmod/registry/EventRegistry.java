package com.fabbe50.starwarsmod.registry;

import com.fabbe50.starwarsmod.event.SpaceEvents;
import com.fabbe50.starwarsmod.handlers.DimensionTeleportHandler;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by fabbe on 09/12/2017 - 3:30 AM.
 */
public class EventRegistry {
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new DimensionTeleportHandler());
        MinecraftForge.EVENT_BUS.register(new SpaceEvents());
    }
}
