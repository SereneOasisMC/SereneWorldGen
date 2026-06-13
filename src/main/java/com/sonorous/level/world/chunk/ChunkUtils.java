package com.sonorous.level.world.chunk;

import com.sonorous.level.world.noise.GenerationNoise;
import com.sonorous.level.world.noise.NoiseCategories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChunkUtils {

    public static final int Y_LIMIT = 300;
    public static final int SEA_LEVEL = 70;
    public static final int COAST_HEIGHT = 100;
    public static final int FLATLAND_HEIGHT = 110;
    public static final int WETLAND_HEIGHT = 130;
    public static final int WOODLAND_HEIGHT = 170;
    public static final int ARID_HEIGHT = 175;
    public static final int HIGHLAND_HEIGHT = 230;

    public static final int PRIMARY_HEIGHT = 10;
//    public static final int SECONDARY_HEIGHT = 30;

    public static final float EROSION_FLATTEN = 0.6f;
    public static final float PEAKS_DEVIATION = 1.2f;

    public static final float NOISE_BOUND_OFFLAND = -0.2f;
    public static final float NOISE_BOUND_COASTAL = -0.1f;
    public static final float NOISE_BOUND_FLATLAND = 0.15f;
    public static final float NOISE_BOUND_WETLAND = 0.25f;
    public static final float NOISE_BOUND_WOODLAND = 0.35f;
    public static final float NOISE_BOUND_ARID = 0.45f;
    public static final float NOISE_BOUND_HIGHLAND = 1.0f;



    public static final float BASE_PEAK_INTENSITY_SCALE = 40.0f;
    public static final float RIVER_TAPER_START = 0.90f, RIVER_THRESHOLD = 0.92f;

    private static final Map<Long, HeightData> heightCache = new ConcurrentHashMap<>();

    public static class HeightData {
        public float surfaceY;
        public float primaryY;
        public float secondaryY;

        public HeightData(float surfaceY, float primaryY, float secondaryY) {
            this.surfaceY = surfaceY;
            this.primaryY = primaryY;
            this.secondaryY = secondaryY;
        }
    }

    private static float lerp(float start, float end, float t) {
        return start + t * (end - start);
    }

    private static float getContinentMinByNoise(float noiseVal) {
        if (noiseVal < NOISE_BOUND_OFFLAND) {
            return SEA_LEVEL;
        }

        else if (noiseVal < NOISE_BOUND_COASTAL) {
            return SEA_LEVEL;
        }
        else if (noiseVal < NOISE_BOUND_FLATLAND) {
            return COAST_HEIGHT;
        }
        else if (noiseVal < NOISE_BOUND_WETLAND){
            return FLATLAND_HEIGHT;
        }
        else if (noiseVal < NOISE_BOUND_WOODLAND){
            return WETLAND_HEIGHT;
        }
        else if (noiseVal < NOISE_BOUND_ARID){
            float t = (noiseVal - NOISE_BOUND_WOODLAND) / (NOISE_BOUND_ARID - NOISE_BOUND_WOODLAND);
            return WOODLAND_HEIGHT;
        } else {
            float t = (noiseVal - NOISE_BOUND_ARID) / (NOISE_BOUND_HIGHLAND - NOISE_BOUND_ARID);
            return ARID_HEIGHT;
        }
    }

    private static float getContinentMaxByNoise(float noiseVal) {
        if (noiseVal < NOISE_BOUND_OFFLAND) {
            return COAST_HEIGHT;
        }
        else if (noiseVal < NOISE_BOUND_COASTAL) {
            return FLATLAND_HEIGHT;
        }
        else if (noiseVal < NOISE_BOUND_FLATLAND) {
            return WETLAND_HEIGHT;
        }
        else if (noiseVal < NOISE_BOUND_WETLAND){
            return FLATLAND_HEIGHT;
        }
        else if (noiseVal < NOISE_BOUND_WOODLAND){
            return WOODLAND_HEIGHT;
        }
        else if (noiseVal < NOISE_BOUND_ARID){
            float t = (noiseVal - NOISE_BOUND_WOODLAND) / (NOISE_BOUND_ARID - NOISE_BOUND_WOODLAND);
            return ARID_HEIGHT;
        } else {
            float t = (noiseVal - NOISE_BOUND_ARID) / (NOISE_BOUND_HIGHLAND - NOISE_BOUND_ARID);
            return HIGHLAND_HEIGHT;
        }
    }

    private static float evaluateContinentalSpline(float noiseVal) {
        if (noiseVal < NOISE_BOUND_OFFLAND) {
            return SEA_LEVEL;
        }

        else if (noiseVal < NOISE_BOUND_COASTAL) {
            float t = (noiseVal - NOISE_BOUND_OFFLAND) / (NOISE_BOUND_COASTAL - NOISE_BOUND_OFFLAND);
            return lerp(SEA_LEVEL, COAST_HEIGHT, t);
        }
        else if (noiseVal < NOISE_BOUND_FLATLAND) {
            float t = (noiseVal - NOISE_BOUND_COASTAL) / (NOISE_BOUND_FLATLAND - NOISE_BOUND_COASTAL);
            return lerp(COAST_HEIGHT, FLATLAND_HEIGHT, t);
        }
        else if (noiseVal < NOISE_BOUND_WETLAND){
            float t = (noiseVal - NOISE_BOUND_FLATLAND) / (NOISE_BOUND_WETLAND - NOISE_BOUND_FLATLAND);
            return lerp(FLATLAND_HEIGHT, WETLAND_HEIGHT, t);
        }
        else if (noiseVal < NOISE_BOUND_WOODLAND){
            float t = (noiseVal - NOISE_BOUND_WETLAND) / (NOISE_BOUND_WOODLAND - NOISE_BOUND_WETLAND);
            return lerp(WETLAND_HEIGHT, WOODLAND_HEIGHT, t);
        }
        else if (noiseVal < NOISE_BOUND_ARID){
            float t = (noiseVal - NOISE_BOUND_WOODLAND) / (NOISE_BOUND_ARID - NOISE_BOUND_WOODLAND);
            return lerp(WOODLAND_HEIGHT, ARID_HEIGHT, t);
        } else {
            float t = (noiseVal - NOISE_BOUND_ARID) / (NOISE_BOUND_HIGHLAND - NOISE_BOUND_ARID);
            return lerp(ARID_HEIGHT, HIGHLAND_HEIGHT, t);
        }
    }

    private static int OFFSET = 1;
    private static int DISTANCE = 5;
    private static float calculateSurfaceHeight(int x, int z, float continentalness, float erosion, float weirdness, float detailNoise, float riverNoise) {



        float erosionFactor = (erosion + 1.0f) * 0.5f;
        float baseHeight = evaluateContinentalSpline(continentalness);



        if (riverNoise < RIVER_THRESHOLD){
            float riverFactor = Math.max(0, (riverNoise - RIVER_TAPER_START) / ( RIVER_THRESHOLD - RIVER_TAPER_START) );
            return lerp(baseHeight,  baseHeight - 2, riverFactor);
        } else {
            float riverNoiseEast  = GenerationNoise.getNoise(NoiseCategories.RIVER, x + OFFSET, z);
            float riverNoiseWest  = GenerationNoise.getNoise(NoiseCategories.RIVER, x - OFFSET, z);
            float riverNoiseNorth = GenerationNoise.getNoise(NoiseCategories.RIVER, x, z - OFFSET);
            float riverNoiseSouth = GenerationNoise.getNoise(NoiseCategories.RIVER, x, z + OFFSET);

            float deltaX = riverNoiseEast - riverNoiseWest;
            float deltaZ = riverNoiseSouth - riverNoiseNorth;

            float localTerrainSlope;
            if (Math.abs(deltaX) > Math.abs(deltaZ)) {
                float terrainNorth = evaluateContinentalSpline(GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z - DISTANCE));
                float terrainSouth = evaluateContinentalSpline(GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z + DISTANCE));
                localTerrainSlope = (terrainNorth + terrainSouth) * 0.5f;
            } else {
                float terrainEast = evaluateContinentalSpline(GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x + DISTANCE, z));
                float terrainWest = evaluateContinentalSpline(GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x - DISTANCE, z));
                localTerrainSlope = (terrainEast + terrainWest) * 0.5f;
            }

            float SLOPE_GENTLENESS = 0.3f;
            localTerrainSlope = lerp(baseHeight, localTerrainSlope, SLOPE_GENTLENESS);

            float riverFactor = Math.max(0, (riverNoise - RIVER_THRESHOLD) / (1 - RIVER_THRESHOLD)) *  Math.max(0, (riverNoise - RIVER_THRESHOLD) / (1 - RIVER_THRESHOLD));
            float riverBedHeight = localTerrainSlope - 2.0f;

            return lerp(baseHeight - 1.0f, riverBedHeight, riverFactor);

        }



