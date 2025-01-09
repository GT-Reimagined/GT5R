package org.gtreimagined.gt5r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.block.BlockAsphalt;
import org.gtreimagined.gt5r.block.BlockAsphaltSlab;
import org.gtreimagined.gt5r.block.BlockAsphaltStair;
import org.gtreimagined.gt5r.block.BlockCasing;
import org.gtreimagined.gt5r.block.BlockCoil;
import org.gtreimagined.gt5r.block.BlockColoredWall;
import org.gtreimagined.gt5r.block.BlockFakeCasing;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.gtreimagined.gt5r.data.GT5RMachines;
import org.gtreimagined.gt5r.data.GT5RTags;
import org.gtreimagined.gt5r.integration.AppliedEnergisticsRegistrar;

import static muramasa.antimatter.data.AntimatterMaterials.Wood;

public class GT5RBlockTagProvider extends AntimatterBlockTagProvider {

    public GT5RBlockTagProvider(String providerDomain, String providerName, boolean replace) {
        super(providerDomain, providerName, replace);
    }

    @Override
    public void processTags(String domain){
        super.processTags(domain);
        AntimatterAPI.all(BlockCasing.class, GT5RRef.ID, cas -> {
            if (cas.getId().contains("long_distance_wire")){
                this.tag(AntimatterDefaultTools.WIRE_CUTTER.getToolType()).add(cas);
                return;
            }
            this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockColoredWall.class, GT5RRef.ID, cas -> {
            if (cas.getMaterial() == Wood){
                this.tag(AntimatterDefaultTools.AXE.getToolType()).add(cas);
            } else {
                this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
            }
        });
        AntimatterAPI.all(BlockFakeCasing.class, GT5RRef.ID, cas -> {
            this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(cas);
        });
        for (DyeColor color : DyeColor.values()) {
            this.tag(GT5RTags.ASPHALT).add(AntimatterPlatformUtils.INSTANCE.getBlockFromId(new ResourceLocation(color.getName() + "_concrete")));
        }
        AntimatterAPI.all(BlockAsphalt.class, GT5RRef.ID, cas -> {
            this.tag(GT5RTags.ASPHALT).add(cas);
            this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockAsphaltSlab.class, GT5RRef.ID, cas -> {
            this.tag(GT5RTags.ASPHALT).add(cas);
            this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockAsphaltStair.class, GT5RRef.ID, cas -> {
            this.tag(GT5RTags.ASPHALT).add(cas);
            this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(cas);
        });
        AntimatterAPI.all(BlockCoil.class, GT5RRef.ID, cas -> {
            this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(cas);
        });
        this.tag(AntimatterDefaultTools.AXE.getToolType()).add(GT5RBlocks.BRITTLE_CHARCOAL, GT5RBlocks.POWDER_BARREL);
        this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).add(GT5RBlocks.MINING_PIPE, GT5RBlocks.MINING_PIPE_THIN, GT5RBlocks.SOLID_SUPER_FUEL);
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(GT5RMachines.MINIATURE_NETHER_PORTAL.getBlockState(Tier.NONE));
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            //TODO config for this
            this.tag(AntimatterDefaultTools.WRENCH.getToolType()).add(AppliedEnergisticsRegistrar.getAe2Block("cable_bus"));
            this.tag(AntimatterDefaultTools.PICKAXE.getToolType()).remove(AppliedEnergisticsRegistrar.getAe2Block("cable_bus"));
        }
    }
}
