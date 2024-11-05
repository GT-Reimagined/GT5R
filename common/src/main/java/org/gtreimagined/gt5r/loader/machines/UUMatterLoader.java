package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gt5r.data.RecipeMaps;
import org.gtreimagined.gtcore.data.GTCoreItems;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static org.gtreimagined.gt5r.data.Materials.UUAmplifier;

public class UUMatterLoader {
    public static void init(){
        RecipeMaps.MASS_FABRICATOR.RB().fi(UUAmplifier.getLiquid(1)).fo(Materials.UUMatter.getLiquid(1)).add("uu_matter", 803, 256);
        RecipeMaps.MASS_FABRICATOR.RB().ii(GTCoreItems.SELECTOR_TAG_INGREDIENTS.get(0)).fo(Materials.UUMatter.getLiquid(1)).add("uu_matter_expensive", 3215, 256);
        AntimatterAPI.all(Material.class).stream().filter(m -> m.getElement() != null && (m.has(DUST) || m.has(LIQUID) || m.has(GAS))).forEach(m -> {
            RecipeBuilder b = RecipeMaps.AMP_FABRICATOR.RB();
            if (m.has(AntimatterMaterialTypes.DUST)){
                b.ii(DUST.getMaterialIngredient(m, 1));
            } else if (m.has(LIQUID)){
                b.fi(m.getLiquid(1000));
            } else if (m.has(GAS)){
                b.fi(m.getGas(1000));
            }
            b.fo(UUAmplifier.getLiquid((m.getProtons() + m.getNeutrons()) * TesseractGraphWrappers.dropletMultiplier)).add("uu_amplifier_from_" + m.getId(), 100, 32);
        });
    }
}
