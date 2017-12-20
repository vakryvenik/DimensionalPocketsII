package com.zeher.dimensionalpockets.core.pocket;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;
import com.zeher.dimensionalpockets.core.handlers.BiomeHandler;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class PocketChunkGenerator implements IChunkProvider, IChunkGenerator {

	private World world;

	public PocketChunkGenerator(World world) {
		this.world = world;
	}

	@Override
	public Chunk getLoadedChunk(int x, int z) {
		return provideChunk(x, z);
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		Chunk chunk = new Chunk(world, x, z);

		byte[] byteArray = new byte[256];
		Arrays.fill(byteArray,
				(byte) BiomeHandler.pocketBiome.getIdForBiome(BiomeHandler.pocketBiome));

		chunk.setBiomeArray(byteArray);
		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public String makeString() {
		return null;
	}

	@Override
	public boolean tick() {
		return false;
	}

	@Override
	public boolean isChunkGeneratedAt(int p_191062_1_, int p_191062_2_) {
		return true;
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		return null;
	}

	@Override
	public void populate(int x, int z) {
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return null;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) { }

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

}
