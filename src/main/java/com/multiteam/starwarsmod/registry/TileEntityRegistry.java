package com.multiteam.starwarsmod.registry;

import com.multiteam.starwarsmod.Reference;
import com.multiteam.starwarsmod.tileentities.TileEntitySolarGenerator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class TileEntityRegistry {
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);


    public TileEntityRegistry() {
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
