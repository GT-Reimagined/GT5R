package org.gtreimagined.gt5r.blockentity.multi;

import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.registration.ITextureProvider;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityTreeGrowthSimulator extends BlockEntityMultiMachine<BlockEntityTreeGrowthSimulator> {
    public BlockEntityTreeGrowthSimulator(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipe findRecipe() {
                IRecipe recipe = super.findRecipe();
                /*if (recipe == null) {
                    ExtendedItemContainer container = itemHandler.map(i -> i.getInputHandler()).orElse(null);
                    if (container != null && container.getContainerSize() > 0){
                        for (int i = 0; i < container.getContainerSize(); i++) {
                            ItemStack stack = container.getItem(i);
                            if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof SaplingBlock){
                                ResourceLocation id = AntimatterPlatformUtils.INSTANCE.getIdFromBlock(blockItem.getBlock());
                                ResourceLocation logId = new ResourceLocation(id.getNamespace(), id.getPath().replace("_sapling", "_log"));
                                if (AntimatterPlatformUtils.INSTANCE.blockExists(logId)){
                                    return RecipeMaps.TREE_GROWTH_SIMULATOR.RB().recipeMapOnly().ii(RecipeIngredient.of(stack.getItem(), 1).setNoConsume()).io(new ItemStack(AntimatterPlatformUtils.INSTANCE.getItemFromID(logId), 10)).add(id.getPath(), 10, 16);
                                }
                            }
                        }
                    }
                }*/
                return recipe;
            }
        });
    }

    @Override
    public ITextureProvider getHatchBlock(BlockPos pos) {
        if (pos.getY() == this.getBlockPos().getY()) return GT5RBlocks.CASING_BLACK_BRONZE;
        return super.getHatchBlock(pos);
    }
}
