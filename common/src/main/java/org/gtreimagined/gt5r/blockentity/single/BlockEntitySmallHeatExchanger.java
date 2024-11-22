package org.gtreimagined.gt5r.blockentity.single;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.fluid.FluidTanks;
import muramasa.antimatter.capability.machine.DefaultHeatHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.util.CodeUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.machine.HeatExchangerMachine;
import org.gtreimagined.gtcore.data.GTCoreTags;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.data.AntimatterMaterials.Water;
import static org.gtreimagined.gt5r.data.Materials.DistilledWater;
import static org.gtreimagined.gt5r.data.Materials.Steam;

public class BlockEntitySmallHeatExchanger extends BlockEntitySecondaryOutput<BlockEntitySmallHeatExchanger> {
    boolean hadNoWater = false;
    int rate;
    int efficiency;

    public BlockEntitySmallHeatExchanger(HeatExchangerMachine type, BlockPos pos, BlockState state, int rate, int efficiency) {
        super(type, pos, state);
        this.rate = rate;
        this.efficiency = efficiency;
        heatHandler.set(() -> new DefaultHeatHandler(this, Integer.MAX_VALUE, 80, 0));
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){

            @Override
            protected boolean validateRecipe(IRecipe r) {
                return super.validateRecipe(r) && r.getSpecialValue() != 0;
            }

            @Override
            protected boolean canRecipeContinue() {
                return super.canRecipeContinue() && heatHandler.map(h -> h.getHeat() < rate * 2).orElse(false);
            }

            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                if (activeRecipe == null) return false;
                if (currentProgress > 0) return true;
                long totalPower = CodeUtils.units(activeRecipe.getTotalPower(), 10000, efficiency, false);
                return tile.heatHandler.map(e -> e.insertInternal((int) totalPower, simulate) >= totalPower).orElse(false);
            }

            @Override
            public boolean accepts(FluidHolder stack) {
                return super.accepts(stack) || stack.getFluid() == Water.getLiquid() || stack.getFluid() == DistilledWater.getLiquid();
            }
        });
        fluidHandler.set(() -> new SmallHeatExchangerFluidHandler(this));
    }

    int steamHeat = 0;

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        heatHandler.ifPresent(h -> {
            if (h.getHeat() > 0){
                int tempRate = Math.min(h.getHeat(), rate);
                steamHeat += tempRate;
                h.extractInternal(tempRate, false);
            }
        });
        if (level.getGameTime() % 20 == 0){
            fluidHandler.ifPresent(f -> {
                if (steamHeat >= 2560){
                    Utils.createExplosion(this.level, worldPosition, 6.0F, Explosion.BlockInteraction.DESTROY);
                    return;
                }
                if (steamHeat >= 80){
                    int heatMultiplier = Math.min(6, steamHeat / 80);
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
                            steamHeat -= waterToExtract * 80;
                        }
                        hadNoWater = false;
                    } else {
                        hadNoWater = true;
                    }
                }
            });
        }

    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        steamHeat = tag.getInt("steamHeat");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("steamHeat", steamHeat);
    }

    public static class SmallHeatExchangerFluidHandler extends MachineFluidHandler<BlockEntitySmallHeatExchanger> {
        public SmallHeatExchangerFluidHandler(BlockEntitySmallHeatExchanger tile) {
            super(tile);
            tanks.put(FluidDirection.INPUT, FluidTanks.create(tile, SlotType.FL_IN, b -> {
                b.tank(this::acceptWater, 1000);
                b.tank(this::acceptsRecipe, 4000);
                return b;
            }));
            tanks.put(FluidDirection.OUTPUT, FluidTanks.create(tile, SlotType.FL_OUT, b -> {
                b.tank(f -> !f.getFluid().is(GTCoreTags.STEAM), 1000);
                b.tank(f -> f.getFluid().is(GTCoreTags.STEAM), 16000);
                return b;
            }));
        }

        public boolean acceptsRecipe(FluidHolder stack) {
            return tile.recipeHandler.map(t -> t.accepts(stack)).orElse(true);
        }

        public boolean acceptWater(FluidHolder stack) {
            return stack.getFluid() == Water.getLiquid() || stack.getFluid() == DistilledWater.getLiquid();
        }

        @Override
        public boolean canFluidBeAutoOutput(FluidHolder fluid) {
            return fluid.getFluid() != Steam.getGas();
        }
    }
}
