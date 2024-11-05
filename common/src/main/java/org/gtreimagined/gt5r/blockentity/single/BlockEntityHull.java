package org.gtreimagined.gt5r.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineEnergyHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BlockEntityHull extends BlockEntityMachine<BlockEntityHull> {
    public BlockEntityHull(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        fluidHandler.set(() -> new MachineFluidHandler<>(this, 1000 + (1000 * getMachineTier().getIntegerId()), 2000){
            @Override
            public FluidHolder[] getOutputs() {
                return getInputs();
            }

            @Override
            public @Nullable FluidTanks getOutputTanks() {
                return getInputTanks();
            }

            @Override
            public FluidTanks getAllTanks() {
                return getInputTanks();
            }

            @Override
            protected FluidTank getTank(int tank) {
                return getInputTanks().getTank(tank);
            }
            @Override
            public FluidTanks getTanks(int tank) {
                return getInputTanks();
            }
        });
        energyHandler.set(() -> new MachineEnergyHandler<>(this, 0L, 512L + getMachineTier().getVoltage() * 8L, getMachineTier().getVoltage(), getMachineTier().getVoltage(), 1, 1) {
            @Override
            public boolean canOutput(Direction direction) {
                return tile.getFacing().get3DDataValue() == direction.get3DDataValue();
            }

            @Override
            public boolean canInput(Direction direction) {
                return !canOutput(direction);
            }
        });
    }

    @Override
    public boolean wrenchMachine(Player player, BlockHitResult res, boolean crouch) {
        return setFacing(player, Utils.getInteractSide(res));
    }

    @Override
    public boolean canPlayerOpenGui(Player playerEntity) {
        return super.canPlayerOpenGui(playerEntity) && playerEntity.isCreative();
    }
}
