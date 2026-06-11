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

import java.util.Random;

/***
 * Populates the world with trees
 */
public class TreePopulator extends BlockPopulator {

    @Override
    public void populate(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, LimitedRegion limitedRegion) {
        int x = random.nextInt(16) + chunkX * 16;
        int z = random.nextInt(16) + chunkZ * 16;
        int y = 319;
        while(limitedRegion.getType(x, y, z).isAir() && y > -64) y--;

        Location location = new Location(Bukkit.getWorld(worldInfo.getUID()), x, y, z);

        Biome biome = limitedRegion.getBiome(location);
        if (BiomeRepresentation.isTreeBiome(biome) && !KingdomUtils.isInsideKingdomInclWalls(chunkX*16, chunkZ*16)){

            if (PopulatorUtils.isSurface(biome, limitedRegion.getType(x,y,z))) {
                limitedRegion.setType(location, Material.AIR);
                limitedRegion.generateTree(location, random, BiomeRepresentation.getTreeTypes(biome).get(random.nextInt(BiomeRepresentation.getTreeTypes(biome).size())));
            }


//            if (GenerationNoise.getNoise(NoiseCategories.CUSTOM_TREES, chunkX * 16, chunkZ * 16) > 0.5 && (GenerationNoise.getNoise(NoiseCategories.CUSTOM_TREES, chunkX * 16, chunkZ * 16) < 0.55)){
//                TreeGenerationUtils.generateCherryTree(location, 5, random);
//            }
        }
    }
}
