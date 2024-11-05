package org.gtreimagined.gt5r.blockentity.single;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.serializer.AntimatterRecipeSerializer;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.RecipeMaps;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gtcore.data.GTCoreItems;
import tesseract.api.item.ExtendedItemContainer;

public class BlockEntityScanner extends BlockEntityMachine<BlockEntityScanner> implements IFilterableHandler {
    public BlockEntityScanner(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipe findRecipe() {
                IRecipe recipe = super.findRecipe();
                if (recipe == null){
                    MachineItemHandler<?> ih = itemHandler.orElse(null);
                    ExtendedItemContainer inputHandler = ih.getInputHandler();
                    ItemStack input0 = inputHandler.getItem(0);
                    ItemStack input1 = inputHandler.getItem(1);
                    if (!input0.isEmpty()) {
                        if (input0.getItem() == GT5RItems.DataStick) {
                            CompoundTag prospect = input0.getTagElement("prospectData");
                            if (prospect != null) {
                                ItemStack output = input0.copy();
                                output.getTagElement("prospectData").putBoolean("analyzed", true);
                                return RecipeMaps.SCANNER.RB().recipeMapOnly().ii(RecipeIngredient.of(input0.copy())).io(output).add("data_stick_prospection", 1000, 32);
                            }
                        } else if (input0.getItem() == Items.WRITTEN_BOOK && input0.getTag() != null && input1.getItem() == GT5RItems.DataStick && input1.getTag() == null){
                            ItemStack output = new ItemStack(GT5RItems.DataStick);
                            output.getOrCreateTag().put("bookData", input0.getTag().copy());
                            return RecipeMaps.SCANNER.RB().recipeMapOnly().ii(RecipeIngredient.of(input0.copy()), RecipeIngredient.of(input1.copy())).io(output).add("book_copying", 128, 32);
                        }
                    }
                }
                return recipe;
            }

            @Override
            public boolean accepts(ItemStack stack) {
                return super.accepts(stack) || stack.getItem() == GT5RItems.DataStick || stack.getItem() == Items.WRITTEN_BOOK;
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
                    activeRecipe = AntimatterRecipeSerializer.INSTANCE.fromJson(new ResourceLocation(nbt.getString("AR")), (JsonObject) JsonParser.parseString(nbt.getString("activeRecipe")));
                }
                if (nbt.contains("lastRecipe")) {
                    lastRecipe = AntimatterRecipeSerializer.INSTANCE.fromJson(new ResourceLocation(nbt.getString("LR")), (JsonObject) JsonParser.parseString(nbt.getString("lastRecipe")));
                }
            }
        });
    }

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.IT_IN && slot == 1){
            return stack.getItem() == GT5RItems.DataStick || stack.getItem() == GTCoreItems.DataOrb;
        }
        return true;
    }
}
