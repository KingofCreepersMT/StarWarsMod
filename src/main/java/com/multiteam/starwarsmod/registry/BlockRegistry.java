package com.multiteam.starwarsmod.registry;

import com.multiteam.starwarsmod.Reference;
import com.multiteam.starwarsmod.StarWarsMod;
import com.multiteam.starwarsmod.blocks.BlockCoalGenerator;
import com.multiteam.starwarsmod.blocks.BlockKyberCrystal;
import com.multiteam.starwarsmod.blocks.BlockSolarGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabbe on 09/12/2017 - 11:28 AM.
 */
public class BlockRegistry {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MOD_ID);


    public BlockRegistry() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void fixRenders() {
        RenderTypeLookup.setRenderLayer(KYBER_CRYSTAL_BLUE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(KYBER_CRYSTAL_GREEN.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(KYBER_CRYSTAL_PURPLE.get(), RenderType.getTranslucent());
        RenderTypeLookup.setRenderLayer(KYBER_CRYSTAL_RED.get(), RenderType.getTranslucent());
    }
}
