package net.steakboi.strongermobs;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.random.Random;
import net.steakboi.strongermobs.config.ModConfigs;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.minecraft.enchantment.Enchantments.*;

public class StrongerMobsMod implements ModInitializer {
	public static final String MOD_ID = "strongermobs";

	@Override
	public void onInitialize() {
		//CONFIG
		ModConfigs.registerConfigs();
	}
	public enum PiglinGearType {
		NETHERITE,
		GOLD
	}

	public static <T> T pickWeighted (Map<T, Integer> weightMap, Random random) {
		int total = 0;
		for (T t : weightMap.keySet()){
			total += weightMap.get(t);
		}
		if (total <= 0) {
			List<T> list = new ArrayList<>(weightMap.keySet());
			Collections.shuffle(list);
			return list.getFirst();
		}
		int select = random.nextInt(total);
		for (T t : weightMap.keySet()){
			if (select < weightMap.get(t)) {
				return t;
			}
			select = select - weightMap.get(t);
		}
		return null;
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
		int gold_sword_weight = max(ModConfigs.NetherGoldSwordWeight,0);
		int netherite_sword_weight = max(ModConfigs.NetherNetheriteSwordWeight,0);
		int min_sharpness = max(ModConfigs.NetherMaxSharpnessLevel,0);
		int max_sharpness = max(ModConfigs.NetherMinSharpnessLevel,min_sharpness);
		int sharpness_level = min_sharpness;
		if (max_sharpness > min_sharpness) {
			sharpness_level += 1;
			sharpness_level += random.nextInt(max_sharpness - min_sharpness);
		}
		int fireAspectChance = max(min(ModConfigs.NetherFireAspectChance, 100), 0);
		int knockbackChance = max(min(ModConfigs.NetherKnockbackChance, 100), 0);

		Map<Item, Integer> weightmap = new HashMap<>() {{
			put(Items.NETHERITE_SWORD, netherite_sword_weight);
			put(Items.GOLDEN_SWORD, gold_sword_weight);
		}};
		Item weaponItem = pickWeighted(weightmap, random);
		ItemStack weapon = new ItemStack(weaponItem);

		weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(SHARPNESS).get(), sharpness_level);
		if (random.nextInt(100) < fireAspectChance) weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(FIRE_ASPECT).get(),2);
		if (random.nextInt(100) < knockbackChance) weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(KNOCKBACK).get(),2);
		return weapon;
	}

	public static ItemStack getSkeletonBow(Random random, MobEntity mobEntity) {
		int min_power = max(ModConfigs.OverworldSkeletonMinPowerLevel,0);
		int max_power = max(ModConfigs.OverworldSkeletonMaxPowerLevel,min_power);
		int power_level = min_power;
		if (max_power > min_power) {
			power_level += 1;
			power_level += random.nextInt(max_power - min_power);
		}
		int flameChance = max(min(ModConfigs.OverworldSkeletonFlameChance, 100), 0);
		int punchChance = max(min(ModConfigs.OverworldSkeletonPunchChance, 100), 0);

		ItemStack bow = new ItemStack(Items.BOW);
		bow.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(POWER).get(), power_level);
		if (random.nextInt(100) < punchChance) bow.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PUNCH).get(), random.nextInt(2)+1);
		if (random.nextInt(100) < flameChance) bow.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(FLAME).get(), 1);
		return bow;
	}

	public static ItemStack getZombieSword(Random random, MobEntity mobEntity) {
		int gold_sword_weight = max(ModConfigs.OverworldZombieGoldSwordWeight,0);
		int iron_sword_weight = max(ModConfigs.OverworldZombieIronSwordWeight,0);
		int diamond_sword_weight = max(ModConfigs.OverworldZombieDiamondSwordWeight,0);
		int netherite_sword_weight = max(ModConfigs.OverworldZombieNetheriteSwordWeight,0);
		int min_sharpness = max(ModConfigs.OverworldZombieMinSharpnessLevel,0);
		int max_sharpness = max(ModConfigs.OverworldZombieMaxSharpnessLevel,min_sharpness);
		int sharpness_level = min_sharpness;
		if (max_sharpness > min_sharpness) {
			sharpness_level += 1;
			sharpness_level += random.nextInt(max_sharpness - min_sharpness);
		}
		int fireAspectChance = max(min(ModConfigs.OverworldZombieFireAspectChance, 100), 0);
		int knockbackChance = max(min(ModConfigs.OverworldZombieKnockbackChance, 100), 0);
		int swordChance = max(min(ModConfigs.OverworldZombieZombieSwordChance, 100), 0);

		if (random.nextInt(100) <  swordChance ) {
			Map<Item, Integer> weightmap = new HashMap<>() {{
				put(Items.NETHERITE_SWORD, netherite_sword_weight);
				put(Items.GOLDEN_SWORD, gold_sword_weight);
				put(Items.DIAMOND_SWORD, diamond_sword_weight);
				put(Items.IRON_SWORD, iron_sword_weight);
			}};
			Item weaponItem = pickWeighted(weightmap, random);
			ItemStack weapon = new ItemStack(weaponItem);

			weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(SHARPNESS).get(), sharpness_level);
			if (random.nextInt(100) < fireAspectChance)
				weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(FIRE_ASPECT).get(), 2);
			if (random.nextInt(100) < knockbackChance)
				weapon.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(KNOCKBACK).get(), 2);
			return weapon;
		}
		return null;
	}

	public static void EquipPiglinArmor(Random random, MobEntity mobEntity){
		int maxArmor = max(min(4, ModConfigs.NetherMaxArmorPieces), 0);
		int minArmor = max(min(maxArmor, ModConfigs.NetherMinArmorPieces), 0);
		int armorChance = max(min(ModConfigs.NetherArmorChancePerSlotPercentage, 100), 0);

		int armorToEquip = minArmor;
		for (int i = 0; i < maxArmor- minArmor; i++){
			if (random.nextInt(100) <armorChance) armorToEquip += 1;
		}

		List<EquipmentSlot> EquipmentSlots = Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.FEET);
		Collections.shuffle(EquipmentSlots);

		for (int i = 0; i < armorToEquip; i++) {
			equipPiglinArmor(EquipmentSlots.get(i), random, mobEntity);
		}
	}

	private static void equipPiglinArmor(EquipmentSlot equipmentSlot, Random random, MobEntity mobEntity) {
		int netherite_armor_weight = max(0, ModConfigs.NetherNetheriteArmorWeight);
		int gold_armor_weight = max(0, ModConfigs.NetherGoldArmorWeight);
		int min_protection = max(ModConfigs.NetherMinProtectionLevel,0);
		int max_protection = max(ModConfigs.NetherMaxProtectionLevel,min_protection);
		int protection_level = min_protection;
		if (max_protection > min_protection) {
			protection_level += 1;
			protection_level += random.nextInt(max_protection - min_protection);
		}

		Map<Item, Integer> weightmap = new HashMap<>() {{
			put(PiglinArmorMap.get(StrongerMobsMod.PiglinGearType.NETHERITE).get(equipmentSlot), netherite_armor_weight);
			put(PiglinArmorMap.get(StrongerMobsMod.PiglinGearType.GOLD).get(equipmentSlot), gold_armor_weight);
		}};
		Item armorItem = pickWeighted(weightmap, random);

		ItemStack armorPiece = new ItemStack(armorItem);
		armorPiece.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PROTECTION).get(), protection_level);
		mobEntity.equipStack(equipmentSlot, armorPiece);

	}

	public static void EquipArmor(Random random, MobEntity mobEntity){
		int leather_armor_weight = max(0, ModConfigs.OverworldLeatherArmorWeight);
		int gold_armor_weight = max(0, ModConfigs.OverworldGoldArmorWeight);
		int chainmail_armor_weight = max(0, ModConfigs.OverworldChainmailArmorWeight);
		int iron_armor_weight = max(0, ModConfigs.OverworldIronArmorWeight);
		int diamond_armor_weight = max(0, ModConfigs.OverworldDiamondArmorWeight);
		int netherite_armor_weight = max(0, ModConfigs.OverworldNetheriteArmorWeight);
		int maxArmor = max(min(4, ModConfigs.OverworldMaxArmorPieces), 0);
		int minArmor = max(min(maxArmor, ModConfigs.OverworldMinArmorPieces), 0);
		int armorChance = max(min(ModConfigs.OverworldArmorChancePerSlotPercentage, 100), 0);
		int min_protection = max(ModConfigs.OverworldMinProtectionLevel,0);
		int max_protection = max(ModConfigs.OverworldMaxProtectionLevel,min_protection);
		int protection_level = min_protection;
		if (max_protection > min_protection) {
			protection_level += 1;
			protection_level += random.nextInt(max_protection - min_protection);
		}
		int armorToEquip = minArmor;
		for (int i = 0; i < (maxArmor - minArmor); i++){
			if (random.nextInt(100) < armorChance) armorToEquip += 1;
		}

		Map<Integer, Integer> weightmap = new HashMap<>() {{
			put(0, leather_armor_weight);
			put(1, gold_armor_weight);
			put(2, chainmail_armor_weight);
			put(3, iron_armor_weight);
			put(4, diamond_armor_weight);
			put(5, netherite_armor_weight);
		}};
		int quality = pickWeighted(weightmap, random);

		List<EquipmentSlot> EquipmentSlots = Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.FEET);
		Collections.shuffle(EquipmentSlots);

		for (int i = 0; i < armorToEquip; i++) {
			EquipArmorSlot(mobEntity, quality, EquipmentSlots.get(i), protection_level);
		}
	}
	public static void EquipArmorSlot(MobEntity mobEntity, int quality, EquipmentSlot equipmentSlot, int protectionLevel){
		if (equipmentSlot.getType() == EquipmentSlot.Type.HUMANOID_ARMOR) {
			ItemStack itemStack = mobEntity.getEquippedStack(equipmentSlot);
			if (itemStack.isEmpty()) {
				Item item = getEquipmentForSlot(equipmentSlot, quality);
				if (item != null) {
					ItemStack armor_piece = new ItemStack(item);
					armor_piece.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PROTECTION).get(), protectionLevel);
					mobEntity.equipStack(equipmentSlot, armor_piece);
				}
			}
		}
	}

	public static Item getEquipmentForSlot(EquipmentSlot equipmentSlot, int quality) {
		return getArmorMap(equipmentSlot, quality);
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
}