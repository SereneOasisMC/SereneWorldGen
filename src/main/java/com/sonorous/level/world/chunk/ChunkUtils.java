package com.sonorous.level.world.chunk;

import com.sonorous.level.world.biome.biomes.BiomeCategories;
import com.sonorous.level.world.noise.GenerationNoise;
import com.sonorous.level.world.noise.NoiseCategories;
import com.sonorous.level.world.noise.NoiseMaster;

public class ChunkUtils {

    public static final int Y_LIMIT = 300;
    public static final int SEA_LEVEL = 70;
    public static final int LAYER_1_HEIGHT = 10;
    public static final int AVERAGE_HEIGHT = 150;
    public static final int CONTINENTALNESS_DEVIATION = 100;
    public static final int TERRAIN_DEVIATION = 20;
    public static final int DETAIL_DEVIATION = 3;

    public static float getCurrentY(int x, int z){

        float continentalness = GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z) ;


        float terrain = GenerationNoise.getNoise(NoiseCategories.TERRAIN, x ,z);

        if (NoiseMaster.getCategory(x, z, true).equals(BiomeCategories.OFF)) {
            return ChunkUtils.SEA_LEVEL;
        }
        return AVERAGE_HEIGHT + (continentalness * CONTINENTALNESS_DEVIATION);


//        if (KingdomUtils.isInsideKingdomInclWalls(x, z)){
//            return AVERAGE_HEIGHT + (continentalness * CONTINENTALNESS_DEVIATION);
//        }
//
//
//        if (NoiseMaster.getCategory(x, z, true).equals(BiomeCategories.RIVER)  ) {
//            return AVERAGE_HEIGHT + (continentalness * CONTINENTALNESS_DEVIATION) +  (terrain*TERRAIN_DEVIATION);
//        }
//
//
//        if (terrain > 0.9){
//            terrain = (float) Math.pow(8, (terrain-0.9));
//        }
//
//        if (continentalness > 0.8){
//            continentalness = (float) Math.pow(4, (continentalness)) - 2.23143313302f;
//        }
//
//        float detail = GenerationNoise.getNoise(NoiseCategories.DETAIl, x ,z);
//        float currentY = AVERAGE_HEIGHT +
//                (continentalness * CONTINENTALNESS_DEVIATION) +
//                (terrain*TERRAIN_DEVIATION) +
//                DETAIL_DEVIATION * detail;
//        return currentY;
    }


    public static float getCurrentY(int x, int chunkX, int z, int chunkZ){
        return getCurrentY(chunkX * 16 + x, chunkZ * 16 + z);
    }
}
