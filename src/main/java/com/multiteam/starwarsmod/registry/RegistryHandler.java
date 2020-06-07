package com.multiteam.starwarsmod.registry;

import com.google.common.base.Preconditions;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

/**
 * Created by fabbe on 08/12/2017 - 11:47 PM.
 */
public class RegistryHandler {
    public static void init() {
        new BlockRegistry();
        new ItemRegistry();
        new TileEntityRegistry();
        new ContainerRegistry();
        new EntityRegistry();
        new DimensionRegistry();
    }
}
