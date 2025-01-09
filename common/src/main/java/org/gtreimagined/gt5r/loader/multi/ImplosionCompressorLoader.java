package org.gtreimagined.gt5r.loader.multi;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Items;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Diamond;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.IMPLOSION_COMPRESSOR;

public class ImplosionCompressorLoader {
    public static void init(){
        GEM.all().stream().filter(m -> !m.has(GT5RMaterialTags.CRYSTALLIZE) && !m.has(GT5RMaterialTags.NON_GEMS) && m.has(DUST)).forEach(m -> {
            int tnt = m == RedGarnet || m == YellowGarnet || m == Ruby ? 4 : m == Diamond ? 8: 6;
            if (m.has(GEM_EXQUISITE)){
                IMPLOSION_COMPRESSOR.RB().ii(DUST.getMaterialIngredient(m, 4), RecipeIngredient.of(Items.TNT, tnt * 2), GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(4)).io(GEM.get(m, 3), DUST_TINY.get(DarkAsh, tnt * 2)).add(m.getId() + "_from_tnt", 20, 30);
                IMPLOSION_COMPRESSOR.RB().ii(DUST.getMaterialIngredient(m, 6), RecipeIngredient.of(Items.TNT, tnt * 3), GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(6)).io(GEM_EXQUISITE.get(m, 1), DUST_TINY.get(DarkAsh, tnt * 2)).add("exquisite_" + m.getId() + "_from_tnt", 30, 30);
            } else {
                IMPLOSION_COMPRESSOR.RB().ii(DUST.getMaterialIngredient(m, 4), RecipeIngredient.of(Items.TNT, tnt * 2)).io(GEM.get(m, 3), DUST_TINY.get(DarkAsh, tnt * 2)).add(m.getId() + "_from_tnt", 20, 30);
            }
        });
    }
}
