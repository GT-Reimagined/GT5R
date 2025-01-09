package org.gtreimagined.gt5r.machine.caps;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.Antimatter;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.util.Utils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static muramasa.antimatter.machine.MachineState.OUTPUT_FULL;

public class ParallelRecipeHandler<T extends BlockEntityMachine<T>> extends MachineRecipeHandler<T> {
    public int concurrentRecipes = 0;
    final int maxSimultaneousRecipes;
    public ParallelRecipeHandler(T tile, int maxSimultaneousRecipes) {
        super(tile);
        this.maxSimultaneousRecipes = maxSimultaneousRecipes;
    }

    @Override
    public boolean consumeInputs() {
        concurrentRecipes = 0;
        for (int i = 0; i < maxSimultaneousRecipes(); i++) {
            boolean simulate = i != 0;
            boolean consumeInput = consumeSingleInput(simulate);
            if (!consumeInput) break;
            if (simulate) {
                consumeSingleInput(false);
            }
            concurrentRecipes++;
        }
        return concurrentRecipes > 0;
    }

    protected boolean consumeSingleInput(boolean simulate) {
        boolean flag = true;
        if (!tile.hadFirstTick()) return true;
        final List<ItemStack>[] itemInputs = new List[]{new ArrayList<>()};
        final List<FluidHolder>[] fluidInputs = new List[]{new ArrayList<>()};
        if (activeRecipe.hasInputItems()) {
            flag &= tile.itemHandler.map(h -> {
                itemInputs[0] = h.consumeInputs(activeRecipe, simulate);
                return !itemInputs[0].isEmpty();
            }).orElse(true);
        }
        if (activeRecipe.hasInputFluids()) {
            flag &= tile.fluidHandler.map(h -> {
                fluidInputs[0] = h.consumeAndReturnInputs(activeRecipe.getInputFluids(), simulate);
                return !fluidInputs[0].isEmpty();
            }).orElse(true);
        }
        if (!simulate) {
            if (flag) {
                consumedResources = true;
            }
            this.itemInputs = itemInputs[0];
            this.fluidInputs = fluidInputs[0];
        }
        return flag;
    }

    @Override
    protected void addOutputs() {
        for (int i = 0; i < concurrentRecipes; i++) {
            if (activeRecipe.hasOutputItems()) {
                tile.itemHandler.ifPresent(h -> {
                    //Roll the chances here. If they don't fit add flat (no chances).
                    ItemStack[] out = activeRecipe.getOutputItems(true);
                    if (h.canOutputsFit(out)) {
                        h.addOutputs(out);
                    } else {
                        h.addOutputs(activeRecipe.getFlatOutputItems());
                    }
                });
            }
            if (activeRecipe.hasOutputFluids()) {
                tile.fluidHandler.ifPresent(h -> {
                    h.addOutputs(activeRecipe.getOutputFluids());
                });
            }
        }
        if (activeRecipe.hasOutputItems()) tile.onMachineEvent(MachineEvent.ITEMS_OUTPUTTED);
        if (activeRecipe.hasOutputFluids()) tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
        concurrentRecipes = 0;
    }

    private void addPartialOutputs(){
        int successfulRecipes = 0;
        for (int i = 0; i < concurrentRecipes; i++) {
            AtomicBoolean successful = new AtomicBoolean(false);
            if (activeRecipe.hasOutputItems()) {
                tile.itemHandler.ifPresent(h -> {
                    //Roll the chances here. If they don't fit add flat (no chances).
                    ItemStack[] out = activeRecipe.getOutputItems(true);
                    if (h.canOutputsFit(out)) {
                        h.addOutputs(out);
                        successful.set(true);
                    } else if (h.canOutputsFit(activeRecipe.getFlatOutputItems())){
                        successful.set(true);
                        h.addOutputs(activeRecipe.getFlatOutputItems());
                    }
                });
            }
            if (activeRecipe.hasOutputFluids()) {
                tile.fluidHandler.ifPresent(h -> {
                    if (h.canOutputsFit(activeRecipe.getOutputFluids())) {
                        h.addOutputs(activeRecipe.getOutputFluids());
                        successful.set(true);
                    }
                });
            }
            if (successful.get()){
                successfulRecipes++;
            }
        }
        concurrentRecipes -= successfulRecipes;
        if (activeRecipe.hasOutputItems()) tile.onMachineEvent(MachineEvent.ITEMS_OUTPUTTED);
        if (activeRecipe.hasOutputFluids()) tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
    }


    protected void logString(String message){
        Antimatter.LOGGER.info(message);
    }
    @Override
    public void onServerUpdate() {
        if (tile.getMachineState() == OUTPUT_FULL) {
            if (!canOutput() && super.canOutput()) {
                addPartialOutputs();
                return;
            }
        }
        if (activeRecipe == null && concurrentRecipes > 0) concurrentRecipes = 0;
        super.onServerUpdate();
    }

    public boolean canOutput() {
        if (concurrentRecipes <= 1) return super.canOutput();
        if (tile.itemHandler.isPresent() && activeRecipe.hasOutputItems() && !tile.itemHandler.map(t -> {
            List<ItemStack> outputs = new ArrayList<>();
            for (int i = 0; i < concurrentRecipes; i++) {
                for (ItemStack item : activeRecipe.getFlatOutputItems()) {
                    outputs.add(item.copy());
                }
            }
            List<ItemStack> merged = Utils.mergeItems(new ArrayList<>(), outputs);
            return t.canOutputsFit(merged.toArray(ItemStack[]::new));
        }).orElse(false))
            return false;
        if (!tile.fluidHandler.isPresent() || !activeRecipe.hasOutputFluids()) return true;
        List<FluidHolder> outputs = new ArrayList<>();
        for (int i = 0; i < concurrentRecipes; i++) {
            for (FluidHolder fluidHolder : activeRecipe.getOutputFluids()) {
                outputs.add(fluidHolder.copyHolder());
            }
        }
        List<FluidHolder> merged = Utils.mergeFluids(new ArrayList<>(), outputs);
        return tile.fluidHandler.map(t -> t.canOutputsFit(merged.toArray(FluidHolder[]::new))).orElse(false);
    }

    protected int maxSimultaneousRecipes(){
        return maxSimultaneousRecipes;
    }

    @Override
    public int getOverclock() {
        return 0;
    }

    @Override
    public long getPower() {
        return super.getPower() * (maxSimultaneousRecipes() > 1 ? 2 : 1);
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt = super.serialize();
        nbt.putInt("concurrentRecipes", concurrentRecipes);
        return nbt;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        concurrentRecipes = nbt.getInt("concurrentRecipes");
    }

    @Override
    public void getInfo(List<String> builder) {
        super.getInfo(builder);
        builder.add("Concurrent Recipes: " + concurrentRecipes);
        builder.add("Tick timer: " + tickTimer);
    }
}
