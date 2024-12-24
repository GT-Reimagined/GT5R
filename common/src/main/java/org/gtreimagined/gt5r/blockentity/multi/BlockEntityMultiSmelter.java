package org.gtreimagined.gt5r.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import lombok.Getter;
import lombok.Setter;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.event.IMachineEvent;
import muramasa.antimatter.recipe.map.IRecipeMap;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.util.Utils;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt5r.data.RecipeMaps;
import org.gtreimagined.gt5r.machine.caps.ParallelRecipeHandler;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import org.gtreimagined.gt5r.block.BlockCoil;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gtcore.item.ItemSelectorTag;

import java.util.List;

public class BlockEntityMultiSmelter extends BlockEntityMultiMachine<BlockEntityMultiSmelter> {
    @Getter
    @Setter
    private BlockCoil.CoilData coilData;
    private IRecipeMap recipeMap = RecipeMaps.ELECTRIC_FURNACE;

    public BlockEntityMultiSmelter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new ParallelRecipeHandler<>(this, 1){
            @Override
            protected int maxSimultaneousRecipes(){
                if (coilData == null) return 0;
                return coilData.maxSimultaneousRecipes();
            }

            @Override
            protected boolean canRecipeContinue() {
                if (activeRecipe != null && !activeRecipe.getMapId().equals(recipeMap.getId())){
                    return false;
                }
                return super.canRecipeContinue();
            }

            @Override
            public IRecipeMap getRecipeMap() {
                return recipeMap;
            }
        });
    }

    @Override
    public void onFirstTick() {
        super.onFirstTick();
        onMachineEvent(SlotType.STORAGE);
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return MultiSmelterInfoWidget.build().setPos(10, 10);
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        int superDraw = super.drawInfo(instance, stack, renderer, left, top);
        if (!(instance instanceof MultiSmelterInfoWidget widget)) return superDraw;
        if (getMachineState() == MachineState.ACTIVE && instance.drawActiveInfo()){
            renderer.draw(stack, "Concurrent Recipes: " + widget.concurrentRecipes, left, top + 32, 16448255);
            superDraw += 8;
        }
        int add = getMachineState() == MachineState.ACTIVE && instance.drawActiveInfo() ? 40 : 16;
        RecipeMap<?> map = AntimatterAPI.get(RecipeMap.class, widget.recipeMap);
        if (map != null){
            renderer.draw(stack, Utils.literal("Recipe map: ").append(map.getDisplayName()).getString(), left, top + add, 16448255);
            superDraw += 8;
        }

        return superDraw;
    }

    @Override
    public void onMachineEvent(IMachineEvent event, Object... data) {
        if (event == SlotType.STORAGE){
            ItemStack circuit = itemHandler.map(i -> i.getHandler(SlotType.STORAGE).getItem(0)).orElse(ItemStack.EMPTY);
            if (circuit.getItem() instanceof ItemSelectorTag tag && tag.circuitId == 1){
                this.recipeMap = RecipeMaps.ALLOY_SMELTER;
            } else {
                this.recipeMap = RecipeMaps.ELECTRIC_FURNACE;
            }
        }
        super.onMachineEvent(event, data);
    }

    @Override
    public List<String> getInfo(boolean simple) {
        return super.getInfo(simple);
    }

    public static class MultiSmelterInfoWidget extends InfoRenderWidget.MultiRenderWidget{
        int concurrentRecipes;
        String recipeMap;
        protected MultiSmelterInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            super.init();
            BlockEntityMultiSmelter m = (BlockEntityMultiSmelter) gui.handler;
            gui.syncInt(() -> m.recipeHandler.map(r -> ((ParallelRecipeHandler<?>)r).concurrentRecipes).orElse(0), i -> concurrentRecipes = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
            gui.syncString(() -> m.recipeMap.getId(), i -> recipeMap = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new MultiSmelterInfoWidget(a, b, (IInfoRenderer) a.handler));
        }
    }
}
