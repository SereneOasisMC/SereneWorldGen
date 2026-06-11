package com.sonorous.level.world.noise;

import com.mojang.datafixers.util.Pair;
import com.sonorous.level.world.KingdomUtils;
import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import com.sonorous.level.world.chunk.ChunkUtils;
import com.sonorous.libs.FastNoiseLite;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.HashMap;
import java.util.List;

public class NoiseMaster {

    public static void initNoise(){

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.01f, NoiseCategories.TERRAIN).
                    attachFractal(FastNoiseLite.FractalType.FBm, 4, 1.3f, 0.3f, -0.5f);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.001f, NoiseCategories.EROSION)
                .attachFractal(FastNoiseLite.FractalType.Ridged, 4, 2.0f, 0.5f, 0);



            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2S, 0.0001f, NoiseCategories.CONTINENTALNESS).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 2.0f, 0);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0002f, NoiseCategories.TEMPERATURE).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3f);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0002f, NoiseCategories.HUMIDITY).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3f);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.01f, NoiseCategories.DETAIl).
                    attachFractal(FastNoiseLite.FractalType.FBm, 1, 0, 0, 0);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0001f, NoiseCategories.WEIRDNESS).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3.0f);

            new GenerationNoise(FastNoiseLite.NoiseType.ValueCubic, 0.001f, NoiseCategories.RIVER).
                attachFractal(FastNoiseLite.FractalType.PingPong, 1, 0,0, 0).
                 attachPingPong(1.0f);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0025F, NoiseCategories.CAVES).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0, 0.4f, 0);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.013f, NoiseCategories.CAVE_WORMS );
