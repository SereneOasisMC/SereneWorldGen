package com.sonorous.level.world.noise;

import com.mojang.datafixers.util.Pair;
import com.sonorous.level.world.KingdomUtils;
import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import com.sonorous.libs.FastNoiseLite;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import java.util.HashMap;
import java.util.List;

public class NoiseMaster {

    public static void initNoise(){

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.01f, NoiseCategories.TERRAIN).
                    attachFractal(FastNoiseLite.FractalType.FBm, 4, 1.3f, 0.3f, -0.5f);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0001f, NoiseCategories.CONTINENTALNESS).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0, 0, 0);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0002f, NoiseCategories.TEMPERATURE).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3f);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0002f, NoiseCategories.HUMIDITY).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3f);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.01f, NoiseCategories.DETAIl).
                    attachFractal(FastNoiseLite.FractalType.FBm, 1, 0, 0, 0);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.0001f, NoiseCategories.WEIRDNESS).
                    attachFractal(FastNoiseLite.FractalType.FBm, 3, 0.5f, 0.3f, 3.0f);

            new GenerationNoise(FastNoiseLite.NoiseType.OpenSimplex2, 0.001f, NoiseCategories.RIVER).
                attachFractal(FastNoiseLite.FractalType.PingPong, 3, 10, 0.5f, 0).
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

    public static BiomeCategories getCategory(int x, int z, boolean ocean){
        BiomeCategories category = null;
        double targetContinentalness = GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z) ;


        if (!KingdomUtils.isInsideKingdomInclWalls(x, z) && GenerationNoise.getNoise(NoiseCategories.RIVER, x ,z ) > 0.95 && targetContinentalness >= -0.1 && targetContinentalness < 0.3 ) {
            category = BiomeCategories.RIVER;
        } else {

            if (targetContinentalness <= -0.2 && ocean) { // offland
                category = BiomeCategories.OFF;
            } else if (targetContinentalness <= -0.1) { // coastal
                category = BiomeCategories.COASTAL;
            } else if (targetContinentalness > -0.1 && targetContinentalness <= 0.15) { // flatland
                category = BiomeCategories.FLAT;
            } else if (targetContinentalness > 0.15 && targetContinentalness <= 0.25) { // wet
                category = BiomeCategories.WET;
            } else if (targetContinentalness > 0.25 && targetContinentalness <= 0.35) { // woodland
                category = BiomeCategories.WOOD;

            } else if (targetContinentalness > 0.35 && targetContinentalness <= 0.5) { // aridland
                category = BiomeCategories.ARID;

            } else { // highland
                category = BiomeCategories.HIGH;

            }
        }
        return category;

    }

    /***
     * Calculates which biome representation represents a specified location
     * @param x the X of the Location we want to obtain the biome representation for
     * @param z the Z of the Location we want to obtain the biome representation for
     * @param ocean whether under the sea level
     * @return A best fitting biome representation
     */
    private static BiomeRepresentation getBiomeRepresentation(int x, int z, boolean ocean){
        double targetTemeprature = GenerationNoise.getNoise(NoiseCategories.TEMPERATURE, x, z) ;
        double targetHumidity = GenerationNoise.getNoise(NoiseCategories.HUMIDITY, x, z) ;
        double weirdness = GenerationNoise.getNoise(NoiseCategories.WEIRDNESS, x ,z) ;


        // Below uses an algorithm to select which Biome out of the already chosen category is most appropriate.
        // There is a score given based on the difference between ideal characteristics which is meant to be minimised.
        // Weirdness is also taken into account to minimise the amount of more unusual biomes
        return BiomeRepresentation.getBiomeRepresentations(getCategory(x, z, ocean))
                .stream()
                .map(biomeRepresentation -> {
                    return Pair.of(biomeRepresentation,
                             (Math.abs(biomeRepresentation.getHumidity() - targetHumidity) )
                            + (Math.abs(biomeRepresentation.getTemperature() - targetTemeprature) )
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
     * @param ocean whether under the sea level
     * @return A best fitting biome
     */
    public static Biome getBiome(int x, int z, boolean ocean){
       return getBiomeRepresentation(x, z, ocean).getBiome();
    }

    /***
     * Calculates what the layers of the terrain at a given location should be
     * @param x the X of the Location we want to obtain the Biome for
     * @param z the Z of the Location we want to obtain the Biome for
     * @param ocean whether under the sea level
     * @return A best fitting Biome
     */
    public static HashMap<BiomeLayers, List<Material>> getBiomeLayers(int x, int z, boolean ocean){
        return getBiomeRepresentation(x, z, ocean).getLayers();
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
