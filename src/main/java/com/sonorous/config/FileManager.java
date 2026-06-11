package com.sonorous.config;


import java.io.File;
import java.util.stream.Stream;

public class FileManager {

    private final File mainDir, schemDir, chatDir, smallHouseDir, mediumHouseDir, bigHouseDir;

    public File getChatDir() {
        return chatDir;
    }

    public FileManager(){
        mainDir = getOrCreateDir("SonorousWorldGen");
        schemDir = getOrCreateDir("Schematics", mainDir);
        smallHouseDir = getOrCreateDir("SmallHouses", schemDir);
        mediumHouseDir = getOrCreateDir("MediumHouses", schemDir);
        bigHouseDir = getOrCreateDir("BigHouses", schemDir);
        chatDir = getOrCreateDir("Chats", mainDir);
    }

    public File[] getSmallHouseSchematics(){
        return smallHouseDir.listFiles();
    }

    public File[] getMediumHouseSchematics(){
        return mediumHouseDir.listFiles();
    }
    public File[] getBigHouseSchematics(){
        return bigHouseDir.listFiles();
    }

    public File[] getSchematics(){
        return Stream.of(
                        getSmallHouseSchematics(),
                        getMediumHouseSchematics(),
                        getBigHouseSchematics()
                )
                .filter(files -> files != null)
                .flatMap(Stream::of)
                .toArray(File[]::new);
    }


    private static File getOrCreateDir(String name){
        File file = new File(name);
        if (!file.isDirectory()) {
            file.mkdir();
        }
        return file;
    }

    private static File getOrCreateDir(String name, File parent){
        File file = new File(parent, name);
        if (!file.isDirectory()) {
            file.mkdir();
        }
        return file;
    }
}
