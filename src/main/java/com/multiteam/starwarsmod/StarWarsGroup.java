package com.multiteam.starwarsmod;

import com.multiteam.starwarsmod.registry.BlockRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe on 09/12/2017 - 12:35 AM.
 */
public class StarWarsGroup extends ItemGroup {
    public static StarWarsGroup group = new StarWarsGroup(Reference.MOD_NAME);

    private StarWarsGroup(String title) {
        super(title);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(BlockRegistry.KYBER_CRYSTAL_BLUE.get());
    }
}
