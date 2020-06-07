package com.multiteam.starwarsmod.items;

import com.multiteam.starwarsmod.interfaces.IActivatable;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe on 09/12/2017 - 12:59 AM.
 */
public class ItemLightSaber extends SwordItem implements IActivatable {
    public ItemLightSaber(IItemTier tier, int damage, float attackSpeed, Properties properties) {
        super(tier, damage, attackSpeed, properties);
        this.addPropertyOverride(new ResourceLocation("powered"), new IItemPropertyGetter() {
            @Override
            public float call(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity) {
                return getActivated(itemStack) ? 1 : 0;
            }
        });
    }

    @Override
    public boolean canHarvestBlock(ItemStack stack, BlockState state) {
        return false;
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState blockState, World world, BlockPos pos, PlayerEntity player) {
        return !player.isCreative();
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        if (playerIn.isCrouching()) {
            setActivatedState(stack, !getActivated(stack));
            return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
        } else {
            if (playerIn.getHeldItem(Hand.OFF_HAND).getItem() instanceof ItemLightSaber)
                playerIn.swingArm(Hand.OFF_HAND);
        }
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, PlayerEntity player) {
        if (getActivated(item))
            setActivatedState(item, false);

        return true;
    }

    @Override
    public boolean canHarvestBlock(BlockState blockIn) {
        return false;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        switch (stack.getDamage()) {
            case 0:
                tooltip.add(new StringTextComponent("Shift + Right Click to turn off"));
                break;
            case 1:
                tooltip.add(new StringTextComponent("Shift + Right Click to turn on"));
                break;
        }
    }
}
