package org.gtreimagined.gt5r.datagen;

import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

import static org.gtreimagined.gt5r.data.Materials.Oil;

public class GT5RFluidTagProvider extends AntimatterFluidTagProvider {
    public GT5RFluidTagProvider(String providerDomain, String providerName, boolean replace) {
        super(providerDomain, providerName, replace);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        for (DyeColor color : DyeColor.values()) {
            String dyeName = color.getName() + "_dye";
            Material chemDye = Material.get("chemical_" + dyeName);
            Material waterMixedDye = Material.get("water_mixed_" + dyeName);
            this.tag(TagUtils.getFluidTag(new ResourceLocation(domain, dyeName))).add(chemDye.getLiquid(), waterMixedDye.getLiquid());
        }
        this.tag(TagUtils.getForgelikeFluidTag("crude_oil")).add(Oil.getLiquid());
        this.tag(TagUtils.getForgelikeFluidTag("oil")).addTag(TagUtils.getForgelikeFluidTag("crude_oil"));
    }
}
