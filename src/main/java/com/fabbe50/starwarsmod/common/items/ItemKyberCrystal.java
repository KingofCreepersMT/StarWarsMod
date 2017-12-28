package com.fabbe50.starwarsmod.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by fabbe on 20/12/2017 - 8:53 AM.
 */
public class ItemKyberCrystal extends ItemBase {
    private String[] names = new String[] {"blue", "green", "purple", "red"};

    public ItemKyberCrystal(String name) {
        super(name);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.getMetadata() <= 3)
            return "item." + this.getRegistryName().getResourceDomain() + ":" + this.getRegistryName().getResourcePath() + "_" + names[stack.getMetadata()];
        return "";
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if (this.isInCreativeTab(tab)) {
            for (int i = 0; i < 4; ++i) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }
}
