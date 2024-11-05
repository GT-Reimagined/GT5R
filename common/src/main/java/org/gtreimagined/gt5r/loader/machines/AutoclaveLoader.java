package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;
import org.gtreimagined.gt5r.data.Materials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.AUTOCLAVE;
import static org.gtreimagined.gtcore.data.GTCoreItems.EnergyCrystal;
import static org.gtreimagined.gtcore.data.GTCoreItems.RawLapotronCrustal;

public class AutoclaveLoader {
    public static void init() {
        GT5RMaterialTags.CRYSTALLIZE.all().stream().filter(m -> m.has(GEM) && m.has(DUST)).forEach(m -> {
            int dur;
            long d = m.getDensity();
            if(d <= 1000){
                dur = 100;
            }else if(d <= 2000){
                dur = 200;
            }else{
                dur = 400;
            }
            AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(m, 1)).fi(Water.getLiquid(200)).io(GEM.get(m, 1)).add(m.getId(), dur,8);
            AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(m, 1)).fi(DistilledWater.getLiquid(200)).io(GEM.get(m, 1)).add(m.getId() + "_with_distilled_water", dur,8);
        });
        AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(Bauxite, 2), DUST.getMaterialIngredient(SodiumHydroxide, 3)).fi(DistilledWater.getLiquid(1000)).io(DUST.get(SodiumAluminate, 4), DUST_TINY.get(Ilmenite, 1), DUST_TINY.get(Rutile, 1)).outputChances(1.0, 0.25, 0.5).add("bauxite_distilled_water", 1500, 16);
        AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(Bauxite, 2), DUST.getMaterialIngredient(SodiumHydroxide, 3)).fi(Water.getLiquid(1000)).io(DUST.get(SodiumAluminate, 4), DUST_TINY.get(Ilmenite, 1), DUST_TINY.get(Rutile, 1)).outputChances(1.0, 0.25, 0.5).add("bauxite", 3000, 16);
        if (GT5RConfig.HARD_CARBON.get()){
            AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(Materials.Carbon, 4)).fi(Materials.Platinum.getLiquid(1)).io(new ItemStack(GTCoreItems.CarbonFibre, 2)).add("carbon_fibre", 150, 5);
            AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(Materials.Carbon, 4)).fi(Materials.Lutetium.getLiquid(1)).io(new ItemStack(GTCoreItems.CarbonFibre, 2)).add("carbon_fibre_1", 150, 5);
            AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(Materials.Carbon, 4)).fi(Materials.Palladium.getLiquid(1)).io(new ItemStack(GTCoreItems.CarbonFibre, 2)).add("carbon_fibre_2", 150, 5);
        }
        AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(Energium, 9)).fi(DistilledWater.getLiquid(1000)).io(EnergyCrystal).add("energy_crystal_distilled_water", 250, 256);
        AUTOCLAVE.RB().ii(DUST.getMaterialIngredient(Energium, 9)).fi(Water.getLiquid(1000)).io(EnergyCrystal).add("energy_crystal", 500, 256);
        AUTOCLAVE.RB().ii(RecipeIngredient.of(1, GEM_EXQUISITE.getMaterialTag(Sapphire), GEM_EXQUISITE.getMaterialTag(GreenSapphire))).fi(Lapotronium.getLiquid(1000)).io(RawLapotronCrustal).add("raw_lapotron_crystal", 250, 256);
        AUTOCLAVE.RB().ii(of(Items.NETHER_STAR)).fi(Neutronium.getLiquid(L * 2)).io(GT5RItems.GraviStar).add("gravistar", 480, 7680);
    }
}
