package org.gtreimagined.gt5r.loader.crafting;

import muramasa.antimatter.datagen.loaders.MaterialRecipes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gtcore.data.GTCoreItems;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static org.gtreimagined.gt5r.data.Materials.*;

public class Smelting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.IRON_NUGGET), NUGGET.get(Materials.WroughtIron), 0.5f, 200).unlockedBy("has_nugget_iron", provider.hasSafeItem(Items.IRON_NUGGET)).save(output, "smelting_nugget_wrought_iron");
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.IRON_NUGGET), NUGGET.get(Materials.WroughtIron), 0.5f, 100).unlockedBy("has_nugget_iron", provider.hasSafeItem(Items.IRON_NUGGET)).save(output, "blasting_nugget_wrought_iron");
        SimpleCookingRecipeBuilder.smelting(RecipeIngredient.of(GTCoreItems.CompressedFireClay, 1), GTCoreItems.FireBrick, 0.5F, 200).unlockedBy("has_compressed_fire_clay", provider.hasSafeItem(GTCoreItems.CompressedFireClay)).save(output, GT5RRef.ID + ":firebrick");
        MaterialRecipes.addSmeltingRecipe(output, provider, CRUSHED, NUGGET, 7, Cassiterite, Tin);
        MaterialRecipes.addSmeltingRecipe(output, provider, CRUSHED, NUGGET, 7, Garnierite, Nickel);
        if (GT5RConfig.HARDER_ALUMINIUM_PROCESSING.get()) {
            MaterialRecipes.addSmeltingRecipe(output, provider, DUST, NUGGET, 3, AluminiumHydroxide, Alumina);
            MaterialRecipes.addSmeltingRecipe(output, provider, DUST, INGOT, 1, Aluminium);
        }
    }
}
