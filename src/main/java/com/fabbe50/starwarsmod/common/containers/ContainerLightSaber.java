package com.fabbe50.starwarsmod.common.containers;

import com.google.common.base.Preconditions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

import javax.annotation.Nonnull;

/**
 * Created by fabbe on 21/12/2017 - 7:00 AM.
 */
public class ContainerLightSaber extends Container {
    public ContainerLightSaber(@Nonnull ItemStack saberStack, @Nonnull IInventory playerInventory) {
        Preconditions.checkNotNull(saberStack, "saberStack cannot be null");
        Preconditions.checkNotNull(playerInventory, "playerInventory cannot be null");

        for (int i1 = 0; i1 < 3; ++i1) {
            for (int k1 = 0; k1 < 9; ++k1) {
                this.addSlotToContainer(new Slot(playerInventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 84 + i1 * 18));
            }
        }

        for (int j1 = 0; j1 < 9; ++j1) {
            this.addSlotToContainer(new Slot(playerInventory, j1, 8 + j1 * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
    }
}
