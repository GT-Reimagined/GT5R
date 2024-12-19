package org.gtreimagined.gt5r.data;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import org.gtreimagined.gt5r.material.GregTechMaterialEvent;
import muramasa.antimatter.Data;
import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.material.SubTag;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import org.gtreimagined.gtcore.data.GTCoreBlocks;
import org.gtreimagined.gtcore.data.GTCoreMaterials;

import java.util.List;

import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterMaterialTypes.*;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.antimatter.material.MaterialTags.*;
import static org.gtreimagined.gt5r.data.GT5RMaterialTags.ALLOY;

public class GT5RMaterialEvents {
    public static void onMaterialEvent(GregTechMaterialEvent event){
        flags(event);
        antimatterMaterials(event);
        byproducts(event);
        /**
         *** Periodic Table of Elements (No Isotopes)
         **/
        event.setMaterial(Materials.Hydrogen).asGas(20).fluidDensity(-1112);
        event.setMaterial(Materials.Helium).asGas().fluidDensity(-560);
        event.setMaterial(Materials.Lithium).asSolid(454, 0, BOLT, MOLTEN).asOre().harvestLevel(2);
        event.setMaterial(Materials.Beryllium).asSolid(1560).asOre();
        event.setMaterial(Materials.Boron).asDust(2349);
        event.setMaterial(Materials.Carbon).asDust(PLATE, MOLTEN).asFluid();
        event.setMaterial(Materials.Nitrogen).asGas().asFluid(0, 77);
        event.setMaterial(Materials.Oxygen).asGas();
        event.setMaterial(Materials.Fluorine).asGas();
        event.setMaterial(Materials.Neon).asGas().fluidDensity(-111);
        event.setMaterial(Materials.Sodium).asDust(370, MOLTEN).asFluid();
        event.setMaterial(Materials.Magnesium).asMetal(1383);
        event.setMaterial(Materials.Aluminium).asMetal(933, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, FOIL, HAS_CUSTOM_SMELTING);
        event.setMaterial(Materials.Silicon).asMetal(1687, PLATE, FOIL, GT5RMaterialTypes.BOULE);
        event.setMaterial(Materials.Phosphor).asDust(933);
        event.setMaterial(Materials.Sulfur).asDust(388, MOLTEN).asOre().harvestLevel(2).asFluid();
        event.setMaterial(Materials.Chlorine).asGas().fluidDensity(3);
        event.setMaterial(Materials.Argon).asGas();
        event.setMaterial(Materials.Potassium).asSolid(337, MOLTEN);
        event.setMaterial(Materials.Calcium).asDust(1115);
        //Scandium, not added
        event.setMaterial(Materials.Titanium).asMetal(1941, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, GEAR_SMALL, ROTOR, SPRING).forceBF(true);
        event.setMaterial(Materials.Vanadium).asMetal(2183, INGOT_HOT);
        event.setMaterial(Materials.Chromium).asMetal(2180, 1700, SCREW, BOLT, RING, PLATE).forceBF(false);
        event.setMaterial(Materials.Manganese).asMetal(1519, FOIL).asOre();
        //Iron, added by vanilla
        event.setMaterial(Materials.Cobalt).asMetal(1768, CRUSHED);
        event.setMaterial(Materials.Nickel).asMetal(1728, PLATE).asOre();
        //Copper, by vanilla
        event.setMaterial(Materials.Zinc).asMetal(692, PLATE, FOIL).asOre();
        event.setMaterial(Materials.Gallium).asMetal(303, MOLTEN);
        event.setMaterial(Materials.Germanium).asMetal(1211);
        event.setMaterial(Materials.Arsenic).asMetal(1090);
        //event.setMaterial(Selenium).asMetal(494, 0);
        //Bromine, not added
        event.setMaterial(Materials.Krypton).asGas().fluidDensity(3);
        //rubidium, strontium
        event.setMaterial(Materials.Yttrium).asMetal(1799);
        event.setMaterial(Materials.Zirconium).asMetal(2130).forceBF(true);
        event.setMaterial(Materials.Niobium).asMetal(2750, PLATE);
        event.setMaterial(Materials.Molybdenum).asMetal(2896).asOre();
        event.setMaterial(Materials.Technetium).asMetal(2430);
        //event.setMaterial(Ruthenium).asMetal(2607,2607);
        //event.setMaterial(Rhodium).asMetal(2237, 2237);
        event.setMaterial(Materials.Palladium).asMetal(1828).forceBF(true);
        event.setMaterial(Materials.Silver).asMetal(1234, PLATE, SCREW).asOre().harvestLevel(2);
        event.setMaterial(Materials.Cadmium).asDust(594);
        event.setMaterial(Materials.Indium).asSolid(430, MOLTEN);
        event.setMaterial(Materials.Tin).asMetal(505, PLATE, ROD_LONG, SCREW, BOLT, RING, FOIL, WIRE_FINE, FRAME, ROTOR).asOre().harvestLevel(1);
        event.setMaterial(Materials.Antimony).asMetal(1449);
        //Tellurium
        event.setMaterial(Materials.Iodine).asSolid(387);
        event.setMaterial(Materials.Xenon).asGas().fluidDensity(5);
        event.setMaterial(Materials.Caesium).asMetal(301);
        event.setMaterial(Materials.Barium).asDust(1000);
        //Lanthanum
        event.setMaterial(Materials.Cerium).asDust();
        //Praseodymium
        event.setMaterial(Materials.Neodymium).asMetal(1297, PLATE, ROD_LONG).asOre().harvestLevel(2); // TODO: Bastnasite or Monazite for Ore For;
        event.setMaterial(Materials.Promethium).asMetal(1353);
        //Samarium
        event.setMaterial(Materials.Europium).asMetal(1099, PLATE);
        event.setMaterial(Materials.Gadolinium).asMetal(1585);
        event.setMaterial(Materials.Terbium).asMetal(1696);
        //dysprosium, holmium, erbium, thulium, ytterbium, lutetium
        event.setMaterial(Materials.Hafnium).asMetal(2506);
        event.setMaterial(Materials.Tantalum).asSolid(3290);
        event.setMaterial(Materials.Tungsten).asMetal(3695, 3000, FOIL, RING, FRAME);
        //event.setMaterial(Rhenium).asMetal(3459,3459);
        event.setMaterial(Materials.Osmium).asOre().asMetal(3306, SCREW, BOLT, RING, PLATE, ROD_LONG);
        event.setMaterial(Materials.Iridium).asMetal(2719, FRAME, PLATE, GT5RMaterialTypes.CHAMBER).asOre();
        event.setMaterial(Materials.Platinum).asMetal(2041, 0, PLATE, FOIL, ROD_LONG, WIRE_FINE, FRAME).asOre();
        //Gold, added by vanilla
        event.setMaterial(Materials.Mercury).asFluid();
        // thalium
        event.setMaterial(Materials.Lead).asMetal(600, PLATE, PLATE_DENSE, ROD_LONG, FRAME, BOLT, RING).asOre().harvestLevel(1);
        event.setMaterial(Materials.Bismuth).asOre();
        //polonium
        //event.setMaterial(Astatine).asMetal(575, 0);
        event.setMaterial(Materials.Radon).asGas().fluidDensity(9);
        //event.setMaterial(Francium).asMetal(298, 0);
        //event.setMaterial(Radium).asMetal(973, 0);
        //event.setMaterial(Actinium).asMetal(1323, 0);
        event.setMaterial(Materials.Thorium).asMetal(2115, 0, PLATE_DENSE).asOre();
        //protactinium
        event.setMaterial(Materials.Uranium).asMetal(1405).harvestLevel(2);
        //event.setMaterial(Neptunium).asMetal(912, 0);
        event.setMaterial(Materials.Plutonium).asMetal(912);
        event.setMaterial(Materials.Americium).asMetal(1149, ROD_LONG);
        //Elements 96 - 118 not added
        /**
         ***  Solids
         **/
        /**
         **  Metals
         **/
        event.setMaterial(Materials.AnnealedCopper).asMetal(1357, PLATE, FOIL, ROD_LONG, WIRE_FINE, SCREW);
        event.setMaterial(Materials.BatteryAlloy).asMetal(295, PLATE, ALLOY);
        event.setMaterial(Materials.BismuthBronze).asMetal(1100, PLATE, ALLOY);
        event.setMaterial(Materials.BlackBronze).asMetal(2000, FRAME, ALLOY);
        event.setMaterial(Materials.BlackSteel).asMetal(1200, 1200, FRAME, PLATE).forceBF(false);
        event.setMaterial(Materials.BlueSteel).asMetal(1400, 1400, FRAME).forceBF(false);
        event.setMaterial(Materials.Brass).asMetal(1170, FRAME, ROD_LONG, PLATE, GEAR_SMALL, ALLOY);
        event.setMaterial(Materials.Bronze).asMetal(1125, GEAR, FRAME, ROTOR, ALLOY);
        event.setMaterial(Materials.CdInAGAlloy).asMetal(752, ROD, ALLOY);
        event.setMaterial(Materials.CobaltBrass).asMetal(1500, GEAR, ALLOY);
        event.setMaterial(Materials.Cupronickel).asMetal(1728, PLATE, ALLOY);
        event.setMaterial(Materials.Duranium).asMetal(295, MOLTEN);
        event.setMaterial(Materials.Electrum).asMetal(1330, PLATE, FOIL, ROD_LONG, WIRE_FINE, ALLOY);
        event.setMaterial(Materials.EnrichedNaquadah).asMetal(4500, 4500, POSITIVE_CHANGING_RGB);
        event.setMaterial(Materials.GalliumArsenide).asMetal(295, 1200, PLATE, ALLOY);
        event.setMaterial(Materials.HSSE).asMetal(5400, 5400, FRAME);
        event.setMaterial(Materials.HSSG).asMetal(4500, 4500, FRAME);
        event.setMaterial(Materials.HSSS).asMetal(5400, 5400);
        event.setMaterial(Materials.Invar).asMetal(1700, FRAME, RING, ALLOY);
        event.setMaterial(Materials.IronMagnetic).asMetal(1811, ROD_LONG);
        event.setMaterial(Materials.Kanthal).asMetal(1800).forceBF(true);
        event.setMaterial(Materials.Magnalium).asMetal(870, PLATE, ROD_LONG, ALLOY);
        event.setMaterial(Materials.NeodymiumMagnetic).asMetal(1297, ROD_LONG);
        event.setMaterial(Materials.Neutronium).asMetal(10000, 10000, SCREW, BOLT, RING, FRAME);
        event.setMaterial(Materials.Naquadah).asMetal(5400, 5400).asOre();
        event.setMaterial(Materials.NaquadahAlloy).asMetal(7200, 7200);
        event.setMaterial(Materials.Naquadria).asMetal(9000, 9000, POSITIVE_CHANGING_RGB);
        event.setMaterial(Materials.Nichrome).asMetal(2700, 2700);
        event.setMaterial(Materials.NickelZincFerrite).asMetal(1500);
        event.setMaterial(Materials.NiobiumTitanium).asMetal(4500, 4500, PLATE, FOIL, ROD_LONG, WIRE_FINE);
        event.setMaterial(Materials.Osmiridium).asMetal(3333, 2500, FRAME);
        event.setMaterial(Materials.RedAlloy).asMetal(295, PLATE, FOIL, ROD_LONG, WIRE_FINE, ALLOY);
        event.setMaterial(Materials.RedSteel).asMetal(1300, 1300).forceBF(false);
        event.setMaterial(Materials.RoseGold).asMetal(1600, WIRE_FINE, ALLOY);
        event.setMaterial(Materials.SolderingAlloy).asMetal(400, PLATE, ROD_LONG, ALLOY);
        event.setMaterial(Materials.Steel).asMetal(1811, 1000, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL, ITEM_CASING, WIRE_FINE).forceBF(false);
        event.setMaterial(Materials.SteelMagnetic).asMetal(1000, 1000, ROD_LONG).forceBF(false);
        event.setMaterial(Materials.SterlingSilver).asMetal(1700, ALLOY);
        event.setMaterial(Materials.StainlessSteel).asMetal(1700, 1700, PLATE, PLATE_DENSE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL, ITEM_CASING).forceBF(false);
        event.setMaterial(Materials.TinAlloy).asMetal(1158, ALLOY);
        event.setMaterial(Materials.Tritanium).asMetal(295, FRAME, MOLTEN);
        event.setMaterial(Materials.TungstenCarbide).asMetal(2460, 2460);
        event.setMaterial(Materials.TungstenSteel).asMetal(3000, 3000, PLATE, ROD_LONG, SCREW, BOLT, RING, GEAR, FRAME, ROTOR, GEAR_SMALL);
        event.setMaterial(Materials.Ultimet).asMetal(2700, 2700, PLATE);
        event.setMaterial(Materials.VanadiumGallium).asMetal(4500, 4500, ROD_LONG, PLATE);
        event.setMaterial(Materials.VanadiumSteel).asMetal(1453, 1453).forceBF(false);
        event.setMaterial(Materials.Vibranium).asMetal(295, FRAME);
        event.setMaterial(Materials.WroughtIron).asMetal(1811, PLATE, ROD_LONG, SCREW, BOLT, RING, FRAME);
        event.setMaterial(Materials.YttriumBariumCuprate).asMetal(4500, 4500, PLATE, FOIL, ROD_LONG, WIRE_FINE, ALLOY);
        /**
         **  Dusts
         **/
        /**
         *  Organic
         **/
        event.setMaterial(Materials.Ash).asDust();
        event.setMaterial(Materials.AntimonyTrioxide).asDust();
        event.setMaterial(Materials.Clay).asDust();
        event.setMaterial(Materials.CupricOxide).asDust();
        event.setMaterial(Materials.DarkAsh).asDust();
        event.setMaterial(Materials.Dibenzene).asDust();
        event.setMaterial(Materials.DibenzoylPeroxide).asDust();
        event.setMaterial(Materials.GelledToluene).asDust();
        event.setMaterial(Materials.Graphene).asDust(PLATE, WIRE_FINE);
        event.setMaterial(Materials.Polydimethylsiloxane).asDust();
        event.setMaterial(Materials.RareEarth).asDust();
        event.setMaterial(Materials.RawRubber).asDust(RUBBERTOOLS);
        event.setMaterial(Materials.RawStyreneButadieneRubber).asDust(RUBBERTOOLS);
        event.setMaterial(Materials.SodaAsh).asDust();
        /**
         *  Inorganic
         **/
        event.setMaterial(Materials.AluminiumHydroxide).asDust();
        event.setMaterial(Materials.AluminiumTrichloride).asDust();
        event.setMaterial(Materials.Aluminosilicate).asDust();
        event.setMaterial(Materials.AmmoniumChloride).asDust();
        event.setMaterial(Materials.ArsenicTrioxide).asDust();
        event.setMaterial(Materials.Biotite).asDust();
        event.setMaterial(Materials.Bitumen).asDust().asFluid(0, 1000).flags(MOLTEN);
        event.setMaterial(Materials.BlackGranite).asDust(ROCK);
        event.setMaterial(Materials.BlueSchist).asDust(ROCK);
        event.setMaterial(Materials.BorosilicateGlass).asDust();
        event.setMaterial(Materials.Brick).asDust();
        event.setMaterial(Materials.CalciumChloride).asDust();
        event.setMaterial(Materials.CalciumSulfate).asDust();
        event.setMaterial(Materials.CobaltOxide).asDust();
        event.setMaterial(Materials.Concrete).asDust(300).asFluid();
        event.setMaterial(Materials.DialuminiumTrioxide).asDust();
        event.setMaterial(Materials.Energium).asDust();
        event.setMaterial(Materials.FerricChloride).asDust();
        event.setMaterial(Materials.FerriteMixture).asDust();
        event.setMaterial(Materials.Ferrosilite).asDust();
        event.setMaterial(Materials.Fireclay).asDust();
        event.setMaterial(Materials.Fluorite).asDust();
        event.setMaterial(Materials.GreenSchist).asDust(ROCK);
        event.setMaterial(Materials.IndiumGalliumPhosphide).asSolid(349, PLATE, MOLTEN, ALLOY);
        event.setMaterial(Materials.IridiumSodiumOxide).asDust();
        event.setMaterial(Materials.Kimberlite).asDust(ROCK);
        event.setMaterial(Materials.Komatiite).asDust(ROCK);
        event.setMaterial(Materials.Lapotronium).asFluid();
        event.setMaterial(Materials.Limestone).asDust(ROCK);
        event.setMaterial(Materials.LithiumChloride).asSolid(880, MOLTEN);
        event.setMaterial(Materials.MagnesiumCarbonate).asDust();
        event.setMaterial(Materials.MagnesiumChloride).asDust();
        event.setMaterial(Materials.Marble).asDust(ROCK);
        event.setMaterial(Materials.Massicot).asDust();
        event.setMaterial(Materials.Obsidian).asDust(ROD_LONG, PLATE);
        event.setMaterial(Materials.PlatinumGroupSludge).asDust();
        event.setMaterial(Materials.PhosphorousPentoxide).asDust();
        event.setMaterial(Materials.PotassiumBisulfate).asDust();
        event.setMaterial(Materials.PotassiumFeldspar).asDust();
        event.setMaterial(Materials.Powellite).asOre(1, 5, true).harvestLevel(2);
        //event.setMaterial(Pyrochlore).asDust(ORE).addComposition(of(Calcium, 2, Niobium, 2, Oxygen, 7));
        event.setMaterial(Materials.Quartzite).asDust(ROCK);
        event.setMaterial(Materials.Quicklime).asDust();
        event.setMaterial(Materials.ReactionCatalyst).asDust();
        event.setMaterial(Materials.RedGranite).asDust(ROCK);
        event.setMaterial(Materials.SiliconDioxide).asDust();
        event.setMaterial(Materials.Shale).asDust(ROCK);
        event.setMaterial(Materials.Slate).asDust(ROCK);
        event.setMaterial(Materials.SodiumAluminate).asDust();
        event.setMaterial(Materials.SodiumBisulfate).asDust();
        event.setMaterial(Materials.SodiumPersulfate).asDust();
        event.setMaterial(Materials.SodiumHydroxide).asDust();
        event.setMaterial(Materials.SodiumSulfate).asDust();
        event.setMaterial(Materials.SodiumSulfide).asDust();
        event.setMaterial(Materials.TungsticAcid).asDust();
        event.setMaterial(Materials.TungstenTrioxide).asDust();
        event.setMaterial(Materials.Wollastonite).asDust();
        //Nuclear

        /**
         **  Ores
         **/
        event.setMaterial(Materials.Almandine).asOre().harvestLevel(1);
        event.setMaterial(Materials.Alumina).asOre().asSolid(2345, MOLTEN);
        event.setMaterial(Materials.Andradite).asOre();
        event.setMaterial(Materials.Hematite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Bastnasite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Barite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Bentonite).asOre(); // TODO: Ore Gen
        event.setMaterial(Materials.BrownLimonite).asOre().harvestLevel(1);
        event.setMaterial(Materials.Calcite).asOre(MOLTEN).asFluid(0, 1612).harvestLevel(1);
        event.setMaterial(Materials.Cassiterite).asOre().harvestLevel(1);
        event.setMaterial(Materials.Chalcopyrite).asOre().harvestLevel(1);
        event.setMaterial(Materials.Cinnabar).asOre().harvestLevel(1);
        event.setMaterial(Materials.Chromite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Cobaltite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Sheldonite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Galena).asOre().harvestLevel(3);
        event.setMaterial(Materials.Garnierite).asOre().harvestLevel(3);
        event.setMaterial(Materials.Glauconite).asOre().harvestLevel(2); // TODO: Ore Gen;
        event.setMaterial(Materials.Graphite).asDust(ROD).asOre().harvestLevel(2);
        event.setMaterial(Materials.Grossular).asOre().harvestLevel(1);
        event.setMaterial(Materials.Ilmenite).asOre().harvestLevel(3);
        event.setMaterial(Materials.Lepidolite).asOre().harvestLevel(2); // TODO: Ore Gen;
        event.setMaterial(Materials.Rutile).asOre();
        event.setMaterial(Materials.Magnesite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Magnetite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Malachite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Molybdenite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Pentlandite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Phosphate).asOre().harvestLevel(1);
        event.setMaterial(Materials.Pitchblende).asOre();
        event.setMaterial(Materials.Pyrite).asOre().harvestLevel(1);
        event.setMaterial(Materials.Pyrolusite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Pyrope).asOre().harvestLevel(2);
        event.setMaterial(Materials.Saltpeter).asOre();
        event.setMaterial(Materials.Scheelite).asDust(2500).asOre().harvestLevel(3);
        event.setMaterial(Materials.Soapstone).asOre().harvestLevel(1); // TODO: Ore Gen;
        event.setMaterial(Materials.Spodumene).asOre().harvestLevel(2);
        event.setMaterial(Materials.Sperrylite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Spessartine).asOre().harvestLevel(2);
        event.setMaterial(Materials.Sphalerite).asOre().harvestLevel(1);
        event.setMaterial(Materials.Stibnite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Tantalite).asOre().harvestLevel(3);
        event.setMaterial(Materials.Talc).asOre();
        event.setMaterial(Materials.Tetrahedrite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Tungstate).asOre().harvestLevel(3);
        event.setMaterial(Materials.Uraninite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Uvarovite).asOre();
        event.setMaterial(Materials.VanadiumMagnetite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Wulfenite).asOre().harvestLevel(3);
        event.setMaterial(Materials.YellowLimonite).asOre().harvestLevel(2);
        event.setMaterial(Materials.Zircon).asOre();
        /**
         **  Ore Stones
         **/
        event.setMaterial(Materials.Bauxite).asOreStone(ORE_SMALL, ROCK).harvestLevel(1);
        event.setMaterial(Materials.Lignite).asGemBasic(false).asOreStone(0, 2, ORE_SMALL, ROCK);
        event.setMaterial(Materials.OilShale).asDust(ORE, ORE_STONE, ORE_SMALL, RAW_ORE, RAW_ORE_BLOCK, BEARING_ROCK, ROCK).setExpRange(1,5).harvestLevel(1);
        event.setMaterial(Materials.RockSalt).asOreStone(ORE_SMALL, ROCK).harvestLevel(1);
        event.setMaterial(Materials.Salt).asOreStone(ORE_SMALL, ROCK).harvestLevel(1);
        /**
         **  Gems
         **/
        /**
         *  Regular
         **/
        event.setMaterial(Materials.Amber).asGem(false).asOre(3, 7, true);
        event.setMaterial(Materials.Amethyst).asGem(false).replaceItem(GEM_FLAWED, Items.AMETHYST_SHARD).asOre(3, 7, true);
        event.setMaterial(Materials.Sapphire).asGem(true, GT5RMaterialTypes.BOULE).asOre(3, 7, true);
        event.setMaterial(Materials.BlueTopaz).asGem(true).asOre(3, 7, true);
        event.setMaterial(Materials.Glass).asFluid(0, 1500).asDust(PLATE, LENS, MOLTEN);
        event.setMaterial(Materials.GreenSapphire).asGem(true, GT5RMaterialTypes.BOULE).asOre(3, 7, true);
        event.setMaterial(Materials.Jade).asGem(false).asOre(3, 7, true);
        event.setMaterial(Materials.Lazurite).asGemBasic(false, PLATE).asOre(2, 5, true).harvestLevel(1);
        event.setMaterial(Materials.LigniteCoke).asGemBasic(false);
        event.setMaterial(Materials.PetroleumCoke).asGemBasic(false);
        event.setMaterial(Materials.Olivine).asGem(false, PLATE).asOre(3, 7, true);
        event.setMaterial(Materials.Opal).asGem(true).asOre(3, 7, true);
        event.setMaterial(Materials.TricalciumPhosphate).asOre(3, 7, true).harvestLevel(2);
        event.setMaterial(Materials.MilkyQuartz).asGemBasic(false, BEARING_ROCK, ROD_LONG, QUARTZ_LIKE_BLOCKS).asOre(2, 5, true).harvestLevel(1);
        event.setMaterial(Materials.RedGarnet).asGem(true).asOre(3, 7, true);
        event.setMaterial(Materials.Ruby).asGem(true, GT5RMaterialTypes.BOULE).asOre(3, 7, true);
        event.setMaterial(Materials.Sodalite).asGemBasic(false).asOre(2, 5, true).harvestLevel(1);
        event.setMaterial(Materials.Tanzanite).asGem(false).asOre(3, 7, true);
        event.setMaterial(Materials.Topaz).asGem(false).asOre(3, 7, true);
        event.setMaterial(Materials.YellowGarnet).asGem(true).asOre(3, 7, true);
        /**
         *  Basic
         **/
        event.setMaterial(Materials.CertusQuartz).asGemBasic(false,PLATE, QUARTZ_LIKE_BLOCKS).asOre(1, 5, true);
        event.setMaterial(Materials.Fluix).asGemBasic(false, QUARTZ_LIKE_BLOCKS);
        event.setMaterial(Charcoal).asGemBasic(false);
        event.setMaterial(Materials.CoalCoke).asGemBasic(false);
        event.setMaterial(Materials.Apatite).asGemBasic(false, PLATE).asOre(2, 5, true).harvestLevel(1);
        event.setMaterial(Materials.Monazite).asOre(3, 7, true).harvestLevel(1);
        /**
         **  Plastic Related
         **/
        event.setMaterial(Materials.EpoxyResin).asSolid(400, 0, PLATE, MOLTEN);
        event.setMaterial(Materials.FiberReinforcedEpoxyResin).flags(PLATE);
        event.setMaterial(Materials.Plastic).asSolid(295, 0, PLATE, FOIL, FRAME).asFluid();
        //event.setMaterial(Polystyrene).asSolid(295, 0);
        event.setMaterial(Materials.Polytetrafluoroethylene).asSolid(1400, 0, PLATE, FRAME, ROD_LONG);
        event.setMaterial(Materials.PolyvinylChloride).asSolid(295, 0, PLATE, FOIL);
        event.setMaterial(Materials.Rubber).asSolid(295, 0, PLATE, RING, MOLTEN);
        event.setMaterial(Materials.SiliconeRubber).asSolid(900, 0, PLATE, FOIL, MOLTEN);
        event.setMaterial(Materials.StyreneButadieneRubber).asSolid(295, 0, PLATE, RING);
        /**
         **  Misc
         **/

        event.setMaterial(Materials.Superconductor).asSolid(PLATE);
        /**
         ***  Fluids
         **/
        /**
         * Dyes
         */
        event.setMaterial(Materials.WaterMixedWhiteDye).asFluid();
        event.setMaterial(Materials.WaterMixedOrangeDye).asFluid();
        event.setMaterial(Materials.WaterMixedMagentaDye).asFluid();
        event.setMaterial(Materials.WaterMixedLightBlueDye).asFluid();
        event.setMaterial(Materials.WaterMixedYellowDye).asFluid();
        event.setMaterial(Materials.WaterMixedLimeDye).asFluid();
        event.setMaterial(Materials.WaterMixedPinkDye).asFluid();
        event.setMaterial(Materials.WaterMixedGrayDye).asFluid();
        event.setMaterial(Materials.WaterMixedLightGrayDye).asFluid();
        event.setMaterial(Materials.WaterMixedCyanDye).asFluid();
        event.setMaterial(Materials.WaterMixedPurpleDye).asFluid();
        event.setMaterial(Materials.WaterMixedBlueDye).asFluid();
        event.setMaterial(Materials.WaterMixedBrownDye).asFluid();
        event.setMaterial(Materials.WaterMixedGreenDye).asFluid();
        event.setMaterial(Materials.WaterMixedRedDye).asFluid();
        event.setMaterial(Materials.WaterMixedBlackDye).asFluid();
        event.setMaterial(Materials.ChemicalWhiteDye).asFluid();
        event.setMaterial(Materials.ChemicalOrangeDye).asFluid();
        event.setMaterial(Materials.ChemicalMagentaDye).asFluid();
        event.setMaterial(Materials.ChemicalLightBlueDye).asFluid();
        event.setMaterial(Materials.ChemicalYellowDye).asFluid();
        event.setMaterial(Materials.ChemicalLimeDye).asFluid();
        event.setMaterial(Materials.ChemicalPinkDye).asFluid();
        event.setMaterial(Materials.ChemicalGrayDye).asFluid();
        event.setMaterial(Materials.ChemicalLightGrayDye).asFluid();
        event.setMaterial(Materials.ChemicalCyanDye).asFluid();
        event.setMaterial(Materials.ChemicalPurpleDye).asFluid();
        event.setMaterial(Materials.ChemicalBlueDye).asFluid();
        event.setMaterial(Materials.ChemicalBrownDye).asFluid();
        event.setMaterial(Materials.ChemicalGreenDye).asFluid();
        event.setMaterial(Materials.ChemicalRedDye).asFluid();
        event.setMaterial(Materials.ChemicalBlackDye).asFluid();
        event.setMaterial(Materials.WhiteConcrete).asFluid();
        event.setMaterial(Materials.OrangeConcrete).asFluid();
        event.setMaterial(Materials.MagentaConcrete).asFluid();
        event.setMaterial(Materials.LightBlueConcrete).asFluid();
        event.setMaterial(Materials.YellowConcrete).asFluid();
        event.setMaterial(Materials.LimeConcrete).asFluid();
        event.setMaterial(Materials.PinkConcrete).asFluid();
        event.setMaterial(Materials.GrayConcrete).asFluid();
        event.setMaterial(Materials.LightGrayConcrete).asFluid();
        event.setMaterial(Materials.CyanConcrete).asFluid();
        event.setMaterial(Materials.PurpleConcrete).asFluid();
        event.setMaterial(Materials.BlueConcrete).asFluid();
        event.setMaterial(Materials.BrownConcrete).asFluid();
        event.setMaterial(Materials.GreenConcrete).asFluid();
        event.setMaterial(Materials.RedConcrete).asFluid();
        event.setMaterial(Materials.BlackConcrete).asFluid();

        /**
         *  Organic
         **/
        event.setMaterial(Materials.Acetone).asFluid();
        event.setMaterial(Materials.CharcoalByproducts).asGas();
        event.setMaterial(Materials.FermentedBiomass).asFluid(16);
        event.setMaterial(Materials.SeedOil).asFluid(2).flags(GT5RMaterialTags.SEMIFUELS);
        event.setMaterial(Materials.WoodTar).asFluid();
        event.setMaterial(Materials.WoodVinegar).asFluid();
        //Alkanoles
        event.setMaterial(Materials.Methanol).asFluid(84);
        event.setMaterial(Materials.Ethanol).asFluid(148);
        event.setMaterial(Materials.Propanol).asFluid(196);
        event.setMaterial(Materials.Butanol).asFluid(166);
        event.setMaterial(Materials.Heptanol).asFluid(226);
        //Alkenoles
        event.setMaterial(Materials.Ethenol).asFluid(120);
        event.setMaterial(Materials.Propenol).asFluid(196);
        event.setMaterial(Materials.Butenol).asFluid(186);
        //Alkanedioles
        event.setMaterial(Materials.Ethanediol).asFluid(216);
        event.setMaterial(Materials.Propanediol).asFluid(256);
        event.setMaterial(Materials.Butanediol).asFluid(286);
        //Plastic Related
        event.setMaterial(Materials.SiliconeRubber).asFluid();
        //Misc
        event.setMaterial(Materials.AceticAcid).asFluid();
        event.setMaterial(Materials.AllylChloride).asFluid();
        event.setMaterial(Materials.Benzaldehyde).asFluid();
        event.setMaterial(Materials.Benzene).asFluid(288);
        event.setMaterial(Materials.BenzoylChloride).asFluid();
        event.setMaterial(Materials.Biomass).asFluid(8).flags(GT5RMaterialTags.SEMIFUELS);
        event.setMaterial(Materials.BisphenolA).asFluid();
        event.setMaterial(Materials.Chloramine).asFluid();
        event.setMaterial(Materials.Chloroform).asFluid();
        event.setMaterial(Materials.Cumene).asFluid();
        event.setMaterial(Materials.Chlorobenzene).asFluid();
        event.setMaterial(Materials.Dichloroethane).asFluid();
        event.setMaterial(Materials.Dimethyldichlorosilane).asFluid();
        event.setMaterial(Materials.Dimethylhydrazine).asFluid();
        event.setMaterial(Materials.Epichlorohydrin).asFluid();
        event.setMaterial(Materials.Glue).asFluid();
        event.setMaterial(Materials.Glycerol).asFluid(164);
        event.setMaterial(Materials.GlycerylTrinitrate).asFluid();
        event.setMaterial(Materials.Honey).asFluid(); // TODO: Only when Forestry's present;
        event.setMaterial(Materials.Isoprene).asFluid();
        event.setMaterial(Materials.MethylAcetate).asFluid();
        event.setMaterial(Materials.Naphtha).asFluid(256);
        event.setMaterial(Materials.Phenol).asFluid(288);
        event.setMaterial(Materials.PolyvinylAcetate).asFluid();
        event.setMaterial(Materials.SquidInk).asFluid();
        event.setMaterial(Materials.Styrene).asFluid();
        event.setMaterial(Materials.SulfuricNaphtha).asFluid(32);
        event.setMaterial(Materials.Toluene).asFluid(328);
        event.setMaterial(Materials.VinylAcetate).asFluid();
        /**
         *  Inorganic
         **/
        event.setMaterial(Materials.AluminiumFluoride).asFluid(0,1560).flags(MOLTEN, ACID);
        event.setMaterial(Materials.Antimatter).asFluid();
        event.setMaterial(Materials.BlueVitriol).asFluid();
        event.setMaterial(Materials.GreenVitriol).asFluid();
        event.setMaterial(Materials.RedVitriol).asFluid();
        event.setMaterial(Materials.PinkVitriol).asFluid();
        event.setMaterial(Materials.CyanVitriol).asFluid();
        event.setMaterial(Materials.WhiteVitriol).asFluid();
        event.setMaterial(Materials.GrayVitriol).asFluid();
        event.setMaterial(Materials.MartianVitriol).asFluid();
        event.setMaterial(Materials.VitriolOfClay).asFluid();
        event.setMaterial(Materials.AquaRegia).asFluid().flags(ACID);
        event.setMaterial(Materials.CalciumAcetateSolution).asFluid();
        event.setMaterial(Materials.ChloroplatinicAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.Coolant).asFluid();
        event.setMaterial(Materials.Cryolite).asFluid(0, 1285).flags(MOLTEN, ACID);
        event.setMaterial(Materials.DistilledWater).asFluid();
        event.setMaterial(Materials.DilutedHydrochloricAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.DilutedSulfuricAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.DrillingFluid).asFluid(); // TODO: Perhaps for a bedrock drill;
        event.setMaterial(Materials.HexafluorosilicicAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.HotCoolant).asFluid(0,500);
        event.setMaterial(Materials.HeavyWater).asFluid();
        event.setMaterial(Materials.SemiheavyWater).asFluid();
        event.setMaterial(Materials.TritiatedWater).asFluid();
        event.setMaterial(Materials.HotHeavyWater).asFluid(0, 600);
        event.setMaterial(Materials.HotSemiheavyWater).asFluid(0, 550);
        event.setMaterial(Materials.HotTritiatedWater).asFluid(0, 650);
        event.setMaterial(Materials.HotMoltenSodium).asFluid(0, 1100).flags(MOLTEN);
        event.setMaterial(Materials.HotMoltenTin).asFluid(0, 2800).flags(MOLTEN);
        event.setMaterial(Materials.HotMoltenLithiumChloride).asFluid(0, 1600).flags(MOLTEN);
        event.setMaterial(Materials.HydrochloricAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.HydrofluoricAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.HydrogenFluoride).asGas().flags(ACID).fluidDensity(-111);
        event.setMaterial(Materials.HydrogenPeroxide).asFluid();
        event.setMaterial(Materials.HypochlorousAcid).asFluid().flags(ACID);

        event.setMaterial(Materials.Lubricant).asFluid();
        event.setMaterial(Materials.NickelSulfate).asFluid();
        event.setMaterial(Materials.NitricAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.PeroxydisulfuricAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.PhosphoricAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.SaltWater).asFluid();
        event.setMaterial(Materials.SodiumPersulfateSolution).asFluid();
        event.setMaterial(Materials.Steam).asGas(1, 395).fluidDensity(-800);
        event.setMaterial(Materials.SuperheatedSteam).asGas(2, 600).fluidDensity(-3000);
        event.setMaterial(Materials.SulfuricAcid).asFluid().flags(ACID);
        event.setMaterial(Materials.Tar).asFluid();
        event.setMaterial(Materials.ThoriumSalt).asFluid(0, 600).flags(MOLTEN);
        event.setMaterial(Materials.TitaniumTetrachloride).asFluid().flags(ACID);
        event.setMaterial(Materials.UUAmplifier).asFluid();
        event.setMaterial(Materials.UUMatter).asFluid();
        //Nuclear
        /**
         ***  Gases/Plasmas
         **/
        /**
         *  Organic
         **/
        //Alkanes
        event.setMaterial(Materials.Methane).asGas(104);
        event.setMaterial(Materials.Ethane).asGas(168);
        event.setMaterial(Materials.Propane).asGas(232).fluidDensity(-1000);
        event.setMaterial(Materials.Butane).asGas(296).fluidDensity(-1000);
        //Alkenes
        event.setMaterial(Materials.Ethylene).asGas(128).fluidDensity(1000);
        event.setMaterial(Materials.Propene).asGas(192);
        event.setMaterial(Materials.Butene).asGas(256);
        event.setMaterial(Materials.Butadiene).asGas(206);
        //Misc
        event.setMaterial(Materials.HotCarbonDioxide).asGas().fluidDensity(1000);
        event.setMaterial(Materials.CarbonDioxide).asGas().fluidDensity(756);
        event.setMaterial(Materials.CarbonMonoxide).asGas(24).fluidDensity(1134);
        event.setMaterial(Materials.Chloromethane).asGas();
        event.setMaterial(Materials.Dimethylamine).asGas();
        event.setMaterial(Materials.LPG).asFluid(256);
        event.setMaterial(Materials.NaturalGas).asGas(15).fluidDensity(-500);
        event.setMaterial(Materials.RefineryGas).asGas(128);
        event.setMaterial(Materials.SulfuricGas).asGas(20);
        event.setMaterial(Materials.Tetrafluoroethylene).asGas();
        event.setMaterial(Materials.VinylChloride).asGas();
        event.setMaterial(Materials.WoodGas).asGas(24);
        /**
         *  Inorganic
         **/
        event.setMaterial(Materials.Air).asGas().fluidDensity(0);
        event.setMaterial(Materials.Ammonia).asGas().flags(ACID).fluidDensity(-263);
        event.setMaterial(Materials.EnderAir).asGas().fluidDensity(0);
        event.setMaterial(Materials.DinitrogenTetroxide).asGas();
        event.setMaterial(Materials.HydrogenSulfide).asGas();
        event.setMaterial(Materials.NetherAir).asGas().fluidDensity(0);
        event.setMaterial(Materials.NitrogenMonoxide).asGas();
        event.setMaterial(Materials.NitrogenDioxide).asGas();
        event.setMaterial(Materials.NobleGases).asGas(0,790);
        event.setMaterial(Materials.SulfurDioxide).asGas().fluidDensity(689);
        event.setMaterial(Materials.SulfurTrioxide).asGas(0,300).fluidDensity(517);
        /**
         ** Fuels
         **/
        event.setMaterial(Materials.Creosote).asFluid(8).flags(GT5RMaterialTags.SEMIFUELS);
        event.setMaterial(Materials.Oil).asFluid(16).flags(GT5RMaterialTags.SEMIFUELS);
        event.setMaterial(Materials.OilHeavy).asFluid(32).flags(GT5RMaterialTags.SEMIFUELS);
        event.setMaterial(Materials.OilLight).asFluid(16).flags(GT5RMaterialTags.SEMIFUELS);
        event.setMaterial(Materials.BioDiesel).asFluid(192);
        event.setMaterial(Materials.Diesel).asFluid(128);
        event.setMaterial(Materials.FishOil).asFluid(2).flags(GT5RMaterialTags.SEMIFUELS);
        event.setMaterial(Materials.NitroDiesel).asFluid(512);
        event.setMaterial(Materials.FuelOil).asFluid(448);
        event.setMaterial(Materials.Gasoline).asFluid(384);
        event.setMaterial(Materials.Kerosene).asFluid(384);
        /**
         ** Cracked Stuff
         */
        event.setMaterial(Materials.SteamCrackedEthane).asGas();
        event.setMaterial(Materials.SteamCrackedPropane).asGas();
        event.setMaterial(Materials.SteamCrackedButane).asGas();
        event.setMaterial(Materials.SteamCrackedNaphtha).asFluid();
        event.setMaterial(Materials.SteamCrackedRefineryGas).asGas();
        event.setMaterial(Materials.HydroCrackedEthane).asGas();
        event.setMaterial(Materials.HydroCrackedPropane).asGas();
        event.setMaterial(Materials.HydroCrackedButane).asGas();
        event.setMaterial(Materials.HydroCrackedNaphtha).asFluid();
        event.setMaterial(Materials.HydroCrackedRefineryGas).asGas();
        processInto(event);
        nuclearIsotopes(event);
        toolsAndArmor(event);
        workbenches(event);
    }

