package org.gtreimagined.gt5r.blockentity.multi;

import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.Utils;
import muramasa.antimatter.util.int3;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.gui.ButtonOverlays;

import static org.gtreimagined.gt5r.data.GT5RBlocks.MINING_PIPE;
import static org.gtreimagined.gt5r.data.GT5RBlocks.MINING_PIPE_THIN;

public class BlockEntityDrillingRigBase<T extends BlockEntityDrillingRigBase<T>> extends BlockEntityMultiMachine<T> implements IMiningPipeTile, IFilterableHandler {
    protected boolean foundBottom = false;
    protected boolean stopped = false;
    protected boolean pullingUp;
    protected BlockPos miningPos;
    public BlockEntityDrillingRigBase(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        miningPos = new int3(pos, this.getFacing(state)).back(1).immutable().below();
    }

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        if (foundBottom){
            LongList positions = new LongArrayList();
            for (int y = miningPos.getY(); y < this.getBlockPos().getY(); y++) {
                positions.add(BlockPos.asLong(miningPos.getX(), y, miningPos.getZ()));
            }
            MiningPipeStructureCache.add(this.level, this.getBlockPos(), positions);
        }
    }

    @Override
    public void onRemove() {
        super.onRemove();
        MiningPipeStructureCache.remove(this.level, this.getBlockPos());
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("foundBottom", foundBottom);
        tag.putBoolean("pullingUp", pullingUp);
        tag.putLong("miningPos", miningPos.asLong());
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.foundBottom = nbt.getBoolean("foundBottom");
        this.pullingUp = nbt.getBoolean("pullingUp");
        this.miningPos = BlockPos.of(nbt.getLong("miningPos"));
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON) {
            int[] data = ((GuiEvents.GuiEvent)event).data;
            if (data[1] == 0) {
                pullingUp = !pullingUp;
                playerEntity.sendMessage(Utils.literal((pullingUp ? "Currently pulling up mining pipes" : "No longer pulling up mining pipes")), playerEntity.getUUID());
            }
        }
    }

    @Override
    public void addWidgets(GuiInstance instance, IGuiElement parent) {
        super.addWidgets(instance, parent);
        instance.addSwitchButton(152, 23, 18, 18, ButtonOverlays.PULL_UP_OFF, ButtonOverlays.PULL_UP_ON, h -> ((BlockEntityOilDrillingRig)h).pullingUp, false);
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        return slotType != SlotType.STORAGE || stack.getItem() == MINING_PIPE_THIN.asItem();
    }

    @Override
    public void onMiningPipeUpdate(BlockPos miningPipePos) {
        BlockState pipe = level.getBlockState(miningPipePos);
        if (pipe.getBlock() != MINING_PIPE && pipe.getBlock() != MINING_PIPE_THIN && !pullingUp){
            resetMiningPos();
        }
    }

    protected void resetMiningPos(){
        foundBottom = false;
        BlockPos centerPos = miningPos.atY(this.getBlockPos().getY()).below();
        while (true){
            BlockState state = level.getBlockState(centerPos);
            if (state.getBlock() == MINING_PIPE || state.getBlock() == MINING_PIPE_THIN){
                centerPos = centerPos.below();
                continue;
            }
            break;
        }
        miningPos = centerPos;
        MiningPipeStructureCache.remove(level, this.getBlockPos());
    }

    public enum MineResult {
        FOUND_BOTTOM, FOUND_OBSTRUCTION, FOUND_MINING_PIPE, FOUND_MINEABLE, PIPE_BROKEN
    }
}
