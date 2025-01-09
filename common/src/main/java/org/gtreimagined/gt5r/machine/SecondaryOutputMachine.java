package org.gtreimagined.gt5r.machine;

import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.machine.types.BasicMachine;
import org.gtreimagined.gt5r.blockentity.single.BlockEntitySecondaryOutput;

public class SecondaryOutputMachine extends BasicMachine implements ISecondaryOutputMachine {
    protected CoverFactory secondaryOutputCover = ICover.emptyFactory;
    public SecondaryOutputMachine(String domain, String id) {
        super(domain, id);
        this.setTile(BlockEntitySecondaryOutput::new);
    }

    @Override
    public CoverFactory getSecondaryOutputCover() {
        return secondaryOutputCover;
    }

    public SecondaryOutputMachine setSecondaryOutputCover(CoverFactory cover) {
        this.secondaryOutputCover = cover;
        return this;
    }
}
