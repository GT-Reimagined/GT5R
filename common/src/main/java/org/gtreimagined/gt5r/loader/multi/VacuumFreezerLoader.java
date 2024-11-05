package org.gtreimagined.gt5r.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT_HOT;
import static org.gtreimagined.gt5r.data.Materials.Nitrogen;
import static org.gtreimagined.gt5r.data.RecipeMaps.VACUUM_FREEZER;

public class VacuumFreezerLoader {
    public static void init() {
        INGOT_HOT.all().forEach(hi -> {
            Item ingot = INGOT.get(hi);
            VACUUM_FREEZER.RB().ii(RecipeIngredient.of(INGOT_HOT.getMaterialTag(hi),1))
                    .io(new ItemStack(ingot,1)).add("ingot_hot_" + hi.getId(),hi.getMass(), 120);
        });
        VACUUM_FREEZER.RB().fi(Nitrogen.getGas(1000)).fo(Nitrogen.getLiquid(1000)).add("liquid_nitrogen", Nitrogen.getMass(), 120);
    }
}
