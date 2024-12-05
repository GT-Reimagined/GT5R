package org.gtreimagined.gt5r.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.integration.jeirei.renderer.IInfoRenderer;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import tesseract.TesseractGraphWrappers;

import java.util.List;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;
import static org.gtreimagined.gt5r.data.Materials.Lubricant;
import static org.gtreimagined.gt5r.data.Materials.Oxygen;

public class BlockEntityCombustionEngine extends BlockEntityMultiMachine<BlockEntityCombustionEngine> {

    long lastEu = 0;
    long lastConsumption = 0;
    int startup = 0;

    public BlockEntityCombustionEngine(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){

            int lubeTicker = 0;

            @Override
            protected boolean consumeGeneratorResources(boolean simulate) {
                boolean boostEU = fluidHandler.map(f -> f.drainInput(Oxygen.getGas(2), true).getFluidAmount() == 2 * TesseractGraphWrappers.dropletMultiplier).orElse(false);
                int fuelConsumption = (int) (boostEU ? (4096 / activeRecipe.getPower()) : (2048 / activeRecipe.getPower()));
                int lubeConsume = boostEU ? 2 : 1;
                if ((lubeTicker == 72 || simulate) &&!fluidHandler.map(f -> f.drainInput(Lubricant.getLiquid(lubeConsume), true).getFluidAmount() == lubeConsume * TesseractGraphWrappers.dropletMultiplier).orElse(false)) {
                    if (!simulate && startup > 0) startup = 0;
                    return false;
                }
                long toConsume = fuelConsumption * activeRecipe.getInputFluids().get(0).getAmount();
                if (fluidHandler.map(f -> !f.consumeAndReturnInputs(List.of(activeRecipe.getInputFluids().get(0).copy(toConsume)), simulate).isEmpty()).orElse(false)){
                    if (!simulate) {
                        if (startup > 100) startup = 100;
                        fluidHandler.ifPresent(f -> {
                            f.drainInput(Oxygen.getGas(2), false);
                            if (lubeTicker == 72) f.drainInput(Lubricant.getLiquid(lubeConsume), false);
                        });
                        lastConsumption = toConsume;
                        long euPerTick = boostEU ? 6144 : 2048;
                        lastEu = startup < 20 ? 0 : (long)(euPerTick * ((float)startup / 100));
                        energyHandler.ifPresent(e -> {
                            e.insertInternal(lastEu, simulate);
                            if (lastEu > e.getOutputVoltage()){
                                explodeMultiblock();
                            }
                        });

                        if (startup < 100){
                            startup ++;
                        }
                        lubeTicker++;
                        if (lubeTicker > 72) lubeTicker = 0;
                    }
                    return true;
                } else if (!simulate){
                    resetRecipe();
                }
                if (!simulate && startup > 0) startup = 0;
                return false;
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag nbt = super.serialize();
                nbt.putInt("startup", startup);
                nbt.putInt("lubeTicker", lubeTicker);
                return nbt;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                startup = nbt.getInt("startup");
                lubeTicker = nbt.getInt("lubeTicker");
            }
        });
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        CombustionEngineWidget widget = (CombustionEngineWidget) instance;
        renderer.draw(stack, this.getDisplayName().getString(), left, top, 16448255);
        if (getMachineState() != MachineState.ACTIVE) {
            renderer.draw(stack, "Inactive.", left, top + 8, 16448255);
            return 16;
        } else if (instance.drawActiveInfo()) {
            renderer.draw(stack, "EU/t: " + widget.lastEU, left, top + 8, 16448255);
            renderer.draw(stack, "Startup progress: " + (((float)widget.startup / 100) * 100) + "%", left, top + 16, 16448255);
            renderer.draw(stack, "Current: " + widget.currentConsumption + " mb/t", left, top + 24, 16448255);
            return 32;
        }
        return 8;
    }

    @Override
    public WidgetSupplier getInfoWidget() {
        return CombustionEngineWidget.build().setPos(10,10);
    }

    public static class CombustionEngineWidget extends InfoRenderWidget.MultiRenderWidget {

        public long currentConsumption = 0;
        public long lastEU = 0;
        public int startup = 0;

        protected CombustionEngineWidget(GuiInstance gui, IGuiElement parent, IInfoRenderer<MultiRenderWidget> renderer) {
            super(gui, parent, renderer);
        }

        @Override
        public void init() {
            super.init();
            BlockEntityCombustionEngine turbine = (BlockEntityCombustionEngine) gui.handler;
            gui.syncLong(() -> turbine.lastEu, i -> this.lastEU = i, SERVER_TO_CLIENT);
            gui.syncInt(() -> turbine.startup, i -> this.startup = i, SERVER_TO_CLIENT);
            gui.syncLong(() -> turbine.lastConsumption, i -> currentConsumption = i, SERVER_TO_CLIENT);
        }

        public static WidgetSupplier build() {
            return builder((a, b) -> new CombustionEngineWidget(a, b, (IInfoRenderer<MultiRenderWidget>) a.handler));
        }

    }
}
