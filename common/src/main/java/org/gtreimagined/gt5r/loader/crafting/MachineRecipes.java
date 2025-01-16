package org.gtreimagined.gt5r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.item.ItemCover;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Wire;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.gtreimagined.gt5r.GT5RConfig;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.GT5Reimagined;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gt5r.data.GT5RCovers;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.GT5RMaterialTypes;
import org.gtreimagined.gt5r.data.GT5RTags;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gt5r.data.TierMaps;
import org.gtreimagined.gtcore.GTCore;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gtcore.data.GTCoreMaterials;
import org.gtreimagined.gtcore.machine.BarrelMachine;
import org.gtreimagined.gtcore.machine.ChestMachine;
import org.gtreimagined.gtcore.machine.LockerMachine;
import org.gtreimagined.gtcore.machine.MassStorageMachine;
import org.gtreimagined.gtcore.machine.MultiblockTankMachine;
import org.gtreimagined.gtcore.machine.WorkbenchMachine;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.machine.Tier.*;
import static org.gtreimagined.gt5r.data.GT5RMachines.*;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.TierMaps.*;
import static org.gtreimagined.gtcore.data.GTCoreItems.*;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;

public class MachineRecipes {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        addBasicMachineRecipes(output, provider);
        addHatchRecipes(output, provider);
        addMultiblockRecipes(output, provider);
        addUtilityBlockRecipes(output, provider);
        addStorageTransformerRecipes(output, provider);
    }

    private static void addBasicMachineRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item motor = AntimatterAPI.get(ItemBasic.class, "motor_"+tier.getId(), GTCore.ID);
            if (motor == null) return;
            Item piston = GT5Reimagined.get(ItemBasic.class, "piston_"+tier.getId());
            if (piston == null) return;
            Item arm = GT5Reimagined.get(ItemCover.class, "robot_arm_"+tier.getId());
            if (arm == null) return;
            Item conveyor = GT5Reimagined.get(ItemCover.class, "conveyor_"+tier.getId());
            if (conveyor == null) return;
            Item pump = GT5Reimagined.get(ItemCover.class, "pump_"+tier.getId());
            if (pump == null) return;
            Item hull = HULL.getItem(tier);
            if (hull == null) return;
            Item sensor = GT5Reimagined.get(ItemBasic.class, "sensor_"+tier.getId());
            if (sensor == null) return;
            Item emitter = GT5Reimagined.get(ItemBasic.class, "emitter_"+tier.getId());
            if (emitter == null) return;
            Item field = GT5Reimagined.get(ItemBasic.class, "field_gen_"+tier.getId());
            if (field == null) return;
            TagKey<Item> circuit = TIER_CIRCUITS.apply(tier);
            if (circuit == null) return;
            Object cable = CABLE_GETTER.apply(PipeSize.VTINY, tier, true);
            if (cable == null) return;
            TagKey<Item> rotor = TierMaps.TIER_ROTORS.get(tier) == null ? null : ROTOR.getMaterialTag(TIER_ROTORS.get(tier));
            if (rotor == null) return;
            Item glass = Items.GLASS;
            Object diamond = Items.DIAMOND;
            Material material = TIER_MATERIALS.getOrDefault(tier, Material.NULL);

            add(ALLOY_SMELTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', TierMaps.WIRE_GETTER.apply(PipeSize.SMALL, tier))
                            .put('H', hull)
                            .put('C', circuit)
                            .put('G', cable).build(), "CLC", "LHL", "GLG"));
            Tier tier2 = tier == LV ? tier : Tier.getTier(tier.getVoltage() * 4);
            add(AMP_FABRICATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', TIER_CIRCUITS.apply(tier2))
                            .put('W', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .put('H', hull)
                            .put('P', pump).build(), "WPW", "PHP", "CPC"));

            add(ARC_FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'C', circuit,
                            'W', DUST.get(Materials.Graphite),
                            'L', CABLE_GETTER.apply(PipeSize.SMALL, tier, true),
                            'H', PLATE.get(material),
                            'M', hull
                    ), "LWL", "CMC", "HHH"));
            add(ASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'R', arm,
                            'O', conveyor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "RCR", "OHO", "LCL"));


            add(AUTOCLAVE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('T', AntimatterMaterialTypes.PLATE.getMaterialTag(material))
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('P', pump).build(), "TGT", "THT", "CPC"));
            add(BENDER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'P', piston,
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "PLP", "CHC", "MLM"));
            add(CANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('P', pump)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('G', glass).build(), "LPL", "CHC", "GGG"));

            add(CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "CMC", "LHL", "CMC"));
            add(CHEMICAL_REACTOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('L', cable).build(), "GRG", "LML", "CHC"));
            add(COMPRESSOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of('C', circuit, 'P', piston, 'L', cable, 'H', hull), "LCL", "PHP", "LCL"));
            Material chamber = tier == LV || tier == MV ? Quartz : Iridium;
            Object cWire = TierMaps.WIRE_GETTER.apply(tier == MV ? PipeSize.SMALL : PipeSize.TINY, tier == LV ? MV : tier);
            add(CRYSTALLIZATION_CHAMBER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C',circuit)
                            .put('P', TIER_PIPES.get(tier).apply(PipeSize.NORMAL))
                            .put('H', hull)
                            .put('g', GT5RMaterialTypes.CHAMBER.getMaterialTag(chamber))
                            .put('W', cWire).build(), "CgC", "PHP", "WWW"));
            add(CROP_HARVESTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', arm)
                            .put('C', circuit)
                            .put('P', piston)
                            .put('H', hull)
                            .put('S', sensor)
                            .put('B', SWORD_BLADE.getMaterialTag(material))
                            .put('c', conveyor)
                            .build(), "RCR", "PHS", "BcB"));

            add(CUTTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('L', cable)
                            .put('H', hull)
                            .put('D', DiamondSawBlade)
                            .put('V', conveyor)
                            .put('G', glass).build(), "LCG", "VHD", "CLM"));
            add(DEHYDRATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier))
                            .put('C', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .put('H', hull)
                            .put('c', circuit)
                            .put('P', PLATE.getMaterialTag(material))
                            .put('R', arm).build(), "WcW", "CHC", "PRP"));
            add(DISASSEMBLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', arm)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', CABLE_GETTER.apply(PipeSize.VTINY, tier, false)).build(), "RCR", "LHL", "RCR"));
            add(DISTILLERY, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('Z', Items.BLAZE_ROD)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('L', cable).build(), "GZG", "CHC", "LPL"));

            add(ELECTRIC_OVEN, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    of('W', WIRE_GETTER.apply(PipeSize.TINY, tier), 'c', cable, 'C', circuit, 'H', hull), "WCW", "WHW", "cCc"));

            Wire<?> electroWire = tier == LV ? GT5RBlocks.WIRE_GOLD : tier == MV ? GT5RBlocks.WIRE_SILVER : tier == HV ? GT5RBlocks.WIRE_ELECTRUM : tier == EV ? GT5RBlocks.WIRE_PLATINUM : GT5RBlocks.WIRE_OSMIUM;
            add(ELECTROLYZER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'C', circuit,
                            'W', electroWire.getBlockItem(PipeSize.VTINY),
                            'L', cable,
                            'H', hull,
                            'G', glass
                    ), "WGW", "WHW", "CLC"));
            Item esWire = tier == LV ? GT5RBlocks.WIRE_TIN.getBlockItem(PipeSize.TINY) : tier == MV ? GT5RBlocks.WIRE_COPPER.getBlockItem(PipeSize.TINY) : tier == HV ? GT5RBlocks.WIRE_COPPER.getBlockItem(PipeSize.SMALL) : GT5RBlocks.WIRE_ANNEALED_COPPER.getBlockItem(PipeSize.NORMAL);
            add(ELECTROMAGNETIC_SEPARATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('c', conveyor)
                            .put('W', cable)
                            .put('w', esWire)
                            .put('H', hull)
                            .put('R', ROD.getMaterialTag(material))
                            .put('C', circuit)
                            .build(), "cWw", "WHR", "CWw"));
            add(EXTRACTOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('M', pump)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('L', cable)
                            .put('H', hull).build(), "GCG", "PHM", "LCL"));

            add(EXTRUDER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('I', TIER_PIPES.get(tier).apply(PipeSize.NORMAL))
                            .put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier))
                            .put('C', circuit)
                            .put('H', hull).build(), "WWC", "PHI", "WWC"));
            add(FERMENTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('L', cable).build(), "LPL", "GHG", "LCL"));
            add(FLUID_CANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "GCG", "PHP", "LCL"));
            add(FLUID_HEATER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('W', WIRE_GETTER.apply(PipeSize.SMALL, tier))
                            .put('L', cable).build(), "WGW", "PHP", "LCL"));
            add(FLUID_PRESS, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('T', piston)
                            .put('L', cable).build(), "GCG", "PHT", "LCL"));
            add(FLUID_SOLIDIFIER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', pump)
                            .put('C', circuit)
                            .put('G', glass)
                            .put('H', hull)
                            .put('E', ForgeCTags.CHESTS)
                            .put('L', cable).build(), "PGP", "LHL", "CEC"));
            add(FORGE_HAMMER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('A', Items.ANVIL).build(), "LPL", "CHC", "LAL"));
            add(FORMING_PRESS, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', cable)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .build(), "LPL", "CHC", "LPL"));
            add(FURNACE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of('C', circuit, 'L', cable, 'H', hull, 'W', TierMaps.WIRE_GETTER.apply(PipeSize.TINY, tier)), "CWC", "WHW", "LWL"));

            add(LATHE, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('M', motor)
                            .put('C', circuit)
                            .put('L', cable)
                            .put('H', hull)
                            .put('D', diamond).build(), "LCL", "MHD", "CLP"));
            add(LASER_ENGRAVER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "PEP", "CHC", "LCL"));
            TagKey<Item> grindHead = tier == LV || tier == MV ? GEM.getMaterialTag(Diamond) : GT5RTags.GRIND_HEADS;
            add(MACERATOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('M', motor)
                            .put('C', circuit)
                            .put('L', cable)
                            .put('H', hull)
                            .put('D', grindHead).build(), "PMD", "LLH", "CCL"));
            add(MASS_FABRICATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('F', field)
                            .put('B', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .put('C', circuit)
                            .put('H', hull).build(), "CFC", "BHB", "CFC"));
            add(MIXER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'M', motor,
                            'R', rotor,
                            'G', glass,
                            'C', circuit,
                            'H', hull
                    ), "GRG", "GMG", "CHC"));
            add(ORE_WASHER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('G', glass)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('L', cable).build(), "RGR", "CMC", "LHL"));
            add(PACKAGER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('c', conveyor)
                            .put('R', arm)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable)
                            .put('S', ForgeCTags.CHESTS).build(), "SCS", "RHc", "LCL"));
            Item wire = tier == LV ? GT5RBlocks.WIRE_TIN.getBlockItem(PipeSize.TINY) : tier == MV ? GT5RBlocks.WIRE_COPPER.getBlockItem(PipeSize.TINY) : tier == HV ? GT5RBlocks.WIRE_COPPER.getBlockItem(PipeSize.SMALL) : GT5RBlocks.WIRE_ANNEALED_COPPER.getBlockItem(PipeSize.NORMAL);
            Material rodMaterial = tier == LV ? Iron : tier == MV || tier == HV ? Steel : tier == EV ? Neodymium : VanadiumGallium;
            add(POLARIZER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', ROD.getMaterialTag(rodMaterial))
                            .put('W', wire)
                            .put('H', hull)
                            .put('L', cable).build(), "WRW", "LHL", "WRW"));
            add(PRINTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "MLM", "CHC", "LML"));

            add(PUMP, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('R', rotor)
                            .put('H', hull)
                            .put('M', motor)
                            .put('P', TIER_PIPES.get(tier).apply(PipeSize.LARGE))
                            .put('C', circuit).build(), "MCM", "PHP", "RPR"));
            add(RECYCLER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLOWSTONE_DUST)
                            .put('P', piston)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable).build(), "GCG", "PHP", "LCL"));
            add(REPLICATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('F', field)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('B', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .build(), "EFE", "CHC", "EBE"));
            add(ROASTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('S', CABLE_GETTER.apply(PipeSize.SMALL, tier, true))
                            .put('V', CABLE_GETTER.apply(PipeSize.VTINY, tier, true))
                            .put('C', circuit)
                            .put('H', hull)
                            .put('W', WIRE_GETTER.apply(PipeSize.TINY, tier)).build(), "SVS", "CHC", "WWW"));
            add(ROCK_BREAKER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('P', piston)
                            .put('M', motor)
                            .put('G', GT5RTags.GRIND_HEADS)
                            .put('C', cable)
                            .put('H', hull)
                            .put('g', glass)
                            .build(), "PMG", "CHC", "ggg"));
            add(SCANNER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('S', sensor)
                            .put('E', emitter)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', CABLE_GETTER.apply(PipeSize.VTINY, tier, false)).build(), "CEC", "LHL", "CSC"));
            TagKey<Item> plate = PLATE.getMaterialTag(tier == LV ? Steel : VanadiumSteel);
            add(SEISMIC_PROSPECTOR, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    of('P', plate, 'C', circuit, 'H', hull, 'S', sensor), "PPP", "CHC", "SSS"));
            add(SIFTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', circuit)
                            .put('P', piston)
                            .put('L', cable)
                            .put('H', hull)
                            .put('F', GT5RCovers.COVER_ITEM_FILTER.getItem())
                            .build(), "LFL", "PHP", "CFC"));
            add(SMELTER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('W', WIRE_GETTER.apply(PipeSize.TINY, tier))
                            .put('C', circuit)
                            .put('H', hull).build(), "WWW", "WHW", "WCW"));
            add(THERMAL_CENTRIFUGE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('W', TierMaps.WIRE_GETTER.apply(tier == IV ? PipeSize.HUGE : PipeSize.SMALL, tier))
                            .put('L', cable).build(), "CMC", "WHW", "LML"));
            add(UNPACKAGER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('c', conveyor)
                            .put('R', arm)
                            .put('C', circuit)
                            .put('H', hull)
                            .put('L', cable)
                            .put('S', ForgeCTags.CHESTS).build(), "SCS", "cHR", "LCL"));
            add(WIRE_MILL, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.of(
                            'M', motor,
                            'C', circuit,
                            'L', cable,
                            'H', hull
                    ), "MLM", "CHC", "MLM"));



            add(STEAM_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('C', circuit)
                            .put('P',  TIER_PIPES.get(tier).apply(PipeSize.NORMAL))
                            .build(), "PCP", "RHR", "MLM"));
            add(COMBUSTION_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('G', GEAR.getMaterialTag(material))
                            .put('C', circuit)
                            .put('P',  piston)
                            .build(), "PCP", "MHM", "GLG"));
            add(SEMIFLUID_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('G', Blocks.MAGMA_BLOCK)
                            .put('C', circuit)
                            .put('P',  PLATE.getMaterialTag(Invar))
                            .build(), "PCP", "MHM", "GLG"));
            add(GAS_GENERATOR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('M', motor)
                            .put('L', cable)
                            .put('H', hull)
                            .put('R', rotor)
                            .put('C', circuit)
                            .build(), "CRC", "RHR", "MLM"));
        });
        add(BATH, NONE, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('W', WRENCH.getTag())
                        .put('H', GT5RBlocks.CASING_HV)
                        .put('C', ITEM_CASING.getMaterialTag(StainlessSteel))
                        .put('S', PLATE.getMaterialTag(StainlessSteel))
                        .build(), "CWC", "SHS", "SSS"));
        provider.addItemRecipe(output, "trash_bin", GTCoreBlocks.ENDER_GARBAGE_BIN.getItem(NONE),
                of('O', PLATE.getMaterialTag(Obsidian), 'I', PLATE.getMaterialTag(Iron), 'E', Items.ENDER_EYE), "OOO", "OEO", "III");

        provider.addItemRecipe(output, "solar_panels", SOLAR_PANEL.getItem(NONE),
                of('S', GT5RItems.Wafer, 'G', ForgeCTags.GLASS_PANES, 'C', CIRCUITS_BASIC,
                        'P', PLATE.getMaterialTag(Carbon), 'H', HULL.getItem(ULV), 'W', GT5RBlocks.CABLE_SOLDERING_ALLOY.getBlockItem(PipeSize.VTINY)), "SGS", "CPC", "WHW");
        provider.addItemRecipe(output, "solar_panels", SOLAR_PANEL.getItem(ULV),
                of('W', GT5RItems.Wafer, 'G', ForgeCTags.GLASS_PANES, 'C', CIRCUITS_ADVANCED, 'g', GT5RBlocks.WIRE_GRAPHENE.getBlock(PipeSize.SMALL), 'P', PLATE.getMaterialTag(GalliumArsenide), 'H', HULL.getItem(ULV)), "WGW", "CgC", "PHP");
        provider.addItemRecipe(output, "solar_panels", SOLAR_PANEL.getItem(LV),
                of('W', GT5RItems.Wafer, 'G', GTCoreBlocks.REINFORCED_GLASS, 'C', TIER_CIRCUITS.apply(IV), 'g', GT5RBlocks.WIRE_GRAPHENE.getBlock(PipeSize.HUGE), 'P', PLATE.getMaterialTag(IndiumGalliumPhosphide), 'H', HULL.getItem(LV)), "WGW", "CgC", "PHP");

        provider.addItemRecipe(output, "machines", NUCLEAR_REACTOR_CORE.getItem(NONE),
                of('C', TIER_CIRCUITS.apply(Tier.IV), 'P', GT5RItems.PistonEV, 'L', GT5RBlocks.CASING_DENSE_LEAD), "PCP", "CLC", "PCP");
        provider.addItemRecipe(output, "machines", INVAR_SMALL_HEAT_EXCHANGER.getItem(NONE),
                of('L', PLATE.getMaterialTag(Lead), 'H', GT5RBlocks.CASING_HEAT_PROOF, 'P', GT5RBlocks.FLUID_PIPE_COPPER.getBlock(PipeSize.SMALL), 'C', PLATE.getMaterialTag(Copper)), "LCL", "PHP", "LCL");
        provider.addItemRecipe(output, "machines", TUNGSTEN_SMALL_HEAT_EXCHANGER.getItem(NONE),
                of('L', PLATE.getMaterialTag(Lead), 'H', GT5RBlocks.CASING_TUNGSTEN, 'P', GT5RBlocks.FLUID_PIPE_COPPER.getBlock(PipeSize.NORMAL), 'C', PLATE.getMaterialTag(Copper)), "LCL", "PHP", "LCL");
        provider.addItemRecipe(output, "machines", TUNGSTENSTEEL_SMALL_HEAT_EXCHANGER.getItem(NONE),
                of('L', PLATE.getMaterialTag(Lead), 'H', GT5RBlocks.CASING_TUNGSTENSTEEL, 'P', GT5RBlocks.FLUID_PIPE_COPPER.getBlock(PipeSize.NORMAL), 'C', PLATE.getMaterialTag(Copper)), "LCL", "PHP", "LCL");
    }

    private static void addStorageTransformerRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item hull = HULL.getItem(tier);
            if (hull == null) return;
            add(BATTERY_BUFFER_ONE, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS_WOODEN)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.VTINY)).build(), "LCL", "LHL"));

            add(BATTERY_BUFFER_FOUR, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS_WOODEN)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.SMALL)).build(), "LCL", "LHL"));

            add(BATTERY_BUFFER_EIGHT, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS_WOODEN)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.NORMAL)).build(), "LCL", "LHL"));
            add(BATTERY_BUFFER_SIXTEEN, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS_WOODEN)
                            .put('H', hull)
                            .put('L', TierMaps.TIER_WIRES.get(tier).getPipe().getType().getBlockItem(PipeSize.HUGE)).build(), "LCL", "LHL"));
        });
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.ULV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(ULV))
                        .put('C', GT5RBlocks.CABLE_SOLDERING_ALLOY.getBlockItem(PipeSize.VTINY))
                        .put('W', GT5RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(LV))
                        .put('C', GT5RBlocks.CABLE_TIN.getBlockItem(PipeSize.VTINY))
                        .put('W', GT5RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(MV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(MV))
                        .put('C', GT5RBlocks.CABLE_COPPER.getBlockItem(PipeSize.VTINY))
                        .put('W', GT5RBlocks.CABLE_GOLD.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.HV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(HV))
                        .put('C', GT5RBlocks.CABLE_GOLD.getBlockItem(PipeSize.VTINY))
                        .put('W', GT5RBlocks.CABLE_ALUMINIUM.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.EV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(EV))
                        .put('C', GT5RBlocks.CABLE_ALUMINIUM.getBlockItem(PipeSize.VTINY))
                        .put('W', GT5RBlocks.CABLE_TUNGSTEN.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");

        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.IV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(IV))
                        .put('C', GT5RBlocks.CABLE_TUNGSTEN.getBlockItem(PipeSize.VTINY))
                        .put('W', GT5RBlocks.CABLE_VANADIUM_GALLIUM.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(Tier.LUV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(LUV))
                        .put('C', GT5RBlocks.CABLE_VANADIUM_GALLIUM.getBlockItem(PipeSize.VTINY))
                        .put('W', GT5RBlocks.CABLE_NAQUADAH.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(ZPM),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(ZPM))
                        .put('C', GT5RBlocks.CABLE_NAQUADAH.getBlockItem(PipeSize.VTINY))
                        .put('W', GT5RBlocks.WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL)).build(), " CC", "WH ", " CC");
        provider.addItemRecipe(output, "machines", TRANSFORMER.getItem(UV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(UV))
                        .put('C', GT5RBlocks.WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL))
                        .put('W', GT5RBlocks.WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.VTINY)).build(), " CC", "WH ", " CC");
    }

    private static void addUtilityBlockRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item hull = HULL.getItem(tier);
            if (hull == null) return;
            Item conveyor = GT5Reimagined.get(ItemCover.class, "conveyor_"+tier.getId());
            if (conveyor == null) return;
            add(SUPER_BUFFER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('D', DataOrb)
                            .put('M', hull)
                            .put('C', conveyor).build(), "DMC"));
            add(SUPER_BUFFER, tier, (m, item) -> provider.addItemRecipe(output, GT5RRef.ID, "super_buffer_" + tier.getId() +"_1", "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('D', GT5RItems.DataStick)
                            .put('M', hull)
                            .put('C', conveyor).build(), "DMC", "DDD"));
            add(CHEST_BUFFER, tier, (m, item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('D', ForgeCTags.CHESTS_WOODEN)
                            .put('M', hull)
                            .put('C', conveyor)
                            .put('c', TIER_CIRCUITS.apply(LV)).build(), "DMC", " c "));

            add(ELECTRIC_TYPE_FILTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('H', hull)
                            .put('C', TIER_CIRCUITS.apply(HV))
                            .put('F', GT5RCovers.COVER_ITEM_FILTER.getItem())
                            .put('E', ForgeCTags.CHESTS_WOODEN)
                            .put('c', conveyor).build(), " F ", "EHc", " C "));
            add(ELECTRIC_ITEM_FILTER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('H', hull)
                            .put('C', TIER_CIRCUITS.apply(LV))
                            .put('F', GT5RCovers.COVER_ITEM_FILTER.getItem())
                            .put('E', ForgeCTags.CHESTS_WOODEN)
                            .put('c', conveyor).build(), " F ", "EHc", " C "));
        });
        provider.addItemRecipe(output, "mini_portals", MINIATURE_NETHER_PORTAL.getItem(NONE), of('O', ROD_LONG.get(Obsidian), 'S', SAW.getTag()), "OOO", "OSO", "OOO");
        provider.addItemRecipe(output, "mini_portals", MINIATURE_END_PORTAL.getItem(NONE), of('R', ROD_LONG.get(Endstone), 'G', Items.GHAST_TEAR, 'E', Items.ENDER_EYE), "ERE", "RGR", "ERE");
        if (AntimatterAPI.isModLoaded(Ref.MOD_TWILIGHT)){
            provider.addItemRecipe(output, "mini_portals", MINIATURE_TWILIGHT_PORTAL.getItem(NONE), of('R', Items.GRASS_BLOCK, 'G', Items.WATER_BUCKET, 'E', ItemTags.SMALL_FLOWERS), "ERE", "RGR", "ERE");
        }

        var circuit = GT5RConfig.HARDER_CIRCUITS ? CIRCUITS_ADVANCED : EngravedCrystalChip;
        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(Tier.LV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(LV))
                        .put('C', circuit)
                        .put('F', GT5RItems.FieldGenLV)
                        .put('P', PLATE.get(Steel)).build(), "CFC", "PHP", "CPC");

        circuit = GT5RConfig.HARDER_CIRCUITS ? CIRCUITS_COMPLEX : CIRCUITS_DATA;
        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(MV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(MV))
                        .put('C', circuit)
                        .put('F', GT5RItems.FieldGenMV)
                        .put('P', PLATE.get(Aluminium)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(Tier.HV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(HV))
                        .put('C', CIRCUITS_ELITE)
                        .put('F', GT5RItems.FieldGenHV)
                        .put('P', PLATE.get(StainlessSteel)).build(), "CFC", "PHP", "CPC");

        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(Tier.EV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(EV))
                        .put('C', CIRCUITS_MASTER)
                        .put('F', GT5RItems.FieldGenEV)
                        .put('P', PLATE.get(Titanium)).build(), "CFC", "PHP", "CPC");

        circuit = GT5RConfig.HARDER_CIRCUITS ? CIRCUITS_DATA_ORB : CIRCUITS_DATA_ORB;
        provider.addItemRecipe(output, "machines", QUANTUM_TANK.getItem(Tier.IV),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(IV))
                        .put('C', circuit)
                        .put('F', GT5RItems.FieldGenIV)
                        .put('P', PLATE.get(TungstenSteel)).build(), "CFC", "PHP", "CPC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(EV),
                of('T', TRANSFORMER.getItem(EV), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(IV),
                of('T', TRANSFORMER.getItem(IV), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(LUV),
                of('T', TRANSFORMER.getItem(LUV), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(ZPM),
                of('T', TRANSFORMER.getItem(ZPM), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_TRANSFORMER_ENDPOINT.getItem(UV),
                of('T', TRANSFORMER.getItem(UV), 'C', CABLE_GETTER.apply(PipeSize.SMALL, MV, false), 'W', WIRE_CUTTER.getTag()), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_FLUID_ENDPOINT.getItem(NONE),
                of('T', GT5RBlocks.FLUID_PIPE_TUNGSTEN.getBlock(PipeSize.NORMAL), 'C', PLATE.getMaterialTag(Plastic), 'W', GT5RBlocks.CASING_TUNGSTEN), "CTC", "TWT", "CTC");
        provider.addItemRecipe(output, "machines", LONG_DISTANCE_ITEM_ENDPOINT.getItem(NONE),
                of('T', GT5RBlocks.ITEM_PIPE_PLATINUM.getBlock(PipeSize.NORMAL), 'C', PLATE.getMaterialTag(Plastic), 'W', GT5RBlocks.CASING_PLATINUM), "CTC", "TWT", "CTC");
        AntimatterAPI.all(WorkbenchMachine.class).forEach(m -> {
            if (!m.getId().contains("charging")) {
                provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "machines", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(m.getMaterial()), 'C', ForgeCTags.CHESTS_WOODEN, 'c', Items.CRAFTING_TABLE, 'S', SCREWDRIVER.getTag()), "PSP", "PcP", "PCP");
            } else {
                provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "machines", m.getItem(HV),
                        of('S', SCREWDRIVER.getTag(), 'w', WIRE_CUTTER.getTag(), 'W', Machine.get(m.getId().replace("charging_", ""), GTCore.ID).map(mch -> mch.getItem(NONE)).orElse(Items.AIR), 'c', CABLE_GETTER.apply(PipeSize.SMALL, HV, false), 'C', CIRCUITS_ADVANCED, 'R', ROD.getMaterialTag(m.getMaterial())), "RCR", "SWw", "ccc");
            }
        });
        AntimatterAPI.all(LockerMachine.class).forEach(m -> {
            Material material = m.getMaterial();
            ChestMachine chest = AntimatterAPI.get(ChestMachine.class, material.getId() + "_chest", GTCore.ID);
            if (material.has(SCREW) && chest != null){
                if (!m.getId().contains("charging")) {
                    provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "machines", m.getItem(NONE),
                            of('s', SCREW.getMaterialTag(m.getMaterial()), 'C', chest.getItem(NONE), 'c', GT5RBlocks.CASING_SOLID_STEEL, 'S', SCREWDRIVER.getTag(), 'R', ROD.getMaterialTag(material), 'L', Items.LEATHER), "RSR", "LCL", "scs");
                } else {
                    provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "machines", m.getItem(HV),
                            of('W', Machine.get(m.getId().replace("charging_", ""), GTCore.ID).map(mch -> mch.getItem(NONE)).orElse(Items.AIR), 'c', CABLE_GETTER.apply(PipeSize.VTINY, HV, false), 'C', CIRCUITS_ADVANCED), "cCc", "cWc", "cCc");
                }
            }

        });
        AntimatterAPI.all(ChestMachine.class).forEach(m -> {
            Material material = m.getMaterial();
            if (material.has(RING) && material.has(PLATE)){
                provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "machines", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(material), 'R', ROD.getMaterialTag(material), 'r', RING.getMaterialTag(material), 'S', SAW.getTag(), 'W', WRENCH.getTag()), "SPW", "rRr", "PPP");
            }
        });
        AntimatterAPI.all(BarrelMachine.class).forEach(m -> {
            Material material = m.getMaterial();
            if (material.has(ROD) && material.has(PLATE)){
                provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "machines", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(material), 'R', ROD.getMaterialTag(material), 'S', SAW.getTag(), 'W', WRENCH.getTag()), "SPW", "PRP", " P ");
            }
        });
        AntimatterAPI.all(MassStorageMachine.class).forEach(m -> {
            Material material = m.getMaterial();
            ChestMachine chest = AntimatterAPI.get(ChestMachine.class, material.getId() + "_chest", GTCore.ID);
            if (material.has(SCREW) && material.has(PLATE) && !material.has(MaterialTags.WOOD) && chest != null){
                provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "machines", m.getItem(NONE),
                        of('C', chest.getItem(NONE), 'S', SCREW.getMaterialTag(material), 'c', GT5RBlocks.CASING_SOLID_STEEL, 's', SCREWDRIVER.getTag(), 'W', WRENCH.getTag()), "SCS", "Wcs", "SCS");
            }
        });

        AntimatterAPI.all(MultiblockTankMachine.class, GT5RRef.ID).forEach(m -> {
            if (m.isSmall()){
                Block block = AntimatterAPI.get(Block.class, m.getMaterial().getId() + "_wall", GT5RRef.ID);
                if (block == null) return;
                Material ringMaterial = m.getMaterial() == Wood ? Lead : m.getMaterial();
                TagKey<Item> hammer = m.getMaterial() == Wood ? SOFT_HAMMER.getTag() : HAMMER.getTag();
                provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "multiblock_tanks", m.getItem(NONE),
                        of('R', RING.getMaterialTag(ringMaterial), 'S', SAW.getTag(), 'H', hammer, 'W', block.asItem()), " R ", "HWS", " R ");
            } else {
                Block block = AntimatterAPI.get(Block.class, m.getId().replace("large", "small"), GT5RRef.ID);
                if (block == null) return;
                provider.addItemRecipe(output, GT5RRef.ID, m.getId(), "multiblock_tanks", m.getItem(NONE),
                        of('P', PLATE.getMaterialTag(m.getMaterial()), 'S', SAW.getTag(), 'H', HAMMER.getTag(), 'W', block.asItem()), "PPP", "HWS", "PPP");
            }
        });
        provider.addItemRecipe(output, "item_barrels", GTCoreBlocks.WOOD_ITEM_BARREL.getItem(NONE), of('S', SOFT_HAMMER.getTag(), 'C', ForgeCTags.CHESTS, 'R', ROD_LONG.getMaterialTag(Lead), 'W', ItemTags.PLANKS, 's', SAW.getTag()), "SCs", "WRW", "WRW");
        provider.addItemRecipe(output, "plastic_storage_box", GTCoreBlocks.PLASTIC_STORAGE_BOX.getItem(NONE),
                of('S', SCREW.getMaterialTag(Plastic), 'C', ForgeCTags.CHESTS_WOODEN, 'P', PLATE.getMaterialTag(Plastic)), "SPS", "PCP", "SPS");
        if (GTCoreBlocks.IRONWOOD_ITEM_BARREL != null) {
            provider.addItemRecipe(output, "item_barrels", GTCoreBlocks.IRONWOOD_ITEM_BARREL.getItem(NONE), of('S', SOFT_HAMMER.getTag(), 'C', ForgeCTags.CHESTS, 'R', ROD_LONG.getMaterialTag(Iron), 'W', PLATE.getMaterialTag(GTCoreMaterials.Ironwood), 's', SAW.getTag()), "SCs", "WRW", "WRW");
        }
    }

    private static void addHatchRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        Arrays.stream(Tier.getAllElectric()).forEach(tier -> {
            Item hull = HULL.getItem(tier);
            if (hull == null) return;
            add(INPUT_BUS, tier, (m, item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS)
                            .put('H', hull)
                            .build(), "C", "H"));

            add(INPUT_HATCH, tier, (m, item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLASS)
                            .put('H', hull)
                            .build(), "G", "H"));

            add(OUTPUT_BUS, tier, (m, item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', ForgeCTags.CHESTS)
                            .put('H', hull)
                            .build(), "H", "C"));

            add(OUTPUT_HATCH, tier, (m, item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('G', Items.GLASS)
                            .put('H', hull)
                            .build(), "H", "G"));

            add(ENERGY_HATCH, tier, (m, item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', CABLE_GETTER.apply(PipeSize.VTINY, tier, false))
                            .put('H', hull)
                            .build(), "CH"));
            add(DYNAMO_HATCH, tier, (m, item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', CABLE_GETTER.apply(PipeSize.VTINY, tier, false))
                            .put('H', hull)
                            .build(), "HC"));
            add(MUFFLER_HATCH, tier, (m, item) ->  provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('C', GT5RBlocks.FLUID_PIPE_STEEL.getBlockItem(PipeSize.NORMAL))
                            .put('H', hull)
                            .build(), "H", "C"));
        });
    }

    private static void addMultiblockRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider){
        add(BLAST_FURNACE, LV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_GETTER.apply(PipeSize.VTINY, LV, true))
                        .put('H', GT5RBlocks.CASING_HEAT_PROOF)
                        .put('C', TIER_CIRCUITS.apply(LV))
                        .put('F', Items.FURNACE)
                        .build(), "FFF", "CHC", "LCL"));
        provider.addItemRecipe(output, "machines", COKE_OVEN.getItem(COKE_OVEN.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', FireBrick).build(), "HHH", "H H", "HHH");
        add(COMBUSTION_ENGINE, EV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', GT5RBlocks.CABLE_TUNGSTEN_STEEL.getBlockItem(PipeSize.VTINY))
                        .put('H', HULL.getItem(EV))
                        .put('C', TIER_CIRCUITS.apply(EV))
                        .put('P', GT5RItems.PistonEV)
                        .put('G', GEAR.getMaterialTag(Titanium))
                        .put('M', MotorEV)
                        .build(), "PCP", "MHM", "GLG"));
        add(CRACKING_UNIT, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', GT5RCovers.COVER_PUMP.getItem(HV).getItem())
                        .put('O', GT5RBlocks.COIL_CUPRONICKEL)
                        .put('H', HULL.getItem(HV))
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .build(), "OPO", "CHC", "OPO"));
        add(DISTLLATION_TOWER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', GT5RCovers.COVER_PUMP.getItem(HV).getItem())
                        .put('I', GT5RBlocks.FLUID_PIPE_STAINLESS_STEEL.getBlock(PipeSize.LARGE))
                        .put('H', HULL.getItem(HV))
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .build(), "CIC", "PHP", "CIC"));
        add(CRYO_DISTLLATION_TOWER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', GT5RCovers.COVER_PUMP.getItem(HV).getItem())
                        .put('I', GT5RBlocks.FLUID_PIPE_COPPER.getBlock(PipeSize.LARGE))
                        .put('H', HULL.getItem(HV))
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .build(), "CIC", "PHP", "CIC"));
        add(LARGE_HEAT_EXCHANGER, NONE, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', GT5RCovers.COVER_PUMP.getItem(EV).getItem())
                        .put('I', GT5RBlocks.FLUID_PIPE_TITANIUM.getBlock(PipeSize.NORMAL))
                        .put('H', GT5RBlocks.CASING_PIPE_TITANIUM)
                        .build(), "PIP", "IHI", "PIP"));
        add(IMPLOSION_COMPRESSOR, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_GETTER.apply(PipeSize.VTINY, HV, true))
                        .put('O', Items.OBSIDIAN)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .put('S', GT5RBlocks.CASING_SOLID_STEEL)
                        .build(), "OOO", "CSC", "LCL"));
        add(LARGE_AUTOCLAVE, HV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('W', GT5RBlocks.CASING_STAINLESS_STEEL)
                        .put('D', PLATE_DENSE.getMaterialTag(StainlessSteel))
                        .put('C', TIER_CIRCUITS.apply(IV))
                        .put('c', TIER_CIRCUITS.apply(EV)).build(), "cCc", "DWD", "DDD"));
        add(LARGE_BATHING_VAT, NONE, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('W', GT5RBlocks.STAINLESS_STEEL_WALL)
                        .put('D', PLATE_DENSE.getMaterialTag(StainlessSteel))
                        .put('R', GT5RCovers.COVER_ROBOT_ARM.getItem(MV))
                        .put('C', TIER_CIRCUITS.apply(IV))
                        .put('c', TIER_CIRCUITS.apply(EV)).build(), "cCc", "DWD", "RDR"));
        add(LARGE_CENTRIFUGE, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('M', MotorEV)
                        .put('H', HULL.getItem(IV))
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .build(), "CMC", "MHM", "CMC"));
        add(LARGE_CHEMICAL_REACTOR, HV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .put('M', MotorHV)
                        .put('R', ROTOR.getMaterialTag(StainlessSteel))
                        .put('P', GT5RBlocks.FLUID_PIPE_PVC.getBlockItem(PipeSize.LARGE))
                        .put('H', HULL.getItem(HV)).build(), "CRC", "PMP", "CHC"));
        add(LARGE_ELECTROLYZER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', GT5RBlocks.WIRE_PLATINUM.getBlockItem(PipeSize.SMALL))
                        .put('O', GT5RBlocks.COIL_NICHROME)
                        .put('H', HULL.getItem(HV))
                        .put('C', TIER_CIRCUITS.apply(EV))
                        .build(), "OPO", "CHC", "OPO"));
        add(LARGE_PULVERIZER, HV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('P', GT5RItems.PistonIV)
                        .put('M', MotorIV)
                        .put('T', PLATE.getMaterialTag(TungstenCarbide))
                        .put('G', GT5RTags.GRIND_HEADS)
                        .put('H', HULL.getItem(IV))
                        .put('C', TIER_CIRCUITS.apply(IV))
                        .build(), "TGT", "PHP", "MCM"));
        add(LARGE_ORE_WASHER, EV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('G', GEAR.getMaterialTag(Titanium))
                        .put('R', ROD.getMaterialTag(Titanium))
                        .put('W', GT5RBlocks.TITANIUM_WALL)
                        .put('C', TIER_CIRCUITS.apply(IV))
                        .put('c', TIER_CIRCUITS.apply(EV)).build(), "GGG", "RWR", "cCc"));
        add(LARGE_SIFTER, EV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                of('C', TIER_CIRCUITS.apply(IV), 'M', MotorHV, 'c', GT5RCovers.COVER_CONVEYOR.getItem(HV), 'T', GT5RBlocks.CASING_TITANIUM), "McM", "cTc", "MCM"));
        Arrays.stream(getStandard()).forEach(tier -> {
            Block firebox = tier == LV ? GT5RBlocks.CASING_FIREBOX_BRONZE : tier == MV ? GT5RBlocks.CASING_FIREBOX_STEEL : tier == HV ? GT5RBlocks.CASING_FIREBOX_TITANIUM : GT5RBlocks.CASING_FIREBOX_TUNGSTENSTEEL;
            Tier circuitTier = tier == LV ? tier : tier == MV ? HV : tier == HV ? EV : IV;
            add(LARGE_BOILER, tier, (m,item) -> provider.addItemRecipe(output, "machines", item,
                    ImmutableMap.<Character, Object>builder()
                            .put('L', CABLE_GETTER.apply(PipeSize.VTINY, tier, true))
                            .put('H', firebox)
                            .put('C', TIER_CIRCUITS.apply(circuitTier))
                            .build(), "LCL", "CHC", "LCL"));
        });

        Arrays.stream(new Tier[]{HV, EV, IV}).forEach(tier -> {
            Material gear = tier == HV ? Steel : tier == EV ? StainlessSteel : tier == IV ? Titanium : TungstenSteel;
            Tier pipe = tier == UV ? IV : Tier.getTier(tier.getVoltage() / 4);
            add(LARGE_TURBINE, tier, (m, item) -> {
                provider.addItemRecipe(output, "machines", item,
                        ImmutableMap.of('G', GEAR.getMaterialTag(gear),
                                'H', HULL.getItem(tier),
                                'C', TIER_CIRCUITS.apply(tier),
                                'P', TIER_PIPES.get(pipe).apply(PipeSize.LARGE)), "CGC", "GHG", "PGP");
            });
        });
        add(MULTI_SMELTER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_GETTER.apply(PipeSize.VTINY, HV, false))
                        .put('F', Items.FURNACE)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .put('H', GT5RBlocks.CASING_HEAT_PROOF)
                        .build(), "FFF", "CHC", "LCL"));
        add(OIL_DRILLING_RIG, MV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('M', MotorMV)
                        .put('C', TIER_CIRCUITS.apply(MV))
                        .put('H', HULL.getItem(MV))
                        .put('F', FRAME.getMaterialTag(Steel)).build(), "FFF", "CHC", "MMM"));
        add(ORE_MINING_RIG, EV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder().
                        put('M', MotorEV)
                        .put('C', TIER_CIRCUITS.apply(EV))
                        .put('H', HULL.getItem(EV))
                        .put('F', FRAME.getMaterialTag(Titanium)).build(), "FFF", "CHC", "MMM"));
        provider.addItemRecipe(output, "machines", PRIMITIVE_BLAST_FURNACE.getItem(PRIMITIVE_BLAST_FURNACE.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', FireBrick)
                        .put('C', PLATE.getMaterialTag(Iron)).build(), "HHH", "HCH", "HHH");
        add(PROCESSING_ARRAY, EV, (m, item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('C', TIER_CIRCUITS.apply(LUV))
                        .put('R', GT5RCovers.COVER_ROBOT_ARM.getItem(EV))
                        .put('L', BatteryEnergyOrb)
                        .put('M', HULL.getItem(EV))
                        .put('S', GT5RBlocks.FLUID_PIPE_STAINLESS_STEEL.getBlockItem(PipeSize.LARGE)).build(), "CLC", "RMR", "CSC"));
        provider.addItemRecipe(output, "machines", PYROLYSIS_OVEN.getItem(PYROLYSIS_OVEN.getFirstTier()),
                ImmutableMap.<Character, Object>builder()
                        .put('H', HULL.getItem(MV))
                        .put('C', CIRCUITS_GOOD)
                        .put('P', GT5RCovers.COVER_PUMP.getItem(MV).getItem())
                        .put('W', GT5RBlocks.WIRE_CUPRONICKEL.getBlockItem(PipeSize.SMALL))
                        .put('B', GT5RItems.PistonMV).build(), "BCW", "CHC", "BPW");
        provider.addItemRecipe(output, "machines", TREE_GROWTH_SIMULATOR.getItem(LV),
                ImmutableMap.<Character, Object>builder()
                        .put('E', GT5RItems.EmitterLV)
                        .put('S', GT5RItems.SensorLV)
                        .put('H', GT5RBlocks.CASING_BLACK_BRONZE)
                        .put('R', DiamondSawBlade)
                        .put('P', GT5RBlocks.FLUID_PIPE_PLASTIC.getBlockItem(PipeSize.SMALL))
                        .put('N', PLATE.getMaterialTag(Nickel)).build(), "ERE", "NHN", "SPS");
        add(VACUUM_FREEZER, HV, (m,item) -> provider.addItemRecipe(output, "machines", item,
                ImmutableMap.<Character, Object>builder()
                        .put('L', CABLE_GETTER.apply(PipeSize.VTINY, HV, true))
                        .put('F', GT5RBlocks.CASING_FROST_PROOF)
                        .put('C', TIER_CIRCUITS.apply(HV))
                        .put('P', GT5RCovers.COVER_PUMP.getItem(HV).getItem())
                        .build(), "PPP", "CFC", "LCL"));
    }

    private static <T extends Machine<T>> void add(T machine, Tier tier, BiConsumer<T, Item> callback) {
        Item item = machine.getItem(tier);
        if (item != null) callback.accept(machine, item);
    }
}
