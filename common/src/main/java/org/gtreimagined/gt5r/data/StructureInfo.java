package org.gtreimagined.gt5r.data;

import muramasa.antimatter.structure.PatternBuilder;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;

import static org.gtreimagined.gt5r.data.GT5RMachines.*;

public class StructureInfo {

    public static void init() {
        // pattern demo
        PatternBuilder builder = new PatternBuilder()
                .of("CCO", "ECM", "CCI").of("BBB", "BAB", "BBB").of(1).of("CCC", "CFC", "CCC")
                .at("F", MUFFLER_HATCH, MUFFLER_HATCH.getFirstTier(), Direction.UP)
                .at("M", BLAST_FURNACE, BLAST_FURNACE.getFirstTier(), Direction.SOUTH)
                .at("C", GT5RBlocks.CASING_HEAT_PROOF.defaultBlockState())
                .at("I", INPUT_BUS, INPUT_BUS.getFirstTier(), Direction.SOUTH)
                .at("O", OUTPUT_BUS, OUTPUT_BUS.getFirstTier(), Direction.SOUTH)
                .at("E", ENERGY_HATCH, ENERGY_HATCH.getFirstTier(), Direction.NORTH);
        BLAST_FURNACE.setStructurePattern(
                // reuse the builder for page COIL_CUPRONICKEL
                builder.at("B", GT5RBlocks.COIL_CUPRONICKEL.defaultBlockState()).description(GT5RBlocks.COIL_CUPRONICKEL.getDescriptionId()).build(),
                // reuse the builder for page COIL_HSSG
                builder.at("B", GT5RBlocks.COIL_HSSG.defaultBlockState()).description(GT5RBlocks.COIL_HSSG.getDescriptionId()).build(),
                // reuse the builder for page COIL_KANTHAL
                builder.at("B", GT5RBlocks.COIL_KANTHAL.defaultBlockState()).description(GT5RBlocks.COIL_KANTHAL.getDescriptionId()).build(),
                // reuse the builder for page COIL_NAQUADAH and replace one casing with(C) the fluid hatch (K).
                builder.of(3, "CCC", "CFC", "CCK")
                        .at("K", INPUT_HATCH, INPUT_HATCH.getFirstTier(), Direction.EAST)
                        .at("B", GT5RBlocks.COIL_NAQUADAH.defaultBlockState())
                        .description(GT5RBlocks.COIL_NAQUADAH.getDescriptionId())
                        .build());
        builder = new PatternBuilder()
                .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of(0)
                .at("M", COKE_OVEN, COKE_OVEN.getFirstTier(), Direction.SOUTH)
                .at("C", GT5RBlocks.CASING_FIRE_BRICK.defaultBlockState())
                .description(COKE_OVEN.getDisplayName(COKE_OVEN.getFirstTier()));
        COKE_OVEN.setStructurePattern(builder.build());
        builder = new PatternBuilder()
                .of("CCC", "CCC", "CCC").of("CCC", "CAM", "CCC").of("CCC", "CAC", "CCC").of(2)
                .at("M", PRIMITIVE_BLAST_FURNACE, PRIMITIVE_BLAST_FURNACE.getFirstTier(), Direction.SOUTH)
                .at("C", GT5RBlocks.CASING_FIRE_BRICK.defaultBlockState());
        PRIMITIVE_BLAST_FURNACE.setStructurePattern(builder
                .at("A", Blocks.AIR.defaultBlockState()).description(PRIMITIVE_BLAST_FURNACE.getDisplayName(PRIMITIVE_BLAST_FURNACE.getFirstTier())).build());

        builder = new PatternBuilder()
                .of("CCC", "CCC", "CCC").of("CCI", "EAM", "CCO").of("CCC", "CCC", "CCC")
                .at("M", VACUUM_FREEZER, VACUUM_FREEZER.getFirstTier(), Direction.SOUTH)
                .at("C", GT5RBlocks.CASING_FROST_PROOF.defaultBlockState())
                .at("I", INPUT_BUS, INPUT_BUS.getFirstTier(), Direction.SOUTH)
                .at("O", OUTPUT_BUS, OUTPUT_BUS.getFirstTier(), Direction.SOUTH)
                .at("E", ENERGY_HATCH, ENERGY_HATCH.getFirstTier(), Direction.NORTH);
        VACUUM_FREEZER.setStructurePattern(builder.build());
        builder = new PatternBuilder()
                .of("CCCC", "CCCC", "CCCC").of("CCCC", "EAAM", "CIOC").of(0)
                .at("M", LARGE_TURBINE, LARGE_TURBINE.getFirstTier(), Direction.SOUTH)
                .at("C", GT5RBlocks.CASING_TURBINE_TUNGSTENSTEEL.defaultBlockState())
                .at("E", DYNAMO_HATCH, DYNAMO_HATCH.getFirstTier(), Direction.NORTH)
                .at("I", INPUT_HATCH, INPUT_HATCH.getFirstTier(), Direction.EAST)
                .at("O", OUTPUT_HATCH, OUTPUT_HATCH.getFirstTier(), Direction.EAST)
                .description(LARGE_TURBINE.getDisplayName(LARGE_TURBINE.getFirstTier()));
        LARGE_TURBINE.setStructurePattern(builder.build());
    }
}
