package org.gtreimagined.gt5r.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.ICanSyncData;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.block.BlockCoil;
import org.gtreimagined.gt5r.machine.caps.ParallelRecipeHandler;

public class BlockEntityLargeChemicalReactor extends BlockEntityMultiMachine<BlockEntityLargeChemicalReactor> {
    private BlockCoil.CoilData coilData;

    public BlockEntityLargeChemicalReactor(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new ParallelRecipeHandler<>(this, 16));
    }

//    @Override
//    public void onRecipeFound() {
////        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
////        this.mEfficiencyIncrease = 10000;
//
//        int tier = Utils.getVoltageTier(getMaxInputVoltage());
//        EUt = (-4 * (1 << tier - 1) * (1 << tier - 1) * level / discount);
//        maxProgress = Math.max(1, 512 / (1 << tier - 1));
//    }

    public void setCoilData(BlockCoil.CoilData coilData) {
        this.coilData = coilData;
    }

    public BlockCoil.CoilData getCoilData() {
        return coilData;
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return MultiSmelterInfoWidget.build().setPos(10, 10);
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        int superDraw = super.drawInfo(instance, stack, renderer, left, top);
        if (getMachineState() == MachineState.ACTIVE && instance.drawActiveInfo()){
            renderer.draw(stack, "Concurrent Recipes: " + ((MultiSmelterInfoWidget)instance).concurrentRecipes, left, top + 32, 0xFAFAFF);
            return superDraw + 8;
        }
        return superDraw;
    }

    public static class MultiSmelterInfoWidget extends InfoRenderWidget.MultiRenderWidget{
        int concurrentRecipes;
        protected MultiSmelterInfoWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            super.init();
            BlockEntityLargeChemicalReactor m = (BlockEntityLargeChemicalReactor) gui.handler;
            gui.syncInt(() -> m.recipeHandler.map(r -> ((ParallelRecipeHandler<?>)r).concurrentRecipes).orElse(0), i -> concurrentRecipes = i, ICanSyncData.SyncDirection.SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new MultiSmelterInfoWidget(a, b, (IInfoRenderer) a.handler));
        }
    }
}
