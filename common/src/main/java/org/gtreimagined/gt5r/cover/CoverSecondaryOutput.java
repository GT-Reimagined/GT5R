package org.gtreimagined.gt5r.cover;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import org.gtreimagined.gt5r.blockentity.single.BlockEntitySmallHeatExchanger;
import org.gtreimagined.gt5r.data.Materials;
import muramasa.antimatter.blockentity.BlockEntityCache;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

public class CoverSecondaryOutput extends BaseCover {
    public CoverSecondaryOutput(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        return true;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile() instanceof BlockEntitySmallHeatExchanger heatExchanger){
            if (heatExchanger.fluidHandler.isPresent()){
                MachineFluidHandler<?> fluidHandler = heatExchanger.fluidHandler.get();
                int outputfluid = fluidHandler.getOutputTanks().getFirstAvailableTank(Materials.Steam.getGas(1), true);
                if (outputfluid >= 0){
                    FluidTank outputTank = fluidHandler.getOutputTanks().getTank(outputfluid);
                    BlockEntityCache.getFluidHandlerCached(heatExchanger.getLevel(), heatExchanger.getBlockPos().relative(this.side), this.side.getOpposite()).ifPresent(f -> {
                        tryFluidTransfer(f, outputTank, Integer.MAX_VALUE * TesseractGraphWrappers.dropletMultiplier, true);
                    });
                }
            }
        }
    }

    public void tryFluidTransfer(PlatformFluidHandler fluidDestination, PlatformFluidHandler fluidSource, long maxAmount, boolean doTransfer) {
        for (int i = 0; i < fluidSource.getTankAmount(); i++) {
            FluidHolder fluid = fluidSource.getFluidInTank(i);
            FluidPlatformUtils.INSTANCE.tryFluidTransfer(fluidDestination, fluidSource, fluid.copyWithAmount(Math.min(fluid.getFluidAmount(), maxAmount)), doTransfer);
        }
    }
}
