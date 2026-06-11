package com.sonorous.level.world;

import com.sonorous.SonorousWorldGen;
import com.sonorous.utils.SchematicUtils;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.logging.Level;

/***
 * Caches schematics on plugin enable.
 * This is used to randomly generate structures (of .schem filetypes) throughout the world.
 * WorldEdit is used to do this.
 */
public class Schematics {

    // Represents all of the schematics stored
    private static final Supplier<HashMap<String, Clipboard>> schematicClipboards = () -> {
        HashMap<String, Clipboard> stringClipboardHashMap = new HashMap<>();
        Arrays.stream(SonorousWorldGen.getFileManager().getSchematics()).forEach(file -> {
            try {
                stringClipboardHashMap.put(file.getName(), SchematicUtils.createClipboard(file));
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.INFO, "Invalid schematic provided or mistake reading");
            }
        });
        return stringClipboardHashMap;
    };

    /***
     * Pastes initialised clipboards at a specified location
     * @param filename the name of the schematic file
     * @param location where the schematic should be pasted
     */
    public static void pasteClipboard(String filename, Location location){
        Clipboard clipboard = schematicClipboards.get().get(filename);
        SchematicUtils.pasteClipboard(clipboard, location);
    }

}
