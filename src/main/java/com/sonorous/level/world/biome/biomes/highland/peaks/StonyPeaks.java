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

public class StonyPeaks extends BiomeRepresentation implements FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.STONE));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public StonyPeaks() {
        super(org.bukkit.block.Biome.STONY_PEAKS, "Stony Peaks", layers, -0.3, 1.0, -0.2, 0.1, BiomeCategories.HIGH);
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.CRYSTALS.get(), 0.05);
        feature.put(DefaultFeatures.ROCK.get(), 0.1);
        feature.put(DefaultFeatures.STONE_CLUMP.get(), 0.1);
        feature.put(DefaultFeatures.EMERALD_ORE_CLUMP.get(), 0.01);
        return feature;
    }
}
