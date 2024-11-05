package org.gtreimagined.gt5r.data;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.material.MaterialTypeItem;

import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.data.AntimatterMaterialTypes.SCREW;

public class GT5RMaterialTypes {

    public static final MaterialTypeItem<?> TURBINE_BLADE = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("turbine_blade", 2, true, (Ref.U * 3) + (Ref.U8 * 2)));//.unSplitName();
    public static final MaterialTypeItem<?> HUGE_BROKEN_TURBINE_ROTOR = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("huge_broken_turbine_rotor", 2, true, TURBINE_BLADE.getUnitValue() * 8));
    public static final MaterialTypeItem<?> LARGE_BROKEN_TURBINE_ROTOR = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("large_broken_turbine_rotor", 2, true, TURBINE_BLADE.getUnitValue() * 6));
    public static final MaterialTypeItem<?> BROKEN_TURBINE_ROTOR = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("broken_turbine_rotor", 2, true, TURBINE_BLADE.getUnitValue() * 4));
    public static final MaterialTypeItem<?> SMALL_BROKEN_TURBINE_ROTOR = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("small_broken_turbine_rotor", 2, true, TURBINE_BLADE.getUnitValue() * 2));
    public static final MaterialTypeItem<?> BOULE = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("boule", 2, true, Ref.U * 4));
    public static final MaterialTypeItem<?> CHAMBER = AntimatterAPI.register(MaterialTypeItem.class, new MaterialTypeItem<>("chamber", 2, true, Ref.U * 7));

    public static void init(){
        BOULE.setIgnoreTextureSets();
        CHAMBER.setIgnoreTextureSets();
        TURBINE_BLADE.unSplitName().setIgnoreTextureSets();
        SMALL_BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        LARGE_BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        HUGE_BROKEN_TURBINE_ROTOR.unSplitName().setIgnoreTextureSets();
        SMALL_BROKEN_TURBINE_ROTOR.dependents(TURBINE_BLADE);
        BROKEN_TURBINE_ROTOR.dependents(TURBINE_BLADE);
        LARGE_BROKEN_TURBINE_ROTOR.dependents(TURBINE_BLADE);
        HUGE_BROKEN_TURBINE_ROTOR.dependents(TURBINE_BLADE);
        TURBINE_BLADE.dependents(SCREW, PLATE);
    }
}
