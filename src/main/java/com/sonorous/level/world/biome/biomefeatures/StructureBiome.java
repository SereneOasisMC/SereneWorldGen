package com.sonorous.level.world.biome.biomefeatures;

import java.util.HashMap;

public interface StructureBiome {

    /***
     * Retrieves the structures associated with a Biome
     * @return a HashMap linking the name of a structure to a probability from 0 to 1 (the chance of it spawning)
     */
    HashMap<String, Double>getStructures();
}
