package com.sonorous;

import com.sonorous.command.SWGCommand;
import com.sonorous.config.FileManager;
import com.sonorous.level.world.biome.BiomeRepresentation;
import com.sonorous.level.world.chunk.CustomChunkGenerator;
import com.sonorous.level.world.noise.NoiseMaster;
import com.sonorous.listeners.SWGListener;
//// import com.sereneoasis.utils.NPCUtils;
import org.bukkit.GameRule;
import org.bukkit.GameRules;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/***
 * The main class of the plugin
 */
public class SonorousWorldGen extends JavaPlugin {

    public static SonorousWorldGen plugin;

    private static FileManager fileManager;

    /***
     * Returns the class used to manage files
     * @return the file manager
     */
    public static FileManager getFileManager() {
        return fileManager;
    }


    @Override
    public void onEnable() {

        getLogger().log(Level.INFO, "WorldGenerator was enabled successfully.");
        plugin = this;
        fileManager = new FileManager();
        this.getServer().getPluginManager().registerEvents(new SWGListener(), this);
        this.getCommand("SonorousWorldGen").setExecutor(new SWGCommand());
//        NPCUtils.initUUID(0, this);
    }


    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, "WorldGenerator was disabled successfully.");
    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        BiomeRepresentation.initBiomes();
        NoiseMaster.initNoise();
        getLogger().log(Level.WARNING, "CustomChunkGenerator is used!");
        return new CustomChunkGenerator();
    }

}