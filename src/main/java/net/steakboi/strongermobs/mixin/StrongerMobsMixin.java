package net.steakboi.strongermobs.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.steakboi.strongermobs.StrongerMobsMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import static net.steakboi.strongermobs.StrongerMobsMod.getArmorMap;


@Mixin(MobEntity.class)
public abstract class StrongerMobsMixin {
    @Inject(at = @At("HEAD"), method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
	private void ReplaceInitEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        StrongerMobsMod.EquipArmor(random, localDifficulty, (MobEntity) (Object) this);
		ci.cancel();
	}

	@Inject(at = @At("HEAD"), method = "getEquipmentForSlot(Lnet/minecraft/entity/EquipmentSlot;I)Lnet/minecraft/item/Item;", cancellable = true)
	private static void ReplaceGetEquipmentForSlot(EquipmentSlot equipmentSlot, int equipmentLevel, CallbackInfoReturnable<Item> cir){
		cir.setReturnValue(getArmorMap(equipmentSlot, equipmentLevel));
		cir.cancel();
	}
}