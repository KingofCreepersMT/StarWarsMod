package com.multiteam.starwarsmod.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.multiteam.starwarsmod.Reference;
import com.multiteam.starwarsmod.StarWarsMod;
import com.multiteam.starwarsmod.container.ContainerSolarGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScreenSolarGenerator extends ContainerScreen<ContainerSolarGenerator> {
    private final ResourceLocation guiTexture = new ResourceLocation(Reference.MOD_ID, "textures/gui/solar_generator_gui.png");

    public ScreenSolarGenerator(ContainerSolarGenerator screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 10, 10, 0xffffff);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(this.guiTexture);
        int x = this.guiLeft;
        int y = this.guiTop;
        this.blit(x, y, 0, 0, this.xSize, this.ySize);

        if (this.container.isActive()) {
            this.blit(x + 23, y + 29, 182, 63, 18, 18);
        } else {
            this.blit(x + 23, y + 29, 182, 85, 18, 18);
        }

        int i = this.container.getEnergyScaled();
        this.blit(x + 113, y + 27, 2, 168, i, 22);
    }
}
