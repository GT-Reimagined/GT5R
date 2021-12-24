package muramasa.gti.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.FluidPipe;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.gti.GregTech;
import muramasa.gti.block.BlockCasing;
import muramasa.gti.data.GregTechData;
import muramasa.gti.data.Materials;
import muramasa.gti.data.TierMaps;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.machine.Tier.IV;
import static muramasa.gti.data.GregTechData.*;
import static muramasa.gti.data.Materials.*;
import static muramasa.gti.data.Machines.*;
import static muramasa.gti.data.TierMaps.*;

public class Machines {
    public static void loadRecipes(Consumer<IFinishedRecipe> output, AntimatterRecipeProvider provider) {
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item motor = GregTech.get(ItemBasic.class, "motor_"+tier.getId());
            if (motor == null) return;
            Item piston = GregTech.get(ItemBasic.class, "piston_"+tier.getId());
            if (piston == null) return;
            Item arm = GregTech.get(ItemBasic.class, "robot_arm_"+tier.getId());
            if (arm == null) return;
            Item conveyor = GregTech.get(ItemCover.class, "conveyor_"+tier.getId());
            if (conveyor == null) return;
            Item pump = GregTech.get(ItemCover.class, "pump_"+tier.getId());
            if (pump == null) return;
            Item hull = Item.BY_BLOCK.get(GregTech.get(BlockCasing.class, "hull_" + tier.getId()));
            if (hull == null) return;
            Item sensor = GregTech.get(ItemBasic.class, "sensor_"+tier.getId());
            if (sensor == null) return;
            Item emitter = GregTech.get(ItemBasic.class, "emitter_"+tier.getId());
            if (emitter == null) return;
            Item field = GregTech.get(ItemBasic.class, "field_gen_"+tier.getId());
            if (field == null) return;
            Item circuit = TIER_CIRCUITS.getOrDefault(tier, CircuitBasicElectronic);
            if (circuit == null) return;
            Item cable = TierMaps.TIER_CABLES.get(tier);
            if (cable == null) return;
            Item rotor = TierMaps.TIER_ROTORS.get(tier);
            if (rotor == null) return;
            Item glass = Items.GLASS;
            Object diamond = Items.DIAMOND;

            add(PULVERIZER, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', Items.DIAMOND).build(), "PMD", "LLH", "CCL"));

            add(ALLOY_SMELTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('L', TierMaps.WIRE_GETTER.apply(PipeSize.SMALL, tier)).put('H', hull).put('C', circuit).put('G', cable).build(), "CLC", "LHL", "GLG"));

            add(COMPRESSOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of('C', circuit, 'P', piston, 'L', cable, 'H', hull), "LCL", "PHP", "LCL"));

            add(CUTTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', DiamondSawBlade).put('V', conveyor).put('G', glass).build(), "LCG", "VHD", "CLM"));

            add(FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.of('C', circuit, 'L', cable, 'H', hull, 'W', TierMaps.WIRE_GETTER.apply(PipeSize.TINY, tier)), "CWC", "CHC", "LWL"));

