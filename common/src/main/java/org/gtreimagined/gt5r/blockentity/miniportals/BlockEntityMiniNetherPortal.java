package org.gtreimagined.gt5r.blockentity.miniportals;

import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gtcore.data.GTCoreTags;

import java.util.ArrayList;
import java.util.List;

public class BlockEntityMiniNetherPortal extends BlockEntityMiniPortal{
    public static List<BlockEntityMiniPortal>
            sListNetherSide = new ArrayList<>();
    public BlockEntityMiniNetherPortal(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    protected boolean isPortalSetter(ItemStack stack){
        return stack.is(GTCoreTags.FIRESTARTER);
    }

    @Override
    protected void playActivationSound(Player player){
        level.playSound(player, this.getBlockPos(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
    }

    @Override
    public List<BlockEntityMiniPortal> getPortalListA() {
        return sListWorldSide;
    }

    @Override
    public List<BlockEntityMiniPortal> getPortalListB() {
        return sListNetherSide;
    }

    @Override
    public void addThisPortalToLists() {
        if (level.dimension() == Level.OVERWORLD) {
            if (!sListWorldSide.contains(this)) sListWorldSide.add(this);
        } else if (level.dimension() == Level.NETHER) {
            if (!sListNetherSide.contains(this)) sListNetherSide.add(this);
        }
    }

    @Override
    protected void findTargetPortal() {
        otherSide = null;
        if (level != null && isServerSide()) {
            if (level.dimension() == Level.OVERWORLD) {
                long tShortestDistance = 128*128;
                for (BlockEntityMiniPortal tTarget : sListNetherSide) if (tTarget != this && !tTarget.isRemoved() && tTarget.isSame(this)) {
                    long tXDifference = getBlockPos().getX()-tTarget.getBlockPos().getX()*8, tZDifference = getBlockPos().getZ()-tTarget.getBlockPos().getZ()*8;
                    long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
                    if (tTempDist < tShortestDistance) {
                        tShortestDistance = tTempDist;
                        otherSide = tTarget;
                    } else if (tTempDist == tShortestDistance && (otherSide == null || Math.abs(tTarget.getBlockPos().getY()-getBlockPos().getY()) < Math.abs(otherSide.getBlockPos().getY()-getBlockPos().getY()))) {
                        otherSide = tTarget;
                    }
                }
            } else if (level.dimension() == Level.NETHER) {
                long tShortestDistance = 128*128;
                for (BlockEntityMiniPortal tTarget : sListWorldSide) if (tTarget != this && !tTarget.isRemoved() && tTarget.isSame(this)) {
                    long tXDifference = tTarget.getBlockPos().getX()-getBlockPos().getX()*8, tZDifference = tTarget.getBlockPos().getZ()-getBlockPos().getZ()*8;
                    long tTempDist = tXDifference * tXDifference + tZDifference * tZDifference;
                    if (tTempDist < tShortestDistance) {
                        tShortestDistance = tTempDist;
                        otherSide = tTarget;
                    } else if (tTempDist == tShortestDistance && (otherSide == null || Math.abs(tTarget.getBlockPos().getY()-getBlockPos().getY()) < Math.abs(otherSide.getBlockPos().getY()-getBlockPos().getY()))) {
                        otherSide = tTarget;
                    }
                }
            }
            if (otherSide != null){
                otherSide.setOtherSide(this);
                if (otherSide.getMachineState() != MachineState.ACTIVE){
                    otherSide.setMachineState(MachineState.ACTIVE);
                }
            }
        }
    }
}
