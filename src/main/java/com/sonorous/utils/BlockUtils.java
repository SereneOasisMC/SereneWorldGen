package com.sonorous.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.HashSet;
import java.util.Set;

public class BlockUtils {
    
    public static Set<Block> getBlocksAroundPoint(Location location, double diameter){
        Set<Block>blocks = new HashSet<>();
        for (double x = -diameter/2; x <= diameter/2 ; x++){
            for (double y = -diameter/2; y <= diameter/2 ; y++){
                for (double z = -diameter/2; z <= diameter/2 ; z++){
                    blocks.add(location.clone().add(x,y,z).getBlock());
                }
            }
        }
        return blocks;
    }

    public static Set<Block> getCircleAroundPoint(Location location, double diameter){
        Set<Block>blocks = new HashSet<>();
        for (double x = -diameter/2; x <= diameter/2 ; x++){
            for (double z = -diameter/2; z <= diameter/2 ; z++){
                    blocks.add(location.clone().add(x,0,z).getBlock());
                }
        }
        return blocks;
    }

    public static Set<Block> getAirSphereBlocksAroundPoint(Location location, double diameter){
        Set<Block>blocks = new HashSet<>();
        for (double x = -diameter/2; x <= diameter/2 ; x++){
            for (double y = -diameter/2; y <= diameter/2 ; y++){
                if ( ((x * x) + (y*y)) < ( diameter /2) * ( diameter /2)) {

                    for (double z = -diameter / 2; z <= diameter / 2; z++) {
                        if ( ((z * z) + (y*y)) < ( diameter /2) * ( diameter /2)) {
                            if ( ((x * x) + (z*z)) < ( diameter /2) * ( diameter /2)) {

                                Block b = location.clone().add(x, y, z).getBlock();
                                if (b.getType().isAir()) {
                                    blocks.add(b);
                                }
                            }
                        }
                    }
                }
            }
        }
        return blocks;
    }

    public static Set<Block> getAirBlocksAroundPoint(Location location, double diameter){
        Set<Block>blocks = new HashSet<>();
        for (double x = -diameter/2; x <= diameter/2 ; x++){
            for (double y = -diameter/2; y <= diameter/2 ; y++){
                for (double z = -diameter/2; z <= diameter/2; z++){
                    Block b = location.clone().add(x,y,z).getBlock();
                    if (b.getType().isAir()) {
                        blocks.add(b);
                    }
                }
            }
        }
        return blocks;
    }

    public static Set<Block> getAirCircleAroundPoint(Location location, double diameter){
        Set<Block>blocks = new HashSet<>();
        for (double x =  -diameter /2; x <=  diameter /2 ; x++){
            for (double z =  -diameter /2; z <=  diameter /2 ; z++){
                if ( ((x * x) + (z*z)) < ( diameter /2) * ( diameter /2)) {
                    Block b = location.clone().add(x, 0, z).getBlock();
                    if (b.getType().isAir()) {
                        blocks.add(b);
                    }
                }
            }
        }
        return blocks;
    }

    public static Set<Block> getAirHollowCircleAroundPoint(Location location, double diameter){
        Set<Block>blocks = new HashSet<>();

        for (double x =  -diameter /2 -1; x <=  diameter /2 + 1 ; x++){
            for (double z =  -diameter /2 -1; z <=  diameter /2 + 1 ; z++){
                if ( ((x * x) + (z*z)) < ( diameter /2) * ( diameter /2) &&
                        (((x+1) * (x+1)) + ((z+1)*(z+1))) > ( diameter /2) * ( diameter /2)){

                    Block b = location.clone().add(x, 0, z).getBlock();

                    if (b.getType().isAir()) {
                        blocks.add(b);
                    }
                }
            }
        }
        return blocks;
    }
}
