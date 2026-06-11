package com.sonorous.level.world.biome.biomes.woodland.jungle;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
// import com.sereneoasis.level.world.biome.biomefeatures.*;
import com.sonorous.level.world.biome.biomefeatures.*;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;
import org.bukkit.TreeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Jungle extends BiomeRepresentation implements TreeBiome, FloraBiome, FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public Jungle() {
        super(org.bukkit.block.Biome.JUNGLE, "Jungle", layers, 0.5, 0.4, 0.7, BiomeCategories.WOOD);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.JUNGLE, TreeType.JUNGLE_BUSH, TreeType.COCOA_TREE};
    }


    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.SHORT_GRASS, 10);
        flora.put(Material.TALL_GRASS, 10);
        flora.putAll(FloraBiomeUtils.getFlowers(1));

        return flora;
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.ROCK.get(), 0.05);
        feature.put(DefaultFeatures.JUNGLE_LOGS.get(), 0.1);
        return feature;
    }
}
