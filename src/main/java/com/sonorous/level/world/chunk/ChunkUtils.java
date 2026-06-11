package com.sonorous.level.world.chunk;

import com.sonorous.level.world.noise.GenerationNoise;
import com.sonorous.level.world.noise.NoiseCategories;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChunkUtils {

    public static final int Y_LIMIT = 300;
    public static final int SEA_LEVEL = 70;
    public static final int LAYER_1_HEIGHT = 10;

    public static final float EROSION_FLATTEN = 0.6f;
    public static final float PEAKS_DEVIATION = 1.2f;

    // Continentalness Noise Bounds
    public static final float NOISE_BOUND_OFFLAND = -0.2f;
    public static final float NOISE_BOUND_COASTAL = -0.1f;
    public static final float NOISE_BOUND_FLATLAND = 0.15f;
    public static final float NOISE_BOUND_WETLAND = 0.25f;
    public static final float NOISE_BOUND_WOODLAND = 0.35f;
    public static final float NOISE_BOUND_ARID = 0.45f;

    // Height Targets
    public static final float HEIGHT_DEEP_OCEAN = 35.0f;
    public static final float HEIGHT_SEA_LEVEL_FLOOR = (float) (SEA_LEVEL - 5); // 65.0f
    public static final float HEIGHT_SHORE_LINE = (float) (SEA_LEVEL + 2);       // 72.0f (Beach peaks above water)
    public static final float HEIGHT_HIGHLAND_BASE = (float) (SEA_LEVEL + 4);    // 74.0f
    public static final float HEIGHT_MOUNTAIN_PEAK = 220.0f;

    public static final float BASE_PEAK_INTENSITY_SCALE = 40.0f;
    public static final float RIVER_THRESHOLD = 0.9f;

    private static final Map<Long, HeightData> heightCache = new ConcurrentHashMap<>();

    public static class HeightData {
        public final float surfaceY;   // True top surface (Water level for rivers/oceans, solid block for land)
        public final float riverbedY;  // Solid ground underneath river water

        public HeightData(float surfaceY, float riverbedY) {
            this.surfaceY = surfaceY;
            this.riverbedY = riverbedY;
        }
    }

    private static float lerp(float start, float end, float t) {
        return start + t * (end - start);
    }

    private static float evaluateContinentalSpline(float noiseVal) {
        if (noiseVal < NOISE_BOUND_OFFLAND) { // Below -0.2
            float t = (noiseVal - (-1.0f)) / (NOISE_BOUND_OFFLAND - (-1.0f));
            return lerp(HEIGHT_DEEP_OCEAN, HEIGHT_SEA_LEVEL_FLOOR, t);
        }
        else if (noiseVal < NOISE_BOUND_COASTAL) { // -0.2 to -0.1 (Shoreline transition)
            float t = (noiseVal - NOISE_BOUND_OFFLAND) / (NOISE_BOUND_COASTAL - NOISE_BOUND_OFFLAND);
            // Smoothly climbs from deep water floor (65) up to dry beach (72), crossing sea level (70)
            return lerp(HEIGHT_SEA_LEVEL_FLOOR, HEIGHT_SHORE_LINE, t);
        }
        else if (noiseVal < NOISE_BOUND_FLATLAND) { // -0.1 to 0.15
            float t = (noiseVal - NOISE_BOUND_COASTAL) / (NOISE_BOUND_FLATLAND - NOISE_BOUND_COASTAL);
            // Continues from dry beach (72) up to inland valleys (74)
            return lerp(HEIGHT_SHORE_LINE, HEIGHT_HIGHLAND_BASE, t);
        }
        else { // Above 0.15
            float t = (noiseVal - NOISE_BOUND_FLATLAND) / (1.0f - NOISE_BOUND_FLATLAND);
            return lerp(HEIGHT_HIGHLAND_BASE, HEIGHT_MOUNTAIN_PEAK, t);
        }
    }

    public static HeightData getHeightData(int x, int z) {
        long key = ((long) x << 32) | (z & 0xFFFFFFFFL);

        return heightCache.computeIfAbsent(key, k -> {
            float continentalness = GenerationNoise.getNoise(NoiseCategories.CONTINENTALNESS, x, z);
            float erosion = GenerationNoise.getNoise(NoiseCategories.EROSION, x, z);
            float weirdness = GenerationNoise.getNoise(NoiseCategories.WEIRDNESS, x, z);

            float erosionFactor = (erosion + 1.0f) * 0.5f;
            float baseHeight = evaluateContinentalSpline(continentalness);

            float peakIntensity = (1.0f - erosionFactor);
            float peaksVariation = Math.abs(weirdness) * PEAKS_DEVIATION * BASE_PEAK_INTENSITY_SCALE * peakIntensity;

            // This is the actual solid ground floor (ocean floor or land surface)
            float terrainHeight = baseHeight + peaksVariation;

            float riverNoise = GenerationNoise.getNoise(NoiseCategories.RIVER, x, z);

            // Default values assume normal land
            float finalSurfaceY = terrainHeight;
            float solidRiverbedY = terrainHeight;

            // 1. Handle Rivers
            if (riverNoise > RIVER_THRESHOLD && continentalness >= NOISE_BOUND_OFFLAND && continentalness < NOISE_BOUND_ARID) {
                float maxTrenchDepth = 6.0f;
                float riverTrench = riverNoise * maxTrenchDepth;

                // Water surface snaps to sea level or stays flat with high terrain
                finalSurfaceY = Math.max((float) SEA_LEVEL, terrainHeight);
                // Riverbed drops below the water surface
                solidRiverbedY = finalSurfaceY - riverTrench;
            }
            // 2. Handle Oceans / Seas (Any terrain that dips below Sea Level)
            else if (terrainHeight < SEA_LEVEL) {
                // CRITICAL: The visible surface of an ocean is ALWAYS the water line (SEA_LEVEL)
                finalSurfaceY = (float) SEA_LEVEL;
                // The actual solid block floor is the deep terrain height we calculated
                solidRiverbedY = terrainHeight;
            }

            // Apply global world limits
            float boundedSurfaceY = Math.max(LAYER_1_HEIGHT, Math.min(Y_LIMIT, finalSurfaceY));
            float boundedRiverbedY = Math.max(LAYER_1_HEIGHT, Math.min(boundedSurfaceY, solidRiverbedY));

            return new HeightData(boundedSurfaceY, boundedRiverbedY);
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