package org.gtreimagined.gt5r.blockentity.single;

import org.gtreimagined.gt5r.machine.caps.SecondaryOutputCoverHandler;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class BlockEntitySecondaryOutput<T extends BlockEntitySecondaryOutput<T>> extends BlockEntityMachine<T> {
    public BlockEntitySecondaryOutput(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.coverHandler.set(() -> new SecondaryOutputCoverHandler<>((T)this));
    }

    public Direction getSecondaryOutputFacing() {
        return coverHandler.map(c -> ((SecondaryOutputCoverHandler<?>)c).getSecondaryOutputFacing()).orElse(this.getFacing().getOpposite());
    }

    public boolean setSecondaryOutput(Player player, BlockHitResult result){
        return coverHandler.map(c -> ((SecondaryOutputCoverHandler<?>)c).setSecondaryOutputFacing(player, Utils.getInteractSide(result))).orElse(false);
    }

    @Override
    public InteractionResult onInteractServer(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit, @Nullable AntimatterToolType type) {
        if (type == AntimatterDefaultTools.WRENCH_ALT) {
            if (setSecondaryOutput(player, hit)) {
                Utils.damageStack(player.getItemInHand(hand), hand, player);
                return InteractionResult.SUCCESS;
            }
        }
        return super.onInteractServer(state, world, pos, player, hand, hit, type);
    }
}
