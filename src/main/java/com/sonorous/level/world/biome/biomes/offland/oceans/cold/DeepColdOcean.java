package com.sonorous.level.world.biome.biomes.offland.oceans.cold;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DeepColdOcean extends BiomeRepresentation {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.ICE, Material.WATER, Material.WATER, Material.WATER));
        put(BiomeLayers.PRIMARY, List.of(Material.WATER));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.STONE, Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public DeepColdOcean() {
        super(org.bukkit.block.Biome.DEEP_COLD_OCEAN, "Deep Cold Ocean", layers, -0.8, -1.0, -0.3, BiomeCategories.OFF);
    }
}