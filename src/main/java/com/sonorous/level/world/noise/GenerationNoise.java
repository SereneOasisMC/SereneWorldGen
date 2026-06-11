package com.sonorous.level.world.noise;

import com.sonorous.libs.FastNoiseLite;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/***
 * A Class used as a Wrapper with FastNoiseLite
 */
public class GenerationNoise {

    /***
     * A HashMap with NoiseCategories as keys and noise functions as values
     */
    private static final ConcurrentHashMap<NoiseCategories, FastNoiseLite> NOISE_TYPE_FUNCTION_MAP = new ConcurrentHashMap<>();

    /***
     * Retrieves the noise value for a given Location
     * @param noiseCategories the type of noise to retrieve the value for
     * @param x the X value of a location
     * @param z the Y value of a location
     * @return a value from -1 to 1 representing the noise, which may be stretched
     */
    public static float getNoise(NoiseCategories noiseCategories, int x, int z){
        float noise = NOISE_TYPE_FUNCTION_MAP.get(noiseCategories).GetNoise(x, z);
//        switch (noiseCategories){
//            case CONTINENTALNESS -> {
//                return noise*1.7f;
//            }
//            case HUMIDITY, TEMPERATURE -> {
//                return noise*1.5f;
//            }
//            case CUSTOM_TREES -> {
//                return noise *1.6f;
//            }
//            default -> {
//                return noise;
//            }
//        }
        return noise;
    }

    /***
     * Retrieves the noise value for a given Location when the Y value is relevant
     * @param noiseCategories the type of noise to retrieve the value for
     * @param x the X value of a location
     * @param y the Y value of a location
     * @param z the Z value of a location
     * @return a value from -1 to 1 representing the noise
     */
    public static float getNoise(NoiseCategories noiseCategories, int x, int y, int z){
        return NOISE_TYPE_FUNCTION_MAP.get(noiseCategories).GetNoise(x, z, y) ;
    }

    private final FastNoiseLite noise;

    /***
     * Generates noise
     * @param noiseType
     * @param frequency
     * @param noiseCategories
     */
    public GenerationNoise(FastNoiseLite.NoiseType noiseType, float frequency, NoiseCategories noiseCategories){
        noise = new FastNoiseLite();
        noise.SetNoiseType(noiseType);
        noise.SetFrequency(frequency);
        noise.SetSeed(new Random().nextInt(1,10000));
        NOISE_TYPE_FUNCTION_MAP.put(noiseCategories, noise);
    }


    public GenerationNoise attachFractal(FastNoiseLite.FractalType fractalType,int octaves, float gain, float lacunarity, float weightedStrength ){
        noise.SetFractalType(fractalType);
        noise.SetFractalOctaves(octaves);
        noise.SetFractalGain(gain);
        noise.SetFractalLacunarity(lacunarity);
        noise.SetFractalWeightedStrength(weightedStrength);
        return this;
    }

    public GenerationNoise attachPingPong(float pingPongStrength){
        noise.SetFractalPingPongStrength(pingPongStrength);

        return this;
    }

    public GenerationNoise attachDomainWarp( float domainWarpAmp){
        noise.SetDomainWarpType(FastNoiseLite.DomainWarpType.OpenSimplex2);
        noise.SetDomainWarpAmp(domainWarpAmp);
        return this;
    }

    public GenerationNoise attachCellular(float jitter, FastNoiseLite.CellularReturnType cellularReturnType){
        noise.SetCellularDistanceFunction(FastNoiseLite.CellularDistanceFunction.Hybrid);
        noise.SetCellularReturnType(cellularReturnType);
        noise.SetCellularJitter(jitter);
        return this;
    }

    public GenerationNoise improveXYPlanes(){
        noise.SetRotationType3D(FastNoiseLite.RotationType3D.ImproveXYPlanes);
        return this;
    }
}
