package com.sonorous.level.world.biome.biomes.wetland.swamp;

import com.sonorous.level.world.biome.BiomeLayers;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.biome.biomefeatures.FloraBiome;
import com.sonorous.level.world.biome.biomefeatures.TreeBiome;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import org.bukkit.Material;
import org.bukkit.TreeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MangroveSwamp extends BiomeRepresentation implements TreeBiome, FloraBiome {

    private static final HashMap<BiomeLayers, List<Material>> layers = new HashMap<>() {{
        put(BiomeLayers.SURFACE, Arrays.asList(Material.MUD, Material.MUD, Material.MUD, Material.DIRT));
        put(BiomeLayers.PRIMARY, Arrays.asList(Material.DIRT, Material.MUD));
        put(BiomeLayers.SECONDARY, Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE, Material.GOLD_ORE, Material.DIAMOND_ORE));
        put(BiomeLayers.BASE, List.of(Material.BEDROCK));
    }};
    public MangroveSwamp() {
        super(org.bukkit.block.Biome.MANGROVE_SWAMP, "Mangrove Swamp", layers, 0.5, -0.2, 0.6, BiomeCategories.WET);
    }

    @Override
    public TreeType[] getTreeType() {
        return new TreeType[]{TreeType.MANGROVE};
    }

    @Override
    public HashMap<Material, Integer> getFlora() {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.put(Material.SHORT_GRASS, 20);
        flora.put(Material.BIG_DRIPLEAF, 10);
        return flora;
    }

}