//                .attachFractal(FastNoiseLite.FractalType.Ridged,1, 0, 0,0)
//                .improveXYPlanes();

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.05F, NoiseCategories.FLORA).
                    attachFractal(FastNoiseLite.FractalType.FBm, 2, 0, 0, 0);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2,0.02F, NoiseCategories.CUSTOM_TREES).
                    attachFractal(FastNoiseLite.FractalType.FBm, 2, 0, 0, 0);


        new GenerationNoise(FastNoiseLite.NoiseType.Cellular, 0.001f, NoiseCategories.KINGDOM_BORDERS ).
                attachFractal(FastNoiseLite.FractalType.Ridged, 3, 0f, 0, 0).
                attachCellular(1.3f, FastNoiseLite.CellularReturnType.CellValue);


        new GenerationNoise(FastNoiseLite.NoiseType.Cellular, 0.02f, NoiseCategories.KINGDOM_PATHS ).
                attachFractal(FastNoiseLite.FractalType.Ridged, 1, 0, 0, 0).
                attachCellular(1.3f, FastNoiseLite.CellularReturnType.Distance2Div);

        new GenerationNoise(FastNoiseLite.NoiseType.Cellular, 0.002f, NoiseCategories.TOWN_BORDERS ).
                attachFractal(FastNoiseLite.FractalType.Ridged, 3, 0f, 0, 0).
                attachCellular(1.3f, FastNoiseLite.CellularReturnType.CellValue);


        new GenerationNoise(FastNoiseLite.NoiseType.Cellular, 0.03f, NoiseCategories.TOWN_PATHS ).
                attachFractal(FastNoiseLite.FractalType.Ridged, 1, 0, 0, 0).
                attachCellular(1.3f, FastNoiseLite.CellularReturnType.Distance2Div);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.002f, NoiseCategories.ROADS).
                attachFractal(FastNoiseLite.FractalType.FBm, 1, 0, 0, 0);

    }

    public static BiomeCategories getCategory(int x, int z) {
        double targetContinentalness = GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z);

        // 1. Check for Rivers first if you want them to cut through multiple biomes
        // (Adjust the threshold or add a continentalness cap if you don't want rivers in high mountains)
        if (GenerationNoise.getNoise(NoiseCategories.RIVER, x, z) > ChunkUtils.RIVER_THRESHOLD) {
            if (targetContinentalness <= ChunkUtils.NOISE_BOUND_FLATLAND) {
                return BiomeCategories.RIVER;
            }
        }

        // 2. Step-ladder check from lowest noise to highest noise
        if (targetContinentalness <= ChunkUtils.NOISE_BOUND_OFFLAND) { // <= -0.2
            return BiomeCategories.OFF;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_COASTAL) { // -0.2 to -0.1
            return BiomeCategories.COASTAL;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_FLATLAND) { // -0.1 to 0.15
            return BiomeCategories.FLAT;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_WETLAND) { // 0.15 to 0.25
            return BiomeCategories.WET;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_WOODLAND) { // 0.25 to 0.35
            return BiomeCategories.WOOD;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_ARID) { // 0.35 to 0.45
            return BiomeCategories.ARID;
        }
        else { // > 0.45
            return BiomeCategories.HIGH;
        }
    }

    /***
     * Calculates which biome representation represents a specified location
     * @param x the X of the Location we want to obtain the biome representation for
     * @param z the Z of the Location we want to obtain the biome representation for
     * @return A best fitting biome representation
     */
    private static BiomeRepresentation getBiomeRepresentation(int x, int z){
        double targetTemperature = GenerationNoise.getNoise(NoiseCategories.TEMPERATURE, x, z) ;
        double targetHumidity = GenerationNoise.getNoise(NoiseCategories.HUMIDITY, x, z) ;
        double weirdness = GenerationNoise.getNoise(NoiseCategories.WEIRDNESS, x ,z) ;


        // Below uses an algorithm to select which Biome out of the already chosen category is most appropriate.
        // There is a score given based on the difference between ideal characteristics which is meant to be minimised.
        // Weirdness is also taken into account to minimise the amount of more unusual biomes
        return BiomeRepresentation.getBiomeRepresentations(getCategory(x, z))
                .stream()
                .map(biomeRepresentation -> {
                    return Pair.of(biomeRepresentation,
                             (Math.abs(biomeRepresentation.getHumidity() - targetHumidity) )
                            + (Math.abs(biomeRepresentation.getTemperature() - targetTemperature) )
                            + (Math.abs(weirdness * (biomeRepresentation.getWeirdness() -  weirdness) )));
                })
                .reduce((biomeRepresentationDoublePair, biomeRepresentationDoublePair2) -> {
                    if (biomeRepresentationDoublePair.getSecond() < biomeRepresentationDoublePair2.getSecond()) {
                        return biomeRepresentationDoublePair;
                    }
                    return biomeRepresentationDoublePair2;
                })
                .get().getFirst();
    }

    /***
     * Calculates which biome represents a specified location
     * @param x the X of the Location we want to obtain the biome for
     * @param z the Z of the Location we want to obtain the biome for
     * @return A best fitting biome
     */
    public static Biome getBiome(int x, int z){
       return getBiomeRepresentation(x, z).getBiome();
    }

    /***
     * Calculates what the layers of the terrain at a given location should be
     * @param x the X of the Location we want to obtain the Biome for
     * @param z the Z of the Location we want to obtain the Biome for
     * @return A best fitting Biome
     */
    public static HashMap<BiomeLayers, List<Material>> getBiomeLayers(int x, int z){
        return getBiomeRepresentation(x, z).getLayers();
    }

    /***
     * Obtains the noise used to generate caves
     * @param chunkX The value representing the Chunk X (it's actual X coordinate divided by 16)
     * @param chunkZ The value representing the Chunk Z (it's actual Z coordinate divided by 16)
     * @param x The X value relative to the chunk (from 0-15)
     * @param y The Y value relative to the chunk (from 0-15)
     * @param z The Z value relative to the chunk (from 0-15)
     * @return A value from -1 to 1 used to control cave generation
     */
    public static float getCaveNoise(int chunkX, int chunkZ, int x, int y, int z){
        return GenerationNoise.getNoise(NoiseCategories.CAVES, chunkX * 16 + x, y, chunkZ * 16 + z);
    }

    /***
     * Obtains the noise used to generate caves
     * @param chunkX The value representing the Chunk X (it's actual X coordinate divided by 16)
     * @param chunkZ The value representing the Chunk Z (it's actual Z coordinate divided by 16)
     * @param x The X value relative to the chunk (from 0-15)
     * @param y The Y value
     * @param z The Z value relative to the chunk (from 0-15)
     * @return A value from -1 to 1 used to control cave generation
     */
    public static float getCaveWormNoise(int chunkX, int chunkZ, int x, int y, int z){
        return GenerationNoise.getNoise(NoiseCategories.CAVE_WORMS, chunkX * 16 + x, y, chunkZ * 16 + z);
    }

    /***
     * Obtains the noise used to generate Flora
     * @param chunkX The value representing the Chunk X (it's actual X coordinate divided by 16)
     * @param chunkZ The value representing the Chunk Z (it's actual Z coordinate divided by 16)
     * @param x The X value relative to the chunk (from 0-15)
     * @param z The Z value relative to the chunk (from 0-15)
     * @return A value from -1 to 1 used to control flora generation
     */
    public static float getFloraNoise(int chunkX, int chunkZ, int x, int z){
        return GenerationNoise.getNoise(NoiseCategories.FLORA, chunkX * 16 + x, chunkZ * 16 + z);
    }

}
