package com.sonorous.level.world.biome.biomes.offland.oceans.cold;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DeepFrozenOcean extends BiomeRepresentation {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.ICE));
        put(BiomeLayers.PRIMARY, List.of(Material.WATER));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.STONE, Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public DeepFrozenOcean() {
        super(org.bukkit.block.Biome.DEEP_FROZEN_OCEAN, "Deep Frozen Ocean", layers, -1.0, -1.0, -0.7, BiomeCategories.OFF);
    }
}