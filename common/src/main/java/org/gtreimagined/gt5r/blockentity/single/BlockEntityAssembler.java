package org.gtreimagined.gt5r.blockentity.single;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.serializer.MachineRecipeSerializer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.RecipeMaps;
import tesseract.TesseractGraphWrappers;
import tesseract.api.item.ExtendedItemContainer;

import static org.gtreimagined.gt5r.data.Materials.Glue;

public class BlockEntityAssembler extends BlockEntityMachine<BlockEntityAssembler> {
    public BlockEntityAssembler(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipe findRecipe() {
                IRecipe recipe = super.findRecipe();
                if (recipe == null){
                    ExtendedItemContainer container = itemHandler.get().getInputHandler();
                    ItemStack printedPages = ItemStack.EMPTY;
                    boolean leather = false;
                    for (int i = 0; i < container.getContainerSize(); i++) {
                        ItemStack stack = container.getItem(i);
                        if (stack.getItem() == GT5RItems.PrintedPages && printedPages.isEmpty()){
                            printedPages = stack;
                        } else if (stack.getItem() == Items.LEATHER){
                            leather = true;
                        }
                    }
                    if (!printedPages.isEmpty() && leather){
                        FluidHolder glue = fluidHandler.map(f -> f.getFluidInTank(0)).orElse(FluidHooks.emptyFluid());
                        if (!glue.isEmpty() && glue.matches(Glue.getLiquid(20)) && glue.getFluidAmount() >= 20 * TesseractGraphWrappers.dropletMultiplier){
                            ItemStack output = new ItemStack(Items.WRITTEN_BOOK);
                            output.setTag(printedPages.copy().getTag());
                            return RecipeMaps.ASSEMBLER.RB().recipeMapOnly().ii(RecipeIngredient.of(printedPages.copy()), RecipeIngredient.of(Items.LEATHER)).fi(Glue.getLiquid(20)).io(output).add("written_book", 32, 8);
                        }
                    }
                }
                return recipe;
            }

            @Override
            public boolean accepts(ItemStack stack) {
                return super.accepts(stack) || stack.getItem() == GT5RItems.PrintedPages;
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag nbt = super.serialize();
                if (activeRecipe != null) {
                    nbt.putString("activeRecipe", activeRecipe.toJson().toString());
                }
                if (lastRecipe != null) {
                    nbt.putString("lastRecipe", lastRecipe.toJson().toString());
                }
                return nbt;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                if (nbt.contains("activeRecipe")) {
                    activeRecipe = MachineRecipeSerializer.INSTANCE.fromJson(new ResourceLocation(nbt.getString("AR")), (JsonObject) JsonParser.parseString(nbt.getString("activeRecipe")));
                }
                if (nbt.contains("lastRecipe")) {
                    lastRecipe = MachineRecipeSerializer.INSTANCE.fromJson(new ResourceLocation(nbt.getString("LR")), (JsonObject) JsonParser.parseString(nbt.getString("lastRecipe")));
                }
            }
        });
    }
}
