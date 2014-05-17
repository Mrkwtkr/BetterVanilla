package com.agadar.bettervanilla.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LavaBottle extends Item 
{
	public LavaBottle() 
	{
		super();
        this.setMaxStackSize(4);
        this.setCreativeTab(CreativeTabs.tabBrewing);
	}
	
	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par2World.isRemote)
        {
            par3EntityPlayer.setFire(15);
        }

        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
        	--par1ItemStack.stackSize;
        	
        	if (par1ItemStack.stackSize <= 0) return new ItemStack(Items.glass_bottle);
        	
            if (!par3EntityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle)))
	            par3EntityPlayer.dropItem(Items.glass_bottle, 1);
        }

        return par1ItemStack;
    }

	@Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 32;
    }

	@Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.drink;
    }

	@Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}
