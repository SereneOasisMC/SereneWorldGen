package com.sonorous.level.world.biome.biomes.aridland.badlands;

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

public class WoodedBadlands extends BiomeRepresentation implements TreeBiome, FloraBiome, FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.TERRACOTTA));
        put(BiomeLayers.PRIMARY, Arrays.asList(Material.TERRACOTTA, Material.YELLOW_TERRACOTTA, Material.WHITE_TERRACOTTA, Material.GRAY_TERRACOTTA));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public WoodedBadlands() {
        super(org.bukkit.block.Biome.WOODED_BADLANDS, "Wooded Badlands", layers, 1.0, 0.7, -0.9, 0.1, BiomeCategories.ARID);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.TREE};
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.DEAD_BUSH, 20);
        return flora;
    }

    @Override
    public HashMap<Feature, Double> getFeatures() {
        HashMap<Feature, Double>feature = new HashMap<>();
        feature.put(DefaultFeatures.COAL_ORE_CLUMP.get(), 0.1);
        feature.put(DefaultFeatures.COBWEBS.get(), 0.2);
        return feature;
    }
}

