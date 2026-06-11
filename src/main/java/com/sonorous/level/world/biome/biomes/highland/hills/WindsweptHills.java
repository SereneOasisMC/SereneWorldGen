package com.sonorous.level.world.biome.biomes.highland.hills;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomefeatures.FloraBiome;
import com.sonorous.level.world.biome.biomefeatures.FloraBiomeUtils;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WindsweptHills extends BiomeRepresentation implements FloraBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public WindsweptHills() {
        super(org.bukkit.block.Biome.WINDSWEPT_HILLS, "Windswept Hills", layers, -0.5, 0.6, -0.2, 0.5, BiomeCategories.HIGH);
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.putAll(FloraBiomeUtils.getFlowers(1));
        flora.put(Material.SHORT_GRASS, 10);
        flora.put(Material.TALL_GRASS, 20);
        return flora;
    }
}

