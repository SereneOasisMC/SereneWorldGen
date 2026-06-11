package com.sonorous.command;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.sonorous.level.world.tree.TreeGenerationUtils;
import com.sonorous.loader.ChunkHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class SWGCommand implements CommandExecutor {

//    private static final Set<ChatMaster> chatMasters = new HashSet<>();

    public static final int CHUNK_SQUARE_TO_LOAD = 100;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player player) {
            if (strings.length == 0) {

            }
            switch (strings[0]) {

                case "tree" -> {
                    int iterations = Integer.valueOf(strings[2]);
                    switch (strings[1]) {
                        case "fir" -> {
                            TreeGenerationUtils.generateFirTree(player.getLocation(), iterations, new Random());
                        }
                        case "acacia" -> {
                            TreeGenerationUtils.generateAcaciaTree(player.getLocation(), iterations, new Random());
                        }
                        case "birch" -> {
                            TreeGenerationUtils.generateBirchTree(player.getLocation(), iterations, new Random());
                        }
                        case "spruce" -> {
                            TreeGenerationUtils.generateSpruceTree(player.getLocation(), iterations, new Random());
                        }
                        case "oak" -> {
                            TreeGenerationUtils.generateOakTree(player.getLocation(), iterations, new Random());
                        }
                        case "jungle" -> {
                            TreeGenerationUtils.generateJungleTree(player.getLocation(), iterations, new Random());
                        }
                        case "cherry" -> {
                            TreeGenerationUtils.generateCherryTree(player.getLocation(), iterations, new Random());
                        }
                        default -> {
                            player.sendMessage("Not a valid tree");
                        }
                    }
                }


            }

        }

        switch(strings[0]) {
            case "create" -> {
                if (strings.length == 2) {
                    MultiverseCore core = (MultiverseCore) Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
                    MVWorldManager worldManager = core.getMVWorldManager();

                    if (worldManager.isMVWorld(strings[1])) {
                        worldManager.deleteWorld(strings[1]);
                    }

                    worldManager.addWorld(
                            strings[1], // The worldname
                            World.Environment.NORMAL, // The overworld environment type.
                            null, // The world seed. Any seed is fine for me, so we just pass null.
                            WorldType.NORMAL, // Nothing special. If you want something like a flat world, change this.
                            true, // This means we want to structures like villages to generator, Change to false if you don't want this.
                            "SonorousWorldGen" // Specifies a custom generator. We are not using any so we just pass null.
                    );

                    new ChunkHandler(worldManager.getMVWorld(strings[1]).getCBWorld());

//                        ChunkyAPI chunky = Bukkit.getServer().getServicesManager().load(ChunkyAPI.class);
//
//                        chunky.startTask(strings[1], "square", 0, 0, CHUNK_SQUARE_TO_LOAD * 16, CHUNK_SQUARE_TO_LOAD * 16, "concentric");
//                        chunky.onGenerationComplete(event -> SonorousWorldGen.plugin.getLogger().info("Generation completed for " + event.world()));

                }
            }
        }
        return true;
    }
}