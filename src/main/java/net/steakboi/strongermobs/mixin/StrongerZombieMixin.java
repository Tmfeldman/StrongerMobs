package net.steakboi.strongermobs.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.steakboi.strongermobs.StrongerMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public class StrongerZombieMixin {
    @Inject(at = @At(value = "HEAD"), method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
    private void ReplaceInitEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        MobEntity mobThis = (MobEntity) (Object) this;
        StrongerMobsMod.EquipArmor(random, mobThis);
        ItemStack weapon = StrongerMobsMod.getZombieSword(random);
        if (weapon != null) {
            mobThis.equipStack(EquipmentSlot.MAINHAND, weapon);
        }
        ci.cancel();
    }
}
