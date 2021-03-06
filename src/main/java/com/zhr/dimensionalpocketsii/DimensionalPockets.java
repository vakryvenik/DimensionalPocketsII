package com.zhr.dimensionalpocketsii;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zhr.dimensionalpocketsii.client.screen.ScreenPocketConfig;
import com.zhr.dimensionalpocketsii.core.management.ModConfigurationManager;
import com.zhr.dimensionalpocketsii.core.management.RecipeHandler;
import com.zhr.dimensionalpocketsii.core.management.bus.BusSubscriberMod;
import com.zhr.dimensionalpocketsii.pocket.core.management.PocketEventManager;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(DimensionalPockets.MOD_ID)
public final class DimensionalPockets {

	public static final String MOD_ID = "dimensionalpocketsii";
	
	// Directly reference a log4j logger.
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String LOGGER_PREFIX = "< " + MOD_ID + " >: ";

	public DimensionalPockets() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLCommonSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onFMLClientSetup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(BusSubscriberMod::setRenderLayers);

		MinecraftForge.EVENT_BUS.register(this);
		
		ModLoadingContext context = ModLoadingContext.get();
		
		context.registerConfig(ModConfig.Type.COMMON, ModConfigurationManager.spec, "dimpockets-common.toml");
	}

	public void onFMLCommonSetup(FMLCommonSetupEvent event) {
		LOGGER.info(LOGGER_PREFIX + "[FMLCommonSetupEvent] CommonSetup...");
		
		RecipeHandler.preInitialization();

		// GameRegistry.registerWorldGenerator(new WorldGenerationHandler(), 0);
		// NetworkHandler.preInitialization();

		//ModDimensionManager.registerDimensions();

		if (ModConfigurationManager.getInstance().getKeepChunksLoaded()) {
			// ForgeChunkManager.setForcedChunkLoadingCallback(this, new
			// ChunkLoaderManagerRoom());
			// ForgeChunkManager.setForcedChunkLoadingCallback(this, new
			// ChunkLoaderManagerBlock());
		}

		MinecraftForge.EVENT_BUS.register(new PocketEventManager());
	}
	
	public void onFMLClientSetup(FMLClientSetupEvent event) {
		final ModLoadingContext context = ModLoadingContext.get();
		
		registerGUI(context);
		
		LOGGER.info(LOGGER_PREFIX + "[FMLClientSetupEvent] ClientSetup...");
	}
	
	@OnlyIn(Dist.CLIENT)
	public void registerGUI(ModLoadingContext context) {
		context.registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (mc, screen) -> new ScreenPocketConfig(screen));
	}
}