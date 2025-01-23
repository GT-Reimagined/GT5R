package org.gtreimagined.gt5r.machine.recipe;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import lombok.Getter;
import lombok.Setter;
import muramasa.antimatter.recipe.Recipe;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.gtreimagined.gt5r.data.GT5RRecipeTypes;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Setter
@Getter
public class FusionRecipe extends Recipe {
    int huOutput;
    public FusionRecipe(@NotNull List<Ingredient> stacksInput, ItemStack[] stacksOutput, @NotNull List<FluidIngredient> fluidsInput, FluidHolder[] fluidsOutput, int duration, long power, int special, int amps) {
        super(stacksInput, stacksOutput, fluidsInput, fluidsOutput, duration, power, special, amps);
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return GT5RRecipeTypes.FUSION_RECIPE;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return FusionRecipeSerializer.INSTANCE;
    }
}
