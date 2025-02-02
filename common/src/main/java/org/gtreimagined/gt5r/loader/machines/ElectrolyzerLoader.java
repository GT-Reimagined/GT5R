package org.gtreimagined.gt5r.loader.machines;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.data.GT5RTags;
import org.gtreimagined.gt5r.data.Materials;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static org.gtreimagined.gt5r.data.GT5RMaterialTags.*;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.ELECTROLYZER;
import static org.gtreimagined.gtcore.data.GTCoreItems.SELECTOR_TAG_INGREDIENTS;

public class ElectrolyzerLoader {
    public static void init() {
        List<Material> elecMaterials = new ArrayList<>(ELEC.all().stream().toList());
        if (!GT5RConfig.HARDER_ALUMINIUM_PROCESSING.get()){
            elecMaterials.add(Alumina);
        }
        elecMaterials.forEach(t -> {
            if (!t.has(DUST) && !t.has(LIQUID) && !t.has(GAS)) return;
            FluidHolder[] fluids = t.getProcessInto().stream().filter(mat -> ((mat.m.has(GAS) || mat.m.has(AntimatterMaterialTypes.LIQUID)) && !mat.m.has(AntimatterMaterialTypes.DUST))).map(mat -> mat.m.has(GAS) ? mat.m.getGas(mat.s*1000) : mat.m.getLiquid(mat.s*1000)).toArray(FluidHolder[]::new);
            for (FluidHolder fluid : fluids) {
                if (fluid.isEmpty())
                    return;
            }
            if (fluids.length > 6) return;
            int euPerTick = t.has(ELEC30) ? 30 : t.has(ELEC60) || t == Alumina ? 60 : t.has(ELEC90) ? 90 : 120;
            ItemStack[] items = t.getProcessInto().stream().filter(mat -> mat.m.has(AntimatterMaterialTypes.DUST)).map(mat -> AntimatterMaterialTypes.DUST.get(mat.m, mat.s)).toArray(ItemStack[]::new);
            int inputAmount = MaterialTags.PROCESS_INTO.get(t).getRight() > 0 ? MaterialTags.PROCESS_INTO.get(t).getRight() : t.getProcessInto().stream().mapToInt(mat -> mat.s).sum();
            RecipeBuilder b = ELECTROLYZER.RB();
            String prefix = "dust";
            if (t.has(DUST)){
                b.ii(DUST.getMaterialIngredient(t, inputAmount));
            } else {
                prefix = "fluid";
                b.fi(t.getFluidIngredient(inputAmount * 1000));
            }
            if (t.has(ELEC_CIRCUIT)){
                b.ii(SELECTOR_TAG_INGREDIENTS.get(1));
            }
            long duration = t.has(ELEC_TICKS) ? ELEC_TICKS.getInt(t) : t.getMass() * 20;
            b.io(items).fo(fluids).add(prefix + "_" + t.getId(),duration, euPerTick);
        });
        ELECTROLYZER.RB().ii(DUST.getMaterialIngredient(SodiumBisulfate, 2), WIRE_FINE.getMaterialIngredient(Platinum, 1).setNoConsume()).io(DUST.get(SodiumPersulfate)).fo(Hydrogen.getGas(1000)).add("sodium_persulfate_creation", 600, 30);
        ELECTROLYZER.RB().ii(RecipeIngredient.of(ItemTags.SAND, 8)).io(DUST.get(Materials.SiliconDioxide)).add("sand_to_silicon_dioxide", 500, 25);
        ELECTROLYZER.RB().ii(RecipeIngredient.of(GT5RTags.DUST_SANDS, 32)).io(DUST.get(Materials.SiliconDioxide)).add("sand_dusts_to_silicon_dioxide", 500, 25);
        ELECTROLYZER.RB().ii(RecipeIngredient.of(Items.BONE_MEAL, 3)).io(DUST.get(Materials.Calcium)).add("bone_meal", 98, 26);
        ELECTROLYZER.RB().ii(DUST.getMaterialIngredient(Bentonite, 33)).io(DUST_SMALL.get(Sodium, 2), DUST.get(Magnesium, 3), DUST.get(Silicon, 6))
                .fo(Hydrogen.getGas(3000), Water.getLiquid(2500), Oxygen.getGas(18000)).add("dust_bentonite", 240, 120);
        if (GT5RConfig.HARDER_ALUMINIUM_PROCESSING.get()) {
            ELECTROLYZER.RB().ii(DUST.getMaterialIngredient(Carbon, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_carbon", 2040, 16);
            ELECTROLYZER.RB().ii(DUST.getMaterialIngredient(Charcoal, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_charcoal", 2040, 16);
            ELECTROLYZER.RB().ii(DUST.getMaterialIngredient(Coal, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_coal", 2040, 16);
            ELECTROLYZER.RB().ii(DUST.getMaterialIngredient(CoalCoke, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_coke", 2040, 16);
            ELECTROLYZER.RB().ii(DUST.getMaterialIngredient(Graphite, 3), DUST.getMaterialIngredient(Alumina, 10))
                    .fi(AluminiumFluoride.getLiquid(L / 36), Cryolite.getLiquid(L / 72)).io(DUST.get(Aluminium, 4))
                    .fo(CarbonDioxide.getGas(9000), Fluorine.getGas(29)).add("alumina_graphite", 2040, 16);
            addVitriolRecipe(BlueVitriol, Copper);
            addVitriolRecipe(GreenVitriol, Iron);
            addVitriolRecipe(RedVitriol, Cobalt);
            addVitriolRecipe(PinkVitriol, Magnesium);
            addVitriolRecipe(CyanVitriol, Nickel);
            addVitriolRecipe(WhiteVitriol, Zinc);
            addVitriolRecipe(GrayVitriol, Manganese);
            ELECTROLYZER.RB().fi(Water.getLiquid(900), VitriolOfClay.getLiquid(1700)).ii(SELECTOR_TAG_INGREDIENTS.get(1)).io(DUST_SMALL.get(Alumina, 2)).fo(SulfuricAcid.getLiquid(2100)).add("vitriol_of_clay_to_alumina", 19, 64);
            ELECTROLYZER.RB().fi(DistilledWater.getLiquid(900), VitriolOfClay.getLiquid(1700)).ii(SELECTOR_TAG_INGREDIENTS.get(1)).io(DUST_SMALL.get(Alumina, 2)).fo(SulfuricAcid.getLiquid(2100)).add("vitriol_of_clay_to_alumina_distilled", 19, 64);
            ELECTROLYZER.RB().fi(Water.getLiquid(6000), ChloroplatinicAcid.getLiquid(9000)).ii(SELECTOR_TAG_INGREDIENTS.get(2)).io(DUST.get(Platinum)).fo(HydrochloricAcid.getLiquid(12000), Oxygen.getGas(2000)).add("chloroplatinic_acid", 96, 64);
            ELECTROLYZER.RB().fi(DistilledWater.getLiquid(6000), ChloroplatinicAcid.getLiquid(9000)).ii(SELECTOR_TAG_INGREDIENTS.get(2)).io(DUST.get(Platinum)).fo(HydrochloricAcid.getLiquid(12000), Oxygen.getGas(2000)).add("chloroplatinic_acid_distilled", 96, 64);
        }
    }

    private static void addVitriolRecipe(Material vitriol, Material dust){
        ELECTROLYZER.RB().fi(vitriol.getLiquid(6000), Water.getLiquid(3000)).ii(SELECTOR_TAG_INGREDIENTS.get(2)).io(DUST.get(dust)).fo(SulfuricAcid.getLiquid(7000), Oxygen.getGas(1000)).add(vitriol.getId() + "_to_" + dust.getId(), 64, 64);
        ELECTROLYZER.RB().fi(vitriol.getLiquid(6000), DistilledWater.getLiquid(3000)).ii(SELECTOR_TAG_INGREDIENTS.get(2)).io(DUST.get(dust)).fo(SulfuricAcid.getLiquid(7000), Oxygen.getGas(1000)).add(vitriol.getId() + "_to_" + dust.getId() + "_distilled", 64, 64);
    }

}
