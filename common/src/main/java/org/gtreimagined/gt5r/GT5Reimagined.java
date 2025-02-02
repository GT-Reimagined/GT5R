package org.gtreimagined.gt5r;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.AntimatterConfig;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.Ref;
import muramasa.antimatter.common.event.CommonEvents;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.event.CraftingEvent;
import muramasa.antimatter.event.ProvidersEvent;
import muramasa.antimatter.integration.jeirei.AntimatterJEIREIPlugin;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.mixin.LivingEntityAccessor;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.worldgen.IAntimatterWorldgenFunction;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gtreimagined.gt5r.block.BlockAsphalt;
import org.gtreimagined.gt5r.client.GT5RModelManager;
import org.gtreimagined.gt5r.data.ClientData;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gt5r.data.GT5RCovers;
import org.gtreimagined.gt5r.data.GT5RData;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.GT5RMachines;
import org.gtreimagined.gt5r.data.GT5RMaterialTypes;
import org.gtreimagined.gt5r.data.GT5RRecipeTypes;
import org.gtreimagined.gt5r.data.GT5RSounds;
import org.gtreimagined.gt5r.data.GT5RTags;
import org.gtreimagined.gt5r.data.Guis;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gt5r.data.MenuHandlers;
import org.gtreimagined.gt5r.data.Models;
import org.gtreimagined.gt5r.data.Structures;
import org.gtreimagined.gt5r.data.TierMaps;
import org.gtreimagined.gt5r.data.ToolTypes;
import org.gtreimagined.gt5r.datagen.GT5RBlockLootProvider;
import org.gtreimagined.gt5r.datagen.GT5RBlockTagProvider;
import org.gtreimagined.gt5r.datagen.GT5RFluidTagProvider;
import org.gtreimagined.gt5r.datagen.GT5RItemTagProvider;
import org.gtreimagined.gt5r.datagen.GT5RLocalizations;
import org.gtreimagined.gt5r.datagen.GT5RTwilightStalctites;
import org.gtreimagined.gt5r.datagen.ProgressionAdvancements;
import org.gtreimagined.gt5r.integration.AppliedEnergisticsRegistrar;
import org.gtreimagined.gt5r.integration.SpaceModRegistrar;
import org.gtreimagined.gt5r.integration.ThermalRegistrar;
import org.gtreimagined.gt5r.integration.rei.REIRegistrar;
import org.gtreimagined.gt5r.loader.LootLoader;
import org.gtreimagined.gt5r.loader.crafting.BlockParts;
import org.gtreimagined.gt5r.loader.crafting.ElectricToolRecipes;
import org.gtreimagined.gt5r.loader.crafting.MachineRecipes;
import org.gtreimagined.gt5r.loader.crafting.MaterialCrafting;
import org.gtreimagined.gt5r.loader.crafting.Miscellaneous;
import org.gtreimagined.gt5r.loader.crafting.Parts;
import org.gtreimagined.gt5r.loader.crafting.Smelting;
import org.gtreimagined.gt5r.loader.crafting.SteamMachines;
import org.gtreimagined.gt5r.loader.crafting.VanillaExtensions;
import org.gtreimagined.gt5r.loader.crafting.WireCablesPlates;
import org.gtreimagined.gt5r.loader.crafting.WoodCrafting;
import org.gtreimagined.gt5r.loader.items.Circuitry;
import org.gtreimagined.gt5r.loader.machines.AlloySmelterLoader;
import org.gtreimagined.gt5r.loader.machines.ArcFurnaceLoader;
import org.gtreimagined.gt5r.loader.machines.AssemblerLoader;
import org.gtreimagined.gt5r.loader.machines.AutoclaveLoader;
import org.gtreimagined.gt5r.loader.machines.BathLoader;
import org.gtreimagined.gt5r.loader.machines.BenderLoader;
import org.gtreimagined.gt5r.loader.machines.CannerLoader;
import org.gtreimagined.gt5r.loader.machines.CentrifugeLoader;
import org.gtreimagined.gt5r.loader.machines.ChemicalReactorLoader;
import org.gtreimagined.gt5r.loader.machines.CompressorLoader;
import org.gtreimagined.gt5r.loader.machines.CrystallizationChamberLoader;
import org.gtreimagined.gt5r.loader.machines.CutterLoader;
import org.gtreimagined.gt5r.loader.machines.DehydratorLoader;
import org.gtreimagined.gt5r.loader.machines.DistilleryLoader;
import org.gtreimagined.gt5r.loader.machines.ElectrolyzerLoader;
import org.gtreimagined.gt5r.loader.machines.ElectromagneticSeparatorLoader;
import org.gtreimagined.gt5r.loader.machines.ExtractorLoader;
import org.gtreimagined.gt5r.loader.machines.ExtruderLoader;
import org.gtreimagined.gt5r.loader.machines.FermenterLoader;
import org.gtreimagined.gt5r.loader.machines.FluidCannerLoader;
import org.gtreimagined.gt5r.loader.machines.FluidHeaterLoader;
import org.gtreimagined.gt5r.loader.machines.FluidPressLoader;
import org.gtreimagined.gt5r.loader.machines.FluidSolidifierLoader;
import org.gtreimagined.gt5r.loader.machines.ForgeHammerLoader;
import org.gtreimagined.gt5r.loader.machines.FormingPressLoader;
import org.gtreimagined.gt5r.loader.machines.LaserEngraverLoader;
import org.gtreimagined.gt5r.loader.machines.LatheLoader;
import org.gtreimagined.gt5r.loader.machines.MaceratorLoader;
import org.gtreimagined.gt5r.loader.machines.MixerLoader;
import org.gtreimagined.gt5r.loader.machines.OreByproducts;
import org.gtreimagined.gt5r.loader.machines.OreWasherLoader;
import org.gtreimagined.gt5r.loader.machines.PackagerLoader;
import org.gtreimagined.gt5r.loader.machines.PolarizerLoader;
import org.gtreimagined.gt5r.loader.machines.PrinterLoader;
import org.gtreimagined.gt5r.loader.machines.RoasterLoader;
import org.gtreimagined.gt5r.loader.machines.RockBreakerLoader;
import org.gtreimagined.gt5r.loader.machines.ScannerLoader;
import org.gtreimagined.gt5r.loader.machines.SifterLoader;
import org.gtreimagined.gt5r.loader.machines.SmelterLoader;
import org.gtreimagined.gt5r.loader.machines.ThermalCentrifugeLoader;
import org.gtreimagined.gt5r.loader.machines.UUMatterLoader;
import org.gtreimagined.gt5r.loader.machines.UnpackagerLoader;
import org.gtreimagined.gt5r.loader.machines.WiremillLoader;
import org.gtreimagined.gt5r.loader.machines.generator.Fuels;
import org.gtreimagined.gt5r.loader.machines.generator.LargeBoilerLoader;
import org.gtreimagined.gt5r.loader.machines.generator.SolidFuelBoilerLoader;
import org.gtreimagined.gt5r.loader.multi.BlastFurnaceLoader;
import org.gtreimagined.gt5r.loader.multi.CokeOvenLoader;
import org.gtreimagined.gt5r.loader.multi.CrackingUnitLoader;
import org.gtreimagined.gt5r.loader.multi.DistillationTowerLoader;
import org.gtreimagined.gt5r.loader.multi.FusionReactorLoader;
import org.gtreimagined.gt5r.loader.multi.HeatExchangerLoader;
import org.gtreimagined.gt5r.loader.multi.ImplosionCompressorLoader;
import org.gtreimagined.gt5r.loader.multi.PyrolysisOvenLoader;
import org.gtreimagined.gt5r.loader.multi.TreeGrowthSimulatorLoader;
import org.gtreimagined.gt5r.loader.multi.VacuumFreezerLoader;
import org.gtreimagined.gt5r.machine.recipe.FusionRecipeSerializer;
import org.gtreimagined.gt5r.proxy.CommonHandler;
import org.gtreimagined.gtcore.GTCore;
import org.gtreimagined.gtcore.data.GTCoreItems;

