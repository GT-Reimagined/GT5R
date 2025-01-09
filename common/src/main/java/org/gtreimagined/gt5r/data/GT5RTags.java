package org.gtreimagined.gt5r.data;

import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import org.gtreimagined.gt5r.GT5RRef;

public class GT5RTags {
    public static final TagKey<Fluid> STEAM =  TagKey.create(Registry.FLUID_REGISTRY, new ResourceLocation((AntimatterPlatformUtils.INSTANCE.isForge() ? "forge" : "c"), "steam"));
    public static final TagKey<Item> GRIND_HEADS = getTag("grind_heads");
    public static final TagKey<Item> GEM_SAPPHIRES = getTag("gems/sapphires");
    public static final TagKey<Item> DUST_SAPPHIRES = getTag("dusts/sapphires");
    public static final TagKey<Item> DUST_SANDS = getTag("dusts/sands");
    public static final TagKey<Item> DUST_SIO = getTag("dusts/silicon_dioxides");
    public static final TagKey<Block> ASPHALT = TagUtils.getBlockTag(new ResourceLocation(GT5RRef.ID, "asphalt"));
    public static TagKey<Item> getTag(String id){
        return TagUtils.getForgelikeItemTag(id);
    }
}
