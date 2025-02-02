package org.gtreimagined.gt5r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterStoneTypes;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.ore.CobbleStoneType;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.gtreimagined.gt5r.block.BlockAsphalt;
import org.gtreimagined.gt5r.block.BlockAsphaltSlab;
import org.gtreimagined.gt5r.block.BlockAsphaltStair;
import org.gtreimagined.gt5r.block.BlockBedrockFlower;
import org.gtreimagined.gt5r.block.BlockCasing;
import org.gtreimagined.gt5r.block.BlockCoil;
import org.gtreimagined.gt5r.block.BlockColoredWall;
import org.gtreimagined.gt5r.block.BlockFakeCasing;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gt5r.data.Materials;
import org.gtreimagined.gt5r.integration.AppliedEnergisticsRegistrar;
import org.gtreimagined.gt5r.integration.SpaceModRegistrar;

import static muramasa.antimatter.data.AntimatterMaterialTypes.RAW_ORE;
import static muramasa.antimatter.data.AntimatterMaterials.*;

public class GT5RBlockLootProvider extends AntimatterBlockLootProvider {
    public GT5RBlockLootProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    protected void loot() {
        super.loot();
        AntimatterAPI.all(BlockCasing.class,providerDomain, this::add);
        AntimatterAPI.all(BlockColoredWall.class,providerDomain, this::add);
        AntimatterAPI.all(BlockCoil.class,providerDomain, this::add);
        AntimatterAPI.all(BlockFakeCasing.class, providerDomain, this::add);
        AntimatterAPI.all(BlockAsphalt.class, providerDomain, this::add);
        AntimatterAPI.all(BlockAsphaltSlab.class, providerDomain, b -> tables.put(b, BlockLoot::createSlabItemTable));
        AntimatterAPI.all(BlockAsphaltStair.class, providerDomain, this::add);
        AntimatterAPI.all(BlockBedrockFlower.class, providerDomain, this::add);
        this.add(GT5RBlocks.MINING_PIPE_THIN);
        this.add(GT5RBlocks.SOLID_SUPER_FUEL);
        this.add(GT5RBlocks.POWDER_BARREL);
        tables.put(GT5RBlocks.MINING_PIPE, b -> this.build(GT5RBlocks.MINING_PIPE_THIN));
        tables.put(Blocks.LAPIS_ORE, b -> createOreDrop(b, RAW_ORE.get(Lapis)));
        tables.put(Blocks.DEEPSLATE_LAPIS_ORE, b -> createOreDrop(b, RAW_ORE.get(Lapis)));
        tables.put(Blocks.REDSTONE_ORE, b -> createOreDrop(b, RAW_ORE.get(Redstone)));
        tables.put(Blocks.DEEPSLATE_REDSTONE_ORE, b -> createOreDrop(b, RAW_ORE.get(Redstone)));
        tables.put(Blocks.DIAMOND_ORE, b -> createOreDrop(b, RAW_ORE.get(Diamond)));
        tables.put(Blocks.DEEPSLATE_DIAMOND_ORE, b -> createOreDrop(b, RAW_ORE.get(Diamond)));
        tables.put(Blocks.EMERALD_ORE, b -> createOreDrop(b, RAW_ORE.get(Emerald)));
        tables.put(Blocks.DEEPSLATE_EMERALD_ORE, b -> createOreDrop(b, RAW_ORE.get(Emerald)));
        tables.put(Blocks.COPPER_ORE, b -> createOreDrop(b, RAW_ORE.get(Copper)));
        tables.put(Blocks.DEEPSLATE_COPPER_ORE, b -> createOreDrop(b, RAW_ORE.get(Copper)));
        tables.put(Blocks.COAL_ORE, b -> createOreDrop(b, RAW_ORE.get(Coal)));
        tables.put(Blocks.DEEPSLATE_COAL_ORE, b -> createOreDrop(b, RAW_ORE.get(Coal)));
        tables.put(GT5RBlocks.BRITTLE_CHARCOAL, b -> createSingleItemTable(Items.CHARCOAL, UniformGenerator.between(1.0f, 2.0f)));
        tables.put(Blocks.ANCIENT_DEBRIS, b -> createOreDrop(b, RAW_ORE.get(NetheriteScrap)));
        tables.put(Blocks.ANDESITE, b -> createSingleItemTableWithSilkTouch(Blocks.ANDESITE, ((CobbleStoneType)AntimatterStoneTypes.ANDESITE).getBlock("cobble")));
        tables.put(Blocks.DIORITE, b -> createSingleItemTableWithSilkTouch(Blocks.DIORITE, ((CobbleStoneType)AntimatterStoneTypes.DIORITE).getBlock("cobble")));
        tables.put(Blocks.GRANITE, b -> createSingleItemTableWithSilkTouch(Blocks.GRANITE, ((CobbleStoneType)AntimatterStoneTypes.GRANITE).getBlock("cobble")));
        tables.put(Blocks.BASALT, b -> createSingleItemTableWithSilkTouch(Blocks.BASALT, ((CobbleStoneType)AntimatterStoneTypes.BASALT).getBlock("cobble")));
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            tables.put(AppliedEnergisticsRegistrar.getAe2Block("quartz_ore"), b -> createOreDrop(b, RAW_ORE.get(Materials.CertusQuartz)));
            tables.put(AppliedEnergisticsRegistrar.getAe2Block("deepslate_quartz_ore"), b -> createOreDrop(b, RAW_ORE.get(Materials.CertusQuartz)));
        }
        if (AntimatterAPI.isModLoaded("ad_astra")){
            tables.put(SpaceModRegistrar.getSpaceBlock("mars_diamond_ore"), b -> createOreDrop(b, RAW_ORE.get(Diamond)));
        }
    }
}
