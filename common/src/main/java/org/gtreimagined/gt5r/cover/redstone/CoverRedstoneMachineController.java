package org.gtreimagined.gt5r.cover.redstone;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import org.gtreimagined.gt5r.cover.base.CoverBasicRedstoneInput;
import org.jetbrains.annotations.Nullable;


public class CoverRedstoneMachineController extends CoverBasicRedstoneInput {

    public CoverRedstoneMachineController(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public void onRemove() {
        if (handler.getTile() instanceof BlockEntityMachine<?> machine){
            if (machine.getLevel().isLoaded(machine.getBlockPos())) {
                if (machine.getMachineState() == MachineState.DISABLED){
                    machine.toggleMachine();
                }
            }
        }
    }

    @Override
    public void onUpdate() {
        if (handler.getTile() instanceof BlockEntityMachine<?> machine && machine.isServerSide()){
            if (machine.getMachineState() != MachineState.DISABLED){
                if (!isPowered()){
                    machine.toggleMachine();
                }
            } else {
                if (isPowered()){
                    machine.toggleMachine();
                }
            }

        }
    }
}
