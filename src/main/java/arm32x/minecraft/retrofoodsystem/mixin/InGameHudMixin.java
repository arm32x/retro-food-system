package arm32x.minecraft.retrofoodsystem.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {
	
	// TODO:  Figure out a way to do this that doesn’t disrupt the normal return value.
	@Inject(method = "getHeartCount(Lnet/minecraft/entity/LivingEntity;)I", at = @At("RETURN"), cancellable = true)
	public void onGetHeartCount(LivingEntity entity, CallbackInfoReturnable<Integer> cir) {
		if (cir.getReturnValue() == 0) {
			cir.setReturnValue(-1);
		}
	}
	
}
