package com.sonorous.level.world.biome.biomes.river;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FrozenRiver extends BiomeRepresentation {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.ICE, Material.WATER, Material.WATER, Material.WATER, Material.WATER));
        put(BiomeLayers.PRIMARY, Arrays.asList(Material.BLUE_ICE, Material.PACKED_ICE, Material.ICE));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.STONE));
        put(BiomeLayers.ORES, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public FrozenRiver() {
        super(org.bukkit.block.Biome.FROZEN_RIVER, "FrozenRiver", layers, -1.0, -0.3, 0, BiomeCategories.RIVER);
    }
}

