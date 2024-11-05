package org.gtreimagined.gt5r.loader.machines;

import org.gtreimagined.gt5r.GT5RConfig;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST_SMALL;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.DEHYDRATOR;

public class DehydratorLoader {
    public static void init() {
        DEHYDRATOR.RB().ii(DUST.getMaterialIngredient(TungsticAcid, 7)).io(DUST.get(TungstenTrioxide, 4)).fo(DistilledWater.getLiquid(3000)).add("tungsten_trioxide", 300 * 20, 16);
        DEHYDRATOR.RB().fi(SaltWater.getFluidIngredient(1000)).io(DUST_SMALL.get(Salt, 1)).fo(DistilledWater.getLiquid(750)).add("salt_water_drying", 8 * 20, 16);
        if (GT5RConfig.HARDER_ALUMINIUM_PROCESSING.get()) {
            DEHYDRATOR.RB().ii(DUST.getMaterialIngredient(AluminiumHydroxide, 14)).io(DUST.get(Alumina, 5)).fo(DistilledWater.getLiquid(9000)).add("alumina", 200 * 20, 16);
        }
    }
}
