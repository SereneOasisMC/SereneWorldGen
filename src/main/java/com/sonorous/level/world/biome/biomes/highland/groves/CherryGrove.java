package com.sonorous.level.world.biome.biomes.highland.groves;

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

public class CherryGrove extends BiomeRepresentation implements TreeBiome, FloraBiome, FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public CherryGrove() {
        super(org.bukkit.block.Biome.CHERRY_GROVE, "Cherry Grove", layers, 0.3, 0.5, 0.1, BiomeCategories.HIGH);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.CHERRY};
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.putAll(FloraBiomeUtils.getFlowers(1));
        flora.put(Material.SHORT_GRASS, 10);
        flora.put(Material.POPPY, 20);
        flora.put(Material.PINK_PETALS, 30);
        return flora;
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.CRYSTALS.get(), 0.1);
        return feature;
    }
}
