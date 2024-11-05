package org.gtreimagined.gt5r.blockentity.single;

import muramasa.antimatter.blockentity.multi.BlockEntityHatch;
import muramasa.antimatter.capability.machine.MachineCoverHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.types.HatchMachine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityInputHatch extends BlockEntityHatch<BlockEntityInputHatch> {
    public BlockEntityInputHatch(HatchMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        fluidHandler.set(() -> new MachineFluidHandler<>(this, 8000 * (getMachineTier().getIntegerId() + 1), 1000 * (250 + getMachineTier().getIntegerId())){
            @Override
            public boolean canInput(Direction direction) {
                return super.canInput(direction) && direction == coverHandler.map(MachineCoverHandler::getOutputFacing).orElse(null);
            }
        });
    }
}
