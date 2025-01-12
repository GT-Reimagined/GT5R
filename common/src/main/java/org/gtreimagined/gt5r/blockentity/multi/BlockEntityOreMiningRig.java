package org.gtreimagined.gt5r.blockentity.multi;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.capability.machine.MultiMachineEnergyHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gt5r.worldgen.PlayerPlacedBlockSavedData;
import tesseract.TesseractGraphWrappers;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;
import static org.gtreimagined.gt5r.blockentity.multi.BlockEntityDrillingRigBase.MineResult.*;
import static org.gtreimagined.gt5r.data.GT5RBlocks.MINING_PIPE;
import static org.gtreimagined.gt5r.data.GT5RBlocks.MINING_PIPE_THIN;

public class BlockEntityOreMiningRig extends BlockEntityDrillingRigBase<BlockEntityOreMiningRig> {
    static  ItemStack miningPickaxe = ItemStack.EMPTY;
    List<BlockPos> oresToMine = new ArrayList<>();
    int progress = 0;
    RunningState runningState = RunningState.MINING;
    ItemStack[] currentDrops = null;

    public BlockEntityOreMiningRig(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        if (miningPickaxe.isEmpty()) {
            miningPickaxe = Items.NETHERITE_PICKAXE.getDefaultInstance();
            EnchantmentHelper.setEnchantments(ImmutableMap.of(Enchantments.BLOCK_FORTUNE, 3), miningPickaxe);
        }
    }

    @Override
    protected MineResult mineBelowBlock(Level level, BlockPos pos, boolean dropBlock, ItemStack item) {
        BlockState blockstate = level.getBlockState(pos);
        BlockState aboveBlockState = level.getBlockState(pos.above());
        if (aboveBlockState.getBlock() != MINING_PIPE && pos.getY() + 1 != this.getBlockPos().getY()){
            resetMiningPos();
            return BlockEntityDrillingRigBase.MineResult.PIPE_BROKEN;
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
            return miningPipe ? FOUND_BOTTOM_MINING_PIPE : FOUND_BOTTOM;
        }
    }

    protected boolean mineBlock(Level level, BlockPos pos, boolean dropBlock, ItemStack item){
        BlockState state = level.getBlockState(pos);
        if (state.isAir()) return true;
        BlockEntity blockentity = state.hasBlockEntity() ? level.getBlockEntity(pos) : null;
        //BlockEve event = new BlockEvent.BreakEvent(level, pos, blockstate, entity instanceof Player player ? player : null);
        //MinecraftForge.EVENT_BUS.post(event);
            /*if (event.isCanceled()){
                return false;
            }*/
        if (dropBlock) {
            if (level instanceof ServerLevel serverLevel) {
                if (currentDrops != null){
                    itemHandler.ifPresent(i -> i.addOutputs(currentDrops));
                } else {
                    List<ItemStack> drops = Block.getDrops(state, serverLevel, pos, blockentity, null, item);
                    if (itemHandler.map(i -> i.canOutputsFit(drops.toArray(ItemStack[]::new))).orElse(false)){
                        itemHandler.ifPresent(i -> i.addOutputs(drops.toArray(ItemStack[]::new)));
                    } else {
                        drops.forEach(i -> Block.popResource(level, pos, i));
                    }
                }
                state.spawnAfterBreak(serverLevel, pos, ItemStack.EMPTY);
            }
        }
        return level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
    }

    @Override
    public ItemStack getMiningPickaxe() {
        return miningPickaxe;
    }

    @Override
    protected void run(Level level, BlockPos pos, BlockState state) {
        if (runningState == RunningState.FINISHED) return;
        fillMineListIfEmpty(level);
        if (!oresToMine.isEmpty()) {
            BlockPos ore = oresToMine.get(0);
            if (currentDrops == null) {
                BlockState state1 = level.getBlockState(ore);
                BlockEntity blockentity = state1.hasBlockEntity() ? level.getBlockEntity(ore) : null;
                currentDrops = Block.getDrops(state, (ServerLevel) level, pos, blockentity, null, getMiningPickaxe()).toArray(new ItemStack[0]);
            }
            if (!energyHandler.map(e -> e.getEnergy() >= euPerTick).orElse(false)){
                runningState = RunningState.OUT_OF_ENERGY;
                if (getMachineState() == MachineState.ACTIVE) setMachineState(MachineState.IDLE);
                if (progress > 0) progress = 0;
                return;
            }
            if (progress == cycle && !fluidHandler.map(f -> f.getInputTanks().extractFluid(Materials.DrillingFluid.getLiquid(100), true).getFluidAmount() == 100 * TesseractGraphWrappers.dropletMultiplier).orElse(false)){
                runningState = RunningState.OUT_OF_DRILLING_FLUID;
                if (getMachineState() == MachineState.ACTIVE) setMachineState(MachineState.IDLE);
                return;
            }
            if (progress == cycle && currentDrops != null && !itemHandler.map(i -> i.canOutputsFit(currentDrops)).orElse(false)){
                runningState = RunningState.FULL;
                if (getMachineState() == MachineState.ACTIVE) setMachineState(MachineState.IDLE);
                return;
            }
            if (getMachineState() == MachineState.IDLE) setMachineState(MachineState.ACTIVE);
            runningState = RunningState.MINING;
            energyHandler.ifPresent(e -> e.extractEu(euPerTick, false));
            if (progress < cycle) {
                progress++;
                return;
            }
            fluidHandler.ifPresent(f -> f.getInputTanks().extractFluid(Materials.DrillingFluid.getLiquid(100), false));
            mineBlock(level, ore, true, getMiningPickaxe());
            oresToMine.remove(0);
            progress = 0;
            currentDrops = null;
        } else {
            if (getMachineState() == MachineState.ACTIVE) setMachineState(MachineState.IDLE);
            BlockState blockState = level.getBlockState(miningPos);
            if (blockState.getBlock() == Blocks.BEDROCK || blockState.getBlock() == Blocks.VOID_AIR){
                runningState = RunningState.FINISHED;
                return;
            }
            foundBottom = false;
        }
    }

