package com.fabbe50.starwarsmod.event;

import com.fabbe50.starwarsmod.Config;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by fabbe on 29/12/2017 - 2:48 PM.
 */
public class SpaceEvents {
    @SubscribeEvent
    public void blockPlacements(BlockEvent.PlaceEvent event) {
        if (event.getPlayer().dimension == Config.spaceDimId) {
            boolean blacklistedBlock = false;
            switch (event.getPlacedBlock().getBlock().getRegistryName().toString()) {
                case "minecraft:torch":
                    blacklistedBlock = true;
                    break;
            }

            if (blacklistedBlock) {
                event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState());
                event.getWorld().spawnEntity(new EntityItem(event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(Item.getItemFromBlock(event.getPlacedBlock().getBlock()))));
            }
        }
    }
}
