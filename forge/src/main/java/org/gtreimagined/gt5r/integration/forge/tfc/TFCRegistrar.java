package org.gtreimagined.gt5r.integration.forge.tfc;

import org.gtreimagined.gt5r.integration.forge.tfc.datagen.TFCBlockTagProvider;
import org.gtreimagined.gt5r.integration.forge.tfc.datagen.TFCItemTagProvider;
import org.gtreimagined.gt5r.integration.forge.tfc.datagen.TFCLangProvider;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.event.forge.AntimatterLoaderEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import muramasa.antimatter.fluid.AntimatterFluid;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTypeFluid;
import muramasa.antimatter.material.TextureSet;
import muramasa.antimatter.ore.StoneType;
import muramasa.antimatter.recipe.loader.IRecipeRegistrate;
import muramasa.antimatter.registration.IAntimatterRegistrar;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.tool.behaviour.BehaviourBlockTilling;
import muramasa.antimatter.tool.behaviour.BehaviourLogStripping;
import muramasa.antimatter.tool.behaviour.BehaviourVanillaShovel;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import org.gtreimagined.gt5r.GT5RRef;
import net.dries007.tfc.common.blocks.rock.Rock;
import net.dries007.tfc.common.blocks.soil.SandBlockType;
import net.dries007.tfc.common.blocks.soil.SoilBlockType;
import net.dries007.tfc.common.blocks.wood.Wood;
import net.dries007.tfc.common.fluids.TFCFluids;
import net.dries007.tfc.util.Helpers;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import tesseract.FluidPlatformUtils;

import java.util.function.BiConsumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static net.dries007.tfc.common.blocks.soil.SoilBlockType.GRASS_PATH;
import static org.gtreimagined.gt5r.data.Materials.*;

public class TFCRegistrar implements IAntimatterRegistrar {

