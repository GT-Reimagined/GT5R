package org.gtreimagined.gt5r.machine;

import org.gtreimagined.gt5r.GT5RRef;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.machine.MachineFlag;
import muramasa.antimatter.machine.types.BasicMachine;
import org.gtreimagined.gt5r.data.Textures;
import net.minecraft.resources.ResourceLocation;

import static muramasa.antimatter.machine.MachineFlag.UNCULLED;
import static muramasa.antimatter.machine.Tier.NONE;

public class MiniPortalMachine extends BasicMachine {
    public MiniPortalMachine(String domain, String id) {
        super(domain, id);
        this.setTiers(NONE);
        this.addFlags(UNCULLED);
        this.noCovers().allowFrontIO();
        this.itemModelParent(new ResourceLocation(GT5RRef.ID, "block/mini_portal"));
        this.custom(Textures.MINI_PORTAL);
        this.removeFlags(MachineFlag.COVERABLE);
        this.setToolTag(AntimatterDefaultTools.PICKAXE.getToolType());
    }
}
