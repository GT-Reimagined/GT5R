package org.gtreimagined.gt5r.integration.forge.tfc.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.ore.StoneType;
import net.dries007.tfc.common.TFCTags;
import net.minecraft.world.level.block.Block;

import static muramasa.antimatter.data.AntimatterMaterialTypes.ORE;
import static muramasa.antimatter.data.AntimatterMaterialTypes.ORE_SMALL;
import static muramasa.antimatter.data.AntimatterStoneTypes.BEDROCK;

public class TFCBlockTagProvider extends AntimatterBlockTagProvider {
    public TFCBlockTagProvider(String providerDomain, String providerName, boolean replace) {
        super(providerDomain, providerName, replace);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        ORE.all().forEach(m -> {
            AntimatterAPI.all(StoneType.class).stream().filter(s -> s.doesGenerateOre() && s != BEDROCK).forEach(s -> {
                Block ore = ORE.get().get(m, s).asBlock();
                Block smallOre = ORE_SMALL.get().get(m, s).asBlock();
                this.tag(TFCTags.Blocks.CAN_COLLAPSE).add(ore, smallOre);
                this.tag(TFCTags.Blocks.CAN_TRIGGER_COLLAPSE).add(ore, smallOre);
                this.tag(TFCTags.Blocks.MONSTER_SPAWNS_ON).add(ore, smallOre);
                this.tag(TFCTags.Blocks.PROSPECTABLE).add(ore, smallOre);
                this.tag(TFCTags.Blocks.CAN_START_COLLAPSE).add(ore, smallOre);
            });
        });
        /*for (Material material : TFCRegistrar.array) {
            Helpers.mapOfKeys(Ore.Grade.class, (grade) -> {
                return Helpers.mapOfKeys(Rock.class, (rock) -> {
                    Block ore = GT5Reimagined.get(Block.class, grade.name().toLowerCase() + "_" + rock.name().toLowerCase() + "_" + material.getId());
                    this.tag(TFCTags.Blocks.CAN_COLLAPSE).add(ore);
                    this.tag(TFCTags.Blocks.CAN_TRIGGER_COLLAPSE).add(ore);
                    this.tag(TFCTags.Blocks.MONSTER_SPAWNS_ON).add(ore);
                    this.tag(TFCTags.Blocks.PROSPECTABLE).add(ore);
                    this.tag(TFCTags.Blocks.CAN_START_COLLAPSE).add(ore);
                    this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ore);
                    int oreMiningLevel = material.has(MaterialTags.MINING_LEVEL) ? MaterialTags.MINING_LEVEL.getInt(material) : 0;
                    if (oreMiningLevel > 0){
                        this.tag(fromMiningLevel(oreMiningLevel)).add(ore);
                    }
                    return true;
                });
            });
        }*/
    }
}
