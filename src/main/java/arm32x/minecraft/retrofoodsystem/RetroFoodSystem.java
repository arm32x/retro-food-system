package arm32x.minecraft.retrofoodsystem;

import java.util.Iterator;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import arm32x.minecraft.retrofoodsystem.config.ModConfig;

public class RetroFoodSystem implements PreLaunchEntrypoint {
	
	@Override
	public void onPreLaunch() {
		AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
	}
	
}
