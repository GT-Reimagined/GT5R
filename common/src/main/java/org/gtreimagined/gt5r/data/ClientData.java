package org.gtreimagined.gt5r.data;

import org.gtreimagined.gt5r.GT5RRef;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.gui.container.ContainerBasicMachine;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import org.gtreimagined.gt5r.gui.ScreenCoalBoiler;
import org.gtreimagined.gt5r.gui.ScreenFusionReactor;
import org.gtreimagined.gt5r.gui.ScreenSteamMachine;
import net.minecraft.client.gui.screens.MenuScreens;

public class ClientData {
    public final static MenuScreens.ScreenConstructor SCREEN_FUSION_REACTOR = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "fusion_reactor", GT5RRef.ID,(MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenFusionReactor<>((ContainerMultiMachine) a,b,c));
    public static final MenuScreens.ScreenConstructor SCREEN_STEAM = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "steam", GT5RRef.ID, (MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenSteamMachine((ContainerBasicMachine) a,b,c));
    public static final MenuScreens.ScreenConstructor SCREEN_COAL = AntimatterAPI.register(MenuScreens.ScreenConstructor.class, "coal", GT5RRef.ID, (MenuScreens.ScreenConstructor)(a, b, c) -> new ScreenCoalBoiler((ContainerMachine) a,b,c));

    public static void init() {
    }
}
