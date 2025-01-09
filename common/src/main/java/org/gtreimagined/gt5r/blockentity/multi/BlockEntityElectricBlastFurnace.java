package org.gtreimagined.gt5r.blockentity.multi;

import muramasa.antimatter.Ref;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.machine.MachineRecipeHandler;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import org.gtreimagined.gt5r.block.BlockCoil;

public class BlockEntityElectricBlastFurnace extends BlockEntityMultiMachine<BlockEntityElectricBlastFurnace> {
    private BlockCoil.CoilData coilData;

    public BlockEntityElectricBlastFurnace(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        recipeHandler.set(() -> new MachineRecipeHandler<>(this) {

            @Override
            protected void calculateDurations() {
                super.calculateDurations();
                maxProgress = activeRecipe.getDuration();
                overclock = 0;
                long voltage = getMaxInputVoltage();
                int tier = Math.max(1, Utils.getVoltageTier(voltage));
                int recipeTier = Utils.getVoltageTier(activeRecipe.getPower());
                if (recipeTier == tier) {
                    EUt = activeRecipe.getPower();
                    return;
                }
                if (coilData != null && coilData.heat() >= activeRecipe.getSpecialValue()) {
                    int heatDiv = (coilData.heat() - activeRecipe.getSpecialValue()) / 900;
                    if (activeRecipe.getPower() <= 16) {
                        EUt = (activeRecipe.getPower() * (1L << tier - 1) * (1L << tier - 1));
                        maxProgress = (activeRecipe.getDuration() / (1 << tier - 1));
                    } else {
                        EUt = activeRecipe.getPower();
                        maxProgress = activeRecipe.getDuration();
                        int i = 2;
                        while (EUt <= Ref.V[tier - 1]){
                            EUt *= 4;
                            maxProgress /= (heatDiv >= i ? 4 : 2);
                            i += 2;
                        }
                        if (heatDiv > 0){
                            EUt = (long) (EUt * Math.pow(0.95, heatDiv));
                        }
                    }
                }
            }

            @Override
            public long getPower() {
                return EUt;
            }

            @Override
            protected boolean validateRecipe(IRecipe r) {
                return super.validateRecipe(r) && coilData != null && coilData.heat() >= r.getSpecialValue();
            }
        });
    }

    @Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        return true;
    }

    public void setCoilData(BlockCoil.CoilData coilData) {
        this.coilData = coilData;
    }

    public BlockCoil.CoilData getCoilData() {
        return coilData;
    }
}
