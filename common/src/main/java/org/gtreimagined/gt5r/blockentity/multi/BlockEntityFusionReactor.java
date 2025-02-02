package org.gtreimagined.gt5r.blockentity.multi;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.fluid.FluidTank;
import muramasa.antimatter.capability.machine.DefaultHeatHandler;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.gui.widget.InfoRenderWidget;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.texture.Texture;
import net.minecraft.client.gui.Font;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.machine.caps.SecondaryMultiFluidHandler;
import org.gtreimagined.gt5r.machine.recipe.FusionRecipe;
import tesseract.TesseractGraphWrappers;

import static org.gtreimagined.gt5r.data.Materials.*;

public class BlockEntityFusionReactor extends BlockEntityMultiMachine<BlockEntityFusionReactor> {

    Display display = Display.REGULAR;

    public BlockEntityFusionReactor(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.heatHandler.set(() -> new DefaultHeatHandler(this, 32768 * 8, 8192, 0));
        this.fluidHandler.set(() -> new SecondaryMultiFluidHandler<>(this));
        this.recipeHandler.set(() -> new MachineRecipeHandler<>(this){
            boolean consumedStartEu = false;

            @Override
            public boolean consumeResourceForRecipe(boolean simulate) {
                if (activeRecipe == null) return false;
                if (!consumedStartEu){
                    boolean tConsumedStartEu = energyHandler.map(e -> e.extractEu(activeRecipe.getSpecialValue(), true) == activeRecipe.getSpecialValue()).orElse(false);
                    if (tConsumedStartEu){
                        if (!simulate) {
                            energyHandler.ifPresent(e -> e.extractEu(activeRecipe.getSpecialValue(), false));
                        }
                        consumedStartEu = true;
                    } else {
                        return false;
                    }
                }
                boolean run = super.consumeResourceForRecipe(simulate) && consumedStartEu;
                if (run) {
                    heatHandler.ifPresent(h -> h.insert(((FusionRecipe)activeRecipe).getHuOutput(), simulate));
                }
                return run;
            }

            @Override
            protected MachineState tickRecipe() {
                IRecipe oldActive = activeRecipe;
                MachineState oldState = tile.getMachineState();
                MachineState superState =  super.tickRecipe();
                if (consumedStartEu && oldActive != null && ((oldActive != activeRecipe) || (oldState == MachineState.ACTIVE && superState != MachineState.ACTIVE))){
                    consumedStartEu = false;
                }
                return superState;
            }

            @Override
            public CompoundTag serialize() {
                CompoundTag nbt = super.serialize();
                nbt.putBoolean("consumedStartEu", consumedStartEu);
                return nbt;
            }

            @Override
            public void deserialize(CompoundTag nbt) {
                super.deserialize(nbt);
                consumedStartEu = nbt.getBoolean("consumedStartEu");
            }
        });
    }

    @Override
    public void serverTick(Level level, BlockPos pos, BlockState state) {
        super.serverTick(level, pos, state);
        fluidHandler.ifPresent(f -> {
            if (!(f instanceof SecondaryMultiFluidHandler<?> mf)) return;
            if (mf.getSecondaryInputTanks() == null) return;
            heatHandler.ifPresent(h -> {
                if (h.getHeat() >= 30){
                    int heatMultiplier = h.getHeat() / 30;
                    FluidTank coolantTank = mf.getSecondaryInputTanks().getTank(mf.getSecondaryInputTanks().getFirstAvailableTank(Helium.getGas(1), true));
                    if (coolantTank != null) {
                        heatMultiplier = (int) Math.min(heatMultiplier, coolantTank.getTankAmount() / TesseractGraphWrappers.dropletMultiplier);
                        if (coolantTank.extractFluid(Helium.getGas(heatMultiplier), true).getFluidAmount() == heatMultiplier *  TesseractGraphWrappers.dropletMultiplier) {
                            if (mf.getSecondaryOutputTanks() != null && mf.getSecondaryOutputTanks().getSize() >= 1) {
                                long inserted = mf.getSecondaryOutputTanks().internalInsert(HotHelium.getGas(heatMultiplier), true);
                                if (inserted >= TesseractGraphWrappers.dropletMultiplier){
                                    heatMultiplier = (int) Math.min(heatMultiplier, (inserted / TesseractGraphWrappers.dropletMultiplier));
                                    coolantTank.extractFluid(Helium.getGas(heatMultiplier), false);
                                    mf.getSecondaryOutputTanks().internalInsert(HotHelium.getGas(heatMultiplier), false);
                                    h.extract(heatMultiplier * 30, false);
                                }
                            }
                        }
                    }
                }
            });

        });
    }

//    @Override
//    public void onRecipeFound() {
//        consumeEnergy(activeRecipe.getSpecialValue());
//        System.out.println("Consumed Starting Energy");
//    }

    public Display getDisplay() {
        return display;
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("display", display.ordinal());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.display = Display.values()[tag.getInt("display")];
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev =(GuiEvents.GuiEvent) event;
            int[] data = ev.data;
            if (data[1] == 0){
                this.display = Display.REGULAR;
            } else if (data[1] == 1){
                this.display = Display.MIDDLE;
            } else if (data[1] == 2){
                this.display = Display.TOP_BOTTOM;
            }
        }
    }

    public Texture getTextureForHatches(Direction dir, BlockPos hatchPos){
        return new Texture(GT5RRef.ID, "block/casing/fusion_1");
    }

    @Override
    public int guiHeight() {
        return 182;
    }

    @Override
    public int drawInfo(InfoRenderWidget.MultiRenderWidget instance, PoseStack stack, Font renderer, int left, int top) {
        return 0;
    }

    public enum Display{
        REGULAR,
        MIDDLE,
        TOP_BOTTOM
    }
}
