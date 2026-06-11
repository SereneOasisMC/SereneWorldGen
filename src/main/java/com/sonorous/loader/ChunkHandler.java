package com.sonorous.loader;

import org.bukkit.World;

import static com.sonorous.command.SWGCommand.CHUNK_SQUARE_TO_LOAD;

public class ChunkHandler {


    private int squareLength = CHUNK_SQUARE_TO_LOAD;


    public ChunkHandler(World world) {

        new Kingdom(world, squareLength);
//        new Town(world, squareLength);
    }



}