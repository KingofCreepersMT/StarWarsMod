package com.multiteam.starwarsmod.container;

import com.multiteam.starwarsmod.StarWarsGroup;
import com.multiteam.starwarsmod.StarWarsMod;
import com.multiteam.starwarsmod.container.slots.SlotCharging;
import com.multiteam.starwarsmod.registry.ContainerRegistry;
import com.multiteam.starwarsmod.tileentities.TileEntitySolarGenerator;
import com.multiteam.starwarsmod.utils.SWEnergyStorage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.IntReferenceHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;

public class ContainerSolarGenerator extends Container {
    private final IInventory solarInventory;
    private final TileEntitySolarGenerator tileEntity;

    public ContainerSolarGenerator(int id, World world, BlockPos pos, @Nonnull PlayerInventory inv, PlayerEntity player) {
        super(ContainerRegistry.SOLAR_GENERATOR.get(), id);
        this.tileEntity = (TileEntitySolarGenerator) world.getTileEntity(pos);
        this.solarInventory = tileEntity;
        this.addSlot(new SlotCharging(solarInventory, 0, 80, 30));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(inv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(inv, k, 8 + k * 18, 142));
        }

        trackInt(new IntReferenceHolder() {
            @Override
            public int get() {
                return getEnergy();
            }

            @Override
            public void set(int value) {
                tileEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> ((SWEnergyStorage)h).setEnergy(value));
            }
        });
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return this.solarInventory.isUsableByPlayer(playerIn);
    }

    public int getEnergy() {
        return tileEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    @OnlyIn(Dist.CLIENT)
    public int getEnergyScaled() {
        return getEnergy() * 50 / 4000;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isActive() {
        return StarWarsMod.proxy.getClientWorld().getLightFor(LightType.SKY, tileEntity.getPos()) > 0;
    }
}
