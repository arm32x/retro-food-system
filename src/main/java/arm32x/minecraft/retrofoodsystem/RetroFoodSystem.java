package arm32x.minecraft.retrofoodsystem;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;

import arm32x.minecraft.retrofoodsystem.config.ModConfig;

public class RetroFoodSystem implements ModInitializer {
	
	@Override
	public void onInitialize() {
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
	}
	
}
