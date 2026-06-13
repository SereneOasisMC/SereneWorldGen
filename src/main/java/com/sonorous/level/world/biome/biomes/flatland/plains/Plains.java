package com.sonorous.level.world.biome.biomes.flatland.plains;

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

public class Plains extends BiomeRepresentation implements TreeBiome, FloraBiome, FeatureBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.STONE));
        put(BiomeLayers.ORES, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public Plains() {
        super(org.bukkit.block.Biome.PLAINS, "Plains", layers, 0.3, 0.4, 0, BiomeCategories.FLAT);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.TREE};
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        return FloraBiomeUtils.getFlowers(5);

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
