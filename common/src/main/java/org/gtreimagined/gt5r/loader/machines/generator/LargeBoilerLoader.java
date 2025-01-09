package org.gtreimagined.gt5r.loader.machines.generator;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gt5r.data.RecipeMaps;

import static org.gtreimagined.gt5r.data.GT5RMaterialTags.SEMIFUELS;

public class LargeBoilerLoader {
    public static void init(){
        AntimatterPlatformUtils.INSTANCE.getAllBurnables().forEach((i, b) -> {
            ResourceLocation id = AntimatterPlatformUtils.INSTANCE.getIdFromItem(i);
            RecipeMaps.LARGE_BOILERS.RB().ii(RecipeIngredient.of(i)).add(id.getNamespace() + "." + id.getPath(), b / 80, b % 80);
        });
        AntimatterMaterialTypes.LIQUID.all().forEach(mat -> {
            if (!mat.has(MaterialTags.FUEL_POWER) || MaterialTags.FUEL_POWER.getInt(mat) <= 0) return;
            int special = mat.has(SEMIFUELS) ? -1 : 4;
            int ticks = mat.has(SEMIFUELS) ? MaterialTags.FUEL_POWER.getInt(mat) * 2 : MaterialTags.FUEL_POWER.getInt(mat) / 2;
            RecipeMaps.LARGE_BOILERS.RB().fi(mat.getLiquid(1000)).add(mat.getId(), ticks, 0, special);
        });
    }
}
