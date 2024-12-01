package org.gtreimagined.gt5r.block;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.builder.AntimatterBlockModelBuilder;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.gtreimagined.gt5r.data.GT5RBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockBedrockFlower extends BushBlock implements IAntimatterObject, IModelProvider, ITextureProvider {
    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0);
    private final String domain, id;
    public final muramasa.antimatter.material.Material tooltipMaterial;
    private final boolean sand;

    public BlockBedrockFlower(String domain, String id, muramasa.antimatter.material.Material tooltipMaterial, boolean sand) {
        this(domain, id, tooltipMaterial, sand, Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS));
    }

    public BlockBedrockFlower(String domain, String id, muramasa.antimatter.material.Material tooltipMaterial, boolean sand, Properties props) {
        super(props);
        this.domain = domain;
        this.id = id;
        this.tooltipMaterial = tooltipMaterial;
        this.sand = sand;
        AntimatterAPI.register(BlockBedrockFlower.class, this);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public void onItemModelBuild(ItemLike item, AntimatterItemModelProvider prov) {
        prov.tex(item, getTextures());
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        AntimatterBlockModelBuilder builder = prov.getBuilder(block);
        builder.parent(new ResourceLocation("block/cross"));
        Texture texture = getTextures()[0];
        builder.texture("cross", texture);
        builder.property("particle", texture.toString());
        prov.state(block, builder);
    }

    @Override
    public Texture[] getTextures() {
       return new Texture[]{new Texture(domain, "block/flower/" + getId())};
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(level, pos);
        return SHAPE.move(vec3.x, vec3.y, vec3.z);
    }

    @Override
    public BlockBehaviour.OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    @Override
    public boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        if (sand){
            return state.is(BlockTags.SAND);
        }
        return super.mayPlaceOn(state, level, pos);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Utils.translatable("tooltip." + getDomain() + "." + getId().replace("/", ".")));
    }
}
