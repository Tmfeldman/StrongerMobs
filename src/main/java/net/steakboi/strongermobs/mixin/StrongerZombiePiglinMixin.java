package net.steakboi.strongermobs.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.steakboi.strongermobs.StrongerMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombifiedPiglinEntity.class)
public class StrongerZombiePiglinMixin {
    @Inject(at = @At("HEAD"), method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
    private void ReplaceInitEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        ZombifiedPiglinEntity thisPiglin = (ZombifiedPiglinEntity) (Object) this;
        StrongerMobsMod.EquipPiglinArmor(random, thisPiglin);
        ItemStack weapon = StrongerMobsMod.getPiglinSword(random, thisPiglin);
        thisPiglin.equipStack(EquipmentSlot.MAINHAND, weapon);
        ci.cancel();
    }
}
