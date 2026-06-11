package com.sonorous.level.world.biome.biomes.aridland.savanna;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomefeatures.FloraBiome;
import com.sonorous.level.world.biome.biomefeatures.FloraBiomeUtils;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WindsweptSavanna extends BiomeRepresentation implements FloraBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRASS_BLOCK));
        put(BiomeLayers.PRIMARY, List.of(Material.DIRT));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public WindsweptSavanna() {
        super(org.bukkit.block.Biome.WINDSWEPT_SAVANNA, "Windswept Savanna", layers, 0.5, 1.0, -0.1, 0.5, BiomeCategories.ARID);
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.SHORT_GRASS , 10);
        flora.put(Material.TALL_GRASS, 5);
        flora.put(Material.DEAD_BUSH, 20);
        flora.putAll(FloraBiomeUtils.getFlowers(1));
        return flora;
    }
}

