package arm32x.minecraft.retrofoodsystem.mixin;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.gui.FabricGuiEntry;
import net.fabricmc.loader.launch.common.FabricLauncher;
import net.minecraft.MinecraftVersion;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import arm32x.minecraft.retrofoodsystem.config.ModConfig;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
	
	private PlayerEntity player;
	
	@Inject(method = "update(Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At("HEAD"), cancellable = true)
	public void onUpdate(PlayerEntity player, CallbackInfo ci) {
		if (player != null) {
			this.player = player;
		}
		ci.cancel();
	}
	
	@Inject(method = "add(IF)V", at = @At("HEAD"), cancellable = true)
	public void onAdd(int food, float f, CallbackInfo ci) {
		if (player != null) {
			player.heal(food);
		}
		ci.cancel();
	}
	
	@Inject(method = "getFoodLevel()I", at = @At("HEAD"), cancellable = true)
	public void onGetFoodLevel(CallbackInfoReturnable<Integer> cir) {
		ModConfig.SprintingMode sprintingMode = AutoConfig.getConfigHolder(ModConfig.class).getConfig().sprintingMode;
		switch (sprintingMode) {
			case NEVER:
				cir.setReturnValue(0);
				break;
			case ABOVE_THREE_HEARTS:
				if (player != null) {
					cir.setReturnValue(MathHelper.clamp((int)player.getHealth(), 0, 20));
				}
				break;
			case ALWAYS:
				cir.setReturnValue(20);
				break;
		}
	}
	
}
