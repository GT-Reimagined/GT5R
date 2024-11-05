package org.gtreimagined.gt5r.cover;

import org.gtreimagined.gt5r.cover.base.CoverBasicRedstoneOutput;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.api.gt.IEnergyHandler;

public class CoverEnergyDetector extends CoverBasicRedstoneOutput {
    public CoverEnergyDetector(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public boolean canPlace() {
        return handler.getTile() instanceof BlockEntityMachine<?> machine && machine.energyHandler.isPresent();
    }

    @Override
    public String getId() {
        return "energy_detector";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel() == null || handler.getTile().getLevel().isClientSide) return;
        if (handler.getTile() instanceof BlockEntityMachine<?> machine && machine.energyHandler.isPresent()){
            IEnergyHandler energyHandler = machine.energyHandler.get();
            long scale = energyHandler.getCapacity() / 15L;
            if (scale > 0){
                setOutputRedstone(inverted ? (int) (15L - energyHandler.getEnergy() / scale) : (int) (energyHandler.getEnergy() / scale));
            } else {
                setOutputRedstone(inverted ? 15 : 0);
            }
        }
    }
}
