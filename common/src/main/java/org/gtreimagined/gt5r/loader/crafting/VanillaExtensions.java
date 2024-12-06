package org.gtreimagined.gt5r.loader.crafting;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import org.gtreimagined.gt5r.GT5RRef;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gtcore.GTCoreConfig;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.HAMMER;
import static muramasa.antimatter.data.AntimatterDefaultTools.WRENCH;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Coal;
import static org.gtreimagined.gt5r.data.Materials.*;

public class VanillaExtensions {
    public static void loadRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider) {
        provider.addConditionalRecipe(consumer, provider.getStackRecipe("", false,
                new ItemStack(Blocks.TORCH, 2), of('D', TagUtils.getForgelikeItemTag("dusts/sulfur"), 'R', ForgeCTags.RODS_WOODEN), "D", "R"), GT5RRef.class, "sulfurTorch", GT5RRef.ID, "sulfur_torch");

        provider.addItemRecipe(consumer, GT5RRef.ID, "chainmail_helmet", "chainmail_armor",
                Items.CHAINMAIL_HELMET, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR");
        provider.addItemRecipe(consumer, GT5RRef.ID, "chainmail_chestplate", "chainmail_armor",
                Items.CHAINMAIL_CHESTPLATE, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RHR", "RRR", "RRR");
        provider.addItemRecipe(consumer, GT5RRef.ID, "chainmail_leggings", "chainmail_armor",
                Items.CHAINMAIL_LEGGINGS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "RRR", "RHR", "R R");
        provider.addItemRecipe(consumer, GT5RRef.ID, "chainmail_boots", "chainmail_armor",
                Items.CHAINMAIL_BOOTS, of('R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'H', HAMMER.getTag()), "R R", "RHR");
        provider.addItemRecipe(consumer, GT5RRef.ID, "saddle", "", Items.SADDLE,
                of('L', Items.LEATHER, 'R', AntimatterMaterialTypes.RING.getMaterialTag(Steel), 'S', AntimatterMaterialTypes.SCREW.getMaterialTag(Steel)), "LLL", "LSL", "R R");

        provider.addItemRecipe(consumer, "magnetic_rods_iron", ROD.get(IronMagnetic),
                of('R', ForgeCTags.DUSTS_REDSTONE, 'S', TagUtils.getForgelikeItemTag("rods/iron")), " R ", "RSR", " R ");

        provider.addItemRecipe(consumer, "magnetic_rods_neodymium", ROD.get(Neodymium),
                of('R', ForgeCTags.DUSTS_REDSTONE, 'S', TagUtils.getForgelikeItemTag("rods/neodymium")), " R ", "RSR", " R ");
        provider.addStackRecipe(consumer, GT5RRef.ID, "torch_from_coal", "torches", new ItemStack(Items.TORCH, 4),
                of('C', Ingredient.of(RAW_ORE.get(Coal), DUST.get(Coal), DUST_IMPURE.get(Coal), DUST_PURE.get(Coal), CRUSHED.get(Coal),CRUSHED_PURIFIED.get(Coal), CRUSHED_REFINED.get(Coal)), 'S', Items.STICK), "C", "S");
        provider.addStackRecipe(consumer, GT5RRef.ID, "torch_from_lignite", "torches", new ItemStack(Items.TORCH, 2),
                of('C', Ingredient.of(GEM.get(Lignite), RAW_ORE.get(Lignite), DUST.get(Lignite), DUST_IMPURE.get(Lignite), DUST_PURE.get(Lignite), CRUSHED.get(Lignite),CRUSHED_PURIFIED.get(Lignite), CRUSHED_REFINED.get(Lignite)), 'S', Items.STICK), "C", "S");
        provider.addStackRecipe(consumer, GT5RRef.ID, "torch_from_creosote", "torches", new ItemStack(Items.TORCH, 6),
                of('W', ItemTags.WOOL, 'C', Creosote.getLiquid().getBucket(), 'S', Items.STICK), "C", "W", "S");
        provider.shapeless(consumer, GT5RRef.ID, "green_dye_from_malachite", "dyes", Items.GREEN_DYE.getDefaultInstance(), DUST.getMaterialTag(Malachite));
        provider.shapeless(consumer, GT5RRef.ID, "blue_dye_from_sodalite_dust", "dyes", Items.BLUE_DYE.getDefaultInstance(), DUST.getMaterialTag(Sodalite));
        provider.shapeless(consumer, GT5RRef.ID, "blue_dye_from_sodalite_gem", "dyes", Items.BLUE_DYE.getDefaultInstance(), GEM.getMaterialTag(Sodalite));
        provider.shapeless(consumer, GT5RRef.ID, "cyan_dye_from_lazurite_dust", "dyes", Items.CYAN_DYE.getDefaultInstance(), DUST.getMaterialTag(Lazurite));
        provider.shapeless(consumer, GT5RRef.ID, "cyan_dye_from_lazurite_gem", "dyes", Items.CYAN_DYE.getDefaultInstance(), GEM.getMaterialTag(Lazurite));
        provider.shapeless(consumer, GT5RRef.ID, "green_dye_from_blue_and_yellow", "dyes", new ItemStack(Items.GREEN_DYE, 2), Items.YELLOW_DYE, Items.BLUE_DYE);
        provider.shapeless(consumer, GT5RRef.ID, "brown_dye_from_primaries", "dyes", new ItemStack(Items.BROWN_DYE, 3), Items.YELLOW_DYE, Items.BLUE_DYE, Items.RED_DYE);
        if (GTCoreConfig.VANILLA_OVERRIDES.get()){
            for (DyeColor dye : DyeColor.values()){
                provider.removeRecipe(new ResourceLocation(dye.getName() + "_concrete_powder"));
            }
            provider.removeRecipe(new ResourceLocation("netherite_ingot"));
        }
        provider.shapeless(consumer, GT5RRef.ID, "yellow_dye_from_bedrock_flower", "dyes", Items.YELLOW_DYE.getDefaultInstance(), GT5RBlocks.DESERT_TRUMPET);
        provider.shapeless(consumer, GT5RRef.ID, "pink_dye_from_bedrock_flower", "dyes", Items.PINK_DYE.getDefaultInstance(), GT5RBlocks.BECIUM_HOMBLEI);
        provider.shapeless(consumer, GT5RRef.ID, "yellow_dye_from_bedrock_flower_2", "dyes", Items.YELLOW_DYE.getDefaultInstance(), GT5RBlocks.PRINCES_PLUME);
        provider.shapeless(consumer, GT5RRef.ID, "purple_dye_from_bedrock_flower", "dyes", Items.PURPLE_DYE.getDefaultInstance(), GT5RBlocks.THOMPSONS_LOCOWEED);
        provider.shapeless(consumer, GT5RRef.ID, "green_dye_from_bedrock_flower", "dyes", Items.GREEN_DYE.getDefaultInstance(), GT5RBlocks.TUNGSTUS); //smelt?
        provider.shapeless(consumer, GT5RRef.ID, "yellow_dye_from_bedrock_flower_3", "dyes", Items.YELLOW_DYE.getDefaultInstance(), GT5RBlocks.ALTERED_ANDESITE_BUCKWHEAT);
        provider.shapeless(consumer, GT5RRef.ID, "yellow_dye_from_bedrock_flower_4", "dyes", Items.YELLOW_DYE.getDefaultInstance(), GT5RBlocks.CROSBY_BUCKWHEAT);
        provider.shapeless(consumer, GT5RRef.ID, "magenta_dye_from_bedrock_flower", "dyes", Items.MAGENTA_DYE.getDefaultInstance(), GT5RBlocks.ALPINE_CATCHFLY);
        provider.shapeless(consumer, GT5RRef.ID, "yellow_dye_from_bedrock_flower_5", "dyes", Items.YELLOW_DYE.getDefaultInstance(), GT5RBlocks.VIOLA_CALAMINARIA);
        provider.shapeless(consumer, GT5RRef.ID, "pink_dye_from_bedrock_flower_2", "dyes", Items.PINK_DYE.getDefaultInstance(), GT5RBlocks.THLASPI_LERESCHIANUM);
        provider.shapeless(consumer, GT5RRef.ID, "white_dye_from_bedrock_flower_", "dyes", Items.WHITE_DYE.getDefaultInstance(), GT5RBlocks.TUFTED_EVENING_PRIMROSE);
        provider.shapeless(consumer, GT5RRef.ID, "light_blue_dye_from_bedrock_flower", "dyes", Items.LIGHT_BLUE_DYE.getDefaultInstance(), GT5RBlocks.NARCISSUS_SHELDONIA);
        provider.shapeless(consumer, GT5RRef.ID, "brown_dye_from_bedrock_flower", "dyes", Items.BROWN_DYE.getDefaultInstance(), GT5RBlocks.ORECHID);

    }
}
