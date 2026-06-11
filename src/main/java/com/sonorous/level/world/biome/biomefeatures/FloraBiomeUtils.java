package com.sonorous.level.world.biome.biomefeatures;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.HashMap;

/***
 * Some basic templates of vegetation which biomes can use
 */
public class FloraBiomeUtils {

    private static final Material[] smallFlowers = new Material[]{Material.POPPY, Material.LILY_OF_THE_VALLEY, Material.ALLIUM,
            Material.DANDELION, Material.AZURE_BLUET, Material.ORANGE_TULIP, Material.PINK_TULIP, Material.RED_TULIP,
            Material.WHITE_TULIP, Material.TORCHFLOWER, Material.BLUE_ORCHID, Material.OXEYE_DAISY};

    public static HashMap<Material, Integer> getSmallFlowers(int amount) {
        HashMap<Material, Integer>flora = new HashMap<>();
        Arrays.stream(smallFlowers).forEach(material -> {
            flora.put(material, amount);
        });
        return flora;
    }


    private static final Material[] tallFlowers = new Material[]{Material.ROSE_BUSH, Material.LILAC, Material.PEONY, Material.SUNFLOWER};


    public static HashMap<Material, Integer> getTallFlowers(int amount) {
        HashMap<Material, Integer>flora = new HashMap<>();
        Arrays.stream(tallFlowers).forEach(material -> {
            flora.put(material, amount);
        });
        return flora;
    }

    public static HashMap<Material, Integer> getFlowers(int amount) {
        HashMap<Material, Integer>flora = new HashMap<>();
        flora.putAll(getSmallFlowers(amount));
        flora.putAll(getTallFlowers(amount));
        return flora;
    }
}
