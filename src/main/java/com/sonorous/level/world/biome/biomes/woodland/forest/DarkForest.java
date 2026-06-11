package com.sonorous.level.world.biome.biomes.woodland.forest;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomefeatures.FloraBiome;
import com.sonorous.level.world.biome.biomefeatures.FloraBiomeUtils;
import com.sonorous.level.world.biome.biomefeatures.TreeBiome;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;
import org.bukkit.TreeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DarkForest extends BiomeRepresentation implements TreeBiome, FloraBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public DarkForest() {
        super(org.bukkit.block.Biome.DARK_FOREST, "Dark Forest", layers, 0.3, 0.4, 0, BiomeCategories.WOOD);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.DARK_OAK, TreeType.BROWN_MUSHROOM, TreeType.RED_MUSHROOM};
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

