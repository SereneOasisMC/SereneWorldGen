package com.sonorous.level.world.biome.biomes.aridland;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomefeatures.DefaultFeatures;
import com.sonorous.level.world.biome.biomefeatures.Feature;
import com.sonorous.level.world.biome.biomefeatures.FeatureBiome;
import com.sonorous.level.world.biome.biomefeatures.FloraBiome;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Desert extends BiomeRepresentation implements FloraBiome, FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.SAND));
        put(BiomeLayers.PRIMARY, List.of(Material.SANDSTONE));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public Desert() {
        super(org.bukkit.block.Biome.DESERT, "Desert", layers, 0.7, 0.5, 0.2, BiomeCategories.ARID);
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.DEAD_BUSH, 5);
        return flora;
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.GRAVEL_CLUMP.get(), 0.1);
        return feature;
    }
}

