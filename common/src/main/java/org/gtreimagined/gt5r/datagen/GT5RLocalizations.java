package org.gtreimagined.gt5r.datagen;


import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.Utils;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.block.BlockAsphalt;
import org.gtreimagined.gt5r.block.BlockAsphaltSlab;
import org.gtreimagined.gt5r.block.BlockAsphaltStair;
import org.gtreimagined.gt5r.block.BlockBedrockFlower;
import org.gtreimagined.gt5r.block.BlockCasing;
import org.gtreimagined.gt5r.block.BlockCoil;
import org.gtreimagined.gt5r.block.BlockColoredWall;
import org.gtreimagined.gt5r.block.BlockFakeCasing;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gt5r.data.GT5RCovers;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gt5r.items.ItemDepletedRod;
import org.gtreimagined.gt5r.items.ItemNuclearFuelRod;

import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpaced;
import static muramasa.antimatter.util.Utils.lowerUnderscoreToUpperSpacedRotated;
import static org.gtreimagined.gt5r.data.GT5RMachines.*;

public class GT5RLocalizations {

    public static class en_US extends AntimatterLanguageProvider {

        public en_US() {
            super(GT5RRef.ID, GT5RRef.NAME + " en_us Localization", "en_us");
        }

        @Override
        protected void addTranslations() {
            super.addTranslations();
            add(GT5RRef.ID + ".advancements.greg", "GT5Reimagined Intergalactical");
            add(GT5RRef.ID + ".advancements.greg.desc", "Getting familiar with your surroundings");
            add(GT5RRef.ID + ".rei.tooltip.ore.byproducts", "Ore Byproducts List");
            add(GT5RRef.ID + ".rei.tooltip.material_tree", "Material Tree");
            add("machine.transformer.voltage_info", "%s -> %s (Use Soft Hammer to invert)");
            add("machine.upgraded_batch.parallel", "Runs up to %s recipes at a time");
            add(GT5RRef.ID + ".rotor.tooltip.efficiency", "Turbine Efficiency: %s");
            add(GT5RRef.ID + ".rotor.tooltip.steam_flow", "Optimal Steam flow: %sL/sec");
            add(GT5RRef.ID + ".rotor.tooltip.gas_flow", "Optimal Gas flow(EU burnvalue per tick): %sEU/t");
            add("tooltip.gt5r.redstone_mode.2", "Ignore Redstone");
            add("tooltip.gt5r.redstone_mode.1", "Invert Conditional");
            add("tooltip.gt5r.redstone_mode.0", "Conditional");
            add("tooltip.gt5r.redstone_mode.normal", "Normal");
            add("tooltip.gt5r.redstone_mode.inverted", "Inverted");
            add("tooltip.gt5r.export_mode.0", "Export");
            add("tooltip.gt5r.export_mode.1", "Import");
            add("tooltip.gt5r.export_mode.2", "Export allow Import");
            add("tooltip.gt5r.export_mode.3", "Import allow Export");
            add("tooltip.gt5r.filter_mode.0", "Filter on both Export and Import");
            add("tooltip.gt5r.filter_mode.1", "Filter on Import only");
            add("tooltip.gt5r.filter_mode.2", "Filter on Export only");
            add("tooltip.gt5r.whitelist", "Whitelist");
            add("tooltip.gt5r.blacklist", "Blacklist");
            add("tooltip.gt5r.nbt.on", "Don't ignore nbt");
            add("tooltip.gt5r.nbt.off", "Ignore nbt");
            add("tooltip.gt5r.data_stick.raw_prospection_data", "Raw Prospection Data");
            add("tooltip.gt5r.data_stick.analyzed_prospection_data", "Analyzed Prospection Data");
            add("tooltip.gt5r.data_stick.by", "By X: %s Z: %s Dim: %s");
            add("tooltip.gt5r.coil.percentage", "Pyrolysis oven processing speed percentage: %s");
            add("tooltip.gt5r.coil.maxSimultaneousRecipes", "Max simultaneous recipes in Multismelter: %s");
            add("tooltip.gt5r.coil.autoclaveBoosts", "Large Autoclave processing speed: %s");
            add("tooltip.gt5r.depleted_rod.depleted", "Depleted");
            add("tooltip.gt5r.depleted_rod.0", "This Rod is %s and will not output or accept any Neutrons");
            add("tooltip.gt5r.depleted_rod.1", "Can be centrifuged to get valuable materials");
            add("tooltip.gt5r.enriched_rod.0", "Emits half the Heat per Neutron on this Rod");
            add("tooltip.gt5r.enriched_rod.1", "Breed from %s");
            add("tooltip.gt5r.breeder_rod.0", "Absorbs Neutrons to breed into an %s");
            add("tooltip.gt5r.breeder_rod.1", "Emits half the Heat per Neutron on this Rod");
            add("tooltip.gt5r.breeder_rod.2", "Can't breed with Neutrons from %s Fuel Rods");
            add("tooltip.gt5r.breeder_rod.3", "The %s value gets subtracted from Neutrons entering this Rod");
            add("tooltip.gt5r.breeder_rod.4", "This applies to each side where Neutrons enter, not to the total of all sides");
            add("tooltip.gt5r.breeder_rod.5", "Remaining Neurons on this Rod get added to the breeding process");
            add("tooltip.gt5r.breeder_rod.6", "Turns into: %s");
            add("tooltip.gt5r.breeder_rod.7", "Needed: %s %s");
            add("tooltip.gt5r.breeder_rod.8", "Loss: %s %s");
            add("tooltip.gt5r.breeder_rod.loss", "Loss");
            add("tooltip.gt5r.breeder_rod.neutrons", "Neutrons");
            add("tooltip.gt5r.breeder_rod.enriched", "Enriched Rod");
            add("tooltip.gt5r.nuclear_rod.emission_1", "Emission");
            add("tooltip.gt5r.nuclear_rod.self_1", "Self");
            add("tooltip.gt5r.nuclear_rod.maximum_1", "Maximum");
            add("tooltip.gt5r.nuclear_rod.factor_1", "Factor");
            add("tooltip.gt5r.nuclear_rod.emission_info", "The %s describes how many Neutrons are emitted to adjacent Rods");
            add("tooltip.gt5r.nuclear_rod.self_info", "The %s describes how many Neutrons naturally onto this Rod");
            add("tooltip.gt5r.nuclear_rod.maximum_info", "The %s describes how many Neutrons can be on this Rod while lasting the advertised duration");
            add("tooltip.gt5r.nuclear_rod.factor_info", "A greater %s means the Rod emits more extra Neutrons for the amount of Neutrons on it");
            add("tooltip.gt5r.nuclear_rod.remaining", "Remaining: %s Minutes");
            add("tooltip.gt5r.nuclear_rod.emission", "Emission: %s %s");
            add("tooltip.gt5r.nuclear_rod.self", "Self: %s %s");
            add("tooltip.gt5r.nuclear_rod.maximum", "Maximum: %s %s");
            add("tooltip.gt5r.nuclear_rod.neutrons", "Neutrons/t");
            add("tooltip.gt5r.nuclear_rod.factor", "Factor: %s");
            add("tooltip.gt5r.nuclear_rod.critical.0", "This fuel is %s");
            add("tooltip.gt5r.nuclear_rod.critical.1", "Critical");
            add("tooltip.gt5r.nuclear_rod.moderated.0", "Fuel rods will be %s");
            add("tooltip.gt5r.nuclear_rod.moderated.1", "Moderated");
            add("tooltip.gt5r.nuclear_rod.heat", "%s the heat per Neutron");
            add("tooltip.gt5r.nuclear_rod.when_used.1", "When used with %s:");
            add("tooltip.gt5r.nuclear_rod.when_used.2", "When used with %s or %s:");
            add("tooltip.gt5r.empty_nuclear_fuel_rod.0", "Empty Reactor Rod, transparent to Neutrons.");
            add("tooltip.gt5r.neutron_absorber_rod.0", "Absorbs Neutrons and emits twice the Heat per Neutron to Coolant");
            add("tooltip.gt5r.neutron_reflector_rod.0", "Reflects Neutrons back to their Source, boosting the Reaction");
            add("tooltip.gt5r.neutron_moderator_rod.0", "Reflects Neutrons back times the number of fuel rods touching when active");
            add("tooltip.gt5r.neutron_moderator_rod.1", "Touching Fuel Rods become moderated and moderate touching Fuel Rods");
            add("tooltip.gt5r.neutron_moderator_rod.2", "Moderated Fuel Rods can't be used for Breeding and only last a quarter as long");
            add("tooltip.gt5r.int_circuit.0", "Right click to cycle mode forward");
            add("tooltip.gt5r.int_circuit.1", "Shift right click to cycle mode backward");
            add("message.gt5r.nuclear_reactor.off", "Reactor Block is OFF");
            add("message.gt5r.nuclear_reactor.on", "Reactor Block is ON");
            add("message.gt5r.nuclear_reactor.neutron_levels", "Neutron Levels: %sn; %sn; %sn; %sn");
            add("message.gt5r.mini_portal.connect", "Target at: x: %s y: %s z: %s in %s");
            add("message.gt5r.redstone_mode.normal", "Redstone Mode: Normal");
            add("message.gt5r.redstone_mode.inverted", "Redstone Mode: Inverted");
            add("message.gt5r.needs_maintenance.scaled.normal", "Outputs a scaled signal");
            add("message.gt5r.needs_maintenance.scaled.inverted", "Outputs a scaled signal (Inverted)");
            add("message.gt5r.needs_maintenance.unscaled.normal", "Outputs a flat signal");
            add("message.gt5r.needs_maintenance.unscaled.inverted", "Outputs a flat signal (Inverted)");
            add("tooltip.gt5r.spray_can.full", "Full");
            add("tooltip.gt5r.spray_can.used", "Used");
            add("tooltip.gt5r.spray_can_chlorine", "Cleans dyed pipes.");
            add("tooltip.gt5r.spray_can.0", "Can color things in %s");
            add("tooltip.gt5r.spray_can.1", "Remaining uses: %s");
            add("tooltip.gt5r.diversity_filter.on", "Diversity Filter: ON");
            add("tooltip.gt5r.diversity_filter.off", "Diversity Filter: OFF");
            add("tooltip.gt5r.small_heat_exchanger.heat_rate", "Rate of heating: %s HU/tick");
            add("tooltip.gt5r.small_heat_exchanger.efficiency", "Efficiency: %s");
            add("tooltip.gt5r.boiler", "Generates Steam from water.");
            add("tooltip.macerator.0", "Crushes Raw ores down to crushed ore and byproducts. Macerator gives no byproducts, Pulverizer gives byproducts");
            add("text.gt5r.prospected_book", "Prospection Data From:\nX: %s Z: %s Dim: %s\nProduces %sL %s");
            add("jei.category.macerator_recycling", "Macerator Recycling");
            add("jei.category.macerator_ore_processing", "Macerator Ore Processing");
            add("jei.category.arc_furnace_recycling", "Arc Furnace Recycling");
            add("jei.category.alloy_smelter_molds", "Alloy Smelter Molds");
            structureTranslations();
            advancements();
        }

