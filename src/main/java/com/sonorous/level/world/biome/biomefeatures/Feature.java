package com.sonorous.level.world.biome.biomefeatures;

import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.HashMap;

/***
 * A class used to generate a feature,
 * where a feature is a small structure enclosed within a chunk
 */
public class Feature {

    /***
     * A Map between Vectors representing a Materials position relative to some origin
     */
    private final HashMap<Vector, Material> vectorMaterialHashMap;

    /***
     * A Map between Vectors representing a Materials position relative to some origin
     * @return A Map between Vectors representing a Materials position relative to some origin
     */
    public HashMap<Vector, Material> getVectorMaterialHashMap() {
        return vectorMaterialHashMap;
    }

    public Feature(HashMap<Vector, Material> vectorMaterialHashMap){
        this.vectorMaterialHashMap = vectorMaterialHashMap;
    }
}
