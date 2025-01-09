package org.gtreimagined.gt5r.cover;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.CoverOutput;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.blockentity.single.BlockEntityNuclearReactorCore;
import org.jetbrains.annotations.Nullable;

public class CoverReactorOutput extends CoverOutput {


    public CoverReactorOutput(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
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
    public boolean shouldOutputFluids() {
        return true;
    }
}
