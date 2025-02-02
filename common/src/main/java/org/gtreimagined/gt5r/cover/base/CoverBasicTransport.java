package org.gtreimagined.gt5r.cover.base;

import muramasa.antimatter.blockentity.pipe.BlockEntityPipe;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.cover.ICoverRedstoneSensitive;
import org.gtreimagined.gt5r.cover.ImportExportMode;
import org.gtreimagined.gt5r.cover.RedstoneMode;
import org.jetbrains.annotations.Nullable;

public abstract class CoverBasicTransport extends CoverBasicRedstone implements ICoverRedstoneSensitive {

    protected ImportExportMode exportMode;
    int coverModeInt;

    public CoverBasicTransport(ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        this.exportMode = source.getTile() instanceof BlockEntityPipe<?> ? ImportExportMode.IMPORT : ImportExportMode.EXPORT;
        redstoneMode = RedstoneMode.NO_WORK;
        coverModeInt = exportMode.ordinal();
        addButtons();
    }

    protected void addButtons(){
        addGuiCallback(t -> {
            t.addCycleButton(70, 34, 16, 16, h -> ((CoverBasicRedstone)h).redstoneMode.ordinal(), true, i -> "tooltip.gt5r.redstone_mode." + i, ButtonOverlay.TORCH_OFF, ButtonOverlay.TORCH_ON, ButtonOverlay.REDSTONE);
            t.addCycleButton(88, 34, 16, 16, h -> ((CoverBasicTransport)h).exportMode.ordinal(), true, i -> "tooltip.gt5r.export_mode." + i, ButtonOverlay.EXPORT, ButtonOverlay.IMPORT, ButtonOverlay.EXPORT_IMPORT, ButtonOverlay.IMPORT_EXPORT);
        });
    }

    @Override
    public String getDomain() {
        return GT5RRef.ID;
    }

    @Override
    public void onPlace() {
        super.onPlace();
        if (handler.getTile().getLevel() == null) return;
        if (handler.getTile() instanceof BlockEntityPipe<?> pipe){
            pipe.setConnection(this.side);
        }
    }

    @Override
    public <T> boolean blocksInput(Class<T> cap, @Nullable Direction side) {
        return exportMode == ImportExportMode.EXPORT;
    }

    @Override
    public <T> boolean blocksOutput(Class<T> cap, @Nullable Direction side) {
        return exportMode == ImportExportMode.IMPORT;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        super.onGuiEvent(event, playerEntity);
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){

            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            if (ev.data[1] == 1){
                exportMode = ev.data[0] == 0 ? exportMode.next() : exportMode.previous();
                handler.getTile().setChanged();
            }
        }
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putInt("coverMode", exportMode.ordinal());
        return nbt;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        if (nbt.contains("coverMode")) {
            coverModeInt = nbt.getInt("coverMode");
            if (coverModeInt > 3) coverModeInt = 2;
            exportMode = ImportExportMode.values()[coverModeInt];
        }
    }
}
