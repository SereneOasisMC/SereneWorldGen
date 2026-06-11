package com.sonorous.utils;

import com.sonorous.SonorousWorldGen;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;

import java.util.Random;

public class StructureUtils {

    // https://minecraft.fandom.com/wiki/Ancient_City
    public static void spawnStructure(Location loc, String name){
        SonorousWorldGen.plugin.getServer().getStructureManager().loadStructure(NamespacedKey.minecraft(name)).place(loc, false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
    }

    public static void spawnStructure(Location loc) {
        SonorousWorldGen.plugin.getServer().getStructureManager().getStructures().values().stream().findAny().get().place(loc, false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
//        SonorousWorldGen.plugin.getServer().getStructureManager().getStructures().values().stream().findAny().get().place(loc, false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
    }

}
