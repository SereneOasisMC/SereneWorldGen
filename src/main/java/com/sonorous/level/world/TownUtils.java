package com.sonorous.level.world;


import com.sonorous.level.world.noise.GenerationNoise;
import com.sonorous.level.world.noise.NoiseCategories;

public class TownUtils {

    public static final int WALL_HEIGHT = 20;

    public static boolean isInsideTownInclWalls(int x, int z){
        double targetContinentalness = GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z) ;

        return (GenerationNoise.getNoise(NoiseCategories.TOWN_BORDERS, x,  z) > 0.7) && targetContinentalness >= -0.1 && targetContinentalness < 0.5;
//        return (GenerationNoise.getNoise(NoiseCategories.KINGDOM_BORDERS, x,  z) > 0.5);
    }

    public static boolean isInsideTownExclWalls(int x, int z){
        return isInsideTownInclWalls(x,z)&&
                (isInsideTownInclWalls(x-16, z) && isInsideTownInclWalls(x, z-16) && isInsideTownInclWalls(x+16, z) && isInsideTownInclWalls(x, z+16));
//        return (GenerationNoise.getNoise(NoiseCategories.KINGDOM_BORDERS, x,  z) > 0.3);
    }


    public static boolean isTownWalls(int x, int z){
        return isInsideTownInclWalls(x,z)&& !(isInsideTownInclWalls(x-1, z) && isInsideTownInclWalls(x, z-1) && isInsideTownInclWalls(x+1, z) && isInsideTownInclWalls(x, z+1));
    }

}