package com.agadar.bettervanilla.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

import com.agadar.bettervanilla.BetterVanilla;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class CauldronWater extends Block
{
    @SideOnly(Side.CLIENT)
    private IIcon field_150029_a;
    @SideOnly(Side.CLIENT)
    private IIcon field_150028_b;
    @SideOnly(Side.CLIENT)
    private IIcon field_150030_M;
    @SuppressWarnings("unused")
	private static final String __OBFID = "CL_00000213";

    public CauldronWater()
    {
        super(Material.iron);
        this.setHardness(2.0F);
        //this.setBlockName("cauldron.water");
        this.setBlockTextureName("cauldron");
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        return p_149691_1_ == 1 ? this.field_150028_b : (p_149691_1_ == 0 ? this.field_150030_M : this.blockIcon);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_150029_a = p_149651_1_.registerIcon(this.getTextureName() + "_" + "inner");
        this.field_150028_b = p_149651_1_.registerIcon(this.getTextureName() + "_top");
        this.field_150030_M = p_149651_1_.registerIcon(this.getTextureName() + "_" + "bottom");
        this.blockIcon = p_149651_1_.registerIcon(this.getTextureName() + "_side");
    }

    /**
     * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
     * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
     */
    @Override
    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, @SuppressWarnings("rawtypes") List p_149743_6_, Entity p_149743_7_)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3125F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        float f = 0.125F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
        super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
        this.setBlockBoundsForItemRender();
    }

    @SideOnly(Side.CLIENT)
    public static IIcon getCauldronIcon(String p_150026_0_)
    {
        return p_150026_0_.equals("inner") ? BetterVanilla.cauldronWater.field_150029_a : (p_150026_0_.equals("bottom") ? BetterVanilla.cauldronWater.field_150030_M : null);
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    @Override
    public void setBlockBoundsForItemRender()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
    public int getRenderType()
    {
        return BetterVanilla.cauldronWaterRenderer.getRenderId();
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
    @Override
    public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_)
    {
        int l = func_150027_b(p_149670_1_.getBlockMetadata(p_149670_2_, p_149670_3_, p_149670_4_));
        float f = (float)p_149670_3_ + (6.0F + (float)(3 * l)) / 16.0F;

        if (!p_149670_1_.isRemote && p_149670_5_.isBurning() && l > 0 && p_149670_5_.boundingBox.minY <= (double)f)
        {
            p_149670_5_.extinguish();
            this.func_150024_a(p_149670_1_, p_149670_2_, p_149670_3_, p_149670_4_, l - 1);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	if (par1World.isRemote)
        {
            return true;
        }
        
        ItemStack itemstack = par5EntityPlayer.inventory.getCurrentItem();

        if (itemstack == null)
        {
        	return true;
        }

        int i1 = par1World.getBlockMetadata(par2, par3, par4);
        int j1 = func_150027_b(i1);

        if (itemstack.getItem() == Items.water_bucket && j1 < 3)
        {
        	if (!par5EntityPlayer.capabilities.isCreativeMode)
        	{
        		par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, new ItemStack(Items.bucket));
        	}
        	
        	this.func_150024_a(par1World, par2, par3, par4, 3);
        }
        else if (itemstack.getItem() == Items.bucket && j1 >= 3)
        {
        	if (!par5EntityPlayer.capabilities.isCreativeMode)
        	{
        		par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, new ItemStack(Items.water_bucket));
        	}

        	this.func_150024_a(par1World, par2, par3, par4, 0);
        }  
        else if (itemstack.getItem() == Items.lava_bucket && j1 <= 0)
        {
        	if (!par5EntityPlayer.capabilities.isCreativeMode)
        	{
        		par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, new ItemStack(Items.bucket));
        	}

        	par1World.setBlock(par2, par3, par4, BetterVanilla.cauldronLava, 3, 2);
        	par1World.func_147453_f(par2, par3, par4, BetterVanilla.cauldronLava);
        }              
        else if (j1 > 0)
        {
        	if (itemstack.getItem() == Items.glass_bottle)
        	{     		
        		ItemStack itemstack1 = new ItemStack(Items.potionitem, 1, 0);

        		if (!par5EntityPlayer.inventory.addItemStackToInventory(itemstack1))
        		{
        			par1World.spawnEntityInWorld(new EntityItem(par1World, (double)par2 + 0.5D, (double)par3 + 1.5D, (double)par4 + 0.5D, itemstack1));
        		}
        		else if (par5EntityPlayer instanceof EntityPlayerMP)
        		{
        			((EntityPlayerMP)par5EntityPlayer).sendContainerToPlayer(par5EntityPlayer.inventoryContainer);
        		}

        		--itemstack.stackSize;

        		if (itemstack.stackSize <= 0)
        		{
        			par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, (ItemStack)null);
        		}

        		this.func_150024_a(par1World, par2, par3, par4, j1 - 1);        		
        	}
        	else if (itemstack.getItem() instanceof ItemArmor && ((ItemArmor)itemstack.getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.CLOTH)
        	{
        		ItemArmor itemarmor = (ItemArmor)itemstack.getItem();
        		itemarmor.removeColor(itemstack);
        		this.func_150024_a(par1World, par2, par3, par4, j1 - 1); 
        	}
        }
        return true;
    }

    public void func_150024_a(World p_150024_1_, int p_150024_2_, int p_150024_3_, int p_150024_4_, int p_150024_5_)
    {
        p_150024_1_.setBlockMetadataWithNotify(p_150024_2_, p_150024_3_, p_150024_4_, MathHelper.clamp_int(p_150024_5_, 0, 3), 2);
        p_150024_1_.func_147453_f(p_150024_2_, p_150024_3_, p_150024_4_, this);
    }

    /**
     * currently only used by BlockCauldron to incrament meta-data during rain
     */
    @Override
    public void fillWithRain(World p_149639_1_, int p_149639_2_, int p_149639_3_, int p_149639_4_)
    {
        if (p_149639_1_.rand.nextInt(20) == 1)
        {
            int l = p_149639_1_.getBlockMetadata(p_149639_2_, p_149639_3_, p_149639_4_);

            if (l < 3)
            {
                p_149639_1_.setBlockMetadataWithNotify(p_149639_2_, p_149639_3_, p_149639_4_, l + 1, 2);
            }
        }
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(BetterVanilla.cauldronWater);
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
    {
        return Item.getItemFromBlock(BetterVanilla.cauldronWater);
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    @Override
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    @Override
    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        int i1 = p_149736_1_.getBlockMetadata(p_149736_2_, p_149736_3_, p_149736_4_);
        return func_150027_b(i1);
    }

    public static int func_150027_b(int p_150027_0_)
    {
        return p_150027_0_;
    }

    
    @SideOnly(Side.CLIENT)
    public static float getRenderLiquidLevel(int p_150025_0_)
    {
        int j = MathHelper.clamp_int(p_150025_0_, 0, 3);
        return (float)(6 + 3 * j) / 16.0F;
    }
}