    @Override
    public void afterStructureFormed() {
        super.afterStructureFormed();
        this.energyHandler.ifPresent(e -> {
            int tier = ((MultiMachineEnergyHandler<?>) e).getAccumulatedPower().getIntegerId();
            this.euPerTick = 3 * (1 << (tier << 1));
            this.cycle = (int) (160 * (tier == 0 ? 2 : Math.pow(0.5, tier - 1))) * 3;
        });
    }

    private void fillMineListIfEmpty(Level level) {
        if (!oresToMine.isEmpty()) return;

        int startX = (miningPos.getX() >> 4) << 4;
        int startZ = (miningPos.getZ() >> 4) << 4;
        for (int x = startX - 16; x < (startX + 32); ++x)
            for (int z = startZ - 16; z < (startZ + 32); ++z) tryAddOreBlockToMineList(level,new BlockPos(x, miningPos.getY() + 1, z));
        /*if (mChunkLoadingEnabled) {

        } else {
            int radius = chunkRadiusConfig << 4;
            for (int xOff = -radius; xOff <= radius; xOff++) for (int zOff = -radius; zOff <= radius; zOff++)
                tryAddOreBlockToMineList(xDrill + xOff, yHead, zDrill + zOff);
        }*/
    }

    private void tryAddOreBlockToMineList(Level level, BlockPos pos) {
       BlockState blockstate = level.getBlockState(pos);
       if (!(level instanceof ServerLevel serverLevel)) return;
       if (PlayerPlacedBlockSavedData.getOrCreate(serverLevel).getPlacedPositions().contains(pos.asLong())) return;
       if (!blockstate.is(TagUtils.getForgelikeBlockTag("bedrock_ores")) && !blockstate.is(TagUtils.getForgelikeBlockTag("bedrock_small_ores")) &&
               (blockstate.is(TagUtils.getForgelikeBlockTag("ores")) || blockstate.is(TagUtils.getForgelikeBlockTag("small_ores")))){
           oresToMine.add(pos);
       }
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("progress", progress);
        tag.putInt("runningState", runningState.ordinal());
        if (currentDrops != null){
            ListTag drops = new ListTag();
            for (var drop : currentDrops) {
                drops.add(drop.save(new CompoundTag()));
            }
            tag.put("currentDrops", drops);
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        progress = nbt.getInt("progress");
        runningState = RunningState.values()[nbt.getInt("runningState")];
        if (nbt.contains("currentDrops")){
            ListTag drops = nbt.getList("currentDrops", 10);
            ItemStack[] stacks = new ItemStack[drops.size()];
            for (int i = 0; i < drops.size(); i++) {
                stacks[i] = ItemStack.of(drops.getCompound(i));
            }
            currentDrops = stacks;
        }
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return OreInfoWidget.build().setPos(10, 10);
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        OreInfoWidget oilInfoWidget = (OreInfoWidget) instance;
        renderer.draw(stack, this.getDisplayName().getString(), left, top, 0xFAFAFF);
        if (getMachineState() != MachineState.ACTIVE) {
            renderer.draw(stack, "Inactive.", left, top + 8, 0xFAFAFF);
            if (oilInfoWidget.runningState == RunningState.FINISHED) {
                renderer.draw(stack, "Finished mining to bedrock", left, top + 16, 0xFAFAFF);
                return 24;
            }
            return 16;
        } else if (instance.drawActiveInfo()) {
            if (oilInfoWidget.foundBottom){
                if (oilInfoWidget.runningState == RunningState.MINING) {
                    renderer.draw(stack, "Progress: " + instance.currentProgress + "/" + instance.maxProgress, left, top + 8, 0xFAFAFF);
                    renderer.draw(stack, "Ores left at y-level " + oilInfoWidget.currentPos.above().getY() + ": " + oilInfoWidget.oresLeft, left, top + 16, 0xFAFAFF);
                    return 24;
                }

            }
        }
        return 8;
    }

    public static class OreInfoWidget extends InfoRenderWidget.MultiRenderWidget {
        BlockPos currentPos;
        boolean stopped;
        boolean foundBottom;
        RunningState runningState;
        int oresLeft;


        protected OreInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            BlockEntityOreMiningRig m = (BlockEntityOreMiningRig) gui.handler;
            gui.syncLong(() -> m.miningPos.asLong(), l -> currentPos = BlockPos.of(l), SERVER_TO_CLIENT);
            gui.syncBoolean(() -> m.stopped, s -> stopped = s, SERVER_TO_CLIENT);
            gui.syncBoolean(() -> m.foundBottom, b -> foundBottom = b, SERVER_TO_CLIENT);
            gui.syncInt(() -> m.progress, i -> currentProgress = i, SERVER_TO_CLIENT);
            gui.syncInt(() -> m.cycle, i -> maxProgress = i, SERVER_TO_CLIENT);
            gui.syncInt(() -> m.runningState.ordinal(), i -> runningState = RunningState.values()[i], SERVER_TO_CLIENT);
            gui.syncInt(() -> m.oresToMine.size(), i -> oresLeft = i, SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new OreInfoWidget(a, b, (IInfoRenderer) a.handler));
        }
    }

    enum RunningState {
        MINING, FINISHED, OUT_OF_DRILLING_FLUID, FULL, OUT_OF_ENERGY
    }
}
