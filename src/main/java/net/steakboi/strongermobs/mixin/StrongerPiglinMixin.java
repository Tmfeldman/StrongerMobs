package net.steakboi.strongermobs.mixin;

import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.steakboi.strongermobs.StrongerMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PiglinEntity.class)
public class StrongerPiglinMixin {
    @Inject(at = @At("HEAD"), method = "makeInitialWeapon()Lnet/minecraft/item/ItemStack;", cancellable = true)
    private void replaceMakeInitialWeapon(CallbackInfoReturnable<ItemStack> ci){
        ItemStack weapon;
        PiglinEntity thisPiglin = (PiglinEntity) (Object) this;
        Random random = thisPiglin.getWorld().getRandom();
        if (random.nextInt(2) == 1){
            weapon = new ItemStack(Items.CROSSBOW);
        } else {
            weapon = StrongerMobsMod.getPiglinSword(random, thisPiglin);
        }
        ci.setReturnValue(weapon);
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
    private void ReplaceInitEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        PiglinEntity thisPiglin = (PiglinEntity) (Object) this;
        if (thisPiglin.isAdult()) {
            StrongerMobsMod.EquipPiglinArmor(random, thisPiglin);
        }
        ci.cancel();
    }


}
