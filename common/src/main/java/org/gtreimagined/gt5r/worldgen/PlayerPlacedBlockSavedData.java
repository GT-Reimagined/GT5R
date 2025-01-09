package org.gtreimagined.gt5r.worldgen;

import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class PlayerPlacedBlockSavedData extends SavedData {
    @Getter
    private final LongSet placedPositions = new LongLinkedOpenHashSet();

    // runtime

    private final ServerLevel serverLevel;

    public static PlayerPlacedBlockSavedData getOrCreate(ServerLevel serverLevel) {
        return serverLevel.getDataStorage().computeIfAbsent(tag -> new PlayerPlacedBlockSavedData(serverLevel, tag), () -> new PlayerPlacedBlockSavedData(serverLevel), "gt5r_player_placed_blocks");
    }

    public PlayerPlacedBlockSavedData(ServerLevel serverLevel) {
        this.serverLevel = serverLevel;
    }

    public PlayerPlacedBlockSavedData(ServerLevel serverLevel, CompoundTag nbt) {
        this(serverLevel);
        var list = nbt.getList("player_placed_positions", Tag.TAG_LONG);
        for (Tag tag : list) {
            if (tag instanceof LongTag l) {
                placedPositions.add(l.getAsLong());
            }
        }

    }

    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        var playerPositions = new ListTag();
        for (long l : placedPositions) {
            playerPositions.add(LongTag.valueOf(l));
        }
        compoundTag.put("player_placed_positions", playerPositions);
        return compoundTag;
    }

    public void addBlockPos(BlockPos pos) {
        placedPositions.add(pos.asLong());
        setDirty();
    }

    public void removeBlockPos(BlockPos pos) {
        placedPositions.remove(pos.asLong());
        setDirty();
    }
}
