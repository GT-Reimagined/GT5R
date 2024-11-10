package org.gtreimagined.gt5r.cover;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverMuffler extends BaseCover {
    public CoverMuffler(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public boolean canPlace() {
        return handler.getTile() instanceof BlockEntityMachine<?> && !handler.hasCover(this.factory);
    }

    @Override
    public void onPlace() {
        if (handler.getTile() instanceof BlockEntityMachine<?> machine){
            if (!machine.isMuffled()){
                machine.setMuffled(true);
            }
        }
    }

    @Override
    public void onRemove() {
        if (handler.getTile() instanceof BlockEntityMachine<?> machine){
            if (machine.isMuffled()){
                machine.setMuffled(false);
            }
        }
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
       return getBasicModel();
    }
}
