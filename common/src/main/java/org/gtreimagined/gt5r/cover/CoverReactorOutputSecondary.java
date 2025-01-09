package org.gtreimagined.gt5r.cover;

import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import muramasa.antimatter.blockentity.BlockEntityCache;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.machine.MachineFluidHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.blockentity.single.BlockEntityNuclearReactorCore;
import org.jetbrains.annotations.Nullable;

public class CoverReactorOutputSecondary extends BaseCover {


    public CoverReactorOutputSecondary(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public boolean canPlace() {
        return super.canPlace() && handler.getTile() instanceof BlockEntityNuclearReactorCore;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return new ResourceLocation(GT5RRef.ID + ":block/cover/nuclear");
    }

    @Override
    public void onUpdate() {
        if (handler.getTile() instanceof BlockEntityNuclearReactorCore core){
            if (core.fluidHandler.isPresent()){
                MachineFluidHandler<?> fluidHandler = core.fluidHandler.get();
                FluidHolder inputfluid = fluidHandler.getInputTanks().getFluidInTank(0);
                if (inputfluid.getFluidAmount() > fluidHandler.getInputTanks().getTankCapacity(0) / 2){
                    long extra = inputfluid.getFluidAmount() - (fluidHandler.getInputTanks().getTankCapacity(0) / 2);
                    BlockEntityCache.getFluidHandlerCached(core.getLevel(), core.getBlockPos().relative(this.side), this.side.getOpposite()).ifPresent(f -> {
                        fluidHandler.drainInput(f.insertFluid(Utils.ca(extra, inputfluid), false), false);
                    });
                }
            }
        }
    }
}
