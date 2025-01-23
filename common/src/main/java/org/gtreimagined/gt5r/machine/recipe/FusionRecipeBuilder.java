package org.gtreimagined.gt5r.machine.recipe;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;

public class FusionRecipeBuilder extends RecipeBuilder {
    int huOutput;
    @Override
    protected IRecipe buildRecipe() {
        FusionRecipe recipe = new FusionRecipe(
                ingredientInput,
                itemsOutput != null ? itemsOutput.toArray(new ItemStack[0]) : null,
                fluidsInput != null ? fluidsInput : Collections.emptyList(),
                fluidsOutput != null ? fluidsOutput.toArray(new FluidHolder[0]) : null,
                duration, power, special, amps
        );
        if (outputChances != null) recipe.addOutputChances(outputChances);
        if (inputChances != null) recipe.addInputChances(inputChances);
        recipe.setHidden(hidden);
        recipe.setFake(fake);
        recipe.setHuOutput(huOutput);
        recipe.addTags(new ObjectOpenHashSet<>(tags));
        recipe.setId(this.id);
        recipe.setMapId(this.recipeMap.getId());
        return recipe;
    }

    public FusionRecipeBuilder huOutput(int huOutput) {
        this.huOutput = huOutput;
        return this;
    }

    @Override
    public void clear() {
        super.clear();
        this.huOutput = 0;
    }
}
