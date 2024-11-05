package org.gtreimagined.gt5r.cover.redstone;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.Tier;
import org.gtreimagined.gt5r.cover.base.CoverBasicRedstoneOutput;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverActivityDetectorProcessing extends CoverBasicRedstoneOutput {
    public CoverActivityDetectorProcessing(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public void onUpdate() {
        if (source().getTile() instanceof BlockEntityMachine<?> machine && machine.has(MachineFlag.RECIPE) && machine.isServerSide()){
            if (machine.getMachineState() == MachineState.ACTIVE){
                setOutputRedstone(inverted ? 0 : 15);
            } else {
                setOutputRedstone(inverted ? 15 : 0);
            }
        }
    }
}
