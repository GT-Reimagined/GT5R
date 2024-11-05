package org.gtreimagined.gt5r.cover.redstone;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tool.AntimatterToolType;
import org.gtreimagined.gt5r.cover.base.CoverBasicRedstoneOutput;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverRedstoneConductorEmit extends CoverBasicRedstoneOutput {
    public CoverRedstoneConductorEmit(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public void onUpdate() {
        int newPower = 0;
        for (Direction direction : Direction.values()) {
            if (direction == this.side) continue;
            ICover cover = source().get(direction);
            if (!(cover instanceof CoverRedstoneConductorAccept accept)) continue;
            if (newPower < accept.getRedstonePower()){
                newPower = accept.getRedstonePower();
            }
        }
        setOutputRedstone(newPower);
    }

    @Override
    public InteractionResult onInteract(Player player, InteractionHand hand, Direction side, @Nullable AntimatterToolType type) {
        return InteractionResult.PASS;
    }
}
