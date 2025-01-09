package org.gtreimagined.gt5r.loader.crafting;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.pipe.types.PipeType;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.block.BlockAsphalt;
import org.gtreimagined.gt5r.block.BlockAsphaltSlab;
import org.gtreimagined.gt5r.block.BlockAsphaltStair;
import org.gtreimagined.gt5r.block.BlockCasing;
import org.gtreimagined.gt5r.block.BlockCoil;
import org.gtreimagined.gt5r.block.BlockColoredWall;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gt5r.data.GT5RCovers;
import org.gtreimagined.gt5r.data.GT5RTags;
import org.gtreimagined.gtcore.data.GTCoreItems;

import java.util.function.Consumer;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.antimatter.machine.Tier.*;
import static org.gtreimagined.gt5r.data.GT5RMachines.HULL;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gt5r.data.TierMaps.*;

public class BlockParts {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        AntimatterMaterialTypes.FRAME.all().forEach(frame -> {
            if (!frame.has(AntimatterMaterialTypes.ROD)) return;
            provider.addStackRecipe(output, GT5RRef.ID, "", "gtblockparts", AntimatterMaterialTypes.FRAME.get().get(frame).asStack(2),
                    of('R', AntimatterMaterialTypes.ROD.get(frame), 'W', WRENCH.getTag())
            , "RRR","RWR", "RRR");
        });

        AntimatterAPI.all(BlockColoredWall.class, b -> {
            if (b.getMaterial() == Wood){
                provider.addItemRecipe(output, "walls", b.asItem(),
                        of('P', PLATE.getMaterialTag(Lead), 'H', HAMMER.getTag(), 'S', SAW.getTag(), 'W', ItemTags.PLANKS), "W W", "SPH", "W W");
            } else {
                provider.addItemRecipe(output, "walls", b.asItem(),
                        of('P', PLATE.getMaterialTag(b.getMaterial()), 'H', HAMMER.getTag(), 'W', WRENCH.getTag()), "WPP", "HPP");
            }
        });

        addBrickedCasing(output, provider, Bronze, GT5RBlocks.CASING_BRICKED_BRONZE);
        provider.addStackRecipe(output, GT5RRef.ID, "firebricks", "blocks",
                new ItemStack(GT5RBlocks.CASING_FIRE_BRICK), of('F', GTCoreItems.FireBrick), "FF", "FF");
        addBrickedCasing(output, provider, Steel, GT5RBlocks.CASING_BRICKED_STEEL);

        addFirebox(output, provider, Bronze, GT5RBlocks.CASING_FIREBOX_BRONZE);
        addFirebox(output, provider, Steel, GT5RBlocks.CASING_FIREBOX_STEEL);
        addFirebox(output, provider, Titanium, GT5RBlocks.CASING_FIREBOX_TITANIUM);
        addFirebox(output, provider, TungstenSteel, GT5RBlocks.CASING_FIREBOX_TUNGSTENSTEEL);

        addGearbox(output, provider, Bronze, GT5RBlocks.CASING_GEARBOX_BRONZE);
        addGearbox(output, provider, Steel, GT5RBlocks.CASING_GEARBOX_STEEL);
        addGearbox(output, provider, Titanium, GT5RBlocks.CASING_GEARBOX_TITANIUM);
        addGearbox(output, provider, TungstenSteel, GT5RBlocks.CASING_GEARBOX_TUNGSTENSTEEL);

        addPipeCasing(output, provider, Bronze, GT5RBlocks.FLUID_PIPE_BRONZE, GT5RBlocks.CASING_PIPE_BRONZE);
        addPipeCasing(output, provider, Steel, GT5RBlocks.FLUID_PIPE_STEEL, GT5RBlocks.CASING_PIPE_STEEL);
        addPipeCasing(output, provider, Titanium, GT5RBlocks.FLUID_PIPE_TITANIUM, GT5RBlocks.CASING_PIPE_TITANIUM);
        addPipeCasing(output, provider, TungstenSteel, GT5RBlocks.FLUID_PIPE_TUNGSTEN_STEEL, GT5RBlocks.CASING_PIPE_TUNGSTENSTEEL);
        addPipeCasing(output, provider, Polytetrafluoroethylene, GT5RBlocks.FLUID_PIPE_STAINLESS_STEEL, GT5RBlocks.CASING_PIPE_PTFE);

        addTurbine(output, provider, Steel, GT5RBlocks.CASING_TURBINE_STEEL);
        addTurbine(output, provider, StainlessSteel, GT5RBlocks.CASING_TURBINE_STAINLESS);
        addTurbine(output, provider, Titanium, GT5RBlocks.CASING_TURBINE_TITANIUM);
        addTurbine(output, provider, TungstenSteel, GT5RBlocks.CASING_TURBINE_TUNGSTENSTEEL);

