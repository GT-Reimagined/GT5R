package org.gtreimagined.gt5r.data;

import com.gtnewhorizon.structurelib.structure.IStructureElement;
import com.gtnewhorizon.structurelib.structure.StructureUtility;
import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.structure.FakeTileElement;
import muramasa.antimatter.util.int3;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.gtreimagined.gt5r.block.BlockCoil;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityCokeOven;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityCombustionEngine;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityDistillationTower;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityElectricBlastFurnace;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityFusionReactor;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityImplosionCompressor;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeAutoclave;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeBath;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeBoiler;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeCentrifuge;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeChemicalReactor;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeElectrolyzer;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeHeatExchanger;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeMacerator;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeOreWasher;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeSifter;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityLargeTurbine;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityMultiSmelter;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityOilCrackingUnit;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityOilDrillingRig;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityOreMiningRig;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityPrimitiveBlastFurnace;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityProcessingArray;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityPyrolysisOven;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityTreeGrowthSimulator;
import org.gtreimagined.gt5r.blockentity.multi.BlockEntityVacuumFreezer;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.gtnewhorizon.structurelib.structure.StructureUtility.*;
import static muramasa.antimatter.data.AntimatterMaterialTypes.BLOCK;
import static muramasa.antimatter.data.AntimatterMaterialTypes.FRAME;
import static muramasa.antimatter.machine.Tier.HV;
import static muramasa.antimatter.machine.Tier.LUV;
import static muramasa.antimatter.structure.AntimatterStructureUtility.ofHatch;
import static muramasa.antimatter.structure.AntimatterStructureUtility.ofHatchMinTier;
import static org.gtreimagined.gt5r.data.GT5RMachines.*;

public class Structures {

