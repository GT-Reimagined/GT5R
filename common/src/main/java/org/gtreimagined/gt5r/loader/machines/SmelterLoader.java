package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeItem;
import muramasa.antimatter.ore.CobbleStoneType;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.world.level.block.Blocks;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;
import org.gtreimagined.gtcore.data.GTCoreBlocks;

import static muramasa.antimatter.Ref.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Lava;
import static muramasa.antimatter.material.MaterialTags.MOLTEN;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.SMELTER;

public class SmelterLoader {
    public static void init() {
        MaterialTypeItem<?>[] items = new MaterialTypeItem<?>[]{INGOT, NUGGET, PLATE, PLATE_DENSE, ROD, ROD_LONG, RING, FOIL, BOLT, SCREW, GEAR, GEAR_SMALL, WIRE_FINE, ROTOR};
        for (MaterialTypeItem<?> item : items) {
            item.all().forEach(m -> {
                long amount = m == Alumina ? (item.getUnitValue() * 7) / 2 : item.getUnitValue();
                add(m, item, amount);
            });
        }
        DUST.all().forEach(m -> {
            if (m.has(LIQUID) && m.has(MOLTEN) && !m.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE)){
                long amount = m == Alumina ? (DUST.getUnitValue() * 7) / 2 : DUST.getUnitValue();
                add(m, DUST, amount);
            }
        });
        if (GT5RConfig.HARDER_ALUMINIUM_PROCESSING.get()){
            SMELTER.RB().ii(DUST.getMaterialIngredient(AluminiumHydroxide, 1)).fo(Alumina.getLiquid(((L9 * 7 * 3) / 2) + (L9 * 3 / 4))).add("aluminium_hydroxide_to_alumina", 55, 16);
        }
        addLava(Obsidian, ROD_LONG, ROD_LONG.getUnitValue());
        addLava(Obsidian, PLATE, PLATE.getUnitValue());
        addLava(Obsidian, DUST, DUST.getUnitValue());
        SMELTER.RB().ii(DUST.getMaterialIngredient(Limestone, 1)).fo(Calcite.getLiquid(L)).add("limestone_dust_to_calcite", Limestone.getMass(), 24);
        SMELTER.RB().ii(GTCoreBlocks.LIMESTONE.getState().getBlock()).fo(Calcite.getLiquid(L)).add("limestone_to_calcite", Limestone.getMass(), 24);
        SMELTER.RB().ii(((CobbleStoneType)GTCoreBlocks.LIMESTONE).getBlock("cobble")).fo(Calcite.getLiquid(L)).add("limestone_cobble_to_calcite", Limestone.getMass(), 24);
        SMELTER.RB().ii(Blocks.CALCITE).fo(Calcite.getLiquid(L)).add("mc_calcite_to_calcite", Limestone.getMass(), 24);
    }

    private static void add(Material m, MaterialTypeItem<?> i, long materialAmount) {
        if (!m.has(AntimatterMaterialTypes.LIQUID)) return;
        long amount = //(long) (L * ratio);
                (L * materialAmount) / U;
        long duration = Math.max(1, (24 * materialAmount) / U);
        SMELTER.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(m.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/L)), Math.max(8, (int) Math.sqrt(2 * MaterialTags.MELTING_POINT.getInt(m))), duration);
    }

    private static void addLava(Material m, MaterialTypeItem<?> i, long materialAmount) {
        long flUnit = AntimatterPlatformUtils.INSTANCE.isFabric() ? L : 111;
        long amount = //(long) (L * ratio);
                (flUnit * materialAmount) / U;
        long duration = Math.max(1, (24 * materialAmount) / U);
        SMELTER.RB()
                .ii(RecipeIngredient.of(i.getMaterialTag(m),1))
                .fo(Lava.getLiquid(amount))
                .add(m.getId() + "_from_" + i.getId(), (long)(m.getMass()*((float)amount/L)), Math.max(8, (int) Math.sqrt(2 * MaterialTags.MELTING_POINT.getInt(m))), duration);
    }
}