        private void structureTranslations(){
            add("tooltip.electric_blast_furnace.0", "Controller Block for the Electric Blast Furnace");
            add("tooltip.electric_blast_furnace.1", "Size(WxHxD): 3x4x3 (Hollow) Controller (Front middle bottom)");
            add("tooltip.electric_blast_furnace.2", "16x Heating Coils (2 middle Layers, hollow)");
            add("tooltip.electric_blast_furnace.3", "1x Input Bus (Any bottom layer casing)");
            add("tooltip.electric_blast_furnace.4", "1x Output Bus (Any bottom layer casing)");
            add("tooltip.electric_blast_furnace.5", "1x Energy Hatch (Any bottom layer casing)");
            add("tooltip.electric_blast_furnace.6", "1x Muffler Hatch (Top middle)");
            add("tooltip.electric_blast_furnace.7", "Heat Proof Casings for the rest");
            add("tooltip.electric_blast_furnace.8", "Each 900K over the min. Heat Capacity grants 5% speedup (multiplicatively)");
            add("tooltip.electric_blast_furnace.9", "Each 1800K over the min. Heat Capacity allows for one upgraded overclock");
            add("tooltip.electric_blast_furnace.10", "Upgraded overclocks reduce recipe time to 25% and increase EU/t to 400%");

            add("tooltip.combustion_engine.0", "Controller Block for the Large Combustion Engine");
            add("tooltip.combustion_engine.1", "Size(WxHxD): 3x3x4, Controller (front centered)");
            add("tooltip.combustion_engine.2", "3x3x4 of Stable Titanium Casing (hollow, Min 16!)");
            add("tooltip.combustion_engine.3", "2x Titanium Gear Box Casing inside the Hollow Casing");
            add("tooltip.combustion_engine.4", "8x Engine Intake Casings (around controller)");
            add("tooltip.combustion_engine.5", "2x Input Hatch (one of the Casings next to a gearbox)");
            add("tooltip.combustion_engine.6", "1x Muffler Hatch (Top middle back, next to the rear gearbox)");
            add("tooltip.combustion_engine.7", "1x Dynamo Hatch (back centered) ");
            add("tooltip.combustion_engine.8", "Engine Intake Casings not obstructed (only air blocks)");
            add("tooltip.combustion_engine.9", "Supply Flammable Fuels and 1000L of Lubricant per hour to run");
            add("tooltip.combustion_engine.10", "Supply 40L of Oxygen per second to boost output (optional)");
            add("tooltip.combustion_engine.11", "Default: Produces 2048EU/t at 100% efficiency");
            add("tooltip.combustion_engine.12", "Boosted: Produces 6144EU/t at 150% efficiency");

            add("tooltip.cracking_unit.0", "Controller Block for the Oil Cracking Unit");
            add("tooltip.cracking_unit.1", "Size(WxHxD): 5x3x3 (Hollow) Controller (Front center)");
            add("tooltip.cracking_unit.2", "Ring of 8 Cupronickel Coils (Each side of Controller)");
            add("tooltip.cracking_unit.3", "1x Input Hatch (Left side center casing)(For hydrocarbons");
            add("tooltip.cracking_unit.4", "1x Input Hatch (Any middle ring casing)(For steam/Hydrogen)");
            add("tooltip.cracking_unit.5", "1x Output Hatch (Right side center casing) (Outputs cracked product");
            add("tooltip.cracking_unit.6", "1x Energy Hatch (Any middle ring casing)");
            add("tooltip.cracking_unit.7", "Clean Stainless Steel Casings for the rest (18 at least!)");
            add("tooltip.cracking_unit.8", "Optional 1x Item Hatch Input or 1x Item Hatch Output  on the middle Ring Casings");

            add("tooltip.distillation_tower.0", "Controller Block for the Distillation Tower");
            add("tooltip.distillation_tower.1", "Size(WxHxD): 3xhx3(with h ranging from 3 to 12)");
            add("tooltip.distillation_tower.2", "Controller (Front bottom)");
            add("tooltip.distillation_tower.3", "1x Input Hatch (Any bottom layer casing)");
            add("tooltip.distillation_tower.4", "2-11x Output Hatch (One per layer except bottom layer)");
            add("tooltip.distillation_tower.5", "1x Output Bus (Any bottom layer casing)");
            add("tooltip.distillation_tower.6", "1x Energy Hatch (Any bottom layer casing)");
            add("tooltip.distillation_tower.7", "Clean Stainless Steel Casings for the rest (7 x h - 4 at least)");

            add("tooltip.cryo_distillation_tower.0", "Controller Block for the Cryo Distillation Tower");
            add("tooltip.cryo_distillation_tower.1", "Size(WxHxD): 3xhx3(with h ranging from 3 to 12)");
            add("tooltip.cryo_distillation_tower.2", "Controller (Front bottom)");
            add("tooltip.cryo_distillation_tower.3", "1x Input Hatch (Any bottom layer casing)");
            add("tooltip.cryo_distillation_tower.4", "2-11x Output Hatch (One per layer except bottom layer)");
            add("tooltip.cryo_distillation_tower.5", "1x Output Bus (Any bottom layer casing)");
            add("tooltip.cryo_distillation_tower.6", "1x Energy Hatch (Any bottom layer casing)");
            add("tooltip.cryo_distillation_tower.7", "Frostproof Casings for the rest (7 x h - 4 at least)");

            add("tooltip.large_heat_exchanger.0", "Controller Block for the Heat Exchanger");
            add("tooltip.large_heat_exchanger.1", "Size(WxHxD): 3x4x3, Controller (Front middle at bottom)");
            add("tooltip.large_heat_exchanger.2", "3x3x4 of Stable Titanium Casings (hollow, min 24!)");
            add("tooltip.large_heat_exchanger.3", "1x Distiller Water Input (Any mid layer casing)");
            add("tooltip.large_heat_exchanger.4", "1x Steam Output (Any mid layer casing)");
            add("tooltip.large_heat_exchanger.5", "1x Hot Fluid Input (Bottom Center)");
            add("tooltip.large_heat_exchanger.6", "1x Cold Fluid Output (Top Center)");

            add("tooltip.implosion_compressor.0", "Controller Block for the Implosion Compressor");
            add("tooltip.implosion_compressor.1", "Size(WxHxD): 3x3x3 (Hollow), Controller (Front centered)");
            add("tooltip.implosion_compressor.2", "1x Input Bus (Any casing)");
            add("tooltip.implosion_compressor.3", "1x Output Bus (Any casing)");
            add("tooltip.implosion_compressor.4", "1x Muffler Hatch (Any casing)");
            add("tooltip.implosion_compressor.5", "1x Energy Hatch (Any casing)");
            add("tooltip.implosion_compressor.6", "Solid Steel Casings for the rest (17 at least!)");

            add("tooltip.large_autoclave.0", "Controller Block for the LArge Autoclave");
            add("tooltip.large_autoclave.1", "Runs up to 16 Items at once");
            add("tooltip.large_autoclave.2", "Size(WxHxD):3x3x3 (Hollow), Controller (Front middle at bottom)");
            add("tooltip.large_autoclave.3", "1x Coil at Bottom center");
            add("tooltip.large_autoclave.4", "1x Input Bus (Any casing)");
            add("tooltip.large_autoclave.5", "1x Output Bus (Any casing)");
            add("tooltip.large_autoclave.6", "1x Input Hatch (Any casing)");
            add("tooltip.large_autoclave.7", "1x Energy Hatch (Any casing)");
            add("tooltip.large_autoclave.8", "Stainless Steel Casings for the rest");

            add("tooltip.large_bathing_vat.0", "Controller Block for the Large Bathing Vat");
            add("tooltip.large_bathing_vat.1", "Runs up to 64 Items at once");
            add("tooltip.large_bathing_vat.2", "Size(WxHxD): 5x2x5, Controller (Front middle at bottom)");
            add("tooltip.large_bathing_vat.3", "1x Input Bus (Any casing)");
            add("tooltip.large_bathing_vat.4", "1x Output Bus (Any casing)");
            add("tooltip.large_bathing_vat.5", "1x Input Hatch (Any casing)");
            add("tooltip.large_bathing_vat.6", "1-3x Output Hatch (Any casing, optional)");
            add("tooltip.large_bathing_vat.7", "Stainless Steel Walls for the rest (46 at most!)");

            add("tooltip.large_boiler.0", "Controller Block for the Large Boiler");
            add("tooltip.large_boiler.1", "Produces %sL of Steam with 1 Coal at %sL/s");
            add("tooltip.large_boiler.2", "A programmed circuit in the main block throttles the boiler (-1000L/s per config)");
            add("tooltip.large_boiler.3", "Size(WxHxD): 3x5x3, Controller (Front middle in fireboxes)");
            add("tooltip.large_boiler.4", "3x1x3 of %s (Bottom layer, Min 4)");
            add("tooltip.large_boiler.5", "3x4x3 of %s (above Fireboxes, hollow, Min 24!)");
            add("tooltip.large_boiler.6", "1x3x1 of %s (Inside the Hollow Casings/Plated Bricks)");
            add("tooltip.large_boiler.7", "1x Fuel Input Hatch (Any Firebox)");
            add("tooltip.large_boiler.8", "1x Water Input Hatch (Any Firebox)");
            add("tooltip.large_boiler.9", "1x Steam Output Hatch (Any Casing)");
            add("tooltip.large_boiler.10", "1x Muffler Hatch (Any Firebox)");
            add("tooltip.large_boiler.11", "Diesel fuels have 1/4 efficiency");
            add("tooltip.large_boiler.12", "Takes %s seconds to heat up");

            add("tooltip.large_centrifuge.0", "Controller Block for the Large Centrifuge");
            add("tooltip.large_centrifuge.1", "Runs up to 16 recipes at a time");
            add("tooltip.large_centrifuge.2", "Size(WxHxD): 3x2x3, Controller (Front middle at bottom)");
            add("tooltip.large_centrifuge.3", "1x Input Hatch/Bus (Any casing except top middle)");
            add("tooltip.large_centrifuge.4", "1x Output Hatch/Bus (Any casing except top middle)");
            add("tooltip.large_centrifuge.5", "1x Energy Hatch (Any casing except top middle)");
            add("tooltip.large_centrifuge.6", "Tungstensteel Casings for the rest (14 at most!)");

            add("tooltip.large_chemical_reactor.0", "Controller Block for the Large Chemical Reactor");
            add("tooltip.large_chemical_reactor.1", "Does not lose efficiency when overclocked");
            add("tooltip.large_chemical_reactor.2", "Size(WxHxD): 3x3x3");
            add("tooltip.large_chemical_reactor.3", "3x3x3 of Chemically Inert Casings (hollow, min 8!)");
            add("tooltip.large_chemical_reactor.4", "Controller (Front centered)");
            add("tooltip.large_chemical_reactor.5", "1x PTFE Pipe Casing (inside the hollow casings)");
            add("tooltip.large_chemical_reactor.6", "1x Cupronickel Coil Block (Next to the PTFE Pipe Casing)");
            add("tooltip.large_chemical_reactor.7", "1x Input Hatch (Any inert casing)");
            add("tooltip.large_chemical_reactor.8", "1x Output Hatch (Any inert casing)");
            add("tooltip.large_chemical_reactor.9", "1x Energy Hatch (Any inert casing)");

            add("tooltip.large_electrolyzer.0", "Controller Block for the Large Electrolyzer");
            add("tooltip.large_electrolyzer.1", "Runs up to x recipes at a time (x is dependent on the coils used)");
            add("tooltip.large_electrolyzer.2", "Size(WxHxD): 5x2x5, Controller (Front middle at bottom)");
            add("tooltip.large_electrolyzer.3", "3x3 of Electrolytic Cells in top middle");
            add("tooltip.large_electrolyzer.4", "6 Nichrome coils to the left and right of the electrolytic cells(3 per side)");
            add("tooltip.large_electrolyzer.5", "1x Input Hatch/Bus (Any casing except top middle)");
            add("tooltip.large_electrolyzer.6", "1x Output Hatch/Bus (Any casing except top middle)");
            add("tooltip.large_electrolyzer.7", "1x Energy Hatch (Any casing except top middle)");
            add("tooltip.large_electrolyzer.8", "Stainless Steel Casings for the rest (32 at most!)");

            add("tooltip.large_pulverizer.0", "Controller Block for the Large Pulverizer(AKA Macerator)");
            add("tooltip.large_pulverizer.1", "Runs up to 16 recipes at a time");
            add("tooltip.large_pulverizer.2", "Size(WxHxD): 5x3x5, Controller (Front middle at bottom)");
            add("tooltip.large_pulverizer.3", "5x3x5 Basin of Tungstensteel Walls (Min 53!)");
            add("tooltip.large_pulverizer.4", "3x2x3 of Grinding Wheels (Inside basin, exactly 18!)");
            add("tooltip.large_pulverizer.5", "1x Input Bus (Any Casing)");
            add("tooltip.large_pulverizer.6", "1x Output Bus (Any Casing)");
            add("tooltip.large_pulverizer.7", "1x HV+ Energy Hatch (Any Casing)");

            add("tooltip.large_sifter.0", "Controller Block for the Large Sifter");
            add("tooltip.large_sifter.1", "Runs up to 64 recipes at a time");
            add("tooltip.large_sifter.2", "Size(WxHxD): 5x7x3 Controller (Front middle at bottom)");
            add("tooltip.large_sifter.3", "5x7x3 Basin of Titanium Casings (Max 78!)");
            add("tooltip.large_sifter.4", "3x6x1 of Filter Casings (Inside basin, exactly 18!)");
            add("tooltip.large_sifter.5", "1x Input Bus (Any Casing on top layer)");
            add("tooltip.large_sifter.6", "6x Output Bus (1 per layer except top layer)");
            add("tooltip.large_sifter.7", "1x HV+ Energy Hatch (Any Casing on bottom layer)");

            add("tooltip.large_turbine.0", "Controller Block for the %s");
            add("tooltip.large_turbine.1", "Size(WxHxD): 3x3x4 (Hollow), Controller (Front centered)");
            add("tooltip.large_turbine.2", "1x Input Hatch (Side centered)");
            add("tooltip.large_turbine.3", "1x Muffler Hatch (Side centered)");
            add("tooltip.large_turbine.4", "1x Dynamo Hatch (Back centered)");
            add("tooltip.large_turbine.5", "%s for the rest (24 at least!)");
            add("tooltip.large_turbine.6", "Needs a Turbine Item (Inside controller GUI)");
            add("tooltip.large_turbine.7", "Output depending on Rotor: %sEU/t");

            add("tooltip.multi_smelter.0", "Controller Block for the Multi Smelter");
            add("tooltip.multi_smelter.1", "Can make alloys if a selector tag with id of 1 is placed in the controller.");
            add("tooltip.multi_smelter.2", "Smelts up to 8-1024 Items at once");
            add("tooltip.multi_smelter.3", "Size(WxHxD):3x3x3 (Hollow), Controller (Front middle at bottom)");
            add("tooltip.multi_smelter.4", "8x Heating Coils (Middle layer, hollow)");
            add("tooltip.multi_smelter.5", "1x Input Bus (One of bottom)");
            add("tooltip.multi_smelter.6", "1x Output Bus (One of bottom)");
            add("tooltip.multi_smelter.7", "1x Muffler Hatch (Top middle)");
            add("tooltip.multi_smelter.8", "1x Energy Hatch (One of bottom)");
            add("tooltip.multi_smelter.9", "Heat Proof Casings for the rest");

            add("tooltip.oil_drilling_rig.0", "Controller Block for the Oil Drilling Rig");
            add("tooltip.oil_drilling_rig.1", "Size(WxHxD):3x7x3");
            add("tooltip.oil_drilling_rig.2", "Controller (Front middle at bottom)");
            add("tooltip.oil_drilling_rig.3", "3x1x3 Base of Solid Steel Casings");
            add("tooltip.oil_drilling_rig.4", "1x3x1 Solid Steel Casing Pillar (Center of base)");
            add("tooltip.oil_drilling_rig.5", "1x3x1 Steel Frames (Each pillar side and on top)");
            add("tooltip.oil_drilling_rig.6", "1x Output Hatch (Any bottom layer casing)");
            add("tooltip.oil_drilling_rig.7", "1x MV+ Energy Hatch (Any bottom layer casing)");
            add("tooltip.oil_drilling_rig.8", "1x Output Bus (Any bottom layer casing, optional)");

            add("tooltip.ore_mining_rig.0", "Controller Block for the Ore Mining Rig");
            add("tooltip.ore_mining_rig.1", "Formerly known as Advanced Miner II in gt5u");
            add("tooltip.ore_mining_rig.2", "Size(WxHxD):3x7x3");
            add("tooltip.ore_mining_rig.3", "Controller (Front middle at bottom)");
            add("tooltip.ore_mining_rig.4", "3x1x3 Base of Solid Steel Casings");
            add("tooltip.ore_mining_rig.5", "1x3x1 Solid Steel Casing Pillar (Center of base)");
            add("tooltip.ore_mining_rig.6", "1x3x1 Steel Frames (Each pillar side and on top)");
            add("tooltip.ore_mining_rig.7", "1x Input Hatch for drilling fluid (Any bottom layer casing)");
            add("tooltip.ore_mining_rig.8", "1x MV+ Energy Hatch (Any bottom layer casing)");
            add("tooltip.ore_mining_rig.9", "1x Output Bus (Any bottom layer casing)");
            add("tooltip.ore_mining_rig.10", "Radius is 48 blocks");

            add("tooltip.processing_array.0", "Controller Block for the Processing Array");
            add("tooltip.processing_array.1", "Runs supplied machines as if placed in the world");
            add("tooltip.processing_array.2", "Size(WxHxD):3x3x3 (Hollow), Controller (Front centered)");
            add("tooltip.processing_array.3", "1x Input Hatch/Bus (Any casing)");
            add("tooltip.processing_array.4", "1x Output Hatch/Bus (Any Casing)");
            add("tooltip.processing_array.5", "1x Energy Hatch (Any Casing)");
            add("tooltip.processing_array.6", "Robust Tungstensteel Casings for the rest");
            add("tooltip.processing_array.7", "Place up to 16 Single Block Machines into the GUI Inventory");

            add("tooltip.vacuum_freezer.0", "Controller Block for the Vacuum Freezer");
            add("tooltip.vacuum_freezer.1", "Super cools hot ingots and gases like Nitrogen");
            add("tooltip.vacuum_freezer.2", "Size(WxHxD):3x3x3 (Hollow), Controller (Front centered)");
            add("tooltip.vacuum_freezer.3", "1x Input Hatch/Bus (Any casing)");
            add("tooltip.vacuum_freezer.4", "1x Output Hatch/Bus (Any Casing)");
            add("tooltip.vacuum_freezer.5", "1x Energy Hatch (Any Casing)");
            add("tooltip.vacuum_freezer.6", "Frost Proof Casings for the rest");

            add("tooltip.fusion_control_computer.0", "It's over 9000!!!");
            add("tooltip.fusion_control_computer.1", "Fusion Casings around Fusion Coils");
            add("tooltip.fusion_control_computer.2", "2-16 Input Hatches/Buses");
            add("tooltip.fusion_control_computer.3", "1-16 Output Hatches/Buses");
            add("tooltip.fusion_control_computer.4", "1-16 Energy Hatches");
            add("tooltip.fusion_control_computer.5", "All Energy Hatches must be LuV or better");
            add("tooltip.fusion_control_computer.6", "8192EU/t and 40mio EU Cap per Energy Hatch");

        }

