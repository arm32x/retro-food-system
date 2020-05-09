package arm32x.minecraft.retrofoodsystem.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
	
	protected PlayerEntityMixin(EntityType<? extends LivingEntity> type, World world) {
		super(type, world);
	}
	
	@Inject(method = "canConsume(Z)Z", at = @At("HEAD"), cancellable = true)
	public void onCheckCanConsume(boolean ignoreHunger, CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(true);
	}
	
}
