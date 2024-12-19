package org.gtreimagined.gt5r.blockentity.single;

import muramasa.antimatter.blockentity.single.BlockEntityGenerator;
import muramasa.antimatter.capability.item.ITrackedHandler;
import muramasa.antimatter.capability.machine.MachineItemHandler;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.types.Machine;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.Map;

public class BlockEntityMagicEnergyConverter extends BlockEntityGenerator<BlockEntityMagicEnergyConverter> {
    public BlockEntityMagicEnergyConverter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.itemHandler.set(() -> new MachineItemHandler<>(this){
            @Override
            public ITrackedHandler getCellInputHandler() {
                return super.getInputHandler();
            }

            @Override
            public ITrackedHandler getCellOutputHandler() {
                return super.getOutputHandler();
            }

            @Override
            public ITrackedHandler getHandler(SlotType<?> type) {
                if (type == SlotType.CELL_IN) type = SlotType.IT_IN;
                if (type == SlotType.CELL_OUT) type = SlotType.IT_OUT;
                return super.getHandler(type);
            }
        });
    }


    private long euFromItem(ItemStack tStack) {
        if (tStack.isEmpty()) return 0;
        if (!tStack.isEnchanted()) return 0;
        long tEU = 0;
        // Convert enchantments to their EU Value
        Map<Enchantment, Integer> tMap = EnchantmentHelper.getEnchantments(tStack);
        for (Map.Entry<Enchantment, Integer> e : tMap.entrySet()) {
            Enchantment ench = e.getKey();
            Integer tLevel = e.getValue();
            tEU += 1000000L * tLevel / ench.getMaxLevel() / ench.getRarity().getWeight();
        }
        return tEU;
    }
}
