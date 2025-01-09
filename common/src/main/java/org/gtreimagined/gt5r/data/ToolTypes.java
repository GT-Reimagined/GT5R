package org.gtreimagined.gt5r.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.item.ItemBattery;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.pipe.BlockPipe;
import muramasa.antimatter.recipe.ingredient.PropertyIngredient;
import muramasa.antimatter.recipe.material.MaterialRecipe;
import muramasa.antimatter.tool.AntimatterToolType;
import muramasa.antimatter.tool.IAntimatterTool;
import muramasa.antimatter.tool.behaviour.BehaviourExtendedHighlight;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.blockentity.single.BlockEntitySecondaryOutput;
import org.gtreimagined.gt5r.items.ItemPortableScanner;
import org.gtreimagined.gt5r.items.ItemTurbineRotor;
import org.gtreimagined.gtcore.GTCore;
import org.gtreimagined.gtcore.data.GTCoreItems;
import org.gtreimagined.gtcore.data.GTCoreTags;
import org.gtreimagined.gtcore.data.GTCoreTools;
import org.gtreimagined.gtcore.item.ItemPowerUnit;
import org.jetbrains.annotations.NotNull;
import tesseract.TesseractCapUtils;
import tesseract.api.gt.IGTNode;

import java.util.Map;
import java.util.function.BiFunction;

import static muramasa.antimatter.material.Material.NULL;

public class ToolTypes {