import java.util.Arrays;
import java.util.function.BiConsumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.worldgen.AntimatterWorldGenerator.removeDecoratedFeatureFromAllBiomes;

public class GT5Reimagined extends AntimatterMod {

    public static GT5Reimagined INSTANCE;
    public static Logger LOGGER = LogManager.getLogger(GT5RRef.ID);

    public GT5Reimagined() {
        super();
    }

    @Override
    public void onRegistrarInit() {
        super.onRegistrarInit();
        new AppliedEnergisticsRegistrar();
        new SpaceModRegistrar();
        LOGGER.info("Loading GT5Reimagined");
        INSTANCE = this;


        AntimatterDynamics.clientProvider(GT5RRef.ID,
                () -> new AntimatterBlockStateProvider(GT5RRef.ID, GT5RRef.NAME + " BlockStates"));
        AntimatterDynamics.clientProvider(GT5RRef.ID,
                () -> new AntimatterItemModelProvider(GT5RRef.ID, GT5RRef.NAME + " Item Models"));
        AntimatterDynamics.clientProvider(GT5RRef.ID, GT5RLocalizations.en_US::new);
        GT5RConfig.createConfig();
    }

    public static void onProviders(ProvidersEvent ev) {
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        ev.addProvider(GT5RRef.ID, () -> {
            p[0] = new GT5RBlockTagProvider(GT5RRef.ID, GT5RRef.NAME.concat(" Block Tags"), false);
            return p[0];
        });
        ev.addProvider(GT5RRef.ID, () -> new GT5RItemTagProvider(GT5RRef.ID, GT5RRef.NAME.concat(" Item Tags"),
                false, p[0]));
        ev.addProvider(GT5RRef.ID, () -> new GT5RFluidTagProvider(GT5RRef.ID,
                GT5RRef.NAME.concat(" Fluid Tags"), false));
        ev.addProvider(GT5RRef.ID, () -> new AntimatterAdvancementProvider(GT5RRef.ID,
                GT5RRef.NAME.concat(" Advancements"), new ProgressionAdvancements()));
        ev.addProvider(GT5RRef.ID,
                () -> new GT5RBlockLootProvider(GT5RRef.ID, GT5RRef.NAME.concat(" Loot generator")));
    }
    
