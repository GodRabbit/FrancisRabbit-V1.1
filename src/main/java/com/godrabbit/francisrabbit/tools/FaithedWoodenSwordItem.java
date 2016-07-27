package com.godrabbit.francisrabbit.tools;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.godrabbit.francisrabbit.armor.FaithEmblemItem;
import com.godrabbit.francisrabbit.items.ModItems;
import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class FaithedWoodenSwordItem extends ItemSword{
	
	static ToolMaterial faithed = EnumHelper.addToolMaterial("faithed", 4, 195, 15.0f, 0.5f, 1);
	public static final String un_name = "faithed_wooden_sword" ;
	private final float attackDamage = 4.5f;
	
	public FaithedWoodenSwordItem() {
		super(faithed);
		this.setUnlocalizedName(un_name);
		this.setCreativeTab(ModItems.ultrarabbitTab);
		this.setMaxDamage(195);
		
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if(!advanced)
		{
			tooltip.add(this.getMaxDamage()-stack.getItemDamage()+"/"+this.getMaxDamage());
		}
		super.addInformation(stack, playerIn, tooltip, advanced);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if(world.getWorldTime() % 2400 == 0 && entity instanceof EntityPlayer && 
				((EntityPlayer)entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST) != null &&
				((EntityPlayer)entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof
				FaithEmblemItem)
		{
			ItemStack chest = ((EntityPlayer)entity).getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if(chest.getTagCompound() != null && chest.getTagCompound().hasKey("faith"))
			{
				int faith = chest.getTagCompound().getInteger("faith");
				if(faith >= 30 && faith < 100 &&  stack.getItemDamage() > 0)
				{
					stack.setItemDamage(stack.getItemDamage()-1);
				}
				if(faith >= 100)
				{
					stack.setItemDamage(0);
				}
			}
		}
		super.onUpdate(stack, world, entity, itemSlot, isSelected);
	}
	
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
        	//replaceModifier(multimap, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, 1);
			replaceModifier(multimap, SharedMonsterAttributes.ATTACK_SPEED, ATTACK_SPEED_MODIFIER, 0.5);
        }

        return multimap;
    }
	
	/**
	 * Replace a modifier in the {@link Multimap} with a copy that's had {@code multiplier} applied to its value.
	 *
	 * @param modifierMultimap The MultiMap
	 * @param attribute        The attribute being modified
	 * @param id               The ID of the modifier
	 * @param multiplier       The multiplier to apply
	 * 
	 * credit to Choonster
	 * https://github.com/Choonster/TestMod3/blob/d7e425a5591f89dc3ea259b8f392ee1825ed1e70/src/main/java/com/choonster/testmod3/item/ItemSlowSword.java
	 */
	private void replaceModifier(Multimap<String, AttributeModifier> modifierMultimap, IAttribute attribute, UUID id, double multiplier) {
		// Get the modifiers for the specified attribute
		final Collection<AttributeModifier> modifiers = modifierMultimap.get(attribute.getAttributeUnlocalizedName());

		// Find the modifier with the specified ID, if any
		final Optional<AttributeModifier> modifierOptional = modifiers.stream().filter(attributeModifier -> attributeModifier.getID().equals(id)).findFirst();

		if (modifierOptional.isPresent()) { // If it exists,
			final AttributeModifier modifier = modifierOptional.get();
			modifiers.remove(modifier); // Remove it
			modifiers.add(new AttributeModifier(modifier.getID(), modifier.getName(), modifier.getAmount() * multiplier, modifier.getOperation())); // Add the new modifier
		}
	}

}
