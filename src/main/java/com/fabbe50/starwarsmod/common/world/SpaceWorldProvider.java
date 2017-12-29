package com.fabbe50.starwarsmod.common.world;

import com.fabbe50.starwarsmod.registry.DimensionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.client.IRenderHandler;

import javax.annotation.Nullable;

/**
 * Created by fabbe on 09/12/2017 - 3:05 AM.
 */
public class SpaceWorldProvider extends WorldProvider {
    @Override
    public DimensionType getDimensionType() {
        return DimensionRegistry.SPACE_DIM;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new SpaceChunkGenerator(world);
    }

    @Override
    public boolean canRespawnHere() {
        return false;
    }

    @Override
    public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
        return new Vec3d(0, 0, 0);
    }

    @Override
    public long getWorldTime() {
        return 18000;
    }

    @Override
    public boolean isDaytime() {
        return true;
    }

    @Nullable
    @Override
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return null;
    }

    @Override
    public int getRespawnDimension(EntityPlayerMP player) {
        return 0;
    }

    @Override
    public void setCloudRenderer(IRenderHandler renderer) {
        super.setCloudRenderer(null);
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    @Override
    public boolean doesWaterVaporize() {
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public void setSkyRenderer(IRenderHandler skyRenderer) {
        super.setSkyRenderer(null);
    }
}
