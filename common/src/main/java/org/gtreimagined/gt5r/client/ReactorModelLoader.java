package org.gtreimagined.gt5r.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import org.gtreimagined.gt5r.GT5RRef;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.client.ModelUtils;
import muramasa.antimatter.client.model.loader.AntimatterModelLoader;
import muramasa.antimatter.machine.MachineState;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.texture.MissingTextureAtlasSprite;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ReactorModelLoader extends AntimatterModelLoader<ReactorModel> {
    public ReactorModelLoader(ResourceLocation loc) {
        super(loc);
    }

    @Override
    public ReactorModel readModel(JsonDeserializationContext context, JsonObject json) {
        ResourceLocation particle = json.has("particle") ? new ResourceLocation(json.get("particle").getAsString()) : MissingTextureAtlasSprite.getLocation();
        Map<MachineState, UnbakedModel[]> m = new HashMap<>();
        AntimatterAPI.all(MachineState.class, t -> {
            if (json.has(t.toString().toLowerCase())) {
                JsonArray arr = json.get(t.toString().toLowerCase()).getAsJsonArray();
                UnbakedModel[] a = new UnbakedModel[6];
                for (int i = 0; i < 6; i++) {
                    a[i] = context.deserialize(arr.get(i), BlockModel.class);
                }
                m.put(t, a);
            }
        });
        UnbakedModel[] rods = new UnbakedModel[4];
        String[] array = new String[]{"north-west", "south-west", "north-east", "south-east"};
        //northwest is 0, southwest is 1, northeast is 2, southeast is 3
        for (int i = 0; i < 4; i++) {
            ResourceLocation modelLocation = new ResourceLocation(GT5RRef.ID, "block/machine/overlay/nuclear_reactor_core/"+ array[i] + "-rod");
            try {
                rods[i] = ModelUtils.getModel(modelLocation);
            } catch (Exception ignored){
                //rods[i] = ModelUtils.getModel(modelLocation);
            }

        }
        return new ReactorModel(m, particle, rods);
    }
}
