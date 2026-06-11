package com.sonorous.level.world.biome.biomes.highland.groves;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
// import com.sereneoasis.level.world.biome.biomefeatures.*;
import com.sonorous.level.world.biome.biomefeatures.*;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Grove extends BiomeRepresentation implements FloraBiome, FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public Grove() {
        super(org.bukkit.block.Biome.GROVE, "Grove", layers, 0.2, 0.5, 0.1, BiomeCategories.HIGH);
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.SHORT_GRASS, 20);
        flora.putAll(FloraBiomeUtils.getFlowers(1));
        flora.put(Material.POPPY, 5);
        return flora;
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.CRYSTALS.get(), 0.05);
        return feature;
    }
}

