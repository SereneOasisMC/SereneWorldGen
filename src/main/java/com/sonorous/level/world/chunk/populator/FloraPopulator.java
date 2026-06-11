package com.sonorous.level.world.chunk.populator;

import com.sonorous.level.world.KingdomUtils;
import com.sonorous.level.world.biome.BiomeRepresentation;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/***
 * Populates the world with flora
 */
public class FloraPopulator extends BlockPopulator {
    @Override
    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
        Biome biome = limitedRegion.getBiome(chunkX * 16,0,chunkZ * 16);
        if (BiomeRepresentation.isFloraBiome(biome) && !KingdomUtils.isInsideKingdomInclWalls(chunkX*16, chunkZ*16)) {
            BiomeRepresentation.getFloraTypes(biome).forEach((material, integer) -> {
                for (int iteration = 0; iteration < integer; iteration++) {
                    int x = random.nextInt(16) + chunkX * 16;
                    int z = random.nextInt(16) + chunkZ * 16;
//                    if (NoiseMaster.getFloraNoise(chunkX, chunkZ, x, z) > 0.25 ) {

                        int y = 319;


                        while (limitedRegion.getType(x, y, z).isAir() && y > -64) y--;

                        if (PopulatorUtils.isSurface(biome, limitedRegion.getType(x,y,z))) {
                            limitedRegion.setType(x, y+1, z, material);

                        }
                    }
//                }
            });

        }
    }
}