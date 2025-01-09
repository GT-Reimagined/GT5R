package org.gtreimagined.gt5r.integration.rei;

import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.integration.rei.REIUtils;
import muramasa.antimatter.machine.Tier;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gt5r.data.GT5RMachines;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;

public class REIRegistrar {

    public static void init(){
        REIUtils.addExtraCategory(r -> {
            OreProcessingCategory cat = new OreProcessingCategory();
            r.add(cat);
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.MACERATOR.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.ORE_WASHER.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.CENTRIFUGE.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.THERMAL_CENTRIFUGE.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.BATH.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.ELECTROMAGNETIC_SEPARATOR.getItem(Tier.LV))));
            r.addWorkstations(cat.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.SIFTER.getItem(Tier.LV))));
            MaterialTreeCategory cat2 = new MaterialTreeCategory();
            r.add(cat2);
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.LATHE.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.BENDER.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.CUTTER.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.ASSEMBLER.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.COMPRESSOR.getItem(Tier.LV))));
            r.addWorkstations(cat2.getCategoryIdentifier(), EntryStack.of(VanillaEntryTypes.ITEM,  new ItemStack(GT5RMachines.FLUID_PRESS.getItem(Tier.LV))));
        });
        REIUtils.addExtraDisplay(r -> {
            AntimatterMaterialTypes.ORE.all().forEach(m -> {
                if (m.has(GT5RMaterialTags.BATH_PERSULFATE) || m.has(GT5RMaterialTags.BATH_MERCURY)){
                    if (m.has(GT5RMaterialTags.BATH_MERCURY)){
                        r.add(new OreProcessingDisplay(m, OreProcessingDisplay.BathingMode.MERCURY));
                    }
                    if (m.has(GT5RMaterialTags.BATH_PERSULFATE)){
                        r.add(new OreProcessingDisplay(m, OreProcessingDisplay.BathingMode.PERSULFATE));
                    }
                } else {
                    r.add(new OreProcessingDisplay(m, OreProcessingDisplay.BathingMode.NONE));
                }
            });
            AntimatterMaterialTypes.DUST.all().forEach(m -> {
                r.add(new MaterialTreeDisplay(m));
            });
        });
    }
}
