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

public class WindsweptGravellyHills extends BiomeRepresentation implements FloraBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.GRAVEL));
        put(BiomeLayers.PRIMARY, List.of(Material.GRAVEL));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public WindsweptGravellyHills() {
        super(org.bukkit.block.Biome.WINDSWEPT_GRAVELLY_HILLS, "Windswept Gravelly Hills", layers, -0.5, -0.7, -0.2, 0.5, BiomeCategories.HIGH);
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.putAll(FloraBiomeUtils.getFlowers(1));
        return flora;
    }
}

