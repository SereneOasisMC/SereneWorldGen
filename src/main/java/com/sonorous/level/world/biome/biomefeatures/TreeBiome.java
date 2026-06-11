package com.sonorous.level.world.biome.biomefeatures;

import org.bukkit.TreeType;

public interface TreeBiome {

    /***
     * Returns what Trees will belong to a biome
     * @return An array consisting of the TreeTypes per biome
     */
    TreeType[] getTreeType();

}
