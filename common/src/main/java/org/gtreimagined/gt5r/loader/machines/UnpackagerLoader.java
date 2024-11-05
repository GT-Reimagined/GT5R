package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.material.Material;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.UNPACKAGER;

public class UnpackagerLoader {
    public static void init() {
        for (Material material : DUST.all()) {
            if (material.has(DUST_TINY)) {
                UNPACKAGER.RB().ii(DUST.getMaterialIngredient(material, 1), GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(9)).io(DUST_TINY.get(material, 9)).add("dust_tiny_" + material.getId(),100, 2);
            }
            if (material.has(DUST_SMALL)) {
                UNPACKAGER.RB().ii(DUST.getMaterialIngredient(material, 1), GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(4)).io(DUST_SMALL.get(material, 4)).add("dust_small_" + material.getId(),100, 2);
            }
        }

    }
}
