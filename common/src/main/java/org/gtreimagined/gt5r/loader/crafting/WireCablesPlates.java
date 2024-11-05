package org.gtreimagined.gt5r.loader.crafting;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import org.gtreimagined.gt5r.GT5RRef;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gtcore.block.RedstoneWire;

import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.pipe.PipeSize.*;
import static org.gtreimagined.gt5r.data.Materials.Rubber;

public class WireCablesPlates {
    @SuppressWarnings("unchecked")
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        AntimatterAPI.all(Wire.class, wire -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + wire.getMaterial().getId());
            ImmutableSet<PipeSize> sizes = wire.getSizes();
            Map<PipeSize, Item> wires = sizes.stream().map(s -> new Pair<>(s, wire.getBlockItem(s))).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond));
            PipeSize[] val = VALUES;
            for (int i = 1; i < val.length; i += 1) {
                int offset = val[i] == HUGE ? 1 : 0;
                if (val[i] == LARGE){
                    provider.shapeless(output,"three_to_one_" + AntimatterPlatformUtils.INSTANCE.getIdFromItem(wires.get(LARGE)).getPath(),"wire",
                            new ItemStack(wires.get(val[i]),1),wires.get(SMALL),wires.get(SMALL),wires.get(SMALL));
                    provider.shapeless(output,"one_to_three_" + AntimatterPlatformUtils.INSTANCE.getIdFromItem(wires.get(NORMAL)).getPath(),"wire",
                            new ItemStack(wires.get(SMALL),3),wires.get(val[i]));
                    continue;
                }
                twoToOne(wires, val[i-1 - offset], val[i], output,provider);
                oneToTwo(wires, val[i], val[i-1 - offset], output, provider);
                if (i > 1) {
                    fourToOne(wires, val[i-2 - offset], val[i], output, provider);
                }
            }
            if (wire.getMaterial().has(PLATE)) {
                provider.shapeless(output,  wire.getMaterial().getId() + "_plate_to_wire","wire",
                        new ItemStack(wires.get(VTINY)),
                        AntimatterDefaultTools.WIRE_CUTTER.getTag(), PLATE.get(wire.getMaterial()));
            }
            if (cable != null){ //manual lv cable crafting
                provider.shapeless(output, GT5RRef.ID, wire.getId() + "_cable_1x", "cables", new ItemStack(cable.getBlockItem(VTINY)), wire.getBlockItem(VTINY), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GT5RRef.ID, wire.getId() + "_cable_2x", "cables", new ItemStack(cable.getBlockItem(TINY)), wire.getBlockItem(TINY), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GT5RRef.ID, wire.getId() + "_cable_4x", "cables", new ItemStack(cable.getBlockItem(SMALL)), wire.getBlockItem(SMALL), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GT5RRef.ID, wire.getId() + "_cable_8x", "cables", new ItemStack(cable.getBlockItem(NORMAL)), wire.getBlockItem(NORMAL), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GT5RRef.ID, wire.getId() + "_cable_12x", "cables", new ItemStack(cable.getBlockItem(LARGE)), wire.getBlockItem(LARGE), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
                provider.shapeless(output, GT5RRef.ID, wire.getId() + "_cable_16x", "cables", new ItemStack(cable.getBlockItem(HUGE)), wire.getBlockItem(HUGE), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber), AntimatterMaterialTypes.PLATE.getMaterialTag(Rubber));
            }
        });
        AntimatterAPI.all(RedstoneWire.class, wire -> {
            if (wire.getMaterial().has(PLATE)) {
                provider.shapeless(output,  wire.getMaterial().getId() + "_plate_to_wire","wire",
                        new ItemStack(wire.getBlockItem(VTINY)),
                        AntimatterDefaultTools.WIRE_CUTTER.getTag(), PLATE.get(wire.getMaterial()));
            }
        });
    }

    private static void twoToOne(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"two_to_one_" + AntimatterPlatformUtils.INSTANCE.getIdFromItem(wires.get(to)).getPath(),"wire",
                new ItemStack(wires.get(to),1),wires.get(from),wires.get(from));
    }

    private static void oneToTwo(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"one_to_two_" + AntimatterPlatformUtils.INSTANCE.getIdFromItem(wires.get(to)).getPath(),"wire",
                new ItemStack(wires.get(to),2),wires.get(from));
    }

    private static void fourToOne(Map<PipeSize, Item> wires, PipeSize from, PipeSize to, Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.shapeless(output,"four_to_one_" + AntimatterPlatformUtils.INSTANCE.getIdFromItem(wires.get(to)).getPath(),"wire",
                new ItemStack(wires.get(to),1),wires.get(from),wires.get(from),wires.get(from),wires.get(from));
    }
}
