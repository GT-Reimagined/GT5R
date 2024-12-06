package org.gtreimagined.gt5r.loader.machines;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialStack;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreItems;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.METAL;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt5r.data.GT5RMaterialTags.ALLOY;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.ALLOY_SMELTER;

public class AlloySmelterLoader {

    public static void init() {
        INGOT.all().forEach(t -> {
            if (t.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE)) return;
            if (!t.has(ALLOY)) return;
            if (t == BlackBronze || t == BismuthBronze) return;
            if (t == Magnalium && !GT5RConfig.HARDER_ALUMINIUM_PROCESSING.get()) return;
            List<MaterialStack> stacks = t.getProcessInto();
            ImmutableMap.Builder<Material, Integer> builder = ImmutableMap.builder();
            int cumulative = 0;
            for (MaterialStack stack : stacks) {
                builder.put(stack.m, stack.s);
                cumulative += stack.s;
            }
            cumulative = t == RedAlloy ? 1 : cumulative;
            addAlloyRecipes(builder.build(), t, cumulative);
        });
        addAlloyRecipes(ImmutableMap.of(Copper, 3, Electrum, 2), BlackBronze, 5);
        addAlloyRecipes(ImmutableMap.of(Bismuth, 1, Brass, 4), BismuthBronze, 5);
        addAlloyRecipes(ImmutableMap.of(Gold, 4, NetheriteScrap, 4), Netherite, 1);
        //pre Chemical Reactor Rubber
        ALLOY_SMELTER.RB().ii(of(DUST.get(RawRubber), 3), of(DUST.getMaterialTag(Sulfur), 1))
                .io(INGOT.get(Rubber, 1)).add("rubber_via_alloy_smelter",20, 10);
        PLATE.all().stream().filter(m -> !m.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE) && m.has(INGOT)).forEach(m ->{
            int euPerTick = m.has(RUBBERTOOLS) ? 8 : 32;
            ALLOY_SMELTER.RB().ii(INGOT.getMaterialIngredient(m, 2), RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).io(PLATE.get(m, 1)).add(m.getId() + "_plate", m.getMass() * 2, euPerTick);
            if (m.has(RUBBERTOOLS)) {
                ALLOY_SMELTER.RB().ii(DUST.getMaterialIngredient(m, 2), RecipeIngredient.of(GTCoreItems.MoldPlate, 1).setNoConsume()).io(PLATE.get(m, 1)).add(m.getId() + "_plate_from_dust", m.getMass() * 2, euPerTick);
            }
        });
        INGOT.all().stream().filter(m -> !m.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE)).forEach(m -> { //TODO other ingot recipes
            if (m.has(NUGGET)){
                ALLOY_SMELTER.RB().ii(NUGGET.getMaterialIngredient(m, 9), RecipeIngredient.of(GTCoreItems.MoldIngot, 1).setNoConsume()).io(INGOT.get(m, 1)).add(m.getId() + "_ingot_from_nugget", 200, 2);
            }
            if (m.has(RUBBERTOOLS)) {
                ALLOY_SMELTER.RB().ii(DUST.getMaterialIngredient(m, 1), RecipeIngredient.of(GTCoreItems.MoldIngot, 1).setNoConsume()).io(INGOT.get(m, 1)).add(m.getId() + "_ingot_from_dust", m.getMass(), 10);
            }
        });
        ITEM_CASING.all().forEach(m -> {
            ALLOY_SMELTER.RB().ii(INGOT.getMaterialIngredient(m, 2), of(GTCoreItems.MoldCasing, 1).setNoConsume()).io(ITEM_CASING.get(m, 3)).add(m.getId() + "_item_casing", Math.max(m.getMass() * 2 / 3, 1), 16);
        });
        GEAR.all().stream().filter(m -> !m.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE) && m.has(INGOT)).forEach(m ->{
            int euPerTick = m.has(RUBBERTOOLS) ? 8 : 32;
            ALLOY_SMELTER.RB().ii(INGOT.getMaterialIngredient(m, 8), RecipeIngredient.of(GTCoreItems.MoldGear, 1).setNoConsume()).io(GEAR.get(m, 1)).add(m.getId() + "_gear", m.getMass() * 8, euPerTick);
            if (m.has(RUBBERTOOLS)) {
                ALLOY_SMELTER.RB().ii(DUST.getMaterialIngredient(m, 8), RecipeIngredient.of(GTCoreItems.MoldGear, 1).setNoConsume()).io(GEAR.get(m, 1)).add(m.getId() + "_gear_from_dust", m.getMass() * 8, euPerTick);
            }
        });
        ALLOY_SMELTER.RB().ii(DUST.getMaterialIngredient(Glass, 1), RecipeIngredient.of(GTCoreItems.MoldBall, 1).setNoConsume()).io(GTCoreItems.GlassTube).add("glass_tube", 160, 8);
        ALLOY_SMELTER.RB().ii(DUST.getMaterialIngredient(Glass, 1), RecipeIngredient.of(GTCoreItems.MoldBottle, 1).setNoConsume()).io(Items.GLASS_BOTTLE).add("glass_bottle", 64, 4);
        ALLOY_SMELTER.RB().ii(INGOT.getMaterialIngredient(Iron, 31), RecipeIngredient.of(GTCoreItems.MoldAnvil, 1).setNoConsume()).io(Items.ANVIL).add("anvil", 512, 64);
    }

    private static void addAlloyRecipes(ImmutableMap<Material, Integer> inputs, Material output, int amount){
        if (inputs.size() > 1){
            List<Ingredient> ingredients = new ArrayList<>();
            inputs.forEach((m, i) -> {
                List<TagKey<Item>> tags = new ArrayList<>();
                if (m.has(DUST)){
                    tags.add(DUST.getMaterialTag(m));
                }
                if (m.has(INGOT)) {
                    tags.add(INGOT.getMaterialTag(m));
                }
                if (m == Copper){
                    tags.add(INGOT.getMaterialTag(AnnealedCopper));
                }
                ingredients.add(RecipeIngredient.of(i, tags.toArray(TagKey[]::new)));
            });
            ALLOY_SMELTER.RB().ii(ingredients).io(INGOT.get(output, amount)).add(output.getId() + "_ingot", 100, 12);
        }
    }
}
