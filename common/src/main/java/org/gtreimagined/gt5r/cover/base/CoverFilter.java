package org.gtreimagined.gt5r.cover.base;

import muramasa.antimatter.capability.CoverHandler;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.texture.Texture;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

public class CoverFilter extends BaseCover {
    protected boolean blacklist = false;
    protected boolean ignoreNBT = false;
    protected byte filterMode = 0;
    public CoverFilter(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
    }

    public void clearFilter(){
        blacklist = false;
        ignoreNBT = false;
    }

    @Override
    public ItemStack getDroppedStack() {
        ItemStack stack =  super.getDroppedStack();
        stack.getOrCreateTag().putBoolean("blacklist", blacklist);
        stack.getOrCreateTag().putBoolean("ignoreNBT", ignoreNBT);
        stack.getOrCreateTag().putByte("filterMode", filterMode);
        return stack;
    }

    @Override
    public void addInfoFromStack(ItemStack stack) {
        super.addInfoFromStack(stack);
        if (stack.getTag() == null) return;
        CompoundTag tag = stack.getTag();
        blacklist = tag.getBoolean("blacklist");
        ignoreNBT = tag.getBoolean("ignoreNBT");
        filterMode = tag.getByte("filterMode");
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag tag = super.serialize();
        tag.putBoolean("blacklist", blacklist);
        tag.putBoolean("ignoreNBT", ignoreNBT);
        tag.putByte("filterMode", filterMode);
        return tag;
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        this.blacklist = nbt.getBoolean("blacklist");
        this.ignoreNBT = nbt.getBoolean("ignoreNBT");
        this.filterMode = nbt.getByte("filterMode");
        if (this.handler.getTile().getLevel() != null && this.handler.getTile().getLevel().isClientSide() && factory.getTextures().size() == 2) {
            if (this.handler instanceof CoverHandler<?> coverHandler && coverHandler.coverTexturer != null && coverHandler.coverTexturer.get(this.side) != null){
                coverHandler.coverTexturer.get(this.side).invalidate();
            }
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
    }

    @Override
    public void setTextures(BiConsumer<String, Texture> texer) {
        if (factory.getTextures().size() == 2){
            texer.accept("overlay", factory.getTextures().get(blacklist ? 1 : 0));
        } else {
            super.setTextures(texer);
        }
    }
}
