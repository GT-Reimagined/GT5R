package org.gtreimagined.gt5r.blockentity.single;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.utils.FluidHooks;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.capability.IFilterableHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.serializer.MachineRecipeSerializer;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.RecipeMaps;
import tesseract.FluidPlatformUtils;
import tesseract.api.item.ExtendedItemContainer;

import static muramasa.antimatter.Ref.L;
import static org.gtreimagined.gt5r.data.Materials.SquidInk;

public class BlockEntityPrinter extends BlockEntityMachine<BlockEntityPrinter> implements IFilterableHandler {
    public BlockEntityPrinter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            @Override
            public IRecipe findRecipe() {
                IRecipe recipe = super.findRecipe();
                if (recipe == null){
                    MachineItemHandler<?> ih = itemHandler.orElse(null);
                    ExtendedItemContainer inputHandler = ih.getInputHandler();
                    ItemStack paper = inputHandler.getItem(0);
                    ItemStack stored = inputHandler.getItem(1);
                    if (paper.getItem() == Items.PAPER && paper.getCount() >= 3 && stored.getItem() == GT5RItems.DataStick){
                        CompoundTag prospect = stored.getTagElement("prospectData");
                        CompoundTag bookData = stored.getTagElement("bookData");
                        FluidHolder ink = fluidHandler.map(f -> f.getFluidInTank(0)).orElse(FluidHooks.emptyFluid());
                        if (!ink.isEmpty() && ink.matches(SquidInk.getLiquid(20)) && ink.getFluidAmount() >= L){
                            ItemStack output = new ItemStack(GT5RItems.PrintedPages);
                            if (prospect != null && prospect.getBoolean("analyzed")){
                                CompoundTag nbt = output.getOrCreateTag();
                                nbt.putString("filtered_title", "Analyzed Prospection Data");
                                nbt.putString("title", "Analyzed Prospection Data");
                                BlockPos pos1 = BlockPos.of(prospect.getLong("pos"));
                                nbt.putString("author", "X: " + pos1.getX() + " Y: " + pos1.getY() + " Z: " + pos1.getZ() + " Dim: " + prospect.getString("dimension"));
                                nbt.putBoolean("resolved", true);
                                ListTag pages = new ListTag();
                                StringBuilder stringBuilder = new StringBuilder();
                                if (prospect.contains("fluid")){
                                    CompoundTag fluid = prospect.getCompound("fluid");
                                    FluidHolder fluid1 = FluidPlatformUtils.createFluidStack(AntimatterPlatformUtils.INSTANCE.getFluidFromID(new ResourceLocation(fluid.getString("name"))), 1);
                                    pages.add(StringTag.valueOf(Component.Serializer.toJson(Utils.translatable("text.gt5r.prospected_book", pos1.getX(), pos1.getZ(), prospect.getString("dimension"),
                                            fluid.getLong("maxYield"), Utils.translatable(fluid1.getTranslationKey())))));
                                } else {
                                    stringBuilder.append("Prospection Data From:");
                                    stringBuilder.append("\n").append("X: ").append(pos1.getX()).append(" Z: ").append(pos1.getZ()).append(" Dim: ").append(prospect.getString("dimension"));
                                    stringBuilder.append("\n").append("Produces ");
                                    stringBuilder.append("No oil");
                                    pages.add(StringTag.valueOf(Component.Serializer.toJson(Utils.literal(stringBuilder.toString()))));
                                    stringBuilder = new StringBuilder();
                                }

                                if (prospect.contains("ores")){
                                    CompoundTag ores = prospect.getCompound("ores");
                                    if (!ores.isEmpty()) {
                                        stringBuilder.append("Prospected Ores:\n");
                                        boolean addedFirst = false;
                                        for (String key : ores.getAllKeys()) {
                                            Material m = Material.get(key);
                                            if (m != Material.NULL){
                                                if (addedFirst){
                                                    stringBuilder.append(", ");
                                                }
                                                if (!addedFirst){
                                                    addedFirst = true;
                                                }
                                                stringBuilder.append(Utils.getLocalizedType(m));
                                            }
                                        }
                                    }
                                }
                                //page.putString("text", stringBuilder.toString());
                                if (!stringBuilder.isEmpty()) {
                                    pages.add(StringTag.valueOf(Component.Serializer.toJson(Utils.literal(stringBuilder.toString()))));
                                }
                                nbt.put("pages", pages);
                            } else if (bookData != null){
                                output.setTag(bookData.copy());
                            }
                            if ((prospect != null && prospect.getBoolean("analyzed")) || bookData != null){
                                return RecipeMaps.SCANNER.RB().recipeMapOnly().ii(RecipeIngredient.of(Items.PAPER, 3)).fi(SquidInk.getLiquid(L)).io(output).add("data_stick_book_printing", 400, 2);
                            }
                        }

                    }
                }
                return recipe;
            }

            @Override
            public boolean accepts(ItemStack stack) {
                return super.accepts(stack) || stack.getItem() == Items.PAPER || stack.getItem() == GT5RItems.DataStick;
            }

            @Override
            public void onMachineEvent(IMachineEvent event, Object... data) {
                if (event == SlotType.IT_IN && data[0] instanceof Integer integer && integer == 1){
                    lastRecipe = null;
                }
                super.onMachineEvent(event, data);
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

    @Override
    public boolean test(SlotType<?> slotType, int slot, ItemStack stack) {
        if (slotType == SlotType.IT_IN){
            return (slot == 0) == (stack.getItem() != GT5RItems.DataStick);
        }
        return true;
    }
}
