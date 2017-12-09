package com.fabbe50.starwarsmod.common;

import com.fabbe50.starwarsmod.Reference;
import com.fabbe50.starwarsmod.registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by fabbe on 09/12/2017 - 12:35 AM.
 */
public class CreativeTabSW {
    public static CreativeTabs starwarstab = new CreativeTabs(Reference.MOD_ID.toLowerCase() + ".starwarsmod") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.LIGHT_SABER);
        }
    };
}
