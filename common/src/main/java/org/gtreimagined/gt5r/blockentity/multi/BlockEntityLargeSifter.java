package org.gtreimagined.gt5r.blockentity.multi;

import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.capability.machine.MultiMachineItemHandler;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.machine.caps.ParallelRecipeHandler;

import java.util.HashSet;
import java.util.Set;

public class BlockEntityLargeSifter extends BlockEntityMultiMachine<BlockEntityLargeSifter> {
    public Set<Integer> HATCH_LAYERS = new HashSet<>();
    public BlockEntityLargeSifter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new ParallelRecipeHandler<>(this, 64));
        this.itemHandler.set(() -> new MultiMachineItemHandler<>(this){
            @Override
            protected int compareOutputBuses(MachineItemHandler<?> a, MachineItemHandler<?> b) {
                return a.getTile().getBlockPos().getY() < b.getTile().getBlockPos().getY() ? 1 : -1;
            }
        });
    }

    @Override
    public boolean checkStructure() {
        HATCH_LAYERS.clear();
        return super.checkStructure();
    }
}
