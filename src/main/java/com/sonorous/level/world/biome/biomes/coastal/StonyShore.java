package com.sonorous.level.world.biome.biomes.coastal;

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

public class StonyShore extends BiomeRepresentation implements FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, Arrays.asList(Material.STONE, Material.GRAVEL));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public StonyShore() {
        super(org.bukkit.block.Biome.STONY_SHORE, "Stony Shore", layers, -0.1, -0.3, 0, BiomeCategories.COASTAL);
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.ROCK.get(), 0.2);
        feature.put(DefaultFeatures.GRAVEL_CLUMP.get(), 0.2);
        feature.put(DefaultFeatures.COAL_ORE_CLUMP.get(), 0.1);
        return feature;
    }
}
