package org.gtreimagined.gt5r;

import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterRemapping;
import muramasa.antimatter.Ref;
import muramasa.antimatter.cover.CoverFactory;
import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gtcore.GTCore;

import java.util.Map;

import static org.gtreimagined.gt5r.data.GT5RMachines.*;

public class GTRemapping {
    private static final Map<String, String> REMAPPING_MAP = new Object2ObjectArrayMap<>();

    public static void init(){
        AntimatterRemapping.getBeRemappingFunctionList().add(r -> {
            if (r.getNamespace().equals("gregtech") || r.getNamespace().equals("gti")){
                var r2 = new ResourceLocation(GT5RRef.ID, r.getPath());
                if (AntimatterRemapping.getBeRemappingMap().containsKey(r2)){
                    return AntimatterRemapping.getBeRemappingMap().get(r2);
                }
                return r2;
            }
            return r;
        });
        for (CoverFactory cover : AntimatterAPI.all(CoverFactory.class, GT5RRef.ID)){
            AntimatterRemapping.remapCover(new ResourceLocation("gti", cover.getId()), cover.getLoc());
        }
        for (int i = 0; i < 25; i++) {
            remapGTCore("int_circuit_" + i, "selector_tag_"+i);
        }
        AntimatterRemapping.remapBlockEntity(new ResourceLocation(Ref.SHARED_ID, "fluid_pipe_polyethylene"), new ResourceLocation(Ref.SHARED_ID, "fluid_pipe_plastic"));
        remap("bath_hv", "bath");
        remap("coke_oven_bronze", "coke_oven");
        remap("primitive_blast_furnace_bronze", "primitive_blast_furnace");
        remap("heat_exchanger_ev", "large_heat_exchanger");
        AntimatterRemapping.remapMachine("small_heat_exchanger", INVAR_SMALL_HEAT_EXCHANGER);
        AntimatterRemapping.remapBlockEntity(new ResourceLocation(GT5RRef.ID, "heat_exchanger"), new ResourceLocation(GT5RRef.ID, "large_heat_exchanger"));
        AntimatterRemapping.remapMachine("item_input_hatch", INPUT_BUS);
        AntimatterRemapping.remapMachine("item_output_hatch", OUTPUT_BUS);
        AntimatterRemapping.remapMachine("fluid_input_hatch", INPUT_HATCH);
        AntimatterRemapping.remapMachine("fluid_output_hatch", OUTPUT_HATCH);
        AntimatterRemapping.remapMachine("coal_boiler", SOLID_FUEL_BOILER);
        AntimatterRemapping.remapMachine("pulverizer", MACERATOR);
        AntimatterRemapping.remapMachine("large_macerator", LARGE_PULVERIZER);
        AntimatterRemapping.remapMachine("fluid_extractor", FLUID_PRESS);
        AntimatterRemapping.remapMachine("hatch_item_input", INPUT_BUS);
        AntimatterRemapping.remapMachine("hatch_item_output", OUTPUT_BUS);
        AntimatterRemapping.remapMachine("hatch_fluid_input", INPUT_HATCH);
        AntimatterRemapping.remapMachine("hatch_fluid_output", OUTPUT_HATCH);
        AntimatterRemapping.remapMachine("hatch_energy", ENERGY_HATCH);
        AntimatterRemapping.remapMachine("hatch_dynamo", DYNAMO_HATCH);
        AntimatterRemapping.remapMachine("hatch_muffler", MUFFLER_HATCH);
        AntimatterRemapping.remapMachine("steam_generator", STEAM_GENERATOR);
        AntimatterRemapping.remapMachine("gas_generator", GAS_GENERATOR);
        AntimatterRemapping.remapMachine("battery_buffer_one", BATTERY_BUFFER_ONE);
        AntimatterRemapping.remapMachine("battery_buffer_four", BATTERY_BUFFER_FOUR);
        AntimatterRemapping.remapMachine("battery_buffer_nine", BATTERY_BUFFER_EIGHT);
        remapGTCore("rubber_log", "rubber_log");
        remapGTCore("rubber_leaves", "rubber_leaves");
        remapGTCore("rubber_sapling", "rubber_sapling");
        remapGTCore("circuit_basic", "basic_circuit");
        remapGTCore("circuit_basic_electronic", "basic_circuit");
        remapGTCore("basic_integrated_circuit", "basic_circuit");
        remapGTCore("basic_electronic_circuit", "basic_circuit");
        remapGTCore("good_integrated_circuit", "good_circuit");
        remapGTCore("good_electronic_circuit", "good_circuit");
        remapGTCore("circuit_good", "good_circuit");
        remapGTCore("circuit_adv", "advanced_circuit");
        remapGTCore("plant_ball", "plantball");
        remap("circuit_nanoprocessor", "nanoprocessor");
        remap("circuit_quantumprocessor", "quantumprocessor");
        remapGTCore("circuit_energy_flow", "energy_flow_circuit");
        remap("circuit_wetware", "wetware_circuit");
        remap("vacuumtube", "vacuum_tube");
        remap("adv_circuit_parts", "advanced_circuit_parts");
        remapGTCore("board_coated", "coated_circuit_board");
        remapGTCore("board_phenolic", "phenolic_circuit_board");
        remapGTCore("board_plastic", "plastic_circuit_board");
        remapGTCore("board_epoxy", "epoxy_circuit_board");
        remapGTCore("board_fiber_reinforced", "fiber_reinforced_circuit_board");
        remapGTCore("board_multilayer_fiber_reinforced", "multilayer_fiber_reinforced_circuit_board");
        remapGTCore("board_wetware", "wetware_circuit_board");
        remapGTCore("mold_plate", "plate_mold");
        remapGTCore("mold_casing", "casing_mold");
        remapGTCore("mold_gear", "gear_mold");
        remapGTCore("mold_small_gear", "small_gear_mold");
        remapGTCore("mold_coinage", "coinage_mold");
        remapGTCore("mold_bottle", "bottle_mold");
        remapGTCore("mold_ingot", "ingot_mold");
        remapGTCore("mold_ball", "ball_mold");
        remapGTCore("mold_block", "block_mold");
        remapGTCore("mold_nugget", "nugget_mold");
        remapGTCore("mold_anvil", "anvil_mold");
        remapGTCore("shape_plate", "plate_shape");
        remapGTCore("shape_casing", "casing_shape");
        remapGTCore("shape_rod", "rod_shape");
        remapGTCore("shape_bolt", "bolt_shape");
        remapGTCore("shape_ring", "ring_shape");
        remapGTCore("shape_cell", "cell_shape");
        remapGTCore("shape_ingot", "ingot_shape");
        remapGTCore("shape_wire", "wire_shape");

        remapGTCore("shape_pipe_tiny", "tiny_pipe_shape");
        remapGTCore("shape_pipe_small", "small_pipe_shape");
        remapGTCore("shape_pipe_normal", "normal_pipe_shape");
        remapGTCore("shape_pipe_large", "large_pipe_shape");
        remapGTCore("shape_pipe_huge", "huge_pipe_shape");
        remapGTCore("shape_head_sword", "sword_head_shape");
        remapGTCore("shape_head_pickaxe", "pickaxe_head_shape");
        remapGTCore("shape_head_shovel", "shovel_head_shape");
        remapGTCore("shape_head_axe", "axe_head_shape");
        remapGTCore("shape_head_hoe", "hoe_head_shape");
        remapGTCore("shape_head_hammer", "hammer_head_shape");
        remapGTCore("shape_head_file", "file_head_shape");
        remapGTCore("shape_head_saw", "saw_head_shape");
        remapGTCore("shape_block", "block_shape");
        remapGTCore("shape_gear", "gear_shape");
        remapGTCore("shape_gear_small", "small_gear_shape");
        remapGTCore("battery_hull_small", "small_battery_hull");
        remapGTCore("battery_hull_medium", "medium_battery_hull");
        remapGTCore("battery_hull_large", "large_battery_hull");
        remapGTCore("battery_small_mercury", "small_mercury_battery");
        remapGTCore("battery_small_acid", "small_acid_battery");
        remapGTCore("battery_small_cadmium", "small_cadmium_battery");
        remapGTCore("battery_small_lithium", "small_lithium_battery");
        remapGTCore("battery_small_sodium", "small_sodium_battery");
        remapGTCore("battery_medium_mercury", "medium_mercury_battery");
        remapGTCore("battery_medium_acid", "medium_acid_battery");
        remapGTCore("battery_medium_cadmium", "medium_cadmium_battery");
        remapGTCore("battery_medium_lithium", "medium_lithium_battery");
        remapGTCore("battery_medium_sodium", "medium_sodium_battery");
        remapGTCore("battery_large_mercury", "large_mercury_battery");
        remapGTCore("battery_large_acid", "large_acid_battery");
        remapGTCore("battery_large_cadmium", "large_cadmium_battery");
        remapGTCore("battery_large_lithium", "large_lithium_battery");
        remapGTCore("battery_large_sodium", "large_sodium_battery");
        remap("coil_cupronickel", "cupronickel_coil");
        remap("coil_kanthal", "kanthal_coil");
        remap("coil_nichrome", "nichrome_coil");
        remap("coil_tungstensteel", "tungstensteel_coil");
        remap("coil_hssg", "hssg_coil");
        remap("coil_naquadah", "naquadah_coil");
        remap("coil_naquadah_alloy", "naquadah_alloy_coil");
        remap("coil_fusion", "fusion_coil");
        remap("coil_superconductor", "superconductor_coil");
        remap("casing_bronze", "bronze_casing");
        remap("casing_bricked_bronze", "bricked_bronze_casing");
        remap("casing_steel", "steel_casing");
        remap("casing_bricked_steel", "bricked_steel_casing");
        remap("casing_bronze_plated_brick", "bronze_plated_brick_casing");
        remap("casing_solid_steel", "solid_steel_casing");
        remap("casing_stainless_steel", "stainless_steel_casing");
        remap("casing_titanium", "titanium_casing");
        remap("casing_tungstensteel", "tungstensteel_casing");
        remap("casing_heat_proof", "heat_proof_casing");
        remap("casing_frost_proof", "frost_proof_casing");
        remap("casing_radiation_proof", "radiation_proof_casing");
        remap("casing_firebox_bronze", "bronze_firebox_casing");
        remap("casing_firebox_steel", "steel_firebox_casing");
        remap("casing_firebox_titanium", "titanium_firebox_casing");
        remap("casing_firebox_tungstensteel", "tungstensteel_firebox_casing");
        remap("casing_gearbox_bronze", "bronze_gearbox_casing");
        remap("casing_gearbox_steel", "steel_gearbox_casing");
        remap("casing_gearbox_titanium", "titanium_gearbox_casing");
        remap("casing_gearbox_tungstensteel", "tungstensteel_gearbox_casing");
        remap("casing_pipe_bronze", "bronze_pipe_casing");
        remap("casing_pipe_steel", "steel_pipe_casing");
        remap("casing_pipe_titanium", "titanium_pipe_casing");
        remap("casing_pipe_tungstensteel", "tungstensteel_pipe_casing");
        remap("casing_engine_intake", "engine_intake_casing");
        remap("casing_turbine_1", "steel_turbine_casing");
        remap("casing_turbine_2", "stainless_steel_turbine_casing");
        remap("casing_turbine_3", "titanium_turbine_casing");
        remap("casing_turbine_4", "tungstensteel_turbine_casing");
        AntimatterRemapping.remap(new ResourceLocation(GT5RRef.ID, "monocrystalline_silicon_boule"), new ResourceLocation(Ref.SHARED_ID, "boule_silicon"));
        remap("cell_tin", "tin_cell");
        remap("cell_steel", "steel_cell");
        remap("cell_tungstensteel", "tungstensteel_cell");
    }

    private static void remap(String oldId, String newId){
        AntimatterRemapping.remap(GT5RRef.ID, oldId, newId);
    }

    private static void remapGTCore(String oldId, String newId){
        AntimatterRemapping.remap(new ResourceLocation(GT5RRef.ID, oldId), new ResourceLocation(GTCore.ID, newId));
    }

    public static Map<String, String> getRemappingMap() {
        return REMAPPING_MAP;
    }
}
