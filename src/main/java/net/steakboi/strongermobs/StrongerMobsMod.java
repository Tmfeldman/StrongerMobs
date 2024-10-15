package net.steakboi.strongermobs;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;

import java.util.*;

import static net.minecraft.enchantment.Enchantments.*;
import static net.minecraft.entity.mob.MobEntity.getEquipmentForSlot;

public class StrongerMobsMod implements ModInitializer {
	public static final String MOD_ID = "strongermobs";

	@Override
	public void onInitialize() {

	}
	public enum PiglinGearType {
		NETHERITE,
		GOLD
	}

	public static Map<PiglinGearType, Map<EquipmentSlot, Item>> PiglinArmorMap = new HashMap<>(){{
		Map<EquipmentSlot, Item> Netherite = new HashMap<>(){{
			put(EquipmentSlot.HEAD, Items.NETHERITE_HELMET);
			put(EquipmentSlot.CHEST, Items.NETHERITE_CHESTPLATE);
			put(EquipmentSlot.LEGS, Items.NETHERITE_LEGGINGS);
			put(EquipmentSlot.FEET, Items.NETHERITE_BOOTS);
		}};
		Map<EquipmentSlot, Item> Gold = new HashMap<>(){{
			put(EquipmentSlot.HEAD, Items.GOLDEN_HELMET);
			put(EquipmentSlot.CHEST, Items.GOLDEN_CHESTPLATE);
			put(EquipmentSlot.LEGS, Items.GOLDEN_LEGGINGS);
			put(EquipmentSlot.FEET, Items.GOLDEN_BOOTS);
		}};
		put(PiglinGearType.NETHERITE, Netherite);
		put(PiglinGearType.GOLD, Gold);
	}};

