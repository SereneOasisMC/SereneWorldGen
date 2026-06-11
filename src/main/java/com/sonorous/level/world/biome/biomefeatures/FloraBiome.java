package com.sonorous.level.world.biome.biomefeatures;

import org.bukkit.Material;

import java.util.HashMap;

public interface FloraBiome {

    /***
     * Represents flora and an amount to decide how regular generation will be
     * @return A HashMap between a material (some vegetation) and the amount of times it will attempt to generate
     */
    HashMap<Material, Integer>getFlora();
}
