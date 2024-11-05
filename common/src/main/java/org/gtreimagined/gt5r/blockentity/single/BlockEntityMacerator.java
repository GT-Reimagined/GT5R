package org.gtreimagined.gt5r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.map.IRecipeMap;
import org.gtreimagined.gt5r.data.RecipeMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import static muramasa.antimatter.machine.Tier.LV;
import static muramasa.antimatter.machine.Tier.MV;

public class BlockEntityMacerator extends BlockEntityMachine<BlockEntityMacerator> {
    public BlockEntityMacerator(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            protected IRecipeMap getRecipeMap() {
                if (tile.getMachineTier() == LV || tile.getMachineTier() == MV) return RecipeMaps.MACERATOR;
                return super.getRecipeMap();
            }
        });
    }
}
