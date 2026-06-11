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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;

/***
 * Represents data associated with a Vanilla Biome
 */
public abstract class BiomeRepresentation {

    // The Bukkit Biome
    protected Biome biome;

    // A name to refer to the biome as
    protected String name;

    // A HashMap between BiomeLayers and a list of the materials contained
    protected HashMap<BiomeLayers, List<Material>>layers;

    // Biome specific characteristics
    protected double temperature, continentalness, humidity, weirdness = 0;

    // A HashMap tying Biomes in to their representations in case stored data needs to be retrieved
    private static final HashMap<Biome, BiomeRepresentation> BIOME_MAP = new HashMap<>();

    /***
     * Obtains the Biome representation per biome
     * @param biome The Biome that we want to obtain the representation for
     * @return the Biome Representation of a given biome
     */
    public static BiomeRepresentation getBiomeRepresentation(Biome biome){
        return BIOME_MAP.get(biome);
    }

    /***
     * The Biomes that have been initialised
     */
    private static final HashSet<Biome> VALID_BIOMES = new HashSet<>();

    /***
     * Retrieves valid biomes
     * @return The Biomes that have been initialised
     */
    public static HashSet<Biome> getValidBiomes() {
        return VALID_BIOMES;
    }

    /***
     * A HashMap with Biome Categories as keys and a HashSet of BiomeRepresentations as values.
     */
    private static final HashMap<BiomeCategories, HashSet<BiomeRepresentation>> BIOME_CATEGORIES_MAP = new HashMap<>();

    /***
     * Retrieves the BiomeRepresentations under a category
     * @param categories the specified category which the BiomeRepresentations will be under
     * @return A HashMap with Biome Categories as keys and a HashSet of BiomeRepresentations as values.
     */
    public static HashSet<BiomeRepresentation> getBiomeRepresentations(BiomeCategories categories) {
        return BIOME_CATEGORIES_MAP.get(categories);
    }

    /***
     * Uses Reflection to initialise all biomes with their representation classes
     */
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


    private static final HashMap<Biome, FeatureBiome>FEATURE_BIOMES = new HashMap<>();

    public static boolean isFeatureBiome(Biome biome){
        return (FEATURE_BIOMES.containsKey(biome));
    }

    public static HashMap<Feature, Double> getBiomeFeatures(Biome biome){
        return FEATURE_BIOMES.get(biome).getFeatures();
    }
    
    
    private static final HashMap<Biome, TreeBiome>TREE_BIOMES = new HashMap<>();

    public static boolean isTreeBiome(Biome biome){
        return (TREE_BIOMES.containsKey(biome));
    }

    public static List<TreeType> getTreeTypes(Biome biome){
        return List.of(TREE_BIOMES.get(biome).getTreeType());
    }


    private static final HashMap<Biome, FloraBiome>FLORA_BIOMES = new HashMap<>();

    public static boolean isFloraBiome(Biome biome){
        return (FLORA_BIOMES.containsKey(biome));
    }

    public static HashMap<Material, Integer> getFloraTypes(Biome biome){
        return FLORA_BIOMES.get(biome).getFlora();
    }

    public BiomeRepresentation(org.bukkit.block.Biome biome, String name, HashMap<BiomeLayers, List<Material>> layers, double temperature, double continentalness, double humidity, BiomeCategories categories){
        this.biome = biome;
        this.name = name;
        this.layers = layers;
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
        HashSet<BiomeRepresentation> categoryBiomes = BIOME_CATEGORIES_MAP.getOrDefault(categories, new HashSet<>());
        categoryBiomes.add(this);
        BIOME_CATEGORIES_MAP.put(categories, categoryBiomes);
    }

    public BiomeRepresentation(org.bukkit.block.Biome biome, String name, HashMap<BiomeLayers, List<Material>> layers, double temperature, double continentalness, double humidity, double weirdness, BiomeCategories categories){
        this.biome = biome;
        this.name = name;
        this.layers = layers;
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

        HashSet<BiomeRepresentation> categoryBiomes = BIOME_CATEGORIES_MAP.getOrDefault(categories, new HashSet<>());
        categoryBiomes.add(this);
        BIOME_CATEGORIES_MAP.put(categories, categoryBiomes);
    }


    /***
     * Represents the Vanilla Biome
     * @return The Vanilla Biome associated with this class
     */
    public org.bukkit.block.Biome getBiome(){
        return this.biome;
    }

    /***
     * Returns the name
     * @return The name of the Biome which should be displayed
     */
    public String getName() {
        return this.name;
    }

    /***
     * Represents the layers a Biome contains for World Generation purposes
     * @return A HashMap associating Biome layers with a list of materials
     */
    public HashMap<BiomeLayers, List<Material>>getLayers(){
        return this.layers;
    }

    /***
     * Represents the temperature, used to dictate where it will generate
     * @return A double showing Biome temperature from -1 to 1
     */
    public double getTemperature(){
        return this.temperature;
    }

    /***
     * Represents the continentalness, used to dictate how inland it will generate
     * @return A double showing continentalness from -1 to 1
     */
    public double getContinentalness(){
        return this.temperature;
    }

    /***
     * Represents the humidity, used to dictate where it will generate
     * @return A double showing humidity from -1 to 1
     */
    public double getHumidity(){
        return this.humidity;
    }

    /***
     * Represents the weirdness, dictates whether a variant of a biome should spawn
     * @return A double which makes this biome less likely to spawn
     */
    public double getWeirdness() {
        return weirdness;
    }
}
