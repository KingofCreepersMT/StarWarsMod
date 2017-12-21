package com.fabbe50.starwarsmod.registry;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * Created by fabbe on 21/12/2017 - 4:04 AM.
 */
public class KeyBindingRegistry {
    public static KeyBinding OPEN_SABER_UI = new KeyBinding("key.opensaberui.desc", Keyboard.KEY_G, "swmod.keybinds");

    public static void init() {
        ClientRegistry.registerKeyBinding(OPEN_SABER_UI);
    }
}
