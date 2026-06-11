package com.sonorous.level.world.biome.biomes.river;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class River extends BiomeRepresentation {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.WATER));
        put(BiomeLayers.PRIMARY, List.of(Material.WATER));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.STONE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public River() {
        super(org.bukkit.block.Biome.RIVER, "River", layers, -0.1, -0.2, 0, BiomeCategories.RIVER);
    }
}

