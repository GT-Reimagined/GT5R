package org.gtreimagined.gt5r.data;

import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.integration.jeirei.renderer.IRecipeInfoRenderer;
import muramasa.antimatter.integration.jeirei.renderer.InfoRenderers;
import muramasa.antimatter.machine.BlockMachine;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.recipe.IRecipe;
import muramasa.antimatter.recipe.RecipeProxies;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.recipe.map.Proxy;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.antimatter.recipe.map.RecipeMap;
import muramasa.antimatter.recipe.map.SubCategory;
import net.minecraft.client.gui.Font;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.gtreimagined.gt5r.GT5RRef;
import org.gtreimagined.gt5r.machine.recipe.FusionRecipe;
import org.gtreimagined.gt5r.machine.recipe.FusionRecipeBuilder;
import org.gtreimagined.gt5r.machine.recipe.FusionRecipeSerializer;
import org.gtreimagined.gtcore.data.RecipeBuilders;
import org.gtreimagined.gtcore.data.RecipeBuilders.SteamBuilder;
import tesseract.TesseractGraphWrappers;

import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

@SuppressWarnings("unchecked")
public class RecipeMaps {

    public static BiFunction<Integer, Integer, Proxy> DISSASSEMBLER_PROXY = (power, duration) -> new Proxy(RecipeType.CRAFTING, getDefaultCrafting(power, duration));
    public static RecipeMap<RecipeBuilder> STEAM_ALLOY_SMELTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_alloy_smelter", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_COMPRESSOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_compressor", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_EXTRACTOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_extractor", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> HP_STEAM_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "hp_steam_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> STEAM_FORGE_HAMMER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_forge_hammer", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_MACERATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_macerator", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_SIFTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_sifter", new RecipeBuilder()).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_furnace", new RecipeBuilder())
                    .setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 160)).setGuiTier(Tier.BRONZE));
    public static RecipeMap<RecipeBuilder> STEAM_OVEN = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "steam_oven", new RecipeBuilder())
                    .setProxy(RecipeProxies.SMOKING_PROXY.apply(8, 80)).setGuiTier(Tier.BRONZE));

    public static RecipeMap<RecipeBuilder> ALLOY_SMELTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "alloy_smelter", new SteamBuilder(STEAM_ALLOY_SMELTER)));
    public static RecipeMap<RecipeBuilder> AMP_FABRICATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "amp_fabricator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ARC_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "arc_furnace", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ASSEMBLER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "assembler", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> AUTOCLAVE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "autoclave", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PRIMITIVE_BLAST_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "primitive_blast_furnace", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> BENDER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "bender", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CANNER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "canner", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CENTRIFUGE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "centrifuge", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> BATH = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "bath", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CHEMICAL_REACTOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "chemical_reactor", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CIRCUIT_ASSEMBLER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "circuit_assembler", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SOLID_FUEL_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "solid_fuel_boilers", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COKE_OVEN = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "coke_oven", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COMBUSTION_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "combustion_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> COMPRESSOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "compressor", new SteamBuilder(STEAM_COMPRESSOR)));
    public static RecipeMap<RecipeBuilder> CRYSTALLIZATION_CHAMBER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "crystallization_chamber", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CUTTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "cutter", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> CRACKING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "cracking", new RecipeBuilder())).setGuiData(Guis.MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> ASSEMBLY_LINE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "assembly_line", new RecipeBuilder())).setGuiData(Guis.MULTI_DISPLAY_FLUID);
    public static RecipeMap<RecipeBuilder> DEHYDRATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "dehydrator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> DISASSEMBLER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "disassembler", new RecipeBuilder())).setProxy(DISSASSEMBLER_PROXY.apply(8, 200));
    public static RecipeMap<RecipeBuilder> DISTILLATION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "distillation", new RecipeBuilder())).setGuiData(Guis.MULTI_DISPLAY_FLUID);
    public static RecipeMap<RecipeBuilder> CRYO_DISTILLATION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "cryo_distillation", new RecipeBuilder())).setGuiData(Guis.MULTI_DISPLAY_FLUID);
    public static RecipeMap<RecipeBuilder> DISTILLERY = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "distillery", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilders.BlastingBuilder> E_BLAST_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "electric_blast_furnace", new RecipeBuilders.BlastingBuilder())).setGuiData(Guis.MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> ELECTRIC_FURNACE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "electric_furnace", new RecipeBuilder())
                    .setProxy(RecipeProxies.FURNACE_PROXY.apply(8, 60)));
    public static RecipeMap<RecipeBuilder> ELECTRIC_OVEN = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "electric_oven", new RecipeBuilder())
                    .setProxy(RecipeProxies.SMOKING_PROXY.apply(8, 30)));
    public static RecipeMap<RecipeBuilder> ELECTROLYZER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "electrolyzer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ELECTROMAGNETIC_SEPARATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "electromagnetic_separator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> EXTRACTOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "extractor", new SteamBuilder(STEAM_EXTRACTOR)));
    public static RecipeMap<RecipeBuilder> EXTRUDER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "extruder", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FERMENTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "fermenter", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_CANNER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "fluid_canner", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_PRESS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "fluid_press", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_HEATER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "fluid_heater", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FLUID_SOLIDIFYER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "fluid_solidifyer", new RecipeBuilder()));
    public static RecipeMap<FusionRecipeBuilder> FUSION = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "fusion", new FusionRecipeBuilder())).setRecipeSerializer(FusionRecipeSerializer.INSTANCE).setGuiData(Guis.MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> GAS_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "gas_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> FORGE_HAMMER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "forge_hammer", new SteamBuilder(STEAM_FORGE_HAMMER)));
    public static RecipeMap<RecipeBuilder> HEAT_EXCHANGER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "heat_exchanger", new RecipeBuilder())).setGuiData(Guis.MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> IMPLOSION_COMPRESSOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "implosion_compressor", new RecipeBuilder())).setGuiData(Guis.MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> LARGE_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "large_boilers", new RecipeBuilder())).setGuiData(Guis.MULTI_DISPLAY);
    public static RecipeMap<RecipeBuilder> LASER_ENGRAVER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "laser_engraver", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> LATHE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "lathe", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> MACERATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "macerator", new SteamBuilder(STEAM_MACERATOR)));
    public static RecipeMap<RecipeBuilder> MAGIC_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "magic_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> MASS_FABRICATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "mass_fabricator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> MIXER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "mixer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ORE_BYPRODUCTS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "ore_byproducts", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ORE_WASHER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "ore_washer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PACKAGER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "packager", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> POLARIZER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "polarizer", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PULVERIZER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "pulverizer", new PulverizerBuilder()).setGuiTier(Tier.HV));
    public static RecipeMap<RecipeBuilder> FORMING_PRESS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "forming_press", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PRINTING = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "printing", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> PYROLYSIS_OVEN = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "pyrolysis_oven", new RecipeBuilder())).setGuiData(Guis.MULTI_DISPLAY);

    public static RecipeMap<RecipeBuilder> ROASTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "roaster", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> RECYCLER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "recycler", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> REPLICATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "replicating", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> ROCK_BREAKER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "rock_breaker", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SCANNER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "scanning", new RecipeBuilder()));

    public static RecipeMap<RecipeBuilder> SEMI_FUELS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "semi_fuels", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SIFTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "sifter", new SteamBuilder(STEAM_SIFTER)));
    public static RecipeMap<RecipeBuilder> SMELTER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "smelter", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> SMALL_BOILERS = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "small_boilers", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> THERMAL_CENTRIFUGE = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "thermal_centrifuge", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> TREE_GROWTH_SIMULATOR = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "tree_growth_simulator", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> UNPACKAGER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "unpackager", new RecipeBuilder()));
    public static RecipeMap<RecipeBuilder> VACUUM_FREEZER = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "vacuum_freezer", new RecipeBuilder()).setGuiData(Guis.MULTI_DISPLAY));
    public static RecipeMap<RecipeBuilder> WIRE_MILL = AntimatterAPI.register(RecipeMap.class,
            new RecipeMap<>(GT5RRef.ID, "wire_mill", new RecipeBuilder()));

    private static BiFunction<Recipe<?>, RecipeBuilder, IRecipe> getDefaultCrafting(int power, int duration) {
        return (t, b) -> {
            if (!(t instanceof ShapedRecipe shapedRecipe)) return null;
            List<Ingredient> ingredients = t.getIngredients();
            if (!(t.getResultItem().getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BlockMachine machine)) return null;
            if (machine.getType() == GT5RMachines.HULL) return null;
            List<ItemStack> list = new ObjectArrayList<>();
            for (Ingredient i : ingredients){
                for (ItemStack stack : i.getItems()){
                    if (!stack.isEmpty() && !stack.isDamageableItem()){
                        list.add(stack);
                        break;
                    }
                }
            }
            ItemStack craftingOut = shapedRecipe.getResultItem();
            if (list.isEmpty()) return null;
            RecipeIngredient ing = RecipeIngredient.of(craftingOut);
            IRecipe recipe = b.recipeMapOnly().ii(ing)
                    .io(list.toArray(new ItemStack[0])).hide().add(t.getId().getPath(), duration, power, 0, 1);
            recipe.setMapId(b.getMap().getId());
            return recipe;
        };
    }

    public static final IRecipeInfoRenderer LARGE_BOILER_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            String additional = recipe.getDuration() < 1200 ? "" : recipe.getDuration() < 36000 ? " (" + (recipe.getDuration() / 20.0f) + " secs)" : " (" + (recipe.getDuration() / 1200.0f) + " mins)";
            String duration = "Duration: " + recipe.getDuration() + " ticks" + additional;
            String extraBurntime = "Extra saved Burntime: " + recipe.getPower();
            String heatIncreaseMultiplier = "Heat increase multiplier: " + (Math.max(recipe.getSpecialValue(), 1));
            renderString(stack, duration, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, extraBurntime, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, heatIncreaseMultiplier, fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
        }

        @Override
        public int getRows() {
            return 3;
        }
    };

    public static final IRecipeInfoRenderer HEAT_EXCHANGER_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            String additional = recipe.getDuration() < 1200 ? "" : recipe.getDuration() < 36000 ? " (" + (recipe.getDuration() / 20.0f) + " secs)" : " (" + (recipe.getDuration() / 1200.0f) + " mins)";
            String duration = "Duration: " + recipe.getDuration() + " ticks" + additional;
            String extraBurntime = "HU/t: " + recipe.getPower();
            String heatIncreaseMultiplier = "Total HU: " + (recipe.getPower() * recipe.getDuration());
            String heatExchanger = recipe.getSpecialValue() == 1 ? "" : (recipe.getSpecialValue() == -1 ? "Small" : "Large") + " Heat Exchanger only";
            renderString(stack, duration, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, extraBurntime, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, heatIncreaseMultiplier, fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
            renderString(stack, heatExchanger, fontRenderer, 5, 30, guiOffsetX, guiOffsetY);
        }

        @Override
        public int getRows() {
            return 4;
        }
    };

    public static final IRecipeInfoRenderer FUSION_RENDERER = new IRecipeInfoRenderer() {
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            if (!(recipe instanceof FusionRecipe fusionRecipe)) return;
            if (recipe.getDuration() == 0) return;
            String additional = recipe.getDuration() < 1200 ? "" : recipe.getDuration() < 36000 ? " (" + (recipe.getDuration() / 20.0f) + " secs)" : " (" + (recipe.getDuration() / 1200.0f) + " mins)";
            String power = "Duration: " + recipe.getDuration() + " ticks" + additional;
            String euT = "EU/t: " + recipe.getPower();
            String huT = "Creates " + fusionRecipe.getHuOutput() + " HU/t";
            String total = "Total: " + recipe.getPower() * recipe.getDuration() + " EU";
            String start = "Start: " + recipe.getSpecialValue() + " EU";
            Tier tier = Tier.getTier(recipe.getPower() / recipe.getAmps());
            Tier outputTier = Tier.getTier(fusionRecipe.getHuOutput());
            String formattedText = " (" + tier.getId().toUpperCase() + ")";
            String formattedText1 = " (" + outputTier.getId().toUpperCase() + ")";
            renderString(stack, power, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, euT, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, formattedText, fontRenderer, 5 + stringWidth(euT, fontRenderer), 10, Tier.EV.getRarityFormatting().getColor(), guiOffsetX, guiOffsetY);
            renderString(stack, huT, fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
            renderString(stack, formattedText1, fontRenderer, 5 + stringWidth(huT, fontRenderer), 20, Tier.ULV.getRarityFormatting().getColor(), guiOffsetX, guiOffsetY);
            renderString(stack, total, fontRenderer, 5, 30, guiOffsetX, guiOffsetY);
            renderString(stack, start, fontRenderer, 5, 40, guiOffsetX, guiOffsetY);
        }

        @Override
        public int getRows() {
            return 5;
        }
    };

    public static final IRecipeInfoRenderer CHEM_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            if (recipe.getDuration() == 0 && recipe.getPower() == 0) return;
            String additional = recipe.getDuration() < 1200 ? "" : recipe.getDuration() < 36000 ? " (" + (recipe.getDuration() / 20.0f) + " secs)" : " (" + (recipe.getDuration() / 1200.0f) + " mins)";
            String power = "Duration: " + recipe.getDuration() + " ticks" + additional;
            String euT = "EU/t: " + recipe.getPower();
            String total = "Total: " + recipe.getPower() * recipe.getDuration() + " EU";
            String complicated = recipe.getSpecialValue() == -1 ? "Complicated Recipe" : null;
            boolean toLarge = false;
            for (FluidIngredient outputFluid : recipe.getInputFluids()) {
                if (outputFluid.getAmount() > 32000 * TesseractGraphWrappers.dropletMultiplier){
                    toLarge = true;
                    break;
                }
            }
            Tier tier = Tier.getTier((recipe.getPower() / recipe.getAmps()));
            String formattedText = " (" + tier.getId().toUpperCase() + ")";
            renderString(stack, power, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, euT, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, formattedText, fontRenderer, 5 + stringWidth(euT, fontRenderer), 10, Tier.EV.getRarityFormatting().getColor(), guiOffsetX, guiOffsetY);
            renderString(stack, total, fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
            if (complicated != null){
                renderString(stack, complicated, fontRenderer, 5, 30, guiOffsetX, guiOffsetY);
            }
            if (toLarge){
                renderString(stack, "Large chem reactor only", fontRenderer, 5, complicated != null ? 40 : 30, guiOffsetX, guiOffsetY);
            }
        }

        @Override
        public int getRows() {
            return 5;
        }
    };

    public static final IRecipeInfoRenderer MACERATOR_RENDERER = new IRecipeInfoRenderer() {
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            if (recipe.getDuration() == 0 && recipe.getPower() == 0) return;
            String additional = recipe.getDuration() < 1200 ? "" : recipe.getDuration() < 36000 ? " (" + (recipe.getDuration() / 20.0f) + " secs)" : " (" + (recipe.getDuration() / 1200.0f) + " mins)";
            String power = "Duration: " + recipe.getDuration() + " ticks" + additional;
            String euT = "EU/t: " + recipe.getPower();
            String amps = "Amps: " + recipe.getAmps();
            String total = "Total: " + recipe.getPower() * recipe.getDuration() + " EU";
            Tier tier = Tier.getTier((recipe.getPower() / recipe.getAmps()));
            String formattedText = " (" + tier.getId().toUpperCase() + ")";
            renderString(stack, power, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, euT, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, formattedText, fontRenderer, 5 + stringWidth(euT, fontRenderer), 10, Tier.EV.getRarityFormatting().getColor(), guiOffsetX, guiOffsetY);
            renderString(stack, amps, fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
            renderString(stack, total, fontRenderer, 5, 30, guiOffsetX, guiOffsetY);
            renderString(stack, "Secondary outputs only available in pulverizers", fontRenderer, 5, 40, guiOffsetX, guiOffsetY);
        }

        @Override
        public int getRows() {
            return 5;
        }
    };

    public static final IRecipeInfoRenderer ALLOY_SMELTER_RENDERER = new IRecipeInfoRenderer() {
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            if (recipe.getDuration() == 0 && recipe.getPower() == 0) return;
            String additional = recipe.getDuration() < 1200 ? "" : recipe.getDuration() < 36000 ? " (" + (recipe.getDuration() / 20.0f) + " secs)" : " (" + (recipe.getDuration() / 1200.0f) + " mins)";
            String power = "Duration: " + recipe.getDuration() + " ticks" + additional;
            String euT = "EU/t: " + recipe.getPower();
            String amps = "Amps: " + recipe.getAmps();
            String total = "Total: " + recipe.getPower() * recipe.getDuration() + " EU";
            Tier tier = Tier.getTier((recipe.getPower() / recipe.getAmps()));
            String formattedText = " (" + tier.getId().toUpperCase() + ")";
            renderString(stack, power, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            renderString(stack, euT, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            renderString(stack, formattedText, fontRenderer, 5 + stringWidth(euT, fontRenderer), 10, Tier.EV.getRarityFormatting().getColor(), guiOffsetX, guiOffsetY);
            renderString(stack, amps, fontRenderer, 5, 20, guiOffsetX, guiOffsetY);
            renderString(stack, total, fontRenderer, 5, 30, guiOffsetX, guiOffsetY);
            if (recipe.getInputItems().size() > 2){
                renderString(stack, "Multi Smelter only", fontRenderer, 5, 40, 0xFF0000, guiOffsetX, guiOffsetY);
            }

        }

        @Override
        public int getRows() {
            return 5;
        }
    };

    public static final IRecipeInfoRenderer MAGIC_FUEL_RENDERER = new IRecipeInfoRenderer() {
        @Override
        public void render(PoseStack stack, IRecipe recipe, Font fontRenderer, int guiOffsetX, int guiOffsetY) {
            if (recipe.hasInputFluids()){
                String fuelPerMb = "EU/L: " + ((double) recipe.getPower() / (double) Objects.requireNonNull(recipe.getInputFluids()).get(0).getAmount());
                String fuelPerB = "Fluid Amount / tick: " + Objects.requireNonNull(recipe.getInputFluids()).get(0).getAmount();
                renderString(stack, fuelPerMb, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
                renderString(stack, fuelPerB, fontRenderer, 5, 10, guiOffsetX, guiOffsetY);
            } else if (recipe.hasInputItems()){
                String fuelValue = "Fuel Value: " + recipe.getTotalPower() + " EU";
                renderString(stack, fuelValue, fontRenderer, 5, 0, guiOffsetX, guiOffsetY);
            }
        }

        @Override
        public int getRows() {
            return 2;
        }
    };

    static {
        PULVERIZER.addSubCategory("macerator_recycling", new SubCategory("jei.category.macerator_recycling", () -> new ResourceLocation(GT5RRef.ID, "textures/gui/icon/macerator_recycling.png"), r -> r.getTags().contains(GT5RRecipeTags.RECYCLING)));
        PULVERIZER.addSubCategory("macerator_ore_processing", new SubCategory("jei.category.macerator_ore_processing", () -> GT5RMachines.MACERATOR.getItem(Tier.LV), r -> r.getTags().contains(GT5RRecipeTags.MACERATOR_ORE_PROCESING)));
        ARC_FURNACE.addSubCategory("arc_furnace_recycling", new SubCategory("jei.category.arc_furnace_recycling", () -> new ResourceLocation(GT5RRef.ID, "textures/gui/icon/arc_furnace_recycling.png"), r -> r.getTags().contains(GT5RRecipeTags.RECYCLING)));
        ALLOY_SMELTER.addSubCategory("alloy_smelter_molds", new SubCategory("jei.category.alloy_smelter_molds", () -> new ResourceLocation(GT5RRef.ID, "textures/gui/icon/alloy_smelter_molding.png"), r -> r.getTags().contains(GT5RRecipeTags.ALLOY_SMELTER_MOLDING)));
        SOLID_FUEL_BOILERS.setGuiData(Guis.SIMPLE_DISPLAY);
        COMBUSTION_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        GAS_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        SEMI_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        MAGIC_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        ORE_BYPRODUCTS.setGuiData(Guis.ORE_BYPRODUCTS);
        STEAM_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        HP_STEAM_FUELS.setGuiData(Guis.MULTI_DISPLAY);
        TREE_GROWTH_SIMULATOR.setGuiData(Guis.MULTI_DISPLAY);
        DISTILLATION.setGuiData(Guis.MULTI_DISPLAY_DISTILLATION);
        CRYO_DISTILLATION.setGuiData(Guis.MULTI_DISPLAY_DISTILLATION);
        ALLOY_SMELTER.setGuiData(Guis.ALLOY_SMELTER_DISPLAY);
    }

    public static void clientMaps() {

        E_BLAST_FURNACE.setInfoRenderer(InfoRenderers.BLASTING_RENDERER);
        PRIMITIVE_BLAST_FURNACE.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        COKE_OVEN.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        SOLID_FUEL_BOILERS.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        ALLOY_SMELTER.setInfoRenderer(ALLOY_SMELTER_RENDERER);
        BATH.setInfoRenderer(InfoRenderers.BASIC_RENDERER);
        COMBUSTION_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        GAS_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        SEMI_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        MAGIC_FUELS.setInfoRenderer(MAGIC_FUEL_RENDERER);
        ORE_BYPRODUCTS.setInfoRenderer(InfoRenderers.EMPTY_RENDERER);
        STEAM_ALLOY_SMELTER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_COMPRESSOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_EXTRACTOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        HP_STEAM_FUELS.setInfoRenderer(InfoRenderers.FUEL_RENDERER);
        STEAM_FORGE_HAMMER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_MACERATOR.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_FURNACE.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        STEAM_SIFTER.setInfoRenderer(InfoRenderers.STEAM_RENDERER);
        LARGE_BOILERS.setInfoRenderer(LARGE_BOILER_RENDERER);
        HEAT_EXCHANGER.setInfoRenderer(HEAT_EXCHANGER_RENDERER);
        FUSION.setInfoRenderer(FUSION_RENDERER);
        CHEMICAL_REACTOR.setInfoRenderer(CHEM_RENDERER);
        PULVERIZER.setInfoRenderer(MACERATOR_RENDERER);
    }

    public static class PulverizerBuilder extends RecipeBuilder{
        @Override
        public IRecipe add(String domain, String id) {
            IRecipe recipe = super.add(domain, id);
            var  recipeBuilder = MACERATOR.RB().hide().ii(recipe.getInputItems());
            if (recipe.hasOutputItems() && recipe.getOutputItems().length > 0) {
                recipeBuilder.io(recipe.getOutputItems(false)[0]);
            }
            if (recipe.hasOutputChances() && recipe.getOutputChances().length > 0) {
                recipeBuilder.outputChances(recipe.getOutputChances()[0]);
            }
            recipeBuilder.inputChances(recipe.getInputChances()).add(domain, id, recipe.getDuration(), recipe.getPower(), recipe.getSpecialValue(), recipe.getAmps());
            return recipe;
        }
    }
}
