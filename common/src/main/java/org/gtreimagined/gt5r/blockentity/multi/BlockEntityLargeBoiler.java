package org.gtreimagined.gt5r.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.GT5Reimagined;
import org.gtreimagined.gt5r.block.BlockCasing;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gtcore.item.ItemSelectorTag;

import static muramasa.antimatter.machine.Tier.*;
import static org.gtreimagined.gt5r.data.Materials.DistilledWater;
import static org.gtreimagined.gt5r.data.Materials.Steam;

public class BlockEntityLargeBoiler extends BlockEntityMultiMachine<BlockEntityLargeBoiler> {

    public BlockEntityLargeBoiler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            private int euPerTick = 0;
            private int efficiency = 0;
            private int efficiencyIncrease;
            private int integratedCircuitConfig = 0; //Steam output is reduced by 1000L per config
            private int excessFuel = 0; //Eliminate rounding errors for fuels that burn half items
            private int excessProjectedEU = 0; //Eliminate rounding errors from throttling the boiler
            boolean explode = false;
            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                if (processingBlocked) return false;
                int tGeneratedEU = (int) (this.euPerTick * 2L * this.efficiency / 10000L);
                if (tGeneratedEU > 0 && !simulate) {
                    int amount = (tGeneratedEU + 160) / 160;
                    fluidHandler.ifPresent(f -> {
                        if (f.drainInput(AntimatterMaterials.Water.getLiquid(amount), false).getFluidAmount() == amount || f.drainInput(DistilledWater.getLiquid(amount), false).getFluidAmount() == amount) {
                            f.addOutputs(Steam.getGas(tGeneratedEU));
                            tile.onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
                        } else {
                            explode = true;
                        }
                    });

                }
                return true;
            }

            @Override
            public void checkRecipe() {
                super.checkRecipe();
                itemHandler.ifPresent(i -> {
                    ItemStack circuit = i.getHandler(SlotType.STORAGE).getItem(0);
                    if (circuit.getItem() instanceof ItemSelectorTag intCircuit){
                        if (intCircuit.circuitId > 0 && intCircuit.circuitId <= 24){
                            integratedCircuitConfig = intCircuit.circuitId;
                        }
                    } else {
                        if (integratedCircuitConfig != 0){
                            integratedCircuitConfig = 0;
                        }
                    }
                });
            }

            @Override
            public void onServerUpdate() {
                if (explode){
                    explodeMultiblock();
                    return;
                }
                super.onServerUpdate();
                if (tile.machineState == MachineState.ACTIVE && efficiency < 10000){
                    this.efficiency += efficiencyIncrease;
                    if (efficiency > 10000) efficiency = 10000;
                } else if (tile.machineState != MachineState.ACTIVE && efficiency > 0){
                    this.efficiency -= Math.min(efficiency, 1000);
                }
            }

            @Override
            protected void calculateDurations() {
                maxProgress = activeRecipe.getDuration();
                if (activeRecipe.hasInputItems()){
                    this.excessFuel += (int) activeRecipe.getPower();
                    this.maxProgress += this.excessFuel / 80;
                    this.excessFuel %= 80;
                }
                this.maxProgress = adjustBurnTimeForConfig(runtimeBoost(maxProgress));
                this.euPerTick = adjustEUtForConfig(getEUt());
                this.efficiencyIncrease = getEfficiencyIncrease() * Math.max(activeRecipe.getSpecialValue(), 1);
            }

            @Override
            public void resetRecipe() {
                super.resetRecipe();
                this.euPerTick = 0;
                this.efficiencyIncrease = 0;
            }

            private int adjustEUtForConfig(int rawEUt) {
                int adjustedSteamOutput = rawEUt - 25 * integratedCircuitConfig;
                return Math.max(adjustedSteamOutput, 25);
            }

            private int adjustBurnTimeForConfig(int rawBurnTime) {
                if (efficiency < 10000) {
                    return rawBurnTime;
                }
                int adjustedEUt = Math.max(25, getEUt() - 25 * integratedCircuitConfig);
                int adjustedBurnTime = rawBurnTime * getEUt() / adjustedEUt;
                this.excessProjectedEU += getEUt() * rawBurnTime - adjustedEUt * adjustedBurnTime;
                adjustedBurnTime += this.excessProjectedEU / adjustedEUt;
                this.excessProjectedEU %= adjustedEUt;
                return adjustedBurnTime;
            }

            @Override
            public void onMachineEvent(IMachineEvent event, Object... data) {
                super.onMachineEvent(event, data);
                if (event == SlotType.STORAGE){
                    checkRecipe();
                }
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag tag = super.serialize();
                tag.putInt("excessProjectedEu", this.excessProjectedEU);
                tag.putInt("excessFuel", excessFuel);
                tag.putInt("efficiency", efficiency);
                return tag;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                this.excessProjectedEU = nbt.getInt("excessProjectedEu");
                this.excessFuel = nbt.getInt("excessFuel");
                this.efficiency = nbt.getInt("efficiency");
            }
        });
    }

    public BlockBasic getCasing(){
        if (tier == LV){
            return GT5RBlocks.CASING_BRONZE_PLATED_BRICK;
        } else if (tier == MV){
            return GT5RBlocks.CASING_SOLID_STEEL;
        } else if (tier == HV){
            return GT5RBlocks.CASING_TITANIUM;
        }
        return GT5RBlocks.CASING_TUNGSTENSTEEL;
    }

    public int getEUt(){
        if (tier == LV){
            return 400;
        } else if (tier == MV){
            return 600;
        } else if (tier == HV){
            return 800;
        }
        return 1000;
    }

    public int getEfficiencyIncrease(){
        if (tier == LV){
            return 16;
        } else if (tier == MV){
            return 12;
        } else if (tier == HV){
            return 8;
        }
        return 4;
    }

    int runtimeBoost(int time) {
        if (tier == LV) return time * 2;
        int dividend = tier == MV ? 150 : tier == HV ? 130 : 120;
        return time * dividend / 100;
    }

    public Block getFireboxCasing(){
        if (tier == LV){
            return GT5RBlocks.CASING_FIREBOX_BRONZE;
        } else if (tier == MV){
            return GT5RBlocks.CASING_FIREBOX_STEEL;
        } else if (tier == HV){
            return GT5RBlocks.CASING_FIREBOX_TITANIUM;
        }
        return GT5RBlocks.CASING_FIREBOX_TUNGSTENSTEEL;
    }

    public Block getPipeCasing(){
        if (tier == LV){
            return GT5RBlocks.CASING_PIPE_BRONZE;
        } else if (tier == MV){
            return GT5RBlocks.CASING_PIPE_STEEL;
        } else if (tier == HV){
            return GT5RBlocks.CASING_PIPE_TITANIUM;
        }
        return GT5RBlocks.CASING_PIPE_TUNGSTENSTEEL;
    }

    public Texture getTextureForHatches(Direction dir, BlockPos hatchPos){
        if (hatchPos.getY() != this.getBlockPos().getY()) return super.getTextureForHatches(dir, hatchPos);
        String prefix = tier == LV ? "bronze" : tier == MV ? "steel" : tier == HV ? "titanium" : "tungstensteel";
        return new Texture(GT5RRef.ID, "block/casing/" + prefix + "_firebox");
    }

    @Override
    public ITextureProvider getHatchBlock(BlockPos hatchPos) {
        if (hatchPos.getY() != this.getBlockPos().getY()) return this.getCasing();
        String prefix = tier == LV ? "bronze" : tier == MV ? "steel" : tier == HV ? "titanium" : "tungstensteel";
        return GT5Reimagined.get(BlockCasing.class, prefix + "_firebox_casing");
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        renderer.draw(stack, this.getDisplayName().getString(), left, top, 0xFAFAFF);
        if (getMachineState() != MachineState.ACTIVE) {
            renderer.draw(stack, "Inactive.", left, top + 8, 0xFAFAFF);
            return 16;
        } else if (instance.drawActiveInfo()) {
            renderer.draw(stack, "Progress: " + instance.currentProgress + "/" + instance.maxProgress, left, top + 8, 0xFAFAFF);
            renderer.draw(stack, "Overclock: " + instance.overclock, left, top + 16, 0xFAFAFF);
            renderer.draw(stack, "EU/t: " + instance.euT, left, top + 24, 0xFAFAFF);
            return 32;
        }
        return 8;
    }

}
