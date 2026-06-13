package com.sonorous.level.world.biome;

import com.sonorous.level.world.biome.biomefeatures.Feature;
import com.sonorous.level.world.biome.biomefeatures.FeatureBiome;
import com.sonorous.level.world.biome.biomefeatures.FloraBiome;
import com.sonorous.level.world.biome.biomefeatures.TreeBiome;
import com.sonorous.level.world.biome.biomes.BiomeCategories;
import com.sonorous.utils.ReflectionUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Biome;

import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;

public abstract class BiomeRepresentation {

    protected Biome biome;
    protected String name;
    protected Map<BiomeLayers, List<Material>> layers;
    protected double temperature, continentalness, humidity, weirdness = 0;

    private static final Map<Biome, BiomeRepresentation> BIOME_MAP = new ConcurrentHashMap<>();
    private static final Set<Biome> VALID_BIOMES = ConcurrentHashMap.newKeySet();
    private static final Map<BiomeCategories, Set<BiomeRepresentation>> BIOME_CATEGORIES_MAP = new ConcurrentHashMap<>();
    private static final Map<Biome, FeatureBiome> FEATURE_BIOMES = new ConcurrentHashMap<>();
    private static final Map<Biome, TreeBiome> TREE_BIOMES = new ConcurrentHashMap<>();
    private static final Map<Biome, FloraBiome> FLORA_BIOMES = new ConcurrentHashMap<>();

    public static BiomeRepresentation getBiomeRepresentation(Biome biome){
        return BIOME_MAP.get(biome);
    }

    public static Set<Biome> getValidBiomes() {
        return VALID_BIOMES;
    }

    public static Set<BiomeRepresentation> getBiomeRepresentations(BiomeCategories categories) {
        return BIOME_CATEGORIES_MAP.get(categories);
    }

    public static void initBiomes(){
        ReflectionUtils.findAllClasses("com.sonorous.level.world.biome.biomes").stream()
                .forEach(aClass -> {
                    try {
                        Bukkit.getServer().getLogger().log(Level.INFO, () -> aClass.getName() + " is loaded");
                        aClass.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public static boolean isFeatureBiome(Biome biome){
        return (FEATURE_BIOMES.containsKey(biome));
    }

    public static Map<Feature, Double> getBiomeFeatures(Biome biome){
        return FEATURE_BIOMES.get(biome).getFeatures();
    }

    public static boolean isTreeBiome(Biome biome){
        return (TREE_BIOMES.containsKey(biome));
    }

    public static List<TreeType> getTreeTypes(Biome biome){
        return List.of(TREE_BIOMES.get(biome).getTreeType());
    }

    public static boolean isFloraBiome(Biome biome){
        return (FLORA_BIOMES.containsKey(biome));
    }

    public static Map<Material, Integer> getFloraTypes(Biome biome){
        return FLORA_BIOMES.get(biome).getFlora();
    }

    public BiomeRepresentation(org.bukkit.block.Biome biome, String name, Map<BiomeLayers, List<Material>> layers, double temperature, double continentalness, double humidity, BiomeCategories categories){
        this.biome = biome;
        this.name = name;
        this.layers = Collections.unmodifiableMap(layers);
        this.temperature = temperature;
        this.continentalness = continentalness;
        this.humidity = humidity;
        BIOME_MAP.put(biome, this);
        VALID_BIOMES.add(biome);
        if (this instanceof TreeBiome treeBiome){
            TREE_BIOMES.put(biome, treeBiome);
        }
        if (this instanceof FloraBiome floraBiome){
            FLORA_BIOMES.put(biome, floraBiome);
        }
        if (this instanceof FeatureBiome featureBiome){
            FEATURE_BIOMES.put(biome, featureBiome);
        }
        Set<BiomeRepresentation> categoryBiomes = BIOME_CATEGORIES_MAP.computeIfAbsent(categories, k -> new CopyOnWriteArraySet<>());
        categoryBiomes.add(this);
    }

    public BiomeRepresentation(org.bukkit.block.Biome biome, String name, Map<BiomeLayers, List<Material>> layers, double temperature, double continentalness, double humidity, double weirdness, BiomeCategories categories){
        this.biome = biome;
        this.name = name;
        this.layers = Collections.unmodifiableMap(layers);
        this.temperature = temperature;
        this.continentalness = continentalness;
        this.humidity = humidity;
        this.weirdness = weirdness;
        BIOME_MAP.put(biome, this);
        VALID_BIOMES.add(biome);
        if (this instanceof TreeBiome treeBiome){
            TREE_BIOMES.put(biome, treeBiome);
        }
        if (this instanceof FloraBiome floraBiome){
            FLORA_BIOMES.put(biome, floraBiome);
        }
        if (this instanceof FeatureBiome featureBiome){
            FEATURE_BIOMES.put(biome, featureBiome);
        }
        Set<BiomeRepresentation> categoryBiomes = BIOME_CATEGORIES_MAP.computeIfAbsent(categories, k -> new CopyOnWriteArraySet<>());
        categoryBiomes.add(this);
    }

    public boolean isBiomeInCategory(BiomeCategories category){
        Set<BiomeRepresentation> biomes = BIOME_CATEGORIES_MAP.get(category);
        return biomes != null && biomes.contains(this);
    }

    public org.bukkit.block.Biome getBiome(){
        return this.biome;
    }

    public String getName() {
        return this.name;
    }

    public Map<BiomeLayers, List<Material>> getLayers(){
        return this.layers;
    }

    public double getTemperature(){
        return this.temperature;
    }

    public double getContinentalness(){
        return this.temperature;
    }

    public double getHumidity(){
        return this.humidity;
    }

    public double getWeirdness() {
        return weirdness;
    }
}