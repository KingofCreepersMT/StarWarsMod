package com.multiteam.starwarsmod.tileentities;

import com.multiteam.starwarsmod.StarWarsMod;
import com.multiteam.starwarsmod.blocks.BlockSolarGenerator;
import com.multiteam.starwarsmod.container.ContainerSolarGenerator;
import com.multiteam.starwarsmod.registry.TileEntityRegistry;
import com.multiteam.starwarsmod.utils.SWEnergyStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class TileEntitySolarGenerator extends TileEntity implements ISidedInventory, ITickableTileEntity, INamedContainerProvider {
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(this::createEnergy);
    private boolean isActive = false;
    public static final int MAX_POWER = 4000;

    protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);

    public TileEntitySolarGenerator() {
        super(TileEntityRegistry.SOLAR_GENERATOR.get());
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isRemote) {
            if (this.world.getGameTime() % 20L == 0L) {
                BlockState state = this.getBlockState();
                Block block = state.getBlock();
                if (block instanceof BlockSolarGenerator) {
                    BlockSolarGenerator.updatePower(state, this.world, this.pos);
                    isActive = BlockSolarGenerator.getActive(this.world, this.pos);
                }
            }
            if (isActive) {
                generatePower();
            }
            sendEnergy();
        }
    }

    private IEnergyStorage createEnergy() {
        return new SWEnergyStorage(MAX_POWER, 0);
    }

    private void sendEnergy() {
        energy.ifPresent(energy -> {
            AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
            if (capacity.get() > 0) {
                for (Direction direction : Direction.values()) {
                    TileEntity te = world.getTileEntity(pos.offset(direction));
                    if (te != null) {
                        boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                    if (handler.canReceive()) {
                                        int received = handler.receiveEnergy(Math.min(capacity.get(), 20), false);
                                        capacity.addAndGet(-received);
                                        ((SWEnergyStorage) energy).consumePower(received);
                                        markDirty();
                                        return capacity.get() > 0;
                                    } else {
                                        return true;
                                    }
                                }
                        ).orElse(true);
                        if (!doContinue) {
                            return;
                        }
                    }
                }
            }
        });
    }

    private void generatePower() {
        energy.ifPresent(e -> ((SWEnergyStorage)e).generatePower(20));
        markDirty();
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public void read(CompoundNBT compound) {
        this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, items);
        CompoundNBT energyTag = compound.getCompound("energy");
        energy.ifPresent(h -> ((INBTSerializable<CompoundNBT>) h).deserializeNBT(energyTag));
        this.isActive = compound.getBoolean("active");
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ItemStackHelper.saveAllItems(compound, items);
        energy.ifPresent(h -> {
            CompoundNBT tag = ((INBTSerializable<CompoundNBT>) h).serializeNBT();
            compound.put("energy", tag);
        });
        compound.putBoolean("active", isActive);
        return super.write(compound);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }

        return super.getCapability(cap, side);
    }

    public boolean canInteractWith(PlayerEntity player) {
        BlockPos pos1 = pos.add(0.5D, 0.5D, 0.5D);
        return !isRemoved() && player.getDistanceSq(pos1.getX(), pos1.getY(), pos1.getZ()) <= 64D;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.solar_generator");
    }

    @Nullable
    @Override
    public Container createMenu(int id, PlayerInventory inv, PlayerEntity player) {
        return new ContainerSolarGenerator(id, world, pos, inv, player);
    }

    public int getSizeInventory() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.items, index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.items, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        ItemStack itemstack = this.items.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.items.set(index, stack);
        if (stack.getCount() > this.getInventoryStackLimit()) {
            stack.setCount(this.getInventoryStackLimit());
        }

        if (index == 0 && !flag) {
            markDirty();
        }
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        if (this.world.getTileEntity(this.pos) != this) {
            return false;
        } else {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
        return itemStackIn.getCapability(CapabilityEnergy.ENERGY).isPresent();
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        if (direction == Direction.DOWN && index == 0) {
            IEnergyStorage storage = stack.getCapability(CapabilityEnergy.ENERGY).orElse(null);
            return storage.getEnergyStored() == storage.getMaxEnergyStored();
        }
        return false;
    }

    @Override
    public void clear() {
        this.items.clear();
    }
}
