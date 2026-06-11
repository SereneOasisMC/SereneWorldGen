package com.sonorous.level.world;

import com.sonorous.level.world.noise.GenerationNoise;
import com.sonorous.level.world.noise.NoiseCategories;

public class KingdomUtils {

    public static final int WALL_HEIGHT = 40;

    public static boolean isInsideKingdomInclWalls(int x, int z){
        double targetContinentalness = GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z) ;

        return (GenerationNoise.getNoise(NoiseCategories.KINGDOM_BORDERS, x,  z) > 0.7) && targetContinentalness >= -0.1 && targetContinentalness < 0.5;
//        return (GenerationNoise.getNoise(NoiseCategories.KINGDOM_BORDERS, x,  z) > 0.5);
    }

    public static boolean isInsideKingdomExclWalls(int x, int z){
        return isInsideKingdomInclWalls(x,z)&&
                (isInsideKingdomInclWalls(x-16, z) && isInsideKingdomInclWalls(x, z-16) && isInsideKingdomInclWalls(x+16, z) && isInsideKingdomInclWalls(x, z+16));
//        return (GenerationNoise.getNoise(NoiseCategories.KINGDOM_BORDERS, x,  z) > 0.3);
    }


    public static boolean isKingdomWalls(int x, int z){
        return isInsideKingdomInclWalls(x,z)&& !(isInsideKingdomInclWalls(x-1, z) && isInsideKingdomInclWalls(x, z-1) && isInsideKingdomInclWalls(x+1, z) && isInsideKingdomInclWalls(x, z+1));
    }

}