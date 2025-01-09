package org.gtreimagined.gt5r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.ItemPipe;
import muramasa.antimatter.util.Utils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.data.GT5RMaterialTypes;
import org.gtreimagined.gtcore.data.GTCoreItems;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.QUARTZ_LIKE_BLOCKS;
import static org.gtreimagined.gt5r.data.GT5RMaterialTypes.CHAMBER;
import static org.gtreimagined.gt5r.data.Materials.*;

public class MaterialCrafting {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        addShapelessDustRecipe(output, provider, "bronze_dust", DUST.get(Bronze, 4), DUST.get(Copper), DUST.get(Copper), DUST.get(Copper), DUST.get(Tin));
        addShapelessDustRecipe(output, provider, "brass_dust", DUST.get(Brass, 4), DUST.get(Copper), DUST.get(Copper), DUST.get(Copper), DUST.get(Zinc));
        addShapelessDustRecipe(output, provider, "black_bronze_dust", DUST.get(BlackBronze, 5), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Gold));
        addShapelessDustRecipe(output, provider, "black_steel_dust", DUST.get(BlackSteel, 5), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(BlackBronze), DUST.getMaterialTag(Nickel));
        addShapelessDustRecipe(output, provider, "rose_gold", DUST.get(RoseGold, 5), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Gold), DUST.getMaterialTag(Copper));
        addShapelessDustRecipe(output, provider, "sterling_silver", DUST.get(SterlingSilver, 5), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Silver), DUST.getMaterialTag(Copper));
        addShapelessDustRecipe(output, provider, "bismuth_bronze", DUST.get(BismuthBronze, 5), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Copper), DUST.getMaterialTag(Bismuth), DUST.getMaterialTag(Zinc));
        addShapelessDustRecipe(output, provider, "red_steel", DUST.get(RedSteel, 8), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel),
                DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(Brass), DUST.getMaterialTag(RoseGold));
        addShapelessDustRecipe(output, provider, "blue_steel", DUST.get(BlueSteel, 8), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel), DUST.getMaterialTag(BlackSteel),
                DUST.getMaterialTag(Steel), DUST.getMaterialTag(Steel), DUST.getMaterialTag(BismuthBronze), DUST.getMaterialTag(SterlingSilver));
        addShapelessDustRecipe(output, provider, "cobalt_brass", DUST.get(CobaltBrass, 9), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass),
                DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Brass), DUST.getMaterialTag(Aluminium), DUST.getMaterialTag(Cobalt));
        addShapelessDustRecipe(output, provider, "gallium_arsenide", DUST.get(GalliumArsenide, 2), DUST.getMaterialTag(Gallium), DUST.getMaterialTag(Arsenic));
        addShapelessDustRecipe(output, provider, "indium_gallium_phosphide", DUST.get(IndiumGalliumPhosphide, 3), DUST.getMaterialTag(Indium), DUST.getMaterialTag(Gallium), DUST.getMaterialTag(Phosphor));
        provider.shapeless(output, GT5RRef.ID, "", "dusts", AntimatterMaterialTypes.DUST_SMALL.get(Clay, 2), MORTAR.getTag(), Items.CLAY_BALL);
        loadAutoRecipes(output, provider);
        loadMixedMetal(output, provider);
    }

    public static void loadMixedMetal(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        mixedMetalRecipe(consumer, provider, Iron, Bronze, Tin, 1);
        mixedMetalRecipe(consumer, provider, Iron, Bronze, Zinc, 1);
        mixedMetalRecipe(consumer, provider, Iron, Bronze, Aluminium, 1);
        mixedMetalRecipe(consumer, provider, Iron, Brass, Tin, 1);
        mixedMetalRecipe(consumer, provider, Iron, Brass, Zinc, 1);
        mixedMetalRecipe(consumer, provider, Iron, Brass, Aluminium, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Bronze, Tin, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Bronze, Zinc, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Bronze, Aluminium, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Brass, Tin, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Brass, Zinc, 1);
        mixedMetalRecipe(consumer, provider, Nickel, Brass, Aluminium, 1);
        mixedMetalRecipe(consumer, provider, Invar, Bronze, Tin, 2);
        mixedMetalRecipe(consumer, provider, Invar, Bronze, Zinc, 2);
        mixedMetalRecipe(consumer, provider, Invar, Bronze, Aluminium, 3);
        mixedMetalRecipe(consumer, provider, Invar, Brass, Tin, 2);
        mixedMetalRecipe(consumer, provider, Invar, Brass, Zinc, 2);
        mixedMetalRecipe(consumer, provider, Invar, Brass, Aluminium, 3);
        mixedMetalRecipe(consumer, provider, Steel, Bronze, Tin, 2);
        mixedMetalRecipe(consumer, provider, Steel, Bronze, Zinc, 2);
        mixedMetalRecipe(consumer, provider, Steel, Bronze, Aluminium, 3);
        mixedMetalRecipe(consumer, provider, Steel, Brass, Tin, 2);
        mixedMetalRecipe(consumer, provider, Steel, Brass, Zinc, 2);
        mixedMetalRecipe(consumer, provider, Steel, Brass, Aluminium, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Bronze, Tin, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Bronze, Zinc, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Bronze, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Brass, Tin, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Brass, Zinc, 3);
        mixedMetalRecipe(consumer, provider, StainlessSteel, Brass, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, Titanium, Bronze, Tin, 3);
        mixedMetalRecipe(consumer, provider, Titanium, Bronze, Zinc, 3);
        mixedMetalRecipe(consumer, provider, Titanium, Bronze, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, Titanium, Brass, Tin, 3);
        mixedMetalRecipe(consumer, provider, Titanium, Brass, Zinc, 3);
        mixedMetalRecipe(consumer, provider, Titanium, Brass, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, Tungsten, Bronze, Tin, 3);
        mixedMetalRecipe(consumer, provider, Tungsten, Bronze, Zinc, 3);
        mixedMetalRecipe(consumer, provider, Tungsten, Bronze, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, Tungsten, Brass, Tin, 3);
        mixedMetalRecipe(consumer, provider, Tungsten, Brass, Zinc, 3);
        mixedMetalRecipe(consumer, provider, Tungsten, Brass, Aluminium, 4);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Bronze, Tin, 5);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Bronze, Zinc, 5);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Bronze, Aluminium, 6);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Brass, Tin, 5);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Brass, Zinc, 5);
        mixedMetalRecipe(consumer, provider, TungstenSteel, Brass, Aluminium, 6);
    }

    public static void mixedMetalRecipe(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider, Material top, Material middle, Material bottom, int amount){
        provider.addStackRecipe(consumer, GT5RRef.ID, "mixed_metal_from_" + top.getId() + "_" + middle.getId() + "_" + bottom.getId(), "mixed_metal", Utils.ca(amount, GTCoreItems.MixedMetalIngot.getMixedMetalIngot(top, middle, bottom)),
                of('T', PLATE.getMaterialTag(top), 'M', PLATE.getMaterialTag(middle), 'B', PLATE.getMaterialTag(bottom)), "T", "M", "B");
    }

    public static void loadAutoRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        DUST.all().forEach(m -> {
            provider.addStackRecipe(consumer, GT5RRef.ID, m.getId() + "_small_dust", "antimatter_materials", DUST_SMALL.get(m, 4),
                    of('D', DUST.getMaterialTag(m)), " D");
            /*provider.addStackRecipe(consumer, GT5RRef.ID, m.getId() + "_tiny_dust", "antimatter_materials", "has_wrench", in, DUST_TINY.get(m, 9),
                    of('D', DUST.getMaterialTag(m)), "D ");*/
        });
        GT5RMaterialTypes.TURBINE_BLADE.all().forEach(m -> {
            provider.addStackRecipe(consumer, GT5RRef.ID, "", "antimatter_materials",
                    GT5RMaterialTypes.TURBINE_BLADE.get(m, 1), ImmutableMap.<Character, Object>builder()
                            .put('S', SCREWDRIVER.getTag())
                            .put('F', FILE.getTag())
                            .put('P', PLATE.getMaterialTag(m))
                            .put('s', SCREW.getMaterialTag(m)).build(), "FPS", "sPs", " P ");
        });
        CHAMBER.all().forEach(m -> {
            if (!m.has(GEM) && !m.has(PLATE)) return;
            TagKey<Item> input = m == Quartz ? ForgeCTags.GEMS_QUARTZ_ALL : m.has(GEM) ? GEM.getMaterialTag(m) :  PLATE.getMaterialTag(m);
            provider.addItemRecipe(consumer, "chambers", CHAMBER.get(m),
                    of('I', input, 'H', HAMMER.getTag() ,'W', WRENCH.getTag()), "IHI", "IWI", "III");
        });
        AntimatterAPI.all(ItemPipe.class, i -> {
            if (i.getSizes().contains(PipeSize.NORMAL)){
                provider.addStackRecipe(consumer, GT5RRef.ID, "", "antimatter_pipes", new ItemStack(i.getRestrictedBlock(PipeSize.NORMAL), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.NORMAL)), " H ", "RPR", " R ");
            }
            if (i.getSizes().contains(PipeSize.LARGE)){
                provider.addStackRecipe(consumer, GT5RRef.ID, "", "antimatter_pipes", new ItemStack(i.getRestrictedBlock(PipeSize.LARGE), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.LARGE)), "HR ", "RPR", " R ");
            }
            if (i.getSizes().contains(PipeSize.HUGE)) {
                provider.addStackRecipe(consumer, GT5RRef.ID, "", "antimatter_pipes", new ItemStack(i.getRestrictedBlock(PipeSize.HUGE), 1), of('H', HAMMER.getTag(), 'R', RING.getMaterialTag(Steel), 'P', i.getBlock(PipeSize.HUGE)), " H ", "RPR", "RRR");
            }
        });
        //todo move to gt core
        BLOCK.all().forEach(m -> {
            if (m.has(INGOT) || m.has(GEM)){
                MaterialTypeItem<?> input = m.has(GEM) ? GEM : INGOT;
                String typeID = m.has(GEM) ? "gem" : "ingot";
                int output = m.has(QUARTZ_LIKE_BLOCKS) ? 4 : 9;
                String[] strings = m.has(QUARTZ_LIKE_BLOCKS) ? new String[]{"II", "II"} : new String[]{"III", "III", "III"};
                provider.addItemRecipe(consumer, GT5RRef.ID, m.getId() + "_block", "blocks", BLOCK.get().get(m).asItem(), of('I', input.getMaterialTag(m)), strings);
                provider.shapeless(consumer, GT5RRef.ID, m.getId() + "_" + typeID, "blocks", input.get(m, output), BLOCK.getMaterialTag(m));
            }
        });
        RAW_ORE_BLOCK.all().forEach(m -> {
            if (m.has(RAW_ORE)){
                provider.addItemRecipe(consumer, "blocks", RAW_ORE_BLOCK.get().get(m).asItem(), of('I', RAW_ORE.getMaterialTag(m)), "III", "III", "III");
                provider.shapeless(consumer, "", "blocks", RAW_ORE.get(m, 9), RAW_ORE_BLOCK.getMaterialTag(m));
            }
        });
    }

    private static void addShapelessDustRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, String recipeName, ItemStack outputItem, Object... inputs) {
        provider.shapeless(output, GT5RRef.ID, recipeName, "misc", outputItem, inputs);
    }
}
