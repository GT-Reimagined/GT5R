package org.gtreimagined.gt5r.integration.rei;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.util.Utils;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.data.GT5RMachines;
import org.gtreimagined.gt5r.data.GT5RMaterialTags;
import org.gtreimagined.gt5r.data.Materials;

import java.util.ArrayList;
import java.util.List;

import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.integration.rei.REIUtils.toREIFLuidStack;
import static muramasa.antimatter.material.MaterialTags.*;

public class OreProcessingCategory implements DisplayCategory<OreProcessingDisplay> {
    protected static Renderer icon = EntryStacks.of(Items.IRON_ORE);
    private static final Component title = Utils.translatable(GT5RRef.ID + ".rei.tooltip.ore.byproducts");
    static CategoryIdentifier<? extends OreProcessingDisplay> id = CategoryIdentifier.of(GT5RRef.ID, "ore_byproducts");

    @Override
    public CategoryIdentifier<? extends OreProcessingDisplay> getCategoryIdentifier() {
        return id;
    }

    @Override
    public List<Widget> setupDisplay(OreProcessingDisplay display, Rectangle bounds) {
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createDrawableWidget((helper, matrices, mouseX, mouseY, delta) -> {
            drawTexture(matrices, new ResourceLocation(GT5RRef.ID, "textures/gui/ore_byproducts/background.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            drawTexture(matrices, new ResourceLocation(GT5RRef.ID, "textures/gui/ore_byproducts/base.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            if (display.bathingMode != OreProcessingDisplay.BathingMode.NONE){
                drawTexture(matrices, new ResourceLocation(GT5RRef.ID, "textures/gui/ore_byproducts/chem.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (display.ore.has(AntimatterMaterialTypes.GEM)){
                drawTexture(matrices, new ResourceLocation(GT5RRef.ID, "textures/gui/ore_byproducts/sift.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (display.sepMode != OreProcessingDisplay.SepMode.NONE){
                drawTexture(matrices, new ResourceLocation(GT5RRef.ID, "textures/gui/ore_byproducts/sep.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (!display.ore.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE)){
                drawTexture(matrices, new ResourceLocation(GT5RRef.ID, "textures/gui/ore_byproducts/smelt1.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (display.ore.has(DUST) && display.ore.has(INGOT)){
                drawTexture(matrices, new ResourceLocation(GT5RRef.ID, "textures/gui/ore_byproducts/smelt2.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
            if (display.ore.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE) && display.ore.has(INGOT_HOT)){
                drawTexture(matrices, new ResourceLocation(GT5RRef.ID, "textures/gui/ore_byproducts/vac.png"), bounds.x, bounds.y, 0, 0, bounds.getWidth(), bounds.getHeight());
            }
        }));
        widgets.addAll(setupSlots(display, bounds));
        return widgets;
    }

    private List<Widget> setupSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(4, 4, bounds)).entries(EntryIngredients.ofIngredient(ORE.getMaterialIngredient(display.ore, 1))).markInput().disableBackground());
        widgets.addAll(setupBaseMachineSlots(display, bounds));
        if (!display.ore.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE)){
            widgets.addAll(setupPrimaryFurnaceSlot(display, bounds));
        }
        if (display.ore.has(INGOT)){
            widgets.addAll(setupSecondaryFurnaceSlots(display, bounds));
        }
        if (display.bathingMode != OreProcessingDisplay.BathingMode.NONE){
            widgets.addAll(setupChemMachineSlots(display, bounds));
        }
        if (display.ore.has(AntimatterMaterialTypes.GEM)){
            widgets.addAll(setupSiftMachineSlots(display, bounds));
        }
        if (display.sepMode != OreProcessingDisplay.SepMode.NONE){
            widgets.addAll(setupSepMachineSlots(display, bounds));
        }
        if (display.ore.has(INGOT_HOT)){
            widgets.addAll(setupVacMachineSlots(display, bounds));
        }
        return widgets;
    }

    private List<Widget> setupPrimaryFurnaceSlot(OreProcessingDisplay display, Rectangle bounds){
        Item ingot, gem, dust;
        if (!SMELT_INTO.getMapping(display.ore).has(INGOT) && !SMELT_INTO.getMapping(display.ore).has(GEM) && !SMELT_INTO.getMapping(display.ore).has(DUST)) return List.of();
        if (display.ore.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE)) return List.of();
        if(SMELT_INTO.getMapping(display.ore).has(INGOT) && !SMELT_INTO.getMapping(display.ore).has(GEM)){
            ingot = INGOT.get(SMELT_INTO.getMapping(display.ore));
            return List.of(Widgets.createSlot(xy(50, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(ingot, SMELTING_MULTI.getInt(display.ore))))).markOutput().disableBackground());
        }else if(!SMELT_INTO.getMapping(display.ore).has(INGOT) && SMELT_INTO.getMapping(display.ore).has(GEM)){
            gem = GEM.get(SMELT_INTO.getMapping(display.ore));
            return List.of(Widgets.createSlot(xy(50, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(gem, SMELTING_MULTI.getInt(display.ore))))).markOutput().disableBackground());
        }else if(!SMELT_INTO.getMapping(display.ore).has(INGOT) && !SMELT_INTO.getMapping(display.ore).has(GEM) && SMELT_INTO.getMapping(display.ore).has(DUST)){
            dust = DUST.get(SMELT_INTO.getMapping(display.ore));
            return List.of(Widgets.createSlot(xy(50, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(dust, SMELTING_MULTI.getInt(display.ore))))).markOutput().disableBackground());
        }
        return List.of();
    }

    private List<Widget> setupSecondaryFurnaceSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        if (display.ore.has(GT5RMaterialTags.NEEDS_BLAST_FURNACE)){
            widgets.add(Widgets.createSlot(xy(122, 111, bounds)).entries(ofMachine(GT5RMachines.BLAST_FURNACE)).markOutput().disableBackground());
            widgets.add(Widgets.createSlot(xy(72, 146, bounds)).entries(ofMachine(GT5RMachines.BLAST_FURNACE)).markOutput().disableBackground());
            if(display.ore.has(INGOT_HOT)){
                widgets.add(Widgets.createSlot(xy(97, 146, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(INGOT_HOT.get(display.ore),1)))).markOutput().disableBackground());
            }else{
                widgets.add(Widgets.createSlot(xy(97, 146, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(INGOT.get(display.ore),1)))).markOutput().disableBackground());
            }
        }else{
            widgets.add(Widgets.createSlot(xy(122, 111, bounds)).entries(
                            EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.FURNACE)),
                                    EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.BLAST_FURNACE))))
                    .markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(72, 146, bounds)).entries(
                            EntryIngredient.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.FURNACE)),
                                    EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(Items.BLAST_FURNACE))))
                    .markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(97, 146, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(INGOT.get(display.ore),1)))).markOutput().disableBackground());
        }
        return widgets;
    }

    private List<Widget> setupBaseMachineSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(4, 26, bounds)).entries(ofMachine(GT5RMachines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(4, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, CRUSHED.get(display.ore, 2 * ORE_MULTI.getInt(display.ore))))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(4, 66, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.byProduct1),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 72, bounds)).entries(ofMachine(GT5RMachines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 93, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST_IMPURE.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 111, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.byProduct1),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(29, 26, bounds)).entries(ofMachine(GT5RMachines.ORE_WASHER)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 26, bounds)).entries(ofFluid(AntimatterMaterials.Water, 1000)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(72, 26, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, CRUSHED_PURIFIED.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(90, 26, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST_TINY.get(display.byProduct1),3)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(120, 48, bounds)).entries(ofMachine(GT5RMachines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST_PURE.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(166, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.byProduct2),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(97, 72, bounds)).entries(ofMachine(GT5RMachines.THERMAL_CENTRIFUGE)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(97, 93, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, CRUSHED_REFINED.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(97, 111, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST_TINY.get(display.byProduct2),3)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 72, bounds)).entries(ofMachine(GT5RMachines.CENTRIFUGE)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 93, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 111, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST_TINY.get(display.byProduct2),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 81, bounds)).entries(ofMachine(GT5RMachines.CENTRIFUGE)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 102, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(50, 120, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST_TINY.get(display.byProduct1),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(72, 81, bounds)).entries(ofMachine(GT5RMachines.MACERATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(72, 102, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST.get(display.ore, 1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(72, 120, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.byProduct3),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(4, 128, bounds)).entries(ofMachine(GT5RMachines.ORE_WASHER, Items.CAULDRON)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(25, 146, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, DUST.get(display.ore, 1)))).markOutput().disableBackground());
        return widgets;
    }

    private List<Widget> setupChemMachineSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(29, 48, bounds)).entries(ofMachine(GT5RMachines.BATH)).markInput().disableBackground());
        if(display.bathingMode == OreProcessingDisplay.BathingMode.MERCURY){
            widgets.add(Widgets.createSlot(xy(50, 48, bounds)).entries(ofFluid(Materials.Mercury,1000)).markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(90, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(GT5RMaterialTags.BATH_MERCURY.getMapping(display.ore)),1)))).markOutput().disableBackground());
        }else{
            widgets.add(Widgets.createSlot(xy(50, 48, bounds)).entries(ofFluid(Materials.SodiumPersulfateSolution,1000)).markInput().disableBackground());
            widgets.add(Widgets.createSlot(xy(90, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(GT5RMaterialTags.BATH_PERSULFATE.getMapping(display.ore)),1)))).markOutput().disableBackground());
        }
        widgets.add(Widgets.createSlot(xy(72, 48, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(CRUSHED_PURIFIED.get(display.ore),1)))).markOutput().disableBackground());
        return widgets;
    }

    private List<Widget> setupSiftMachineSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(110, 25, bounds)).entries(ofMachine(GT5RMachines.SIFTER)).markInput().disableBackground());
        boolean e = display.ore.has(GEM_EXQUISITE);
        widgets.add(Widgets.createSlot(xy(130, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(e ? GEM_EXQUISITE.get(display.ore) : GEM.get(display.ore),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(e ? GEM_FLAWLESS.get(display.ore) : GEM.get(display.ore),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(130, 22, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(e ? GEM_FLAWED.get(display.ore) : GEM.get(display.ore),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 22, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(e ? GEM_CHIPPED.get(display.ore) : GEM.get(display.ore),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(166, 4, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(GEM.get(display.ore),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(166, 22, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.ore),1)))).markOutput().disableBackground());
        return widgets;
    }

    private List<Widget> setupSepMachineSlots(OreProcessingDisplay display, Rectangle bounds){
        Item dust, nugget;
        List<Widget> widgets = new ArrayList<>();
        if(display.sepMode == OreProcessingDisplay.SepMode.IRON){
            dust = DUST_SMALL.get(AntimatterMaterials.Iron);
            nugget = NUGGET.get(AntimatterMaterials.Iron);
        } else if(display.sepMode == OreProcessingDisplay.SepMode.GOLD){
            dust = DUST_SMALL.get(AntimatterMaterials.Gold);
            nugget = NUGGET.get(AntimatterMaterials.Gold);
        }else{
            dust = DUST_SMALL.get(Materials.Neodymium);
            nugget = NUGGET.get(Materials.Neodymium);
        }
        widgets.add(Widgets.createSlot(xy(166, 72, bounds)).entries(ofMachine(GT5RMachines.ELECTROMAGNETIC_SEPARATOR)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(166, 93, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(DUST.get(display.ore),1)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(166, 111, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(dust,2)))).markOutput().disableBackground());
        widgets.add(Widgets.createSlot(xy(166, 129, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(nugget,1)))).markOutput().disableBackground());
        return widgets;
    }

    private List<Widget> setupVacMachineSlots(OreProcessingDisplay display, Rectangle bounds){
        List<Widget> widgets = new ArrayList<>();
        widgets.add(Widgets.createSlot(xy(123, 146, bounds)).entries(ofMachine(GT5RMachines.VACUUM_FREEZER)).markInput().disableBackground());
        widgets.add(Widgets.createSlot(xy(148, 146, bounds)).entries(List.of(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(INGOT.get(display.ore),1)))).markOutput().disableBackground());
        return widgets;
    }

    private Point xy(int x, int y, Rectangle bounds){
        int offsetX = 0, offsetY = 0;
        return new Point(offsetX + x + bounds.x, offsetY + y + bounds.y);
    }

    private EntryIngredient ofMachine(Machine<?> machine, Item... extra){
        List<EntryStack<?>> stacks = new ArrayList<>();
        machine.getTiers().forEach(t -> stacks.add(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(machine.getItem(t)))));
        for (Item item : extra) {
            stacks.add(EntryStack.of(VanillaEntryTypes.ITEM, new ItemStack(item)));
        }
        return EntryIngredient.of(stacks);
    }

    private EntryIngredient ofFluid(Material fluid, int amount){
        List<EntryStack<?>> stacks = new ArrayList<>();
        FluidHolder stack;
        if(fluid.has(LIQUID)){
           stack = fluid.getLiquid(amount);
        }else{
            stack = fluid.getGas(amount);
        }
        stacks.add(EntryStack.of(VanillaEntryTypes.FLUID, toREIFLuidStack(stack)));
        return EntryIngredient.of(stacks);
    }

    private static void drawTexture(PoseStack stack, ResourceLocation loc, int left, int top, int x, int y, int sizeX, int sizeY) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, loc);
        //AbstractGui.blit(stack, left, top, x, y, sizeX, sizeY);
        GuiComponent.blit(stack, left, top, 0, x, y, sizeX, sizeY, 186, 166);
    }

    @Override
    public Component getTitle() {
        return title;
    }

    @Override
    public Renderer getIcon() {
        return icon;
    }


    @Override
    public int getDisplayHeight() {
        return 166;
    }

    @Override
    public int getDisplayWidth(OreProcessingDisplay display) {
        return 186;
    }
}
