package org.gtreimagined.gt5r.loader.multi;

import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.RAW_ORE;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.PYROLYSE_OVEN;
import static org.gtreimagined.gtcore.data.GTCoreItems.SELECTOR_TAG_INGREDIENTS;

public class PyrolysisOvenLoader {
    public static void init(){
        PYROLYSE_OVEN.RB().ii(DUST.getMaterialIngredient(Materials.OilShale, 16)).fo(Materials.Oil.getLiquid(400)).add("oilshale", 200, 120);
        PYROLYSE_OVEN.RB().ii(RAW_ORE.getMaterialIngredient(Materials.OilShale, 16)).fo(Materials.Oil.getLiquid(400)).add("oilshale_raw", 200, 120);
        PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(GTCoreItems.Biochaff, 1), SELECTOR_TAG_INGREDIENTS.get(1)).fi(AntimatterMaterials.Water.getLiquid(1000)).fo(Materials.Biomass.getLiquid(1500)).add("biomass", 100, 10);
        PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(GTCoreItems.Biochaff, 1), SELECTOR_TAG_INGREDIENTS.get(2)).fi(AntimatterMaterials.Water.getLiquid(1500)).fo(Materials.Biomass.getLiquid(1500)).add("fermented_biomass", 200, 10);
        PYROLYSE_OVEN.RB().ii(DUST.getMaterialIngredient(Sugar, 23), SELECTOR_TAG_INGREDIENTS.get(1)).io(DUST.get(Charcoal, 12)).fo(Water.getLiquid(11000)).add("sugar_to_charcoal", 320, 64);
        PYROLYSE_OVEN.RB().ii(DUST.getMaterialIngredient(Sugar, 23), SELECTOR_TAG_INGREDIENTS.get(2)).fi(Nitrogen.getGas(500)).io(DUST.get(Charcoal, 12)).fo(Water.getLiquid(11000)).add("sugar_to_charcoal_2", 160, 96);
        PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(5)).io(new ItemStack(Items.CHARCOAL, 20)).fo(WoodGas.getGas(1500)).add("wood_gas", 640, 64);
        PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(0)).io(new ItemStack(Items.CHARCOAL, 20)).fo(Creosote.getLiquid(4000)).add("creosote_oil", 640, 64);
        PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(2)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(Creosote.getLiquid(4000)).add("creosote_oil_nitrogen", 320, 64);
        PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(1)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(WoodGas.getGas(1500)).add("wood_gas_nitrogen", 320, 64);
        PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(3)).io(DUST.get(Ash, 4)).fo(OilHeavy.getLiquid(4000)).add("oil_heavy", 320, 64);
        if (GT5RConfig.COMPLICATED_CHEMICAL_PROCESSING.get()){
            PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(7)).io(new ItemStack(Items.CHARCOAL, 20)).fo(WoodVinegar.getLiquid(3000)).add("wood_vinegar", 640, 64);
            PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(9)).io(new ItemStack(Items.CHARCOAL, 20)).fo(WoodTar.getLiquid(1500)).add("wood_tar", 640, 64);
            PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(6)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(WoodGas.getGas(1500)).add("wood_gas_2", 320, 64);
            PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(8)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(WoodVinegar.getLiquid(3000)).add("wood_vinegar_2", 320, 64);
            PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(10)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(WoodTar.getLiquid(1500)).add("wood_tar_2", 320, 64);
            PYROLYSE_OVEN.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), SELECTOR_TAG_INGREDIENTS.get(4)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(CharcoalByproducts.getGas(4000)).add("charcoal_byproducts", 320, 64);
        }

    }
}
