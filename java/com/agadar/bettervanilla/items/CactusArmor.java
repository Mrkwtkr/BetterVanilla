package com.agadar.bettervanilla.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class CactusArmor extends ItemArmor {
	
	public CactusArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k) {
		super(i, enumarmormaterial, j, k);
	}
	
	@Override
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, int layer) {
		return "bettervanilla:textures/models/armor/cactus_layer_" + layer + ".png";
	}
	
	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return par2ItemStack.itemID == Block.cactus.blockID;
    }
}