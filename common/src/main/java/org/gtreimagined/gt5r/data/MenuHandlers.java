package org.gtreimagined.gt5r.data;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.antimatter.capability.IGuiHandler;
import muramasa.antimatter.gui.MenuHandlerMachine;
import muramasa.antimatter.gui.container.ContainerMultiMachine;
import net.minecraft.world.entity.player.Inventory;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityFusionReactor;

public class MenuHandlers {
    public static MenuHandlerMachine<BlockEntityFusionReactor, ? extends ContainerMultiMachine> FUSION_MENU_HANDLER = new MenuHandlerMachine(GT5RRef.ID, "container_fusion_reactor") {
        @Override
        public ContainerMultiMachine getMenu(IGuiHandler tile, Inventory playerInv, int windowId) {
            return tile instanceof BlockEntityMachine ? new ContainerMultiMachine((BlockEntityMultiMachine<?>) tile, playerInv, this, windowId) : null;
        }
        @Override
        public String screenDomain() {
            return GT5RRef.ID;
        }

        @Override
        public String screenID() {
            return "fusion_reactor";
        }
    };

    public static void init(){

    }
}
