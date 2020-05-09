package arm32x.minecraft.retrofoodsystem;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

import arm32x.minecraft.retrofoodsystem.config.ModConfig;

public class RetroFoodSystem implements PreLaunchEntrypoint {
	
	@Override
	public void onPreLaunch() {
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
	}
	
}
