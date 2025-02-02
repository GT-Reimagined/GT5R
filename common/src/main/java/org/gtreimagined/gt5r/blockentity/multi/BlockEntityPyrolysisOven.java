package org.gtreimagined.gt5r.blockentity.multi;

import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.block.BlockCoil;

public class BlockEntityPyrolysisOven extends BlockEntityMultiMachine<BlockEntityPyrolysisOven> {
    private BlockCoil.CoilData coilData;

    public BlockEntityPyrolysisOven(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            protected void calculateDurations() {
                super.calculateDurations();
                maxProgress = (int) (maxProgress / tile.coilData.percentage());
            }
        });
    }

    public BlockCoil.CoilData getCoilData() {
        return coilData;
    }

    public void setCoilData(BlockCoil.CoilData coilData) {
        this.coilData = coilData;
    }
}
