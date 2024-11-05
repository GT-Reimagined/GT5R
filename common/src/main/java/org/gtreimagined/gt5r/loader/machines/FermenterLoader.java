package org.gtreimagined.gt5r.loader.machines;

import static org.gtreimagined.gt5r.data.Materials.Biomass;
import static org.gtreimagined.gt5r.data.Materials.FermentedBiomass;
import static org.gtreimagined.gt5r.data.RecipeMaps.FERMENTER;

public class FermenterLoader {
    public static void init() {
        FERMENTER.RB().fi(Biomass.getLiquid(100)).fo(FermentedBiomass.getLiquid(100)).add("fermented_biomass",100,2);
    }
}
