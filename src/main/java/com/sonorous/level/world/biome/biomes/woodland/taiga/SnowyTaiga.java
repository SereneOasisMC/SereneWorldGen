package com.sonorous.level.world.biome.biomes.woodland.taiga;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomefeatures.TreeBiome;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;
import org.bukkit.TreeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SnowyTaiga extends BiomeRepresentation implements TreeBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public SnowyTaiga() {
        super(org.bukkit.block.Biome.SNOWY_TAIGA, "Snowy Taiga", layers, -0.7, 0.4, 0, BiomeCategories.WOOD);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.REDWOOD};
    }
}

