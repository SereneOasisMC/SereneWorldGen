package com.sonorous.level.world.biome.biomefeatures;

import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Random;
import java.util.function.Supplier;

/***
 * Contains features which will be created by default.
 */
public class DefaultFeatures {

    public static final Supplier<Feature> ROCK = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                for (int y = 0; y < 2; y++) {
                    if (new Random().nextBoolean()){
                        rockMap.put(new Vector(x, y, z), Material.COBBLESTONE);
                    }
                }
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> SANDSTONE_ROCK = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -1; x < 1; x++) {
            for (int z = -1; z < 1; z++) {
                for (int y = 0; y < 2; y++) {
                    rockMap.put(new Vector(x, y, z), Material.SANDSTONE);
                }
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> SANDSTONE_CLUMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                rockMap.put(new Vector(x, new Random().nextInt(0,2), z), Material.SANDSTONE);
            }
        }
        return new Feature(rockMap);
    } ;



    public static final Supplier<Feature> GRAVEL_CLUMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                rockMap.put(new Vector(x, new Random().nextInt(0,2), z), Material.GRAVEL);
            }
        }
        return new Feature(rockMap);
    } ;


    public static final Supplier<Feature> SNOW_CLUMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                rockMap.put(new Vector(x, new Random().nextInt(0,2), z), Material.SNOW_BLOCK);
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> GOLD_ORE_CLUMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                    rockMap.put(new Vector(x, new Random().nextInt(0,2), z), Material.GOLD_ORE);
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> COAL_ORE_CLUMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                rockMap.put(new Vector(x, new Random().nextInt(0,2), z), Material.COAL_ORE);
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> EMERALD_ORE_CLUMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                rockMap.put(new Vector(x, new Random().nextInt(0,2), z), Material.EMERALD_ORE);
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> REDSTONE_ORE_CLUMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                rockMap.put(new Vector(x, new Random().nextInt(0,2), z), Material.REDSTONE_ORE);
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> PACKED_ICE_SPIKE = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                for (int y = 0; y < 6; y++) {
                    rockMap.put(new Vector(x, y, z), Material.PACKED_ICE);
                }
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> STONE_CLUMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -2; x < 2; x++) {
            for (int z = -2; z < 2; z++) {
                for (int y = 0; y < 6; y++) {
                    rockMap.put(new Vector(x, y, z), Material.STONE);
                }
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> BAMBOO = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();

        for (int y = 0; y < new Random().nextInt(0, 13); y++) {
            rockMap.put(new Vector(0, y, 0), Material.BAMBOO);
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> LAMP = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();

        for (int y = 0; y < 6; y++) {
            rockMap.put(new Vector(0, y, 0), Material.CHERRY_FENCE);
        }
        rockMap.put(new Vector(0, 6, 0), Material.REDSTONE_LAMP);
        rockMap.put(new Vector(0, 7, 0), Material.DAYLIGHT_DETECTOR);
//        rockMap.put(new Vector(0, 7, 0), Material.REDSTONE_BLOCK);
//        rockMap.put(new Vector(0, 8, 0), Material.REDSTONE_LAMP);
//        rockMap.put(new Vector(1, 7, 0), Material.REDSTONE_LAMP);
//        rockMap.put(new Vector(0, 7, 1), Material.REDSTONE_LAMP);
//        rockMap.put(new Vector(-1, 7, 0), Material.REDSTONE_LAMP);
//        rockMap.put(new Vector(0, 7, -1), Material.REDSTONE_LAMP);
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> CRYSTALS = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -1; x < 1; x++) {
            for (int z = -1; z < 1; z++) {
                rockMap.put(new Vector(x, 0, z), Material.AMETHYST_CLUSTER);
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> COBWEBS = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        for (int x = -1; x < 1; x++) {
            for (int z = -1; z < 1; z++) {
                rockMap.put(new Vector(x, 0, z), Material.COBWEB);
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> SPRUCE_LOGS = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
        if (true) {
            for (int z = -2; z < 2; z++) {
                rockMap.put(new Vector(0, 0, z), Material.STRIPPED_SPRUCE_WOOD);
            }
        } else {
            for (int x = -2; x < 2; x++) {
                rockMap.put(new Vector(x, 0, 0), Material.STRIPPED_SPRUCE_WOOD);
            }
        }
        return new Feature(rockMap);
    } ;

    public static final Supplier<Feature> JUNGLE_LOGS = () -> {
        HashMap< Vector, Material> rockMap = new HashMap<>();
//        if (new Random().nextBoolean()) {
        if (true) {
            for (int z = -2; z < 2; z++) {
                rockMap.put(new Vector(0, 0, z), Material.STRIPPED_JUNGLE_WOOD);
            }
        } else {
            for (int x = -2; x < 2; x++) {
                rockMap.put(new Vector(x, 0, 0), Material.STRIPPED_JUNGLE_WOOD);
            }
        }
        return new Feature(rockMap);
    } ;
}
