package net.steakboi.strongermobs.config;


import com.mojang.datafixers.util.Pair;
import net.steakboi.strongermobs.StrongerMobsMod;

//{
//        "Overworld.armorChancePerSlotPercentace" : "Armor Chance Per Slot Percentage [0-100]",
//        "Overworld.maxArmorPieces" : "Max Armor Pieces",
//        "Overworld.minArmorPieces" : "Min Armor Pieces",
//        "Overworld.maxProtectionLevel" : "Max Protection Level",
//        "Overworld.minProtectionLevel" : "Min Protection Level",
//        "Overworld.leatherArmorWeight" : "Leather Armor Weight",
//        "Overworld.goldArmorWeight" : "Gold Armor Weight",
//        "Overworld.chainmailArmorWeight" : "Chainmail Armor Weight",
//        "Overworld.ironArmorWeight" : "Iron Armor Weight",
//        "Overworld.diamondArmorWeight" : "Diamond Armor Weight",
//        "Overworld.netheriteArmorWeight" : "Netherite Armor Weight",
//        "Overworld.Zombie.zombieSwordChance" : "Zombie Sword Chance",
//        "Overworld.Zombie.goldSwordWeight" : "Gold Sword Weight",
//        "Overworld.Zombie.ironSwordWeight" : "Iron Sword Weight",
//        "Overworld.Zombie.diamondSwordWeight" : "Diamond Sword Weight",
//        "Overworld.Zombie.netheriteSwordWeight" : "Netherite Sword Weight",
//        "Overworld.Zombie.maxSharpnessLevel" : "Max Sharpness Level",
//        "Overworld.Zombie.minSharpnessLevel" : "Min Sharpness Level",
//        "Overworld.Zombie.fireAspectChance" : "Fire Aspect Chance [0-100]",
//        "Overworld.Zombie.knockbackChance" : "Knockback Chance [0-100]",
//        "Overworld.Skeleton.maxPowerLevel" : "Max Power Level",
//        "Overworld.Skeleton.minPowerLevel" : "Min Power Level",
//        "Overworld.Skeleton.flameChance" : "Flame Chance [0-100]",
//        "Overworld.Skeleton.punchChance" : "Punch Chance [0-100]",
//        "Nether.armorChancePerSlotPercentace" : "Armor Chance Per Slot [0-100]",
//        "Nether.maxArmorPieces" : "Max Armor Pieces",
//        "Nether.minArmorPieces" : "Min Armor Pieces",
//        "Nether.maxProtectionLevel" : "Max Protection Level",
//        "Nether.minProtectionLevel" : "Min Protection Level",
//        "Nether.goldArmorWeight" : "Gold Armor Weight",
//        "Nether.netheriteArmorWeight" : "Netherite Armor Weight",
//        "Nether.goldSwordWeight" : "Gold Sword Weight",
//        "Nether.netheriteSwordWeight" : "Netherite Sword Weight",
//        "Nether.maxSharpnessLevel" : "Max Sharpness Level",
//        "Nether.minSharpnessLevel" : "Min Sharpness Level",
//        "Nether.fireAspectChance" : "Fire Aspect Chance [0-100]",
//        "Nether.knockbackChance" : "Knockback Chance [0-100]"
//        }

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;


    public static int OverworldArmorChancePerSlotPercentage;
    public static int OverworldMaxArmorPieces;
    public static int OverworldMinArmorPieces;
    public static int OverworldMaxProtectionLevel;
    public static int OverworldMinProtectionLevel;
    public static int OverworldLeatherArmorWeight;
    public static int OverworldGoldArmorWeight;
    public static int OverworldChainmailArmorWeight;
    public static int OverworldIronArmorWeight;
    public static int OverworldDiamondArmorWeight;
    public static int OverworldNetheriteArmorWeight;
    public static int OverworldZombieZombieSwordChance;
    public static int OverworldZombieGoldSwordWeight;
    public static int OverworldZombieIronSwordWeight;
    public static int OverworldZombieDiamondSwordWeight;
    public static int OverworldZombieNetheriteSwordWeight;
    public static int OverworldZombieMaxSharpnessLevel;
    public static int OverworldZombieMinSharpnessLevel;
    public static int OverworldZombieFireAspectChance;
    public static int OverworldZombieKnockbackChance;
    public static int OverworldSkeletonMaxPowerLevel;
    public static int OverworldSkeletonMinPowerLevel;
    public static int OverworldSkeletonFlameChance;
    public static int OverworldSkeletonPunchChance;
    public static int NetherArmorChancePerSlotPercentage;
    public static int NetherMaxArmorPieces;
    public static int NetherMinArmorPieces;
    public static int NetherMaxProtectionLevel;
    public static int NetherMinProtectionLevel;
    public static int NetherGoldArmorWeight;
    public static int NetherNetheriteArmorWeight;
    public static int NetherGoldSwordWeight;
    public static int NetherNetheriteSwordWeight;
    public static int NetherMaxSharpnessLevel;
    public static int NetherMinSharpnessLevel;
    public static int NetherFireAspectChance;
    public static int NetherKnockbackChance;


    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(StrongerMobsMod.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("Overworld.armorChancePerSlotPercentace" , 55),  "Armor Chance Per Slot Percentage [0-100]");
        configs.addKeyValuePair(new Pair<>("Overworld.maxArmorPieces" , 4),  "Max Armor Pieces");
        configs.addKeyValuePair(new Pair<>("Overworld.minArmorPieces" , 0),  "Min Armor Pieces");
        configs.addKeyValuePair(new Pair<>("Overworld.maxProtectionLevel" , 5),  "Max Protection Level");
        configs.addKeyValuePair(new Pair<>("Overworld.minProtectionLevel" , 2),  "Min Protection Level");
        configs.addKeyValuePair(new Pair<>("Overworld.leatherArmorWeight" , 5),  "Leather Armor Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.goldArmorWeight" , 5),  "Gold Armor Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.chainmailArmorWeight" , 10),  "Chainmail Armor Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.ironArmorWeight" , 10),  "Iron Armor Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.diamondArmorWeight" , 40),  "Diamond Armor Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.netheriteArmorWeight" , 30),  "Netherite Armor Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.zombieSwordChance" , 50),  "Zombie Sword Chance");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.goldSwordWeight" , 20),  "Gold Sword Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.ironSwordWeight" , 30),  "Iron Sword Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.diamondSwordWeight" , 30),  "Diamond Sword Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.netheriteSwordWeight" , 20),  "Netherite Sword Weight");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.maxSharpnessLevel" , 5),  "Max Sharpness Level");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.minSharpnessLevel" , 2),  "Min Sharpness Level");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.fireAspectChance" , 13),  "Fire Aspect Chance [0-100]");
        configs.addKeyValuePair(new Pair<>("Overworld.Zombie.knockbackChance" , 13),  "Knockback Chance [0-100]");
        configs.addKeyValuePair(new Pair<>("Overworld.Skeleton.maxPowerLevel" , 5),  "Max Power Level");
        configs.addKeyValuePair(new Pair<>("Overworld.Skeleton.minPowerLevel" , 2),  "Min Power Level");
        configs.addKeyValuePair(new Pair<>("Overworld.Skeleton.flameChance" , 13),  "Flame Chance [0-100]");
        configs.addKeyValuePair(new Pair<>("Overworld.Skeleton.punchChance" , 13),  "Punch Chance [0-100]");
        configs.addKeyValuePair(new Pair<>("Nether.armorChancePerSlotPercentace" , 80),  "Armor Chance Per Slot [0-100]");
        configs.addKeyValuePair(new Pair<>("Nether.maxArmorPieces" , 4),  "Max Armor Pieces");
        configs.addKeyValuePair(new Pair<>("Nether.minArmorPieces" , 0),  "Min Armor Pieces");
        configs.addKeyValuePair(new Pair<>("Nether.maxProtectionLevel" , 5),  "Max Protection Level");
        configs.addKeyValuePair(new Pair<>("Nether.minProtectionLevel" , 2),  "Min Protection Level");
        configs.addKeyValuePair(new Pair<>("Nether.goldArmorWeight" , 65),  "Gold Armor Weight");
        configs.addKeyValuePair(new Pair<>("Nether.netheriteArmorWeight" , 35),  "Netherite Armor Weight");
        configs.addKeyValuePair(new Pair<>("Nether.goldSwordWeight" , 65),  "Gold Sword Weight");
        configs.addKeyValuePair(new Pair<>("Nether.netheriteSwordWeight" , 35),  "Netherite Sword Weight");
        configs.addKeyValuePair(new Pair<>("Nether.maxSharpnessLevel" , 5),  "Max Sharpness Level");
        configs.addKeyValuePair(new Pair<>("Nether.minSharpnessLevel" , 2),  "Min Sharpness Level");
        configs.addKeyValuePair(new Pair<>("Nether.fireAspectChance" , 13),  "Fire Aspect Chance [0-100]");
        configs.addKeyValuePair(new Pair<>("Nether.knockbackChance" , 13),  "Knockback Chance [0-100]");
    }

    private static void assignConfigs() {
        OverworldArmorChancePerSlotPercentage = CONFIG.getOrDefault("Overworld.armorChancePerSlotPercentace" , 55);
        OverworldMaxArmorPieces = CONFIG.getOrDefault("Overworld.maxArmorPieces" , 4);
        OverworldMinArmorPieces = CONFIG.getOrDefault("Overworld.minArmorPieces" , 0);
        OverworldMaxProtectionLevel = CONFIG.getOrDefault("Overworld.maxProtectionLevel" , 5);
        OverworldMinProtectionLevel = CONFIG.getOrDefault("Overworld.minProtectionLevel" , 2);
        OverworldLeatherArmorWeight = CONFIG.getOrDefault("Overworld.leatherArmorWeight" , 5);
        OverworldGoldArmorWeight = CONFIG.getOrDefault("Overworld.goldArmorWeight" , 5);
        OverworldChainmailArmorWeight = CONFIG.getOrDefault("Overworld.chainmailArmorWeight" , 10);
        OverworldIronArmorWeight = CONFIG.getOrDefault("Overworld.ironArmorWeight" , 10);
        OverworldDiamondArmorWeight = CONFIG.getOrDefault("Overworld.diamondArmorWeight" , 40);
        OverworldNetheriteArmorWeight = CONFIG.getOrDefault("Overworld.netheriteArmorWeight" , 30);
        OverworldZombieZombieSwordChance = CONFIG.getOrDefault("Overworld.Zombie.zombieSwordChance" , 50);
        OverworldZombieGoldSwordWeight = CONFIG.getOrDefault("Overworld.Zombie.goldSwordWeight" , 20);
        OverworldZombieIronSwordWeight = CONFIG.getOrDefault("Overworld.Zombie.ironSwordWeight" , 30);
        OverworldZombieDiamondSwordWeight = CONFIG.getOrDefault("Overworld.Zombie.diamondSwordWeight" , 30);
        OverworldZombieNetheriteSwordWeight = CONFIG.getOrDefault("Overworld.Zombie.netheriteSwordWeight" , 20);
        OverworldZombieMaxSharpnessLevel = CONFIG.getOrDefault("Overworld.Zombie.maxSharpnessLevel" , 5);
        OverworldZombieMinSharpnessLevel = CONFIG.getOrDefault("Overworld.Zombie.minSharpnessLevel" , 2);
        OverworldZombieFireAspectChance = CONFIG.getOrDefault("Overworld.Zombie.fireAspectChance" , 13);
        OverworldZombieKnockbackChance = CONFIG.getOrDefault("Overworld.Zombie.knockbackChance" , 13);
        OverworldSkeletonMaxPowerLevel = CONFIG.getOrDefault("Overworld.Skeleton.maxPowerLevel" , 5);
        OverworldSkeletonMinPowerLevel = CONFIG.getOrDefault("Overworld.Skeleton.minPowerLevel" , 2);
        OverworldSkeletonFlameChance = CONFIG.getOrDefault("Overworld.Skeleton.flameChance" , 13);
        OverworldSkeletonPunchChance = CONFIG.getOrDefault("Overworld.Skeleton.punchChance" , 13);
        NetherArmorChancePerSlotPercentage = CONFIG.getOrDefault("Nether.armorChancePerSlotPercentace" , 80);
        NetherMaxArmorPieces = CONFIG.getOrDefault("Nether.maxArmorPieces" , 4);
        NetherMinArmorPieces = CONFIG.getOrDefault("Nether.minArmorPieces" , 0);
        NetherMaxProtectionLevel = CONFIG.getOrDefault("Nether.maxProtectionLevel" , 5);
        NetherMinProtectionLevel = CONFIG.getOrDefault("Nether.minProtectionLevel" , 2);
        NetherGoldArmorWeight = CONFIG.getOrDefault("Nether.goldArmorWeight" , 65);
        NetherNetheriteArmorWeight = CONFIG.getOrDefault("Nether.netheriteArmorWeight" , 35);
        NetherGoldSwordWeight = CONFIG.getOrDefault("Nether.goldSwordWeight" , 65);
        NetherNetheriteSwordWeight = CONFIG.getOrDefault("Nether.netheriteSwordWeight" , 35);
        NetherMaxSharpnessLevel = CONFIG.getOrDefault("Nether.maxSharpnessLevel" , 5);
        NetherMinSharpnessLevel = CONFIG.getOrDefault("Nether.minSharpnessLevel" , 2);
        NetherFireAspectChance = CONFIG.getOrDefault("Nether.fireAspectChance" , 13);
        NetherKnockbackChance = CONFIG.getOrDefault("Nether.knockbackChance" , 13);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}
