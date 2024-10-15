package net.steakboi.strongermobs.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.enchantment.Enchantments.*;

@Mixin(AbstractSkeletonEntity.class)
public class StrongerSkeletonMixin extends HostileEntity {
    protected StrongerSkeletonMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(at = @At(value = "HEAD"), method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V", cancellable = true)
    private void ReplaceInitEquipment(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        super.initEquipment(random, localDifficulty);
        ItemStack bow = new ItemStack(Items.BOW);
        bow.addEnchantment(this.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(POWER).get(), random.nextInt(4)+2);
        if (random.nextInt(7) == 1) bow.addEnchantment(this.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(PUNCH).get(), random.nextInt(2)+1);
        if (random.nextInt(7) == 1) bow.addEnchantment(this.getWorld().getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(FLAME).get(), 1);
        this.equipStack(EquipmentSlot.MAINHAND, bow);
        ci.cancel();
    }
}