        private void advancements(){
            addAdvancement("gt5r", "GT5R", "Advancements of GT5R");
            addAdvancement("rock", "Pickup a rock of some kind");
            addAdvancement("flint_pickaxe", "No wood tools allowed!", "Craft a flint pickaxe");
            addAdvancement("raw_copper", "Find some copper ore and mine it");
            addAdvancement("raw_tin", "Find some tin ore and mine it");
            addAdvancement("mortar", "Craft a mortar");
            addAdvancement("bronze_dust", "Make some bronze dust using a mortar");
            addAdvancement("hammer", "Craft a hammer");
            addAdvancement("bronze_solid_fuel_boiler", "Craft a Solid Fuel Boiler");
            addAdvancement("bronze_steam_forge_hammer", "Craft a Steam Forge Hammer");
            addAdvancement("bronze_steam_compressor", "Craft a Steam Compressor");
            addAdvancement("fire_brick", "Craft a Fire Brick");
            addAdvancement("coke_oven", "Craft a Coke Oven");
        }

        private void addAdvancement(String key, String translatedTitle, String translatedDesc){
            add("gt5r.advancements." + key + ".title", translatedTitle);
            add("gt5r.advancements." + key + ".desc", translatedDesc);
        }

        private void addAdvancement(String key, String translatedDesc){
            add("gt5r.advancements." + key + ".title", Utils.lowerUnderscoreToUpperSpaced(key));
            add("gt5r.advancements." + key + ".desc", translatedDesc);
        }