        addCasing(output, provider, Invar, GT5RBlocks.CASING_HEAT_PROOF);
        addCasing(output, provider, Aluminium, GT5RBlocks.CASING_FROST_PROOF);
        addCasing(output, provider, Steel, GT5RBlocks.CASING_SOLID_STEEL);
        addCasing(output, provider, StainlessSteel, GT5RBlocks.CASING_STAINLESS_STEEL);
        addCasing(output, provider, Titanium, GT5RBlocks.CASING_TITANIUM);
        addCasing(output, provider, Lead, GT5RBlocks.CASING_RADIATION_PROOF);
        addCasing(output, provider, TungstenSteel, GT5RBlocks.CASING_TUNGSTENSTEEL);
        addCasing(output, provider, Tungsten, GT5RBlocks.CASING_TUNGSTEN);
        addCasing(output, provider, Platinum, GT5RBlocks.CASING_PLATINUM);
        addCasing(output, provider, BlackBronze, GT5RBlocks.CASING_BLACK_BRONZE);
        addCasing(output, provider, Plastic, GT5RBlocks.CASING_PLASTIC);
        provider.addItemRecipe(output, "casings", GT5RBlocks.CASING_DENSE_LEAD,
                of('L', PLATE_DENSE.getMaterialTag(Lead), 'R', ROD_LONG.getMaterialTag(Lead), 'W', WRENCH.getTag()), "RLL", "LWL", "LLR");