    /** Special Case Elements **/
    public static IStructureElement<?> AIR_OR_LAVA = ofChain(StructureUtility.isAir(), StructureUtility.ofBlockAdder((w, b) -> b == Blocks.LAVA || b == GT5RBlocks.LAVA, Blocks.LAVA));
    public static IStructureElement<?> GLASS_BLOCK = ofBlock(Blocks.GLASS);
    public static IStructureElement<?> LITHIUM_BLOCK = ofBlock(BLOCK.getBlockMaterialTag(Materials.Lithium));
    public static final FakeTileElement<BlockEntityCokeOven> FAKE_CASING = new FakeTileElement<>(GT5RBlocks.CASING_FIRE_BRICK);
    public static void init() {

        BLAST_FURNACE.setStructure(BlockEntityElectricBlastFurnace.class, b -> b.part("main")
                .of("CCC", "CFC", "CCC").of("BBB", "B-B", "BBB").of(1).of("H~H", "HCH", "HHH").build()
                .at('F', MUFFLER_HATCH)
                .at('B', ofCoil(BlockEntityElectricBlastFurnace::setCoilData, BlockEntityElectricBlastFurnace::getCoilData))
                .at('C', GT5RBlocks.CASING_HEAT_PROOF)
                .at('H', GT5RBlocks.CASING_HEAT_PROOF, INPUT_BUS, OUTPUT_BUS, INPUT_HATCH, OUTPUT_HATCH, ENERGY_HATCH)
                .min(1, INPUT_BUS, OUTPUT_BUS, ENERGY_HATCH).offset(1, 3, 0).build()
        );

        COKE_OVEN.setStructure(BlockEntityCokeOven.class, b -> b.part("main")
            .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
            .atElement('C', FAKE_CASING).offset(1, 1, 0)
            .build()
        );

        COMBUSTION_ENGINE.setStructure(BlockEntityCombustionEngine.class, b -> b.part("main")
                .of("VVV", "CMC", "CMC", "CCC").of("V~V", "HGH", "HGH", "CEC").of("VVV", "CCC", "CCC", "CCC").build()
                .at('C', GT5RBlocks.CASING_TITANIUM).at('V', GT5RBlocks.CASING_ENGINE_INTAKE).at('M', GT5RBlocks.CASING_TITANIUM, MUFFLER_HATCH)
                .at('H', GT5RBlocks.CASING_TITANIUM, INPUT_HATCH, OUTPUT_HATCH).at('E', DYNAMO_HATCH).at('G', GT5RBlocks.CASING_GEARBOX_TITANIUM)
                .offset(1, 1, 0).min(2, INPUT_HATCH).exact(1, MUFFLER_HATCH).build()
        );

        CRACKING_UNIT.setStructure(BlockEntityOilCrackingUnit.class, b -> b.part("main")
                .of("CBMBC", "CBMBC", "CBMBC").of( "CB~BC", "L---R", "CBMBC").of(0).build()
                .at('C', GT5RBlocks.CASING_STAINLESS_STEEL)
                //.at("B", "coil", AntimatterAPI.all(BlockCoil.class))
                .at('B', GT5RBlocks.COIL_CUPRONICKEL)
                .at('L', OUTPUT_HATCH, GT5RBlocks.CASING_STAINLESS_STEEL)
                .at('R', INPUT_HATCH)
                .at('M', GT5RBlocks.CASING_STAINLESS_STEEL, INPUT_BUS, INPUT_HATCH, OUTPUT_BUS, ENERGY_HATCH)
                .offset(2, 1, 0).max(1, OUTPUT_HATCH).min(2, INPUT_HATCH).min(1, ENERGY_HATCH).build()
        );



        DISTLLATION_TOWER.setStructure(BlockEntityDistillationTower.class, b -> b.part("bottom")
                .of("H~H", "HHH", "HHH").build()
                .part("layer").of("CCC", "C-C", "CCC").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).max(11).build()
                .part("top").of("CCC", "CCC", "CCC").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).build()
                .atElement('C', ofChain(StructureUtility.<BlockEntityDistillationTower>ofBlock(GT5RBlocks.CASING_STAINLESS_STEEL), ofHatch(OUTPUT_HATCH, (distillationTower, world, pos, machine, handler) -> {
                    int currentY = pos.getY() - distillationTower.getBlockPos().getY();
                    if (distillationTower.HATCH_LAYERS.contains(currentY)) return false;
                    distillationTower.HATCH_LAYERS.add(currentY);
                    distillationTower.FO_HATCHES.add(handler);
                    return true;
                })))
                .at('H', GT5RBlocks.CASING_STAINLESS_STEEL, INPUT_HATCH, ENERGY_HATCH, OUTPUT_BUS)
                .offset(1, 0, 0).min(1, ENERGY_HATCH, OUTPUT_HATCH).exact(1, INPUT_HATCH)
                .setStructurePartCheckCallback((structureDefinition, tile, part, i, newOffset) -> {
                    tile.FO_HATCHES.clear();
                    boolean check = structureDefinition.check(tile, part, tile.getLevel(), tile.getExtendedFacing(), tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), newOffset.getX(), newOffset.getY(), newOffset.getZ(), !tile.isStructureValid());
                     if (!part.equals("bottom")){
                        if (check){
                            tile.FO_HATCHES.forEach(h -> tile.addComponent(OUTPUT_HATCH.getId(), h));
                            tile.LAYERS.add(i);
                        } else {
                            tile.HATCH_LAYERS.remove(i);
                        }
                    }
                    return check && tile.LAYERS.size() == tile.HATCH_LAYERS.size();
                }).build());
        CRYO_DISTLLATION_TOWER.setStructure(BlockEntityDistillationTower.class, b -> b.part("bottom")
                .of("H~H", "HHH", "HHH").build()
                .part("layer").of("CCC", "C-C", "CCC").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).max(11).build()
                .part("top").of("CCC", "CCC", "CCC").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).build()
                .atElement('C', ofChain(StructureUtility.<BlockEntityDistillationTower>ofBlock(GT5RBlocks.CASING_FROST_PROOF), ofHatch(OUTPUT_HATCH, (distillationTower, world, pos, machine, handler) -> {
                    int currentY = pos.getY() - distillationTower.getBlockPos().getY();
                    if (distillationTower.HATCH_LAYERS.contains(currentY)) return false;
                    distillationTower.HATCH_LAYERS.add(currentY);
                    distillationTower.FO_HATCHES.add(handler);
                    return true;
                })))
                .at('H', GT5RBlocks.CASING_FROST_PROOF, INPUT_HATCH, ENERGY_HATCH)
                .offset(1, 0, 0).min(1, ENERGY_HATCH, OUTPUT_HATCH).exact(1, INPUT_HATCH)
                .setStructurePartCheckCallback((structureDefinition, tile, part, i, newOffset) -> {
                    tile.FO_HATCHES.clear();
                    boolean check = structureDefinition.check(tile, part, tile.getLevel(), tile.getExtendedFacing(), tile.getBlockPos().getX(), tile.getBlockPos().getY(), tile.getBlockPos().getZ(), newOffset.getX(), newOffset.getY(), newOffset.getZ(), !tile.isStructureValid());
                    if (!part.equals("bottom")){
                        if (check){
                            tile.FO_HATCHES.forEach(h -> tile.addComponent(OUTPUT_HATCH.getId(), h));
                            tile.LAYERS.add(i);
                        } else {
                            tile.HATCH_LAYERS.remove(i);
                        }
                    }
                    return check && tile.LAYERS.size() == tile.HATCH_LAYERS.size();
                }).build());
        LARGE_HEAT_EXCHANGER.setStructure(BlockEntityLargeHeatExchanger.class, b -> b.part("main")
                .of("DDD", "DOD", "DDD").of("CCC", "CPC", "CCC").of(1).of("D~D", "DID", "DDD").build()
                .at('D', GT5RBlocks.CASING_TITANIUM)
                .at('C', GT5RBlocks.CASING_TITANIUM, OUTPUT_HATCH, INPUT_HATCH).at('P', GT5RBlocks.CASING_PIPE_TITANIUM)
                .at('I', INPUT_HATCH).at('O', OUTPUT_HATCH)
                .offset(1, 3, 0).min(2, OUTPUT_HATCH, INPUT_HATCH).build());
        IMPLOSION_COMPRESSOR.setStructure(BlockEntityImplosionCompressor.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of("CCC", "CCC", "CCC").build()
                .at('C', GT5RBlocks.CASING_SOLID_STEEL, INPUT_BUS, OUTPUT_BUS, ENERGY_HATCH, MUFFLER_HATCH)
                .min(1, INPUT_BUS, OUTPUT_BUS, ENERGY_HATCH).exact(1, MUFFLER_HATCH).offset(1, 1, 0).build()
        );
        LARGE_AUTOCLAVE.setStructure(BlockEntityLargeAutoclave.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("CCC", "C-C", "CCC").of("C~C", "CcC", "CCC").build()
                .at('C', GT5RBlocks.CASING_STAINLESS_STEEL, INPUT_HATCH, INPUT_BUS, OUTPUT_BUS, ENERGY_HATCH)
                .atElement('c', ofCoil(BlockEntityLargeAutoclave::setCoilData, BlockEntityLargeAutoclave::getCoilData))
                .offset(1, 2, 0).min(1, INPUT_BUS, INPUT_HATCH, OUTPUT_BUS, ENERGY_HATCH).build()
        );
        LARGE_BOILER.setStructure(BlockEntityLargeBoiler.class, b -> b.part("main")
                .of("BBB", "BBB", "BBB").of("BBB", "BPB", "BBB").of(1).of(1).of("F~F", "FFF", "FFF").build()
                .atElement('F', StructureUtility.<BlockEntityLargeBoiler>ofChain(
                        lazy(t -> ofBlock(t.getFireboxCasing())),
                        ofHatch(INPUT_HATCH),
                        ofHatch(INPUT_BUS),
                        ofHatch(MUFFLER_HATCH)))
                .atElement('B', StructureUtility.<BlockEntityLargeBoiler>ofChain(
                        lazy(t -> ofBlock(t.getCasing())),
                        ofHatch(OUTPUT_HATCH)))
                .atElement('P', lazy(t -> ofBlock(t.getPipeCasing())))
                .max(1, INPUT_BUS).minMax(1, 2, INPUT_HATCH).exact(1, MUFFLER_HATCH).offset(1, 4, 0).build());
        LARGE_BATHING_VAT.setStructure(BlockEntityLargeBath.class, b -> b.part("main")
                .of("WWWWW", "WWWWW", "WWWWW", "WWWWW", "WWWWW")
                .of("WW~WW", "WWWWW", "WWWWW", "WWWWW", "WWWWW").build()
                .at('W', GT5RBlocks.STAINLESS_STEEL_WALL, INPUT_BUS, OUTPUT_BUS, INPUT_HATCH, OUTPUT_HATCH)
                .offset(2, 1, 0).min(1, INPUT_BUS, INPUT_HATCH, OUTPUT_BUS).build());
        LARGE_CENTRIFUGE.setStructure(BlockEntityLargeCentrifuge.class, b -> b.part("main")
                .of("CCC", "CcC", "CCC").of("C~C", "CCC", "CCC").build()
                .at('C', GT5RBlocks.CASING_TUNGSTENSTEEL, INPUT_BUS, OUTPUT_BUS, INPUT_HATCH, OUTPUT_HATCH, ENERGY_HATCH)
                .at('c', GT5RBlocks.CASING_TUNGSTENSTEEL)
                .offset(1, 1, 0).min(1, ENERGY_HATCH).build()
        );
        LARGE_CHEMICAL_REACTOR.setStructure(BlockEntityLargeChemicalReactor.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("C~C", "CPC", "CCC").of("CCC", "CcC", "CCC").build()
                .at('C', GT5RBlocks.CASING_CHEMICALLY_INERT, INPUT_BUS, OUTPUT_BUS, INPUT_HATCH, OUTPUT_HATCH, ENERGY_HATCH)
                .at('P', GT5RBlocks.CASING_PIPE_PTFE).at('c', ofCoil(BlockEntityLargeChemicalReactor::setCoilData, BlockEntityLargeChemicalReactor::getCoilData))
                .offset(1, 1, 0).min(1, ENERGY_HATCH).build()
        );

        LARGE_ELECTROLYZER.setStructure(BlockEntityLargeElectrolyzer.class, b -> b.part("main")
                .of("CCCCC", "cEEEc", "cEEEc", "cEEEc", "CCCCC").of("CC~CC", "CCCCC", "CCCCC", "CCCCC", "CCCCC").build()
                .at('C', GT5RBlocks.CASING_STAINLESS_STEEL, INPUT_BUS, OUTPUT_BUS, INPUT_HATCH, OUTPUT_HATCH, ENERGY_HATCH)
                .at('c', GT5RBlocks.COIL_NICHROME).at('E', GT5RBlocks.ELECTROLYTIC_CELL)
                .offset(2, 1, 0).min(1, ENERGY_HATCH).build()
        );

        LARGE_PULVERIZER.setStructure(BlockEntityLargeMacerator.class, b -> b.part("main")
                .of("CCCCC", "CGGGC", "CGGGC", "CGGGC", "CCCCC").of(0).of("CC~CC", "CCCCC", "CCCCC", "CCCCC", "CCCCC").build()
                .at('C', GT5RBlocks.TUNGSTENSTEEL_WALL, INPUT_BUS, OUTPUT_BUS, ofHatchMinTier(ENERGY_HATCH, HV))
                .atElement('G', onElementPass((el, t, w, x, y, z) -> {
                    BlockState state = w.getBlockState(new BlockPos(x, y, z));
                    w.setBlock(new BlockPos(x, y, z), state.setValue(BlockStateProperties.HORIZONTAL_FACING, t.getFacing()), 3);
                }, ofBlock(GT5RBlocks.GRINDING_WHEELS)))
                .offset(2, 2, 0).min(1, ENERGY_HATCH, INPUT_BUS, OUTPUT_BUS).build()
        );
        LARGE_ORE_WASHER.setStructure(BlockEntityLargeOreWasher.class, b -> b.part("main")
                .of("OOO", "OOO", "OOO", "OOO", "OOO", "OOO", "OOO")
                .of("CCC", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC")
                .of("C~C", "CCC", "CCC", "CCC", "CCC", "CCC", "CCC").build()
                .atElement('O', onElementPass((el, t, w, x, y, z) -> {
                    BlockState state = w.getBlockState(new BlockPos(x, y, z));
                    w.setBlock(new BlockPos(x, y, z), state.setValue(BlockStateProperties.HORIZONTAL_FACING, t.getFacing()), 3);
                }, ofBlock(GT5RBlocks.ORE_WASHING_PARTS)))
                .at('C', GT5RBlocks.TITANIUM_WALL, INPUT_BUS, INPUT_HATCH, OUTPUT_BUS, ENERGY_HATCH)
                .offset(1, 2, 0).min(1, ENERGY_HATCH, INPUT_HATCH, INPUT_BUS, OUTPUT_BUS).build());
        LARGE_SIFTER.setStructure(BlockEntityLargeSifter.class, b -> b.part("bottom")
                .of("HH~HH", "HHHHH", "HHHHH").build()
                .part("layer").of("OOOOO", "OFFFO", "OOOOO").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).max(5).min(5).build()
                .part("top").of("IIIII", "IFFFI", "IIIII").offsetFunction((i, int3) -> new int3(int3.getX(), int3.getY() + i, int3.getZ())).build()
                .atElement('H', ofChain(StructureUtility.<BlockEntityLargeSifter>ofBlock(GT5RBlocks.CASING_TITANIUM), ofHatch(OUTPUT_BUS, (largeSifter, world, pos, machine, handler) -> {
                    if (!(handler.getTile() instanceof BlockEntityMachine<?> machineTile)) return false;
                    if (machineTile.getMachineTier() != Tier.ULV){
                        return false;
                    }
                    int currentY = pos.getY() - largeSifter.getBlockPos().getY();
                    if (largeSifter.HATCH_LAYERS.contains(currentY)) return false;
                    largeSifter.HATCH_LAYERS.add(currentY);
                    largeSifter.addComponent(machine.getId(), handler);
                    return true;
                }), ofHatchMinTier(ENERGY_HATCH, HV)))
                .atElement('O', ofChain(StructureUtility.<BlockEntityLargeSifter>ofBlock(GT5RBlocks.CASING_TITANIUM), ofHatch(OUTPUT_BUS, (largeSifter, world, pos, machine, handler) -> {
                    if (!(handler.getTile() instanceof BlockEntityMachine<?> machineTile)) return false;
                    if (machineTile.getMachineTier() != Tier.ULV){
                        return false;
                    }
                    int currentY = pos.getY() - largeSifter.getBlockPos().getY();
                    if (largeSifter.HATCH_LAYERS.contains(currentY)) return false;
                    largeSifter.HATCH_LAYERS.add(currentY);
                    largeSifter.addComponent(machine.getId(), handler);
                    return true;
                })))
                .at('I', GT5RBlocks.CASING_TITANIUM, INPUT_BUS)
                .at('F', GT5RBlocks.FILTER_CASING)
                .offset(2, 0, 0).min(1, INPUT_BUS, ENERGY_HATCH).exact(6, OUTPUT_BUS).build()
        );
        LARGE_TURBINE.setStructure(BlockEntityLargeTurbine.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC", "CCC").of("C~C", "H-H", "H-H", "CEC").of(0).build()
                .atElement('C', StructureUtility.lazy(t -> ofBlock(t.getCasing())))
                .atElement('H', StructureUtility.<BlockEntityLargeTurbine>ofChain(
                        StructureUtility.lazy(t -> ofBlock(t.getCasing())),
                        ofHatch(INPUT_HATCH),
                        ofHatch(OUTPUT_HATCH)))
                .atElement('E', ofHatch(DYNAMO_HATCH))
                .min(1, INPUT_HATCH, OUTPUT_HATCH).offset(1, 1, 0).build()
        );
        LARGE_TURBINE.setStructure(BlockEntityLargeTurbine.class, Tier.EV, b -> b.part("main")
                .of("CCC", "CMC", "CMC", "CCC").of("C~C", "H-H", "H-H", "CEC").of("CCC", "CCC", "CCC", "CCC").build()
                .atElement('C', StructureUtility.lazy(t -> ofBlock(t.getCasing())))
                .atElement('H', StructureUtility.<BlockEntityLargeTurbine>ofChain(
                        StructureUtility.lazy(t -> ofBlock(t.getCasing())),
                        ofHatch(INPUT_HATCH),
                        ofHatch(OUTPUT_HATCH),
                        ofHatch(MUFFLER_HATCH)))
                .atElement('M', StructureUtility.<BlockEntityLargeTurbine>ofChain(
                        StructureUtility.lazy(t -> ofBlock(t.getCasing())),
                        ofHatch(MUFFLER_HATCH)
                ))
                .atElement('E', ofHatch(DYNAMO_HATCH))
                .min(1, INPUT_HATCH).exact(1,MUFFLER_HATCH).offset(1, 1, 0).build()
        );

        MULTI_SMELTER.setStructure(BlockEntityMultiSmelter.class, b -> b.part("main")
                .of("CCC", "CMC", "CCC").of("BBB", "B-B", "BBB").of("H~H", "HHH", "HHH").build()
                .atElement('B', ofCoil(BlockEntityMultiSmelter::setCoilData, BlockEntityMultiSmelter::getCoilData)).at('H', GT5RBlocks.CASING_HEAT_PROOF, INPUT_BUS, OUTPUT_BUS, ENERGY_HATCH)
                .at('C', GT5RBlocks.CASING_HEAT_PROOF).at('M', MUFFLER_HATCH)
                .offset(1, 2, 0).min(1, INPUT_BUS, OUTPUT_BUS, ENERGY_HATCH).build()
        );

        PRIMITIVE_BLAST_FURNACE.setStructure(BlockEntityPrimitiveBlastFurnace.class, b -> b.part("main")
            .of("CCC", "C-C", "CCC").of("CCC", "CBC", "CCC").of("C~C", "CBC", "CCC").of("CCC", "CCC", "CCC").build()
            .at('C', GT5RBlocks.CASING_FIRE_BRICK).atElement('B', (IStructureElement<BlockEntityPrimitiveBlastFurnace>) AIR_OR_LAVA)
                .offset(1, 2, 0).build()
        );

        PROCESSING_ARRAY.setStructure(BlockEntityProcessingArray.class, b -> b.part("main")
                .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
                .at('C', GT5RBlocks.CASING_TUNGSTENSTEEL, INPUT_BUS, OUTPUT_BUS, INPUT_HATCH, OUTPUT_HATCH, ENERGY_HATCH)
                .offset(1, 1, 0).min(1, ENERGY_HATCH).build()
        );

        PYROLYSIS_OVEN.setStructure(BlockEntityPyrolysisOven.class, b -> b.part("main")
                .of("UUUUU", "UHHHU", "UHHHU", "UHHHU", "UUUUU").of("UUUUU", "U---U", "U---U", "U---U", "UUUUU").of(1)
                .of("BB~BB", "BCCCB", "BCCCB", "BCCCB", "BBBBB").build()
                .atElement('C', ofCoil(BlockEntityPyrolysisOven::setCoilData, BlockEntityPyrolysisOven::getCoilData)).at('B', GT5RBlocks.CASING_ULV, OUTPUT_BUS, ENERGY_HATCH, OUTPUT_HATCH)
                .at('U', GT5RBlocks.CASING_ULV).at('H', GT5RBlocks.CASING_ULV, INPUT_BUS, INPUT_HATCH, MUFFLER_HATCH)
                .offset(2, 3, 0).min(1, INPUT_BUS, ENERGY_HATCH).exact(1, MUFFLER_HATCH).build()
        );

        OIL_DRILLING_RIG.setStructure(BlockEntityOilDrillingRig.class, b -> b.part("main")
                .of("   ", " F ", "   ").of(0).of(0).of(" F ", "FCF", " F ").of(3).of(3).of("H~H", "HCH", "HHH").build()
                .at('F', FRAME.get().get(Materials.Steel).asBlock()).at('C', GT5RBlocks.CASING_SOLID_STEEL)
                .at('H', GT5RBlocks.CASING_SOLID_STEEL, ENERGY_HATCH, OUTPUT_HATCH, OUTPUT_BUS)
                .offset(1, 6, 0).min(1, OUTPUT_HATCH, ENERGY_HATCH).build());

        ORE_MINING_RIG.setStructure(BlockEntityOreMiningRig.class, b -> b.part("main")
                .of("   ", " F ", "   ").of(0).of(0).of(" F ", "FCF", " F ").of(3).of(3).of("H~H", "HCH", "HHH").build()
                .at('F', FRAME.get().get(Materials.Steel).asBlock()).at('C', GT5RBlocks.CASING_SOLID_STEEL)
                .at('H', GT5RBlocks.CASING_SOLID_STEEL, ENERGY_HATCH, OUTPUT_BUS, INPUT_HATCH)
                .offset(1, 6, 0).min(1, OUTPUT_BUS, INPUT_HATCH, ENERGY_HATCH).build());

        TREE_GROWTH_SIMULATOR.setStructure(BlockEntityTreeGrowthSimulator.class, b -> b.part("main")
                .of("CCCCC", "CCCCC", "CCCCC", "CCCCC", "CCCCC").of("CCCCC", "C---C", "C---C", "C---C", "CCCCC").of(1).of(1).of(1).of(1).of(1)
                .of("CCCCC", "CDDDC", "CDDDC", "CDDDC", "CCCCC").of("BB~BB", "BBBBB", "BBBBB", "BBBBB", "BBBBB").build()
                .at('C', GT5RBlocks.CASING_PLASTIC, OUTPUT_BUS, ENERGY_HATCH)
                .at('B', GT5RBlocks.CASING_BLACK_BRONZE, ENERGY_HATCH, INPUT_BUS, INPUT_HATCH).at('D', BlockTags.DIRT)
                .offset(2, 8, 0).min(1, INPUT_BUS, INPUT_HATCH, OUTPUT_BUS, ENERGY_HATCH).build()
        );

        VACUUM_FREEZER.setStructure(BlockEntityVacuumFreezer.class, b -> b.part("main")
            .of("CCC", "CCC", "CCC").of("C~C", "C-C", "CCC").of(0).build()
            .at('C', GT5RBlocks.CASING_FROST_PROOF, INPUT_BUS, OUTPUT_BUS, INPUT_HATCH, OUTPUT_BUS, ENERGY_HATCH)
            .offset(1, 1, 0).min(1, ENERGY_HATCH).build()
        );

        FUSION_REACTOR.setStructure(BlockEntityFusionReactor.class, b -> b.part("main")
            .of(
                "               ",
                "      BOB      ",
                "    OO   OO    ",
                "   O       O   ",
                "  O         O  ",
                "  O         O  ",
                " B           B ",
                " O           O ",
                " B           B ",
                "  O         O  ",
                "  O         O  ",
                "   O       O   ",
                "    OO   OO    ",
                "      BOB      ",
                "               "
            ).of("      XOX      ",
                 "    OOCCCOO    ",
                 "   ECCXOXCCE   ",
                 "  ECEO   OECE  ",
                 " OCE       ECO ",
                 " OCO       OCO ",
                 "XCX         XCX",
                 "OCO         OCO",
                 "XCX         XCX",
                 " OCO       OCO ",
                 " OCE       ECO ",
                 "  ECEO   OECE  ",
                 "   ECCX~XCCE   ",
                 "    OOCCCOO    ",
                 "      XOX      ").of(0).build()
                .at('O', GT5RBlocks.CASING_FUSION).at('C', GT5RBlocks.COIL_FUSION)
                .atElement('B', StructureUtility.<BlockEntityFusionReactor>ofChain(
                        ofBlock(GT5RBlocks.CASING_FUSION),
                        ofHatch(INPUT_HATCH),
                        ofHatch(INPUT_BUS)))
                .atElement('X', StructureUtility.<BlockEntityFusionReactor>ofChain(
                        ofBlock(GT5RBlocks.CASING_FUSION),
                        ofHatch(OUTPUT_HATCH),
                        ofHatch(OUTPUT_BUS)))
                .atElement('E', StructureUtility.<BlockEntityFusionReactor>ofChain(ofBlock(GT5RBlocks.CASING_FUSION), ofHatchMinTier(ENERGY_HATCH, LUV)))
            .offset(7, 1, 12).min(1, INPUT_HATCH).min(1, OUTPUT_HATCH).min(4, ENERGY_HATCH).build());
    }

    /**
     * Assume all coils accepted.
     *
     * @see #ofCoil(BiPredicate, Function)
     */
    public static <T> IStructureElement<T> ofCoil(BiConsumer<T, BlockCoil.CoilData> aHeatingCoilSetter,
                                                  Function<T, BlockCoil.CoilData> aHeatingCoilGetter) {
        return ofCoil((t, l) -> {
            aHeatingCoilSetter.accept(t, l);
            return true;
        }, aHeatingCoilGetter);
    }

    /**
     * Heating coil structure element.
     *
     * @param aHeatingCoilSetter Notify the controller of this new coil. Got called exactly once per coil. Might be
     *                           called less times if structure test fails. If the setter returns false then it assumes
     *                           the coil is rejected.
     * @param aHeatingCoilGetter Get the current heating level. Null means no coil recorded yet.
     */
    public static <T> IStructureElement<T> ofCoil(BiPredicate<T, BlockCoil.CoilData> aHeatingCoilSetter,
                                                  Function<T, BlockCoil.CoilData> aHeatingCoilGetter) {
        if (aHeatingCoilSetter == null || aHeatingCoilGetter == null) {
            throw new IllegalArgumentException();
        }
        return new IStructureElement<>() {

            @Override
            public boolean check(T t, Level world, int x, int y, int z) {
                Block block = world.getBlockState(new BlockPos(x, y, z)).getBlock();
                if (!(block instanceof BlockCoil coil)) return false;
                BlockCoil.CoilData existingHeat = aHeatingCoilGetter.apply(t),
                        newLevel = coil.getCoilData();
                if (existingHeat == null) {
                    return aHeatingCoilSetter.test(t, newLevel);
                } else {
                    return newLevel == existingHeat;
                }
            }

            @Override
            public boolean spawnHint(T t, Level world, int x, int y, int z, ItemStack trigger) {
                //StructureLibAPI.hintParticle(world, x, y, z, GregTech_API.sBlockCasings5, getMetaFromHint(trigger));
                return true;
            }

            @Override
            public void onStructureFail(T t, Level world, int x, int y, int z) {
                if (aHeatingCoilGetter.apply(t) != null){
                    aHeatingCoilSetter.test(t, null);
                }
            }

            @Override
            public boolean placeBlock(T t, Level world, int x, int y, int z, ItemStack trigger) {
                return false;
                //return world.setBlock(x, y, z, GregTech_API.sBlockCasings5, getMetaFromHint(trigger), 3);
            }

            /*private int getMetaFromHint(ItemStack trigger) {
                return GT_Block_Casings5.getMetaFromCoilHeat(getHeatFromHint(trigger));
            }

            private HeatingCoilLevel getHeatFromHint(ItemStack trigger) {
                return HeatingCoilLevel
                        .getFromTier((byte) Math.min(HeatingCoilLevel.getMaxTier(), Math.max(0, trigger.stackSize - 1)));
            }



            @Override
            public BlocksToPlace getBlocksToPlace(T t, World world, int x, int y, int z, ItemStack trigger,
                                                  AutoPlaceEnvironment env) {
                return BlocksToPlace.create(GregTech_API.sBlockCasings5, getMetaFromHint(trigger));
            }

            @Override
            public PlaceResult survivalPlaceBlock(T t, World world, int x, int y, int z, ItemStack trigger,
                                                  IItemSource s, EntityPlayerMP actor, Consumer<IChatComponent> chatter) {
                return survivalPlaceBlock(
                        t,
                        world,
                        x,
                        y,
                        z,
                        trigger,
                        AutoPlaceEnvironment.fromLegacy(s, actor, chatter));
            }

            @Override
            public PlaceResult survivalPlaceBlock(T t, World world, int x, int y, int z, ItemStack trigger,
                                                  AutoPlaceEnvironment env) {
                Block block = world.getBlock(x, y, z);
                boolean isCoil = block instanceof IHeatingCoil
                        && ((IHeatingCoil) block).getCoilHeat(world.getBlockMetadata(x, y, z)) == getHeatFromHint(trigger);
                if (isCoil) return SKIP;
                return StructureUtility.survivalPlaceBlock(
                        GregTech_API.sBlockCasings5,
                        getMetaFromHint(trigger),
                        world,
                        x,
                        y,
                        z,
                        env.getSource(),
                        env.getActor(),
                        env.getChatter());
            }*/
        };
    }
}
