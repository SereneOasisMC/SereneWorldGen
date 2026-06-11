package com.sonorous.level.world.biome;

import com.sonorous.level.world.chunk.ChunkUtils;
import com.sonorous.level.world.noise.NoiseMaster;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/***
 * A custom Biome provider to handle Biomes for world generation
 */
public class CustomBiomeProvider extends BiomeProvider {

    @NotNull
    @Override
    public Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z) {

        float currentY = ChunkUtils.getCurrentY(x, z);

        boolean ocean = currentY <= ChunkUtils.SEA_LEVEL;
        return NoiseMaster.getBiome(x, z, ocean);
    }

    @NotNull
    @Override
    public List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
        return BiomeRepresentation.getValidBiomes().stream().toList();
    }
}
