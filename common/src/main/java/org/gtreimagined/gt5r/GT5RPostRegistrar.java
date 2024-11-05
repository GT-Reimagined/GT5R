package org.gtreimagined.gt5r;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import org.gtreimagined.gt5r.data.GT5RMaterialEvents;
import org.gtreimagined.gt5r.material.GregTechMaterialEvent;

public class GT5RPostRegistrar implements IAntimatterRegistrar {
    public GT5RPostRegistrar(){
        if (AntimatterPlatformUtils.INSTANCE.isForge()){
            onRegistrarInit();
        }
    }

    @Override
    public String getId() {
        return GT5RRef.ID + "_post";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {

    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
        event = new GregTechMaterialEvent();
        GT5RMaterialEvents.onMaterialEvent((GregTechMaterialEvent) event);
    }

    @Override
    public void onRegistrarInit() {
        AntimatterAPI.addRegistrar(this);
    }

    @Override
    public int getPriority() {
        return 600;
    }
}
