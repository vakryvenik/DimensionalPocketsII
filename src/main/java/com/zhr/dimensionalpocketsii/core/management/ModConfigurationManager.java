package com.zhr.dimensionalpocketsii.core.management;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class ModConfigurationManager {
	
	public static final ForgeConfigSpec spec;
	
	static final ModConfigurationManager INSTANCE;
	
	static {
		{
			final Pair<ModConfigurationManager, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ModConfigurationManager::new);
			INSTANCE = specPair.getLeft();
			spec = specPair.getRight();
		}
	}
	
	
	public static void save() {
		spec.save();
	}
	
	private final BooleanValue connected_textures_inside_pocket;
	private final BooleanValue keep_chunks_loaded;
	private final BooleanValue can_destroy_walls;
	private final BooleanValue can_teleport;
	private final BooleanValue system_message;
	
	private final BooleanValue encrypt_files;
	private final BooleanValue cancel_commands;
	
	ModConfigurationManager(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		keep_chunks_loaded = builder
				.comment("Whether to keep the chunks inside the Pocket Dimension Loaded")
				.define("keep_chunks_loaded", true);
		
		can_destroy_walls = builder
				.comment("Whether the walls of Pockets can be destroyed in creative mode")
				.define("can_destroy_walls", false);
		
		cancel_commands = builder
				.comment("Whether commands are cancelled inside Pockets")
				.define("cancel_commands", true);
		builder.pop();
		
		builder.push("debug");
		can_teleport = builder
					.comment("Whether you can use the /tp command to teleport into and out of the Pocket Dimension")
					.define("can_teleport", false);
		
		system_message = builder
					.comment("Whether DimensionalPockets can send system messages")
					.define("system_message", true);
		
		encrypt_files = builder
				.comment("Whether pocket data files are encrypted or not")
				.define("encrpyt_files", false);
		builder.pop();
		
		builder.push("visual");
		connected_textures_inside_pocket = builder
				.comment("Whether or not connected textures work inside Pockets")
				.define("connected_textures_inside_pocket", true);
		builder.pop();
	}
	
	public static ModConfigurationManager getInstance() {
		return INSTANCE;
	}

	public boolean getConnectedTexturesInsidePocket() {
		return connected_textures_inside_pocket.get();
	}
	
	public void setConnectedTexturesInsidePocket(boolean value) {
		this.connected_textures_inside_pocket.set(value);
	}
	
	public boolean getKeepChunksLoaded() {
		return keep_chunks_loaded.get();
	}
	
	public void setKeepChunksLoaded(boolean value) {
		this.keep_chunks_loaded.set(value);
	}
	
	public boolean getCanDestroyWalls() {
		return can_destroy_walls.get();
	}
	
	public void setCanDestroyWalls(boolean value) {
		this.can_destroy_walls.set(value);
	}
	
	public boolean getCanTeleport() {
		return can_teleport.get();
	}
	
	public void setCanTeleport(boolean value) {
		this.can_teleport.set(value);
	}
	
	public boolean getSystemMessage() {
		return system_message.get();
	}
	
	public void setSystemMessage(boolean value) {
		this.system_message.set(value);
	}
	
	public boolean getCancelCommands() {
		return cancel_commands.get();
	}
	
	public void setCancelCommands(boolean value) {
		this.cancel_commands.set(value);
	}
	
	public boolean getEncryptMessages() {
		return encrypt_files.get();
	}
	
	public void setEncryptMessages(boolean value) {
		this.encrypt_files.set(value);
	}
}
