package org.gtreimagined.gt5r.cover.redstone;

import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeTurbine;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.gui.SlotType;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.util.Utils;
import org.gtreimagined.gt5r.cover.base.CoverBasicRedstoneOutput;
import org.gtreimagined.gt5r.items.ItemTurbineRotor;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoverNeedsMaintenance extends CoverBasicRedstoneOutput {
    ScaledMode mode = ScaledMode.SCALED;
    public CoverNeedsMaintenance(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public void onUpdate() {
        if (this.handler.getTile() instanceof BlockEntityLargeTurbine turbine){
            turbine.itemHandler.ifPresent(i -> {
                ItemStack rotor = i.getHandler(SlotType.STORAGE).getItem(0);
                if (rotor.getItem() instanceof ItemTurbineRotor rotor1){
                    if (!mode.scaled){
                        setOutputRedstone(mode.inverted ? 15 : 0);
                    } else {
                        long scale = rotor.getMaxDamage() / 15L;
                        long damage = rotor.getMaxDamage() - rotor.getDamageValue();
                        if (scale > 0){
                            setOutputRedstone(mode.inverted ? (int) (damage / scale) : (int) (15L - damage / scale));
                        } else {
                            setOutputRedstone(mode.inverted ? 15 : 0);
                        }
                    }
                } else {
                    setOutputRedstone(mode.inverted ? 0 : 15);
                }
            });
        }
    }

    @Override
    public InteractionResult onInteract(Player player, InteractionHand hand, Direction side, @Nullable AntimatterToolType type) {
        if (type != null && type.getTag() == AntimatterDefaultTools.SCREWDRIVER.getTag()){
            mode = player.isShiftKeyDown() ? mode.previous() : mode.next();
            this.handler.getTile().setChanged();
            player.sendMessage(Utils.translatable("message.gt5r.needs_maintenance." + (mode.scaled ? "scaled" : "unscaled") + "." + (mode.inverted ? "inverted" : "normal")), player.getUUID());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public enum ScaledMode {
        SCALED(false,true),
        SCALED_INVERTED(true, true),
        UNSCALED(false, false),
        UNSCALED_INVERTED(true, false);
        final boolean inverted;
        final boolean scaled;

        ScaledMode(boolean inverted, boolean scaled) {
            this.inverted = inverted;
            this.scaled = scaled;
        }

        public ScaledMode next(){
            return switch (this){
                case SCALED -> SCALED_INVERTED;
                case SCALED_INVERTED -> UNSCALED;
                case UNSCALED -> UNSCALED_INVERTED;
                case UNSCALED_INVERTED -> SCALED;
            };
        }
        public ScaledMode previous(){
            return switch (this){
                case UNSCALED_INVERTED -> UNSCALED;
                case UNSCALED -> SCALED_INVERTED;
                case SCALED_INVERTED -> SCALED;
                case SCALED -> UNSCALED_INVERTED;
            };
        }
    }
}
