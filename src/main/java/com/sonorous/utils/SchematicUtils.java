package com.sonorous.utils;

import com.fastasyncworldedit.core.FaweAPI;
import com.fastasyncworldedit.core.function.mask.SingleBlockTypeMask;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.mask.BlockMask;
import com.sk89q.worldedit.function.mask.Mask;
import com.sk89q.worldedit.function.mask.SolidBlockMask;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/***
 * An intermediary between WorldEdit API to simplify schematic usage
 */
public class SchematicUtils {

    /***
     * Spawns a Schematic at a given location
     * @param schematicName the name of the schematic file
     * @param location The Bukkit Location within a World
     * @throws IOException If no file is found
     */
    public static void spawnSchematic(String schematicName, Location location) throws IOException {
        File file = new File(schematicName + ".schematic");
        Clipboard clipboard;

        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {

            clipboard = reader.read();
            pasteClipboard(clipboard, location);
        }
    }

    /***
     * Spawns a Schematic at a given location
     * @param file the schematic file
     * @param location The Bukkit Location within a World
     * @throws IOException If no file is found
     */
    public static void spawnSchematic(File file, Location location) throws IOException {
        pasteClipboard(createClipboard(file), location);
    }

    /***
     * Initialises Clipboards which must be done before a schematic can be pasted
     * @param file the schematic file
     * @return the relevant clipboard to the schematic
     * @throws IOException If no file is found
     */
    public static Clipboard createClipboard(File file) throws IOException {
        Clipboard clipboard;
        if (file == null){
            Bukkit.broadcastMessage("file is null");
        }
        ClipboardFormat format = ClipboardFormats.findByFile(file);

        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            clipboard = reader.read();
            return clipboard;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * Pastes a clipboard at a given location
     * @param clipboard the clipboard which represents an intended schematic
     * @param location the Location for the clipboard to be pasted at
     */
    public static void pasteClipboard(Clipboard clipboard, Location location) {
        World world = FaweAPI.getWorld(location.getWorld().getName());

        BlockVector3 pos1 = BlockVector3.at(location.getX(), location.getY(), location.getZ());


        try (EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {

            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(pos1)
                    // configure here
                    .build();

            Operations.complete(operation);

        } catch (WorldEditException e) {
            throw new RuntimeException(e);
        }

        BlockVector3 pos2 = pos1.add(clipboard.getMaximumPoint());

        CuboidRegion cuboidRegion = new CuboidRegion(pos1,pos2 );
        // Replace Blocks

    }

    private static void replaceBlocks(World world, CuboidRegion cuboidRegion, BlockType from, BlockType to){
        try (EditSession editSession = WorldEdit.getInstance().newEditSession(world)) {
            editSession.replaceBlocks(cuboidRegion, new SingleBlockTypeMask(world, from), to.getDefaultState().toBaseBlock());
//            editSession.setBlocks(cuboidRegion, chest.getDefaultState().toBaseBlock());
        } catch (WorldEditException e) {
            throw new RuntimeException(e);
        }
    }
}



