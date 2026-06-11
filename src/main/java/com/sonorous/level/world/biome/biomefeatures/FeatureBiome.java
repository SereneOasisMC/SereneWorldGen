package com.sonorous.level.world.biome.biomefeatures;

import java.util.HashMap;

public interface FeatureBiome {

    /***
     * Represents the features and a probability to decide how often it generates
     * @return A HashMap between a feature and a double between 0 and 1
     */
    HashMap<Feature, Double> getFeatures();
}
