//package com.sereneoasis.level.world.chunk.populator;
//
//// import com.sereneoasis.level.world.Schematics;
//import org.bukkit.Bukkit;
//import org.bukkit.Location;
//import org.bukkit.generator.BlockPopulator;
//import org.bukkit.generator.LimitedRegion;
//import org.bukkit.generator.WorldInfo;
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Random;
//import java.util.logging.Level;
//
//public class StructurePopulator extends BlockPopulator {
//
//    @Override
//    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
//        if (limitedRegion.getBuffer() >= 16 && random.nextDouble() < 0.001) {
//            int x = random.nextInt(16) + chunkX * 16;
//            int z = random.nextInt(16) + chunkZ * 16;
//            int y = 319;
//            while(limitedRegion.getType(x, y, z).isAir() && y > -64) y--;
//
//            Location location = new Location(Bukkit.getWorld(worldInfo.getUID()), x, y+ 10, z);
//
//            Schematics.pasteClipboard("fort1", location);
//            Bukkit.getServer().getLogger().log(Level.INFO, location.toString());
//
//        }
//    }
//}
