package com.fabbe50.starwarsmod.proxy;

import com.fabbe50.starwarsmod.registry.DimensionRegistry;
import com.fabbe50.starwarsmod.registry.EventRegistry;
import com.fabbe50.starwarsmod.registry.ItemRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;

/**
 * Created by fabbe on 08/12/2017 - 11:41 PM.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        ItemRegistry.init();
        DimensionRegistry.init();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }

    public void onServerStarted(FMLServerStartedEvent event) {
        EventRegistry.init();
    }
}
