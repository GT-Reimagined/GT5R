package org.gtreimagined.gt5r.loader.machines;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.data.GTCoreMaterials;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.RUBBERTOOLS;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.ALLOY_SMELTER;

public class AlloySmelterLoader {

    public static void init() {
        addAlloyRecipes(ImmutableMap.of(Lead, 1, Redstone, 4), GTCoreMaterials.LeadedRedstone, 1);
        addAlloyRecipes(ImmutableMap.of(Tin, 1, Copper, 3), Bronze);
        addAlloyRecipes(ImmutableMap.of(Arsenic, 1, Gallium, 1), GalliumArsenide);
        addAlloyRecipes(ImmutableMap.of(Copper, 1, Silver, 4), SterlingSilver);
        addAlloyRecipes(ImmutableMap.of(Tin, 1, Iron, 1), TinAlloy);
        addAlloyRecipes(ImmutableMap.of(Silver, 1, Gold, 1), Electrum);
        addAlloyRecipes(ImmutableMap.of(Tin, 9, Antimony, 1), SolderingAlloy);
        addAlloyRecipes(ImmutableMap.of(Cadmium, 1, Indium, 1, Silver, 1), CdInAGAlloy);
        addAlloyRecipes(ImmutableMap.of(Iron, 2, Nickel, 1), Invar);
        addAlloyRecipes(ImmutableMap.of(Copper, 1, Redstone, 4), RedAlloy, 1);
        addAlloyRecipes(ImmutableMap.of(Indium, 1, Gallium, 1, Phosphor, 1), IndiumGalliumPhosphide);
        addAlloyRecipes(ImmutableMap.of(Lead, 4, Antimony, 1), BatteryAlloy);
        addAlloyRecipes(ImmutableMap.of(Zinc, 1, Copper, 3), Brass);
        addAlloyRecipes(ImmutableMap.of(Copper, 1, Gold, 4), RoseGold);
        addAlloyRecipes(ImmutableMap.of(Copper, 1, Nickel, 1), Cupronickel);
        addAlloyRecipes(ImmutableMap.of(Copper, 3, Electrum, 2), BlackBronze);
        addAlloyRecipes(ImmutableMap.of(Bismuth, 1, Brass, 4), BismuthBronze);
        addAlloyRecipes(ImmutableMap.of(Gold, 4, NetheriteScrap, 4), Netherite, 1);
        if (GT5RConfig.HARDER_ALUMINIUM_PROCESSING.get()){
            addAlloyRecipes(ImmutableMap.of(Magnesium, 1, Aluminium, 2), Magnalium);
            addAlloyRecipes(ImmutableMap.of(Brass, 7, Aluminium, 1, Cobalt, 1), CobaltBrass);
        }
        addAlloyRecipes(ImmutableMap.of(Copper, 1, Silver, 2, RedAlloy, 5), GTCoreMaterials.Signalum, 8);
        addAlloyRecipes(ImmutableMap.of(Copper, 1, SterlingSilver, 5, RedAlloy, 10), GTCoreMaterials.Signalum, 16, "signalum_ingot_extra");
        addAlloyRecipes(ImmutableMap.of(Tin, 3, Silver, 1, Glowstone, 4), GTCoreMaterials.Lumium, 4);
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
        ALLOY_SMELTER.RB().ii(DUST.getMaterialIngredient(Glass, 1), RecipeIngredient.of(GTCoreItems.MoldBall, 1).setNoConsume()).io(GT5RItems.GlassTube).add("glass_tube", 160, 8);
        ALLOY_SMELTER.RB().ii(DUST.getMaterialIngredient(Glass, 1), RecipeIngredient.of(GTCoreItems.MoldBottle, 1).setNoConsume()).io(Items.GLASS_BOTTLE).add("glass_bottle", 64, 4);
        ALLOY_SMELTER.RB().ii(INGOT.getMaterialIngredient(Iron, 31), RecipeIngredient.of(GTCoreItems.MoldAnvil, 1).setNoConsume()).io(Items.ANVIL).add("anvil", 512, 64);
    }

    public static void addAlloyRecipes(ImmutableMap<Material, Integer> inputs, Material output){
        addAlloyRecipes(inputs, output, inputs.values().stream().mapToInt(i -> i).sum(), output.getId() + "_ingot");
    }

    public static void addAlloyRecipes(ImmutableMap<Material, Integer> inputs, Material output, int amount){
        addAlloyRecipes(inputs, output, amount, output.getId() + "_ingot");
    }

    public static void addAlloyRecipes(ImmutableMap<Material, Integer> inputs, Material output, int amount, String id){
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
            ALLOY_SMELTER.RB().ii(ingredients).io(INGOT.get(output, amount)).add(id, 100, 12);
        }
    }
}
