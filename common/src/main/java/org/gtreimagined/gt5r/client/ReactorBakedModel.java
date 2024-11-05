package org.gtreimagined.gt5r.client;

import com.google.common.collect.ImmutableMap;
import org.gtreimagined.gt5r.blockentity.single.BlockEntityNuclearReactorCore;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.AntimatterProperties;
import muramasa.antimatter.Ref;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.client.ModelUtils;
import muramasa.antimatter.client.baked.MachineBakedModel;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.texture.Texture;
import org.gtreimagined.gt5r.data.GT5RCovers;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class ReactorBakedModel extends MachineBakedModel {
    private final BakedModel[] rodModels;
    public ReactorBakedModel(TextureAtlasSprite particle, ImmutableMap<MachineState, BakedModel[]> sides, BakedModel[] rodModels) {
        super(particle, sides);
        this.rodModels = rodModels;
    }

    @Override
    public List<BakedQuad> getBlockQuads(BlockState state, Direction side, Random rand, BlockAndTintGetter level, @NotNull BlockPos pos) {
        if (side == null) {
            return Collections.emptyList();
        }
        BlockEntity tile = level.getBlockEntity(pos);
        if (!(tile instanceof BlockEntityNuclearReactorCore core)) return Collections.emptyList();
        AntimatterProperties.MachineProperties props = getMachineProperty(core);
        if (props == null) return Collections.emptyList();
        List<BakedQuad> superBlockQuads = new ObjectArrayList<>(20);
        List<BakedQuad> coverQuads = getCoverQuads(state, side, rand, props, core, level, pos);
        ICover cover = props.covers[side.get3DDataValue()];
        boolean isOutputCover = cover.getFactory() == GT5RCovers.COVER_REACTOR_OUTPUT || cover.getFactory() == GT5RCovers.COVER_REACTOR_OUTPUT_SECONDARY;
        if (!coverQuads.isEmpty()) {
            if (isOutputCover) {
                return coverQuads;
            }
            Function<Direction, Texture> ft = core.getMultiTexture();
            List<BakedQuad> machineQuads = props.machineTexturer.getQuads("machine", new ObjectArrayList<>(), state, props.type, new BlockEntityMachine.DynamicKey(new ResourceLocation(props.type.getId()), ft.apply(side), side, props.state, props), side.get3DDataValue(), level, pos);
            for (int i = 0; i < 2; i++) {
                BakedQuad quad = machineQuads.get(i);
                if (quad.getDirection() == side.getOpposite())
                    coverQuads.add(quad);
            }
            return coverQuads;
        }
        BakedModel model = getModel(state, side, props.state, props.type);
        for (Direction dir : Ref.DIRS) {
            superBlockQuads.addAll(ModelUtils.INSTANCE.getQuadsFromBaked(model, state, dir, rand, level, pos));
        }
        superBlockQuads.addAll(ModelUtils.INSTANCE.getQuadsFromBaked(model, state, null, rand, level, pos));
        if (side == Direction.UP){
            List<BakedQuad> list = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                ItemStack rod = core.getRod(i);
                if (rod.isEmpty()) continue;
                BakedModel rodModel = rodModels[i];
                for (Direction dir : Ref.DIRS) {
                    list.addAll(ModelUtils.INSTANCE.getQuadsFromBaked(rodModel, state, dir, rand, level, pos));
                }
                list.addAll(ModelUtils.INSTANCE.getQuadsFromBaked(rodModel, state, null, rand, level, pos));
            }
            list.addAll(superBlockQuads);
            return list;
        }
        return superBlockQuads;
    }
}
