package com.sonorous.loader;

import com.sonorous.SonorousWorldGen;
import com.sonorous.level.world.TownUtils;
import com.sonorous.level.world.chunk.ChunkUtils;
import com.sonorous.level.world.noise.GenerationNoise;
import com.sonorous.level.world.noise.NoiseCategories;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.InvocationTargetException;

public class Town extends Civilisation{
    public Town(World world, int squareLength) {
        super(world, squareLength, (x,z) -> TownUtils.isInsideTownExclWalls(x, z ));
    }

    @Override
    void generateCivilisation(Chunk chunk) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        int x = chunk.getX() * 16;

        int z = chunk.getZ() * 16;

        Location loc = new Location(world, x, ChunkUtils.getCurrentY(x, z) +1, z);

                if (GenerationNoise.getNoise(NoiseCategories.TOWN_PATHS, x+32 ,z+32) < 0 ) {
                    if (!isPopulated( 2, chunk.getX(), chunk.getZ())) {

                        cacheSquare(world, 2, chunk.getX(), chunk.getZ());

                        pasteSchematic(loc, 2, SonorousWorldGen.getFileManager().getMediumHouseSchematics());
                    }
                } else {

                    if (GenerationNoise.getNoise(NoiseCategories.TOWN_PATHS, x+16 ,z+16) < 0 ) {
                        if (!isPopulated(1, chunk.getX(), chunk.getZ())) {
                            cacheSquare(world, 1, chunk.getX(), chunk.getZ());
                            pasteSchematic(loc, 1, SonorousWorldGen.getFileManager().getSmallHouseSchematics());
                        }
                    }
                }
    }
}
