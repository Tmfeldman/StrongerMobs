package net.steakboi.strongermobs;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.Nest;

@Modmenu(modId = "strongermobs")
@Config(name = "stronger-mobs-config", wrapperName = "StrongerMobsConfig")
public class StrongerMobsConfigModel {
    @Nest
    public NestedOverworldMobOptions nestedOverworldMobOptions = new NestedOverworldMobOptions();

    @Nest
    public NestedNetherMobOptions nestedNetherMobOptions = new NestedNetherMobOptions();

    public static class NestedNetherMobOptions {
        public int armorChancePerSlotPercentace = 80;
        public int maxArmorPieces = 4;
        public int minArmorPieces = 0;
        public int maxProtectionLevel = 5;
        public int minProtectionLevel = 2;
        public int goldArmorWeight = 65;
        public int netheriteArmorWeight = 35;
        public int goldSwordWeight = 65;
        public int netheriteSwordWeight = 35;
        public int maxSharpnessLevel = 5;
        public int minSharpnessLevel = 2;
        public int fireAspectChance = 13;
        public int knockbackChance = 13;
    }

    public static class NestedOverworldMobOptions {
        public int armorChancePerSlotPercentace = 55;
        public int maxArmorPieces = 4;
        public int minArmorPieces = 0;
        public int maxProtectionLevel = 5;
        public int minProtectionLevel = 2;
        public int leatherArmorWeight = 5;
        public int goldArmorWeight = 5;
        public int chainmailArmorWeight = 10;
        public int ironArmorWeight = 10;
        public int diamondArmorWeight = 40;
        public int netheriteArmorWeight = 30;
        @Nest
        public NestedZombieMobOptions nestedZombieMobOptions = new NestedZombieMobOptions();
        @Nest
        public NestedSkeletonMobOptions nestedSkeletonMobOptions = new NestedSkeletonMobOptions();

        public static class NestedZombieMobOptions {
            public int zombieSwordChance = 50;
            public int goldSwordWeight = 2;
            public int ironSwordWeight = 3;
            public int diamondSwordWeight = 3;
            public int netheriteSwordWeight = 2;
            public int maxSharpnessLevel = 5;
            public int minSharpnessLevel = 2;
            public int fireAspectChance = 13;
            public int knockbackChance = 13;
        }
        public static class NestedSkeletonMobOptions {
            public int maxPowerLevel = 5;
            public int minPowerLevel = 2;
            public int flameChance = 13;
            public int punchChance = 13;
        }
    }
}
