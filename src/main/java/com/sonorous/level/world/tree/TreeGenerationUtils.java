package com.sonorous.level.world.tree;

import com.sonorous.libs.FastNoiseLite;
import com.sonorous.utils.BlockUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.util.Vector;
import oshi.util.tuples.Pair;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TreeGenerationUtils {
    
    public static void genRandomTree(Location origin, Random random){
        switch (random.nextInt(8)) {
            case 0 -> {
                int iterations = random.nextInt(50, 100);
                TreeGenerationUtils.generateFirTree(origin, iterations, new Random());

            }
            case 1 -> {
                int iterations = random.nextInt(10, 40);

                TreeGenerationUtils.generateAcaciaTree(origin, iterations, new Random());

            }
            case 2 -> {
                int iterations = random.nextInt(40, 70);

                TreeGenerationUtils.generateBirchTree(origin, iterations, new Random());

            }
            case 3 -> {
                int iterations = random.nextInt(30, 60);

                TreeGenerationUtils.generateSpruceTree(origin, iterations, new Random());

            }
            case 4 -> {

                int iterations = random.nextInt(20, 40);

                TreeGenerationUtils.generateOakTree(origin, iterations, new Random());

            }
            case 5 -> {
                int iterations = random.nextInt(60, 80);

                TreeGenerationUtils.generateJungleTree(origin, iterations, new Random());

            }
            case 6 -> {
                int iterations = random.nextInt(20, 40);

                TreeGenerationUtils.generateCherryTree(origin, iterations, new Random());

            }
        }
    }

    public static void generateFirTree(Location origin, int iterations, Random random ){
        Location currentTrunkPos = origin.clone();

        Set<Location> trunk = new HashSet<>();
        Set<Pair<Vector, Location> >branches = new HashSet<>();

        for (int i = 0; i < iterations ; i ++){

            BlockUtils.getAirCircleAroundPoint(currentTrunkPos, 4).forEach(block -> {
                trunk.add(block.getLocation());
            });

            currentTrunkPos.add(random.nextDouble() - 0.5,1,random.nextDouble() - 0.5);

            Pair<Vector, Location> branch = new Pair<>(new Vector(random.nextDouble()-0.5, random.nextDouble()-0.5, random.nextDouble()-0.5).normalize(), currentTrunkPos.clone());
            branches.add(branch);

        }

        branches.forEach(vectorLocationPair -> {
            for (int i = 0; i < random.nextInt(1, iterations) ; i ++) {
                vectorLocationPair.getB().add(vectorLocationPair.getA());
                vectorLocationPair.getB().getBlock().setType(Material.OAK_LOG);
                BlockUtils.getAirBlocksAroundPoint(vectorLocationPair.getB(), 3).forEach(block -> {
                    block.setType(Material.OAK_LEAVES);
                    Leaves blockData = (Leaves) block.getBlockData();
                    blockData.setPersistent(true);
                    block.setBlockData(blockData, false);
                });
            }});

        trunk.forEach(location -> location.getBlock().setType(Material.OAK_LOG));

    }

    public static void generateAcaciaTree(Location origin, int iterations, Random random ){
        Location currentTrunkPos = origin.clone();

        Set<Location> trunk = new HashSet<>();
        Set<Pair<Vector, Location> >branches = new HashSet<>();

        int currentHeight = 0;

        for (int i = 0; i < iterations ; i ++){

            BlockUtils.getAirCircleAroundPoint(currentTrunkPos, 4).forEach(block -> {
                trunk.add(block.getLocation());
            });

            currentTrunkPos.add(0,1, 0);
            currentHeight++;

            if (currentHeight > iterations/3 && currentHeight < iterations*2/3) {
                Pair<Vector, Location> branch = new Pair<>(new Vector(random.nextDouble() - 0.5, random.nextDouble() * currentHeight / 8, random.nextDouble() - 0.5).normalize(), currentTrunkPos.clone());
                branches.add(branch);
            }
        }

        branches.forEach(vectorLocationPair -> {
            for (int i = 0; i < iterations * 2/3 ; i ++) {
                vectorLocationPair.getB().add(vectorLocationPair.getA());
                vectorLocationPair.getB().getBlock().setType(Material.ACACIA_LOG);
                vectorLocationPair.getA().add(new Vector(0,0.2,0)).normalize();
            }
//            BlockUtils.getAirBlocksAroundPoint(vectorLocationPair.getB(), 10).forEach(block -> block.setType(Material.ACACIA_LEAVES));
        });

        FastNoiseLite noise = new FastNoiseLite(random.nextInt(100000));
        noise.SetNoiseType(FastNoiseLite.NoiseType.Cellular);
        noise.SetFrequency(0.20F);
        noise.SetFractalType(FastNoiseLite.FractalType.PingPong);

        for (int height = -iterations/6; height < iterations/2; height++) {
            BlockUtils.getAirCircleAroundPoint(currentTrunkPos, (iterations*2 - height)  ).forEach(block -> {

                if (noise.GetNoise(block.getX(), block.getY(), block.getZ()) > 0.2) {
                    block.setType(Material.ACACIA_LEAVES);
                    Leaves blockData = (Leaves) block.getBlockData();
                    blockData.setPersistent(true);
                    block.setBlockData(blockData, false);

                }
            });
            currentTrunkPos.add(0,1,0);
        }


        trunk.forEach(location -> location.getBlock().setType(Material.ACACIA_LOG));

    }

    public static void generateBirchTree(Location origin, int iterations, Random random ){
        Location currentTrunkPos = origin.clone();

        Set<Location> trunk = new HashSet<>();
        Set<Pair<Vector, Location> >branches = new HashSet<>();

        for (int i = 0; i < iterations ; i ++){

            BlockUtils.getAirCircleAroundPoint(currentTrunkPos, 4).forEach(block -> {
                trunk.add(block.getLocation());
            });

            currentTrunkPos.add(random.nextDouble() - 0.5,1,random.nextDouble() - 0.5);

            Pair<Vector, Location> branch = new Pair<>(new Vector(random.nextDouble()-0.5, random.nextDouble()-0.5, random.nextDouble()-0.5).normalize(), currentTrunkPos.clone());
            branches.add(branch);

        }

        FastNoiseLite noise = new FastNoiseLite(random.nextInt(100000));
        noise.SetNoiseType(FastNoiseLite.NoiseType.Cellular);
        noise.SetFrequency(0.20F);
        noise.SetFractalType(FastNoiseLite.FractalType.PingPong);

        branches.forEach(vectorLocationPair -> {
                    for (int i = 0; i < random.nextInt(1, iterations); i++) {
                        vectorLocationPair.getB().add(vectorLocationPair.getA());
                        vectorLocationPair.getB().getBlock().setType(Material.BIRCH_LOG);
                        vectorLocationPair.getA().add(new Vector(0,0.2,0)).normalize();

                    }
                    BlockUtils.getAirBlocksAroundPoint(vectorLocationPair.getB(), 7 ).forEach(block -> {
                        if (noise.GetNoise(block.getX(), block.getY(), block.getZ()) > 0.35) {
                            block.setType(Material.BIRCH_LEAVES);
                            Leaves blockData = (Leaves) block.getBlockData();
                            blockData.setPersistent(true);
                            block.setBlockData(blockData, false);

                        }
                    });

                }
            );

        trunk.forEach(location -> location.getBlock().setType(Material.BIRCH_LOG));

    }

    public static void generateSpruceTree(Location origin, int iterations, Random random ){
        Location currentTrunkPos = origin.clone();

        Set<Location> trunk = new HashSet<>();
        Set<Pair<Vector, Location> >branches = new HashSet<>();

        for (int i = 0; i < iterations ; i ++){

            BlockUtils.getAirCircleAroundPoint(currentTrunkPos, 4).forEach(block -> {
                trunk.add(block.getLocation());
            });

            currentTrunkPos.add(0, 1, 0);

            for (int branchAmount = 0; branchAmount < random.nextInt(0, iterations/2) ; branchAmount++) {
                Pair<Vector, Location> branch = new Pair<>(new Vector(random.nextDouble()-0.5, random.nextDouble(0.1), random.nextDouble()-0.5).normalize(), currentTrunkPos.clone());
                branches.add(branch);
            }

        }

        int diameter = Math.floorDiv(iterations, 10) + 2;

        branches.forEach(vectorLocationPair -> {

            double heightFromTop = iterations - Math.sqrt(vectorLocationPair.getB().distanceSquared(origin));
            int heightRatio = Math.round(Math.round(heightFromTop/iterations));

            for (int i = 0; i < heightFromTop ; i ++) {
                vectorLocationPair.getB().add(vectorLocationPair.getA());
                vectorLocationPair.getB().getBlock().setType(Material.SPRUCE_LOG);
                BlockUtils.getAirBlocksAroundPoint(vectorLocationPair.getB(), diameter).forEach(block -> {
                    block.setType(Material.SPRUCE_LEAVES);
                    Leaves blockData = (Leaves) block.getBlockData();
                    blockData.setPersistent(true);
                    block.setBlockData(blockData, false);
                });

            }

        });


        trunk.forEach(location -> location.getBlock().setType(Material.SPRUCE_LOG));

    }

    public static void generateOakTree(Location origin, int iterations, Random random ){
        Location currentTrunkPos = origin.clone();

        Set<Location> trunk = new HashSet<>();
        Set<Pair<Vector, Location> >branches = new HashSet<>();

        int currentHeight = 0;

        for (int i = 0; i < iterations ; i ++){

            BlockUtils.getAirCircleAroundPoint(currentTrunkPos, 4).forEach(block -> {
                trunk.add(block.getLocation());
            });

            currentTrunkPos.add(0,1, 0);
            currentHeight++;

            Pair<Vector, Location> branch = new Pair<>(new Vector(random.nextDouble() - 0.5, random.nextDouble() - 0.5, random.nextDouble() - 0.5).normalize(), currentTrunkPos.clone());
            branches.add(branch);
        }

        FastNoiseLite noise = new FastNoiseLite(random.nextInt(100000));
        noise.SetNoiseType(FastNoiseLite.NoiseType.Cellular);
        noise.SetFrequency(0.20F);
        noise.SetFractalType(FastNoiseLite.FractalType.PingPong);


        branches.forEach(vectorLocationPair -> {
            for (int i = 0; i < iterations * 2/3 ; i ++) {
                vectorLocationPair.getB().add(vectorLocationPair.getA());
                BlockUtils.getAirBlocksAroundPoint(vectorLocationPair.getB(), 3).forEach(block -> {
                    block.setType(Material.OAK_LOG);
                });
                vectorLocationPair.getA().add(new Vector(0,0.05,0)).normalize();
            }
            BlockUtils.getAirSphereBlocksAroundPoint(vectorLocationPair.getB(), iterations/2).forEach(block -> {
                if (noise.GetNoise(block.getX(), block.getY(), block.getZ()) > 0.4) {
                    block.setType(Material.OAK_LEAVES);
                    Leaves blockData = (Leaves) block.getBlockData();
                    blockData.setPersistent(true);
                    block.setBlockData(blockData, false);

                }
            });
        });




        trunk.forEach(location -> location.getBlock().setType(Material.OAK_LOG));

    }

    public static void generateJungleTree(Location origin, int iterations, Random random ){
        Location currentTrunkPos = origin.clone();

        Set<Location> trunk = new HashSet<>();
        Set<Pair<Vector, Location> >branches = new HashSet<>();

        for (int i = 0; i < iterations ; i ++){

            BlockUtils.getAirHollowCircleAroundPoint(currentTrunkPos, 9).forEach(block -> {
                trunk.add(block.getLocation());
            });

            currentTrunkPos.add(0, 1, 0);

            if (i > iterations * 2/3) {
                    Pair<Vector, Location> branch = new Pair<>(new Vector(random.nextDouble() - 0.5, random.nextDouble(0.1), random.nextDouble() - 0.5).normalize(), currentTrunkPos.clone());
                    branches.add(branch);

            }

        }

        branches.forEach(vectorLocationPair -> {

            for (int i = 0; i < iterations/3 ; i ++) {
                vectorLocationPair.getB().add(vectorLocationPair.getA());
                vectorLocationPair.getB().getBlock().setType(Material.JUNGLE_LOG);
                vectorLocationPair.getA().add(new Vector(0,0.1,0));
                vectorLocationPair.getA().normalize();
            }
            BlockUtils.getAirCircleAroundPoint(vectorLocationPair.getB(), 20).forEach(block -> {
                block.setType(Material.JUNGLE_LEAVES);
                Leaves blockData = (Leaves) block.getBlockData();
                blockData.setPersistent(true);
                block.setBlockData(blockData, false);
            });
        });


        trunk.forEach(location -> location.getBlock().setType(Material.JUNGLE_LOG));

    }

    public static void generateCherryTree(Location origin, int iterations, Random random ){
        Location currentTrunkPos = origin.clone();

        Set<Location> trunk = new HashSet<>();
        Set<Pair<Vector, Location> >branches = new HashSet<>();

        int currentHeight = 0;

        for (int i = 0; i < iterations ; i ++){

            BlockUtils.getAirCircleAroundPoint(currentTrunkPos, 4).forEach(block -> {
                trunk.add(block.getLocation());
            });

            currentTrunkPos.add(0,1, 0);
            currentHeight++;
            if (currentHeight > iterations/3) {
                Pair<Vector, Location> branch = new Pair<>(new Vector(random.nextDouble() - 0.5, random.nextDouble(), random.nextDouble() - 0.5).normalize(), currentTrunkPos.clone());
                branches.add(branch);
            }
        }

        FastNoiseLite noise = new FastNoiseLite(random.nextInt(100000));
        noise.SetNoiseType(FastNoiseLite.NoiseType.Cellular);
        noise.SetFrequency(0.20F);
        noise.SetFractalType(FastNoiseLite.FractalType.PingPong);


        branches.forEach(vectorLocationPair -> {
            for (int i = 0; i < iterations * 2/3 ; i ++) {
                vectorLocationPair.getB().add(vectorLocationPair.getA());
                BlockUtils.getAirBlocksAroundPoint(vectorLocationPair.getB(), 3).forEach(block -> {
                    block.setType(Material.CHERRY_LOG);
                });
                vectorLocationPair.getA().add(new Vector(0,0.01,0)).normalize();

                BlockUtils.getAirSphereBlocksAroundPoint(vectorLocationPair.getB(), iterations/3).forEach(block -> {
                    if (noise.GetNoise(block.getX(), block.getY(), block.getZ()) > 0.4) {
                        block.setType(Material.CHERRY_LEAVES);
                        if (block.getBlockData() instanceof Leaves blockData) {
                            blockData.setPersistent(true);
                            block.setBlockData(blockData, false);
                        }

                    }
                });
            }

        });

        trunk.forEach(location -> location.getBlock().setType(Material.CHERRY_LOG));

    }
}
