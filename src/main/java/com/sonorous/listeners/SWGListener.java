package com.sonorous.listeners;

import com.sonorous.loader.ChunkHandler;
// import com.sereneoasis.npc.random.types.BasicNPC;
// import com.sereneoasis.utils.NPCUtils;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.GameRules;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SWGListener implements Listener {


    private static final Random random = new Random();


//    private void genTrees(Chunk chunk, ChunkLoadEvent event){
//        int snapshotX = 0;
//        int y = 256;
////        int snapshotZ = random.nextInt(16);
//        int snapshotZ = 0;
//
//        int x = snapshotX + chunk.getX() * 16;
//        int z = snapshotZ + chunk.getZ() * 16;
//
//        if (random.nextDouble() < 0.002 && !KingdomUtils.isInsideKingdom(x, z)){
//
//            while(event.getChunk().getChunkSnapshot(false, false, false).getBlockType(snapshotX, y, snapshotZ).isAir() && y > -64) {
//                y--;
//                if (y == 0 ){
//                    return;
//                }
//            }
//
//            Location loc = new Location(event.getWorld(), x, y, z);
//            loc.setYaw(90 * random.nextInt(0, 4));
//
//            TreeGenerationUtils.genRandomTree(loc, random);
//        }
//    }




    private static ChunkHandler chunkHandler;
    @EventHandler
    public void onWorldLoad(WorldLoadEvent event){


            World world = event.getWorld();
            Bukkit.broadcastMessage("World initialised");
//            chunkHandler = new ChunkHandler(world);
        world.setGameRule(GameRules.WATER_SOURCE_CONVERSION, false);
    }




//    @EventHandler
//    public void onChunkLoad(ChunkLoadEvent event) {
//
//        if (!event.isNewChunk()) {
//            return;
//        }
//        Chunk chunk = event.getChunk();
//
//
////        genTrees(chunk, event);
//
//
//    }

//    private static final ConcurrentHashMap<Chunk, BasicNPC > npcs = new ConcurrentHashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
    }

    private static final ConcurrentHashMap<UUID, Biome> biomeTracker = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<UUID, Chunk> chunkTracker = new ConcurrentHashMap<>();



   /* @EventHandler
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        Player player = playerMoveEvent.getPlayer();
        if (player.getName().equals("Sakrajin")) {
            World world = player.getWorld();
            Biome newBiome = world.getBiome(playerMoveEvent.getTo());
            Biome previousBiome = biomeTracker.get(player.getUniqueId());

            if (previousBiome != newBiome) {
                player.sendTitle(newBiome.getKey().getKey().toString(), "Welcome!", 10, 40, 20);
                biomeTracker.put(player.getUniqueId(), newBiome);
            }
            Chunk previousChunk = chunkTracker.get(player.getUniqueId());

            Chunk newChunk = playerMoveEvent.getTo().getChunk();
            if (previousChunk == null ||  previousChunk.getX() != newChunk.getX() || previousChunk.getZ() != newChunk.getZ() ) {
//                Bukkit.broadcastMessage("new chunk");
                chunkTracker.put(player.getUniqueId(), newChunk);
                BasicNPC serverPlayer = npcs.get(newChunk);
                if (serverPlayer != null) {
//                    Bukkit.broadcastMessage("robot on");
                    NPCUtils.addNPC(player, serverPlayer);
                    NPCUtils.toggleOn(serverPlayer);
                }
//                npcs.forEach((chunk, npcMaster) -> {
//                    if (chunk.getX() != chunk.getX() || chunk.getZ() != chunk.getZ()) {
//                        NPCUtils.toggleOff(npcMaster);
//                    }
//                });
            }

        }
    }
*/


}