    public static void registerCraftingLoaders(CraftingEvent event) {
        event.addLoader(Miscellaneous::loadRecipes);
        event.addLoader(Smelting::loadRecipes);
        event.addLoader(WireCablesPlates::loadRecipes);
        event.addLoader(VanillaExtensions::loadRecipes);
        event.addLoader(MachineRecipes::loadRecipes);
        event.addLoader(SteamMachines::loadRecipes);
        event.addLoader(BlockParts::loadRecipes);
        event.addLoader(Parts::loadRecipes);
        event.addLoader(Circuitry::loadCraftingRecipes);
        event.addLoader(ElectricToolRecipes::loadRecipes);
        event.addLoader(MaterialCrafting::loadRecipes);
        event.addLoader(WoodCrafting::loadRecipes);
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            event.addLoader(AppliedEnergisticsRegistrar::craftingRecipes);
        }
    }

    public static void registerRecipeLoaders(IAntimatterRegistrar registrar, IRecipeRegistrate reg) {
        BiConsumer<String, IRecipeRegistrate.IRecipeLoader> loader = (a, b) -> reg.add(GT5RRef.ID, a, b);
        loader.accept("alloy_smelter", AlloySmelterLoader::init);
        loader.accept("arc_furnace", ArcFurnaceLoader::init);
        loader.accept("assembler", AssemblerLoader::init);
        loader.accept("autoclave", AutoclaveLoader::init);
        loader.accept("bath", BathLoader::init);
        loader.accept("bender", BenderLoader::init);
        loader.accept("canner", CannerLoader::init);
        loader.accept("centrifuge", CentrifugeLoader::init);
        loader.accept("chemical_reactor", ChemicalReactorLoader::init);
        loader.accept("circuitry", Circuitry::init);
        loader.accept("solid_fuel_boiler", SolidFuelBoilerLoader::init);
        loader.accept("compressor", CompressorLoader::init);
        loader.accept("crystallization_chamber", CrystallizationChamberLoader::init);
        loader.accept("cutter", CutterLoader::init);
        loader.accept("dehydrator", DehydratorLoader::init);
        loader.accept("distillery", DistilleryLoader::init);
        loader.accept("electrolyzer", ElectrolyzerLoader::init);
        loader.accept("electromagnetic_separator", ElectromagneticSeparatorLoader::init);
        loader.accept("extractor", ExtractorLoader::init);
        loader.accept("extruder", ExtruderLoader::init);
        loader.accept("fermenter", FermenterLoader::init);
        loader.accept("fluid_canner", FluidCannerLoader::init);
        loader.accept("fluid_heater", FluidHeaterLoader::init);
        loader.accept("fluid_press", FluidPressLoader::init);
        loader.accept("fluid_solidifier", FluidSolidifierLoader::init);
        loader.accept("forge_hammer", ForgeHammerLoader::init);
        loader.accept("forming_press", FormingPressLoader::init);
        loader.accept("fuels", Fuels::init);
        loader.accept("laser_engraver", LaserEngraverLoader::init);
        loader.accept("lathe", LatheLoader::init);
        loader.accept("macerator_auto", MaceratorLoader::initAuto);
        loader.accept("macerator", MaceratorLoader::init);
        loader.accept("mixer", MixerLoader::init);
        loader.accept("ore_byproducts", OreByproducts::init);
        loader.accept("ore_washer", OreWasherLoader::init);
        loader.accept("packager", PackagerLoader::init);
        loader.accept("polarizer", PolarizerLoader::init);
        loader.accept("printer", PrinterLoader::init);
        loader.accept("roaster", RoasterLoader::init);
        loader.accept("rock_breaker", RockBreakerLoader::init);
        loader.accept("scanner", ScannerLoader::init);
        loader.accept("sifter", SifterLoader::init);
        loader.accept("smelter", SmelterLoader::init);
        loader.accept("thermal_centrifuge", ThermalCentrifugeLoader::init);
        loader.accept("uu_matter", UUMatterLoader::init);
        loader.accept("unpackager", UnpackagerLoader::init);
        loader.accept("wiremill", WiremillLoader::init);
        //multiblocks
        loader.accept("blast_furnace", BlastFurnaceLoader::init);
        loader.accept("coke_oven", CokeOvenLoader::init);
        loader.accept("cracking_unit", CrackingUnitLoader::init);
        loader.accept("distillation_tower", DistillationTowerLoader::init);
        loader.accept("fusion_reactor", FusionReactorLoader::init);
        loader.accept("heat_exchanger", HeatExchangerLoader::init);
        loader.accept("implosion_compressor", ImplosionCompressorLoader::init);
        loader.accept("large_boiler", LargeBoilerLoader::init);
        loader.accept("pyrolysis_oven", PyrolysisOvenLoader::init);
        loader.accept("tree_growth_simulator", TreeGrowthSimulatorLoader::init);
        loader.accept("vacuum_freezer", VacuumFreezerLoader::init);
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            loader.accept("ae2", AppliedEnergisticsRegistrar::machineRecipes);
        }
        if (AntimatterAPI.isModLoaded("thermal")){
            loader.accept("thermal", ThermalRegistrar::thermalMachineRecipes);
        }
    }

    public static <T> T get(Class<? extends T> clazz, String id) {
        return AntimatterAPI.get(clazz, id, GT5RRef.ID);
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        switch (event) {
            case DATA_INIT -> {
                GT5RRecipeTypes.init();
                FusionRecipeSerializer.init();
                GT5RMaterialTypes.init();
                ToolTypes.init();
                Materials.init();
                TierMaps.init();
                GT5RData.init(side);
                GT5RCovers.init();
                GT5RItems.init();
                GT5RBlocks.init();
                GT5RMachines.init();
                MenuHandlers.init();
                Guis.init(side);
                Models.init();
                GT5RSounds.init();
                if (AntimatterPlatformUtils.INSTANCE.isForge()){
                    IAntimatterWorldgenFunction function = (name, climate, category, effects, gen ,spawns) -> {
                        if (AntimatterConfig.VANILLA_ORE_GEN.get()) {
                            removeDecoratedFeatureFromAllBiomes(gen, GenerationStep.Decoration.UNDERGROUND_DECORATION, Feature.ORE, Blocks.NETHER_QUARTZ_ORE.defaultBlockState(), Blocks.NETHER_GOLD_ORE.defaultBlockState());
                        }
                    };
                    AntimatterAPI.register(IAntimatterWorldgenFunction.class, "removed_ores", GT5RRef.ID, function);
                }
                if (AntimatterAPI.isModLoaded(Ref.MOD_REI) && side.isClient()){
                    REIRegistrar.init();
                }
                CommonEvents.addPlayerTickCallback((end, logicalServer, player) -> {
                    if (!end && logicalServer && (((LivingEntityAccessor)player).getLastPos() == null || !((LivingEntityAccessor)player).getLastPos().equals(player.blockPosition()))){
                        BlockState state = player.getLevel().getBlockState(player.getOnPos());
                        AttributeInstance attributeinstance = player.getAttribute(Attributes.MOVEMENT_SPEED);
                        if (state.isAir()) state = player.getLevel().getBlockState(player.getOnPos().below());
                        if (attributeinstance == null) {
                            return;
                        }
                        if (state.is(GT5RTags.ASPHALT)){
                            if (attributeinstance.getModifier(BlockAsphalt.SPEED_MODIFIER) == null){
                                attributeinstance.addTransientModifier(new AttributeModifier(BlockAsphalt.SPEED_MODIFIER, "Asphalt speed modification", GT5RConfig.ASPHALT_MULTIPLIER.get(), AttributeModifier.Operation.MULTIPLY_BASE));
                            } 
                        } else {
                            if (attributeinstance.getModifier(BlockAsphalt.SPEED_MODIFIER) != null){
                                attributeinstance.removeModifier(BlockAsphalt.SPEED_MODIFIER);
                            }
                        }
                    }
                });
            }
            case DATA_READY -> {
                Structures.init();
                GT5RTwilightStalctites.init();
                GTRemapping.init();
                LootLoader.init();
                AntimatterJEIREIPlugin.addItemsToHide(GT5RBlocks.LAVA);
                AntimatterJEIREIPlugin.addItemsToHide(l -> {
                    IAntimatterTool screwdriver_mv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_mv", GTCore.ID);
                    IAntimatterTool screwdriver_hv = AntimatterAPI.get(IAntimatterTool.class, "electric_screwdriver_hv", GTCore.ID);
                    l.addAll(Arrays.asList(screwdriver_mv.getItem(), screwdriver_hv.getItem()));
                    l.add(GTCoreItems.BatteryRE);
                    if (!GT5RConfig.HARDER_CIRCUITS){
                        l.addAll(Arrays.asList(GT5RItems.CircuitBoardPhenolic, GT5RItems.CircuitBoardPlastic, GT5RItems.CircuitBoardFiber,
                                GT5RItems.CircuitBoardMultiFiber, GT5RItems.CircuitBoardWetware, PLATE.get(Materials.FiberReinforcedEpoxyResin),
                                GT5RItems.GlassTube, GT5RItems.VacuumTube, GT5RItems.Transistor, GT5RItems.SMDTransistor, GT5RItems.Resistor,
                                GT5RItems.SMDResistor, GT5RItems.Diode, GT5RItems.SMDDiode, GT5RItems.Capacitor, GT5RItems.SMDCapacitor,
                                GT5RItems.SmallCoil, GT5RItems.PetriDish));
                        l.addAll(Arrays.asList(GT5RItems.ProcessorAssembly, GT5RItems.Workstation, GT5RItems.CircuitWetware,
                                GT5RItems.MicroProcessor, GT5RItems.IntegratedProcessor, GT5RItems.NanoProcessor, GT5RItems.QuantumProcessor,
                                GT5RItems.NanoprocessorAssembly, GT5RItems.Mainframe, GT5RItems.QuantumProcessorAssembly, GT5RItems.CrystalProcessor));
                        l.addAll(Arrays.asList(GT5RItems.GlowstoneDopedSiliconBoule, GT5RItems.NaquadahDopedSiliconBoule, GT5RItems.GlowstoneDopedWafer, GT5RItems.NaquadahDopedWafer,
                                GT5RItems.ASoCWafer, GT5RItems.ASoC, GT5RItems.CentralProcessingUnitWafer, GT5RItems.CentralProcessingUnit, GT5RItems.HPICWafer, GT5RItems.HighPowerIC,
                                GT5RItems.IntegratedLogicCircuitWafer, GT5RItems.IntegratedLogicCircuit, GT5RItems.NANDMemoryChipWafer, GT5RItems.NANDMemoryChip,
                                GT5RItems.NanoCpuWafer, GT5RItems.NanoCpu, GT5RItems.NorMemoryChipWafer, GT5RItems.NorMemoryChip, GT5RItems.PICWafer, GT5RItems.PowerIC,
                                GT5RItems.QBitWafer, GT5RItems.QBitProcessingUnit, GT5RItems.RandomAccessMemoryChipWafer, GT5RItems.RandomAccessMemoryChip, GT5RItems.SOCWafer, GT5RItems.SOC));
                        l.addAll(GT5RMachines.CIRCUIT_ASSEMBLER.getTiers().stream().map(GT5RMachines.CIRCUIT_ASSEMBLER::getBlockState).toList());
                    }
                });
                AntimatterJEIREIPlugin.addFluidsToHide(l -> {
                    l.addAll(Arrays.asList(Materials.DinitrogenTetroxide.getGas(), Materials.Dimethylhydrazine.getLiquid(), Materials.Chloramine.getLiquid(), Materials.Dimethylamine.getGas()));
                });
                AntimatterPlatformUtils.INSTANCE.setBurnTime(GT5RBlocks.SOLID_SUPER_FUEL.asItem(), 100000);
                AntimatterPlatformUtils.INSTANCE.setBurnTime(GT5RItems.WoodPellet, 200);
                AntimatterPlatformUtils.INSTANCE.setFlammability(GT5RBlocks.WOOD_WALL, 5, 20);
                AntimatterPlatformUtils.INSTANCE.setFlammability(GT5RMachines.WOOD_TANK.getBlockState(Tier.NONE), 5, 20);
                CommonHandler.setup();
              //  if (side == Dist.CLIENT) StructureInfo.init();
                TierMaps.providerInit();
            }
            case CLIENT_DATA_INIT -> {
                ClientData.init();
                GT5RModelManager.init();
            }
        }
    }

    @Override
    public String getId() {
        return GT5RRef.ID;
    }
}
