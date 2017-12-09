package com.fabbe50.starwarsmod.common.items;

import com.fabbe50.starwarsmod.Reference;
import com.fabbe50.starwarsmod.common.CreativeTabSW;
import net.minecraft.item.Item;

/**
 * Created by fabbe on 09/12/2017 - 12:14 AM.
 */
public class ItemBase extends Item {
    public ItemBase(String name) {
        this.setCreativeTab(CreativeTabSW.starwarstab);
        this.setRegistryName(Reference.MOD_ID, name);
        this.setUnlocalizedName(this.getRegistryName().toString());
    }
}
