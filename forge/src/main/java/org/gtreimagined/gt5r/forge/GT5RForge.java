package org.gtreimagined.gt5r.forge;

import muramasa.antimatter.event.forge.AntimatterCraftingEvent;
import muramasa.antimatter.event.forge.AntimatterLoaderEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import muramasa.antimatter.event.forge.AntimatterWorldGenEvent;
import org.gtreimagined.gt5r.GT5Reimagined;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.GT5RPostRegistrar;
import org.gtreimagined.gt5r.events.forge.RemappingEvents;
import org.gtreimagined.gt5r.integration.forge.tfc.TFCRegistrar;
import org.gtreimagined.gt5r.loader.WorldGenLoader;
import org.gtreimagined.gt5r.proxy.ClientHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GT5RRef.ID)
public class GT5RForge {

    public GT5RForge(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(RemappingEvents.class);
        MinecraftForge.EVENT_BUS.addListener(GT5RForge::registerRecipeLoaders);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(GT5RForge::registerCraftingLoaders);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(GT5RForge::onProviders);
        MinecraftForge.EVENT_BUS.addListener(GT5RForge::onWorldGen);
        new GT5RPostRegistrar();
        new GT5Reimagined();
        new TFCRegistrar();

    }

    private static void onProviders(AntimatterProvidersEvent ev) {
        GT5Reimagined.onProviders(ev.getEvent());
    }

    private static void registerCraftingLoaders(AntimatterCraftingEvent event) {
        GT5Reimagined.registerCraftingLoaders(event.getEvent());
    }

    private static void registerRecipeLoaders(AntimatterLoaderEvent event) {
        GT5Reimagined.registerRecipeLoaders(event.sender, event.registrat);
    }

    private static void onWorldGen(AntimatterWorldGenEvent event){
        WorldGenLoader.init(event.getEvent());
    }

    private void clientSetup(final FMLClientSetupEvent e) {
        e.enqueueWork(ClientHandler::setup);
    }

    private void setup(final FMLCommonSetupEvent e) {
    }
}
