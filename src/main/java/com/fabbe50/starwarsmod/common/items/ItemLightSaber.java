package com.fabbe50.starwarsmod.common.items;

import com.fabbe50.starwarsmod.Reference;
import com.google.common.collect.Multimap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by fabbe on 09/12/2017 - 12:59 AM.
 */
public class ItemLightSaber extends ItemBase {
    private final float attackDamage;
    private final Item.ToolMaterial material;

    public ItemLightSaber(String name, Item.ToolMaterial material) {
        super(name);
        this.material = material;
        this.maxStackSize = 1;
        this.attackDamage = 7 + material.getAttackDamage();
        this.setHasSubtypes(true);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.isSneaking()) {
            if (playerIn.getHeldItem(handIn).getItemDamage() == 0)
                playerIn.getHeldItem(handIn).setItemDamage(1);
            else
                playerIn.getHeldItem(handIn).setItemDamage(0);

            return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
        }
        return ActionResult.newResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    public float getAttackDamage() {
        return this.material.getAttackDamage();
    }

    public float getDestroySpeed() {
        return 0;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (stack.getMetadata() == 0) {
            target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 8);
            stack.damageItem(1, attacker);
        } else
            stack.damageItem(3, attacker);

        return true;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
        if ((double)state.getBlockHardness(worldIn, pos) != 0.0D) {
            stack.damageItem(2, entityLiving);
        }
        return true;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
        item.setItemDamage(1);
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean isFull3D() {
        return true;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this, 1, 0));
            items.add(new ItemStack(this, 1, 1));
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        switch (stack.getMetadata()) {
            case 0:
                tooltip.add("Shift + Right Click to turn off");
                tooltip.add("");
                tooltip.add("When in main hand:");
                tooltip.add(" " + Math.round(this.attackDamage) + " Attack Damage");
                break;
            case 1:
                stack.setTranslatableName("item." + Reference.MOD_ID + ":light_saber_off.name");
                tooltip.add("Shift + Right Click to turn on");
                break;
        }
    }
}
