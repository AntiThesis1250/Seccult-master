package testmod.seccult.items.armor.Ocean;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.event.world.WorldEvent;
import testmod.seccult.ModReclection;
import testmod.seccult.Seccult;
import testmod.seccult.init.ModItems;
import testmod.seccult.items.armor.OceanArmor;
import testmod.seccult.items.armor.MagickArmor;
import testmod.seccult.items.armor.MagickArmor.CoreType;

public class OceanHelmet extends OceanArmor{

	private int air;
	
	public OceanHelmet(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
		setMagickAttribute(0.1F, 1, 1F);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		
		if(!(entityIn instanceof EntityPlayer))
			return;
		
		
		EntityPlayer player = (EntityPlayer)entityIn;

		ItemStack itemStack = stack;
		
		if(itemStack.getItem() == ModItems.OCEAN_HELMET)
		{
			if (EnchantmentHelper.getEnchantmentLevel(Enchantments.RESPIRATION, itemStack) < 3) {
	        	itemStack.addEnchantment(Enchantments.RESPIRATION, 3);
	        }
		}
		
		if(!hasArmorSetItem(player)) 
		{
			air = 300;
			return;
		}

		player.setAir(0);
		
		//Seccult.proxy.setOutOfWater(player);
		
		/*if(player.isOverWater())
		{
			player.capabilities.allowFlying = true;
			player.capabilities.isFlying = true;
		}
		else if(!MagickArmor.hasFlyingCore(player) || !player.isCreative())
		{
			player.capabilities.allowFlying = false;
			player.capabilities.isFlying = false;
		}*/
		
		if(player.world.handleMaterialAcceleration(player.getEntityBoundingBox().grow(0.0D, -1.0D, 0.0D).offset(0, 1, 0).shrink(0.001D), Material.WATER, player))
		player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 201));
		
		/*if(player.capabilities.isCreativeMode)
			return;

        if (air <= -20)
        {
            for (int i = 0; i < 8; ++i)
            {
                float f2 = Seccult.rand.nextFloat() - Seccult.rand.nextFloat();
                float f = Seccult.rand.nextFloat() - Seccult.rand.nextFloat();
                float f1 = Seccult.rand.nextFloat() - Seccult.rand.nextFloat();
                player.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, player.posX + (double)f2, player.posY + (double)f, player.posZ + (double)f1, player.motionX, player.motionY, player.motionZ);
            }
            
            player.attackEntityFrom(DamageSource.DROWN, 2.0F);
            air = 1;
        }
		*/
		
		if(!player.isInWater())
		{
			for (int i = 0; i < 8; ++i)
            {
                float f2 = Seccult.rand.nextFloat() - Seccult.rand.nextFloat();
                float f = Seccult.rand.nextFloat() - Seccult.rand.nextFloat();
                float f1 = Seccult.rand.nextFloat() - Seccult.rand.nextFloat();
                player.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, player.posX + (double)f2, player.posY + (double)f, player.posZ + (double)f1, player.motionX, player.motionY, player.motionZ);
            }
			this.air = 300;
			player.setAir(0);
		}
		else
		{
			player.fallDistance = 0;
            if (!player.canBreatheUnderwater() && !player.isPotionActive(MobEffects.WATER_BREATHING) && !player.capabilities.disableDamage)
            {
            	air--;
            }
		}
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);
	}
	
	public int getAir()
	{
		return this.air;
	}
}
