package org.gtreimagined.gt5r.cover.base;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverBasicRedstoneOutput extends BaseCover {
    protected boolean inverted = false;
    int outputRedstone = 0;

    public CoverBasicRedstoneOutput(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    protected void setOutputRedstone(int outputRedstone) {
        boolean update = this.outputRedstone != outputRedstone;
        this.outputRedstone = outputRedstone;
        if (update) {
            markAndNotifySource();
            BlockPos neighbor = this.source().getTile().getBlockPos().relative(this.side);
            BlockState neighborState = this.source().getTile().getLevel().getBlockState(neighbor);
            this.source().getTile().getLevel().updateNeighborsAtExceptFromFacing(neighbor, neighborState.getBlock(), this.side.getOpposite());
        }
    }

    @Override
    public InteractionResult onInteract(Player player, InteractionHand hand, Direction side, @Nullable AntimatterToolType type) {
        if (type != null && type.getTag() == AntimatterDefaultTools.SCREWDRIVER.getTag()){
            inverted = !inverted;
            player.sendMessage(Utils.translatable("message.gt5r.redstone_mode." + (inverted ? "inverted" : "normal")), player.getUUID());
            return InteractionResult.SUCCESS;
        }
        return super.onInteract(player, hand, side, type);
    }

    @Override
    public int getWeakPower() {
        return outputRedstone;
    }

    @Override
    public int getStrongPower() {
        return outputRedstone;
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putBoolean("inverted", inverted);
        return nbt;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        inverted = nbt.getBoolean("inverted");
    }
}
