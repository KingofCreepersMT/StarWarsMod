package com.multiteam.starwarsmod.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nonnull;

public interface IActivatable {
    default boolean getActivated(ItemStack stack) {
        return !stack.isEmpty() && stack.hasTag() && stack.getTag().getBoolean("activated");
    }

    @Nonnull
    default ItemStack setActivatedState(ItemStack stack, boolean activated) {
        if (!stack.isEmpty()) {
            if (!stack.hasTag()) {
                stack.setTag(new CompoundNBT());
            }
            stack.getTag().putBoolean("activated", activated);
        }
        return stack;
    }
}
