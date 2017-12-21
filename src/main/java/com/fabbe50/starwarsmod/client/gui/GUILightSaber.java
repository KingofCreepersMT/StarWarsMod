package com.fabbe50.starwarsmod.client.gui;

import com.fabbe50.starwarsmod.Reference;
import com.fabbe50.starwarsmod.common.containers.ContainerLightSaber;
import com.google.common.base.Preconditions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;

/**
 * Created by fabbe on 21/12/2017 - 4:52 AM.
 */
public class GUILightSaber extends GuiContainer {
    private ContainerLightSaber container;

    public GUILightSaber(ContainerLightSaber lightSaber) {
        super(lightSaber);

        Preconditions.checkNotNull(lightSaber, "lightSaber cannot be null");

        container = lightSaber;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);

        TextureManager textureManager = mc.getTextureManager();
        textureManager.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/saber_gui.png"));
        drawTexturedModalRect((width / 2) - 88, (height / 2) - 83, 0, 0, 176, 166);

        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }

    @Override
    public void initGui() {

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
