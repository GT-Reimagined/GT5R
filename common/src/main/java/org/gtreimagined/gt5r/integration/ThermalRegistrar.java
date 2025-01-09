package org.gtreimagined.gt5r.integration;

import com.google.common.collect.ImmutableMap;
import org.gtreimagined.gtcore.data.GTCoreMaterials;

import static muramasa.antimatter.data.AntimatterMaterials.*;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.Materials.Silver;
import static org.gtreimagined.gt5r.loader.machines.AlloySmelterLoader.addAlloyRecipes;
import static org.gtreimagined.gt5r.loader.multi.BlastFurnaceLoader.addBlastAlloyRecipes;

public class ThermalRegistrar {

    public static void thermalMachineRecipes(){

        addBlastAlloyRecipes(GTCoreMaterials.Enderium, 4, 1000, 120, ImmutableMap.of(Tin, 2, Silver, 1, Platinum, 1, EnderPearl, 4));
    }
}
