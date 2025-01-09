package org.gtreimagined.gt5r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.machine.caps.ParallelRecipeHandler;

public class BlockEntityIUpgradedBatchMachine extends BlockEntityMachine<BlockEntityIUpgradedBatchMachine> {
    public BlockEntityIUpgradedBatchMachine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new ParallelRecipeHandler<>(this, 1){
            @Override
            protected int maxSimultaneousRecipes() {
                return 1 << (tier.getIntegerId() - 1);
            }

            @Override
            public long getPower() {
                if (activeRecipe == null) return 0;
                long power = activeRecipe.getPower();
                return power * (concurrentRecipes == 0 ? 1 : maxSimultaneousRecipes());
            }
        });
    }
}
