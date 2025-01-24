package org.gtreimagined.gt5r.cover;

import earth.terrarium.botarium.common.fluid.base.PlatformFluidHandler;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.pipe.BlockEntityFluidPipe;
import muramasa.antimatter.capability.FluidHandler;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.data.Materials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CoverAirVent extends BaseCover {
    public static String ID = "air_vent";
    public CoverAirVent(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public String getDomain() {
        return GT5RRef.ID;
    }

    @Override
    public void onUpdate() {
        BlockEntity tile = handler.getTile();
        if (tile == null) {
            return;
        }
        if (tile.getLevel().isClientSide) return;
        Level level = tile.getLevel();
        Optional<PlatformFluidHandler> cap = Optional.empty();
        if (tile instanceof BlockEntityFluidPipe<?> pipe){
            cap = pipe.getPipeCapHolder().side(side);
        } else if (tile instanceof BlockEntityMachine<?> machine){
            cap = machine.fluidHandler.map(FluidHandler::getInputTanks);
        }
        BlockPos offset = tile.getBlockPos().relative(side);
        BlockState state = level.getBlockState(offset);
        if (state.isAir() && cap.isPresent()){
            if (level.getGameTime() % 360 == (30 + (60L * side.get3DDataValue()))){
                if (level.dimension() == Level.OVERWORLD){
                    cap.get().insertFluid(Materials.Air.getGas(64000), false);
                } else if (level.dimension() == Level.NETHER){
                    cap.get().insertFluid(Materials.NetherAir.getGas(64000), false);
                } else if (level.dimension() == Level.END){
                    cap.get().insertFluid(Materials.EnderAir.getGas(64000), false);
                }
            }
        }
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    protected String getRenderId() {
        return ID;
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }
}
