package org.gtreimagined.gt5r.loader.crafting;

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

       provider.addItemRecipe(consumer, GT5RRef.ID, "", "gears",
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', ForgeCTags.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer, GT5RRef.ID, "gear_wood_alt", "gears",
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Wood), of('P', ItemTags.PLANKS, 'W', WRENCH.getTag(), 'R', ForgeCTags.RODS_WOODEN), "RPR", "PWP", "RPR");

        provider.addItemRecipe(consumer, GT5RRef.ID, "" , "gears",
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Stone), of('S', ForgeCTags.STONE, 'W', WRENCH.getTag(), 'C', ForgeCTags.COBBLESTONE), "SCS", "CWC", "SCS");

        provider.addItemRecipe(consumer, GT5RRef.ID,  "gear_stone_alt", "gears",
                AntimatterMaterialTypes.GEAR.get(AntimatterMaterials.Stone), of('S', ForgeCTags.STONE, 'W', WRENCH.getTag(), 'C', ForgeCTags.COBBLESTONE), "CSC", "SWS", "CSC");

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

    }
}
