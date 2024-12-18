package org.gtreimagined.gt5r.loader.machines.generator;

import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import net.minecraft.world.item.Items;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gtcore.data.GTCoreFluids;
import org.gtreimagined.gtcore.data.GTCoreMaterials;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.EnderEye;
import static org.gtreimagined.gt5r.data.GT5RMaterialTags.SEMIFUELS;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.*;

public class Fuels {
    public static void init() {
        AntimatterAPI.all(Material.class, mat -> {
            if (mat != Materials.Steam && mat != SuperheatedSteam && mat.has(MaterialTags.FUEL_POWER) && MaterialTags.FUEL_POWER.getInt(mat) > 0) {
                if (mat.has(LIQUID)) {
                    RecipeBuilder rb = mat.has(SEMIFUELS) ? SEMI_FUELS.RB() : COMBUSTION_FUELS.RB();
                    rb.fi(mat.getLiquid(1)).add(mat.getId(),1, MaterialTags.FUEL_POWER.getInt(mat));
                } else if (mat.has(GAS)) {
                    GAS_FUELS.RB().fi(mat.getGas(1)).add(mat.getId(),1, MaterialTags.FUEL_POWER.getInt(mat));
                }
            }
        });
        STEAM_FUELS.RB().fi(Steam.getFluidIngredient(2)).add("steam",1,1);
        HP_STEAM_FUELS.RB().fi(FluidIngredient.ofMB(SuperheatedSteam, 1)).add("superheated_steam", 1, 1);
        MAGIC_FUELS.RB().ii(GEM_CHIPPED.getMaterialIngredient(Amber, 1)).add("chipped_amber", 1, 1000);
        MAGIC_FUELS.RB().ii(PLATE.getMaterialIngredient(Amber, 1)).add("amber_plate", 1, 3000);
        MAGIC_FUELS.RB().ii(GEM_FLAWED.getMaterialIngredient(Amber, 1)).add("flawed_amber", 1, 3000);
        MAGIC_FUELS.RB().ii(DUST.getMaterialIngredient(Amber, 1)).add("amber_dust", 1, 3000);
        MAGIC_FUELS.RB().ii(GEM.getMaterialIngredient(Amber, 1)).add("amber", 1, 6000);
        MAGIC_FUELS.RB().ii(GEM_FLAWLESS.getMaterialIngredient(Amber, 1)).add("flawless_amber", 1, 12000);
        MAGIC_FUELS.RB().ii(GEM_EXQUISITE.getMaterialIngredient(Amber, 1)).add("exquisite_amber", 1, 24000);
        MAGIC_FUELS.RB().ii(Items.EXPERIENCE_BOTTLE).add("bottle_o_enchanting", 1, 10000);
        MAGIC_FUELS.RB().ii(DUST.getMaterialIngredient(EnderEye, 1)).add("ender_eye_dust", 1, 10000);
        MAGIC_FUELS.RB().ii(PLATE.getMaterialIngredient(EnderEye, 1)).add("ender_eye_plate", 1, 10000);
        MAGIC_FUELS.RB().ii(Items.ENDER_EYE).add("ender_eye", 1, 20000);
        MAGIC_FUELS.RB().ii(Items.GHAST_TEAR).add("ghast_tear", 1, 50000);
        MAGIC_FUELS.RB().ii(Items.ENCHANTED_GOLDEN_APPLE).add("enchanted_golden_apple", 1, 6400000);
        MAGIC_FUELS.RB().ii(Items.NETHER_STAR).add("nether_star", 1, 100000000);
        MAGIC_FUELS.RB().ii(Items.BEACON).add("beacon", 1, 100000000);
        MAGIC_FUELS.RB().ii(Items.ENCHANTED_BOOK).io(Items.BOOK).fake().add("enchanted_book", 1, 1000, 1);
        if (AntimatterAPI.isModLoaded(Ref.TWILIGHT_FOREST)){
            MAGIC_FUELS.RB().ii(DUST.getMaterialIngredient(GTCoreMaterials.Ironwood, 1)).add("ironwood_dust", 1, 8000);
            MAGIC_FUELS.RB().ii(INGOT.getMaterialIngredient(GTCoreMaterials.Ironwood, 1)).add("ironwood_ingot", 1, 8000);
            MAGIC_FUELS.RB().ii(PLATE.getMaterialIngredient(GTCoreMaterials.Ironwood, 1)).add("ironwood_plate", 1, 8000);
            MAGIC_FUELS.RB().ii(DUST.getMaterialIngredient(GTCoreMaterials.Steeleaf, 1)).add("steeleaf_dust", 1, 24000);
            MAGIC_FUELS.RB().ii(INGOT.getMaterialIngredient(GTCoreMaterials.Steeleaf, 1)).add("steeleaf_ingot", 1, 24000);
            MAGIC_FUELS.RB().ii(PLATE.getMaterialIngredient(GTCoreMaterials.Steeleaf, 1)).add("steeleaf_plate", 1, 24000);
            MAGIC_FUELS.RB().ii(DUST.getMaterialIngredient(GTCoreMaterials.Knightmetal, 1)).add("knightmetal_dust", 1, 24000);
            MAGIC_FUELS.RB().ii(INGOT.getMaterialIngredient(GTCoreMaterials.Knightmetal, 1)).add("knightmetal_ingot", 1, 24000);
            MAGIC_FUELS.RB().ii(PLATE.getMaterialIngredient(GTCoreMaterials.Knightmetal, 1)).add("knightmetal_plate", 1, 24000);
            MAGIC_FUELS.RB().fi(FluidHooks.newFluidHolder(GTCoreFluids.FIERY_BLOOD.getFluid(), 1, null)).add("fiery_blood", 1, 2048);
            MAGIC_FUELS.RB().fi(FluidHooks.newFluidHolder(GTCoreFluids.FIERY_TEARS.getFluid(), 1, null)).add("fiery_tears", 1, 2048);
            MAGIC_FUELS.RB().ii(DUST.getMaterialIngredient(GTCoreMaterials.FierySteel, 1)).add("fiery_steel_dust", 1, 2048000);
            MAGIC_FUELS.RB().ii(INGOT.getMaterialIngredient(GTCoreMaterials.FierySteel, 1)).add("fiery_steel_ingot", 1, 2048000);
            MAGIC_FUELS.RB().ii(PLATE.getMaterialIngredient(GTCoreMaterials.FierySteel, 1)).add("fiery_steel_plate", 1, 2048000);
        }
    }
}
