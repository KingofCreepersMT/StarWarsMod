package com.fabbe50.starwarsmod.registry;

import com.fabbe50.starwarsmod.Config;
import com.fabbe50.starwarsmod.common.world.SpaceWorldProvider;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

/**
 * Created by fabbe on 09/12/2017 - 3:06 AM.
 */
public class DimensionRegistry {
    public static DimensionType SPACE_DIM;

    public static void init() {
        SPACE_DIM = DimensionType.register("starwarsmod", "space", Config.spaceDimId, SpaceWorldProvider.class, false);


        DimensionManager.registerDimension(Config.spaceDimId, SPACE_DIM);
    }
}
