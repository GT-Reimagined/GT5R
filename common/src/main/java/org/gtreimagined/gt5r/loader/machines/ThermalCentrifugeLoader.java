package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;
import net.minecraft.world.item.ItemStack;

import static org.gtreimagined.gt5r.data.RecipeMaps.THERMAL_CENTRIFUGE;

public class ThermalCentrifugeLoader {
    public static void init() {
        AntimatterMaterialTypes.CRUSHED_PURIFIED.all().forEach(m -> {
            Material aOreByProduct1 = m.getByProducts().size() >= 1 ? m.getByProducts().get(0) : MaterialTags.MACERATE_INTO.getMapping(m);
            Material aOreByProduct2 = m.has(GT5RMaterialTags.THERMAL_CENTRIFUGE_EXPLICIT) ? GT5RMaterialTags.THERMAL_CENTRIFUGE_EXPLICIT.getMapping(m) : m.getByProducts().size() >= 2 ? m.getByProducts().get(1) : aOreByProduct1;
            ItemStack stoneDust = AntimatterMaterialTypes.DUST.get(AntimatterMaterials.Stone, 1);

            THERMAL_CENTRIFUGE.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED_PURIFIED.get(m),1)).io(AntimatterMaterialTypes.CRUSHED_REFINED.get(m, 1), AntimatterMaterialTypes.DUST_TINY.get(aOreByProduct2, 1)).add("purified_" + m.getId(),500, 48,0,2);
            THERMAL_CENTRIFUGE.RB().ii(RecipeIngredient.of(AntimatterMaterialTypes.CRUSHED.get(m),1)).io(AntimatterMaterialTypes.CRUSHED_REFINED.get(m, 1), AntimatterMaterialTypes.DUST_TINY.get(aOreByProduct2, 1), stoneDust).add("crushed_" + m.getId(),500, 48,0,2);
        });
    }
}
