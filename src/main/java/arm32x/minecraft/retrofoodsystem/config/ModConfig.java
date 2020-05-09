package arm32x.minecraft.retrofoodsystem.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "retro-food-system")
public class ModConfig implements ConfigData {
	
	public boolean useEatingAnimation = false;
	
	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	public SprintingMode sprintingMode = SprintingMode.NEVER;
	
	@ConfigEntry.Gui.RequiresRestart
	@ConfigEntry.BoundedDiscrete(min = 1, max = 64)
	public int foodStackSize = 1;
	
	public enum SprintingMode {
		NEVER, ABOVE_THREE_HEARTS, ALWAYS
	}
	
}
