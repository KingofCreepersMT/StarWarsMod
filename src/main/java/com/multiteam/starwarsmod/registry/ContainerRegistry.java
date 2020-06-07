package com.multiteam.starwarsmod.registry;

import com.multiteam.starwarsmod.Reference;
import com.multiteam.starwarsmod.StarWarsMod;
import com.multiteam.starwarsmod.client.screens.ScreenSolarGenerator;
import com.multiteam.starwarsmod.container.ContainerSolarGenerator;
import com.multiteam.starwarsmod.tileentities.TileEntitySolarGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class ContainerRegistry {
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Reference.MOD_ID);

    public ContainerRegistry() {
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }



    public static void registerScreens() {
    }
}
