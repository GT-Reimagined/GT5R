package org.gtreimagined.gt5r.material;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.material.IMaterialTag;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.data.ToolData;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;

import static muramasa.antimatter.material.MaterialTags.HAS_CUSTOM_SMELTING;
import static muramasa.antimatter.material.MaterialTags.METAL;

public class GregTechMaterialEvent extends MaterialEvent<GregTechMaterialEvent> {
    public GregTechMaterialEvent asSolid(int meltingPoint, int blastFurnaceTemp, IMaterialTag... tags) {
        super.asSolid(meltingPoint, tags);
        GT5RMaterialTags.BLAST_FURNACE_TEMP.add(material, blastFurnaceTemp);
        if (blastFurnaceTemp >= 2000){
            flags(GT5RMaterialTags.NEEDS_BLAST_FURNACE, HAS_CUSTOM_SMELTING);
        }
        if (blastFurnaceTemp > 2400) {
            flags(AntimatterMaterialTypes.INGOT_HOT);
        }
        return this;
    }

    public GregTechMaterialEvent asMetal(int meltingPoint, int blastFurnaceTemp, IMaterialTag... tags) {
        flags(METAL);
        asSolid(meltingPoint, blastFurnaceTemp, tags);
        return this;
    }

    public GregTechMaterialEvent forceBF(boolean hotIngot) {
        flags(GT5RMaterialTags.NEEDS_BLAST_FURNACE, HAS_CUSTOM_SMELTING);
        if (hotIngot) {
            flags(AntimatterMaterialTypes.INGOT_HOT);
        }
        return this;
    }

    @Override
    public GregTechMaterialEvent asSolid(int meltingPoint, IMaterialTag... tags) {
        return asSolid(meltingPoint, meltingPoint, tags);
    }

    @Override
    public GregTechMaterialEvent asMetal(int meltingPoint, IMaterialTag... tags) {
        return asMetal(meltingPoint, meltingPoint, tags);
    }

    public GregTechMaterialEvent elecTicks(int ticks){
        GT5RMaterialTags.ELEC_TICKS.add(material, ticks);
        return this;
    }

    @Override
    protected GregTechMaterialEvent buildTool(ToolData builder) {
        flags(AntimatterMaterialTypes.ROD_LONG);
        return super.buildTool(builder);
    }

    public GregTechMaterialEvent thermal(Material byProduct){
        GT5RMaterialTags.THERMAL_CENTRIFUGE_EXPLICIT.add(this.material, byProduct);
        return this;
    }

    public GregTechMaterialEvent bathMercury(Material byProduct){
        GT5RMaterialTags.BATH_MERCURY.add(this.material, byProduct);
        return this;
    }

    public GregTechMaterialEvent bathPersulfate(Material byProduct){
        GT5RMaterialTags.BATH_PERSULFATE.add(this.material, byProduct);
        return this;
    }
}
