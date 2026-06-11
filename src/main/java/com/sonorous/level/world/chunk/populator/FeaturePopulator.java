package com.sonorous.level.world.chunk.populator;

import com.sonorous.level.world.KingdomUtils;
import com.sonorous.level.world.biome.BiomeRepresentation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/***
 * Populates the world with features
 */
public class FeaturePopulator extends BlockPopulator {

    @Override
    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
        Biome biome = limitedRegion.getBiome(chunkX * 16,0,chunkZ * 16);
        if (BiomeRepresentation.isFeatureBiome(biome) && !KingdomUtils.isInsideKingdomInclWalls(chunkX*16, chunkZ*16)) {
            BiomeRepresentation.getBiomeFeatures(biome).forEach((feature, aDouble) -> {
                if (aDouble > random.nextDouble()){
                    int x = random.nextInt(2, 15) + chunkX * 16;
                    int z = random.nextInt(2, 15) + chunkZ * 16;

                    int y = 319;
                    while (limitedRegion.getType(x, y, z).isAir() && y > -64) y--;

                    if (PopulatorUtils.isSurface(biome, limitedRegion.getType(x,y,z))) {

                        int finalY = y;
                        feature.getVectorMaterialHashMap().forEach((vector, material) -> {
                            Location loc = new Location(Bukkit.getWorld(worldInfo.getUID()), (int) (x + vector.getX()), (int) (finalY + vector.getY()), (int) (z + vector.getZ()));

                            if (limitedRegion.getType(loc).equals(Material.AIR)) {
                                limitedRegion.setType(loc, material);
                            }

                        });
                    }
                }
            });
        }
    }
}
