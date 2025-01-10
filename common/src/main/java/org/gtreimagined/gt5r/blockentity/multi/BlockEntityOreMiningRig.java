package org.gtreimagined.gt5r.blockentity.multi;

import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityOreMiningRig extends BlockEntityMultiMachine<BlockEntityOreMiningRig> {

    public BlockEntityOreMiningRig(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
