package com.sonorous.level.world.biome.biomes.coastal.beach;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
//// import com.sereneoasis.level.world.biome.biomefeatures.*;
import com.sonorous.level.world.biome.biomefeatures.FloraBiome;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Beach extends BiomeRepresentation implements FloraBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, List.of(Material.SAND));
        put(BiomeLayers.PRIMARY, Arrays.asList(Material.RED_SAND, Material.SAND));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public Beach() {
        super(org.bukkit.block.Biome.BEACH, "Beach", layers, 0.5, -0.3, 0.7, BiomeCategories.COASTAL);
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.CACTUS , 10);
        flora.put(Material.DEAD_BUSH, 6);
        return flora;
    }


}

