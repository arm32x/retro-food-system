package arm32x.minecraft.retrofoodsystem.mixin;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import arm32x.minecraft.retrofoodsystem.config.ModConfig;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemConvertible {
	
	@Mutable @Final @Shadow private int maxCount;
	@Final @Shadow private FoodComponent foodComponent;
	
	@Shadow public abstract boolean isFood();
	
	@Shadow public abstract ItemStack finishUsing(ItemStack stack, World world, LivingEntity user);
	
	@Inject(method = "<init>(Lnet/minecraft/item/Item$Settings;)V", at = @At("RETURN"))
	public void onInitialize(Item.Settings settings, CallbackInfo ci) {
		if (this.foodComponent != null) {
			this.maxCount = AutoConfig.getConfigHolder(ModConfig.class).getConfig().foodStackSize;
		}
	}
	
	@Inject(method = "use(Lnet/minecraft/world/World;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/util/TypedActionResult;", at = @At("HEAD"), cancellable = true)
	public void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
		if (this.isFood() && !AutoConfig.getConfigHolder(ModConfig.class).getConfig().useEatingAnimation) {
			ItemStack itemStack = user.getStackInHand(hand);
			user.getHungerManager().eat(itemStack.getItem(), itemStack);
			for (Pair<StatusEffectInstance, Float> pair : Objects.requireNonNull(itemStack.getItem().getFoodComponent()).getStatusEffects()) {
				if (!world.isClient && pair.getLeft() != null && world.random.nextFloat() < pair.getRight()) {
					user.addStatusEffect(new StatusEffectInstance(pair.getLeft()));
				}
			}
			if (!user.abilities.creativeMode) {
				itemStack.decrement(1);
			}
			cir.setReturnValue(TypedActionResult.success(itemStack));
		}
	}
	
}
