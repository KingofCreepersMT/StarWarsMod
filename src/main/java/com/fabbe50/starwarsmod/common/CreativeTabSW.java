package com.fabbe50.starwarsmod.common;

import com.fabbe50.starwarsmod.Reference;
import com.fabbe50.starwarsmod.common.items.ItemKyberCrystal;
import com.fabbe50.starwarsmod.common.items.ItemLightSaber;
import com.fabbe50.starwarsmod.helpers.LogHelper;
import com.fabbe50.starwarsmod.registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fabbe on 09/12/2017 - 12:35 AM.
 */
public class CreativeTabSW {
    public static CreativeTabs starwarstab = new CreativeTabs(Reference.MOD_ID.toLowerCase() + ".starwarsmod") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.KYBER_CRYSTAL);
        }

        @Override
        public void displayAllRelevantItems(NonNullList<ItemStack> itemList) {
            super.displayAllRelevantItems(itemList);

            List<ItemStack> list = new ArrayList<>();
            list.add(new ItemStack(ItemRegistry.LIGHT_SABER_BLUE, 1, 1));
            list.add(new ItemStack(ItemRegistry.LIGHT_SABER_GREEN, 1, 1));
            list.add(new ItemStack(ItemRegistry.LIGHT_SABER_PURPLE, 1, 1));
            list.add(new ItemStack(ItemRegistry.LIGHT_SABER_RED, 1, 1));
            list.add(new ItemStack(ItemRegistry.KYBER_CRYSTAL, 1, 0));
            list.add(new ItemStack(ItemRegistry.KYBER_CRYSTAL, 1, 1));
            list.add(new ItemStack(ItemRegistry.KYBER_CRYSTAL, 1, 2));
            list.add(new ItemStack(ItemRegistry.KYBER_CRYSTAL, 1, 3));

            List<Item> list1 = new ArrayList<>();
            list1.add(ItemRegistry.LIGHT_SABER_BLUE);
            list1.add(ItemRegistry.LIGHT_SABER_GREEN);
            list1.add(ItemRegistry.LIGHT_SABER_PURPLE);
            list1.add(ItemRegistry.LIGHT_SABER_RED);
            list1.add(ItemRegistry.KYBER_CRYSTAL);

            List<ItemStack> temp = new ArrayList<>();
            temp.addAll(itemList);
            itemList.clear();
            itemList.addAll(list);
        }
    };
}