        provider.addItemRecipe(output, "casings", GT5RBlocks.ELECTROLYTIC_CELL,
                of('W', GT5RBlocks.WIRE_PLATINUM.getBlockItem(PipeSize.VTINY), 'M', GT5RBlocks.CASING_STAINLESS_STEEL, 'C', TIER_CIRCUITS.apply(EV)), "WWW", "WMW", "CCC");
        provider.addItemRecipe(output, "casings", GT5RBlocks.GRINDING_WHEELS,
                of('G', GEAR.getMaterialTag(TungstenSteel), 'M', GT5RBlocks.CASING_TUNGSTENSTEEL, 'D', GT5RTags.GRIND_HEADS), "GDG", "GMG");
        provider.addItemRecipe(output, "casings", GT5RBlocks.ORE_WASHING_PARTS,
                of('G', GEAR.getMaterialTag(Titanium), 'P', GT5RBlocks.FLUID_PIPE_TITANIUM.getBlock(PipeSize.NORMAL),'C', GT5RBlocks.CASING_TITANIUM, 'W', WRENCH.getTag()), "GGG", "PCP", "PWP");
        provider.addItemRecipe(output, "casings", GT5RBlocks.FILTER_CASING, of('C', GT5RBlocks.CASING_TITANIUM, 'I', GT5RCovers.COVER_ITEM_FILTER.getItem()), " I ", "ICI", " I ");
        addCoil(output, provider, GT5RBlocks.WIRE_CUPRONICKEL.getBlockItem(PipeSize.TINY), GT5RBlocks.COIL_CUPRONICKEL);
        addCoil(output, provider, GT5RBlocks.WIRE_KANTHAL.getBlockItem(PipeSize.TINY), GT5RBlocks.COIL_KANTHAL);
        addCoil(output, provider, GT5RBlocks.WIRE_NICHROME.getBlockItem(PipeSize.TINY), GT5RBlocks.COIL_NICHROME);
        addCoil(output, provider, GT5RBlocks.WIRE_TUNGSTEN_STEEL.getBlockItem(PipeSize.TINY), GT5RBlocks.COIL_TUNGSTENSTEEL);
        addCoil(output, provider, GT5RBlocks.WIRE_HSSG.getBlockItem(PipeSize.TINY), GT5RBlocks.COIL_HSSG);
        addCoil(output, provider, GT5RBlocks.WIRE_NAQUADAH.getBlockItem(PipeSize.TINY), GT5RBlocks.COIL_NAQUADAH);
        addCoil(output, provider, GT5RBlocks.WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.TINY), GT5RBlocks.COIL_NAQUADAH_ALLOY);
        addCoil(output, provider, GT5RBlocks.WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.TINY), GT5RBlocks.COIL_SUPERCONDUCTOR);

        addTierCasing(output, provider, Tier.ULV);
        addTierCasing(output, provider, LV);
        addTierCasing(output, provider, MV);
        addTierCasing(output, provider, Tier.HV);
        addTierCasing(output, provider, Tier.EV);
        addTierCasing(output, provider, Tier.IV);
        addTierCasing(output, provider, Tier.LUV);
        addTierCasing(output, provider, Tier.ZPM);
        addTierCasing(output, provider, Tier.UV);
        addTierCasing(output, provider, Tier.UHV);

        addTierHull(output, provider, Wood,Tier.ULV);
        addTierHull(output, provider, WroughtIron, LV);
        addTierHull(output, provider, WroughtIron, MV);
        addTierHull(output, provider, Plastic,Tier.HV);
        addTierHull(output, provider, Plastic,Tier.EV);
        addTierHull(output, provider, Plastic,Tier.IV);
        addTierHull(output, provider, Plastic,Tier.LUV);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.ZPM);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.UV);
        addTierHull(output, provider, Polytetrafluoroethylene,Tier.UHV);

        provider.addStackRecipe(output, GT5RRef.ID, "", "gtblockparts", new ItemStack(GT5RBlocks.CASING_ENGINE_INTAKE, 1),
                of('P', ROTOR.getMaterialTag(Titanium), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', GT5RBlocks.CASING_TITANIUM, 'G', GT5RBlocks.FLUID_PIPE_TITANIUM.getBlockItem(PipeSize.NORMAL))
                ,
                "PHP", "GFG", "PWP");

        provider.addStackRecipe(output, GT5RRef.ID, "", "gtblockparts", new ItemStack(GT5RBlocks.CASING_BRONZE_PLATED_BRICK, 1),
                ImmutableMap.<Character, Object>builder()
                        .put('P', PLATE.get(Bronze))
                        .put('B', Blocks.BRICKS)
                        .put('W', WRENCH.getTag()).put('H', HAMMER.getTag()).build(), "PHP", "PBP", "PWP");

        provider.addItemRecipe(output, "gtblockparts", GT5RBlocks.CASING_STEEL,
                ImmutableMap.<Character, Object>builder()
                        .put('P', PLATE.get(Steel))
                        .put('W', HAMMER.getTag()).build(), "PPP", "PWP", "PPP");
        provider.addStackRecipe(output, GT5RRef.ID, "", "gtblockparts", new ItemStack(GT5RBlocks.CASING_FUSION, 1),
                of('P', PLATE.getMaterialTag(TungstenSteel), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', GT5RBlocks.CASING_LUV)
                ,
                "PHP", "PFP", "PWP");
        /*provider.addStackRecipe(output, GT5RRef.ID, "", "gtblockparts", "has_wrench", provider.hasSafeItem(WRENCH.getTag()), new ItemStack(CASING_FUSION_2, 1),
                of('P', PLATE.getMaterialTag(Americium), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', CASING_FUSION_1)
                ,
                "PHP", "PFP", "PWP");*/

        //TODO make these also use annealed copper
        provider.addItemRecipe(output, "long_distance_cables", GT5RBlocks.LONG_DIST_WIRE_EV,
                of('C', GT5RBlocks.WIRE_TIN.getBlockItem(PipeSize.HUGE), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_cables", GT5RBlocks.LONG_DIST_WIRE_IV,
                of('C', WIRE_GETTER.apply(PipeSize.HUGE, LV), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_cables", GT5RBlocks.LONG_DIST_WIRE_LUV,
                of('C', GT5RBlocks.WIRE_ELECTRUM.getBlockItem(PipeSize.HUGE), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_cables", GT5RBlocks.LONG_DIST_WIRE_ZPM,
                of('C', GT5RBlocks.WIRE_ALUMINIUM.getBlockItem(PipeSize.HUGE), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_cables", GT5RBlocks.LONG_DIST_WIRE_UV,
                of('C', GT5RBlocks.WIRE_PLATINUM.getBlockItem(PipeSize.HUGE), 'c', PLATE.getMaterialTag(Copper), 'A', PLATE.getMaterialTag(Aluminium), 'R', PLATE.getMaterialTag(Rubber)), "RAR", "cCc", "RAR");
        provider.addItemRecipe(output, "long_distance_pipes", GT5RBlocks.LONG_DIST_ITEM_PIPE,
                of('E', GT5RBlocks.ITEM_PIPE_ELECTRUM.getBlockItem(PipeSize.NORMAL), 'W', WRENCH.getTag(), 'P', PLATE.getMaterialTag(Plastic)), "PEP", "EWE", "PEP");
        provider.addItemRecipe(output, "long_distance_pipes", GT5RBlocks.LONG_DIST_FLUID_PIPE,
                of('E', GT5RBlocks.FLUID_PIPE_STAINLESS_STEEL.getBlockItem(PipeSize.NORMAL), 'W', WRENCH.getTag(), 'P', PLATE.getMaterialTag(Plastic)), "PEP", "EWE", "PEP");

        AntimatterAPI.all(BlockAsphalt.class, GT5RRef.ID).forEach(b -> {
            Block slab = AntimatterAPI.get(BlockAsphaltSlab.class, b.getId() + "_slab", GT5RRef.ID);
            Block stairs = AntimatterAPI.get(BlockAsphaltStair.class, b.getId() + "_stairs", GT5RRef.ID);
            if (slab != null){
                addSlabRecipe(output, provider, b, slab);
            }
            if (stairs != null){
                addStairRecipe(output, provider, b, stairs);
            }
        });

        /*provider.addStackRecipe(output, GT5RRef.ID, "blastbrickcasing", "gtblockparts", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), new ItemStack(CASING_BLAST_BRICK,4),
                of('C', CASING_FIRE_BRICK,
                'P', PLATE.get(AntimatterMaterials.Iron),
                        'B', AntimatterAPI.get(Item.class, "liquid_creosote_bucket", Ref.SHARED_ID)
        ), "PCP", "CBC", "PCP");*/
        provider.removeRecipe(new ResourceLocation("tnt"));
        provider.addItemRecipe(output, "misc", GT5RBlocks.POWDER_BARREL,
                of('W', ItemTags.PLANKS, 'G', Items.GUNPOWDER, 'S', Items.STRING), "WSW" ,"GGG", "WGW");
    }

    private static void addSlabRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Block full, Block slab){
        provider.addStackRecipe(output, "slabs", new ItemStack(slab, 6), ImmutableMap.of('F', full), "FFF");
        provider.addItemRecipe(output, "slabs", full, ImmutableMap.of('S', slab), "S", "S");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(full), slab, 2).group("slabs").unlockedBy("has_full", provider.hasSafeItem(full)).save(output, new ResourceLocation(GT5RRef.ID, "stonecutting/" + AntimatterPlatformUtils.INSTANCE.getIdFromItem(slab.asItem()).getPath()));
    }

    private static void addStairRecipe(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Block full, Block stair){
        provider.addStackRecipe(output, "stairs", new ItemStack(stair, 4), ImmutableMap.of('F', full), "F  ", "FF ", "FFF");
        provider.addStackRecipe(output, GT5RRef.ID, AntimatterPlatformUtils.INSTANCE.getIdFromItem(stair.asItem()).getPath() + "_mirrored", "stairs", new ItemStack(stair, 4), ImmutableMap.of('F', full), "  F", " FF", "FFF");
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(full), stair, 1).group("stairs").unlockedBy("has_full", provider.hasSafeItem(full)).save(output, new ResourceLocation(GT5RRef.ID, "stonecutting/" + AntimatterPlatformUtils.INSTANCE.getIdFromItem(stair.asItem()).getPath()));
    }

    private static void addCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, GT5RRef.ID, "", "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                ,
                "PHP", "PFP", "PWP");
    }

    private static void addBrickedCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", casing,
                of('B', Items.BRICK, 'P', PLATE.getMaterialTag(mat), 'H', AntimatterDefaultTools.HAMMER.getTag())
                , "PPP", "PHP", "BBB");
    }

    private static void addFirebox(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, GT5RRef.ID, "", "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'R', ROD.getMaterialTag(mat), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                , "PRP", "RFR", "PRP");
    }

    private static void addGearbox(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, GT5RRef.ID, "", "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'W', WRENCH.getTag(), 'H', AntimatterDefaultTools.HAMMER.getTag(), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem(), 'G', GEAR.getMaterialTag(mat))
                ,
                "PHP", "GFG", "PWP");
    }

    private static void addPipeCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, PipeType<?> pipe, Block casing) {
        provider.addItemRecipe(output, GT5RRef.ID, "", "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'R', pipe.getBlockItem(PipeSize.NORMAL), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                , "PRP", "RFR", "PRP");
    }

    private static void addTurbine(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Block casing) {
        provider.addItemRecipe(output, "gtblockparts", casing,
                of('P', PLATE.getMaterialTag(mat), 'R', AntimatterMaterialTypes.ROD.getMaterialTag(mat), 'F', AntimatterMaterialTypes.FRAME.get().get(mat).asItem())
                , "PRP", "PFP", "PRP");
    }

    private static void addCoil(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, PipeItemBlock wire, BlockCoil coil) {
        provider.addItemRecipe(output, "casings", coil,
                of('P', wire, 'W', WRENCH.getTag())
                , "PPP", "PWP", "PPP");
    }

    private static void addTierCasing(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Tier tier) {
        provider.addItemRecipe(output, "casings", AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GT5RRef.ID),
                of('P', PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 'W', WRENCH.getTag())
                , "PPP", "PWP", "PPP");
    }

    private static void addTierHull(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider, Material mat, Tier tier) {
        provider.addItemRecipe(output, "hulls", HULL.getItem(tier),
                of('P', PLATE.getMaterialTag(mat), 'R', PLATE.getMaterialTag(TIER_MATERIALS.get(tier)), 'W', CABLE_GETTER.apply(tier == Tier.UV ? PipeSize.SMALL : PipeSize.VTINY, tier, false), 'K', AntimatterAPI.get(BlockCasing.class, "casing_" + tier.getId(), GT5RRef.ID))
                , "PRP", "WKW");
    }
}
