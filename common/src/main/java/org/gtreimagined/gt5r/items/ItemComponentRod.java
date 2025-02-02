package org.gtreimagined.gt5r.items;

import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.IMaterialObject;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.blockentity.single.BlockEntityNuclearReactorCore;
import org.gtreimagined.gt5r.data.GT5RItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemComponentRod extends ItemBasic<ItemComponentRod> implements IItemReactorRod, IMaterialObject {
    private final Material material;
    private final int tooltips;

    public ItemComponentRod(String domain, String id, Material material, int tooltips) {
        super(domain, id, new Properties().stacksTo(16).tab(Ref.TAB_ITEMS));
        this.material = material;
        this.tooltips = tooltips;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        for (int i = 0; i < tooltips; i++) {
            tooltipComponents.add(Utils.translatable("tooltip." + getDomain() + "." + getId().replace("/", ".") + "." + i).withStyle(ChatFormatting.AQUA));
        }
    }

    @Override
    public Texture[] getTextures() {
        if (this == GT5RItems.EmptyNuclearFuelRod){
            return new Texture[]{new Texture(GT5RRef.ID, "item/basic/empty_nuclear_fuel_rod")};
        }
        return new Texture[]{new Texture(GT5RRef.ID, "item/basic/nuclear_fuel_rod"), new Texture(GT5RRef.ID, "item/basic/empty_nuclear_fuel_rod")};
    }

    @Override
    public int getItemColor(ItemStack stack, @Nullable Block block, int i) {
        if (i == 0 && material != Material.NULL){
            return material.getRGB();
        }
        return IItemReactorRod.super.getItemColor(stack, block, i);
    }

    @Override
    public boolean isReactorRod(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isModerated(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        return this == GT5RItems.NeutronModeratorRod;
    }

    @Override
    public void updateModeration(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {

    }

    @Override
    public int getReactorRodNeutronEmission(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        return 0;
    }

    @Override
    public boolean getReactorRodNeutronReaction(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        if (this == GT5RItems.NeutronAbsorberRod){
            reactor.heatHandler.ifPresent(h -> h.insertInternal(reactor.oNeutronCounts[slot] * 2, false));
            return true;
        }
        if (this == GT5RItems.NeutronModeratorRod && reactor.getLevel().getGameTime() % 20 == 19){
            short moderation = stack.getTag() == null || !stack.getTag().contains("moderation") ? 0 : stack.getTag().getShort("moderation");
            stack.getOrCreateTag().putShort("oldModeration", moderation);
            stack.getOrCreateTag().putShort("moderation", (short) 0);
        }
        return false;
    }

    @Override
    public int getReactorRodNeutronReflection(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack, int neutrons, boolean moderated) {
        if (this == GT5RItems.NeutronAbsorberRod){
            reactor.mNeutronCounts[slot] += neutrons;
        }
        if (this == GT5RItems.NeutronModeratorRod){
            short oldModeration = stack.getTag() == null || !stack.getTag().contains("oldModeration") ? 0 : stack.getTag().getShort("oldModeration");
            if (neutrons > 0){
                short moderation = stack.getTag() == null || !stack.getTag().contains("moderation") ? 0 : stack.getTag().getShort("moderation");
                moderation++;
                stack.getOrCreateTag().putShort("moderation", moderation);
            }
            return oldModeration * neutrons;
        }
        if (this == GT5RItems.NeutronReflectorRod) return neutrons;
        return 0;
    }

    @Override
    public int getReactorRodNeutronMaximum(BlockEntityNuclearReactorCore reactor, int slot, ItemStack stack) {
        return 0;
    }

    @Override
    public Material getMaterial() {
        return material;
    }
}
