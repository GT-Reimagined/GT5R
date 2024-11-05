package org.gtreimagined.gt5r.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.machine.DefaultHeatHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static org.gtreimagined.gt5r.data.Materials.DistilledWater;
import static org.gtreimagined.gt5r.data.Materials.Steam;

public class BlockEntitySmallHeatExchanger extends BlockEntitySecondaryOutput<BlockEntitySmallHeatExchanger> {
    boolean hadNoWater = false;

    public BlockEntitySmallHeatExchanger(Machine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        heatHandler.set(() -> new DefaultHeatHandler(this, Integer.MAX_VALUE, 80, 0));
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){

            @Override
            protected boolean validateRecipe(IRecipe r) {
                return super.validateRecipe(r) && r.getSpecialValue() != 0;
            }

            @Override
            protected boolean canRecipeContinue() {
                return super.canRecipeContinue() && heatHandler.map(h -> h.getHeat() + (activeRecipe.getTotalPower()) <= h.getHeatCap()).orElse(false);
            }

            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                return tile.heatHandler.map(e -> e.insert((int) getPower() / 5, simulate) >= getPower() / 5).orElse(false);
            }

            @Override
            protected void calculateDurations() {
                maxProgress = activeRecipe.getDuration() * 5;
            }

            @Override
            public boolean accepts(FluidHolder stack) {
                return super.accepts(stack) || stack.getFluid() == Water.getLiquid() || stack.getFluid() == DistilledWater.getLiquid();
            }
        });
        fluidHandler.set(() -> new MachineFluidHandler<>(this){
            @Override
            public boolean canFluidBeAutoOutput(FluidHolder fluid) {
                return fluid.getFluid() != Steam.getGas();
            }
        });
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        if (level.getGameTime() % 20 == 0){
            fluidHandler.ifPresent(f -> {
                heatHandler.ifPresent(h -> {
                    if (h.getHeat() >= 2560){
                        Utils.createExplosion(this.level, worldPosition, 6.0F, Explosion.BlockInteraction.DESTROY);
                        return;
                    }
                    if (h.getHeat() >= 80){
                        int heatMultiplier = Math.min(6, h.getHeat() / 80);
                        int waterToExtract = 0;
                        int waterTankId = f.getInputTanks().getFirstAvailableTank(DistilledWater.getLiquid(1), true);
                        if (waterTankId < 0){
                            waterTankId = f.getInputTanks().getFirstAvailableTank(Water.getLiquid(1), true);
                        }
                        FluidTank waterTank;
                        if (waterTankId < 0){
                            waterTank = null;
                        } else {
                            waterTank = f.getInputTanks().getTank(waterTankId);
                        }
                        if (waterTank != null) {
                            waterToExtract = (int) Math.min(heatMultiplier, waterTank.getStoredFluid().getFluidAmount() / TesseractGraphWrappers.dropletMultiplier);
                        }
                        if (waterToExtract > 0){
                            if (hadNoWater){
                                Utils.createExplosion(this.level, worldPosition, 6.0F, Explosion.BlockInteraction.DESTROY);
                                return;
                            }
                            Material steam = Steam;
                            int waterMultiplier = 160;
                            int steamToAdd = waterToExtract  * waterMultiplier;
                            long inserted = f.getOutputTanks().internalInsert(steam.getGas(steamToAdd), true);
                            int successfulSteam = (int) ((inserted / TesseractGraphWrappers.dropletMultiplier) / 160);
                            if (successfulSteam >= 1){
                                waterToExtract = Math.min(waterToExtract, successfulSteam);
                                waterTank.internalExtract(Utils.ca(waterToExtract, waterTank.getStoredFluid()), false);
                                f.getOutputTanks().internalInsert(steam.getGas(waterToExtract * waterMultiplier), false);
                                h.extractInternal(waterToExtract * 80, false);
                            }
                            hadNoWater = false;
                        } else {
                            hadNoWater = true;
                        }
                    }
                });

            });
        }

    }
}
