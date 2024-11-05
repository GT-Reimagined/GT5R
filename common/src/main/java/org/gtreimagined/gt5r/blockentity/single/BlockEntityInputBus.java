package org.gtreimagined.gt5r.blockentity.single;

import muramasa.antimatter.blockentity.multi.BlockEntityHatch;
import muramasa.antimatter.capability.item.TrackedItemHandler;
import muramasa.antimatter.capability.machine.MachineCoverHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.HatchMachine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BlockEntityInputBus extends BlockEntityHatch<BlockEntityInputBus> {
    boolean diversityFiltering = false;
    public BlockEntityInputBus(HatchMachine type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.itemHandler.set(() -> new MachineItemHandler<>(this){
            @Override
            protected TrackedItemHandler<BlockEntityInputBus> createTrackedHandler(SlotType<?> type, BlockEntityInputBus tile) {
                if (type == SlotType.IT_IN){
                    int count = tile.getMachineType().getCount(tile.getMachineTier(), type);
                    return new TrackedItemHandler<>(tile, type, count, type.output, type.input, type.tester){
                        @Override
                        public boolean hasSlotDiversity() {
                            return diversityFiltering;
                        }
                    };
                }
                return super.createTrackedHandler(type, tile);
            }

            @Override
            public boolean allowsInput(Direction side) {
                return side == coverHandler.map(MachineCoverHandler::getOutputFacing).orElse(null);
            }
        });
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        ItemStack stack = player.getItemInHand(hand);
        if (type == AntimatterDefaultTools.SCREWDRIVER){
            ICover instance = coverHandler.map(h -> h.get(Utils.getInteractSide(hit))).orElse(ICover.empty);
            if (!player.isCrouching()) {
                if (!instance.isEmpty() && instance.openGui(player, Utils.getInteractSide(hit))) {
                    Utils.damageStack(stack,hand, player);
                    return InteractionResult.SUCCESS;
                }
            }
            diversityFiltering = !diversityFiltering;
            player.sendMessage(Utils.translatable("tooltip.gt5r.diversity_filter." + (diversityFiltering ? "on" : "off")), player.getUUID());
            Utils.damageStack(stack, player);
            return InteractionResult.SUCCESS;
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }
}
