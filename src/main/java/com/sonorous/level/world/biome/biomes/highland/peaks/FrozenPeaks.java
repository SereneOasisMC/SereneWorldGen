package com.sonorous.level.world.biome.biomes.highland.peaks;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomefeatures.DefaultFeatures;
import com.sonorous.level.world.biome.biomefeatures.Feature;
import com.sonorous.level.world.biome.biomefeatures.FeatureBiome;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FrozenPeaks extends BiomeRepresentation implements FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.BLUE_ICE));
        put(BiomeLayers.PRIMARY, Arrays.asList(Material.BLUE_ICE, Material.PACKED_ICE, Material.ICE));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public FrozenPeaks() {
        super(org.bukkit.block.Biome.FROZEN_PEAKS, "Frozen Peaks", layers, -1.0, 1.0, -0.2, 0.1, BiomeCategories.HIGH);
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.PACKED_ICE_SPIKE.get(), 0.1);
        return feature;
    }
}

