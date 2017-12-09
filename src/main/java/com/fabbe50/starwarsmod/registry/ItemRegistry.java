package com.fabbe50.starwarsmod.registry;

import com.fabbe50.starwarsmod.common.items.ItemLightSaber;
import javafx.scene.effect.Light;
import net.minecraft.item.Item;

/**
 * Created by fabbe on 09/12/2017 - 1:09 AM.
 */
public class ItemRegistry {
    public static final Item LIGHT_SABER = new ItemLightSaber("light_saber", Item.ToolMaterial.DIAMOND);

    public static void init() {
        RegistryHandler.registerItem(LIGHT_SABER);
    }
}
