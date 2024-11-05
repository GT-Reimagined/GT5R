package org.gtreimagined.gt5r.loader.crafting;

import org.gtreimagined.gt5r.GT5RRef;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static org.gtreimagined.gtcore.data.GTCoreItems.Plantball;

public class Miscellaneous {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addItemRecipe(output, GT5RRef.ID, "plantball", "misc", Plantball, of(
                'C', ForgeCTags.CROPS
        ), "CCC", "C C", "CCC");

        provider.addItemRecipe(output, GT5RRef.ID, "plantball2", "misc", Plantball, of(
                'S', ForgeCTags.SEEDS
        ), "SSS", "S S", "SSS");
        provider.addStackRecipe(output, GT5RRef.ID, "plantball3", "misc", new ItemStack(Plantball, 2), of(
                'S', TagUtils.getItemTag(new ResourceLocation("saplings"))
        ), "SSS", "S S", "SSS");
    }
}