package com.sonorous.level.world.chunk;

import com.sonorous.level.world.KingdomUtils;
import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.CustomBiomeProvider;
import com.sonorous.level.world.chunk.populator.FeaturePopulator;
import com.sonorous.level.world.chunk.populator.FloraPopulator;
import com.sonorous.level.world.chunk.populator.TreePopulator;
import com.sonorous.level.world.noise.GenerationNoise;
import com.sonorous.level.world.noise.NoiseCategories;
import com.sonorous.level.world.noise.NoiseMaster;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/***
 * Written with inspiration from https://www.spigotmc.org/threads/how-to-create-a-custom-world-generator.545616/
 */
public class CustomChunkGenerator extends ChunkGenerator {

    public CustomChunkGenerator() {
    }

    @Override
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {


                float currentY = ChunkUtils.getCurrentY(x, chunkX, z, chunkZ);

                boolean ocean = currentY <= ChunkUtils.SEA_LEVEL;
                HashMap<BiomeLayers, List<Material>> layers = NoiseMaster.getBiomeLayers(chunkX * 16 + x, chunkZ * 16 + z, ocean);

                for (int y = chunkData.getMinHeight(); y < ChunkUtils.Y_LIMIT && y < chunkData.getMaxHeight(); y++) {

                    if (y < currentY) {
                        float distanceToSurface = Math.abs(y - currentY); // The absolute y distance to the world surface.

                        // It is not the closest block to the surface but still very close.
                        if (distanceToSurface < ChunkUtils.LAYER_1_HEIGHT) {
                            chunkData.setBlock(x, y, z, layers.get(BiomeLayers.PRIMARY).get(random.nextInt(layers.get(BiomeLayers.PRIMARY).size())));
                        }
                    } else {
                        if (y - currentY < KingdomUtils.WALL_HEIGHT && KingdomUtils.isKingdomWalls(chunkX * 16 + x, chunkZ * 16 + z)) {
                            chunkData.setBlock(x, y, z, Material.STONE_BRICKS);
                        }
//                        } else if (y - currentY < TownUtils.WALL_HEIGHT && TownUtils.isTownWalls(chunkX * 16 + x, chunkZ * 16 + z)) {
//                            chunkData.setBlock(x, y, z, Material.STRIPPED_OAK_WOOD);
//                        }
                    }

                    if (y <= ChunkUtils.SEA_LEVEL && ocean) {
                        chunkData.setBlock(x, y, z, Material.WATER);
                    }
                }
            }
        }
    }

    @Override
    public void generateSurface(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                float currentY = ChunkUtils.getCurrentY(x, chunkX, z, chunkZ);

                boolean ocean = currentY <= ChunkUtils.SEA_LEVEL;
                HashMap<BiomeLayers, List<Material>> layers = NoiseMaster.getBiomeLayers(chunkX * 16 + x, chunkZ * 16 + z, ocean);

                for (int y = chunkData.getMinHeight(); y < ChunkUtils.Y_LIMIT && y < chunkData.getMaxHeight(); y++) {
                    if (y < currentY) {
                        float distanceToSurface = Math.abs(y - currentY); // The absolute y distance to the world surface.

                        // Set grass if the block closest to the surface.
                        if (distanceToSurface < 1 && y > ChunkUtils.SEA_LEVEL) {

                            if (KingdomUtils.isInsideKingdomInclWalls(chunkX * 16 + x, chunkZ * 16 + z)) {
                                if (GenerationNoise.getNoise(NoiseCategories.KINGDOM_PATHS, chunkX * 16 + x, chunkZ * 16 + z) > 0.75) {
                                    chunkData.setBlock(x, y, z, Material.COBBLESTONE);

                                } else {
                                    chunkData.setBlock(x, y, z, Material.GRASS_BLOCK);

                                }
                            } else {
                                chunkData.setBlock(x, y, z, layers.get(BiomeLayers.SURFACE).get(random.nextInt(layers.get(BiomeLayers.SURFACE).size())));
                            }
                        }
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public BiomeProvider getDefaultBiomeProvider(@NotNull WorldInfo worldInfo) {
        return new CustomBiomeProvider();
    }

    @Override
    public void generateBedrock(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                HashMap<BiomeLayers, List<Material>> layers = NoiseMaster.getBiomeLayers(chunkX * 16 + x, chunkZ * 16 + z, true);
                for (int y = chunkData.getMinHeight(); y < ChunkUtils.Y_LIMIT && y < chunkData.getMaxHeight(); y++) {
                    if (y < chunkData.getMinHeight() + 2) {
                        chunkData.setBlock(x, y, z, layers.get(BiomeLayers.BASE).get(random.nextInt(layers.get(BiomeLayers.BASE).size())));
                    }
                }
            }
        }
    }

    @Override
    public void generateCaves(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                float currentY = ChunkUtils.getCurrentY(x, chunkX, z, chunkZ);

                boolean ocean = currentY <= ChunkUtils.SEA_LEVEL;
                HashMap<BiomeLayers, List<Material>> layers = NoiseMaster.getBiomeLayers(chunkX * 16 + x, chunkZ * 16 + z, ocean);

                for (int y = chunkData.getMinHeight() + 2; y < ChunkUtils.Y_LIMIT && y < chunkData.getMaxHeight(); y++) {

                    if (y < currentY) {
                        float distanceToSurface = Math.abs(y - currentY); // The absolute y distance to the world surface.
                        // Not close to the surface at all.
                        if (distanceToSurface > ChunkUtils.LAYER_1_HEIGHT) {
                            if (isSmallBubble(chunkX, chunkZ, x, y, z)) {
                                chunkData.setBlock(x, y, z, Material.CAVE_AIR);
                            } else if (isCave(chunkX, chunkZ, x, y, z)) {
                                chunkData.setBlock(x, y, z, Material.CAVE_AIR);
                            }
                            else {
                                Material neighbour = Material.STONE;
                                List<Material> neighbourBlocks = new ArrayList<Material>(Arrays.asList(chunkData.getType(Math.max(x - 1, 0), y, z), chunkData.getType(x, Math.max(y - 1, 0), z), chunkData.getType(x, y, Math.max(z - 1, 0)))); // A list of all neighbour blocks.

                                // Randomly place vein anchors.
                                if (random.nextFloat() < 0.0002) {
                                    neighbour = layers.get(BiomeLayers.SECONDARY).get(Math.min(random.nextInt(layers.get(BiomeLayers.SECONDARY).size()), random.nextInt(layers.get(BiomeLayers.SECONDARY).size()))); // A basic way to shift probability to lower values.
                                }

                                // If the current block has an ore block as neighbour, try the current block.
                                if ((!Collections.disjoint(neighbourBlocks, layers.get(BiomeLayers.SECONDARY)))) {
                                    for (Material neighbourBlock : neighbourBlocks) {
                                        if (layers.get(BiomeLayers.SECONDARY).contains(neighbourBlock) && random.nextFloat() < -0.01 * layers.get(BiomeLayers.SECONDARY).indexOf(neighbourBlock) + 0.4) {
                                            neighbour = neighbourBlock;
                                        }
                                    }
                                }

                                chunkData.setBlock(x, y, z, neighbour);
                            }

                        }
                    }
                }
            }
        }
    }

    private static final int MAX_SMALL_BUBBLE = 40, MIN_SMALL_BUBBLE = -30;

    private static final int NARROW_CAVE_END = 40, MEDIUM_CAVE_END = 80, LARGE_CAVE_END = 140;

    private static boolean isSmallBubble(int chunkX, int chunkZ, int x, int y, int z) {
        return y < MAX_SMALL_BUBBLE && y > MIN_SMALL_BUBBLE && NoiseMaster.getCaveNoise(chunkX, chunkZ, x, y, z) > 0.7;
    }


    private static final double NARROW_NOISE =0.4, MEDIUM_NOISE=0.3, LARGE_NOISE=0.1;
    private static boolean isCave(int chunkX, int chunkZ, int x, int y, int z) {
        double heightFromSurface =ChunkUtils.getCurrentY(x, chunkX, z, chunkZ) -  y ;
        if (heightFromSurface < NARROW_CAVE_END){
            return NoiseMaster.getCaveWormNoise(chunkX, chunkZ, x, y, z) > NARROW_NOISE;
        } else if (heightFromSurface < MEDIUM_CAVE_END){
            double noiseRequired = (heightFromSurface - NARROW_CAVE_END)/(MEDIUM_CAVE_END - heightFromSurface) * (NARROW_NOISE - MEDIUM_NOISE) + MEDIUM_NOISE;
            return NoiseMaster.getCaveWormNoise(chunkX, chunkZ, x, y, z) > noiseRequired;
        } else if (heightFromSurface < LARGE_CAVE_END){
            double noiseRequired = (heightFromSurface - MEDIUM_CAVE_END)/(LARGE_CAVE_END - heightFromSurface) * (MEDIUM_NOISE - LARGE_NOISE) + LARGE_NOISE;

            return NoiseMaster.getCaveWormNoise(chunkX, chunkZ, x, y, z) > noiseRequired;
        }
        return false;
    }

    @NotNull
    @Override
    public List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return List.of(new TreePopulator(), new FloraPopulator(), new FeaturePopulator());
    }

    @Override
    public boolean shouldGenerateMobs() {
        return false;
    }

}
























