package org.gtreimagined.gt5r.items;

import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.StringUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemDataStick extends ItemBasic<ItemDataStick> {
    public ItemDataStick(String domain, String id) {
        super(domain, id);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        CompoundTag nbt = stack.getTag();
        if (nbt != null){
            if (nbt.contains("prospectData")){
                CompoundTag prospect = nbt.getCompound("prospectData");
                String raw = prospect.contains("analyzed") ? "analyzed" : "raw";
                tooltipComponents.add(Utils.translatable("tooltip.gt5r.data_stick." + raw + "_prospection_data"));
                if (prospect.contains("analyzed")){
                    BlockPos pos = BlockPos.of(prospect.getLong("pos"));
                    String dimension = prospect.getString("dimension");
                    tooltipComponents.add(Utils.translatable("tooltip.gt5r.data_stick.by", pos.getX(), pos.getZ(), dimension));
                }
            }
            if (nbt.contains("bookData")){
                CompoundTag compoundTag = nbt.getCompound("bookData");
                String string = compoundTag.getString("title");
                if (!StringUtil.isNullOrEmpty(string)) {
                    tooltipComponents.add(Utils.literal(string));
                }
                string = compoundTag.getString("author");
                if (!StringUtil.isNullOrEmpty(string)) {
                    tooltipComponents.add((Utils.translatable("book.byAuthor", string)));
                }
            }
        }
    }
}