            add(EXTRACTOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', pump).put('G', glass).put('C', circuit).put('L', cable).put('H', hull).build(), "GCG", "PHM", "LCL"));

            add(EXTRUDER, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('I', AntimatterAPI.get(FluidPipe.class,"fluid_"+ TierMaps.TIER_PIPE_MATERIAL.get(tier).getId()).getBlockItem(PipeSize.NORMAL)).put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier)).put('C', circuit).put('H', hull).build(), "WWC", "PHI", "WWC"));

            add(LATHE, tier, (m, item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(motor), item,
                    ImmutableMap.<Character, Object>builder().put('P', piston).put('M', motor).put('C', circuit).put('L', cable).put('H', hull).put('D', diamond).build(), "LCL", "MHD", "CLP"));

            add(ELECTROLYZER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('W', WIRE_SILVER.getBlockItem(PipeSize.VTINY))
                            .put('L', cable)
                            .put('H', hull)
                            .put('G', glass)
                            .build(), "WGW", "WHW", "CLC"));

            add(CHEMICAL_BATH, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('W', conveyor)
                            .put('P', pump)
                            .put('L', cable)
                            .put('H', hull)
                            .put('G', glass)
                    .build(), "WGL", "PGW", "CHC"));

            add(SIFTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('P', piston)
                            .put('L', cable)
                            .put('H', hull)
                            .put('F', ItemFilter)
                            .build(), "LFL", "PHP", "CFC"));

            add(BENDER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('M', motor)
                            .put('C', circuit)
                            .put('L', cable)
                            .put('H', hull)
                            .build(), "PLP", "CHC", "MLM"));

            add(WIRE_MILL, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('L', cable)
                            .put('H', hull)
                    .build(), "MLM", "CHC", "MLM"));

            add(ASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', arm)
                            .put('O', conveyor)
                            .put('C', circuit)
                            .put('L', cable)
                            .put('H', hull)
                            .build(), "RCR", "OHO", "LCL"));

            add(CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('L', cable)
                            .put('H', hull)
                            .build(), "CMC", "LHL", "CHC"));

            add(MIXER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('R', rotor)
                            .put( 'G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .build(), "GRG", "GMG", "CHC"));

            add(STEAM_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('C', circuit)
                            .put('P',  AntimatterAPI.get(FluidPipe.class,"fluid_"+ TierMaps.TIER_PIPE_MATERIAL.get(tier).getId()).getBlockItem(PipeSize.NORMAL))
                            .build(), "PCP", "RHR", "MLM"));

            add(BLAST_FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(GregTechData.MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('H', CASING_HEAT_PROOF)
                            .put('C', circuit)
                            .put('F', Items.FURNACE)
                            .build(), "FFF", "CHC", "LCL"));
            add(CANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                    .put('L', cable)
                    .put('P', pump)
                    .put('C', circuit)
                    .put('H', hull)
                    .put('G', glass).build(), "LPL", "CHC", "GGG"));
            add(RECYCLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLOWSTONE_DUST)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "GCG", "PHP", "LCL"));
            add(SCANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('S', sensor)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "CEC", "LHL", "CSC"));
            add(THERMAL_CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('W', TierMaps.WIRE_GETTER.apply(tier == IV ? PipeSize.HUGE : PipeSize.SMALL, tier))
                            .put('L', cable).build(), "CMC", "WHW", "LML"));
            add(ORE_WASHER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('L', cable).build(), "RGR", "CMC", "LHL"));
            add(CHEMICAL_REACTOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('L', cable).build(), "GRG", "LML", "CHC"));
            add(FLUID_CANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "GCG", "PHP", "LCL"));
            add(DISASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', arm)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "RCR", "LHL", "RCR"));
            add(MASS_FABRICATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('F', field)
                            .put('B', TierMaps.EXTRA_4_CABLES_TIER.get(tier))
                            .put('C', circuit)
                            .put('H', hull).build(), "CFC", "BHB", "CFC"));
            add(REPLICATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('F', field)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('B', TierMaps.EXTRA_4_CABLES_TIER.get(tier))
                            .build(), "EFE", "CHC", "EBE"));
            add(FERMENTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('L', cable).build(), "LPL", "GHG", "LCL"));
            add(FLUID_EXTRACTOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('T', piston)
                            .put('L', cable).build(), "GCG", "PHT", "LCL"));
            add(FLUID_SOLIDIFIER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('E', Items.CHEST)
                            .put('L', cable).build(), "PGP", "LHL", "CEC"));
            add(DISTILLERY, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('Z', Items.BLAZE_ROD)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('L', cable).build(), "GZG", "CHC", "LPL"));
            add(AUTOCLAVE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('T', Data.PLATE.getMaterialTag(TierMaps.TIER_MATERIALS.get(tier)))
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('P', pump).build(), "TGT", "THT", "CPC"));
            add(LASER_ENGRAVER, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "PEP", "CHC", "LCL"));
            add(BATTERY_BUFFER_ONE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.TINY)).build(), "LCL", "LHL", "   "));
            add(BATTERY_BUFFER_FOUR, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.NORMAL)).build(), "LCL", "LHL", "   "));
            add(BATTERY_BUFFER_ONE, tier, (m,item) -> provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.LARGE)).build(), "LCL", "LHL", "   "));
            add(HATCH_ITEM_I, tier, (m,item) ->  provider.addItemRecipe(output, "machines", "has_motor", provider.hasSafeItem(MotorLV), item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', Items.CHEST)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.LARGE)).build(), "LCL", "LHL", "   "));
        });
        //Alternative Assembler
        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), ASSEMBLER.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('R', RobotArmLV)
                        .put('O', GregTech.get(ItemCover.class, "conveyor_"+Tier.LV.getId()))
                        .put('C', CircuitBasic)
                        .put('L', CABLE_TIN.getBlock(PipeSize.VTINY))
                        .put('H', HULL_LV)
                .build(), "RCR", "OHO", "LCL");
        //Alternative Centrifuge
        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), CENTRIFUGE.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('M', MotorLV)
                        .put('C', CircuitBasic)
                        .put('L', CABLE_TIN.getBlock(PipeSize.VTINY))
                        .put('H', HULL_LV)
                        .build(), "CMC", "LHL", "CHC");
        //Alternative Bender
        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), BENDER.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('P', PistonLV)
                        .put('M', MotorLV)
                        .put('C', CircuitBasic)
                        .put('L', CABLE_TIN.getBlock(PipeSize.VTINY))
                        .put('H', HULL_LV)
                        .build(), "PLP", "CHC", "MLM");
        //PBF
        provider.addItemRecipe(output, "machines", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), PRIMITIVE_BLAST_FURNACE.getItem(Tier.BRONZE),
                ImmutableMap.<Character, Object>builder()
                        .put('S', SCREW.get(Iron))
                        .put('H', CASING_FIRE_BRICK)
                        .put('C', ROD.get(Tin))
                        .put('F', Items.FURNACE)
                        .put('P', PLATE.get(Steel))
                        .build(), "SPS", "HFH", "HCH");

    }

    private static <T extends Machine<T>> void add(T machine, Tier tier, BiConsumer<T, Item> callback) {
        Item item = machine.getItem(tier);
        if (item != null) callback.accept(machine, item);
    }
}
