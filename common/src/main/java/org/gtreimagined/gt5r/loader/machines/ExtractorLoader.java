package org.gtreimagined.gt5r.loader.machines;

import net.minecraft.world.item.ItemStack;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.data.GTCoreTags;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.recipe.ingredient.RecipeIngredient.of;
import static org.gtreimagined.gt5r.data.Materials.RawRubber;
import static org.gtreimagined.gt5r.data.RecipeMaps.EXTRACTOR;

public class ExtractorLoader {
    public static void init() {
        EXTRACTOR.RB().ii(of(GTCoreTags.RUBBER_LOGS,1)).io(new ItemStack(DUST.get(RawRubber),2)).add("raw_rubber",200,8);
        EXTRACTOR.RB().ii(of(GTCoreBlocks.RUBBER_LEAVES.asItem(),1)).io(new ItemStack(DUST.get(RawRubber))).add("raw_rubber_1",150,8);
        EXTRACTOR.RB().ii(of(GTCoreBlocks.RUBBER_SAPLING.asItem(),1)).io(new ItemStack(DUST.get(RawRubber))).add("raw_rubber_2",150,8);
        EXTRACTOR.RB().ii(of(GTCoreItems.StickyResin,1)).io(new ItemStack(DUST.get(RawRubber),4)).add("raw_rubber_3",200,5);
        EXTRACTOR.RB().ii(of(GTCoreItems.BatteryLargeAcid, 1).setIgnoreNbt()).io(new ItemStack(GTCoreItems.BatteryHullLarge)).add("large_acid_battery_recycling", 200, 5);
        EXTRACTOR.RB().ii(of(GTCoreItems.BatteryLargeMercury, 1).setIgnoreNbt()).io(new ItemStack(GTCoreItems.BatteryHullLarge)).add("large_mercury_battery_recycling", 200, 5);
        EXTRACTOR.RB().ii(of(GTCoreItems.BatteryMediumAcid, 1).setIgnoreNbt()).io(new ItemStack(GTCoreItems.BatteryHullMedium)).add("medium_acid_battery_recycling", 200, 5);
        EXTRACTOR.RB().ii(of(GTCoreItems.BatteryMediumMercury, 1).setIgnoreNbt()).io(new ItemStack(GTCoreItems.BatteryHullMedium)).add("medium_mercury_battery_recycling", 200, 5);
        EXTRACTOR.RB().ii(of(GTCoreItems.BatterySmallAcid, 1).setIgnoreNbt()).io(new ItemStack(GTCoreItems.BatteryHullSmall)).add("small_acid_battery_recycling", 200, 5);
        EXTRACTOR.RB().ii(of(GTCoreItems.BatterySmallMercury, 1).setIgnoreNbt()).io(new ItemStack(GTCoreItems.BatteryHullSmall)).add("small_mercury_battery_recycling", 200, 5);
    }
}
