package org.gtreimagined.gt5r.loader.crafting;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.pipe.PipeSize;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.data.GT5RBlocks;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;

public class WoodCrafting {

    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        provider.addItemRecipe(consumer, GT5RRef.ID, "tiny_wooden_fluid_pipe", "pipes",
                GT5RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.TINY), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.WOODEN_SLABS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GT5RRef.ID, "small_wooden_fluid_pipe", "pipes",
                GT5RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.SMALL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", " s ", "H  ");
        provider.addItemRecipe(consumer, GT5RRef.ID, "normal_wooden_fluid_pipe", "pipes",
                GT5RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.NORMAL), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "sss", "H  ");
        provider.addItemRecipe(consumer, GT5RRef.ID, "large_wooden_fluid_pipe", "pipes",
                GT5RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.LARGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.PLANKS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "ssS", "s s", "Hss");
        provider.addItemRecipe(consumer, GT5RRef.ID, "huge_wooden_fluid_pipe", "pipes",
                GT5RBlocks.FLUID_PIPE_WOOD.getBlockItem(PipeSize.HUGE), of('S', AntimatterDefaultTools.SAW.getTag(), 's', ItemTags.LOGS, 'H', AntimatterDefaultTools.SOFT_HAMMER.getTag()), "  S", "s s", "H  ");
    }
}
