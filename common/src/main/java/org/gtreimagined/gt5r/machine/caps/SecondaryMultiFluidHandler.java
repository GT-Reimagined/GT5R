package org.gtreimagined.gt5r.machine.caps;

import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.IComponentHandler;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MultiMachineFluidHandler;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SecondaryMultiFluidHandler<T extends BlockEntityMultiMachine<T>> extends MultiMachineFluidHandler<T> {
    protected final EnumMap<FluidDirection, FluidTanks> secondaryTanks = new EnumMap<>(FluidDirection.class);
    public SecondaryMultiFluidHandler(T tile) {
        super(tile);
    }

    protected void cacheSecondaryInputs() {
        List<MachineFluidHandler<?>> inputs = tile.getComponentsByHandlerId(secondaryInputComponentString()).stream().map(IComponentHandler::getFluidHandler).map(Optional::get).sorted(this::compareSecondaryInputHatches).collect(Collectors.toUnmodifiableList());//this::allocateExtraSize);
        secondaryTanks.put(FluidDirection.INPUT, new FluidTanks(inputs.stream().filter(t -> t.getInputTanks() != null).flatMap(t -> Arrays.stream(t.getInputTanks().getBackingTanks())).collect(Collectors.toList())));
    }

    protected int compareSecondaryInputHatches(MachineFluidHandler<?> a, MachineFluidHandler<?> b) {
        return 0;
    }

    protected String secondaryInputComponentString(){
        return "secondary_fluid_input";
    }

    protected String secondaryOutputComponentString(){
        return "secondary_fluid_output";
    }

    protected void cacheSecondaryOutputs() {
        List<MachineFluidHandler<?>> outputs = tile.getComponentsByHandlerId(secondaryOutputComponentString()).stream().map(IComponentHandler::getFluidHandler).map(Optional::get).sorted(this::compareSecondaryOutputHatches).collect(Collectors.toUnmodifiableList());//this::allocateExtraSize);
        secondaryTanks.put(FluidDirection.OUTPUT, new FluidTanks(outputs.stream().filter(t -> t.getOutputTanks() != null).flatMap(t -> Arrays.stream(t.getOutputTanks().getBackingTanks())).collect(Collectors.toList())));
    }

    protected int compareSecondaryOutputHatches(MachineFluidHandler<?> a, MachineFluidHandler<?> b) {
        return 0;
    }

    public void invalidate() {
        super.invalidate();
        secondaryTanks.clear();
    }

    public void onStructureBuild() {
        super.onStructureBuild();
        cacheSecondaryInputs();
        cacheSecondaryOutputs();
    }
}
