package org.gtreimagined.gt5r.cover.redstone;

import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.tool.AntimatterToolType;
import org.gtreimagined.gt5r.cover.base.CoverBasicRedstoneOutput;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverActivityDetectorSuccessful extends CoverBasicRedstoneOutput {
    boolean redstoneNextTick = false;

    public CoverActivityDetectorSuccessful(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public InteractionResult onInteract(Player player, InteractionHand hand, Direction side, @Nullable AntimatterToolType type) {
        return InteractionResult.PASS;
    }

    @Override
    public void onUpdate() {
        if (this.redstoneNextTick) {
            this.redstoneNextTick = false;
            setOutputRedstone(15);
            return;
        }
        if (this.getStrongPower() > 0 && !redstoneNextTick){
            setOutputRedstone(0);
        }
    }

    @Override
    public void onMachineEvent(IGuiHandler tile, IMachineEvent event, int... data) {
        if (event == MachineEvent.ITEMS_OUTPUTTED || event == MachineEvent.FLUIDS_OUTPUTTED){
            redstoneNextTick = true;
        }
    }
}
