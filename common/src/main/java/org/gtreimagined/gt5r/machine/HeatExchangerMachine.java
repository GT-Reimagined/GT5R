package org.gtreimagined.gt5r.machine;

import muramasa.antimatter.machine.MachineState;
import muramasa.antimatter.texture.Texture;
import org.gtreimagined.gt5r.blockentity.single.BlockEntitySmallHeatExchanger;

public class HeatExchangerMachine extends SecondaryOutputMachine {
    int rate;
    int efficiency = 10000;
    public HeatExchangerMachine(String domain, String id, int rate) {
        super(domain, id);
        this.rate = rate;
        this.setTile((m, p, s) -> new BlockEntitySmallHeatExchanger(this, p, s, this.rate, this.efficiency));
        this.overlayTexture((type, state, tier, i) -> {
            state = state.getTextureState();
            String stateDir = state == MachineState.IDLE ? "" : state.getId() + "/";
            return new Texture[]{
                    new Texture(domain, "block/machine/overlay/small_heat_exchanger/" + stateDir + "bottom"),
                    new Texture(domain, "block/machine/overlay/small_heat_exchanger/" + stateDir + "top"),
                    new Texture(domain, "block/machine/overlay/small_heat_exchanger/" + stateDir + "back"),
                    new Texture(domain, "block/machine/overlay/small_heat_exchanger/" + stateDir + "front"),
                    new Texture(domain, "block/machine/overlay/small_heat_exchanger/" + stateDir + "side"),
                    new Texture(domain, "block/machine/overlay/small_heat_exchanger/" + stateDir + "side"),
            };
        });
    }

    public HeatExchangerMachine setEfficiency(int efficiency) {
        if (efficiency > 10000) efficiency = 10000;
        if (efficiency < 0) efficiency = 0;
        this.efficiency = efficiency;
        return this;
    }

    public int getRate() {
        return rate;
    }

    public int getEfficiency() {
        return efficiency;
    }
}
