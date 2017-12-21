package com.fabbe50.starwarsmod.handlers;

import com.fabbe50.starwarsmod.Reference;
import com.fabbe50.starwarsmod.StarWarsMod;
import com.fabbe50.starwarsmod.client.gui.GUILightSaber;
import com.fabbe50.starwarsmod.registry.KeyBindingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by fabbe on 21/12/2017 - 4:38 AM.
 */
public class KeybindHandler {
    @SubscribeEvent
    public void keybindHandler(InputEvent.KeyInputEvent event) {
        if (KeyBindingRegistry.OPEN_SABER_UI.isKeyDown()) {
            EntityPlayerSP playerSP = Minecraft.getMinecraft().player;
            World world = playerSP.getEntityWorld();


            //playerSP.openGui(StarWarsMod.INSTANCE, Reference.GUI_SABER, world, (int)playerSP.posX, (int)playerSP.posY, (int)playerSP.posZ);
        }
    }
}
