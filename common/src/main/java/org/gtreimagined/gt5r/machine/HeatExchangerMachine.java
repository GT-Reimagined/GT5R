package org.gtreimagined.gt5r.machine;

import org.gtreimagined.gt5r.blockentity.single.BlockEntitySmallHeatExchanger;

public class HeatExchangerMachine extends SecondaryOutputMachine {
    int rate;
    int efficiency = 10000;
    public HeatExchangerMachine(String domain, String id, int rate) {
        super(domain, id);
        this.rate = rate;
        this.setTile((m, p, s) -> new BlockEntitySmallHeatExchanger(this, p, s, this.rate, this.efficiency));
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