    public static Material[] array;
    public TFCRegistrar(){
        onRegistrarInit();
    }
    @Override
    public String getId() {
        return Ref.MOD_TFC;
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_INIT){
            array = new Material[]{Bauxite, Cobaltite, Galena, Uraninite, VanadiumMagnetite, BrownLimonite, Hematite, Sheldonite, Sperrylite};
            /*for (Material material : array) {
                Helpers.mapOfKeys(Ore.Grade.class, (grade) -> {
                    new GTTFCOreItem(GT5RRef.ID, grade.name().toLowerCase() + "_" + material.getId());
                    return Helpers.mapOfKeys(Rock.class, (rock) -> {
                        new GTTFCOreBlock(GT5RRef.ID, material, rock, grade);
                        return true;
                    });
                });
            }*/
            Helpers.mapOfKeys(Rock.class, (rock) -> {
                Material material = Material.get(rock.name().toLowerCase());
                if (material == Material.NULL){
                    material = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, rock.name().toLowerCase(), rock.color().col, TextureSet.NONE));
                    material.flags(DUST);
                }
                AntimatterAPI.register(StoneType.class, new StoneType(GT5RRef.ID, "raw_" + rock.name().toLowerCase(), material, new Texture(Ref.MOD_TFC, "block/rock/raw/" + rock.name().toLowerCase()), SoundType.STONE, false).setStateSupplier(() -> rock.getBlock(Rock.BlockType.RAW).get().defaultBlockState()).setHardnessAndResistance(rock.category().hardness(6.5F), 10.0F).setHarvestLevel(1));
                AntimatterAPI.register(StoneType.class, new StoneType(GT5RRef.ID, rock.name().toLowerCase() + "_gravel", material, new Texture(Ref.MOD_TFC, "block/rock/gravel/" + rock.name().toLowerCase()), SoundType.GRAVEL, false).setSandLike(true).setHardnessAndResistance(rock.category().hardness(2.0F)).setStateSupplier(() -> rock.getBlock(Rock.BlockType.GRAVEL).get().defaultBlockState()).setHarvestLevel(1).setRequiresTool(true));
                return true;
            });
            Helpers.mapOfKeys(SandBlockType.class, (sand) -> {
                Material material = Material.get(sand.name().toLowerCase() + "_sand");
                if (material == Material.NULL){
                    material = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, sand.name().toLowerCase() + "_sand", sand.getDustColor(), TextureSet.NONE));
                    material.flags(DUST);
                }
                AntimatterAPI.register(StoneType.class, new StoneType(GT5RRef.ID, sand.name().toLowerCase() + "_sand", material, new Texture(Ref.MOD_TFC,"block/sand/" + sand.name().toLowerCase()), SoundType.SAND, false)).setSandLike(true).setRequiresTool(true).setFallingDustColor(sand.getDustColor()).setStateSupplier(() -> AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, "sand/" + sand.name().toLowerCase()).defaultBlockState());
                AntimatterAPI.register(StoneType.class, new StoneType(GT5RRef.ID, sand.name().toLowerCase() + "_raw_sandstone", material, new Texture(Ref.MOD_TFC, "block/sandstone/bottom/" + sand.name().toLowerCase()), SoundType.SAND, false).setStateSupplier(() -> AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, "raw_sandstone/" + sand.name().toLowerCase()).defaultBlockState()));
                return true;
            });
            AntimatterMaterialTypes.LIQUID.set((m, i) -> {
                if (m == null || !AntimatterMaterialTypes.LIQUID.allowGen(m)) return MaterialTypeFluid.getEmptyFluidAndLog(AntimatterMaterialTypes.LIQUID, m);
                if (m.getId().equals("water")) return FluidPlatformUtils.createFluidStack(Fluids.WATER, i);
                else if (m.getId().equals("lava")) return FluidPlatformUtils.createFluidStack(Fluids.LAVA, i);
                else if (m == SaltWater) return FluidPlatformUtils.createFluidStack(TFCFluids.SALT_WATER.getSource(), i);
                AntimatterFluid fluid = AntimatterAPI.get(AntimatterFluid.class, AntimatterMaterialTypes.LIQUID.getId() + "_" + m.getId());
                if (fluid == null) throw new IllegalStateException("Tried to get null fluid");
                return FluidPlatformUtils.createFluidStack(fluid.getFluid(), i);
            });
            // Make TFC logs strippable with AntiMatter tools
            Helpers.mapOfKeys(Wood.class, (wood) -> {
                var log = AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, "wood/log/" + wood.name().toLowerCase());
                var log_stripped = AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, "wood/stripped_log/" + wood.name().toLowerCase());
                BehaviourLogStripping.addStrippedBlock(log, log_stripped);
                return true;
            });
            // Make TFC dirt hoe-able with AntiMatter hoes
            Helpers.mapOfKeys(SoilBlockType.class, (soil) -> {
                switch (soil) {
                    case DIRT: case GRASS: case CLAY: case CLAY_GRASS:
                        for (SoilBlockType.Variant vary : SoilBlockType.Variant.values()) {
                            var dirt = AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, soil.name().toLowerCase()+ "/" + vary.name().toLowerCase());
                            var farmland = AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, "farmland/" + vary.name().toLowerCase());
                            BehaviourBlockTilling.addStrippedBlock(dirt, farmland);
                        } break;
                    case ROOTED_DIRT:
                        for (SoilBlockType.Variant vary : SoilBlockType.Variant.values()) {
                            var rootdirt = AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, soil.name().toLowerCase()+ "/" + vary.name().toLowerCase());
                            var dirt = AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, "dirt/" + vary.name().toLowerCase());
                            BehaviourBlockTilling.addStrippedBlock(rootdirt, dirt);
                        } break;
                    default: break;
                }
                return true;
            });
            // Make TFC dirt path-able with AntiMatter shovels
            Helpers.mapOfKeys(SoilBlockType.class, (path) -> {
                if (path == GRASS_PATH) {
                    for (SoilBlockType.Variant vary : SoilBlockType.Variant.values()) {
                        var grasstype = AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, "grass/" + vary.name().toLowerCase());
                        var pathtype = AntimatterPlatformUtils.INSTANCE.getBlockFromId(Ref.MOD_TFC, "grass_path/" + vary.name().toLowerCase());
                        BehaviourVanillaShovel.addStrippedBlock(grasstype, pathtype);
                    }
                }
                return true;
            });
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void onRegistrarInit() {
        AntimatterAPI.addRegistrar(this);
        if (isEnabled()) {
            FMLJavaModLoadingContext.get().getModEventBus().register(this);
            MinecraftForge.EVENT_BUS.addListener(this::registerRecipeLoaders);
            AntimatterDynamics.clientProvider(GT5RRef.ID, () -> new TFCLangProvider(GT5RRef.ID, "TFC en_us Lang", "en_us"));
        }
    }

    public void registerRecipeLoaders(AntimatterLoaderEvent event){
        BiConsumer<String, IRecipeRegistrate.IRecipeLoader> loader = (a, b) -> event.registrat.add(GT5RRef.ID, a, b);
        loader.accept("tfc_machine_recipes", MachineRecipes::init);
    }

    @SubscribeEvent
    public void onProviders(AntimatterProvidersEvent ev) {
        ev.event.addProvider(Ref.MOD_TFC, () -> new AntimatterFluidTagProvider(Ref.MOD_TFC, "TFC Fluid Tags", false){
            @Override
            protected void processTags(String domain) {
                super.processTags(domain);
                this.tag(TagUtils.getForgelikeFluidTag("salt_water")).add(TFCFluids.SALT_WATER.getSource());
            }
        });
        AntimatterBlockTagProvider[] blockTagProviders = new AntimatterBlockTagProvider[1];
        blockTagProviders[0] = new TFCBlockTagProvider( Ref.MOD_TFC, "TFC Block Tags", false);
        ev.event.addProvider(Ref.MOD_TFC, () -> new TFCItemTagProvider(Ref.MOD_TFC, "TFC Item Tags", false,  blockTagProviders[0]));
        ev.event.addProvider(Ref.MOD_TFC, () -> blockTagProviders[0]);

    }

    @Override
    public boolean isEnabled() {
        return AntimatterAPI.isModLoaded(Ref.MOD_TFC);
    }
}
