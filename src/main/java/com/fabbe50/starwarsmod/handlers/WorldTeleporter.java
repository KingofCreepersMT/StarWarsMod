package com.fabbe50.starwarsmod.handlers;

import com.fabbe50.starwarsmod.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import static com.fabbe50.starwarsmod.Config.debugPlatform;

/**
 * Created by fabbe on 09/12/2017 - 3:51 AM.
 */
public class WorldTeleporter extends Teleporter {
    BlockPos pos;
    WorldServer world;

    public WorldTeleporter(WorldServer worldIn, BlockPos pos) {
        super(worldIn);
        this.pos = pos;
        this.world = worldIn;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        if (world.provider.getDimension() != Config.spaceDimId && entityIn instanceof EntityPlayer) {
            pos = new BlockPos(pos.getX(), 280, pos.getZ());
        }
        if (world.provider.getDimension() == Config.spaceDimId) {
            pos = new BlockPos(pos.getX(), -10, pos.getZ());
            if (debugPlatform) {
                int color = world.rand.nextInt(15);
                for (int x = -3; x < 4; x++) {
                    for (int z = -3; z < 4; z++) {
                        world.setBlockState(pos.add(x, 0, z), Blocks.CONCRETE.getStateFromMeta(color));
                    }
                }
                for (EnumFacing facing : EnumFacing.HORIZONTALS)
                    world.setBlockState(pos.up().offset(facing), Blocks.TORCH.getDefaultState());
            }
        }

        entityIn.setLocationAndAngles((double) pos.getX() + 0.5, (double) pos.getY() + 1, (double)pos.getZ() + 0.5, entityIn.rotationYaw, 0.0f);
        entityIn.motionX = 0;
        entityIn.motionY = 0;
        entityIn.motionZ = 0;
    }
}
