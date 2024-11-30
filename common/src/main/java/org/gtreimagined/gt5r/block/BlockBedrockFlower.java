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
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
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

public class BlockBedrockFlower extends BushBlock implements IAntimatterObject, IModelProvider, ITextureProvider {
    protected static final VoxelShape SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 10.0, 11.0);
    private final String domain, id;

    public BlockBedrockFlower(String domain, String id) {
        this(domain, id, Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.GRASS));
    }

    public BlockBedrockFlower(String domain, String id, Properties props) {
        super(props);
        this.domain = domain;
        this.id = id;
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
}