	public static ItemStack getPiglinSword(Random random, MobEntity mobEntity) {
		ItemStack weapon;
		if (random.nextFloat() >= .65F){
			weapon = new ItemStack(Items.NETHERITE_SWORD);
		} else {
			weapon = new ItemStack(Items.GOLDEN_SWORD);
		}
		weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(SHARPNESS).get(), random.nextInt(4)+2);
		if (random.nextInt(7) == 1) weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(FIRE_ASPECT).get(), random.nextInt(2)+1);
		if (random.nextInt(7) == 1) weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(KNOCKBACK).get(), random.nextInt(2)+1);
		return weapon;
	}

	public static void EquipPiglinArmor(Random random, MobEntity mobEntity){
		equipPiglinArmor(EquipmentSlot.HEAD, random, mobEntity);
		equipPiglinArmor(EquipmentSlot.LEGS, random, mobEntity);
		equipPiglinArmor(EquipmentSlot.CHEST, random, mobEntity);
		equipPiglinArmor(EquipmentSlot.FEET, random, mobEntity);
	}

	private static void equipPiglinArmor(EquipmentSlot equipmentSlot, Random random, MobEntity mobEntity) {
		if (random.nextFloat() < 0.8F) {
			ItemStack armorPiece;
			if (random.nextFloat() < 0.65F) {
				armorPiece = new ItemStack(PiglinArmorMap.get(StrongerMobsMod.PiglinGearType.GOLD).get(equipmentSlot));
			} else {
				armorPiece = new ItemStack(PiglinArmorMap.get(StrongerMobsMod.PiglinGearType.NETHERITE).get(equipmentSlot));
			}
			if (random.nextInt(2) == 1) {
				if (random.nextInt(2) == 1) {
					armorPiece.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PROTECTION).get(), random.nextInt(4) + 2);
				} else {
					armorPiece.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PROJECTILE_PROTECTION).get(), random.nextInt(4) + 2);
				}
			}
			if (equipmentSlot == EquipmentSlot.FEET) {
				armorPiece.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(SOUL_SPEED).get(), random.nextInt(3) + 1);
			}
			mobEntity.equipStack(equipmentSlot, armorPiece);
		}
	}

	public static void EquipArmor(Random random, LocalDifficulty localDifficulty, MobEntity mobEntity){
		if (localDifficulty.getClampedLocalDifficulty() > 0) {
			int quality = curveInt(random.nextInt(101));
			List<EquipmentSlot> equipmentSlots = new java.util.ArrayList<>(Arrays.stream(EquipmentSlot.values()).toList());
			Collections.shuffle(equipmentSlots);

			int i = 1;
			int armor_limit = random.nextInt(7);
			for (EquipmentSlot equipmentSlot : equipmentSlots) {
				if (equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
					ItemStack itemStack = mobEntity.getEquippedStack(equipmentSlot);
					if (i >= armor_limit) {
						break;
					}
					i++;
					if (itemStack.isEmpty()) {
						Item item = getEquipmentForSlot(equipmentSlot, quality);
						if (item != null) {
							ItemStack armor_piece = new ItemStack(item);
							if (random.nextInt(2) == 1) {
								if (random.nextInt(2) == 1) {
									armor_piece.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PROTECTION).get(), random.nextInt(4) + 2);
								} else {
									armor_piece.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PROJECTILE_PROTECTION).get(), random.nextInt(4) + 2);
								}
							}
							mobEntity.equipStack(equipmentSlot, armor_piece);
						}
					}
				}
			}
		}
	}

	public static Item getArmorMap(EquipmentSlot equipmentSlot, int Int){
		if (ArmorMap.containsKey(equipmentSlot) && ArmorMap.get(equipmentSlot).containsKey(Int)){
			return ArmorMap.get(equipmentSlot).get(Int);
		}
		return null;
	}
	private static final Map<EquipmentSlot, Map<Integer, Item>> ArmorMap = new HashMap<>(){{
		Map<Integer, Item> BootsMap = new HashMap<>(){{
			put(0, Items.LEATHER_BOOTS);
			put(1, Items.GOLDEN_BOOTS);
			put(2, Items.CHAINMAIL_BOOTS);
			put(3, Items.IRON_BOOTS);
			put(4, Items.DIAMOND_BOOTS);
			put(5, Items.NETHERITE_BOOTS);
		}};
		Map<Integer, Item> LegsMap = new HashMap<>(){{
			put(0, Items.LEATHER_LEGGINGS);
			put(1, Items.GOLDEN_LEGGINGS);
			put(2, Items.CHAINMAIL_LEGGINGS);
			put(3, Items.IRON_LEGGINGS);
			put(4, Items.DIAMOND_LEGGINGS);
			put(5, Items.NETHERITE_LEGGINGS);
		}};
		Map<Integer, Item> ChestMap = new HashMap<>(){{
			put(0, Items.LEATHER_CHESTPLATE);
			put(1, Items.GOLDEN_CHESTPLATE);
			put(2, Items.CHAINMAIL_CHESTPLATE);
			put(3, Items.IRON_CHESTPLATE);
			put(4, Items.DIAMOND_CHESTPLATE);
			put(5, Items.NETHERITE_CHESTPLATE);
		}};
		Map<Integer, Item> HeadMap = new HashMap<>(){{
			put(0, Items.LEATHER_HELMET);
			put(1, Items.GOLDEN_HELMET);
			put(2, Items.CHAINMAIL_HELMET);
			put(3, Items.IRON_HELMET);
			put(4, Items.DIAMOND_HELMET);
			put(5, Items.NETHERITE_HELMET);
		}};

		put(EquipmentSlot.FEET, BootsMap);
		put(EquipmentSlot.LEGS, LegsMap);
		put(EquipmentSlot.CHEST, ChestMap);
		put(EquipmentSlot.HEAD, HeadMap);
	}};

	public static int curveInt(int i){
		if (i < 5) return 0;
		if (i < 10) return 1;
		if (i < 20) return 2;
		if (i < 30) return 3;
		if (i < 70) return 4;
		return 5;
	}
}