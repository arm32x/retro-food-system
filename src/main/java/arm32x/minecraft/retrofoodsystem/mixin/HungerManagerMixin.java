package arm32x.minecraft.retrofoodsystem.mixin;

import net.fabricmc.loader.gui.FabricGuiEntry;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
	
	private PlayerEntity player;
	
	@Inject(method = "update(Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At("HEAD"), cancellable = true)
	public void onUpdate(PlayerEntity player, CallbackInfo ci) {
		this.player = player;
		ci.cancel();
	}
	
	@Inject(method = "add(IF)V", at = @At("HEAD"), cancellable = true)
	public void onAdd(int food, float f, CallbackInfo ci) {
		if (player != null) {
			player.heal(food);
		}
		ci.cancel();
	}
	
}
