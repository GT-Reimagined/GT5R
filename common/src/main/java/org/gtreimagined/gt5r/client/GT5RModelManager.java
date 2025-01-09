package org.gtreimagined.gt5r.client;

import net.minecraft.resources.ResourceLocation;
import org.gtreimagined.gt5r.GT5RRef;

public class GT5RModelManager {

    public static final ResourceLocation LOADER_REACTOR = new ResourceLocation(GT5RRef.ID, "reactor");

    public static void init(){
        new ReactorModelLoader(LOADER_REACTOR);
    }
}
