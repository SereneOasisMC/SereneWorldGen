package com.sonorous.level.world.noise;

import com.mojang.datafixers.util.Pair;
import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import com.sonorous.level.world.chunk.ChunkUtils;
import com.sonorous.libs.FastNoiseLite;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.Map;
import java.util.List;

public class NoiseMaster {

    public static void initNoise(){
        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.01f, NoiseCategories.TERRAIN).
                attachFractal(FastNoiseLite.FractalType.FBm, 4, 1.3f, 0.3f, -0.5f);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0005f, NoiseCategories.EROSION)
                .attachFractal(FastNoiseLite.FractalType.Ridged, 4, 2.0f, 0.5f, 0);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2S, 0.0002f, NoiseCategories.CONTINENTALNESS).
                attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 2.0f, 0);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0002f, NoiseCategories.TEMPERATURE).
                attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3f);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0002f, NoiseCategories.HUMIDITY).
                attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3f);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.02f, NoiseCategories.DETAIL)
                .attachFractal(FastNoiseLite.FractalType.FBm, 4, 2.0f, 0.4f, 0);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0001f, NoiseCategories.WEIRDNESS).
                attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3.0f);

        new GenerationNoise(FastNoiseLite.NoiseType.ValueCubic, 0.005f, NoiseCategories.RIVER).
                attachFractal(FastNoiseLite.FractalType.PingPong, 1, 0,0, 0).
                attachPingPong(1.0f);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0025F, NoiseCategories.CAVES).
                attachFractal(FastNoiseLite.FractalType.FBm, 3, 0, 0.4f, 0);

        new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.013f, NoiseCategories.CAVE_WORMS );

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

        if (targetContinentalness <= ChunkUtils.NOISE_BOUND_OFFLAND) {
            return BiomeCategories.OFF;
        }

        if (GenerationNoise.getNoise(NoiseCategories.RIVER, x, z) > ChunkUtils.RIVER_THRESHOLD) {
//            if (targetContinentalness <= ChunkUtils.NOISE_BOUND_FLATLAND) {
                return BiomeCategories.RIVER;
//            }
        }
         if (targetContinentalness <= ChunkUtils.NOISE_BOUND_COASTAL) {
            return BiomeCategories.COASTAL;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_FLATLAND) {
            return BiomeCategories.FLAT;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_WETLAND) {
            return BiomeCategories.WET;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_WOODLAND) {
            return BiomeCategories.WOOD;
        }
        else if (targetContinentalness <= ChunkUtils.NOISE_BOUND_ARID) {
            return BiomeCategories.ARID;
        }
        else {
            return BiomeCategories.HIGH;
        }
    }

    public static BiomeRepresentation getBiomeRepresentation(int x, int z){
        double targetTemperature = GenerationNoise.getNoise(NoiseCategories.TEMPERATURE, x, z) ;
        double targetHumidity = GenerationNoise.getNoise(NoiseCategories.HUMIDITY, x, z) ;
        double weirdness = GenerationNoise.getNoise(NoiseCategories.WEIRDNESS, x ,z) ;

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

    public static Biome getBiome(int x, int z){
        return getBiomeRepresentation(x, z).getBiome();
    }

    public static Map<BiomeLayers, List<Material>> getBiomeLayers(int x, int z){
        return getBiomeRepresentation(x, z).getLayers();
    }

    public static float getCaveNoise(int chunkX, int chunkZ, int x, int y, int z){
        return GenerationNoise.getNoise(NoiseCategories.CAVES, chunkX * 16 + x, y, chunkZ * 16 + z);
    }

    public static float getCaveWormNoise(int chunkX, int chunkZ, int x, int y, int z){
        return GenerationNoise.getNoise(NoiseCategories.CAVE_WORMS, chunkX * 16 + x, y, chunkZ * 16 + z);
    }

    public static float getFloraNoise(int chunkX, int chunkZ, int x, int z){
        return GenerationNoise.getNoise(NoiseCategories.FLORA, chunkX * 16 + x, chunkZ * 16 + z);
    }
}