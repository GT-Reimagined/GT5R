package org.gtreimagined.gt5r.loader.multi;

import com.google.common.collect.ImmutableMap;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.GT5RRef;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Iron;
import static muramasa.antimatter.material.MaterialTags.DIRECT_SMELT_INTO;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.E_BLAST_FURNACE;
import static org.gtreimagined.gt5r.data.RecipeMaps.PRIMITIVE_BLAST_FURNACE;
import static org.gtreimagined.gtcore.data.GTCoreItems.SELECTOR_TAG_INGREDIENTS;

public class BlastFurnaceLoader {
    public static int mixedOreYield = GT5RRef.mixedOreYieldsTwoThirdsPureOre ? 2 : 3;

    public static void init() {
        /* PRIMITIVE */
        PRIMITIVE_BLAST_FURNACE.RB().ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron,1)).io(INGOT.get(Steel, 1), DUST_SMALL.get(DarkAsh,8)).outputChances(1.0, 0.5).add("steel_ingot",7200, 0);
        DUST.all().forEach(m -> {
            if (m.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE) && m.has(GT5RMaterialTags.BLAST_FURNACE_TEMP)){
                ItemStack ingot = DIRECT_SMELT_INTO.getMapping(m).has(INGOT_HOT) ? INGOT_HOT.get(DIRECT_SMELT_INTO.getMapping(m), 1) : INGOT.get(DIRECT_SMELT_INTO.getMapping(m), 1);
                int heat = GT5RMaterialTags.BLAST_FURNACE_TEMP.getInt(m);
                E_BLAST_FURNACE.RB().temperature(heat).ii(DUST.getMaterialIngredient(m, 1), SELECTOR_TAG_INGREDIENTS.get(1)).io(ingot).add(DIRECT_SMELT_INTO.getMapping(m).getId() + "_ingot_from_" + m.getId() + "_dust", Math.max(m.getMass() / 40L, 1L) * heat, 120);
            }
        });

        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(BlackSteel, 1)).io(INGOT.get(BlackSteel)).add("black_steel", 10800);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(BlueSteel, 1)).io(INGOT.get(BlueSteel)).add("blue_steel", 14400);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(RedSteel, 1)).io(INGOT.get(RedSteel)).add("red_steel", 14400);

        addCalciteRecipe(Hematite, 4, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(YellowLimonite, 6, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(BrownLimonite, 6, new ItemStack(Items.IRON_INGOT, 1));
        addCalciteRecipe(Magnetite, 11, new ItemStack(Items.IRON_INGOT, 3));
        addCalciteRecipe(Chalcopyrite, 6, new ItemStack(Items.COPPER_INGOT), new ItemStack(Items.IRON_INGOT));
        addCalciteRecipe(Galena, 4, INGOT.get(Lead, 1), INGOT.get(Silver, 1));
        addCalciteRecipe(Garnierite, 1, INGOT.get(Nickel, 1));
        addCalciteRecipe(Cassiterite, 1, INGOT.get(Tin, 1));
        addCalciteRecipe(Cobaltite, 5, INGOT.get(Cobalt, 1));
        addCalciteRecipe(Pyrite, 4, new ItemStack(Items.IRON_INGOT));
        addCalciteRecipe(Stibnite, 4, INGOT.get(Antimony, 1));
        addCalciteRecipe(Tetrahedrite, 12, INGOT.get(Copper, 3), INGOT.get(Antimony, 1), INGOT.get(Iron, 1));
        addCalciteRecipe(Malachite, 15, INGOT.get(Copper, 2));
        /* Annealed Copper*/
        E_BLAST_FURNACE.RB().temperature(1200).ii(DUST.getMaterialIngredient(Copper, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(AnnealedCopper))
                .add("annealed_copper_ingot", 25 * 20, 120);
        /* Steel */
        E_BLAST_FURNACE.RB().temperature(1000).ii(INGOT.getMaterialIngredient(AntimatterMaterials.Iron, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(Steel), DUST_SMALL.get(DarkAsh))
                .add("steel_ingot", 500, 120);
        E_BLAST_FURNACE.RB().temperature(1000).ii(INGOT.getMaterialIngredient(WroughtIron, 1))
                .fi(Oxygen.getGas(1000))
                .io(INGOT.get(Steel), DUST_SMALL.get(DarkAsh))
                .add("steel_ingot_2", 100, 120);
        if (!GT5RConfig.HARDER_ALUMINIUM_PROCESSING.get()){
            /* Aluminium*/
            E_BLAST_FURNACE.RB().temperature(1200).ii(DUST.getMaterialIngredient(Ruby, 1))
                    .io(NUGGET.get(Aluminium, 3), DUST_TINY.get(DarkAsh, 1))
                    .add("aluminium_ingot_from_ruby", 400, 100);
            E_BLAST_FURNACE.RB().temperature(1200).ii(DUST.getMaterialIngredient(Sapphire, 1))
                    .io(NUGGET.get(Aluminium, 3))
                    .add("aluminium_ingot_from_blue_sapphire", 400, 100);
            E_BLAST_FURNACE.RB().temperature(1200).ii(DUST.getMaterialIngredient(GreenSapphire, 1))
                    .io(NUGGET.get(Aluminium, 3), DUST_TINY.get(DarkAsh, 1))
                    .add("aluminium_ingot_from_green_sapphire", 400, 100);
            int heat = GT5RMaterialTags.BLAST_FURNACE_TEMP.getInt(Aluminium);
            E_BLAST_FURNACE.RB().temperature(1700).ii(DUST.getMaterialIngredient(Aluminium, 1), SELECTOR_TAG_INGREDIENTS.get(1)).io(INGOT.get(Aluminium)).add( "aluminium_ingot_from_aluminium_dust", Math.max(Aluminium.getMass() / 40L, 1L) * heat, 120);
            E_BLAST_FURNACE.RB().temperature(1700).ii(DUST.getMaterialIngredient(Alumina, 4), of(1, DUST.getMaterialTag(Calcite), DUST.getMaterialTag(Limestone), DUST.getMaterialTag(Marble))).io(INGOT.get(Aluminium)).add("alumina", 4 * 100, 120);
        }

        /* Stainless Steel*/
        addBlastAlloyRecipes(StainlessSteel, 9, 1000, 120, ImmutableMap.of(Iron, 6, Nickel, 1, Manganese, 1, Chromium, 1));
        /* Tungsten Steel/Carbide*/
        addBlastAlloyRecipes(TungstenSteel, 2, 3000, 480, ImmutableMap.of(Steel, 1, Tungsten, 1));
        addBlastAlloyRecipes(TungstenCarbide, 1, 246 * 20, 480, ImmutableMap.of(Carbon, 1, Tungsten, 1));
        /* Niobium Titanium*/
        addBlastAlloyRecipes(NiobiumTitanium, 2, 225 * 20, 480, ImmutableMap.of(Niobium, 1, Titanium, 1));
        /* Vanadium Gallium*/
        addBlastAlloyRecipes(VanadiumGallium, 4, 225 * 20, 480, ImmutableMap.of(Vanadium, 3, Gallium, 1));
        /* Vanadium Steel*/
        addBlastAlloyRecipes(VanadiumSteel, 9, 225 * 20, 120, ImmutableMap.of(Vanadium, 1, Chromium, 1, Steel, 7));
        /* Ultimet */
        addBlastAlloyRecipes(Ultimet, 9, 2700, 120, ImmutableMap.of(Cobalt, 5, Chromium, 2, Nickel, 1, Molybdenum, 1));
        /* Kanthal*/
        addBlastAlloyRecipes(Kanthal, 3, 1800, 120, ImmutableMap.of(Iron, 1, Aluminium, 1, Chromium, 1));
        /* Nichrome*/
        E_BLAST_FURNACE.RB().temperature(2700).ii(of(4, DUST.getMaterialTag(Nickel), INGOT.getMaterialTag(Nickel)), of(1, DUST.getMaterialTag(Chromium), INGOT.getMaterialTag(Chromium)), SELECTOR_TAG_INGREDIENTS.get(2))
                .io(INGOT_HOT.get(Nichrome, 5))
                .add("nichrome_ingot", 135 * 20, 480);
        /* Osmiridium*/
        E_BLAST_FURNACE.RB().temperature(2900).ii(of(3, DUST.getMaterialTag(Iridium), INGOT.getMaterialTag(Iridium)), of(1, DUST.getMaterialTag(Osmium), INGOT.getMaterialTag(Osmium)))
                .fi(Helium.getGas(1000))
                .io(INGOT_HOT.get(Osmiridium, 4))
                .add("osmiridium_ingot", 25 * 20, 1920);
        /* Naquadah Alloy*/
        E_BLAST_FURNACE.RB().temperature(7200).ii(of(1, DUST.getMaterialTag(Naquadah), INGOT.getMaterialTag(Naquadah)), of(1, INGOT.getMaterialTag(Osmiridium), DUST.getMaterialTag(Osmiridium), INGOT_HOT.getMaterialTag(Osmiridium)))
                .fi(Argon.getGas(1000))
                .io(INGOT_HOT.get(NaquadahAlloy, 2))
                .add("naquadah_alloy_ingot",25 * 20, 30720);
        /* TFC stuff and hss */
        addBlastAlloyRecipes(BlackSteel, 5, 1200, 120, ImmutableMap.of(Nickel, 1, BlackBronze, 1, Steel, 3));
        addBlastAlloyRecipes(BlueSteel, 8, 1400, 120, ImmutableMap.of(SterlingSilver, 1, BismuthBronze, 1, Steel, 2, BlackSteel, 4));
        addBlastAlloyRecipes(RedSteel, 8, 1300, 120, ImmutableMap.of(RoseGold, 1, Brass, 1, Steel, 2, BlackSteel, 4));
        addBlastAlloyRecipes(HSSG, 9, 9000, 120, ImmutableMap.of(TungstenSteel, 5, Chromium, 1, Molybdenum, 2, Vanadium, 1));
        addBlastAlloyRecipes(HSSE, 9, 10800, 120, ImmutableMap.of(HSSG, 6, Cobalt, 1, Manganese, 1, Silicon, 1));
        addBlastAlloyRecipes(HSSS, 9, 810 * 20, 120, ImmutableMap.of(HSSG, 6, Iridium, 2, Osmium, 1));
    }

    private static void addBlastAlloyRecipes(Material output, int amount, int duration, int power, ImmutableMap<Material, Integer> inputs){
        RecipeBuilder b = E_BLAST_FURNACE.RB().temperature(GT5RMaterialTags.BLAST_FURNACE_TEMP.getInt(output));
        b.io((output.has(INGOT_HOT) ?  INGOT_HOT : INGOT).get(output, amount));
        inputs.forEach((m, i) -> {
            if (m.has(INGOT_HOT)){
                b.ii(of(i, DUST.getMaterialTag(m), INGOT.getMaterialTag(m), INGOT_HOT.getMaterialTag(m)));
            } else if (m.has(INGOT)){
                b.ii(of(i, DUST.getMaterialTag(m), INGOT.getMaterialTag(m)));
            } else {
                b.ii(DUST.getMaterialIngredient(m, i));
            }
        });
        b.add(output.getId() + "_ingot", duration,power);
    }

    private static void addCalciteRecipe(Material ore, int input, ItemStack... outputs){
        PRIMITIVE_BLAST_FURNACE.RB().ii(RAW_ORE.getMaterialIngredient(ore, input), of(1, DUST.getMaterialTag(Calcite), DUST.getMaterialTag(Limestone), DUST.getMaterialTag(Marble))).io(outputs).add(ore.getId(), input * 1000L);
        PRIMITIVE_BLAST_FURNACE.RB().ii(CRUSHED.getMaterialIngredient(ore, input), of(1, DUST.getMaterialTag(Calcite), DUST.getMaterialTag(Limestone), DUST.getMaterialTag(Marble))).io(outputs).add("crushed_" + ore.getId(), input * 1000L);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST_IMPURE.getMaterialIngredient(ore, input), of(1, DUST.getMaterialTag(Calcite), DUST.getMaterialTag(Limestone), DUST.getMaterialTag(Marble))).io(outputs).add("impure_" + ore.getId(), input * 1000L);
        PRIMITIVE_BLAST_FURNACE.RB().ii(DUST.getMaterialIngredient(ore, input), of(1, DUST.getMaterialTag(Calcite), DUST.getMaterialTag(Limestone), DUST.getMaterialTag(Marble))).io(outputs).add("dust_" + ore.getId(), input * 1000L);
    }
}
