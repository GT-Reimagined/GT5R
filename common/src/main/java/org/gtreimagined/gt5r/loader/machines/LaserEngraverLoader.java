package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt5r.data.RecipeMaps;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static org.gtreimagined.gt5r.data.GT5RMaterialTypes.BOULE;
import static org.gtreimagined.gt5r.data.Materials.*;

public class LaserEngraverLoader {
    public static void init(){
        RecipeMaps.LASER_ENGRAVER.RB().ii(RecipeIngredient.of(GTCoreItems.LapotronCrystal, 1), LENS.getMaterialIngredient(Sapphire, 1).setNoConsume().setNoConsume()).io(new ItemStack(GTCoreItems.EngravedLapotronChip, 3)).add("engraved_lapotron_chip_sapphire", 256, 480);
        RecipeMaps.LASER_ENGRAVER.RB().ii(RecipeIngredient.of(GTCoreItems.LapotronCrystal, 1), LENS.getMaterialIngredient(BlueTopaz, 1).setNoConsume().setNoConsume()).io(new ItemStack(GTCoreItems.EngravedLapotronChip, 3)).add("engraved_lapotron_chip_blue_topaz", 256, 480);
        RecipeMaps.LASER_ENGRAVER.RB().ii(RecipeIngredient.of(GTCoreItems.LapotronCrystal, 1), LENS.getMaterialIngredient(Opal, 1).setNoConsume().setNoConsume()).io(new ItemStack(GTCoreItems.EngravedLapotronChip, 3)).add("engraved_lapotron_chip_opal", 256, 480);
        RecipeMaps.LASER_ENGRAVER.RB().ii(PLATE.getMaterialIngredient(Emerald, 1), LENS.getMaterialIngredient(Emerald, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EngravedCrystalChip)).add("engraved_crystal_chip_emerald", 256, 480);
        RecipeMaps.LASER_ENGRAVER.RB().ii(PLATE.getMaterialIngredient(Olivine, 1), LENS.getMaterialIngredient(Emerald, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EngravedCrystalChip)).add("engraved_crystal_chip_olivine", 256, 480);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(Copper, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringMV)).add("etched_wiring_mv_1", 64, 30);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(AnnealedCopper, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringMV)).add("etched_wiring_mv_2", 64, 30);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(Copper, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringMV)).add("etched_wiring_mv_3", 64, 30);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(AnnealedCopper, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringMV)).add("etched_wiring_mv_4", 64, 30);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(Gold, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringHV)).add("etched_wiring_hv_1", 64, 120);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(Electrum, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringHV)).add("etched_wiring_hv_2", 64, 120);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(Gold, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringHV)).add("etched_wiring_hv_3", 64, 120);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(Electrum, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringHV)).add("etched_wiring_hv_4", 64, 120);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(Platinum, 1), LENS.getMaterialIngredient(Ruby, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringEV)).add("etched_wiring_ev_1", 64, 480);
        RecipeMaps.LASER_ENGRAVER.RB().ii(FOIL.getMaterialIngredient(Platinum, 1), LENS.getMaterialIngredient(RedGarnet, 1).setNoConsume()).io(new ItemStack(GTCoreItems.EtchedWiringEV)).add("etched_wiring_ev_2", 64, 480);
        BOULE.all().stream().filter(m -> m.has(GEM_EXQUISITE)).forEach(m -> {
            RecipeMaps.LASER_ENGRAVER.RB().ii(BOULE.getMaterialIngredient(m, 1), LENS.getMaterialIngredient(Diamond, 1).setNoConsume()).io(GEM_EXQUISITE.get(m)).add("gem_exquisite_" + m.getId(), 64, 256);
        });
    }
}