    private static void processInto(GregTechMaterialEvent event){
        /**
         ***  Solids
         **/
        /**
         **  Metals
         **/
        event.setMaterial(Materials.AnnealedCopper).mats(of(Copper, 1));
        event.setMaterial(Materials.BatteryAlloy).mats(of(Materials.Lead, 4, Materials.Antimony, 1));
        event.setMaterial(Materials.BismuthBronze).mats(of(Materials.Bismuth, 1, Materials.Zinc, 1, Copper, 3));
        event.setMaterial(Materials.BlackBronze).mats(of(Gold, 1, Materials.Silver, 1, Copper, 3));
        event.setMaterial(Materials.BlackSteel).mats(of(Materials.Nickel, 1, Materials.BlackBronze, 1, Materials.Steel, 3));
        event.setMaterial(Materials.BlueSteel).mats(of(Materials.RoseGold, 1, Materials.Brass, 1, Materials.Steel, 2, Materials.BlackSteel, 4));
        event.setMaterial(Materials.Brass).mats(of(Materials.Zinc, 1, Copper, 3));
        event.setMaterial(Materials.Bronze).mats(of(Materials.Tin, 1, Copper, 3));
        event.setMaterial(Materials.CdInAGAlloy).mats(of(Materials.Cadmium, 1, Materials.Indium, 1, Materials.Silver, 1));
        event.setMaterial(Materials.CobaltBrass).mats(of(Materials.Brass, 7, Materials.Aluminium, 1, Materials.Cobalt, 1));
        event.setMaterial(Materials.Cupronickel).mats(of(Copper, 1, Materials.Nickel, 1));
        event.setMaterial(Materials.Electrum).mats(of(Materials.Silver, 1, Gold, 1));
        event.setMaterial(Materials.HSSE).mats(of(Materials.HSSG, 6, Materials.Cobalt, 1, Materials.Manganese, 1, Materials.Silicon, 1));
        event.setMaterial(Materials.HSSG).mats(of(Materials.TungstenSteel, 5, Materials.Chromium, 1, Materials.Molybdenum, 2, Materials.Vanadium, 1));
        event.setMaterial(Materials.HSSS).mats(of(Materials.HSSG, 6, Materials.Iridium, 2, Materials.Osmium, 1));
        event.setMaterial(Materials.Invar).mats(of(Iron, 2, Materials.Nickel, 1));
        event.setMaterial(Materials.IronMagnetic).mats(of(Iron, 1)).elecTicks(52);
        event.setMaterial(Materials.Kanthal).mats(of(Iron, 1, Materials.Aluminium, 1, Materials.Chromium, 1));
        event.setMaterial(Materials.Magnalium).mats(of(Materials.Magnesium, 1, Materials.Aluminium, 2));
        event.setMaterial(Materials.NeodymiumMagnetic).mats(of(Materials.Neodymium, 1)).elecTicks(122);
        event.setMaterial(Materials.Nichrome).mats(of(Materials.Nickel, 4, Materials.Chromium, 1));
        event.setMaterial(Materials.NickelZincFerrite).mats(of(Materials.Nickel, 1, Materials.Zinc, 1, Iron, 4, Materials.Oxygen, 8));
        event.setMaterial(Materials.NiobiumTitanium).mats(of(Materials.Nickel, 4, Materials.Chromium, 1));
        event.setMaterial(Materials.Osmiridium).mats(of(Materials.Iridium, 3, Materials.Osmium, 1)).elecTicks(608);
        event.setMaterial(Materials.RedAlloy).mats(of(Copper, 1, Redstone, 4));
        event.setMaterial(Materials.RedSteel).mats(of(Materials.SterlingSilver, 1, Materials.BismuthBronze, 1, Materials.Steel, 2, Materials.BlackSteel, 4));
        event.setMaterial(Materials.RoseGold).mats(of(Copper, 1, Gold, 4));
        event.setMaterial(Materials.SolderingAlloy).mats(of(Materials.Tin, 9, Materials.Antimony, 1));
        event.setMaterial(Materials.Steel).mats(of(Iron, 50, Materials.Carbon, 1), 50).elecTicks(2600);
        event.setMaterial(Materials.SteelMagnetic).mats(of(Materials.Steel, 1)).elecTicks(52);
        event.setMaterial(Materials.SterlingSilver).mats(of(Copper, 1, Materials.Silver, 4));
        event.setMaterial(Materials.StainlessSteel).mats(of(Iron, 6, Materials.Chromium, 1, Materials.Manganese, 1, Materials.Nickel, 1)).elecTicks(450);
        event.setMaterial(Materials.TinAlloy).mats(of(Materials.Tin, 1, Iron, 1));
        event.setMaterial(Materials.TungstenCarbide).mats(of(Materials.Tungsten, 1, Materials.Carbon, 1));
        event.setMaterial(Materials.TungstenSteel).mats(of(Materials.Steel, 1, Materials.Tungsten, 1));
        event.setMaterial(Materials.Ultimet).mats(of(Materials.Cobalt, 5, Materials.Chromium, 2, Materials.Nickel, 1, Materials.Molybdenum, 1)).elecTicks(504);
        event.setMaterial(Materials.VanadiumGallium).mats(of(Materials.Vanadium, 3, Materials.Gallium, 1));
        event.setMaterial(Materials.VanadiumSteel).mats(of(Materials.Vanadium, 1, Materials.Chromium, 1, Materials.Steel, 7));
        event.setMaterial(Materials.WroughtIron).mats(of(Iron, 1));
        event.setMaterial(Materials.YttriumBariumCuprate).mats(of(Materials.Yttrium, 1, Materials.Barium, 2, Copper, 3, Materials.Oxygen, 7));
        /**
         **  Dusts
         **/
        /**
         *  Organic
         **/
        event.setMaterial(Materials.AntimonyTrioxide).mats(of(Materials.Antimony, 2, Materials.Oxygen, 3)).elecTicks(250);
        event.setMaterial(Materials.Clay).mats(of(Materials.Alumina, 5, Materials.SiliconDioxide, 12, Water, 1, Materials.Sodium, 1, Materials.Lithium, 1), 18).elecTicks(182);
        event.setMaterial(Materials.CupricOxide).mats(of(Copper, 1, Materials.Oxygen, 1)).elecTicks(72);
        event.setMaterial(Materials.DarkAsh).mats(of(Materials.Carbon, 1, Materials.Ash, 1), 1);
        event.setMaterial(Materials.Dibenzene).mats(of(Materials.Carbon,12, Materials.Hydrogen,10));
        event.setMaterial(Materials.DibenzoylPeroxide).mats(of(Materials.Carbon,14, Materials.Hydrogen,10, Materials.Oxygen,4));
        event.setMaterial(Materials.Polydimethylsiloxane).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 6, Materials.Oxygen, 1, Materials.Silicon, 1)).elecTicks(80);
        event.setMaterial(Materials.RawRubber).mats(of(Materials.Carbon, 5, Materials.Hydrogen, 8));
        event.setMaterial(Materials.RawStyreneButadieneRubber).mats(of(Materials.Styrene, 1, Materials.Butadiene, 3));
        event.setMaterial(Materials.SodaAsh).mats(of(Materials.Sodium, 2, Materials.Carbon, 1, Materials.Oxygen, 3)).elecTicks(96);
        event.setMaterial(Wood).mats(of(Materials.Carbon, 1, Materials.Oxygen, 1, Materials.Hydrogen, 1));
        /**
         *  Inorganic
         **/
        event.setMaterial(Materials.AluminiumHydroxide).mats(of(Materials.Aluminium, 1, Materials.Oxygen, 3, Materials.Hydrogen, 3));
        event.setMaterial(Materials.AluminiumTrichloride).mats(of(Materials.Aluminium,1, Materials.Chlorine,3));
        event.setMaterial(Materials.Aluminosilicate).mats(of(Materials.Aluminium, 2, Materials.Silicon, 1, Materials.Oxygen, 5));
        event.setMaterial(Materials.AmmoniumChloride).mats(of(Materials.Nitrogen,1, Materials.Hydrogen,4, Materials.Chlorine,1));
        event.setMaterial(Materials.ArsenicTrioxide).mats(of(Materials.Arsenic, 2, Materials.Oxygen, 3)).elecTicks(180);
        event.setMaterial(Materials.Biotite).mats(b -> b.put(Materials.Potassium, 2).put(Materials.Magnesium, 6).put(Materials.Alumina, 15).put(Materials.SiliconDioxide, 18).put(Materials.Fluorine, 4)).elecTicks(440);
        event.setMaterial(Materials.BlackGranite).mats(of(Materials.Biotite, 1, Materials.PotassiumFeldspar, 1, Flint, 1));
        event.setMaterial(Materials.BorosilicateGlass).mats(of(Materials.Boron, 1, Materials.Silicon, 7, Materials.Oxygen,14));
        event.setMaterial(Materials.Brick).mats(of(Materials.Aluminium, 4, Materials.Silicon, 3, Materials.Oxygen, 12));
        event.setMaterial(Materials.CalciumChloride).mats(of(Materials.Calcium, 1, Materials.Chlorine, 2)).elecTicks(1024);
        event.setMaterial(Materials.CalciumSulfate).mats(of(Materials.Calcium, 1, Materials.Sulfur, 1, Materials.Oxygen, 4));
        event.setMaterial(Materials.CobaltOxide).mats(of(Materials.Cobalt, 1, Materials.Oxygen, 1)).elecTicks(68);
        event.setMaterial(Materials.Concrete).mats(of(Stone, 1));
        event.setMaterial(Materials.DialuminiumTrioxide).mats(of(Materials.Aluminium,2, Materials.Oxygen,3));
        event.setMaterial(Materials.Energium).mats(of(Redstone,5, Materials.Ruby,4));
        event.setMaterial(Materials.FerricChloride).mats(of(Iron, 1, Materials.Chlorine, 3)).elecTicks(876);
        event.setMaterial(Materials.FerriteMixture).mats(of(Materials.Nickel, 1, Materials.Zinc, 1, Iron, 4));
        event.setMaterial(Materials.Ferrosilite).mats(of(Iron, 1, Materials.Silicon, 1, Materials.Oxygen, 3)).elecTicks(120);
        event.setMaterial(Materials.Fireclay).mats(of(Materials.Brick, 1));
        event.setMaterial(Materials.Fluorite).mats(of(Materials.Calcium, 1, Materials.Fluorine, 2));
        event.setMaterial(Materials.IndiumGalliumPhosphide).mats(of(Materials.Indium, 1, Materials.Gallium, 1, Materials.Phosphor, 1));
        event.setMaterial(Materials.IridiumSodiumOxide).mats(of(Materials.Iridium,1, Materials.Sodium,1, Materials.Oxygen,2));
        event.setMaterial(Materials.Komatiite).mats(of(Materials.Olivine, 1, Materials.MagnesiumCarbonate, 2, Flint, 6, Materials.DarkAsh, 3));
        event.setMaterial(Materials.Lapotronium).mats(of(Lapis, 6, Materials.Sapphire, 5, Redstone, 4));
        event.setMaterial(Materials.Limestone).mats(of(Materials.Calcite, 1));
        event.setMaterial(Materials.LithiumChloride).mats(of(Materials.Lithium, 1, Materials.Chlorine, 1)).elecTicks(256);
        event.setMaterial(Materials.MagnesiumChloride).mats(of(Materials.Magnesium, 1, Materials.Chlorine, 2)).elecTicks(1024);
        event.setMaterial(Materials.Marble).mats(of(Materials.Magnesium, 1, Materials.Calcite, 7));
        event.setMaterial(Materials.Massicot).mats(of(Materials.Lead, 1, Materials.Oxygen, 1)).elecTicks(180);
        event.setMaterial(Materials.Obsidian).mats(of(Materials.Magnesium, 1, Iron, 1, Materials.Silicon, 2, Materials.Oxygen, 8)).elecTicks(240);
        event.setMaterial(Materials.PhosphorousPentoxide).mats(of(Materials.Phosphor, 4, Materials.Oxygen, 10)).elecTicks(560);
        event.setMaterial(Materials.PotassiumBisulfate).mats(of(Materials.Potassium, 1, Materials.Hydrogen, 1, Materials.Sulfur, 1, Materials.Oxygen, 4)).elecTicks(300);
        event.setMaterial(Materials.PotassiumFeldspar).mats(of(Materials.Potassium, 2, Materials.Alumina, 5, Materials.SiliconDioxide, 18, Materials.Oxygen, 1)).elecTicks(260);
        event.setMaterial(Materials.Powellite).mats(of(Materials.Calcium, 1, Materials.Molybdenum, 1, Materials.Oxygen, 4));
        //event.setMaterial(Pyrochlore).asDust(ORE).addComposition(of(Calcium, 2, Niobium, 2, Oxygen, 7));
        event.setMaterial(Materials.Quartzite).mats(of(Materials.Silicon, 1, Materials.Oxygen, 2)).elecTicks(60);
        event.setMaterial(Materials.Quicklime).mats(of(Materials.Calcium, 1, Materials.Oxygen, 1)).elecTicks(56);
        event.setMaterial(Materials.ReactionCatalyst).mats(of(Copper,1, Materials.Zinc,1, Materials.Aluminium,2, Materials.Oxygen,4));
        event.setMaterial(Materials.RedGranite).mats(of(Materials.Biotite, 1, Materials.PotassiumFeldspar, 1, Flint, 1)).elecTicks(120);
        event.setMaterial(Materials.SiliconDioxide).mats(of(Materials.Silicon, 1, Materials.Oxygen, 2)).elecTicks(240);
        event.setMaterial(Materials.Shale).mats(of(Materials.Calcite, 2, Materials.MilkyQuartz, 1, Materials.Clay, 1));
        //event.setMaterial(Slate).mats(of())
        event.setMaterial(Materials.SodiumAluminate).mats(of(Materials.Sodium, 1, Materials.Aluminium, 1, Materials.Oxygen, 2));
        event.setMaterial(Materials.SodiumBisulfate).mats(of(Materials.Sodium, 1, Materials.Hydrogen, 1, Materials.Sulfur, 1, Materials.Oxygen, 4)).elecTicks(600);
        event.setMaterial(Materials.SodiumPersulfate).mats(of(Materials.Sodium, 2, Materials.Sulfur, 2, Materials.Oxygen, 8)).elecTicks(432);
        event.setMaterial(Materials.SodiumHydroxide).mats(of(Materials.Sodium, 1, Materials.Oxygen, 1, Materials.Hydrogen, 1)).elecTicks(36);
        event.setMaterial(Materials.SodiumSulfate).mats(of(Materials.Sodium, 2, Materials.Sulfur, 1, Materials.Oxygen, 4));
        event.setMaterial(Materials.SodiumSulfide).mats(of(Materials.Sodium, 2, Materials.Sulfur, 1)).elecTicks(72);
        event.setMaterial(Materials.TungsticAcid).mats(of(Materials.Hydrogen, 2, Materials.Tungsten, 1, Materials.Oxygen, 4));
        event.setMaterial(Materials.TungstenTrioxide).mats(of(Materials.Tungsten, 1, Materials.Oxygen, 3));
        event.setMaterial(Materials.Wollastonite).mats(of(Materials.Calcium, 1, Materials.SiliconDioxide, 3, Materials.Oxygen, 1)).elecTicks(1460);
        //Nuclear

        /**
         **  Ores
         **/
        event.setMaterial(Materials.Almandine).mats(of(Materials.Alumina, 5, Iron, 3, Materials.SiliconDioxide, 9, Materials.Oxygen, 3)).elecTicks(480);
        event.setMaterial(Materials.Alumina).mats(of(Materials.Aluminium, 2, Materials.Oxygen, 3)).elecTicks(480);
        event.setMaterial(Materials.Andradite).mats(of(Materials.Calcium, 3, Iron, 2, Materials.Silicon, 3, Materials.Oxygen, 12)).elecTicks(480);
        event.setMaterial(Materials.Hematite).mats(of(Iron, 2, Materials.Oxygen, 3)).elecTicks(150);
        event.setMaterial(Materials.Bastnasite).mats(of(Materials.Cerium, 1, Materials.Carbon, 1, Materials.Fluorine, 1, Materials.Oxygen, 3)).elecTicks(192);
        event.setMaterial(Materials.Barite).mats(of(Materials.Barium, 1, Materials.Sulfur, 1, Materials.Oxygen, 4)).elecTicks(204);
        event.setMaterial(Materials.Bentonite).mats(b -> b.put(Materials.Sodium, 1).put(Materials.Magnesium, 6).put(Materials.Silicon, 12).put(Materials.Hydrogen, 6).put(Water, 5).put(Materials.Oxygen, 36)).elecTicks(480); // TODO: Ore Gen
        event.setMaterial(Materials.BrownLimonite).mats(of(Iron, 1, Materials.Hydrogen, 1, Materials.Oxygen, 2));
        event.setMaterial(Materials.Calcite).mats(of(Materials.Calcium, 1, Materials.Carbon, 1, Materials.Oxygen, 3)).elecTicks(100);
        event.setMaterial(Materials.Cassiterite).mats(of(Materials.Tin, 1, Materials.Oxygen, 2), 1).elecTicks(132);
        event.setMaterial(Materials.Chalcopyrite).mats(of(Copper, 1, Iron, 1, Materials.Sulfur, 2)).elecTicks(168);
        event.setMaterial(Materials.Cinnabar).mats(of(Materials.Mercury, 1, Materials.Sulfur, 1));
        event.setMaterial(Materials.Chromite).mats(of(Iron, 1, Materials.Chromium, 2, Materials.Oxygen, 4)).elecTicks(210);
        event.setMaterial(Materials.Cobaltite).mats(of(Materials.Cobalt, 1, Materials.Arsenic, 1, Materials.Sulfur, 1)).elecTicks(150);
        event.setMaterial(Materials.Sheldonite).mats(of(Materials.Platinum, 3, Materials.Nickel, 1, Materials.Sulfur, 1, Materials.Palladium, 1));
        event.setMaterial(Materials.Galena).mats(of(Materials.Lead, 3, Materials.Silver, 3, Materials.Sulfur, 2)).elecTicks(832);
        event.setMaterial(Materials.Garnierite).mats(of(Materials.Nickel, 1, Materials.Oxygen, 1), 1).elecTicks(72);
        event.setMaterial(Materials.Graphite).mats(of(Materials.Carbon, 4), 1).elecTicks(100);
        event.setMaterial(Materials.Glauconite).mats(of(Materials.Alumina, 10, Materials.Potassium, 1, Materials.Magnesium, 2, Water, 3, Materials.Oxygen, 7)).elecTicks(378); // TODO: Ore Gen;
        event.setMaterial(Materials.Grossular).mats(of(Materials.Alumina, 5, Materials.Calcium, 3, Materials.SiliconDioxide, 9, Materials.Oxygen, 3)).elecTicks(440);
        event.setMaterial(Materials.Ilmenite).mats(of(Iron, 1, Materials.Titanium, 1, Materials.Oxygen, 3));
        event.setMaterial(Materials.Lepidolite).mats(of(Materials.Alumina, 10, Materials.Potassium, 1, Materials.Lithium, 3, Materials.Fluorine, 2, Materials.Oxygen, 6)).elecTicks(320); // TODO: Ore Gen;
        event.setMaterial(Materials.Rutile).mats(of(Materials.Cobalt, 1, Materials.Arsenic, 1, Materials.Sulfur, 1));
        event.setMaterial(Materials.Magnesite).mats(of(Materials.Magnesium, 1, Materials.Carbon, 1, Materials.Oxygen, 3)).elecTicks(80);
        event.setMaterial(Materials.Magnetite).mats(of(Iron, 3, Materials.Oxygen, 4)).elecTicks(210);
        event.setMaterial(Materials.Malachite).mats(of(Copper, 2, Materials.Carbon, 1, Materials.Hydrogen, 2, Materials.Oxygen, 5)).elecTicks(200);
        event.setMaterial(Materials.Molybdenite).mats(of(Materials.Molybdenum, 1, Materials.Sulfur, 2)).elecTicks(144);
        event.setMaterial(Materials.Pentlandite).mats(of(Materials.Nickel, 9, Materials.Sulfur, 8)).elecTicks(748);
        event.setMaterial(Materials.Phosphate).mats(of(Materials.Phosphor, 1, Materials.Oxygen, 4)).elecTicks(360);
        event.setMaterial(Materials.Pitchblende).mats(of(Materials.Uraninite, 3, Materials.Thorium, 1, Materials.Lead, 1));
        event.setMaterial(Materials.Pyrite).mats(of(Iron, 1, Materials.Sulfur, 2)).elecTicks(114);
        event.setMaterial(Materials.Pyrolusite).mats(of(Materials.Manganese, 1, Materials.Oxygen, 2)).elecTicks(78);
        event.setMaterial(Materials.Pyrope).mats(of(Materials.Alumina, 5, Materials.Magnesium, 3, Materials.SiliconDioxide, 9, Materials.Oxygen, 3)).elecTicks(400);
        event.setMaterial(Materials.Saltpeter).mats(of(Materials.Potassium, 1, Materials.Nitrogen, 1, Materials.Oxygen, 3)).elecTicks(100);
        event.setMaterial(Materials.Scheelite).mats(of(Materials.Tungsten, 1, Materials.Calcium, 2, Materials.Oxygen, 4)).elecTicks(120);
        event.setMaterial(Materials.Soapstone).mats(of(Materials.Magnesium, 3, Materials.Silicon, 4, Materials.Hydrogen, 2, Materials.Oxygen, 12)).elecTicks(378); // TODO: Ore Gen;
        event.setMaterial(Materials.Spodumene).mats(of(Materials.Lithium, 2, Materials.Alumina, 5, Materials.SiliconDioxide, 12, Materials.Oxygen, 1)).elecTicks(180);
        event.setMaterial(Materials.Sperrylite).mats(of(Materials.Platinum, 1, Materials.Arsenic, 2)).elecTicks(1226);
        event.setMaterial(Materials.Spessartine).mats(of(Materials.Alumina, 5, Materials.Manganese, 3, Materials.SiliconDioxide, 9, Materials.Oxygen, 3)).elecTicks(440);
        event.setMaterial(Materials.Sphalerite).mats(of(Materials.Zinc, 1, Materials.Sulfur, 1)).elecTicks(92);
        event.setMaterial(Materials.Stibnite).mats(of(Materials.Antimony, 2, Materials.Sulfur, 3));
        event.setMaterial(Materials.Tantalite).mats(of(Materials.Manganese, 1, Materials.Tantalum, 2, Materials.Oxygen, 6)).elecTicks(432);
        event.setMaterial(Materials.Talc).mats(of(Materials.Magnesium, 3, Materials.Silicon, 4, Materials.Hydrogen, 2, Materials.Oxygen, 12)).elecTicks(378);
        event.setMaterial(Materials.Tetrahedrite).mats(of(Copper, 3, Materials.Antimony, 1, Materials.Sulfur, 3, Iron, 1));
        event.setMaterial(Materials.Tungstate).mats(of(Materials.Tungsten, 1, Materials.Lithium, 2, Materials.Oxygen, 4)).elecTicks(120);
        event.setMaterial(Materials.Uraninite).mats(of(Materials.Uranium, 1, Materials.Oxygen, 2));
        event.setMaterial(Materials.Uvarovite).mats(of(Materials.Calcium, 3, Materials.Chromium, 2, Materials.Silicon, 3, Materials.Oxygen, 12)).elecTicks(480);
        event.setMaterial(Materials.VanadiumMagnetite).mats(of(Materials.Magnetite, 1, Materials.Vanadium, 1));
        event.setMaterial(Materials.Wulfenite).mats(of(Materials.Lead, 1, Materials.Molybdenum, 1, Materials.Oxygen, 4));
        event.setMaterial(Materials.YellowLimonite).mats(of(Iron, 1, Materials.Hydrogen, 1, Materials.Oxygen, 2));
        event.setMaterial(Materials.Zircon).mats(of(Materials.Zirconium, 1, Materials.SiliconDioxide, 3, Materials.Oxygen, 2)).elecTicks(1740);
        /**
         **  Ore Stones
         **/
        event.setMaterial(Materials.Bauxite).mats(of(Materials.Rutile, 2, Materials.Aluminium, 16, Materials.Hydrogen, 10, Materials.Oxygen, 11));
        event.setMaterial(Materials.Lignite).mats(of(Materials.Carbon, 3, Water, 1)).elecTicks(40);
        event.setMaterial(Materials.RockSalt).mats(of(Materials.Potassium, 1, Materials.Chlorine, 1)).elecTicks(72);
        event.setMaterial(Materials.Salt).mats(of(Materials.Sodium, 1, Materials.Chlorine, 1)).elecTicks(320);
        /**
         **  Gems
         **/
        /**
         *  Regular
         **/
        event.setMaterial(Materials.Amethyst).mats(of(Materials.SiliconDioxide, 4, Iron, 1)).elecTicks(130);
        event.setMaterial(Materials.Sapphire).mats(of(Materials.Alumina, 5, Iron, 1)).elecTicks(100);
        event.setMaterial(Materials.BlueTopaz).mats(of(Materials.Alumina, 5, Materials.SiliconDioxide, 3, Materials.Fluorine, 2, Water, 3)).elecTicks(208);
        event.setMaterial(Materials.Glass).mats(of(Materials.SiliconDioxide, 1));
        event.setMaterial(Materials.GreenSapphire).mats(of(Materials.Alumina, 5, Materials.Magnesium, 1)).elecTicks(100);
        event.setMaterial(Materials.Lazurite).mats(of(Materials.Alumina, 3, Materials.SiliconDioxide, 3, Materials.Calcium, 4, Materials.Sodium, 4)).elecTicks(392);
        event.setMaterial(Materials.Olivine).mats(of(Materials.Magnesium, 2, Iron, 1, Materials.SiliconDioxide, 2)).elecTicks(140);
        event.setMaterial(Materials.Opal).mats(of(Materials.SiliconDioxide, 1)).elecTicks(20);
        event.setMaterial(Materials.TricalciumPhosphate).mats(of(Materials.Calcium, 3, Materials.Phosphate, 2));
        event.setMaterial(Materials.MilkyQuartz).mats(of(Materials.Silicon, 1, Materials.Oxygen, 2)).elecTicks(60);
        event.setMaterial(Materials.RedGarnet).mats(of(Materials.Pyrope, 3, Materials.Almandine, 5, Materials.Spessartine, 8));
        event.setMaterial(Materials.Ruby).mats(of(Materials.Chromium, 1, Materials.Alumina, 5)).elecTicks(144);
        event.setMaterial(Materials.Sodalite).mats(of(Materials.Alumina, 3, Materials.SiliconDioxide, 3, Materials.Sodium, 4, Materials.Chlorine, 1)).elecTicks(264);
        event.setMaterial(Materials.Tanzanite).mats(of(Materials.Alumina, 15, Materials.Calcium, 4, Materials.SiliconDioxide, 18, Water, 3, Materials.Oxygen, 4)).elecTicks(440);
        event.setMaterial(Materials.Topaz).mats(of(Materials.Alumina, 5, Materials.SiliconDioxide, 3, Materials.Fluorine, 2, Water, 3)).elecTicks(208);
        event.setMaterial(Materials.YellowGarnet).mats(of(Materials.Andradite, 5, Materials.Grossular, 8, Materials.Uvarovite, 3));
        /**
         *  Basic
         **/
        event.setMaterial(Charcoal).mats(of(Materials.Carbon, 1)).elecTicks(12);
        event.setMaterial(Materials.CoalCoke).mats(of(Materials.Carbon, 2)).elecTicks(12);
        event.setMaterial(Materials.Apatite).mats(of(Materials.Calcium, 5, Materials.Phosphate, 3, Materials.Chlorine, 1)).elecTicks(288);
        event.setMaterial(Materials.Monazite).mats(of(Materials.RareEarth, 1, Materials.Phosphate, 1)).elecTicks(104);
        /**
         **  Plastic Related
         **/
        event.setMaterial(Materials.EpoxyResin).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 4, Materials.Oxygen, 1));
        event.setMaterial(Materials.FiberReinforcedEpoxyResin).mats(of(Materials.EpoxyResin, 1));
        event.setMaterial(Materials.Plastic).mats(of(Materials.Carbon, 1, Materials.Hydrogen, 2));
        //event.setMaterial(Polystyrene).mats(of(Carbon, 8, Hydrogen, 8));
        event.setMaterial(Materials.Polytetrafluoroethylene).mats(of(Materials.Carbon, 2, Materials.Fluorine, 4));
        event.setMaterial(Materials.PolyvinylChloride).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 3, Materials.Chlorine, 1));
        event.setMaterial(Materials.Rubber).mats(of(Materials.Carbon, 5, Materials.Hydrogen, 8));
        event.setMaterial(Materials.SiliconeRubber).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 6, Materials.Oxygen, 1, Materials.Silicon, 1));
        event.setMaterial(Materials.StyreneButadieneRubber).mats(of(Materials.Styrene, 1, Materials.Butadiene, 3));
        /**
         **  Misc
         **/
        event.setMaterial(Materials.GalliumArsenide).mats(of(Materials.Arsenic, 1, Materials.Gallium, 1));
        /**
         ***  Fluids
         **/
        /**
         *  Organic
         **/
        event.setMaterial(Materials.Acetone).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 6, Materials.Oxygen, 1)).elecTicks(240);
        event.setMaterial(Materials.CharcoalByproducts).mats(of(Materials.WoodTar,1, Materials.WoodGas,1, Materials.WoodVinegar,2));
        event.setMaterial(Materials.WoodTar).mats(of(Materials.Creosote,4, Materials.Phenol,1, Materials.Benzene,2, Materials.Toluene,1));
        //Alkanoles
        event.setMaterial(Materials.Methanol).mats(of(Materials.Carbon, 1, Materials.Hydrogen, 4, Materials.Oxygen, 1)).elecTicks(144);
        event.setMaterial(Materials.Ethanol).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 6, Materials.Oxygen, 1)).elecTicks(144);
        event.setMaterial(Materials.Propanol).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 8, Materials.Oxygen, 1));
        event.setMaterial(Materials.Butanol).mats(of(Materials.Carbon, 4, Materials.Hydrogen, 10, Materials.Oxygen, 1));
        event.setMaterial(Materials.Heptanol).mats(of(Materials.Carbon, 7, Materials.Hydrogen, 16, Materials.Oxygen, 1));
        //Alkenoles
        event.setMaterial(Materials.Ethenol).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 4, Materials.Oxygen, 1));
        event.setMaterial(Materials.Propenol).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 6, Materials.Oxygen, 1));
        event.setMaterial(Materials.Butenol).mats(of(Materials.Carbon, 4, Materials.Hydrogen, 8, Materials.Oxygen, 1));
        //Alkanedioles
        event.setMaterial(Materials.Ethanediol).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 6, Materials.Oxygen, 2));
        event.setMaterial(Materials.Propanediol).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 8, Materials.Oxygen, 2));
        event.setMaterial(Materials.Butanediol).mats(of(Materials.Carbon, 4, Materials.Hydrogen, 10, Materials.Oxygen, 2));
        //Misc
        event.setMaterial(Materials.AceticAcid).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 4, Materials.Oxygen, 2)).elecTicks(128);
        event.setMaterial(Materials.AllylChloride).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 5, Materials.Chlorine, 1)).elecTicks(288);
        event.setMaterial(Materials.Benzaldehyde).mats(of(Materials.Carbon,7, Materials.Hydrogen,6, Materials.Oxygen,1));
        event.setMaterial(Materials.Benzene).mats(of(Materials.Carbon, 6, Materials.Hydrogen, 6)).elecTicks(48);
        event.setMaterial(Materials.BenzoylChloride).mats(of(Materials.Carbon,7, Materials.Hydrogen,5, Materials.Chlorine,1, Materials.Oxygen,1));
        event.setMaterial(Materials.BisphenolA).mats(of(Materials.Carbon, 15, Materials.Hydrogen, 16, Materials.Oxygen, 2));
        event.setMaterial(Materials.Chloramine).mats(of(Materials.Nitrogen, 1, Materials.Hydrogen, 2, Materials.Chlorine, 1)).elecTicks(192);
        event.setMaterial(Materials.Chloroform).mats(of(Materials.Carbon, 1, Materials.Hydrogen, 1, Materials.Chlorine, 3)).elecTicks(440);
        event.setMaterial(Materials.Cumene).mats(of(Materials.Carbon, 9, Materials.Hydrogen, 12));
        event.setMaterial(Materials.Chlorobenzene).mats(of(Materials.Carbon, 6, Materials.Hydrogen, 5, Materials.Chlorine, 1)).elecTicks(384);
        event.setMaterial(Materials.Dichloroethane).mats(of(Materials.Carbon,2, Materials.Hydrogen,4, Materials.Chlorine,2));
        event.setMaterial(Materials.Dimethyldichlorosilane).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 6, Materials.Chlorine, 2, Materials.Silicon, 1)).elecTicks(528);
        event.setMaterial(Materials.Dimethylhydrazine).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 8, Materials.Nitrogen, 2));
        event.setMaterial(Materials.Epichlorohydrin).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 5, Materials.Chlorine, 1, Materials.Oxygen, 1)).elecTicks(320);
        event.setMaterial(Materials.Glycerol).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 8, Materials.Oxygen, 3)).elecTicks(336);
        event.setMaterial(Materials.GlycerylTrinitrate).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 5, Materials.Nitrogen, 3, Materials.Oxygen, 9)).elecTicks(800);
        event.setMaterial(Materials.Isoprene).mats(of(Materials.Carbon, 8, Materials.Hydrogen, 8)).elecTicks(208);
        event.setMaterial(Materials.MethylAcetate).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 6, Materials.Oxygen, 2)).elecTicks(264);
        event.setMaterial(Materials.Naphtha).mats(of(Materials.Carbon,8, Materials.Hydrogen,16));
        event.setMaterial(Materials.Phenol).mats(of(Materials.Carbon, 6, Materials.Hydrogen, 6, Materials.Oxygen, 1)).elecTicks(312);
        event.setMaterial(Materials.PolyvinylAcetate).mats(of(Materials.Carbon, 4, Materials.Hydrogen, 6, Materials.Oxygen, 2));
        event.setMaterial(Materials.Styrene).mats(of(Materials.Carbon, 8, Materials.Hydrogen, 8)).elecTicks(48);
        event.setMaterial(Materials.SulfuricNaphtha).mats(of(Materials.Naphtha, 1, Materials.HydrogenSulfide,1));
        event.setMaterial(Materials.Toluene).mats(of(Materials.Carbon, 7, Materials.Hydrogen, 8)).elecTicks(360);
        event.setMaterial(Materials.VinylAcetate).mats(of(Materials.Carbon, 4, Materials.Hydrogen, 6, Materials.Oxygen, 2)).elecTicks(144);
        /**
         *  Inorganic
         **/
        event.setMaterial(Materials.BlueVitriol).mats(of(Copper,1, Materials.Sulfur,1, Materials.Oxygen,4));
        event.setMaterial(Materials.GreenVitriol).mats(of(Iron,1, Materials.Sulfur,1, Materials.Oxygen,4));
        event.setMaterial(Materials.RedVitriol).mats(of(Materials.Cobalt,1, Materials.Sulfur,1, Materials.Oxygen,4));
        event.setMaterial(Materials.PinkVitriol).mats(of(Materials.Magnesium,1, Materials.Sulfur,1, Materials.Oxygen,4));
        event.setMaterial(Materials.CyanVitriol).mats(of(Materials.Nickel,1, Materials.Sulfur,1, Materials.Oxygen,4));
        event.setMaterial(Materials.WhiteVitriol).mats(of(Materials.Zinc,1, Materials.Sulfur,1, Materials.Oxygen,4));
        event.setMaterial(Materials.GrayVitriol).mats(of(Materials.Manganese,1, Materials.Sulfur,1, Materials.Oxygen,4));
        event.setMaterial(Materials.VitriolOfClay).mats(of(Materials.Alumina, 1, Materials.Sulfur, 3, Materials.Oxygen, 8));
        event.setMaterial(Materials.CalciumAcetateSolution).mats(of(Materials.Calcium, 1, Materials.Carbon, 2, Materials.Oxygen, 4, Materials.Hydrogen, 6)).elecTicks(520);
        event.setMaterial(Materials.DistilledWater).mats(of(Materials.Hydrogen,2, Materials.Oxygen, 1)).elecTicks(2000);
        event.setMaterial(Materials.DilutedHydrochloricAcid).mats(of(Materials.Hydrogen, 1, Materials.Chlorine, 1));
        event.setMaterial(Materials.DilutedSulfuricAcid).mats(of(Materials.Hydrogen,2, Materials.Sulfur,1, Materials.Oxygen,4));
        event.setMaterial(Materials.HeavyWater).mats(of(Materials.Deuterium, 2, Materials.Oxygen, 1)).elecTicks(2000);
        event.setMaterial(Materials.SemiheavyWater).mats(of(Materials.Hydrogen, 1, Materials.Deuterium, 1, Materials.Oxygen, 1)).elecTicks(2000);
        event.setMaterial(Materials.TritiatedWater).mats(of(Materials.Tritanium, 2, Materials.Oxygen, 1)).elecTicks(2000);
        event.setMaterial(Materials.HotHeavyWater).mats(of(Materials.Deuterium, 2, Materials.Oxygen, 1));
        event.setMaterial(Materials.HotSemiheavyWater).mats(of(Materials.Hydrogen, 1, Materials.Deuterium, 1, Materials.Oxygen, 1));
        event.setMaterial(Materials.HotTritiatedWater).mats(of(Materials.Tritanium, 2, Materials.Oxygen, 1));
        event.setMaterial(Materials.HotMoltenLithiumChloride).mats(of(Materials.LithiumChloride, 1));
        event.setMaterial(Materials.HydrochloricAcid).mats(of(Materials.Hydrogen, 1, Materials.Chlorine, 1)).elecTicks(720);
        event.setMaterial(Materials.HydrofluoricAcid).mats(of(Materials.Hydrogen, 1, Materials.Fluorine, 1)).elecTicks(80);
        event.setMaterial(Materials.HydrogenFluoride).mats(of(Materials.Hydrogen, 1, Materials.Fluorine, 1)).elecTicks(80);
        event.setMaterial(Materials.HydrogenPeroxide).mats(of(Materials.Hydrogen,2, Materials.Oxygen,2));
        event.setMaterial(Materials.HypochlorousAcid).mats(of(Materials.Hydrogen, 1, Materials.Chlorine, 1, Materials.Oxygen, 1)).elecTicks(192);

        event.setMaterial(Materials.NickelSulfate).mats(of(Materials.Nickel,1, Materials.Sulfur,1, Materials.Oxygen,4)).elecTicks(900);
        event.setMaterial(Materials.NitricAcid).mats(of(Materials.Hydrogen, 1, Materials.Nitrogen, 1, Materials.Oxygen, 3)).elecTicks(240);
        event.setMaterial(Materials.PeroxydisulfuricAcid).mats(of(Materials.Sulfur,2, Materials.Hydrogen,2, Materials.Oxygen,8));
        event.setMaterial(Materials.PhosphoricAcid).mats(of(Materials.Hydrogen, 3, Materials.Phosphor, 1, Materials.Oxygen, 4)).elecTicks(392);
        event.setMaterial(Materials.SaltWater).mats(of(Materials.Hydrogen,1, Materials.SodiumHydroxide,3, Materials.Chlorine,1), 8).elecTicks(720);
        event.setMaterial(Materials.Steam).mats(of(Water,1));
        event.setMaterial(Materials.SuperheatedSteam).mats(of(Materials.Steam, 1));
        event.setMaterial(Materials.SulfuricAcid).mats(of(Materials.Hydrogen, 2, Materials.Sulfur, 1, Materials.Oxygen, 4)).elecTicks(392);
        event.setMaterial(Materials.SulfurTrioxide).mats(of(Materials.Sulfur, 1, Materials.Oxygen, 3)).elecTicks(320);
        event.setMaterial(Materials.SulfurDioxide).mats(of(Materials.Sulfur, 1, Materials.Oxygen, 2)).elecTicks(300);
        event.setMaterial(Materials.TitaniumTetrachloride).mats(of(Materials.Titanium, 1, Materials.Chlorine, 4));
        /**
         ***  Gases/Plasmas
         **/
        /**
         *  Organic
         **/
        //Alkanes
        event.setMaterial(Materials.Methane).mats(of(Materials.Carbon, 1, Materials.Hydrogen, 4)).elecTicks(80);
        event.setMaterial(Materials.Ethane).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 6)).elecTicks(64);
        event.setMaterial(Materials.Propane).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 6)).elecTicks(196);
        event.setMaterial(Materials.Butane).mats(of(Materials.Carbon, 4, Materials.Hydrogen, 10)).elecTicks(112);
        //Alkenes
        event.setMaterial(Materials.Ethylene).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 4)).elecTicks(48);
        event.setMaterial(Materials.Propene).mats(of(Materials.Carbon, 3, Materials.Hydrogen, 6)).elecTicks(48);
        event.setMaterial(Materials.Butene).mats(of(Materials.Carbon, 4, Materials.Hydrogen, 8)).elecTicks(48);
        event.setMaterial(Materials.Butadiene).mats(of(Materials.Carbon, 4, Materials.Hydrogen, 6)).elecTicks(120);
        //Misc
        event.setMaterial(Materials.CarbonDioxide).mats(of(Materials.Carbon, 1, Materials.Oxygen, 2)).elecTicks(300);
        event.setMaterial(Materials.CarbonMonoxide).mats(of(Materials.Carbon, 1, Materials.Oxygen, 1)).elecTicks(112);
        event.setMaterial(Materials.Chloromethane).mats(of(Materials.Carbon, 1, Materials.Hydrogen, 3, Materials.Chlorine, 1)).elecTicks(200);
        event.setMaterial(Materials.Dimethylamine).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 7, Materials.Nitrogen, 1)).elecTicks(160);
        event.setMaterial(Materials.NaturalGas).mats(of(Materials.Methane,4, Materials.Ethane,2, Materials.Propane,2, Materials.Butane,1));
        event.setMaterial(Materials.RefineryGas).asGas(128);
        event.setMaterial(Materials.SulfuricGas).mats(of(Materials.NaturalGas,1, Materials.Sulfur,1));
        event.setMaterial(Materials.Tetrafluoroethylene).mats(of(Materials.Carbon, 2, Materials.Fluorine, 4));
        event.setMaterial(Materials.VinylChloride).mats(of(Materials.Carbon, 2, Materials.Hydrogen, 3, Materials.Chlorine, 1)).elecTicks(240);
        event.setMaterial(Materials.WoodGas).mats(of(Materials.CarbonDioxide,8, Materials.CarbonMonoxide,4, Materials.Methane,2, Materials.Ethylene,1, Materials.Hydrogen,1));
        /**
         *  Inorganic
         **/
        //event.setMaterial(Air).mats(of(Nitrogen, 40, Oxygen, 11, Argon, 1, NobleGases, 1 ));
        event.setMaterial(Materials.Ammonia).mats(of(Materials.Nitrogen, 1, Materials.Hydrogen, 3)).elecTicks(64);
        event.setMaterial(Materials.DinitrogenTetroxide).mats(of(Materials.Nitrogen, 2, Materials.Oxygen, 4)).elecTicks(168);
        event.setMaterial(Materials.HydrogenSulfide).mats(of(Materials.Hydrogen, 2, Materials.Sulfur, 1));
        event.setMaterial(Materials.NitrogenMonoxide).mats(of(Materials.Nitrogen, 1, Materials.Oxygen, 1)).elecTicks(112);
        event.setMaterial(Materials.NitrogenDioxide).mats(of(Materials.Nitrogen, 1, Materials.Oxygen, 2)).elecTicks(168);
        event.setMaterial(Materials.SulfurDioxide).mats(of(Materials.Sulfur, 1, Materials.Oxygen, 2));
        event.setMaterial(Materials.SulfurTrioxide).mats(of(Materials.Sulfur, 1, Materials.Oxygen, 3));
        /**
         ** Cracked Stuff
         */
        event.setMaterial(Materials.SteamCrackedEthane).mats(of(Materials.Ethane,1, Materials.Steam,1));
        event.setMaterial(Materials.SteamCrackedPropane).mats(of(Materials.Propane,1, Materials.Steam,1));
        event.setMaterial(Materials.SteamCrackedButane).mats(of(Materials.Butane,1, Materials.Steam,1));
        event.setMaterial(Materials.SteamCrackedNaphtha).mats(of(Materials.Naphtha,1, Materials.Steam,1));
        event.setMaterial(Materials.SteamCrackedRefineryGas).mats(of(Materials.RefineryGas,1, Materials.Steam,1));
        event.setMaterial(Materials.HydroCrackedEthane).mats(of(Materials.Ethane,1, Materials.Hydrogen,1));
        event.setMaterial(Materials.HydroCrackedPropane).mats(of(Materials.Propane,1, Materials.Hydrogen,1));
        event.setMaterial(Materials.HydroCrackedButane).mats(of(Materials.Butane,1, Materials.Hydrogen,2));
        event.setMaterial(Materials.HydroCrackedNaphtha).mats(of(Materials.Naphtha,1, Materials.Hydrogen,2));
        event.setMaterial(Materials.HydroCrackedRefineryGas).mats(of(Materials.RefineryGas,1, Materials.Hydrogen,2));
    }

    private static void toolsAndArmor(GregTechMaterialEvent event){
        /**
         ** Periodic Elements
         */
        event.setMaterial(Materials.Beryllium).tool().toolDamage(2).toolSpeed(14).toolDurability(64).toolQuality(2).build();
        event.setMaterial(Materials.Titanium).tool().toolDamage(3).toolSpeed(8).toolDurability(2560).toolQuality(3).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build();
        event.setMaterial(Materials.Chromium).tool().toolDamage(3).toolSpeed(11).toolDurability(256).toolQuality(3).build();
        event.setMaterial(Materials.Manganese).tool().toolDamage(2).toolSpeed(7).toolDurability(256).toolQuality(2).build();
        event.setMaterial(Materials.Cobalt).tool().toolDamage(3).toolSpeed(5).toolDurability(256).toolQuality(3).build();
        event.setMaterial(Materials.Nickel).tool().toolDamage(2).toolSpeed(6).toolDurability(64).toolQuality(2).toolEnchantments(of(Enchantments.BANE_OF_ARTHROPODS, 2)).build().harvestLevel(2);
        event.setMaterial(Materials.Zirconium).tool().toolDamage(3).toolSpeed(6).toolDurability(512).toolQuality(3).build();
        event.setMaterial(Materials.Molybdenum).tool().toolDamage(2).toolSpeed(7).toolDurability(512).toolQuality(2).build().harvestLevel(2);
        event.setMaterial(Materials.Technetium).tool().toolDamage(1).toolSpeed(10).toolDurability(1280).toolQuality(1).build();
        event.setMaterial(Materials.Palladium).tool().toolDamage(2).toolSpeed(8).toolDurability(512).toolQuality(2).build().harvestLevel(2);
        event.setMaterial(Materials.Neodymium).tool().toolDamage(3).toolSpeed(6).toolDurability(512).toolQuality(3).build();
        event.setMaterial(Materials.Tungsten).tool().toolDamage(3).toolSpeed(8).toolDurability(5120).toolQuality(3).build(); // TODO: stats like non burnable
        event.setMaterial(Materials.Osmium).tool().toolDamage(4).toolSpeed(16).toolDurability(1280).toolQuality(4).build();
        event.setMaterial(Materials.Iridium).tool().toolDamage(4).toolSpeed(6).toolDurability(5120).toolQuality(4).build();
        event.setMaterial(Materials.Platinum).tool().toolDamage(2).toolSpeed(15).toolDurability(64).toolQuality(2).toolEnchantments(of(Enchantments.SMITE, 5)).build().harvestLevel(2);
        event.setMaterial(Materials.Thorium).tool().toolDamage(2).toolSpeed(6).toolDurability(512).toolQuality(2).build().harvestLevel(2);
        event.setMaterial(Materials.Uranium).tool().toolDamage(3).toolSpeed(6).toolDurability(512).toolQuality(3).build();
        event.setMaterial(Materials.Plutonium).tool().toolDamage(3).toolSpeed(6).toolDurability(512).toolQuality(3).build(); // TODO: Enchantment: Radioactivity;

        /**
         **  Metals
         **/
        event.setMaterial(Materials.BismuthBronze).tool().toolDamage(2).toolSpeed(8).toolDurability(512).toolQuality(2).toolEnchantments(of(Enchantments.BANE_OF_ARTHROPODS, 4)).build()
                .addArmor(new int[]{2, 6, 5, 2}, 0.0f, 0.0f, 16);
        event.setMaterial(Materials.BlackBronze).tool().toolDamage(2).toolSpeed(12).toolDurability(512).toolQuality(2).toolEnchantments(of(Enchantments.SMITE, 2)).build()
                .addArmor(new int[]{2, 6, 5, 2}, 0.0f, 0.0f, 16);
        event.setMaterial(Materials.BlackSteel).tool().toolDamage(2).toolSpeed(6.5f).toolDurability(768).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build()
                .addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 32, of(Enchantments.ALL_DAMAGE_PROTECTION, 2));
        event.setMaterial(Materials.BlueSteel).tool().toolDamage(2).toolSpeed(7).toolDurability(896).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build()
                .addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 37, of(Enchantments.ALL_DAMAGE_PROTECTION, 3));
        event.setMaterial(Materials.Bronze).tool().toolDamage(2).toolSpeed(6).toolDurability(448).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 1)).build()
                .addArmor(new int[]{2, 6, 5, 2}, 0.0F, 0.0F, 12);
        event.setMaterial(Materials.CobaltBrass).tool().toolDamage(2).toolSpeed(8).toolDurability(256).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 2)).build();
        //event.setMaterial(Duranium).addHandleStat(620, -1.0F, of(Enchantments.SILK_TOUCH, 1))
                //.addTools(6.5F, 16.0F, 5120, 5);
        event.setMaterial(Materials.Electrum).tool().toolDamage(2).toolSpeed(12).toolDurability(64).toolQuality(2).toolEnchantments(of(Enchantments.SMITE, 3)).build();
        event.setMaterial(Materials.EnrichedNaquadah).tool().toolDamage(4).toolSpeed(6).toolDurability(1280).toolQuality(4).build();
        event.setMaterial(Materials.HSSE).tool().toolDamage(4).toolSpeed(10).toolDurability(5120).toolQuality(4).toolEnchantments(of(Enchantments.SHARPNESS, 4)).build();
        event.setMaterial(Materials.HSSG).tool().toolDamage(3).toolSpeed(10).toolDurability(4000).toolQuality(3).toolEnchantments(of(Enchantments.SHARPNESS, 4)).build();
        event.setMaterial(Materials.HSSS).tool().toolDamage(4).toolSpeed(14).toolDurability(3000).toolQuality(4).toolEnchantments(of(Enchantments.SHARPNESS, 4)).build();
        event.setMaterial(Materials.Invar).tool().toolDamage(2).toolSpeed(6).toolDurability(256).toolQuality(3).toolEnchantments(of(Enchantments.BANE_OF_ARTHROPODS, 3)).build()
                .addArmor(new int[]{2, 6, 5, 2}, 0.0F, 0.0F, 15, of(Enchantments.FIRE_PROTECTION, 1));
        event.setMaterial(Materials.IronMagnetic).tool(Iron).toolEnchantments(of(Enchantments.SHARPNESS, 1)).build();
        event.setMaterial(Materials.Kanthal).tool().toolDamage(2).toolSpeed(6).toolDurability(64).toolQuality(2).build();
        event.setMaterial(Materials.Neutronium).tool().toolDamage(9).toolSpeed(24).toolDurability(655360).toolQuality(6).build();
        event.setMaterial(Materials.Naquadah).tool().toolDamage(4).toolSpeed(6).toolDurability(1280).toolQuality(4).build();
        event.setMaterial(Materials.NaquadahAlloy).tool().toolDamage(4.5f).toolSpeed(8).toolDurability(5120).toolQuality(4).build();
        event.setMaterial(Materials.Nichrome).tool().toolDamage(2).toolSpeed(6).toolDurability(64).toolQuality(2).toolEnchantments(of(Enchantments.BANE_OF_ARTHROPODS, 2)).build();
        event.setMaterial(Materials.NickelZincFerrite).tool().toolDamage(0).toolSpeed(3).toolDurability(32).toolQuality(1).build();
        event.setMaterial(Materials.Osmiridium).tool().toolDamage(4).toolSpeed(11).toolDurability(3840).toolQuality(4).build();
        event.setMaterial(Materials.RedSteel).tool().toolDamage(2).toolSpeed(7.5f).toolDurability(1024).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build()
                .addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 42, of(Enchantments.ALL_DAMAGE_PROTECTION, 3));
        event.setMaterial(Materials.RoseGold).tool().toolDamage(2).toolSpeed(14).toolDurability(128).toolQuality(2).toolEnchantments(of(Enchantments.SMITE, 4)).build();
        event.setMaterial(Materials.Steel).tool(Iron).toolDurability(512).toolEnchantments(of(Enchantments.SHARPNESS, 2)).build()
                .addArmor(new int[]{2, 7, 6, 2}, 1.0F, 0.0F, 21, of(Enchantments.ALL_DAMAGE_PROTECTION, 1));
        event.setMaterial(Materials.SteelMagnetic).tool(Materials.Steel).toolEnchantments(of(Enchantments.SHARPNESS, 2)).build();
        event.setMaterial(Materials.SterlingSilver).tool().toolDamage(2).toolSpeed(13).toolDurability(128).toolQuality(2).build();
        event.setMaterial(Materials.StainlessSteel).tool().toolDamage(2).toolSpeed(7).toolDurability(480).toolQuality(2).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build();
        event.setMaterial(Materials.Tritanium).tool().toolDamage(9).toolSpeed(15).toolDurability(9400).toolQuality(6).build();
        event.setMaterial(Materials.TungstenCarbide).tool().toolDamage(4).toolSpeed(10).toolDurability(5120).toolQuality(4).build();
        event.setMaterial(Materials.TungstenSteel).tool(Materials.TungstenCarbide).toolEnchantments(of(Enchantments.SHARPNESS, 4)).build();
        event.setMaterial(Materials.VanadiumSteel).tool().toolDamage(3).toolSpeed(7).toolDurability(512).toolQuality(3).toolEnchantments(of(Enchantments.SHARPNESS, 3)).build();
        event.setMaterial(Materials.Vibranium).tool().toolDamage(15).toolSpeed(1000).toolDurability(512).toolQuality(15).toolEnchantments(of(Enchantments.BLOCK_FORTUNE, 5, Enchantments.MOB_LOOTING, 10)).build();
        event.setMaterial(Materials.WroughtIron).tool(Iron).toolDurability(384).build()
                .addArmor(new int[]{2, 6, 5, 2}, 1.0F, 0.0F, 17, of(Enchantments.ALL_DAMAGE_PROTECTION, 1));
        /**
         **  Gems
         **/
        /**
         *  Regular
         **/
        event.setMaterial(Materials.Amber).tool().toolDamage(3).toolSpeed(4).toolDurability(256).toolQuality(3).toolEnchantments(of(Enchantments.SILK_TOUCH, 1)).build();
        event.setMaterial(Materials.Amethyst).tool().toolDamage(3).toolSpeed(7).toolDurability(256).toolQuality(3).toolEnchantments(of(Data.IMPLOSION, 3)).build()
                .addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30);
        event.setMaterial(Materials.Sapphire).tool().toolDamage(3).toolSpeed(7).toolDurability(512).toolQuality(3).toolEnchantments(of(Data.IMPLOSION, 3)).build()
                .addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30).harvestLevel(2);
        event.setMaterial(Materials.BlueTopaz).tool().toolDamage(3).toolSpeed(7).toolDurability(256).toolQuality(3).toolEnchantments(of(Data.IMPLOSION, 5)).build();
        event.setMaterial(Materials.GreenSapphire).tool(Materials.Sapphire).toolEnchantments(of(Data.IMPLOSION, 3)).build()
                .addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30)
                .harvestLevel(2);
        event.setMaterial(Materials.Jade).tool().toolDamage(2).toolSpeed(8).toolDurability(512).toolQuality(2).toolEnchantments(of(Enchantments.BLOCK_FORTUNE, 3, Enchantments.MOB_LOOTING, 6, Data.IMPLOSION, 3)).build();
        event.setMaterial(Materials.Olivine).tool().toolDamage(2).toolSpeed(7).toolDurability(256).toolQuality(2).toolEnchantments(of(Data.IMPLOSION, 2)).build().harvestLevel(2);
        event.setMaterial(Materials.Opal).tool(Materials.Olivine).toolEnchantments(of(Data.IMPLOSION, 4)).build();
        event.setMaterial(Materials.Ruby).tool(Materials.Sapphire).toolEnchantments(of(Data.IMPLOSION, 3)).build()
                .addArmor(new int[]{3, 7, 7, 3}, 1.0F, 0.0F, 30);
        event.setMaterial(Materials.Tanzanite).tool(Materials.Olivine).toolEnchantments(of(Data.IMPLOSION, 4)).build();
        event.setMaterial(Materials.Topaz).tool(Materials.BlueTopaz).toolEnchantments(of(Data.IMPLOSION, 5)).build();
        /**
         *  Basic
         **/
        event.setMaterial(Materials.CertusQuartz).tool(Iron).toolDurability(256).build();
        event.setMaterial(Materials.Fluix).tool(Iron).toolDamage(2.4f).toolDurability(768).toolEnchantments(of(Enchantments.MOB_LOOTING, 1, Enchantments.BLOCK_FORTUNE, 1)).build();
        /**
         **  Plastic Related
         **/
        event.setMaterial(Materials.Rubber).tool().toolDamage(-1).toolSpeed(0.15f).toolDurability(1024).toolEnchantments(of(Enchantments.KNOCKBACK, 2)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(Materials.EpoxyResin).tool().toolSpeed(2.25f).toolDurability(256).toolQuality(1).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(Materials.Plastic).tool().toolSpeed(0.3f).toolDurability(2048).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 1)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(Materials.PolyvinylChloride).tool().toolSpeed(0.3f).toolDurability(2048).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 1)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(Materials.SiliconeRubber).tool().toolSpeed(0.3f).toolDurability(1024).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 2)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
        event.setMaterial(Materials.StyreneButadieneRubber).tool().toolSpeed(0.3f).toolDurability(1536).toolQuality(1).toolEnchantments(of(Enchantments.KNOCKBACK, 2)).allowedToolTypes(List.of(AntimatterDefaultTools.SOFT_HAMMER)).build();
    }


    private static void nuclearIsotopes(GregTechMaterialEvent event){
        /**
         *** Isotopes (Solids)
         **/
        event.setMaterial(Materials.Cobalt60).asMetal(1768, ROD);
        event.setMaterial(Materials.Thorium230).asMetal(2028, 0, ROD);
        event.setMaterial(Materials.Uranium233).asMetal(1406, ROD);
        event.setMaterial(Materials.Uranium235).asMetal(1406, ROD);
        event.setMaterial(Materials.Plutonium239).asMetal(912, ROD);
        event.setMaterial(Materials.Plutonium241).asMetal(912, ROD);
        event.setMaterial(Materials.Plutonium243).asMetal(912, ROD);
        event.setMaterial(Materials.Americium241).asMetal(1449, ROD);
        event.setMaterial(Materials.Americium242).asMetal(1449, ROD);
        /**
         *** Isotopes (Fluids)
         **/
        /**
         *** Isotopes (Gases/Plasmas)
         **/
        event.setMaterial(Materials.HotHelium).asGas(0, 1150).fluidDensity(1000);
        event.setMaterial(Materials.Deuterium).asGas().fluidDensity(-1112);
        event.setMaterial(Materials.Tritium).asGas().fluidDensity(-1112);
        event.setMaterial(Materials.Helium3).asGas().fluidDensity(-560);
        /**
         * Tetrafluorides
         */
        event.setMaterial(Materials.UraniumTetrafluoride).asDust().mats(of(Materials.Uranium,1, Materials.Fluorine,4));
        event.setMaterial(Materials.Uranium235Tetrafluoride).asDust().mats(of(Materials.Uranium235, 1, Materials.Fluorine, 4));
        event.setMaterial(Materials.Uranium238Tetrafluoride).asDust().mats(of(Materials.Uranium, 1, Materials.Fluorine, 4));
        /**
         * Hexafluorides
         */
        event.setMaterial(Materials.UraniumHexafluoride).asGas().fluidDensity(2708);
        event.setMaterial(Materials.Uranium238Hexafluoride).asGas().mats(of(Materials.Uranium,1, Materials.Fluorine,6)).fluidDensity(2708);
        event.setMaterial(Materials.Uranium235Hexafluoride).asGas().mats(of(Materials.Uranium235,1, Materials.Fluorine,6)).fluidDensity(2708);
    }


    private static void workbenches(GregTechMaterialEvent event){
        METAL.all().forEach(m -> {
            if ((m.getElement() == null || !m.getElement().isIsotope) && m.has(PLATE) && m.has(ROD)){

                GTCoreBlocks.createWorkbench(m, false);
                GTCoreBlocks.createWorkbench(m, true);
                GTCoreBlocks.createLocker(m, false);
                GTCoreBlocks.createLocker(m, true);
                if (m != GTCoreMaterials.Ironwood) {
                    GTCoreBlocks.createMassStorage(m, 1000000);
                }
                GTCoreBlocks.createChest(m, true);
                m.flags(RING, SCREW);
                GTCoreBlocks.createBarrel(m, true);
            }
        });
    }

    public static void byproducts(GregTechMaterialEvent event){
        event.setMaterial(Materials.Almandine).addByProduct(Materials.RedGarnet, Materials.Alumina);
        event.setMaterial(Materials.Alumina).addByProduct(Materials.Bauxite);
        //event.setMaterial(Amber).addByProduct(Amber); TODO: Add Amber
        event.setMaterial(Materials.Amethyst).addByProduct(Materials.Amethyst);
        event.setMaterial(Materials.Monazite).addByProduct(Materials.Thorium, Materials.Neodymium, Materials.RareEarth);
        event.setMaterial(Materials.Apatite).addByProduct(Materials.TricalciumPhosphate, Materials.Fluorite);
        event.setMaterial(Materials.Andradite).addByProduct(Materials.YellowGarnet, Iron, Materials.Boron);
        event.setMaterial(Materials.Antimony).addByProduct(Materials.Zinc, Iron);
        event.setMaterial(Materials.Ash).addByProduct(Materials.Carbon);
        event.setMaterial(Basalt).addByProduct(Materials.Olivine, Materials.DarkAsh);
        event.setMaterial(Materials.Bastnasite).addByProduct(Materials.Neodymium, Materials.RareEarth);
        event.setMaterial(Materials.Bauxite).addByProduct(Materials.Grossular, Materials.Rutile, Materials.Gallium);
        event.setMaterial(Materials.Bentonite).addByProduct(Materials.Alumina, Materials.Calcium, Materials.Magnesium).elecTicks(480);
        event.setMaterial(Materials.Beryllium).addByProduct(Emerald);
        event.setMaterial(Materials.BlackGranite).addByProduct(Materials.Biotite);
        event.setMaterial(Materials.Sapphire).addByProduct(Materials.Alumina);
        event.setMaterial(Materials.GreenSapphire).addByProduct(Materials.Alumina);
        event.setMaterial(Materials.Brass).addByProduct(Copper, Materials.Tin);
        event.setMaterial(Materials.Bronze).addByProduct(Copper, Materials.Zinc);
        event.setMaterial(Materials.BrownLimonite).addByProduct(Materials.Malachite, Materials.YellowLimonite);
        event.setMaterial(Materials.Calcite).addByProduct(Materials.Andradite, Materials.Malachite);
        event.setMaterial(Materials.Cassiterite).addByProduct(Materials.Tin);
        event.setMaterial(Materials.Chalcopyrite).addByProduct(Materials.Pyrite, Materials.Cobalt, Materials.Cadmium).bathMercury(Gold).bathPersulfate(Materials.Cobalt);
        event.setMaterial(Materials.Chromium).addByProduct(Iron, Materials.Magnesium);
        event.setMaterial(Materials.Chromite).addByProduct(Iron, Materials.Magnesium);
        event.setMaterial(Materials.Cinnabar).addByProduct(Redstone, Materials.Sulfur, Glowstone);
        event.setMaterial(Materials.Clay).addByProduct(Materials.Clay);
        event.setMaterial(Coal).addByProduct(Materials.Lignite, Materials.Thorium);
        event.setMaterial(Materials.Cobalt).addByProduct(Materials.Cobaltite);
        event.setMaterial(Materials.Cobaltite).addByProduct(Materials.Cobalt, Materials.Cobalt);
        event.setMaterial(Copper).addByProduct(Materials.Cobalt, Gold, Materials.Nickel).bathMercury(Gold).bathPersulfate(Copper);
        event.setMaterial(Materials.DarkAsh).addByProduct(Materials.Carbon);
        event.setMaterial(Diamond).addByProduct(Materials.Graphite);
        event.setMaterial(Materials.Electrum).addByProduct(Gold, Materials.Silver);
        event.setMaterial(Emerald).addByProduct(Materials.Beryllium, Materials.Alumina);
        event.setMaterial(Endstone).addByProduct(Materials.Helium3);
        event.setMaterial(Materials.EnrichedNaquadah).addByProduct(Materials.Naquadah, Materials.Naquadria);
        event.setMaterial(Flint).addByProduct(Materials.Obsidian);
        event.setMaterial(Materials.Galena).addByProduct(Materials.Sulfur, Materials.Silver, Materials.Lead).bathMercury(Materials.Silver);
        event.setMaterial(Materials.Garnierite).addByProduct(Materials.Nickel).bathPersulfate(Materials.Nickel);
        event.setMaterial(Materials.Glauconite).addByProduct(Materials.Sodium, Materials.Alumina, Iron);
        event.setMaterial(Glowstone).addByProduct(Redstone, Gold);
        event.setMaterial(Gold).addByProduct(Copper, Materials.Nickel).bathMercury(Gold).bathPersulfate(Copper);
        event.setMaterial(Materials.Graphite).addByProduct(Materials.Carbon);
        event.setMaterial(Materials.Grossular).addByProduct(Materials.YellowGarnet, Materials.Calcium);
        event.setMaterial(Materials.Ilmenite).addByProduct(Iron, Materials.Rutile);
        event.setMaterial(Materials.Iridium).addByProduct(Materials.Platinum, Materials.Osmium).bathMercury(Materials.Platinum);
        event.setMaterial(Iron).addByProduct(Materials.Nickel, Materials.Tin).bathPersulfate(Materials.Nickel);
        event.setMaterial(Lapis).addByProduct(Materials.Calcite, Materials.Pyrite);
        event.setMaterial(Materials.Lead).addByProduct(Materials.Silver, Materials.Sulfur).bathMercury(Materials.Silver);
        event.setMaterial(Materials.Lepidolite).addByProduct(Materials.Lithium, Materials.Caesium, Materials.Boron);
        event.setMaterial(Materials.Lignite).addByProduct(Coal);
        event.setMaterial(Materials.Magnesite).addByProduct(Materials.Magnesium);
        event.setMaterial(Materials.Magnesium).addByProduct(Materials.Olivine);
        event.setMaterial(Materials.Magnetite).addByProduct(Iron, Gold).bathMercury(Gold);
        event.setMaterial(Materials.Malachite).addByProduct(Copper, Materials.BrownLimonite, Materials.Calcite).bathPersulfate(Copper);
        event.setMaterial(Materials.Manganese).addByProduct(Materials.Chromium, Iron);
        event.setMaterial(Materials.Marble).addByProduct(Materials.Calcite);
        event.setMaterial(Materials.Molybdenite).addByProduct(Materials.Powellite, Materials.Scheelite, Materials.Wulfenite, Materials.Osmium);
        event.setMaterial(Materials.Molybdenum).addByProduct(Materials.Powellite, Materials.Scheelite, Materials.Wulfenite, Materials.Osmium);
        event.setMaterial(Materials.Naquadah).addByProduct(Materials.EnrichedNaquadah);
        event.setMaterial(Materials.Neodymium).addByProduct(Materials.RareEarth);
        event.setMaterial(Netherrack).addByProduct(Materials.Sulfur);
        event.setMaterial(Materials.Neutronium).addByProduct(Materials.Neutronium);
        event.setMaterial(Materials.Nickel).addByProduct(Materials.Cobalt, Materials.Platinum, Iron).bathMercury(Materials.Platinum).bathPersulfate(Materials.Nickel);
        event.setMaterial(Materials.Obsidian).addByProduct(Materials.Olivine);
        event.setMaterial(Materials.Olivine).addByProduct(Materials.Pyrope, Materials.Magnesium);
        event.setMaterial(Materials.Opal).addByProduct(Materials.Tanzanite);
        event.setMaterial(Materials.Osmium).addByProduct(Materials.Iridium).bathMercury(Materials.Osmium);
        event.setMaterial(Materials.Pentlandite).addByProduct(Iron, Materials.Sulfur, Materials.Cobalt).bathPersulfate(Materials.Cobalt);
        event.setMaterial(Materials.Phosphate).addByProduct(Materials.Phosphor);
        event.setMaterial(Materials.Phosphor).addByProduct(Materials.Phosphate);
        event.setMaterial(Materials.TricalciumPhosphate).addByProduct(Materials.Phosphate, Materials.Fluorite);
        event.setMaterial(Materials.Pitchblende).addByProduct(Materials.Lead, Materials.Thorium, Materials.RareEarth);
        event.setMaterial(Materials.Platinum).addByProduct(Materials.Nickel, Materials.Iridium).bathMercury(Materials.Platinum).bathPersulfate(Materials.Nickel);
        event.setMaterial(Materials.Plutonium).addByProduct(Materials.Uranium, Materials.Lead);
        event.setMaterial(Materials.Powellite).addByProduct(Materials.Molybdenite, Materials.Scheelite);
        event.setMaterial(Materials.Pyrite).addByProduct(Materials.Sulfur, Materials.TricalciumPhosphate, Iron);
        event.setMaterial(Materials.Pyrolusite).addByProduct(Materials.Manganese);
        event.setMaterial(Materials.Pyrope).addByProduct(Materials.RedGarnet, Materials.Magnesium);
        event.setMaterial(Materials.Rutile).addByProduct(Materials.Hematite, Materials.Zircon);
        event.setMaterial(Materials.Zircon).addByProduct(Materials.Rutile, Materials.Hafnium, Materials.Uraninite);
        event.setMaterial(Quartz).addByProduct(Netherrack);
        event.setMaterial(Materials.MilkyQuartz).addByProduct(Materials.Barite);
        event.setMaterial(Materials.RedGarnet).addByProduct(Materials.Spessartine, Materials.Pyrope, Materials.Almandine);
        event.setMaterial(Redstone).addByProduct(Materials.Cinnabar, Materials.RareEarth, Glowstone);
        event.setMaterial(Materials.RedGranite).addByProduct(Materials.PotassiumFeldspar);
        event.setMaterial(Materials.RockSalt).addByProduct(Materials.Salt);
        event.setMaterial(Materials.Ruby).addByProduct(Materials.Chromium, Materials.RedGarnet);
        event.setMaterial(Materials.Salt).addByProduct(Materials.RockSalt);
        event.setMaterial(Materials.Saltpeter).addByProduct(Materials.Saltpeter);
        event.setMaterial(Materials.Scheelite).addByProduct(Materials.Manganese, Materials.Molybdenum, Materials.Calcium).elecTicks(120); //1920 eu/tick
        event.setMaterial(Materials.Sheldonite).addByProduct(Materials.Palladium, Materials.Nickel, Materials.Iridium).bathMercury(Materials.Sheldonite).bathPersulfate(Materials.Nickel);
        event.setMaterial(Materials.Silicon).addByProduct(Materials.SiliconDioxide);
        event.setMaterial(Materials.Silver).addByProduct(Materials.Lead, Materials.Sulfur).bathMercury(Materials.Silver);
        event.setMaterial(Materials.Sodalite).addByProduct(Materials.Lazurite, Lapis);
        event.setMaterial(Materials.Sperrylite).addByProduct(Materials.Antimony, Copper, Materials.Sheldonite, Materials.Hematite).bathMercury(Materials.Sheldonite).bathPersulfate(Copper);
        event.setMaterial(Materials.Spessartine).addByProduct(Materials.RedGarnet, Materials.Manganese);
        event.setMaterial(Materials.Sphalerite).addByProduct(Materials.YellowGarnet, Materials.Cadmium, Materials.Indium, Materials.Zinc).bathPersulfate(Materials.Zinc);
        event.setMaterial(Materials.Spodumene).addByProduct(Materials.Alumina, Materials.Lithium);
        event.setMaterial(Materials.Steel).addByProduct(Iron);
        event.setMaterial(Materials.Stibnite).addByProduct(Materials.Antimony);
        event.setMaterial(Materials.Sulfur).addByProduct(Materials.Sulfur);
        event.setMaterial(Materials.Tantalite).addByProduct(Materials.Manganese, Materials.Niobium, Materials.Tantalum);
        event.setMaterial(Materials.Tanzanite).addByProduct(Materials.Opal);
        event.setMaterial(Materials.Tetrahedrite).addByProduct(Materials.Antimony, Materials.Zinc).bathPersulfate(Materials.Tetrahedrite);
        event.setMaterial(Materials.Thorium).addByProduct(Materials.Lead, Materials.Thorium, Materials.Indium);
        event.setMaterial(Materials.Tin).addByProduct(Iron, Materials.Zinc).bathPersulfate(Materials.Zinc);
        event.setMaterial(Materials.Titanium).addByProduct(Materials.Almandine);
        event.setMaterial(Materials.Tungstate).addByProduct(Materials.Manganese, Materials.Silver, Materials.Lithium).bathMercury(Materials.Silver);
        event.setMaterial(Materials.Tungsten).addByProduct(Materials.Manganese, Materials.Molybdenum);
        event.setMaterial(Materials.Uraninite).addByProduct(Materials.Lead, Materials.Thorium, Materials.RareEarth);
        event.setMaterial(Materials.Uvarovite).addByProduct(Materials.YellowGarnet, Materials.Chromium);
        event.setMaterial(Materials.VanadiumMagnetite).addByProduct(Materials.Magnetite, Materials.Vanadium);
        event.setMaterial(Materials.Wulfenite).addByProduct(Materials.Powellite/*, Sheelite*/, Materials.Molybdenite, Materials.Galena);
        event.setMaterial(Materials.YellowGarnet).addByProduct(Materials.Andradite, Materials.Grossular, Materials.Uvarovite);
        event.setMaterial(Materials.YellowLimonite).addByProduct(Materials.Nickel, Materials.BrownLimonite, Materials.Cobalt).bathPersulfate(Materials.Nickel);
        event.setMaterial(Materials.Zinc).addByProduct(Materials.Tin, Materials.Gallium).bathPersulfate(Materials.Zinc);
    }

    private static void flags(GregTechMaterialEvent event){
        GT5RMaterialTags.BRITTLEG.add(Coal, Charcoal, Materials.Lignite, Materials.PetroleumCoke, Materials.CoalCoke, Materials.LigniteCoke);
        GT5RMaterialTags.CALCITE2X.add(Materials.Pyrite, Materials.YellowLimonite);
        GT5RMaterialTags.CALCITE3X.add(Iron, Materials.BrownLimonite);
        GT5RMaterialTags.CENT5.add(/*Chrysolite*/ Flint, /*Niter*/ Materials.Glass, /*Perlite*/ Materials.WroughtIron, Materials.DarkAsh, Materials.AnnealedCopper,
                Materials.Cinnabar);
        GT5RMaterialTags.CENT10.add(Materials.Magnalium, Materials.VanadiumMagnetite, Materials.BrownLimonite, Materials.YellowLimonite, Materials.BlackGranite, Materials.Cupronickel, Materials.NiobiumTitanium, Materials.BorosilicateGlass,
                Materials.GalliumArsenide, Materials.Marble, Materials.Limestone, Materials.Invar, Materials.TinAlloy, Materials.TungstenCarbide, EnderEye, Materials.Powellite, Materials.VanadiumGallium, Blaze,
                Materials.TungstenSteel, Materials.Brass, Materials.Nichrome, Materials.Electrum, Materials.Bronze, Materials.Stibnite, Materials.Wulfenite, Materials.RedAlloy, Materials.SterlingSilver, Materials.RoseGold, Materials.BatteryAlloy, Materials.SolderingAlloy, Materials.TricalciumPhosphate);
        GT5RMaterialTags.CENT15.add(Materials.Kanthal, Materials.IndiumGalliumPhosphide, Materials.BlackSteel, Materials.RedGarnet, Materials.YellowGarnet, Materials.BismuthBronze, Materials.BlackBronze, Materials.VanadiumSteel, Materials.CdInAGAlloy, Materials.CobaltBrass,
                Materials.Pitchblende, Redstone, Materials.HSSS);
        GT5RMaterialTags.CENT20.add(Lapis, Materials.Tetrahedrite, Materials.RedSteel, Materials.BlueSteel, Basalt, Materials.HSSE, Materials.Sheldonite, Materials.HSSG, Materials.Komatiite);
        GT5RMaterialTags.CENT.add(GT5RMaterialTags.CENT5.all().toArray(new Material[0]));
        GT5RMaterialTags.CENT.add(GT5RMaterialTags.CENT10.all().toArray(new Material[0]));
        GT5RMaterialTags.CENT.add(GT5RMaterialTags.CENT15.all().toArray(new Material[0]));
        GT5RMaterialTags.CENT.add(GT5RMaterialTags.CENT20.all().toArray(new Material[0]));
        GT5RMaterialTags.RECIPE_MASS.add(Materials.Lead, 64);
        FURNACE_FUELS.add(Materials.CoalCoke, GEM, 3200);
        FURNACE_FUELS.add(Materials.CoalCoke, DUST, 3200);
        FURNACE_FUELS.add(Materials.CoalCoke, BLOCK, 32000);
        FURNACE_FUELS.add(Materials.Lignite, GEM, 800);
        FURNACE_FUELS.add(Materials.Lignite, DUST, 800);
        FURNACE_FUELS.add(Materials.Lignite, RAW_ORE, 800);
        FURNACE_FUELS.add(Materials.Lignite, CRUSHED, 900);
        FURNACE_FUELS.add(Materials.Lignite, CRUSHED_PURIFIED, 1000);
        FURNACE_FUELS.add(Materials.Lignite, DUST_IMPURE, 800);
        FURNACE_FUELS.add(Materials.Lignite, DUST_PURE, 800);
        FURNACE_FUELS.add(Materials.Lignite, CRUSHED_REFINED, 1100);
        FURNACE_FUELS.add(Materials.Lignite, BLOCK, 8000);
        FURNACE_FUELS.add(Materials.LigniteCoke, GEM, 1600);
        FURNACE_FUELS.add(Materials.LigniteCoke, DUST, 1600);
        FURNACE_FUELS.add(Materials.LigniteCoke, BLOCK, 16000);
        FURNACE_FUELS.add(Materials.PetroleumCoke, GEM, 6400);
        FURNACE_FUELS.add(Materials.PetroleumCoke, DUST, 6400);
        FURNACE_FUELS.add(Materials.PetroleumCoke, BLOCK, 64000);
        FURNACE_FUELS.add(Coal, DUST, 1600);
        FURNACE_FUELS.add(Coal, RAW_ORE, 1600);
        FURNACE_FUELS.add(Coal, CRUSHED, 1800);
        FURNACE_FUELS.add(Coal, CRUSHED_PURIFIED, 2000);
        FURNACE_FUELS.add(Coal, DUST_IMPURE, 1600);
        FURNACE_FUELS.add(Coal, DUST_PURE, 1600);
        FURNACE_FUELS.add(Coal, CRUSHED_REFINED, 2200);
        FURNACE_FUELS.add(Charcoal, DUST, 1600);
        FURNACE_FUELS.add(Charcoal, BLOCK, 16000);
        FURNACE_FUELS.add(Materials.OilShale, DUST, 400);
        FURNACE_FUELS.add(Materials.OilShale, RAW_ORE, 400);
        RADIOACTIVE.add(Materials.Cobalt60, 2);
        RADIOACTIVE.add(Materials.Astatine, 4);
        RADIOACTIVE.add(Materials.Uranium235, 2);
        RADIOACTIVE.add(Materials.Plutonium, 1);
        RADIOACTIVE.add(Materials.Plutonium241, 3);
        RADIOACTIVE.add(Materials.Plutonium243, 3);
        RADIOACTIVE.add(Materials.Americium241, 4);
        RADIOACTIVE.add(Materials.EnrichedNaquadah, 4);
        RADIOACTIVE.add(Materials.Naquadria, 5);
        GT5RMaterialTags.CRACK.add(Materials.RefineryGas, Materials.Naphtha, Materials.Ethane, Materials.Ethylene, Materials.Propane, Materials.Propene, Materials.Butane, Materials.Butene, Materials.Butadiene);
        GT5RMaterialTags.CRYSTALLIZE.add(Lapis, Materials.Lazurite, Materials.Sodalite, Materials.MilkyQuartz, Quartz, Materials.CertusQuartz, Materials.Fluix, Materials.Jade, Materials.Amber, Materials.Apatite, Flint, EnderEye, EnderPearl);
        GT5RMaterialTags.NON_GEMS.add(Coal, Charcoal, Materials.Lignite, Materials.CoalCoke, Materials.LigniteCoke, Materials.PetroleumCoke);
        GT5RMaterialTags.ELEC30.add(Charcoal, Materials.Opal, Coal, Materials.CoalCoke, Materials.Lignite, Materials.SteelMagnetic, Materials.IronMagnetic, Materials.Quicklime, Materials.Quartzite,
                Materials.SiliconDioxide, Materials.Wollastonite, Materials.CobaltOxide, Materials.Garnierite, Materials.CupricOxide, Materials.RockSalt, /*Zincite,*/Materials.Pyrolusite, /*ChromiumDioxide,*/
                Materials.Phosphate, /*NiobiumNitride,*/ Materials.GreenSapphire, Materials.Sapphire, Materials.NeodymiumMagnetic, Materials.Cassiterite,
                Materials.PhosphorousPentoxide, Materials.Hematite, Materials.Massicot, Materials.ArsenicTrioxide, Sugar, Materials.Magnetite, Materials.AntimonyTrioxide,
                Materials.Salt, Materials.SodiumBisulfate, Materials.PotassiumBisulfate, Materials.HydrochloricAcid, Materials.SaltWater, Materials.HydrochloricAcid, Diamond,
                Materials.NickelSulfate, Water, Materials.DistilledWater, Materials.HeavyWater, Materials.SemiheavyWater, Materials.TritiatedWater, Materials.MilkyQuartz, Materials.FerricChloride, Materials.Sperrylite, Materials.LithiumChloride);
        GT5RMaterialTags.ELEC60.add(Materials.CalciumChloride, Materials.SodiumHydroxide, Materials.Propene, Materials.Ethylene, Materials.Butene, Materials.Benzene, Materials.Styrene, Materials.Ethane, Materials.Ammonia, Materials.SodiumSulfide, Materials.Methane,
                Materials.Magnesite, Materials.HydrofluoricAcid, Materials.HydrogenFluoride, Materials.Sphalerite, /*NitroCarbon,*/ Materials.SodaAsh, Materials.Calcite, Materials.Saltpeter, Materials.Monazite,
                /*Wollastonite,*/ Materials.NitrogenMonoxide, Materials.Butane, Materials.CarbonMonoxide, Materials.Pyrite, Materials.RedGranite, Materials.Ferrosilite, Materials.Butadiene, Materials.Amethyst,
                Materials.Molybdenite, Materials.Ruby, /*Kyanite,*/ Materials.NitrogenDioxide, Materials.DinitrogenTetroxide, Materials.Propane, Materials.Barite, Materials.Isoprene,
                Materials.Chromite, EnderPearl, Materials.SiliconDioxide, Materials.Apatite, Materials.SulfurTrioxide, /*Pyrochlore, */ Materials.Toluene, Materials.Phosphate,
                Materials.Tantalite, Materials.PhosphorousPentoxide, Materials.Osmiridium, Materials.Pentlandite, Materials.Steel, Materials.Graphite, Materials.MagnesiumChloride);
        GT5RMaterialTags.ELEC90.add(Materials.Polydimethylsiloxane, Materials.AceticAcid, Materials.Olivine, Materials.Ethanol, Materials.Methanol, Materials.VinylAcetate, /*Gypsum,*/ Materials.Cobaltite,
                /*Dymethylamine,*/ Materials.Chalcopyrite, /*Mirabilite,*/ Materials.Spodumene, /*Dolomite,*/ Materials.HypochlorousAcid, Materials.Chloramine, Materials.Bastnasite,
                Materials.Chloromethane, Materials.Malachite, /*Borax, */ /*Kaolinite,*/ Materials.Obsidian, Materials.NitricAcid, Materials.VinylChloride, Materials.Acetone, /*Asbestos,*/ Materials.PotassiumFeldspar,
                Materials.MethylAcetate, Materials.Sodalite, Materials.AllylChloride, Materials.Phenol, Materials.Glycerol, Materials.Talc, Materials.Soapstone, Materials.PhosphoricAcid,
                Materials.Chlorobenzene, Materials.SulfuricAcid, Materials.Pyrope, Materials.SodiumPersulfate, Materials.Chloroform, Materials.Grossular, Materials.Spessartine, Materials.Almandine, Materials.Uvarovite, Materials.Andradite,
                Emerald, Materials.Galena, Materials.Zircon);
        GT5RMaterialTags.ELEC120.add(Materials.Clay, /*Trona,*/ Materials.BlueTopaz, Materials.Topaz, /*Pollucite,*/ Materials.CarbonDioxide, Materials.SulfurDioxide, Materials.Epichlorohydrin, Materials.Lepidolite, /*FullersEarth, Alunite,*/ Materials.Glauconite,
                /*Mica,*/ Materials.Lazurite, Materials.Tanzanite, Materials.Biotite, Materials.StainlessSteel, Materials.Ultimet, Materials.CalciumAcetateSolution, Materials.Dimethyldichlorosilane, /*Vermiculate, Zeolite,*/ Materials.GlycerylTrinitrate);
        GT5RMaterialTags.ELEC.add(GT5RMaterialTags.ELEC30.all().toArray(new Material[0]));
        GT5RMaterialTags.ELEC.add(GT5RMaterialTags.ELEC60.all().toArray(new Material[0]));
        GT5RMaterialTags.ELEC.add(GT5RMaterialTags.ELEC90.all().toArray(new Material[0]));
        GT5RMaterialTags.ELEC.add(GT5RMaterialTags.ELEC120.all().toArray(new Material[0]));
        GT5RMaterialTags.ELEC_CIRCUIT.add(Water, Materials.DistilledWater, Materials.SodiumBisulfate);
        GT5RMaterialTags.ELECSEPG.add(Materials.VanadiumMagnetite, Materials.Magnetite);
        GT5RMaterialTags.ELECSEPI.add(Materials.YellowLimonite, Materials.BrownLimonite, Materials.Tin, Materials.Chromium, Materials.Ilmenite, Materials.Hematite, Materials.Pyrite, Materials.Glauconite, Materials.Nickel, Materials.Chromite, Materials.Pentlandite, Materials.Manganese);
        GT5RMaterialTags.ELECSEPN.add(Materials.Monazite, Materials.Bastnasite);
        GT5RMaterialTags.GRINDABLE.add(/* Paper, */Coal, Charcoal, Materials.Lignite, Materials.Lead, Materials.Tin, Materials.SolderingAlloy, Flint, Gold, Materials.Silver, Iron,
                Materials.IronMagnetic, Materials.Steel, Materials.SteelMagnetic, Materials.Zinc, Materials.Antimony, Copper, Materials.AnnealedCopper, Materials.Bronze, Materials.Nickel, Materials.Invar,
                Materials.Brass, Materials.WroughtIron, Materials.Electrum, Materials.Clay, Blaze);
        GT5RMaterialTags.NOBBF.add(Materials.Tetrahedrite, Materials.Chalcopyrite, Materials.Sheldonite, Materials.Pyrolusite, Materials.Magnesite, Materials.Molybdenite, Materials.Galena);
        NOSMASH.add(Wood/* WoodSealed */, Materials.Sulfur, Materials.Saltpeter, Materials.Graphite, /* Paper, */Coal, Charcoal, Materials.Lignite, Materials.PetroleumCoke, Materials.Rubber,
                Materials.StyreneButadieneRubber, Materials.Plastic, Materials.PolyvinylChloride, Materials.Polystyrene, Materials.SiliconeRubber, Materials.Polytetrafluoroethylene, Materials.NitroDiesel,
                Materials.Concrete, Redstone, Glowstone, Netherrack, Stone, Materials.Brick, Endstone, Materials.Marble, Basalt, Materials.Obsidian, Flint,
                Materials.RedGranite, Materials.BlackGranite, Materials.Salt, Materials.RockSalt, Materials.Glass, Diamond, Emerald, Materials.Amethyst, Materials.Tanzanite, Materials.Topaz,
                /* Amber, */ Materials.Sapphire, Materials.Ruby, Materials.Opal, Materials.Olivine, Lapis, Materials.MilkyQuartz, Quartz, Materials.Phosphate, EnderPearl, EnderEye, Materials.Silicon);
        GT5RMaterialTags.NOSMELT.add(Wood/* , WoodSealed */, Materials.Sulfur, Materials.Saltpeter, Materials.Graphite, /* Paper, */Coal, Charcoal, Materials.Lignite,
                Materials.NitroDiesel, Emerald, Materials.Amethyst, Materials.Tanzanite, Materials.Topaz, /* Amber, */ Materials.Sapphire, Materials.Ruby, Materials.Opal, Materials.Olivine,
                Lapis, Materials.Sodalite, Materials.Lazurite, Materials.Monazite , Materials.MilkyQuartz, Quartz, Materials.TricalciumPhosphate, Materials.Phosphate,
                EnderPearl, EnderEye, Blaze);
        RUBBERTOOLS.add(Materials.Rubber, Materials.StyreneButadieneRubber, Materials.Plastic, Materials.PolyvinylChloride, Materials.Polytetrafluoroethylene, Materials.SiliconeRubber, Materials.EpoxyResin);
        GT5RMaterialTags.SMELTF.add(Materials.Concrete, Redstone, Glowstone, Materials.Glass, Blaze);
        GT5RMaterialTags.SOLDER.add(Materials.Lead, Materials.Tin, Materials.SolderingAlloy);
        GT5RMaterialTags.SOLDER.subTag(SubTag.BAD_SOLDER, Materials.Lead, Materials.Tin);
        GT5RMaterialTags.SOLDER.subTag(SubTag.GOOD_SOLDER, Materials.SolderingAlloy, Materials.Tin);
        WIRE.subTag(SubTag.COPPER_WIRE, Materials.AnnealedCopper);
        WIRE.subTag(SubTag.COPPER_WIRE, Copper);
        CABLE.subTag(SubTag.COPPER_CABLE, Materials.AnnealedCopper);
        CABLE.subTag(SubTag.COPPER_CABLE, Copper);

        event.setMaterial(Materials.AnnealedCopper).setDirectSmeltInto(Copper).setMacerateInto(Copper);
        event.setMaterial(Copper).setArcSmeltInto(Materials.AnnealedCopper);
        event.setMaterial(Iron).setArcSmeltInto(Materials.WroughtIron);
        event.setMaterial(Materials.IronMagnetic).setDirectSmeltInto(Iron).setMacerateInto(Iron).setArcSmeltInto(Materials.WroughtIron);
        event.setMaterial(Materials.NeodymiumMagnetic).setDirectSmeltInto(Materials.Neodymium).setMacerateInto(Materials.Neodymium).setArcSmeltInto(Materials.Neodymium);
        event.setMaterial(Materials.SteelMagnetic).setDirectSmeltInto(Materials.Steel).setMacerateInto(Materials.Steel).setArcSmeltInto(Materials.Steel);
        event.setMaterial(Materials.WroughtIron).setDirectSmeltInto(Iron).setMacerateInto(Iron);

        /*event.setMaterial(BandedIron).setDirectSmeltInto(Iron);
        event.setMaterial(BrownLimonite).setDirectSmeltInto(Iron);
        event.setMaterial(Cassiterite).setDirectSmeltInto(Tin);
        event.setMaterial(Chalcopyrite).setDirectSmeltInto(Copper);
        event.setMaterial(Cinnabar).setDirectSmeltInto(Mercury);
        event.setMaterial(Cobaltite).setDirectSmeltInto(Cobalt);
        event.setMaterial(Cooperite).setDirectSmeltInto(Platinum);
        event.setMaterial(Galena).setDirectSmeltInto(Lead);
        event.setMaterial(Garnierite).setDirectSmeltInto(Nickel);
        event.setMaterial(Magnesite).setDirectSmeltInto(Magnesium);
        event.setMaterial(Magnetite).setDirectSmeltInto(Iron);
        event.setMaterial(Malachite).setDirectSmeltInto(Copper);
        event.setMaterial(Molybdenite).setDirectSmeltInto(Molybdenum);
        event.setMaterial(Pentlandite).setDirectSmeltInto(Nickel);
        event.setMaterial(Pyrite).setDirectSmeltInto(Iron);
        event.setMaterial(Pyrolusite).setDirectSmeltInto(Manganese);
        event.setMaterial(Sphalerite).setDirectSmeltInto(Zinc);
        event.setMaterial(Stibnite).setDirectSmeltInto(Antimony);
        event.setMaterial(Tetrahedrite).setDirectSmeltInto(Copper);
        event.setMaterial(YellowLimonite).setDirectSmeltInto(Iron);*/

        event.setMaterial(Materials.Cassiterite).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Glowstone).setOreMulti(5).setSmeltingMulti(5);
        event.setMaterial(Lapis).setOreMulti(6).setSmeltingMulti(6).setByProductMulti(4);
        event.setMaterial(Materials.TricalciumPhosphate).setOreMulti(3).setSmeltingMulti(3);
        event.setMaterial(Quartz).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Redstone).setOreMulti(5).setSmeltingMulti(5);
        event.setMaterial(Materials.RockSalt).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Materials.Salt).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Materials.Saltpeter).setOreMulti(4).setSmeltingMulti(4);
        event.setMaterial(Materials.Scheelite).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Materials.Tungstate).setOreMulti(2).setSmeltingMulti(2);
        event.setMaterial(Materials.Monazite).setOreMulti(8);
        event.setMaterial(Materials.Apatite).setOreMulti(4);
        event.setMaterial(Materials.CertusQuartz).setOreMulti(2);
        event.setMaterial(Materials.Lazurite).setOreMulti(6);
        event.setMaterial(Materials.Sodalite).setOreMulti(6);
        event.setMaterial(Materials.TricalciumPhosphate).setOreMulti(3);

        // Plastic.setEnchantmentForTools(Enchantment.knockback, 1);
        // PolyvinylChloride.setEnchantmentForTools(Enchantment.knockback, 1);
        // Polystyrene.setEnchantmentForTools(Enchantment.knockback, 1);
        // Rubber.setEnchantmentForTools(Enchantment.knockback, 2);
        // StyreneButadieneRubber.setEnchantmentForTools(Enchantment.knockback, 2);
        // Flint.setEnchantmentForTools(Enchantment.fireAspect, 1);
        // Blaze.setEnchantmentForTools(Enchantment.fireAspect, 3);
        // EnderPearl.setEnchantmentForTools(Enchantment.silkTouch, 1);
        // NetherStar.setEnchantmentForTools(Enchantment.silkTouch, 1);
        // BlackBronze.setEnchantmentForTools(Enchantment.smite, 2);
        // Gold.setEnchantmentForTools(Enchantment.smite, 3);
        // RoseGold.setEnchantmentForTools(Enchantment.smite, 4);
        // Platinum.setEnchantmentForTools(Enchantment.smite, 5);
        // Lead.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
        // Nickel.setEnchantmentForTools(Enchantment.baneOfArthropods, 2);
        // Invar.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
        // Antimony.setEnchantmentForTools(Enchantment.baneOfArthropods, 3);
        // BatteryAlloy.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
        // Bismuth.setEnchantmentForTools(Enchantment.baneOfArthropods, 4);
        // BismuthBronze.setEnchantmentForTools(Enchantment.baneOfArthropods, 5);
        // Iron.setEnchantmentForTools(Enchantment.sharpness, 1);
        // Bronze.setEnchantmentForTools(Enchantment.sharpness, 1);
        // Brass.setEnchantmentForTools(Enchantment.sharpness, 2);
        // Steel.setEnchantmentForTools(Enchantment.sharpness, 2);
        // WroughtIron.setEnchantmentForTools(Enchantment.sharpness, 2);
        // StainlessSteel.setEnchantmentForTools(Enchantment.sharpness, 3);
        // BlackSteel.setEnchantmentForTools(Enchantment.sharpness, 4);
        // RedSteel.setEnchantmentForTools(Enchantment.sharpness, 4);
        // BlueSteel.setEnchantmentForTools(Enchantment.sharpness, 5);
        // DamascusSteel.setEnchantmentForTools(Enchantment.sharpness, 5);
        // TungstenCarbide.setEnchantmentForTools(Enchantment.sharpness, 5);
        // HSSE.setEnchantmentForTools(Enchantment.sharpness, 5);
        // HSSG.setEnchantmentForTools(Enchantment.sharpness, 4);
        // HSSS.setEnchantmentForTools(Enchantment.sharpness, 5);
        // Lava.setTemperatureDamage(3.0F);


        // Glue.mChemicalFormula = "No Horses were harmed for the Production";
        // UUAmplifier.mChemicalFormula = "Accelerates the Mass Fabricator";
        // WoodSealed.mChemicalFormula = "";
        // Wood.mChemicalFormula = "";

        // Naquadah.mMoltenRGBa[0] = 0;
        // Naquadah.mMoltenRGBa[1] = 255;
        // Naquadah.mMoltenRGBa[2] = 0;
        // Naquadah.mMoltenRGBa[3] = 0;
        // NaquadahEnriched.mMoltenRGBa[0] = 64;
        // NaquadahEnriched.mMoltenRGBa[1] = 255;
        // NaquadahEnriched.mMoltenRGBa[2] = 64;
        // NaquadahEnriched.mMoltenRGBa[3] = 0;
        // Naquadria.mMoltenRGBa[0] = 128;
        // Naquadria.mMoltenRGBa[1] = 255;
        // Naquadria.mMoltenRGBa[2] = 128;
        // Naquadria.mMoltenRGBa[3] = 0;

        // NaquadahEnriched.mChemicalFormula = "Nq+";
        // Naquadah.mChemicalFormula = "Nq";
        // Naquadria.mChemicalFormula = "NqX";
    }

    private static void antimatterMaterials(GregTechMaterialEvent event){
        event.setMaterial(Basalt).mats(of(Materials.Olivine, 1, Materials.Calcite, 3, Flint, 8, Materials.DarkAsh, 4));
        event.setMaterial(Blaze).mats(of(Materials.Sulfur, 1, Materials.DarkAsh, 1));
        event.setMaterial(Coal).asGemBasic(false).flags(ORE_STONE, ROCK).mats(of(Materials.Carbon, 2));
        event.setMaterial(Copper).flags(PLATE, ROD_LONG, FOIL, WIRE_FINE, BOLT);
        event.setMaterial(Quartz).flags(GT5RMaterialTypes.CHAMBER);
        event.setMaterial(Diamond).asGem(true).mats(of(Materials.Carbon, 64), 1).elecTicks(768).harvestLevel(3);
        event.setMaterial(Emerald).asGem(true).mats(of(Materials.Alumina, 5, Materials.Beryllium, 3, Materials.SiliconDioxide, 18, Materials.Oxygen, 3)).elecTicks(540);
                //.tool().toolDamage(3).toolSpeed(9).toolDurability(590).toolQuality(3).toolEnchantments(of(Data.IMPLOSION, 5)).build();
        event.setMaterial(EnderEye).asGemBasic(false, ROD_LONG, PLATE).mats(of(EnderPearl, 1, Blaze, 1));
        event.setMaterial(EnderPearl).mats(of(Materials.Beryllium, 1, Materials.Potassium, 4, Materials.Nitrogen, 5, Materials.Chlorine, 6)).elecTicks(220);
        event.setMaterial(Gold).flags(FOIL, ROD_LONG, WIRE_FINE);
        event.setMaterial(Iron).flags(RING, ROD_LONG, FRAME, GEAR_SMALL);
        event.setMaterial(Wood).flags(FRAME);
        event.setMaterial(AntimatterMaterials.Netherite).asMetal(2246, 1300, RING);
        event.setMaterial(Lapis).asGemBasic(false, PLATE).mats(of(Materials.Lazurite, 12, Materials.Sodalite, 2, Materials.Pyrite, 1, Materials.Calcite, 1));
        event.setMaterial(Prismarine).mats(of(Materials.Potassium, 2, Materials.Oxygen, 8, Materials.Manganese, 1, Materials.Silicon, 5));
        event.setMaterial(Redstone).mats(of(Materials.Silicon, 1, Materials.Pyrite, 5, Materials.Ruby, 1, Materials.Mercury, 3)).asFluid(0, MaterialTags.MELTING_POINT.getInt(Redstone));//.setOreMulti(4);
        event.setMaterial(Water).mats(of(Materials.Hydrogen, 2, Materials.Oxygen, 1)).elecTicks(2000);
        event.setMaterial(Sugar).mats(of(Water, 11, Materials.Carbon, 12)).elecTicks(184);
        event.setMaterial(Glowstone).asFluid(0, 1000).flags(MOLTEN);
        event.setMaterial(Endstone).flags(ROD_LONG);
        event.setMaterial(Flint).mats(of(Materials.SiliconDioxide, 1)).elecTicks(80);
    }
}