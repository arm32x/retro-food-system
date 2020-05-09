package arm32x.minecraft.retrofoodsystem.config;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class ModMenu implements ModMenuApi {
	
	@Override
	public String getModId() {
		return "retro-food-system";
	}
	
	@Override
	public ConfigScreenFactory<Screen> getModConfigScreenFactory() {
		return parent -> AutoConfig.getConfigScreen(ModConfig.class, parent).get();
	}
}
