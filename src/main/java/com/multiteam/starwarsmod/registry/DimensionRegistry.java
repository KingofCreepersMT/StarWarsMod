package com.multiteam.starwarsmod.registry;

import com.multiteam.starwarsmod.Reference;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Created by fabbe on 09/12/2017 - 3:06 AM.
 */
public class DimensionRegistry {
    private static final DeferredRegister<ModDimension> DIMENSIONS = new DeferredRegister<>(ForgeRegistries.MOD_DIMENSIONS, Reference.MOD_ID);

    public DimensionRegistry() {
        DIMENSIONS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
