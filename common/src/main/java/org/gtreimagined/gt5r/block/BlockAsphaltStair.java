package org.gtreimagined.gt5r.block;

import muramasa.antimatter.block.BlockBasicStair;
import muramasa.antimatter.registration.IColorHandler;
import muramasa.antimatter.texture.Texture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.gtreimagined.gt5r.GT5RRef;
import org.jetbrains.annotations.Nullable;

public class BlockAsphaltStair extends BlockBasicStair implements IColorHandler {
    final int color;
    public BlockAsphaltStair(String domain, String id, Block base, int color) {
        super(domain, id, base, Properties.of(Material.STONE).strength(1.0f, 1.0f).sound(SoundType.STONE));
        this.color = color;
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        return color;
    }

    @Override
    public int getBlockColor(BlockState state, @Nullable BlockGetter world, @Nullable BlockPos pos, int i) {
        return color;
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GT5RRef.ID, "block/stone/asphalt")};
    }
}
