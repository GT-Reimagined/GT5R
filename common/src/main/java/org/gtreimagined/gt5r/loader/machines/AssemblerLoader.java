package org.gtreimagined.gt5r.loader.machines;

import com.google.common.collect.ImmutableSet;
import org.gtreimagined.gt5r.data.*;
import org.gtreimagined.gt5r.data.GT5RMachines;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.MaterialTypeBlock;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.Cable;
import muramasa.antimatter.pipe.types.Wire;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.GT5Reimagined;
import org.gtreimagined.gt5r.block.BlockCasing;
import org.gtreimagined.gt5r.block.BlockCoil;
import org.gtreimagined.gt5r.block.BlockColoredWall;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import org.gtreimagined.gtcore.GTCore;
import org.gtreimagined.gtcore.data.GTCoreItems;

import java.util.Arrays;

import static muramasa.antimatter.Ref.L;
import static muramasa.antimatter.Ref.L9;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.ofObject;
import static org.gtreimagined.gt5r.data.GT5RMachines.HULL;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.ASSEMBLER;
import static org.gtreimagined.gt5r.data.TierMaps.*;
import static org.gtreimagined.gt5r.loader.crafting.Parts.fromTier;
import static org.gtreimagined.gtcore.data.GTCoreItems.*;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;

public class AssemblerLoader {
    public static void init() {
        batteries();
        casings();
        cables();
        carpet();
        coils();
        frames();
        misc();
        motors();
        pistons();
        pumps();
        rotors();
        turbines();
    }

