package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.util.TagUtils;
import muramasa.gti.GregTech;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Materials;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;
import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.util.TagUtils.nc;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Machines.*;
import static muramasa.gti.data.Materials.*;

public class Miscellaneous {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addStackRecipe(output, "gti", "firebrick_casing", "casings", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(CASING_FIRE_BRICK, 3),
                ImmutableMap.of('S', Items.SMOOTH_STONE, 'F', Firebrick, 'P', PLATE.get(Iron))
                , "FSF", "SPS", "FSF");

        provider.shapeless(output, "fireclay", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                new ItemStack(DUST.get(Materials.Fireclay),2),DUST.get(Clay), DUST.get(Brick));

        provider.shapeless(output, "clay_dust", "misc", "has_mortar", provider.hasSafeItem(MORTAR.getTag()),
                new ItemStack(DUST.get(Clay)),MORTAR.getTag(),Items.CLAY_BALL);

        provider.shapeless(output, "brick_dust", "misc", "has_mortar", provider.hasSafeItem(MORTAR.getTag()),
                new ItemStack(DUST.get(Brick)),MORTAR.getTag(),Items.BRICK);

        provider.shapeless(output, "red_alloy_dust", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                new ItemStack(DUST.get(RedAlloy)),DUST.get(Redstone),DUST.get(Copper));

        provider.addItemRecipe(output, "misc", "has_hammer", provider.hasSafeItem(HAMMER.getTag()), PLATE.get(Materials.Wood),
                of('S', Items.OAK_SLAB, 'H', HAMMER.getTag())
                , " H ", " S ", " S ");

        provider.shapeless(output, "coal_dust", "misc", "has_mortar", provider.hasSafeItem(MORTAR.getTag()),
                new ItemStack(DUST.get(Coal)),MORTAR.getTag(),Items.COAL);

        provider.shapeless(output, "tin_cable", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()),
                new ItemStack(CABLE_TIN.getBlock(PipeSize.VTINY)),WIRE_TIN.getBlock(PipeSize.VTINY), Items.WHITE_CARPET);

        provider.addStackRecipe(output, "gti", "sticky", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(StickyResin, 8),
                ImmutableMap.of('R', DUST.get(RawRubber), 'P', Plantball)
                , "PPP", "PRP", "PPP");

        provider.addStackRecipe(output, "gti", "sticky_2", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(StickyResin, 12),
                ImmutableMap.of('R', DUST.get(Rubber), 'P', Plantball)
                , "PPP", "PRP", "PPP");

        provider.addStackRecipe(output, "gti", "plantballz", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(Plantball, 1),
                ImmutableMap.of('S', Tags.Items.SEEDS)
                , "SSS", "S S", "SSS");

        provider.addStackRecipe(output, "gti", "plantballz_2", "misc", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(Plantball, 2),
                ImmutableMap.of('C', Tags.Items.CROPS)
                , "CCC", "C C", "CCC");

        INGOT.all().forEach(t -> {
            if (!t.has(DUST)) return;
            provider.shapeless(output, "dusts", "dusts", "has_mortar", provider.hasSafeItem(MORTAR.getTag()),
                    new ItemStack(DUST.get(t)),INGOT.get(t), MORTAR.getTag());
        });
    }
}