package org.gtreimagined.gt5r.machine.caps;

import muramasa.antimatter.Ref;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineCoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.ICover;
import net.minecraft.sounds.SoundSource;
import org.gtreimagined.gt5r.machine.ISecondaryOutputMachine;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class SecondaryOutputCoverHandler<T extends BlockEntityMachine<T>> extends MachineCoverHandler<T> {
    public SecondaryOutputCoverHandler(T tile) {
        super(tile);
    }

    private CoverFactory getSecondaryOutputCoverFactory() {
        if (getTile().getMachineType() instanceof ISecondaryOutputMachine machine){
            return machine.getSecondaryOutputCover();
        }
        return ICover.emptyFactory;
    }

    public Direction getSecondaryOutputFacing() {
        return lookupSingle(getSecondaryOutputCoverFactory());
    }

    public ICover getSecondaryOutputCover() {
        return get(lookupSingle(getSecondaryOutputCoverFactory()));
    }

    public boolean setSecondaryOutputFacing(Player entity, Direction side) {
        Direction dir = getSecondaryOutputFacing();
        if (dir == null && getSecondaryOutputCoverFactory() == ICover.emptyFactory) return false;
        if (side == dir) return false;
        boolean ok = side == getOutputFacing() ? switchCovers(entity) : dir != null ? moveCover(entity, dir, side) : set(side, getSecondaryOutputCoverFactory().get().get(this, null, side, getSecondaryOutputCoverFactory()), true);
        if (ok) {
            getTile().invalidateCaps();
        }
        return ok;
    }

    @Override
    public boolean setOutputFacing(Player entity, Direction side) {
        if (side == getSecondaryOutputFacing()){
            boolean ok = switchCovers(entity);
            if (ok) getTile().invalidateCaps();
            return ok;
        }
        return super.setOutputFacing(entity, side);
    }

    private boolean switchCovers(Player player){
        ICover secondaryOutput = getSecondaryOutputCover();
        ICover output = getOutputCover();
        Direction outputFacing = getOutputFacing();
        Direction secondOutputFacing = getSecondaryOutputFacing();
        CoverFactory outputFactory = output.getFactory();
        ICover outputCopy = outputFactory.get().get(this, output.getTier(), secondOutputFacing, outputFactory);
        outputCopy.deserialize(output.serialize());
        CoverFactory secondOutputFactory = secondaryOutput.getFactory();
        ICover secondOutputCopy = secondOutputFactory.get().get(this, secondaryOutput.getTier(), outputFacing, secondOutputFactory);
        secondOutputCopy.deserialize(secondaryOutput.serialize());
        boolean ok1 = set(secondOutputFacing, secondaryOutput, outputCopy, true);
        if (!ok1) return false;
        boolean ok2 = set(outputFacing, output, secondOutputCopy, true);
        if (ok2){
            sync();
            player.getLevel().playSound(null, this.getTile().getBlockPos(), Ref.WRENCH, SoundSource.BLOCKS, 1.0f, 1.0f);
        }
        return ok2;
    }

    @Override
    protected boolean canRemoveCover(ICover cover) {
        return super.canRemoveCover(cover) && cover.getFactory() != getSecondaryOutputCoverFactory();
    }

    @Override
    protected boolean isCoverDefault(ICover cover) {
        return super.isCoverDefault(cover) || cover.getFactory() == getSecondaryOutputCoverFactory();
    }

    @Override
    public boolean isValid(@NotNull Direction side, @NotNull ICover replacement) {
        if (!validCovers.contains(replacement.getLoc())) return false;
        if (side == getOutputFacing()) return false;
        return (get(side).isEmpty() && !replacement.isEmpty()) || super.isValid(side, replacement);
    }
}
