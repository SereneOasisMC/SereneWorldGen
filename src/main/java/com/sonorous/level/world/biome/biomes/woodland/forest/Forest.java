package com.sonorous.level.world.biome.biomes.woodland.forest;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomefeatures.FloraBiome;
import com.sonorous.level.world.biome.biomefeatures.FloraBiomeUtils;
import com.sonorous.level.world.biome.biomefeatures.TreeBiome;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Forest extends BiomeRepresentation implements TreeBiome, FloraBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public Forest() {
        super(Biome.FOREST, "Forest", layers, 0.5, 0.4, 0, BiomeCategories.WOOD);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.TREE, TreeType.BIRCH};
    }


    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.SHORT_GRASS, 5);
        flora.put(Material.TALL_GRASS, 10);
        flora.put(Material.SWEET_BERRY_BUSH, 10);
        flora.putAll(FloraBiomeUtils.getFlowers(1));

        return flora;
    }
}
