package com.sonorous.loader;

import com.sonorous.SonorousWorldGen;
import com.sonorous.level.world.Schematics;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import oshi.util.tuples.Pair;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.logging.Level;

public abstract class Civilisation {
    
    protected World world;

    public Civilisation(World world, int squareLength, BiPredicate<Integer, Integer> shouldGenerate){
        int currentX = -squareLength/2, currentZ = -squareLength/2;
        this.world = world;
        Thread chunkLoadingThread = new Thread(() -> {
            try {
                for (int x = currentX; x < squareLength / 2; x++) {
                    for (int z = currentZ; z < squareLength / 2; z++) {
//                        Bukkit.broadcastMessage("currently at X: " + x + " Z: " + z );

                        if (shouldGenerate.test(x*16, z*16)) {
                            Chunk currentChunk = world.getChunkAt(x, z, true);
                            Bukkit.getScheduler().runTask(SonorousWorldGen.plugin, () -> currentChunk.addPluginChunkTicket(SonorousWorldGen.plugin));
                            schematicChunks.add(currentChunk);

                            Thread.sleep(500);

                        }
                        if (x == squareLength-1 && z == squareLength-1){
                            Bukkit.getServer().getLogger().log(Level.INFO, "Finished generating civilisations");
                        }
                    }
                }

            } catch (InterruptedException v) {
                System.out.println(v);
            }
        });
        chunkLoadingThread.start();


        Bukkit.getScheduler().scheduleSyncRepeatingTask(SonorousWorldGen.plugin, () -> {
            for (Chunk chunk : schematicChunks) {
                if (chunk.getLoadLevel().equals(Chunk.LoadLevel.ENTITY_TICKING)) {
                    int x = chunk.getX() * 16;
                    int z = chunk.getZ() * 16;
                    try {
                        generateCivilisation(chunk);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            schematicChunks.removeIf(chunk -> chunk.getLoadLevel().equals(Chunk.LoadLevel.ENTITY_TICKING));
        }, 1000L, 100L);
    }

    abstract void generateCivilisation(Chunk chunk) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    protected static Set<Chunk> schematicChunks = new HashSet<>();


    protected void pasteSchematic(Location loc,int length, File[] schems) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Random rand = new Random();
        File schem = schems[rand.nextInt(schems.length) ];
        Bukkit.broadcastMessage("pasted schematic at " + loc);

        Schematics.pasteClipboard(schem.getName(), loc.clone());


//        new SereneMonster(loc, Archetype.EARTH);
    }

    protected static Set<Pair<Integer, Integer>> populatedChunks = new HashSet<>();

    protected void cacheSquare(World world, int length, int chunkX, int chunkZ){
        for (int newX = 0; newX< length; newX ++) {
            for (int newZ = 0; newZ < length; newZ++) {

                populatedChunks.add(new Pair<>(chunkX + newX, chunkZ + newZ));
            }
        }
    }

    protected boolean isPopulated(int length, int chunkX, int chunkZ){
        for (int newX = 0; newX<length; newX ++) {
            for (int newZ = 0; newZ < length; newZ++) {
                int newChunkX = chunkX + newX;
                int newChunkZ = chunkZ + newZ;

                if (populatedChunks.stream().anyMatch(integerIntegerPair -> integerIntegerPair.getA() ==newChunkX && integerIntegerPair.getB() == newChunkZ)){
                    return true;
                }
            }
        }
        return false;
    }
}
