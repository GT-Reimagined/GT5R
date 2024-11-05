package org.gtreimagined.gt5r.blockentity.single;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BlockEntityRockBreaker extends BlockEntityMachine<BlockEntityRockBreaker> {
    public BlockEntityRockBreaker(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<BlockEntityRockBreaker>(this){
            @Override
            protected boolean canRecipeContinue() {
                return super.canRecipeContinue() && this.hasWaterAndLava();
            }

            public boolean hasWaterAndLava() {
                boolean water = false;
                boolean lava = false;
                for(Direction direction : Direction.Plane.HORIZONTAL) {
                    if (this.tile.level.isWaterAt(this.tile.getBlockPos().relative(direction))) water = true;
                    if (this.tile.level.getFluidState(this.tile.getBlockPos().relative(direction)).getType() == Fluids.LAVA) lava = true;
                }
                return water && lava;
            }
        });
    }

    @Override
    public void onBlockUpdate(BlockPos neighbor) {
        super.onBlockUpdate(neighbor);
        this.recipeHandler.ifPresent(MachineRecipeHandler::checkRecipe);
    }
}