package org.gtreimagined.gt5r.cover.redstone;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import org.gtreimagined.gt5r.cover.base.CoverBasicRedstoneOutput;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverProgressSensor extends CoverBasicRedstoneOutput {
    public CoverProgressSensor(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public boolean canPlace() {
        return handler.getTile() instanceof BlockEntityMachine<?> machine && machine.recipeHandler.side(side).isPresent();
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel() == null || handler.getTile().getLevel().isClientSide) return;
        MachineRecipeHandler<?> recipeHandler = handler.getTile() instanceof BlockEntityMachine<?> machine ? machine.recipeHandler.side(side).orElse(null) : null;
        if (recipeHandler != null){
            long scale = recipeHandler.getMaxProgress() > 0 ? recipeHandler.getMaxProgress() / 15L : 0;
            long currentProgress = recipeHandler.getCurrentProgress();
            if (scale > 0){
                setOutputRedstone(inverted ? (int) (15L - currentProgress / scale) : (int) (currentProgress / scale));
            } else {
                setOutputRedstone(inverted ? 15 : 0);
            }
        }
    }
}
