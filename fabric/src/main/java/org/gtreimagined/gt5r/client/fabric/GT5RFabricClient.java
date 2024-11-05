package org.gtreimagined.gt5r.client.fabric;

import muramasa.antimatter.client.fabric.IAntimatterClientInitializer;
import org.gtreimagined.gt5r.proxy.ClientHandler;

public class GT5RFabricClient implements IAntimatterClientInitializer {
    @Override
    public void onInitializeClient() {
        ClientHandler.setup();
    }
}
