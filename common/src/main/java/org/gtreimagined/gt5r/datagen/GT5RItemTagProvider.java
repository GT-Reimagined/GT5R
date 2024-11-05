package org.gtreimagined.gt5r.datagen;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.gtreimagined.gt5r.data.GT5RTags;
import net.minecraft.world.item.Items;
import org.gtreimagined.gtcore.data.GTCoreItems;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static org.gtreimagined.gt5r.data.Materials.*;
import static org.gtreimagined.gtcore.data.GTCoreTags.*;

public class GT5RItemTagProvider extends AntimatterItemTagProvider {
    public GT5RItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        //this.tag(GT5RTags.CIRCUITS_EXTREME).add(GT5RData.CircuitDataStorage);
        this.tag(CIRCUITS_ELITE).add(GT5RItems.NanoProcessor);
        this.tag(CIRCUITS_MASTER).add(GT5RItems.QuantumProcessor);
        this.tag(RESISTORS).add(GTCoreItems.Resistor, GTCoreItems.SMDResistor);
        this.tag(CAPACITORS).add(GTCoreItems.Capacitor, GTCoreItems.SMDCapacitor);
        this.tag(TRANSISTORS).add(GTCoreItems.Transistor, GTCoreItems.SMDTransistor);
        this.tag(DIODES).add(GTCoreItems.Diode, GTCoreItems.SMDDiode);
        this.tag(FIRESTARTER).add(Items.FLINT_AND_STEEL);
        this.tag(GEM.getMaterialTag(Amethyst)).remove(Items.AMETHYST_SHARD);
        this.tag(GEM.getTag()).remove(Items.AMETHYST_SHARD);
        this.tag(BLOCK.getMaterialTag(Amethyst)).remove(Items.AMETHYST_BLOCK);
        this.tag(PLATES_IRON_ALUMINIUM).addTag(PLATE.getMaterialTag(Iron)).addTag(PLATE.getMaterialTag(WroughtIron)).addTag(PLATE.getMaterialTag(Aluminium));
        this.tag(DUST_LAPIS_LAZURITE).addTag(DUST.getMaterialTag(Lapis)).addTag(DUST.getMaterialTag(Lazurite));
        this.tag(GT5RTags.GRIND_HEADS).add(GTCoreItems.DiamondGrindHead, GTCoreItems.TungstenGrindHead);
        this.tag(DUST_COALS).addTag(DUST.getMaterialTag(Coal)).addTag(DUST.getMaterialTag(Charcoal)).addTag(DUST.getMaterialTag(Carbon));
        this.tag(ForgeCTags.GEMS_QUARTZ_ALL).addTag(GEM.getMaterialTag(MilkyQuartz));
        this.tag(GT5RTags.GEM_SAPPHIRES).addTag(GEM.getMaterialTag(Sapphire)).addTag(GEM.getMaterialTag(GreenSapphire));
        this.tag(GT5RTags.DUST_SAPPHIRES).addTag(DUST.getMaterialTag(Sapphire)).addTag(DUST.getMaterialTag(GreenSapphire));
        this.tag(GT5RTags.DUST_SANDS).addTag(DUST.getMaterialTag(Sand)).addTag(DUST.getMaterialTag(RedSand));
        this.tag(GT5RTags.DUST_SIO).addTag(DUST.getMaterialTag(SiliconDioxide))
                .addTag(DUST.getMaterialTag(Quartzite)).addTag(DUST.getMaterialTag(Quartz))
                .addTag(DUST.getMaterialTag(Glass)).addTag(DUST.getMaterialTag(Flint))
                .addTag(DUST.getMaterialTag(MilkyQuartz));
        if (AntimatterAPI.isModLoaded(Ref.MOD_AE)){
            this.tag(GT5RTags.DUST_SIO).addTag(DUST.getMaterialTag(CertusQuartz));
        }
        this.tag(ForgeCTags.DYES_BLACK).add(Items.INK_SAC);
        this.tag(ForgeCTags.DYES_BLUE).addTag(GEM.getMaterialTag(Lapis)).addTag(GEM.getMaterialTag(Sodalite));
        this.tag(ForgeCTags.DYES_CYAN).addTag(GEM.getMaterialTag(Lazurite));
        this.tag(ForgeCTags.DYES_GREEN).addTag(DUST.getMaterialTag(Malachite));
        this.tag(ForgeCTags.DYES_WHITE).add(Items.BONE_MEAL);
    }
}
