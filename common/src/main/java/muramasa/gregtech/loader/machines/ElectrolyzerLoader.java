package muramasa.gregtech.loader.machines;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.gregtech.data.GregTechMaterialTags;
import muramasa.gregtech.data.Materials;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.gregtech.data.GregTechMaterialTags.*;
import static muramasa.gregtech.data.RecipeMaps.ELECTROLYZING;

public class ElectrolyzerLoader {
    public static void init() {
        ELEC.all().forEach(t -> {
            if (!t.has(DUST) && !t.has(LIQUID) && !t.has(GAS)) return;
            FluidHolder[] fluids = t.getProcessInto().stream().filter(mat -> ((mat.m.has(GAS) || mat.m.has(AntimatterMaterialTypes.LIQUID)) && !mat.m.has(AntimatterMaterialTypes.DUST))).map(mat -> mat.m.has(GAS) ? mat.m.getGas(mat.s*1000) : mat.m.getLiquid(mat.s*1000)).toArray(FluidHolder[]::new);
            for (FluidHolder fluid : fluids) {
                if (fluid.isEmpty())
                    return;
            }
            if (fluids.length > 6) return;
            int euPerTick = t.has(ELEC30) ? 30 : t.has(ELEC60) ? 60 : t.has(ELEC90) ? 90 : 120;
            ItemStack[] items = t.getProcessInto().stream().filter(mat -> mat.m.has(AntimatterMaterialTypes.DUST)).map(mat -> AntimatterMaterialTypes.DUST.get(mat.m, mat.s)).toArray(ItemStack[]::new);
            int inputAmount = MaterialTags.PROCESS_INTO.get(t).getRight() > 0 ? MaterialTags.PROCESS_INTO.get(t).getRight() : t.getProcessInto().stream().mapToInt(mat -> mat.s).sum();
            RecipeBuilder b = ELECTROLYZING.RB();
            if (t.has(DUST)){
                b.ii(DUST.getMaterialIngredient(t, inputAmount));
            } else {
                b.fi(t.has(LIQUID) ? t.getLiquid(inputAmount * 1000) : t.getGas(inputAmount * 1000));
            }
            long duration = t.has(ELEC_TICKS) ? ELEC_TICKS.getInt(t) : t.getMass() * 20;
            b.io(items).fo(fluids).add("dust_" + t.getId(),duration, euPerTick);
        });
        ELECTROLYZING.RB().ii(RecipeIngredient.of(ItemTags.SAND, 8)).io(DUST.get(Materials.SiliconDioxide)).add("sand_to_silicon_dioxide", 500, 25);
        ELECTROLYZING.RB().ii(RecipeIngredient.of(Items.BONE_MEAL, 3)).io(DUST.get(Materials.Calcium)).add("bone_meal", 98, 26);
    }

}
