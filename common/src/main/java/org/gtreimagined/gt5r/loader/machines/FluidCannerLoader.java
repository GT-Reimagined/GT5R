package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.item.ItemFluidCell;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import org.gtreimagined.gt5r.GT5Reimagined;
import org.gtreimagined.gt5r.data.GT5RItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreFluids;
import org.gtreimagined.gtcore.data.GTCoreItems;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.FLUID_CANNER;

public class FluidCannerLoader {
    public static void init() {
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullSmall, 1)).fi(Mercury.getLiquid(1000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatterySmallMercury)).add("battery_small_mercury",16, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullMedium, 1)).fi(Mercury.getLiquid(4000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatteryMediumMercury)).add("battery_medium_mercury",64, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullLarge, 1)).fi(Mercury.getLiquid(16000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatteryLargeMercury)).add("battery_large_mercury",258, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullSmall, 1)).fi(SulfuricAcid.getLiquid(1000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatterySmallAcid)).add("battery_small_acid",16, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullMedium, 1)).fi(SulfuricAcid.getLiquid(4000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatteryMediumAcid)).add("battery_medium_acid",64, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.BatteryHullLarge, 1)).fi(SulfuricAcid.getLiquid(16000)).io(ItemBattery.getFilledBattery(GTCoreItems.BatteryLargeAcid)).add("battery_large_acid",258, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GTCoreItems.LighterEmpty)).fi(Butane.getGas(100)).io(GTCoreItems.Lighter).add("lighter", 1, 1);
        FLUID_CANNER.RB().ii(RecipeIngredient.of(GT5RItems.TritiumEnrichedRod)).io(GT5RItems.EmptyNuclearFuelRod).fo(Tritium.getGas(500)).add("tritium_enriched_rod", 16, 16);
        FLUID_CANNER.RB().ii(GT5RItems.EmptyGeigerCounter).fi(Argon.getGas(1000)).io(GT5RItems.GeigerCounter).add("geiger_counter_argon", 64, 16);
        FLUID_CANNER.RB().ii(GT5RItems.EmptyGeigerCounter).fi(Helium.getGas(1000)).io(GT5RItems.GeigerCounter).add("geiger_counter_helium", 64, 16);
        FLUID_CANNER.RB().ii(GT5RItems.EmptyGeigerCounter).fi(Neon.getGas(1000)).io(GT5RItems.GeigerCounter).add("geiger_counter_neon", 64, 16);
        FLUID_CANNER.RB().ii(Items.HONEY_BOTTLE).io(Items.GLASS_BOTTLE).fo(Honey.getLiquid(250)).add("honey_from_honey_bottle", 20, 8);
        FLUID_CANNER.RB().ii(Items.GLASS_BOTTLE).fi(Honey.getLiquid(250)).io(Items.HONEY_BOTTLE).add("honey_bottle_from_honey", 20, 8);
        for (DyeColor dye : DyeColor.values()) {
            String dyeName = dye.getName();
            Item sprayCan = GT5Reimagined.get(Item.class, dyeName + "_spray_can");
            Material chemDye = Material.get("chemical_" + dyeName + "_dye");
            FLUID_CANNER.RB().ii(GT5RItems.EmptySprayCan).fi(chemDye.getLiquid(2304)).io(sprayCan).add(dyeName +"_spray_can", 37, 1);
        }
        FLUID_CANNER.RB().ii(GT5RItems.EmptySprayCan).fi(Chlorine.getGas(5000)).io(GT5RItems.ChlorineSprayCan).add("chlorine_spray_can", 37, 1);
        AntimatterPlatformUtils.INSTANCE.getAllFluids().forEach(fluid -> {
            Item bucket = fluid.getBucket();
            if (bucket == Items.AIR) return;
            //Only the source, so we don't get duplicates.
            if (!fluid.isSource(fluid.defaultFluidState())) return;
            ResourceLocation fluidId = AntimatterPlatformUtils.INSTANCE.getIdFromFluid(fluid);
            FLUID_CANNER.RB().ii(RecipeIngredient.of(bucket, 1)).fo(FluidPlatformUtils.createFluidStack(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier)).io(Items.BUCKET.getDefaultInstance()).add(fluidId.getNamespace() + "_" + fluidId.getPath() + "_bucket",20, 8);
            FLUID_CANNER.RB().ii(RecipeIngredient.of(Items.BUCKET, 1)).fi(FluidPlatformUtils.createFluidStack(fluid, 1000 * TesseractGraphWrappers.dropletMultiplier)).io(new ItemStack(bucket, 1)).add("bucket_from_" + fluidId.getNamespace() + "_" + fluidId.getPath(),20, 8);

            AntimatterAPI.all(ItemFluidCell.class, emptyCell -> {
                if (!emptyCell.getFilter().test(0, FluidPlatformUtils.createFluidStack(fluid, 1))) return;
                int size = emptyCell.getCapacity();
                ItemStack filled = emptyCell.fill(fluid, size);
                FLUID_CANNER.RB().ii(RecipeIngredient.of(filled)).fo(FluidPlatformUtils.createFluidStack(fluid, size * TesseractGraphWrappers.dropletMultiplier)).io(emptyCell.getDefaultInstance()).add(emptyCell.getId() + "_from_" + AntimatterPlatformUtils.INSTANCE.getIdFromFluid(fluid).getPath(),20, 8);
                FLUID_CANNER.RB().ii(RecipeIngredient.of(emptyCell, 1)).fi(FluidPlatformUtils.createFluidStack(fluid, size * TesseractGraphWrappers.dropletMultiplier)).io(filled).add(AntimatterPlatformUtils.INSTANCE.getIdFromFluid(fluid).getPath() + "_" + emptyCell.getId(),20, 8);
            });
        });
        if (AntimatterAPI.isModLoaded(Ref.MOD_TWILIGHT)){
            FLUID_CANNER.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.INSTANCE.getItemFromID(Ref.MOD_TWILIGHT, "fiery_blood"))).io(Items.GLASS_BOTTLE).fo(FluidPlatformUtils.createFluidStack(GTCoreFluids.FIERY_BLOOD.getFluid(), 250 * TesseractGraphWrappers.dropletMultiplier)).add("fiery_blood_from_fiery_blood_bottle", 20, 8);
            FLUID_CANNER.RB().ii(RecipeIngredient.of(AntimatterPlatformUtils.INSTANCE.getItemFromID(Ref.MOD_TWILIGHT, "fiery_tears"))).io(Items.GLASS_BOTTLE).fo(FluidPlatformUtils.createFluidStack(GTCoreFluids.FIERY_TEARS.getFluid(), 250 * TesseractGraphWrappers.dropletMultiplier)).add("fiery_tears_from_fiery_tears_bottle", 20, 8);
        }
    }
}
