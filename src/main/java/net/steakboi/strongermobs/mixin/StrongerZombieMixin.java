package net.steakboi.strongermobs.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.steakboi.strongermobs.StrongerMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.enchantment.Enchantments.*;

@Mixin(ZombieEntity.class)
public class StrongerZombieMixin {
    @Inject(at = @At(value = "HEAD"), method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
    private void ReplaceInitEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        MobEntity mobThis = (MobEntity) (Object) this;
        StrongerMobsMod.EquipArmor(random, mobThis);
        if (random.nextFloat() <  0.5F ) {
            ItemStack weapon;
            int i = random.nextInt(10);
            if (i < 2) {
                weapon = new ItemStack(Items.IRON_SWORD);
            } else if (i < 5) {
                weapon = new ItemStack(Items.GOLDEN_SWORD);
            } else if (i < 8) {
                weapon = new ItemStack(Items.DIAMOND_SWORD);
            } else {
                weapon = new ItemStack(Items.NETHERITE_SWORD);
            }
            weapon.addEnchantment(mobThis.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(SHARPNESS).get(), random.nextInt(4)+2);
            if (random.nextInt(7) == 1) weapon.addEnchantment(mobThis.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(FIRE_ASPECT).get(), random.nextInt(2)+1);
            if (random.nextInt(7) == 1) weapon.addEnchantment(mobThis.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(KNOCKBACK).get(), random.nextInt(2)+1);
            mobThis.equipStack(EquipmentSlot.MAINHAND, weapon);
        }
        ci.cancel();
    }
}
