package org.gtreimagined.gt5r.datagen;

import muramasa.antimatter.Data;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.machine.types.Machine;
import org.gtreimagined.gt5r.GT5RRef;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.gtreimagined.gtcore.data.GTCoreItems;

import java.util.List;
import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterDefaultTools.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider.*;
import static muramasa.antimatter.machine.Tier.BRONZE;
import static muramasa.antimatter.machine.Tier.NONE;
import static muramasa.antimatter.util.TagUtils.getForgelikeItemTag;
import static muramasa.antimatter.util.Utils.hasItem;
import static muramasa.antimatter.util.Utils.hasItems;
import static org.gtreimagined.gt5r.data.Machines.*;
import static org.gtreimagined.gt5r.data.Materials.*;

public class ProgressionAdvancements implements Consumer<Consumer<Advancement>> {

    public static Advancement progressionRoot;

    @Override
    public void accept(Consumer<Advancement> consumer) {
        progressionRoot = buildRootAdvancement(Data.DEBUG_SCANNER, new ResourceLocation(GT5RRef.ID, "textures/block/machine/base/lv.png"),
                GT5RRef.ID + ".advancements.gt5r.title", GT5RRef.ID + ".advancements.gt5r.desc", FrameType.TASK, false, false, false)
                .addCriterion("has_rocks", hasItem(getForgelikeItemTag("rocks"))).save(consumer, getLoc(GT5RRef.ID, "progression/root"));
        Advancement rock = buildBasicAdvancement(progressionRoot, AntimatterMaterialTypes.ROCK.get(Stone), "rock", FrameType.TASK)
                .addCriterion("has_rocks", hasItems(getForgelikeItemTag("rocks"), getForgelikeItemTag("bearing_rocks"))).save(consumer, getLoc(GT5RRef.ID, "progression/rock"));
        Item pickaxe = PICKAXE.getToolItem(Flint);
        Advancement flintPick = buildBasicAdvancement(rock, pickaxe, "flint_pickaxe", FrameType.TASK)
                .addCriterion("has_flint_pick", hasItem(pickaxe)).save(consumer, getLoc(GT5RRef.ID, "progression/flint_pickaxe"));
        Advancement rawCopper = buildBasicAdvancement(flintPick, RAW_ORE.get(Copper), "raw_copper", FrameType.TASK)
                .addCriterion("has_raw_copper", hasItem(RAW_ORE.getMaterialTag(Copper))).save(consumer, getLoc(GT5RRef.ID, "progression/raw_copper"));
        Advancement rawTin = buildBasicAdvancement(rawCopper, RAW_ORE.get(Tin), "raw_tin", FrameType.TASK)
                .addCriterion("has_raw_tin", hasItem(RAW_ORE.getMaterialTag(Tin))).save(consumer, getLoc(GT5RRef.ID, "progression/raw_tin"));
        Advancement mortar = buildBasicAdvancement(rawTin, MORTAR.getToolItem(Flint), "mortar", FrameType.TASK)
                .addCriterion("has_mortar", hasItem(MORTAR.getTag())).save(consumer, getLoc(GT5RRef.ID, "progression/mortar"));
        Advancement bronzeDust = buildBasicAdvancement(mortar, DUST.get(Bronze), "bronze_dust", FrameType.TASK)
                .addCriterion("has_raw_copper", hasItem(DUST.getMaterialTag(Bronze))).save(consumer, getLoc(GT5RRef.ID, "progression/bronze_dust"));
        Advancement hammer = buildBasicAdvancement(bronzeDust, HAMMER.getToolItem(Bronze), "hammer", FrameType.TASK)
                .addCriterion("has_hammer", hasItem(HAMMER.getTag())).save(consumer, getLoc(GT5RRef.ID, "progression/hammer"));
        Advancement bronzeSmallBoiler = buildBasicAdvancement(hammer, SOLID_FUEL_BOILER.getItem(BRONZE), "bronze_solid_fuel_boiler", FrameType.TASK)
                .addCriterion("has_solid_fuel_boilers", hasItem(SOLID_FUEL_BOILER.getItem(BRONZE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/bronze_small_boiler"));
        buildBasicAdvancement(bronzeSmallBoiler, STEAM_ALLOY_SMELTER.getItem(BRONZE), "bronze_steam_alloy_smelter", FrameType.TASK)
                .addCriterion("has_steam_alloy_smelter", hasItem(STEAM_ALLOY_SMELTER.getItem(BRONZE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/bronze_steam_alloy_smelter"));
        buildBasicAdvancement(bronzeSmallBoiler, STEAM_FURNACE.getItem(BRONZE), "bronze_steam_furnace", FrameType.TASK)
                .addCriterion("has_steam_furnace", hasItem(STEAM_FURNACE.getItem(BRONZE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/bronze_steam_furnace"));
        buildBasicAdvancement(bronzeSmallBoiler, STEAM_MACERATOR.getItem(BRONZE), "bronze_steam_macerator", FrameType.TASK)
                .addCriterion("has_steam_macerator", hasItem(STEAM_MACERATOR.getItem(BRONZE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/bronze_steam_macerator"));
        buildBasicAdvancement(bronzeSmallBoiler, STEAM_EXTRACTOR.getItem(BRONZE), "bronze_steam_extractor", FrameType.TASK)
                .addCriterion("has_steam_extractor", hasItem(STEAM_EXTRACTOR.getItem(BRONZE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/bronze_steam_extractor"));
        Advancement bronzeSteamForgeHammer = buildBasicAdvancement(bronzeSmallBoiler, STEAM_FORGE_HAMMER.getItem(BRONZE), "bronze_steam_forge_hammer", FrameType.TASK)
                .addCriterion("has_steam_forge_hammer", hasItem(STEAM_FORGE_HAMMER.getItem(BRONZE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/bronze_steam_forge_hammer"));
        Advancement bronzeSteamCompressor = buildBasicAdvancement(bronzeSteamForgeHammer, STEAM_COMPRESSOR.getItem(BRONZE), "bronze_steam_compressor", FrameType.TASK)
                .addCriterion("has_steam_compressor", hasItem(STEAM_COMPRESSOR.getItem(BRONZE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/bronze_steam_compressor"));
        Advancement fireBrick= buildBasicAdvancement(bronzeSteamCompressor, GTCoreItems.FireBrick, "fire_brick", FrameType.TASK)
                .addCriterion("has_fire_brick", hasItem(GTCoreItems.FireBrick))
                .save(consumer, getLoc(GT5RRef.ID, "progression/fire_brick"));
        Advancement primitiveBlastFurnace = buildBasicAdvancement(fireBrick, PRIMITIVE_BLAST_FURNACE.getItem(NONE), "primitive_blast_furnace", FrameType.TASK)
                .addCriterion("has_primitive_blast_furnace", hasItem(PRIMITIVE_BLAST_FURNACE.getItem(NONE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/primitive_blast_furnace"));
        buildBasicAdvancement(fireBrick, COKE_OVEN.getItem(NONE), "coke_oven", FrameType.TASK)
                .addCriterion("has_coke_oven", hasItem(COKE_OVEN.getItem(NONE)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/coke_oven"));
        Advancement steel = buildBasicAdvancement(primitiveBlastFurnace, INGOT.get(Steel), "steel_ingot", FrameType.GOAL)
                .addCriterion("has_steel_ingot", hasItem(INGOT.getMaterialTag(Steel)))
                .save(consumer, getLoc(GT5RRef.ID, "progression/steel_ingot"));
    }

    public static Advancement.Builder buildBasicAdvancement(Advancement parent, ItemLike provider, String langComponent, FrameType type) {
        return buildAdvancement(parent, provider, GT5RRef.ID + ".advancements." + langComponent + ".title", GT5RRef.ID + ".advancements." + langComponent + ".desc", type, true, true, false);
    }

    public static InventoryChangeTrigger.TriggerInstance hasMachine(Machine<?> machine){
        List<Item> list = machine.getTiers().stream().map(machine::getItem).toList();
        return hasItem(ItemPredicate.Builder.item().of(list.toArray(ItemLike[]::new)).build());
    }

}
