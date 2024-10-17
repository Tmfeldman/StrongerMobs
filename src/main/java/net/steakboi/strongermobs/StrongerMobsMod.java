package net.steakboi.strongermobs;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.random.Random;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.minecraft.enchantment.Enchantments.*;

public class StrongerMobsMod implements ModInitializer {
	public static final String MOD_ID = "strongermobs";
	public static final net.steakboi.strongermobs.StrongerMobsConfig CONFIG = net.steakboi.strongermobs.StrongerMobsConfig.createAndLoad();

	@Override
	public void onInitialize() {
		//CONFIG

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
		int gold_sword_weight = max(CONFIG.nestedNetherMobOptions.goldSwordWeight(),0);
		int netherite_sword_weight = max(CONFIG.nestedNetherMobOptions.netheriteSwordWeight(),0);
		int min_sharpness = max(CONFIG.nestedNetherMobOptions.minSharpnessLevel(),0);
		int max_sharpness = max(CONFIG.nestedNetherMobOptions.netheriteSwordWeight(),min_sharpness);
		int sharpness_level = min_sharpness;
		if (max_sharpness > min_sharpness) {
			sharpness_level += 1;
			sharpness_level += random.nextInt(max_sharpness - min_sharpness);
		}
		int fireAspectChance = max(min(CONFIG.nestedNetherMobOptions.fireAspectChance(), 100), 0);
		int knockbackChance = max(min(CONFIG.nestedNetherMobOptions.knockbackChance(), 100), 0);

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
		int min_power = max(CONFIG.nestedOverworldMobOptions.nestedSkeletonMobOptions.minPowerLevel(),0);
		int max_power = max(CONFIG.nestedOverworldMobOptions.nestedSkeletonMobOptions.maxPowerLevel(),min_power);
		int power_level = min_power;
		if (max_power > min_power) {
			power_level += 1;
			power_level += random.nextInt(max_power - min_power);
		}
		int flameChance = max(min(CONFIG.nestedOverworldMobOptions.nestedSkeletonMobOptions.flameChance(), 100), 0);
		int punchChance = max(min(CONFIG.nestedOverworldMobOptions.nestedSkeletonMobOptions.punchChance(), 100), 0);

		ItemStack bow = new ItemStack(Items.BOW);
		bow.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(POWER).get(), power_level);
		if (random.nextInt(100) < punchChance) bow.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PUNCH).get(), random.nextInt(2)+1);
		if (random.nextInt(100) < flameChance) bow.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(FLAME).get(), 1);
		return bow;
	}

	public static ItemStack getZombieSword(Random random, MobEntity mobEntity) {
		int gold_sword_weight = max(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.goldSwordWeight(),0);
		int iron_sword_weight = max(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.ironSwordWeight(),0);
		int diamond_sword_weight = max(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.diamondSwordWeight(),0);
		int netherite_sword_weight = max(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.netheriteSwordWeight(),0);
		int min_sharpness = max(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.minSharpnessLevel(),0);
		int max_sharpness = max(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.maxSharpnessLevel(),min_sharpness);
		int sharpness_level = min_sharpness;
		if (max_sharpness > min_sharpness) {
			sharpness_level += 1;
			sharpness_level += random.nextInt(max_sharpness - min_sharpness);
		}
		int fireAspectChance = max(min(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.fireAspectChance(), 100), 0);
		int knockbackChance = max(min(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.knockbackChance(), 100), 0);
		int swordChance = max(min(CONFIG.nestedOverworldMobOptions.nestedZombieMobOptions.zombieSwordChance(), 100), 0);

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
		int maxArmor = max(0, CONFIG.nestedNetherMobOptions.maxArmorPieces());

		List<EquipmentSlot> EquipmentSlots = Arrays.asList(EquipmentSlot.HEAD, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.FEET);
		Collections.shuffle(EquipmentSlots);

		int i = 0;
		for (EquipmentSlot slot : EquipmentSlots) {
			i++;
			equipPiglinArmor(slot, random, mobEntity);
			if (i > maxArmor) break;
		}
	}

	private static void equipPiglinArmor(EquipmentSlot equipmentSlot, Random random, MobEntity mobEntity) {
		int armorChance = max(min(CONFIG.nestedNetherMobOptions.armorChancePerSlotPercentace(), 100), 0);
		int netherite_armor_weight = max(0, CONFIG.nestedNetherMobOptions.netheriteArmorWeight());
		int gold_armor_weight = max(0, CONFIG.nestedNetherMobOptions.goldArmorWeight());
		int min_protection = max(CONFIG.nestedNetherMobOptions.minProtectionLevel(),0);
		int max_protection = max(CONFIG.nestedNetherMobOptions.maxProtectionLevel(),min_protection);
		int protection_level = min_protection;
		if (max_protection > min_protection) {
			protection_level += 1;
			protection_level += random.nextInt(max_protection - min_protection);
		}

		if (random.nextInt(100) < armorChance) {
			Map<Item, Integer> weightmap = new HashMap<>() {{
				put(PiglinArmorMap.get(StrongerMobsMod.PiglinGearType.NETHERITE).get(equipmentSlot), netherite_armor_weight);
				put(PiglinArmorMap.get(StrongerMobsMod.PiglinGearType.GOLD).get(equipmentSlot), gold_armor_weight);
			}};
			Item armorItem = pickWeighted(weightmap, random);

			ItemStack armorPiece = new ItemStack(armorItem);
			armorPiece.addEnchantment(mobEntity.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PROTECTION).get(), protection_level);
			mobEntity.equipStack(equipmentSlot, armorPiece);
		}
	}

	public static void EquipArmor(Random random, MobEntity mobEntity){
		int leather_armor_weight = max(0, CONFIG.nestedOverworldMobOptions.leatherArmorWeight());
		int gold_armor_weight = max(0, CONFIG.nestedOverworldMobOptions.goldArmorWeight());
		int chainmail_armor_weight = max(0, CONFIG.nestedOverworldMobOptions.chainmailArmorWeight());
		int iron_armor_weight = max(0, CONFIG.nestedOverworldMobOptions.ironArmorWeight());
		int diamond_armor_weight = max(0, CONFIG.nestedOverworldMobOptions.diamondArmorWeight());
		int netherite_armor_weight = max(0, CONFIG.nestedOverworldMobOptions.netheriteArmorWeight());
		int maxArmor = max(0, CONFIG.nestedOverworldMobOptions.maxArmorPieces());
		int min_protection = max(CONFIG.nestedOverworldMobOptions.minProtectionLevel(),0);
		int max_protection = max(CONFIG.nestedOverworldMobOptions.maxProtectionLevel(),min_protection);
		int protection_level = min_protection;
		if (max_protection > min_protection) {
			protection_level += 1;
			protection_level += random.nextInt(max_protection - min_protection);
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

		int i = 0;
		for (EquipmentSlot slot : EquipmentSlots) {
			i++;
			EquipArmorSlot(random, mobEntity, quality, slot, protection_level);
			if (i > maxArmor) break;
		}
	}
	public static void EquipArmorSlot(Random random, MobEntity mobEntity, int quality, EquipmentSlot equipmentSlot, int protectionLevel){
		int armorChance = max(min(CONFIG.nestedOverworldMobOptions.armorChancePerSlotPercentace(), 100), 0);
		if (random.nextInt(100) < armorChance) {
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