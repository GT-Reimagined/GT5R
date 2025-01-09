package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gtcore.block.RedstoneWire;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static org.gtreimagined.gt5r.data.GT5RBlocks.FLUID_PIPE_STEEL;
import static org.gtreimagined.gt5r.data.Materials.Carbon;
import static org.gtreimagined.gt5r.data.RecipeMaps.WIRE_MILL;

public class WiremillLoader {
    public static void init() {
        AntimatterAPI.all(Wire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            RecipeIngredient ing = t.getMaterial().has(INGOT) ? INGOT.getMaterialIngredient(t.getMaterial(),1) : DUST.getMaterialIngredient(t.getMaterial(),1);
            WIRE_MILL.RB().ii(ing).io(stack).add(t.getMaterial().getId() + "_wire", 100,4);
            if (WIRE_FINE.allowItemGen(t.getMaterial())) {
                WIRE_MILL.RB().ii(wireItem).io(WIRE_FINE.get(t.getMaterial(),4)).add(t.getMaterial().getId() + "_wire_fine", 200,8);
            }
        });
        AntimatterAPI.all(RedstoneWire.class).forEach(t -> {
            Item wireItem = t.getBlockItem(PipeSize.VTINY);
            ItemStack stack = new ItemStack(wireItem,2);
            RecipeIngredient ing = t.getMaterial().has(INGOT) ? INGOT.getMaterialIngredient(t.getMaterial(),1) : DUST.getMaterialIngredient(t.getMaterial(),1);
            WIRE_MILL.RB().ii(ing).io(stack).add(t.getMaterial().getId() + "_wire", 100,4);
            if (WIRE_FINE.allowItemGen(t.getMaterial())) {
                WIRE_MILL.RB().ii(wireItem).io(WIRE_FINE.get(t.getMaterial(),4)).add(t.getMaterial().getId() + "_wire_fine", 200,8);
            }
        });
        WIRE_MILL.RB().ii(FLUID_PIPE_STEEL.getBlockItem(PipeSize.TINY)).io(GT5RBlocks.MINING_PIPE_THIN.asItem()).add("mining_pipe", 200, 16);
        //if (!GT5RConfig.HARD_CARBON.get()){
            WIRE_MILL.RB().ii(DUST.getMaterialIngredient(Carbon, 8)).io(GTCoreItems.CarbonFibre).add("carbon_fibre", 400, 2);
        //}
    }
}
