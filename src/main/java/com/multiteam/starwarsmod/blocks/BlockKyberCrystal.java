package com.multiteam.starwarsmod.blocks;

import com.multiteam.starwarsmod.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BlockKyberCrystal extends Block {
    public BlockKyberCrystal() {
        super(Block.Properties.create(Material.GLASS).harvestTool(ToolType.PICKAXE).notSolid().variableOpacity());
    }
}
