package org.gtreimagined.gt5r.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import it.unimi.dsi.fastutil.longs.LongArrayList;
import it.unimi.dsi.fastutil.longs.LongList;
import muramasa.antimatter.capability.machine.MultiMachineEnergyHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.event.MachineEvent;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gt5r.worldgen.OilSpoutEntry;
import org.gtreimagined.gt5r.worldgen.OilSpoutSavedData;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import java.util.List;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;
import static org.gtreimagined.gt5r.blockentity.multi.BlockEntityDrillingRigBase.MineResult.*;
import static org.gtreimagined.gt5r.data.GT5RBlocks.MINING_PIPE;
import static org.gtreimagined.gt5r.data.GT5RBlocks.MINING_PIPE_THIN;

public class BlockEntityOilDrillingRig extends BlockEntityDrillingRigBase<BlockEntityOilDrillingRig> {
    int progress = 0;
    OilSpoutEntry oilEntry = null;

    public BlockEntityOilDrillingRig(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    int outputTicker = 0;

    @Override
    public void run(Level level, BlockPos pos, BlockState state) {
        if (oilEntry == null){
            oilEntry = OilSpoutSavedData.getOrCreate((ServerLevel) level).getFluidVeinWorldEntry(SectionPos.blockToSectionCoord(this.miningPos.getX()), SectionPos.blockToSectionCoord(this.miningPos.getZ()));
        }
        if (oilEntry.getFluid() == null) return;
        FluidHolder fluidHolder = FluidPlatformUtils.createFluidStack(oilEntry.getFluid().fluid(), oilEntry.getCurrentYield() * TesseractGraphWrappers.dropletMultiplier);
        if (outputTicker > 0){
            outputTicker--;
            return;
        }
        if (progress == 0){
            if (!fluidHandler.map(f -> f.fillOutput(fluidHolder, true) == oilEntry.getCurrentYield() * TesseractGraphWrappers.dropletMultiplier).orElse(false)){
                outputTicker = 40;
                this.setMachineState(MachineState.IDLE);
                return;
            }
        }
        if (getMachineState() != MachineState.ACTIVE){
            setMachineState(MachineState.ACTIVE);
        }
        energyHandler.ifPresent(e -> e.extractEu(euPerTick, false));
        if (++progress == cycle){
            progress = 0;
            if (fluidHandler.map(f -> f.fillOutput(fluidHolder, true) == oilEntry.getCurrentYield() * TesseractGraphWrappers.dropletMultiplier).orElse(false)){
                fluidHandler.ifPresent(f -> f.fillOutput(fluidHolder, false));
                onMachineEvent(MachineEvent.FLUIDS_OUTPUTTED);
                oilEntry.decreaseLevel();
            }
        }
    }

    protected MineResult mineBelowBlock(Level level, BlockPos pos, boolean dropBlock, ItemStack item) {
        BlockState blockstate = level.getBlockState(pos);
        BlockState aboveBlockState = level.getBlockState(pos.above());
        if (aboveBlockState.getBlock() != MINING_PIPE && pos.getY() + 1 != this.getBlockPos().getY()){
            resetMiningPos();
            return PIPE_BROKEN;
        }
        if (blockstate.getDestroySpeed(level, pos) < 0) {
            return FOUND_OBSTRUCTION;
        } else {
            if (!(blockstate.getBlock() instanceof BaseFireBlock)) {
                level.levelEvent(2001, pos, Block.getId(blockstate));
            }
            boolean miningPipe = blockstate.getBlock() == MINING_PIPE || blockstate.getBlock() == MINING_PIPE_THIN;

            if (!miningPipe){
                if (!mineBlock(level, pos, dropBlock, item)) {
                    return FOUND_OBSTRUCTION;
                }
            }
            boolean flag = blockstate.getBlock() == MINING_PIPE || level.setBlock(pos, MINING_PIPE.defaultBlockState(), 3, 512);
            if (flag && pos.getY() + 1 < this.getBlockPos().getY()) {
                level.setBlock(pos.above(), MINING_PIPE_THIN.defaultBlockState(), 11);
            }
            BlockState belowBlockState = level.getBlockState(pos.below());
            if (belowBlockState.getBlock() == Blocks.BEDROCK || belowBlockState.getBlock() == Blocks.VOID_AIR){
                return miningPipe ? FOUND_BOTTOM_MINING_PIPE : FOUND_BOTTOM;
            }
            return miningPipe ? FOUND_MINING_PIPE : FOUND_MINEABLE;
        }
    }

    @Override
    public void afterStructureFormed() {
        super.afterStructureFormed();
        this.energyHandler.ifPresent(e -> {
            int tier = ((MultiMachineEnergyHandler<?>) e).getAccumulatedPower().getIntegerId();
            this.euPerTick = 3 * (1 << (tier << 1));
            this.cycle = (int) (160 * (tier == 0 ? 2 : Math.pow(0.5, tier - 1)));
        });
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("progress", progress);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.progress = nbt.getInt("progress");
    }


    @Override
    public WidgetSupplier getInfoWidget() {
        return OilInfoWidget.build().setPos(10, 10);
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        OilInfoWidget oilInfoWidget = (OilInfoWidget) instance;
        renderer.draw(stack, this.getDisplayName().getString(), left, top, 0xFAFAFF);
        if (getMachineState() != MachineState.ACTIVE) {
            renderer.draw(stack, "Inactive.", left, top + 8, 0xFAFAFF);
            return 16;
        } else if (instance.drawActiveInfo()) {
            if (oilInfoWidget.foundBottom){
                renderer.draw(stack, "Progress: " + instance.currentProgress + "/" + instance.maxProgress, left, top + 8, 0xFAFAFF);
                return 16;
            } else if (oilInfoWidget.stopped && oilInfoWidget.currentPos != null){
                renderer.draw(stack, "Can't mine at: " + oilInfoWidget.currentPos, left, top + 8, 0xFAFAFF);
                renderer.draw(stack, "Y: " + oilInfoWidget.currentPos.getY(), left, top + 16, 0xFAFAFF);
                return 24;
            } else if (oilInfoWidget.currentPos != null){
                renderer.draw(stack, "Mining Position at: ", left, top + 8, 0xFAFAFF);
                renderer.draw(stack, "Y: " + oilInfoWidget.currentPos.getY(), left, top + 16, 0xFAFAFF);
                return 24;
            }
        }
        return 8;
    }

    public static class OilInfoWidget extends InfoRenderWidget.MultiRenderWidget {
        BlockPos currentPos;
        boolean stopped;
        boolean foundBottom;


        protected OilInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            BlockEntityOilDrillingRig m = (BlockEntityOilDrillingRig) gui.handler;
            gui.syncLong(() -> m.miningPos.asLong(), l -> currentPos = BlockPos.of(l), SERVER_TO_CLIENT);
            gui.syncBoolean(() -> m.stopped, s -> stopped = s, SERVER_TO_CLIENT);
            gui.syncBoolean(() -> m.foundBottom, b -> foundBottom = b, SERVER_TO_CLIENT);
            gui.syncInt(() -> m.progress, i -> currentProgress = i, SERVER_TO_CLIENT);
            gui.syncInt(() -> m.cycle, i -> maxProgress = i, SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new OilInfoWidget(a, b, (IInfoRenderer) a.handler));
        }
    }
}