//        float peakIntensity = (1.0f - erosionFactor);
//        float ridgeNoise = 1.0f - Math.abs(weirdness);
//        float pinchedPeaks = (float) Math.pow(ridgeNoise, 3.0) * PEAKS_DEVIATION * BASE_PEAK_INTENSITY_SCALE * peakIntensity;
//
//        float globalProgress = (continentalness + 1.0f) * 0.5f;
//        float smoothDetailFactor = smoothstep(0.0f, 1.0f, globalProgress);
//        float dynamicDetailAmplitude = 4.0f + (smoothDetailFactor * 16.0f);
//        float microDetail = detailNoise * dynamicDetailAmplitude;

//        return baseHeight + pinchedPeaks + microDetail;
    }

//    private static float calculatePrimaryHeight(float continentalness, float erosion, float weirdness, float detailNoise) {
//        float erosionFactor = (erosion + 1.0f) * 0.5f;
//        float baseHeight = evaluateContinentalSpline(continentalness);
//
//        float peakIntensity = (1.0f - erosionFactor);
//        float ridgeNoise = 1.0f - Math.abs(weirdness);
//        float pinchedPeaks = (float) Math.pow(ridgeNoise, 3.0) * PEAKS_DEVIATION * BASE_PEAK_INTENSITY_SCALE * peakIntensity;
//
//        float globalProgress = (continentalness + 1.0f) * 0.5f;
//        float smoothDetailFactor = smoothstep(0.0f, 1.0f, globalProgress);
//        float dynamicDetailAmplitude = 4.0f + (smoothDetailFactor * 16.0f);
//        float microDetail = detailNoise * dynamicDetailAmplitude;
//
//        return baseHeight + pinchedPeaks + microDetail;
//    }
//
//    private static float calculateSecondaryHeight(float continentalness, float erosion, float weirdness, float detailNoise) {
//        float erosionFactor = (erosion + 1.0f) * 0.5f;
//        float baseHeight = evaluateContinentalSpline(continentalness);
//
//        float peakIntensity = (1.0f - erosionFactor);
//        float ridgeNoise = 1.0f - Math.abs(weirdness);
//        float pinchedPeaks = (float) Math.pow(ridgeNoise, 3.0) * PEAKS_DEVIATION * BASE_PEAK_INTENSITY_SCALE * peakIntensity;
//
//        float globalProgress = (continentalness + 1.0f) * 0.5f;
//        float smoothDetailFactor = smoothstep(0.0f, 1.0f, globalProgress);
//        float dynamicDetailAmplitude = 4.0f + (smoothDetailFactor * 16.0f);
//        float microDetail = detailNoise * dynamicDetailAmplitude;
//
//        return baseHeight + pinchedPeaks + microDetail;
//    }

    public static HeightData getHeightData(int x, int z) {
        long key = ((long) x << 32) | (z & 0xFFFFFFFFL);

        return heightCache.computeIfAbsent(key, k -> {
            float continentalness = GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z);
            float erosion = GenerationNoise.getNoise(NoiseCategories.EROSION, x, z);
            float weirdness = GenerationNoise.getNoise(NoiseCategories.WEIRDNESS, x, z);
            float detailNoise = GenerationNoise.getNoise(NoiseCategories.DETAIL, x, z);

            float riverNoise = GenerationNoise.getNoise(NoiseCategories.RIVER, x, z);

            

            float terrainHeight = calculateSurfaceHeight(x,z, continentalness, erosion, weirdness, detailNoise, riverNoise);

            HeightData data = new HeightData(terrainHeight, terrainHeight - 1, terrainHeight- PRIMARY_HEIGHT);

                return data;


        });
    }

    public static float getCurrentY(int x, int z) {
        return getHeightData(x, z).surfaceY;
    }

    public static float getCurrentY(int x, int chunkX, int z, int chunkZ){
        return getCurrentY(chunkX * 16 + x, chunkZ * 16 + z);
    }

    public static void clearCache() {
        heightCache.clear();
    }
}