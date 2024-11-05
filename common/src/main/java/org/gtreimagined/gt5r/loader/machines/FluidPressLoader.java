package org.gtreimagined.gt5r.loader.machines;

import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreFluids;
import tesseract.FluidPlatformUtils;
import tesseract.TesseractGraphWrappers;

import static muramasa.antimatter.Ref.L;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.RecipeMaps.FLUID_PRESS;

public class FluidPressLoader {
    public static void init() {
        FLUID_PRESS.RB().ii(RecipeIngredient.of(TagUtils.getForgelikeItemTag("seeds"))).fo(SeedOil.getLiquid(10)).add("seed_oil", 32, 2);
        FLUID_PRESS.RB().ii(Items.PUFFERFISH).fo(FishOil.getLiquid(30)).add("fish_oil_pufferfish", 16, 4);
        FLUID_PRESS.RB().ii(Items.COD).fo(FishOil.getLiquid(40)).add("fish_oil_cod", 16, 4);
        FLUID_PRESS.RB().ii(Items.SALMON).fo(FishOil.getLiquid(60)).add("fish_oil_salmon", 16, 4);
        FLUID_PRESS.RB().ii(Items.TROPICAL_FISH).fo(FishOil.getLiquid(70)).add("fish_oil_tropical_fish", 16, 4);
        FLUID_PRESS.RB().ii(Items.INK_SAC).fo(SquidInk.getLiquid(L)).add("squid_ink", 128, 4);
        FLUID_PRESS.RB().ii(Items.BEETROOT).fo(FluidPlatformUtils.createFluidStack(GTCoreFluids.BEET_JUICE.getFluid(), 200 * TesseractGraphWrappers.dropletMultiplier)).add("beet_juice", 16, 16);
    }
}
