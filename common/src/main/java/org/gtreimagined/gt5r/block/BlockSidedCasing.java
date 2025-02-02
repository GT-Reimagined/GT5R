package org.gtreimagined.gt5r.block;

import muramasa.antimatter.texture.Texture;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;


public class BlockSidedCasing extends BlockCasing{
    String bottom, top;
    public BlockSidedCasing(String domain, String id, String bottom, String top) {
        super(domain, id);
        this.bottom = bottom;
        this.top = top;
    }

    public Texture[] getTextures() {
        Texture side = new Texture(getDomain(), "block/casing/" + getId().replaceAll("_casing", ""));
        return new Texture[]{new Texture(getDomain(), "block/casing/" + bottom), new Texture(getDomain(), "block/casing/" + top), side, side, side, side};
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }
}
