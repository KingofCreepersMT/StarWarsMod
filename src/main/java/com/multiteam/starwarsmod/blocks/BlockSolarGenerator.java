package com.multiteam.starwarsmod.blocks;

import com.multiteam.starwarsmod.Reference;
import com.multiteam.starwarsmod.tileentities.TileEntitySolarGenerator;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockSolarGenerator extends Block {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
    public static final IntegerProperty POWER = BlockStateProperties.POWER_0_15;
    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public BlockSolarGenerator() {
        super(Block.Properties.create(Material.IRON, MaterialColor.BLUE).harvestTool(ToolType.PICKAXE).notSolid().variableOpacity());
        this.setDefaultState(this.getStateContainer().getBaseState().with(FACING, Direction.NORTH).with(POWER, 0).with(ACTIVE, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(ACTIVE, false).with(POWER, 0);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(POWER);
        builder.add(ACTIVE);
    }

    @Override
    public BlockRenderType getRenderType(BlockState p_149645_1_) {
        return BlockRenderType.MODEL;
    }

    @Override
    public int getWeakPower(BlockState state, IBlockReader reader, BlockPos pos, Direction direction) {
        return state.get(POWER);
    }

    @Override
    public boolean canProvidePower(BlockState p_149744_1_) {
        return true;
    }

    public static void updatePower(BlockState state, World world, BlockPos pos) {
        if (world.dimension.hasSkyLight()) {
            int skylight = world.getLightFor(LightType.SKY, pos) - world.getSkylightSubtracted();
            float skyangle = world.getCelestialAngleRadians(1.0f);
            if (skylight > 0) {
                float power = skyangle < 3.1415927F ? 0.0F : 6.2831855F;
                skyangle += (power - skyangle) * 0.2F;
                skylight = Math.round((float) skylight * MathHelper.cos(skyangle));
            }
            skylight = MathHelper.clamp(skylight, 0, 15);
            if ((Integer)state.get(POWER) != skylight) {
                world.setBlockState(pos, (BlockState)state.with(POWER, skylight), 3);
                if (skylight > 0) {
                    setActive(world, pos, true);
                } else
                    setActive(world, pos, false);
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof INamedContainerProvider) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
            return ActionResultType.SUCCESS;
        }
        return super.onBlockActivated(state, world, pos, player, hand, trace);
    }

    @Override
    public int tickRate(IWorldReader p_149738_1_) {
        return 1;
    }

    public static void setActive(World world, BlockPos pos, boolean active) {
        world.setBlockState(pos, world.getBlockState(pos).with(ACTIVE, active));
    }

    public static boolean getActive(World world, BlockPos pos) {
        return world.getBlockState(pos).get(ACTIVE);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntitySolarGenerator();
    }
}
