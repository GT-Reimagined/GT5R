package org.gtreimagined.gt5r.data;

import muramasa.antimatter.registration.Side;
import net.minecraft.world.item.DyeColor;

public class GT5RData {

    public static void init(Side side) {
        if (side == Side.CLIENT)
            RecipeMaps.clientMaps();
        /*AntimatterAPI.all(MaterialType.class, t -> {
            if (t instanceof MaterialTypeFluid) return;
            if (t.getClass() == MaterialType.class) return;
            //TODO: add better check
            if (t == AntimatterMaterialTypes.ORE_STONE) return;
            CoverFactory.builder((a,b,c,d) -> new CoverTypeFilter(a,b,c,d,t)).addTextures(Material.NULL.getSet().getTextures(t)).item((a, b) -> {
            return new ItemCover(a.getDomain(), a.getId()).tip("Filters for " + t.getId()).texture(Material.NULL.getSet().getTextures(t));}).build(GT5RRef.ID, "cover_type_" + t.getId());
        });*/
    }

    public static int getColorFromDyeColor(DyeColor color) {
        float[] textureDiffuseColors = color.getTextureDiffuseColors();
        int rgb = (((int)((textureDiffuseColors[0] * 255.0F) + 0.5f)) << 16)
                | (((int)((textureDiffuseColors[1] * 255.0F) + 0.5f)) << 8)
                | ((int)((textureDiffuseColors[2] * 255.0F) + 0.5f));
        return rgb;
    }

    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(GT5RRef.ID, "anthracite_coal", new Texture(GT5RRef.ID, "block/basic/anthracite_coal");
    //public static BlockBasic ANTHRACITE_COAL = new BlockBasic(GT5RRef.ID, "anthracite_coal", new Texture(GT5RRef.ID, "block/basic/anthracite_coal");


}