    public static final AntimatterToolType SMALL_TURBINE_ROTOR = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GT5RRef.ID, "small_turbine_rotor", 1, 1, 1, -1.0F, 0.0f, false)).setHasSecondary(false).setMaterialTypeItem(GT5RMaterialTypes.SMALL_BROKEN_TURBINE_ROTOR).setTag(new ResourceLocation(Ref.ID, "turbine_rotor")).setToolSupplier(ItemTurbineRotor::new);
    public static final AntimatterToolType TURBINE_ROTOR = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GT5RRef.ID, "turbine_rotor", 1, 1, 1, 1.5F, 0.0f, false)).setHasSecondary(false).setMaterialTypeItem(GT5RMaterialTypes.BROKEN_TURBINE_ROTOR).setDurabilityMultiplier(2).setToolSupplier(ItemTurbineRotor::new);
    public static final AntimatterToolType LARGE_TURBINE_ROTOR = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GT5RRef.ID, "large_turbine_rotor", 1, 1, 1, 4.0F, 0.0f, false)).setHasSecondary(false).setMaterialTypeItem(GT5RMaterialTypes.LARGE_BROKEN_TURBINE_ROTOR).setTag(new ResourceLocation(Ref.ID, "turbine_rotor")).setDurabilityMultiplier(3).setToolSupplier(ItemTurbineRotor::new);
    public static final AntimatterToolType HUGE_TURBINE_ROTOR = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GT5RRef.ID, "huge_turbine_rotor", 1, 1, 1, 2.0F, 0.0f, false)).setHasSecondary(false).setMaterialTypeItem(GT5RMaterialTypes.HUGE_BROKEN_TURBINE_ROTOR).setTag(new ResourceLocation(Ref.ID, "turbine_rotor")).setDurabilityMultiplier(4).setToolSupplier(ItemTurbineRotor::new);
    public static final AntimatterToolType PINCERS = AntimatterAPI.register(AntimatterToolType.class, new AntimatterToolType(GT5RRef.ID, "pincers", 1, 2, 10, 5.0f, 0.0f, false)).setRepairable(false);

    public static final MaterialRecipe.Provider SCANNER_BUILDER = MaterialRecipe.registerProvider("portable-scanner", GT5RRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Tuple<Long, Long> battery = (Tuple<Long, Long>) mats.mats.get("battery");
            ItemStack scanner = new ItemStack(GT5RItems.PortableScanner);
            TesseractCapUtils.INSTANCE.getEnergyHandlerItem(scanner).ifPresent(i -> i.setEnergy(battery.getA()));
            return scanner;
        }

        @Override
        public Map<String, Object> getFromResult(@NotNull ItemStack stack) {
            CompoundTag nbt = stack.getOrCreateTagElement(Ref.TAG_ITEM_ENERGY_DATA);
            return ImmutableMap.of("energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });
    public static final MaterialRecipe.Provider POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool", GT5RRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Material m = (Material) mats.mats.get("secondary");
            Tuple<Long, Long> battery = (Tuple<Long, Long>) mats.mats.get("battery");
            String domain = Ref.ID;
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'), GTCore.ID);
            return type.resolveStack((Material) mats.mats.get("primary"), m == null ? NULL : m, battery.getA(), battery.getB());
        }

        @Override
        public Map<String, Object> getFromResult(@NotNull ItemStack stack) {
            CompoundTag nbt = stack.getOrCreateTagElement(muramasa.antimatter.Ref.TAG_TOOL_DATA);
            Material primary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_PRIMARY_MATERIAL));
            Material secondary = AntimatterAPI.get(Material.class, nbt.getString(muramasa.antimatter.Ref.KEY_TOOL_DATA_SECONDARY_MATERIAL));
            return ImmutableMap.of("primary", primary, "secondary", secondary, "energy", getEnergy(stack).getA(), "maxEnergy", getEnergy(stack).getB());
        }
    });

    public static final MaterialRecipe.Provider UNIT_POWERED_TOOL_BUILDER = MaterialRecipe.registerProvider("powered-tool-from-unit", GT5RRef.ID, id -> new MaterialRecipe.ItemBuilder() {

        @Override
        public ItemStack build(CraftingContainer inv, MaterialRecipe.Result mats) {
            Tuple<Long, Tuple<Long, Material>> t = (Tuple<Long, Tuple<Long, Material>>) mats.mats.get("secondary");
            IAntimatterTool type = AntimatterAPI.get(IAntimatterTool.class, id.replace('-', '_'), GTCore.ID);
            t.getB().getB();
            return type.resolveStack((Material) mats.mats.get("primary"), t.getB().getB(), t.getA(), t.getB().getA());
        }

        @Override
        public Map<String, Object> getFromResult(@NotNull ItemStack stack) {
            return ImmutableMap.of();
        }
    });
    static {
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_SMALL.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_MEDIUM.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.BATTERIES_LARGE.location(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreItems.BatteryMediumLithium.getLoc(), ToolTypes::getEnergy);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_LV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_MV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_HV.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_SMALL.location(), ToolTypes::getEnergyAndMat);
        PropertyIngredient.addGetter(GTCoreTags.POWER_UNIT_JACKHAMMER.location(), ToolTypes::getEnergyAndMat);
    }

    public static void init(){
        if (AntimatterAPI.getSIDE().isClient()){
            BiFunction<Direction, BlockEntity, Boolean> REACTOR_FUNCTION = (dir, tile) -> {
                if (tile instanceof BlockEntitySecondaryOutput machine) {
                    Direction direction = machine.getSecondaryOutputFacing();
                    return direction != null && direction == dir;
                }
                return false;
            };
            BehaviourExtendedHighlight.EXTRA_PIPE_FUNCTIONS.add(REACTOR_FUNCTION);
            GTCoreTools.ELECTRIC_WRENCH_ALT.addBehaviour(new BehaviourExtendedHighlight(b -> b instanceof BlockMachine || (b instanceof BlockPipe && b.builtInRegistryHolder().is(AntimatterDefaultTools.WRENCH.getToolType())) || b.defaultBlockState().hasProperty(BlockStateProperties.FACING_HOPPER) || b.defaultBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING), BehaviourExtendedHighlight.PIPE_FUNCTION));
        }

    }

    public static Tuple<Long, Long> getEnergy(ItemStack stack){
        if (stack.getItem() instanceof ItemBattery battery){
            long energy = TesseractCapUtils.INSTANCE.getEnergyHandlerItem(stack).map(IGTNode::getEnergy).orElse((long)0);
            long maxEnergy = TesseractCapUtils.INSTANCE.getEnergyHandlerItem(stack).map(IGTNode::getCapacity).orElse(battery.getCapacity());
            return new Tuple<>(energy, maxEnergy);
        }
        if (stack.getItem() instanceof ItemPortableScanner){
            long energy = TesseractCapUtils.INSTANCE.getEnergyHandlerItem(stack).map(IGTNode::getEnergy).orElse((long)0);
            long maxEnergy = TesseractCapUtils.INSTANCE.getEnergyHandlerItem(stack).map(IGTNode::getCapacity).orElse(400000L);
            return new Tuple<>(energy, maxEnergy);
        }
        if (stack.getItem() instanceof IAntimatterTool tool){
            if (tool.getAntimatterToolType().isPowered()){
                long currentEnergy = tool.getCurrentEnergy(stack);
                long maxEnergy = tool.getMaxEnergy(stack);
                return new Tuple<>(currentEnergy, maxEnergy);
            }
        }
        return null;
    }

    public static Tuple<Long, Tuple<Long, Material>> getEnergyAndMat(ItemStack stack){
        if (stack.getItem() instanceof ItemPowerUnit tool){
            long currentEnergy = tool.getCurrentEnergy(stack);
            long maxEnergy = tool.getMaxEnergy(stack);
            return new Tuple<>(currentEnergy, new Tuple<>(maxEnergy, tool.getMaterial(stack)));
        }
        return null;
    }
}
