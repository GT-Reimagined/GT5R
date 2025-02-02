package org.gtreimagined.gt5r;

import carbonconfiglib.config.Config;
import carbonconfiglib.config.ConfigEntry;
import carbonconfiglib.config.ConfigHandler;
import carbonconfiglib.config.ConfigSection;
import muramasa.antimatter.util.AntimatterPlatformUtils;

public class GT5RConfig {

    //TODO needed?
    public static boolean MORE_COMPLICATED_CHEMICAL_RECIPES = true;
    //TODO change to gt core's config for this
    public static boolean HARDER_CIRCUITS = false;
    public static ConfigEntry.BoolValue HARDER_ALUMINIUM_PROCESSING;
    public static ConfigEntry.BoolValue GT5U_OIL;
    public static ConfigEntry.BoolValue COMPLICATED_CHEMICAL_PROCESSING;
    public static ConfigEntry.BoolValue HARD_CARBON;
    public static ConfigEntry.BoolValue GT6_ORE_GEN;
    public static ConfigEntry.DoubleValue ASPHALT_MULTIPLIER;
    public static ConfigEntry.BoolValue ADD_LOOT;
    static ConfigHandler CONFIG;

    public static void createConfig(){
        Config config = new Config("gt5r");
        ConfigSection section = config.add("general");
        /*MORE_COMPLICATED_CHEMICAL_RECIPES = section.addBool("more_complicated_chemical_recipes", false, "Enables more complicated chemical recipes. - Default: false");
        HARDER_CIRCUITS = section.addBool("harder_circuits", false, "Enables more complicated circuit recipes added in versions of gt5u after 509.25 - Default: false");*/
        GT5U_OIL = section.addBool("gt5u_oil", false, "Enables gt5u oil processing, if false gt6 oil processing is used instead. - Default: false");
        HARDER_ALUMINIUM_PROCESSING = section.addBool("harder_aluminium_processing", true, "Enables gt6's alumina processing, if disabled alumina reverts back to just being in the blast furnace - Default: true");
        GT6_ORE_GEN = section.addBool("gt6_ore_gen", false, "Enables gt6 style veins insteadof gt5 style veins. - Default: false");
        ASPHALT_MULTIPLIER = section.addDouble("asphalt_multiplier", 1.1, "Default speed multiplier applied by concrete.");
        HARD_CARBON = section.addBool("hard_carbon", false, "Makes carbon fibre require the hard recipe from gt5u. - Default: false");
        COMPLICATED_CHEMICAL_PROCESSING = section.addBool("complicated_chemical_processing", false, "Enables complicated chemical recipes");
        ADD_LOOT = section.addBool("add_loot", true, "Enables chest loot for GT5R. - Default: true");
        CONFIG = AntimatterPlatformUtils.INSTANCE.createConfig(GT5RRef.ID, config);
        CONFIG.register();
    }
}