        @Override
        protected void english(String domain, String locale) {
            super.english(domain, locale);
            AntimatterAPI.all(BlockCasing.class, domain).forEach(i -> {
                if (i.getId().startsWith("casing_") || i.getId().startsWith("hull_")){
                    add(i, lowerUnderscoreToUpperSpacedRotated(i.getId()));
                    return;
                }
                if (i.getId().contains("long_distance_cable")){
                    String tier = i.getId().replace("long_distance_cable_", "");
                    add(i, "Long Distance Cable (" + tier.toUpperCase() + ")");
                }
                add(i, lowerUnderscoreToUpperSpaced(i.getId()));
            });
            AntimatterAPI.all(BlockBedrockFlower.class, domain).forEach(b -> {
                if (b == GT5RBlocks.PRINCES_PLUME){
                    add(b, "Prince's Plume");
                } else {
                    add(b, lowerUnderscoreToUpperSpaced(b.getId()));
                }
                String material= b.tooltipMaterial != Material.NULL ? b.tooltipMaterial == Materials.Uranium ? "Uranium" : b.tooltipMaterial.getDisplayNameString() : "Ore";
                String n = material.startsWith("A") || material.startsWith("E") || material.startsWith("I") || material.startsWith("O") || material.startsWith("U") ? "n" : "";
                add("tooltip." + b.getDomain() + "." + b.getId().replace("/", "."), "Indicates presence of a" + n + " " + material + " Deposit nearby");
            });

            add(GT5RBlocks.MINING_PIPE, "Mining Pipe");
            add(GT5RBlocks.MINING_PIPE_THIN, "Mining Pipe");
            add(GT5RBlocks.BRITTLE_CHARCOAL, "Brittle Charcoal");
            add(GT5RBlocks.SOLID_SUPER_FUEL, "Solid Super Fuel");
            AntimatterAPI.all(BlockFakeCasing.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockColoredWall.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockAsphalt.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockAsphaltSlab.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockAsphaltStair.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(BlockCoil.class, domain).forEach(i -> add(i, lowerUnderscoreToUpperSpaced(i.getId())));
            AntimatterAPI.all(ItemBasic.class, domain).forEach(i -> override(i.getDescriptionId(), lowerUnderscoreToUpperSpaced(i.getId())
                    .replace("Lv", "(LV)")
                    .replace("Mv", "(MV)")
                    .replace("Hv", "(HV)")
                    .replace("Ev", "(EV)")
                    .replace("Iv", "(IV)")));
            AntimatterAPI.all(ItemNuclearFuelRod.class, domain).forEach(i -> override(i.getDescriptionId(), Utils.getLocalizedType(i.getMaterial()) + " Fuel Rod"));
            AntimatterAPI.all(ItemDepletedRod.class, domain).forEach(i -> override(i.getDescriptionId(), "Depleted " + Utils.getLocalizedType(i.getMaterial()) + " Fuel Rod"));

//            AntimatterAPI.all(ItemPowerUnit.class, domain).stream().filter(i -> i.getId().startsWith("power_unit") || i.getId().startsWith("small_power_unit")).forEach(i -> override(i.getDescriptionId(), lowerUnderscoreToUpperSpaced(i.getId())));
            override(LARGE_TURBINE, HV, "Large Steam Turbine");
            override(LARGE_TURBINE, EV, "Large Gas Turbine");
            override(LARGE_TURBINE, IV, "Large HP Steam Turbine");
            override(LARGE_BOILER, LV, "Large Bronze Boiler");
            override(LARGE_BOILER, MV, "Large Steel Boiler");
            override(LARGE_BOILER, HV, "Large Titanium Boiler");
            override(LARGE_BOILER, EV, "Large Tungstensteel Boiler");
            override(MACERATOR, HV, "Pulverizer (%s)");
            override(MACERATOR, EV, "Pulverizer (%s)");
            override(GT5RItems.EmptyGeigerCounter.getDescriptionId(), "Geiger Counter (Empty)");
            add(GT5RBlocks.POWDER_BARREL, "Powder Barrel");
            override(GT5RBlocks.CASING_BRONZE_PLATED_BRICK.getDescriptionId(), "Bronze Plated Bricks");
            override("machine.hull", "%s " + HULL.getLang(locale));
            override(GT5RCovers.COVER_REDSTONE_CONDUCTOR_ACCEPT.getItem().getDescriptionId(), "Redstone Conductor (Accept)");
            override(GT5RCovers.COVER_REDSTONE_CONDUCTOR_EMIT.getItem().getDescriptionId(), "Redstone Conductor (Emit)");
            HULL.getTiers().forEach(tier -> {
                override(HULL, tier, tier.getId().toUpperCase() + " " + HULL.getLang(locale));
            });
        }

        @Override
        protected void overrides() {
            String[] fluids = new String[]{"hot_molten_lithium_chloride", "hot_molten_tin", "hot_molten_sodium"};
            for (String s : fluids) {
                override("fluid_type.antimatter_shared.liquid_" + s, Utils.lowerUnderscoreToUpperSpaced(s));
                override("item.antimatter_shared.liquid_" + s + "_bucket", Utils.lowerUnderscoreToUpperSpaced(s) + " Bucket");
            }
            override("fluid_type.antimatter_shared.liquid_nitrogen", Utils.lowerUnderscoreToUpperSpaced("liquid_nitrogen"));
            override("item.antimatter_shared.liquid_nitrogen_bucket", Utils.lowerUnderscoreToUpperSpaced("liquid_nitrogen") + " Bucket");
            override("fluid_type.antimatter_shared.concrete", "Wet Concrete");
            override("item.antimatter_shared.concrete_bucket", "Wet Concrete Bucket");
            override(Ref.ID, "jei.category.pulverizer", "Macerator/Pulverizer");
        }
    }

}
