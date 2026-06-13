package com.sonorous.level.world.biome.biomes.aridland.savanna;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
//// import com.sereneoasis.level.world.biome.biomefeatures.*;
import com.sonorous.level.world.biome.biomefeatures.*;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;
import org.bukkit.TreeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Savanna extends BiomeRepresentation implements TreeBiome, FloraBiome, FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.STONE));
        put(BiomeLayers.ORES, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public Savanna() {
        super(org.bukkit.block.Biome.SAVANNA, "Savanna", layers, 0.6, 0.3, -0.4, BiomeCategories.ARID);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.ACACIA};
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.SHORT_GRASS , 10);
        flora.put(Material.TALL_GRASS, 5);
        flora.put(Material.DEAD_BUSH, 20);
        flora.putAll(FloraBiomeUtils.getFlowers(1));
        return flora;
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.GOLD_ORE_CLUMP.get(), 0.1);
        feature.put(DefaultFeatures.LAMP.get(), 0.2);
        return feature;
    }
}

