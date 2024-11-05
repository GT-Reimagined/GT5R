package org.gtreimagined.gt5r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.IExtendingBlockEntity;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityBridge extends BlockEntityMachine<BlockEntityBridge> implements IExtendingBlockEntity {
    public BlockEntityBridge(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public BlockEntity getExtendedBlockEntity(Direction side) {
        return getCachedBlockEntity(side);
    }
    @Override
    public void onBlockUpdate(BlockPos neighbor) {
        super.onBlockUpdate(neighbor);
        Direction facing = Utils.getOffsetFacing(this.getBlockPos(), neighbor);
        BlockPos offset = getBlockPos().relative(facing.getOpposite());
        getLevel().neighborChanged(offset, getLevel().getBlockState(offset).getBlock(), getBlockPos());
    }
}
