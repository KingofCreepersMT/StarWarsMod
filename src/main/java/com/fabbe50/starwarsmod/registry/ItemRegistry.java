package com.fabbe50.starwarsmod.registry;

import com.fabbe50.starwarsmod.common.items.ItemKyberCrystal;
import com.fabbe50.starwarsmod.common.items.ItemLightSaber;
import net.minecraft.item.Item;

/**
 * Created by fabbe on 09/12/2017 - 1:09 AM.
 */
public class ItemRegistry {
    public static final Item LIGHT_SABER_BLUE = new ItemLightSaber("light_saber_blue", Item.ToolMaterial.DIAMOND);
    public static final Item LIGHT_SABER_GREEN = new ItemLightSaber("light_saber_green", Item.ToolMaterial.DIAMOND);
    public static final Item LIGHT_SABER_PURPLE = new ItemLightSaber("light_saber_purple", Item.ToolMaterial.DIAMOND);
    public static final Item LIGHT_SABER_RED = new ItemLightSaber("light_saber_red", Item.ToolMaterial.DIAMOND);
    public static final Item KYBER_CRYSTAL = new ItemKyberCrystal("kybercrystal");


    //Normal items: RegistryHandler.registerItem(ITEM);
    //Meta items: RegistryHandler.registerItem(ITEM, "path0", "path1", "path2"...);
    public static void init() {
        RegistryHandler.registerItem(LIGHT_SABER_BLUE, "lightsaber_blue", "lightsaber_off_blue");
        RegistryHandler.registerItem(LIGHT_SABER_GREEN, "lightsaber_green", "lightsaber_off_green");
        RegistryHandler.registerItem(LIGHT_SABER_PURPLE, "lightsaber_purple", "lightsaber_off_purple");
        RegistryHandler.registerItem(LIGHT_SABER_RED, "lightsaber_red", "lightsaber_off_red");
        RegistryHandler.registerItem(KYBER_CRYSTAL, "kybercrystal_blue", "kybercrystal_green", "kybercrystal_purple", "kybercrystal_red");
    }
}