    //TODO proper type check for the cables
    private static void batteries() {
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,1), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, LV, false) ,1)).fi(Plastic.getLiquid(L)).io(GTCoreItems.BatteryHullSmall.getDefaultInstance()).add("battery_hull_small",800, 1);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,3), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, MV, false) ,2)).fi(Plastic.getLiquid(L * 3)).io(GTCoreItems.BatteryHullMedium.getDefaultInstance()).add("battery_hull_medium",1600, 2);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(BatteryAlloy,9), ofObject(CABLE_GETTER.apply(PipeSize.VTINY, HV, false) ,4)).fi(Plastic.getLiquid(L * 9)).io(GTCoreItems.BatteryHullLarge.getDefaultInstance()).add("battery_hull_large",3200, 4);
        ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Tantalum, 1), FOIL.getMaterialIngredient(Manganese, 1)).fi(Plastic.getLiquid(L)).io(new ItemStack(GT5RItems.BatteryTantalum, 8)).add("tantalum_capacitor", 100, 4);
        ASSEMBLER.RB().ii(of(RawLapotronCrustal), of(CIRCUITS_ADVANCED, 2)).io(LapotronCrystal).add("lapotron_crystal", 16, 256);

    }

    private static void casings() {
        addTierCasing(ULV);
        addTierCasing(LV);
        addTierCasing(MV);
        addTierCasing(HV);
        addTierCasing(EV);
        addTierCasing(IV);
        addTierCasing(LUV);
        addTierCasing(ZPM);
        addTierCasing(UV);
        addTierCasing(UHV);


        addTierHull(Tier.ULV);
        addTierHull(Tier.LV);
        addTierHull(Tier.MV);
        addTierHull(Tier.HV);
        addTierHull(Tier.EV);
        addTierHull(Tier.IV);
        addTierHull(Tier.LUV);
        addTierHull(Tier.ZPM);
        addTierHull(Tier.UV);
        addTierHull(Tier.UHV);

        addWall(Steel, GT5RBlocks.STEEL_WALL);
        addWall(Invar, GT5RBlocks.INVAR_WALL);
        addWall(StainlessSteel, GT5RBlocks.STAINLESS_STEEL_WALL);
        addWall(Tungsten, GT5RBlocks.TUNGSTEN_WALL);
        addWall(Titanium, GT5RBlocks.TITANIUM_WALL);
        addWall(TungstenSteel, GT5RBlocks.TUNGSTENSTEEL_WALL);
        addWall(Netherite, GT5RBlocks.NETHERITE_WALL);

        addCasing(Bronze, GT5RBlocks.CASING_BRONZE);
        addCasing(Steel, GT5RBlocks.CASING_SOLID_STEEL);
        addCasing(StainlessSteel, GT5RBlocks.CASING_STAINLESS_STEEL);
        addCasing(Titanium, GT5RBlocks.CASING_TITANIUM);
        addCasing(TungstenSteel, GT5RBlocks.CASING_TUNGSTENSTEEL);
        addCasing(Invar, GT5RBlocks.CASING_HEAT_PROOF);
        addCasing(Aluminium, GT5RBlocks.CASING_FROST_PROOF);
        addCasing(Lead, GT5RBlocks.CASING_RADIATION_PROOF);
        addCasing(Plastic, GT5RBlocks.CASING_PLASTIC);
        addCasing(BlackBronze, GT5RBlocks.CASING_BLACK_BRONZE);
        addCasing(Tungsten, GT5RBlocks.CASING_TUNGSTEN);
        ASSEMBLER.RB().ii(of(GT5RBlocks.CASING_SOLID_STEEL), SELECTOR_TAG_INGREDIENTS.get(6)).fi(Polytetrafluoroethylene.getLiquid(L + (L / 2))).io(GT5RBlocks.CASING_CHEMICALLY_INERT.asItem()).add("chemically_inert_casing", 50, 16);
    }

    private static void cables(){
        AntimatterAPI.all(Wire.class,t -> {
            Cable<?> cable = AntimatterAPI.get(Cable.class, "cable" + "_" + t.getMaterial().getId());
            if (cable == null) return;
            ImmutableSet<PipeSize> sizes = t.getSizes();
            sizes.forEach(size -> {
                Item wireItem = t.getBlockItem(size);
                Item cableItem = cable.getBlockItem(size);
                int ct = size.getCableThickness();
                int multiplier = ct == 16 ?  5 : ct == 12 ? 4 : ct == 8 ? 3 : ct == 4 ? 2 : 1;
                long amount = L * multiplier;
                ASSEMBLER.RB().ii(of(wireItem,1), SELECTOR_TAG_INGREDIENTS.get(1)).fi(Rubber.getLiquid(amount)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_rubber",size.getCableThickness()* 20L,8);
                ASSEMBLER.RB().ii(of(wireItem,1), SELECTOR_TAG_INGREDIENTS.get(1)).fi(StyreneButadieneRubber.getLiquid((amount * 3) / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(PolyvinylChloride, multiplier)).fi(StyreneButadieneRubber.getLiquid(amount / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber_2",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(Polydimethylsiloxane, multiplier)).fi(StyreneButadieneRubber.getLiquid(amount / 4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_styrene_butadiene_rubber_3",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), SELECTOR_TAG_INGREDIENTS.get(1)).fi(SiliconeRubber.getLiquid(amount /2)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(PolyvinylChloride, multiplier)).fi(SiliconeRubber.getLiquid(amount /4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber_2",100,8);
                ASSEMBLER.RB().ii(of(wireItem,1), DUST_SMALL.getMaterialIngredient(Polydimethylsiloxane, multiplier)).fi(SiliconeRubber.getLiquid(amount /4)).io(new ItemStack(cableItem,1)).add("cable_" + t.getMaterial().getId() + "_" + size.getId() + "_silicone_rubber_3",100,8);
            });
        });
    }


    private static void coils(){
        addCoil(GT5RBlocks.COIL_CUPRONICKEL, GT5RBlocks.WIRE_CUPRONICKEL.getBlockItem(PipeSize.TINY));
        addCoil(GT5RBlocks.COIL_KANTHAL, GT5RBlocks.WIRE_KANTHAL.getBlockItem(PipeSize.TINY));
        addCoil(GT5RBlocks.COIL_NICHROME, GT5RBlocks.WIRE_NICHROME.getBlockItem(PipeSize.TINY));
        addCoil(GT5RBlocks.COIL_TUNGSTENSTEEL, GT5RBlocks.WIRE_TUNGSTEN_STEEL.getBlockItem(PipeSize.TINY));
        addCoil(GT5RBlocks.COIL_HSSG, GT5RBlocks.WIRE_HSSG.getBlockItem(PipeSize.TINY));
        addCoil(GT5RBlocks.COIL_NAQUADAH, GT5RBlocks.WIRE_NAQUADAH.getBlockItem(PipeSize.TINY));
        addCoil(GT5RBlocks.COIL_NAQUADAH_ALLOY, GT5RBlocks.WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.TINY));
        addCoil(GT5RBlocks.COIL_SUPERCONDUCTOR, GT5RBlocks.WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.TINY));
    }

    private static void frames(){
        FRAME.all().forEach(m -> {
            MaterialTypeBlock.Container f = FRAME.get().get(m);
            ASSEMBLER.RB().ii(of(ROD.get(m),4), SELECTOR_TAG_INGREDIENTS.get(4)).io(f.asItem()).add(AntimatterPlatformUtils.INSTANCE.getIdFromBlock(f.asBlock()).getPath(),40,24);
        });
    }

    private static void misc(){
        ASSEMBLER.RB().ii(GT5RItems.PrintedPages, Items.LEATHER).fi(Glue.getLiquid(20)).io(Items.WRITTEN_BOOK).fake().add("written_book", 32, 8);
        ASSEMBLER.RB().ii(of(GT5RMachines.TRANSFORMER.getItem(ULV), 8), of(GT5RMachines.TRANSFORMER.getItem(LV), 4), of(GT5RMachines.TRANSFORMER.getItem(MV), 2),
                of(GT5RMachines.TRANSFORMER.getItem(HV), 1), of(GT5RItems.ComputerMonitor), of(TIER_CIRCUITS.apply(EV), 4)).io(GT5RMachines.ADJUSTABLE_TRANSFORMER.getItem(EV)).add("ev_adjustable_transformer", 50, 1920);
        ASSEMBLER.RB().ii(of(GT5RMachines.TRANSFORMER.getItem(EV), 1), of(GT5RMachines.ADJUSTABLE_TRANSFORMER.getItem(EV), 2), of(GT5RItems.ComputerMonitor), of(TIER_CIRCUITS.apply(IV), 4)).io(GT5RMachines.ADJUSTABLE_TRANSFORMER.getItem(IV)).add("iv_adjustable_transformer", 50, 1920);
        ASSEMBLER.RB().ii(of(ItemTags.PLANKS,8), SELECTOR_TAG_INGREDIENTS.get(8)).io(new ItemStack(Items.CHEST,1)).add("chest",100,4);

        ASSEMBLER.RB().ii(of(GT5RItems.EmitterMV), PLATE.getMaterialIngredient(Aluminium, 1)).io(GT5RCovers.COVER_NEEDS_MAINTENANCE_COVER.getItem()).add("needs_maintenance_cover", 600, 24);
        ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_BARS, 2)).io(GT5RCovers.COVER_DRAIN.getItem()).add("drain",800, 16);
        ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(GT5RCovers.COVER_PUMP.getItem(LV))).io(GT5RCovers.COVER_AIR_VENT .getItem()).add("air_vent",800, 16);
        addCoverRecipe(GT5RCovers.COVER_REDSTONE_MACHINE_CONTROLLER.getItem(), of(PLATES_IRON_ALUMINIUM, 1), of(Items.LEVER, 1));
        addCoverRecipe(GT5RCovers.COVER_ENERGY_DETECTOR.getItem(), of(PLATES_IRON_ALUMINIUM, 1), of(CIRCUITS_BASIC, 1));
        addCoverRecipe(GT5RCovers.COVER_FLUID_DETECTOR.getItem(), of(PLATES_IRON_ALUMINIUM, 1), of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE, 1));
        addCoverRecipe(GT5RCovers.COVER_ITEM_DETECTOR.getItem(), of(PLATES_IRON_ALUMINIUM, 1), of(Items.LIGHT_WEIGHTED_PRESSURE_PLATE, 1));
        ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), DUST.getMaterialIngredient(Plastic, 2)).io(GT5RCovers.COVER_MUFFLER.getItem()).add("muffler", 1600, 2);
        ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 1), DUST.getMaterialIngredient(Wood, 2)).io(GT5RCovers.COVER_MUFFLER.getItem()).add("muffler_1", 1600, 2);
        ASSEMBLER.RB().ii(of(CarbonFibre, 2), SELECTOR_TAG_INGREDIENTS.get(2)).io(CarbonMesh).add("carbon_mesh", 800, 2);
        ASSEMBLER.RB().ii(of(CarbonFibre, 4), FOIL.getMaterialIngredient(Zinc, 16), SELECTOR_TAG_INGREDIENTS.get(0)).io(GT5RCovers.COVER_ITEM_FILTER.getItem()).add("item_filter", 1600, 32);
        ASSEMBLER.RB().ii(WIRE_FINE.getMaterialIngredient(Steel, 64), FOIL.getMaterialIngredient(Zinc, 16), SELECTOR_TAG_INGREDIENTS.get(0)).io(GT5RCovers.COVER_ITEM_FILTER.getItem()).add("item_filter_cheap", 1600, 32);
        ASSEMBLER.RB().ii(of(CarbonFibre, 4), FOIL.getMaterialIngredient(Zinc, 16), SELECTOR_TAG_INGREDIENTS.get(1)).io(GT5RCovers.COVER_FLUID_FILTER.getItem()).add("fluid_filter", 1600, 32);
        ASSEMBLER.RB().ii(WIRE_FINE.getMaterialIngredient(Steel, 64), FOIL.getMaterialIngredient(Zinc, 16), SELECTOR_TAG_INGREDIENTS.get(1)).io(GT5RCovers.COVER_FLUID_FILTER.getItem()).add("fluid_filter_cheap", 1600, 32);
        ASSEMBLER.RB().ii(of(PLATES_IRON_ALUMINIUM, 2), of(Items.IRON_TRAPDOOR)).io(new ItemStack(GT5RCovers.COVER_SHUTTER.getItem().getItem(), 2)).add("shutter",800, 16);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Invar, 2), of(Items.FLINT, 1)).io(GTCoreItems.LighterEmpty).add("empty_lighter", 256, 16);
        ASSEMBLER.RB().ii(of(Match, 64), of(Items.PAPER, 2)).fi(Glue.getLiquid(10)).io(MatchBook).add("matchbook", 100, 16);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Iron, 2), SELECTOR_TAG_INGREDIENTS.get(2)).io(Items.HEAVY_WEIGHTED_PRESSURE_PLATE).add("heavy_weighted_pressure_plate", 800, 16);
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(Gold, 2), SELECTOR_TAG_INGREDIENTS.get(2)).io(Items.LIGHT_WEIGHTED_PRESSURE_PLATE).add("light_weighted_pressure_plate", 800, 16);
        ASSEMBLER.RB().ii(ROD.getMaterialIngredient(Iron, 6), SELECTOR_TAG_INGREDIENTS.get(6)).io(new ItemStack(Items.IRON_BARS, 8)).add("iron_bars", 800, 16);
        ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Redstone, 1), of(GT5RItems.CellTin)).io(GT5RItems.EmptySprayCan).add("empty_spray_can", 800, 1);
        ASSEMBLER.RB().ii(of(Items.PAPER, 3), SELECTOR_TAG_INGREDIENTS.get(3)).fi(Glue.getLiquid(1000)).io(Tape).add("tape", 200, 16);
        ASSEMBLER.RB().ii(FOIL.getMaterialIngredient(Plastic, 3), SELECTOR_TAG_INGREDIENTS.get(3)).fi(Glue.getLiquid(1000)).io(DuctTape).add("duct_tape", 200, 16);
        ASSEMBLER.RB().ii(FOIL.getMaterialIngredient(Tungsten, 3), SELECTOR_TAG_INGREDIENTS.get(3)).fi(Glue.getLiquid(1000)).io(FALDuctTape).add("fal_duct_tape", 200, 16);
        ASSEMBLER.RB().ii(DUST.getMaterialIngredient(Graphite, 8), FOIL.getMaterialIngredient(Silicon, 1)).fi(Glue.getLiquid(250)).io(DUST_SMALL.get(Graphene)).add("graphene_dust", 480, 240);
    }

    private static void carpet(){
        for (DyeColor dye : DyeColor.values()){
            String dyeName = dye.getName() + "_dye";
            TagKey<Fluid> dyeLiquid = TagUtils.getFluidTag(new ResourceLocation(GT5RRef.ID, dyeName));
            ASSEMBLER.RB().ii(of(Items.STRING, 2), SELECTOR_TAG_INGREDIENTS.get(2)).fi(FluidIngredient.of(dyeLiquid, L9 + (L9 / 2))).io(new ItemStack(AntimatterPlatformUtils.INSTANCE.getItemFromID(new ResourceLocation(dye.getName() + "_carpet")), 1)).add(dye.getName() + "_carpet", 128, 5);
        }
    }

    private static void addCoverRecipe(ItemStack cover, Ingredient... inputs){
        ASSEMBLER.RB().ii(inputs).fi(SolderingAlloy.getLiquid(L / 2)).io(cover).add(AntimatterPlatformUtils.INSTANCE.getIdFromItem(cover.getItem()).getPath() + "_soldering_alloy", 800, 16);
        ASSEMBLER.RB().ii(inputs).fi(Lead.getLiquid(L * 2)).io(cover).add(AntimatterPlatformUtils.INSTANCE.getIdFromItem(cover.getItem()).getPath() + "_lead", 800, 16);
        ASSEMBLER.RB().ii(inputs).fi(Tin.getLiquid(L)).io(cover).add(AntimatterPlatformUtils.INSTANCE.getIdFromItem(cover.getItem()).getPath() + "_tin", 800, 16);
    }

    private static void motors(){
        Arrays.stream(Tier.getStandardWithIV()).forEach(t -> {
            Material magnet = (t == Tier.ULV || t == Tier.LV) ? IronMagnetic : (t == Tier.EV || t == Tier.IV ? NeodymiumMagnetic : SteelMagnetic);
            ASSEMBLER.RB().ii(ofObject(WIRE_GETTER.apply(fromTier(t), LV),4), of(ROD.get(TIER_MATERIALS.get(t)),2),
                    of(ROD.get(magnet),1)
                    , ofObject(CABLE_GETTER.apply(PipeSize.VTINY, t, false), 2)).io(new ItemStack(AntimatterAPI.get(ItemBasic.class,"motor_"+t.getId(), GTCore.ID))).add("motor_"+t.getId(),150,16);
            ASSEMBLER.RB().ii(of(GT5RCovers.COVER_PUMP.getItem(t)), of(TIER_CIRCUITS.apply(t), 2)).io(GT5RCovers.COVER_FLUID_REGULATOR.getItem(t)).add("fluid_regulator_" + t.getId(), 800, 8);
            ASSEMBLER.RB().ii(of(GT5RCovers.COVER_CONVEYOR.getItem(t)), of(TIER_CIRCUITS.apply(t), 2)).io(GT5RCovers.COVER_ITEM_REGULATOR.getItem(t)).add("item_regulator_" + t.getId(), 800, 8);
        });
    }

    private static void pistons(){
        Arrays.stream(Tier.getStandardWithIV()).forEach(t -> {
            ASSEMBLER.RB().ii(ofObject(CABLE_GETTER.apply(PipeSize.VTINY, t, false),2),
                            of(ROD.get(TIER_MATERIALS.get(t)),2),
                            of(PLATE.get(TIER_MATERIALS.get(t)),3),
                            of(AntimatterAPI.get(ItemBasic.class,"motor_"+t.getId(), GTCore.ID),1),
                            of(GEAR.get(TIER_MATERIALS.get(t)),1))
                    .io(new ItemStack(GT5Reimagined.get(ItemBasic.class,"piston_"+t.getId())))
                    .add("piston_"+t.getId(),150,16);
        });
    }
    private static void pumps(){
        Arrays.stream(Tier.getStandardWithIV()).forEach(t -> {
            ASSEMBLER.RB().ii(ofObject(CABLE_GETTER.apply(PipeSize.VTINY, t, false),1),
                            SCREW.getMaterialIngredient(TIER_ROTORS.get(t), 1),
                            ROTOR.getMaterialIngredient(TIER_ROTORS.get(t), 1),
                            RING.getMaterialIngredient(Rubber, 2),
                            of(TIER_PIPES.get(t).apply(PipeSize.NORMAL), 1),
                            of(AntimatterAPI.get(ItemBasic.class,"motor_"+t.getId(), GTCore.ID),1)
                            )
                    .io(GT5RCovers.COVER_PUMP.getItem(t))
                    .add("pump_"+t.getId(),150,16);
        });
    }

    private static void rotors(){
        ROTOR.all().forEach(r -> {
            ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(r,4),RING.getMaterialIngredient(r,1)).fi(SolderingAlloy.getLiquid(144)).io(new ItemStack(ROTOR.get(r),1)).add(r.getId() + "_rotor",240,24);
        });
    }

    private static void turbines(){
        MaterialTags.TOOLS.getAll().forEach((m,t) -> {
            if (t.toolTypes().contains(ToolTypes.SMALL_TURBINE_ROTOR)){
                ASSEMBLER.RB().ii(GT5RMaterialTypes.TURBINE_BLADE.getMaterialIngredient(m, 4), ROD_LONG.getMaterialIngredient(Magnalium, 1)).io(ToolTypes.SMALL_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_small_turbine_rotor", 320, 16);
                ASSEMBLER.RB().ii(GT5RMaterialTypes.TURBINE_BLADE.getMaterialIngredient(m, 2), GT5RMaterialTypes.SMALL_BROKEN_TURBINE_ROTOR.getMaterialIngredient(m, 1)).io(ToolTypes.SMALL_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_small_turbine_rotor_from_broken", 160, 16);
            }
            if (t.toolTypes().contains(ToolTypes.TURBINE_ROTOR)){
                ASSEMBLER.RB().ii(GT5RMaterialTypes.TURBINE_BLADE.getMaterialIngredient(m, 8), ROD_LONG.getMaterialIngredient(Titanium, 1)).io(ToolTypes.TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_turbine_rotor", 480, 64);
                ASSEMBLER.RB().ii(GT5RMaterialTypes.TURBINE_BLADE.getMaterialIngredient(m, 4), GT5RMaterialTypes.BROKEN_TURBINE_ROTOR.getMaterialIngredient(m, 1)).io(ToolTypes.TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_turbine_rotor_from_broken", 240, 64);
            }
            if (t.toolTypes().contains(ToolTypes.LARGE_TURBINE_ROTOR)){
                ASSEMBLER.RB().ii(GT5RMaterialTypes.TURBINE_BLADE.getMaterialIngredient(m, 12), ROD_LONG.getMaterialIngredient(TungstenSteel, 1)).io(ToolTypes.LARGE_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_large_turbine_rotor", 640, 64);
                ASSEMBLER.RB().ii(GT5RMaterialTypes.TURBINE_BLADE.getMaterialIngredient(m, 6), GT5RMaterialTypes.LARGE_BROKEN_TURBINE_ROTOR.getMaterialIngredient(m, 1)).io(ToolTypes.LARGE_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_large_turbine_rotor_from_broken", 320, 64);
            }
            if (t.toolTypes().contains(ToolTypes.HUGE_TURBINE_ROTOR)){
                ASSEMBLER.RB().ii(GT5RMaterialTypes.TURBINE_BLADE.getMaterialIngredient(m, 16), ROD_LONG.getMaterialIngredient(Americium, 1)).io(ToolTypes.HUGE_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_huge_turbine_rotor", 960, 256);
                ASSEMBLER.RB().ii(GT5RMaterialTypes.TURBINE_BLADE.getMaterialIngredient(m, 8), GT5RMaterialTypes.HUGE_BROKEN_TURBINE_ROTOR.getMaterialIngredient(m, 1)).io(ToolTypes.HUGE_TURBINE_ROTOR.getToolStack(m)).add(m.getId() + "_huge_turbine_rotor_from_broken", 480, 256);
            }
        });
    }

    private static void addTierCasing (Tier tier) {
        ASSEMBLER.RB().ii(of(PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 8), SELECTOR_TAG_INGREDIENTS.get(8)).io(new ItemStack(AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GT5RRef.ID))).add("casing_" + tier.getId(),50, 16);
    }

    private static void addTierHull(Tier tier) {
        Material liquid = tier == ZPM || tier == UV || tier == UHV ? Polytetrafluoroethylene : Plastic;
        ASSEMBLER.RB().ii(ofObject(CABLE_GETTER.apply(tier == Tier.UV ? PipeSize.SMALL : PipeSize.VTINY, tier, false), 2), of(AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GT5RRef.ID)))
                .fi(liquid.getLiquid(L * 2)).io(new ItemStack(HULL.getItem(tier))).add("hull_" + tier.getId(), 50, 16);
    }

    private static void addCasing (Material mat, BlockCasing casing) {
        ASSEMBLER.RB().ii(of(FRAME.get().get(mat).asItem(), 1), of(PLATE.get(mat), 6)).io(new ItemStack(casing,1)).add(AntimatterPlatformUtils.INSTANCE.getIdFromBlock(casing).getPath(),80, 30);
    }

    private static void addWall(Material mat, BlockColoredWall casing) {
        ASSEMBLER.RB().ii(PLATE.getMaterialIngredient(mat, 4), SELECTOR_TAG_INGREDIENTS.get(24)).io(new ItemStack(casing,1)).add(AntimatterPlatformUtils.INSTANCE.getIdFromBlock(casing).getPath(),80, 30);
    }

    private static void addCoil (BlockCoil coil, PipeItemBlock wire) {
        ASSEMBLER.RB().ii(of(wire,8), SELECTOR_TAG_INGREDIENTS.get(8)).io(new ItemStack(coil,1)).add(AntimatterPlatformUtils.INSTANCE.getIdFromBlock(coil).getPath(), 100, 30);
    }
}
