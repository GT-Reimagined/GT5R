package org.gtreimagined.gt5r.client;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import muramasa.antimatter.client.model.IModelConfiguration;
import muramasa.antimatter.client.model.MachineModel;
import muramasa.antimatter.machine.MachineState;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ReactorModel extends MachineModel {
    private final UnbakedModel[] rodModels;
    public ReactorModel(Map<MachineState, UnbakedModel[]> models, ResourceLocation particle, UnbakedModel[] rodModels) {
        super(models, particle);
        this.rodModels = rodModels;
    }

    @Override
    public Collection<Material> getMaterials(IModelConfiguration configuration, Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) {
        Collection<Material> materials = super.getMaterials(configuration, modelGetter, missingTextureErrors);
        materials.addAll(Arrays.stream(rodModels).flatMap(i -> i.getMaterials(modelGetter, missingTextureErrors).stream()).toList());
        return materials;
    }

    @Override
    public BakedModel bakeModel(ModelBakery bakery,
                                Function<Material, TextureAtlasSprite> getter, ModelState transform,
                                ResourceLocation loc) {
        ImmutableMap.Builder<MachineState, BakedModel[]> builder = ImmutableMap.builder();

        for (Map.Entry<MachineState, UnbakedModel[]> pair : this.models.entrySet()) {
            BakedModel[] mod = new BakedModel[6];
            for (int i = 0; i < 6; i++) {
                mod[i] = pair.getValue()[i].bake(bakery, getter, transform, loc);
            }
            builder.put(pair.getKey(),mod);
        }
        BakedModel[] rodModelsBaked = new BakedModel[rodModels.length];
        for (int i = 0; i < rodModels.length; i++) {
            rodModelsBaked[i] = rodModels[i].bake(bakery, getter, transform, loc);
        }
        return new ReactorBakedModel(getter.apply(new Material(TextureAtlas.LOCATION_BLOCKS, particle)), builder.build(), rodModelsBaked);
    }
}
