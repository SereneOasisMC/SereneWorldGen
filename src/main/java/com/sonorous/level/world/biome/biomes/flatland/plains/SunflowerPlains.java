package com.sonorous.level.world.biome.biomes.flatland.plains;

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

public class SunflowerPlains extends BiomeRepresentation implements FloraBiome, FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public SunflowerPlains() {
        super(org.bukkit.block.Biome.SUNFLOWER_PLAINS, "Sunflower Plains", layers, 0.4, 0.4, 0.2, 0.1, BiomeCategories.FLAT);
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.SHORT_GRASS, 20);
        flora.put(Material.DANDELION, 10);
        flora.put(Material.ROSE_BUSH, 3);
        flora.put( Material.SUNFLOWER, 20);
        return flora;
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.GOLD_ORE_CLUMP.get(), 0.01);
        feature.put(DefaultFeatures.COAL_ORE_CLUMP.get(), 0.02);
        feature.put(DefaultFeatures.ROCK.get(), 0.05);
        feature.put(DefaultFeatures.LAMP.get(), 0.2);
        return feature;
    }
}

