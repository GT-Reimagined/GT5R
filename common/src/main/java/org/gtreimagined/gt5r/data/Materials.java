package org.gtreimagined.gt5r.data;

import org.gtreimagined.gt5r.GT5RRef;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.material.Material;
import net.minecraft.world.item.DyeColor;
import org.gtreimagined.gtcore.GTCore;
import org.gtreimagined.gtcore.data.GTCoreMaterials;

import static muramasa.antimatter.material.Element.*;
import static muramasa.antimatter.material.TextureSet.*;

public class Materials {
    //TODO add Zincite, chromium dioxide(mass multi=3), niobium nitride, nitro carbon, wollastonite, kyanite, chromite, pyrochlore, gypsum,
    // dymethylamine, mirabilite, dolomite, borax, kaolinite, asbestos, glycerol, chlorobenzene, trona, Pollucite, Fullers Earth, alunite, mica, vermiculate, zeolite


    /**
     *** PSE (No Isotopes)
     **/

    public static Material Hydrogen = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydrogen", 0x0000ff, NONE, H));
    public static Material Helium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "helium", 0xffff00, NONE, He));
    public static Material Lithium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lithium", 0xe1dcff, DULL, Li));
    public static Material Beryllium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "beryllium", 0x64b464, METALLIC, Be));
    public static Material Boron = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "boron", 0xfafafa, DULL, B));
    public static Material Carbon = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "carbon", 0x141414, DULL, C));
    public static Material Nitrogen = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nitrogen", 0x0096c8, NONE, N));
    public static Material Oxygen = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "oxygen", 0x0064c8, NONE, O));
    public static Material Fluorine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fluorine", 0xffffff, NONE, F));
    public static Material Neon = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "neon", 0xffff00, NONE, Ne));
    public static Material Sodium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodium", 0x000096, METALLIC, Na));
    public static Material Magnesium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnesium", 0xffc8c8, METALLIC, Mg));
    public static Material Aluminium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "aluminium", 0x80c8f0, DULL, Al));
    public static Material Silicon = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "silicon", 0x3c3c50, METALLIC, Si));
    public static Material Phosphor = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "phosphor", 0xffff00, DULL, P)).setDisplayNameString("Phosphorus");
    public static Material Sulfur = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sulfur", 0xc8c800, DULL, S));
    public static Material Chlorine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chlorine", 0x00ffff, NONE, Cl));
    public static Material Argon = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "argon", 0xff00f0, NONE, Ar));
    public static Material Potassium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "potassium", 0xfafafa, METALLIC, K));
    public static Material Calcium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "calcium", 0xfff5f5, METALLIC, Ca));
    public static Material Titanium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "titanium", 0xdca0f0, METALLIC, Ti));
    public static Material Vanadium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "vanadium", 0x323232, METALLIC, V));
    public static Material Chromium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chromium", 0xffe6e6, SHINY, Cr));
    public static Material Manganese = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "manganese", 0xfafafa, DULL, Mn));
    public static Material Cobalt = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cobalt", 0x5050fa, METALLIC, Co));
    public static Material Nickel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nickel", 0xc8c8fa, METALLIC, Ni));
    public static Material Zinc = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "zinc", 0xfaf0f0, METALLIC, Zn));
    public static Material Gallium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "gallium", 0xdcdcff, SHINY, Ga));
    public static Material Germanium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "germanium", 0xb2a57b, SHINY, Ge));
    public static Material Arsenic = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "arsenic", 0xa6a586, SHINY, As));
    public static Material Selenium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "selenium", 0xb18bd6, SHINY, Se));
    public static Material Krypton = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "krypton", 0xffffff, DULL, Kr));
    public static Material Rubidium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "rubidium", 0x6e6a61, SHINY, Ru));
    public static Material Strontium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "strontium", 0xd0c49e, SHINY, Sr));
    public static Material Yttrium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "yttrium", 0xdcfadc, METALLIC, Y));
    public static Material Zirconium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "zirconium", 0xeee7d7, SHINY, Zr));
    public static Material Niobium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "niobium", 0xbeb4c8, METALLIC, Nb));
    public static Material Molybdenum = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "molybdenum", 0xb4b4dc, SHINY, Mo));
    public static Material Technetium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "technetium", 0xa3a09b, METALLIC, Tc));
    public static Material Ruthenium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ruthenium", 0x9e9a9e, SHINY, Ru));
    public static Material Rhodium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "rhodium", 0x797665, SHINY, Rh));
    public static Material Palladium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "palladium", 0x808080, SHINY, Pd));
    public static Material Silver = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "silver", 0xdcdcff, SHINY, Ag));
    public static Material Cadmium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cadmium", 0x32323c, SHINY, Cd));
    public static Material Indium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "indium", 0x400080, METALLIC, In));
    public static Material Tin = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tin", 0xdcdcdc, DULL, Sn));
    public static Material Antimony = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "antimony", 0xdcdcf0, SHINY, Sb));
    public static Material Tellurium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tellurium", 0xc1bbc9, SHINY, Te));
    public static Material Iodine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "iodine", 0xbd4eaa, DULL, I));
    public static Material Xenon = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "xenon", 0xffff00, NONE, Xe));
    public static Material Caesium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "caesium", 0x6c5f3f, SHINY, Cs));
    public static Material Barium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "barium", 0x818ca8, METALLIC, Ba));
    public static Material Lanthanum = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lanthanum", 0x807e65, METALLIC, La));
    public static Material Cerium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cerium", 0x8390B2, METALLIC, Ce));
    public static Material Praseodymium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "praseodymium", 0xadac90, METALLIC, Pr));
    public static Material Neodymium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "neodymium", 0x646464, METALLIC, Nd));
    public static Material Promethium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "promethium", 0x4c3d39, SHINY, Pm));
    public static Material Samarium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "samarium", 0xeef2d7, SHINY, Sm));
    public static Material Europium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "europium", 0xc7ae5c, SHINY, Eu));
    public static Material Gadolinium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "gadolinium", 0x86837e, SHINY, Gd));
    public static Material Terbium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "terbium", 0x87897e, METALLIC, Tb));
    public static Material Dysprosium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dysprosium", 0xcfd2b7, METALLIC, Dy));
    public static Material Holmium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "holmium", 0x9b9d88, METALLIC, Ho));
    public static Material Erbium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "erbium", 0xa8a6b4, SHINY, Er));
    public static Material Thulium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "thulium", 0xa39e9B, SHINY, Tm));
    public static Material Ytterbium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ytterbium", 0xc1cac5, SHINY, Yb));
    public static Material Lutetium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lutetium", 0xe1e4dd, SHINY, Lu));
    public static Material Hafnium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hafnium", 0xa29791, SHINY, Hf));
    public static Material Tantalum = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tantalum", 0x9da0a5, SHINY, Ta));
    public static Material Tungsten = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tungsten", 0x323232, METALLIC, W));
    public static Material Rhenium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "rhenium", 0x61615f, SHINY, Re));
    public static Material Osmium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "osmium", 0x3232ff, METALLIC, Os));
    public static Material Iridium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "iridium", 0xf0f0f5, DULL, Ir));
    public static Material Platinum = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "platinum", 0x64b4fa, SHINY, Pt));
    public static Material Mercury = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "mercury", 0xffdcdc, SHINY, Hg));
    public static Material Thallium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "thallium", 0xB6B6D2, SHINY, Tl));
    public static Material Lead = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lead", 0x8c648c, DULL, Pb));
    public static Material Bismuth = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bismuth", 0x64a0a0, METALLIC, Bi));
    public static Material Polonium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "polonium", 0x707646, SHINY, Po));
    public static Material Astatine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "astatine", 0x140E14, SHINY, At));
    public static Material Radon = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "radon", 0xff00ff, NONE, Rn));
    public static Material Francium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "francium", 0xaaaaaa, RAD, Fr));
    public static Material Radium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "radium", 0xf1bd3c, RAD, Ra));
    public static Material Actinium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "actinium", 0xb8c5f1, RAD, Ac));
    public static Material Thorium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "thorium", 0x001e00, RAD, Th)).setDisplayNameString("Thorium 232");
    public static Material Protactinium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "protactinium", 0x8a735a, RAD, Pa));
    public static Material Uranium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium", 0x32f032, RAD, U)).setDisplayNameString("Uranium 238");
    public static Material Neptunium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "neptunium", 0x203f64, RAD, Np));
    public static Material Plutonium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "plutonium", 0xf03232, RAD, Pu));
    public static Material Americium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "americium", 0xc8c8c8, RAD, Am));
    public static Material Curium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "curium", 0x664540, RAD, Cm));
    public static Material Berkelium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "berkelium", 0x88490f, RAD, Bk));
    public static Material Californium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "californium", 0xa78100, RAD, Cf));
    public static Material Einsteinium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "einsteinium", 0xaa8400, RAD, Es));
    public static Material Fermium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fermium", 0x7b3cab, RAD, Fm));
    public static Material Mendelevium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "mendelevium", 0x183dab, RAD, Md));

    /**
     *** Isotopes
     **/

    public static Material HotHelium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_helium", 0xffff00, NONE, He));
    public static Material Deuterium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "deuterium", 0xffff00, NONE, D));
    public static Material Tritium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tritium", 0xff0000, METALLIC, T));
    public static Material Helium3 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "helium_3", 0xffffff, NONE, He3));
    public static Material Cobalt60 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cobalt_60", 0x5a5afa, RAD, Co60));
    public static Material Thorium230 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "thorium_230", 0x001400, RAD, Th230));
    public static Material Uranium233 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium_233", 0x46fa32, RAD, U233));
    public static Material Uranium235 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium_235", 0x46fa46, RAD, U235));
    public static Material Plutonium239 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "plutonium_239", 0xeb3232, RAD, Pu239));
    public static Material Plutonium241 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "plutonium_241", 0xf54646, RAD, Pu241));
    public static Material Plutonium243 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "plutonium_243", 0xfa4646, RAD, Pu243));
    public static Material Americium241 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "americium_241", 0xd2d2d2, RAD, Am241));
    public static Material Americium242 = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "americium_242", 0xd2d2d2, RAD, Am242));

    /**
     *** Solids
     **/

    /**
     ** Metals
     **/

    public static Material AnnealedCopper = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "annealed_copper", 0xff7814, SHINY));
    public static Material BatteryAlloy = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "battery_alloy", 0x9c7ca0, DULL));
    public static Material BismuthBronze = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bismuth_bronze", 0x647d7d, DULL));
    public static Material BlackBronze = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "black_bronze", 0x64327d, DULL));
    public static Material BlackSteel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "black_steel", 0x646464, METALLIC));
    public static Material BlueSteel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "blue_steel", 0x64648c, METALLIC));
    public static Material Brass = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "brass", 0xffb400, METALLIC));
    public static Material Bronze = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bronze", 0xff8000, METALLIC));
    public static Material CdInAGAlloy = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cd_in_ag_alloy", 0x646480, METALLIC)).setDisplayNameString("Cd-In-Ag-Alloy");
    public static Material CobaltBrass = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cobalt_brass", 0xb4b4a0, METALLIC));
    public static Material Cupronickel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cupronickel", 0xe39680, METALLIC));
    public static Material Duranium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "duranium", 0xffffff, METALLIC));
    public static Material Electrum = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "electrum", 0xffff64, SHINY));
    public static Material EnrichedNaquadah = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "enriched_naquadah", 0x323232, SHINY));
    public static Material HSSE = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hsse", 0x336600, METALLIC)).setDisplayNameString("HSS-E");
    public static Material HSSG = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hssg", 0x999900, METALLIC)).setDisplayNameString("HSS-G");
    public static Material HSSS = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hsss", 0x660033, METALLIC)).setDisplayNameString("HSS-S");
    public static Material Invar = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "invar", 0xb4b478, METALLIC));
    public static Material IronMagnetic = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnetic_iron", 0xc8c8c8, MAGNETIC)).setMassMultiplierAndDivider(51, 50);
    public static Material Kanthal = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "kanthal", 0xc2d2df, METALLIC));
    public static Material Magnalium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnalium", 0xc8beff, DULL));
    public static Material Naquadah = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "naquadah", 0x323232, METALLIC, Naq));
    public static Material NaquadahAlloy = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "naquadah_alloy", 0x282828, METALLIC));
    public static Material Naquadria = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "naquadria", 0x1e1e1e, SHINY));
    public static Material NeodymiumMagnetic = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnetic_neodymium", 0x646464, MAGNETIC)).setMassMultiplierAndDivider(51, 50);
    public static Material Neutronium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "neutronium", 0xfafafa, DULL, Nt));
    public static Material Nichrome = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nichrome", 0xcdcef6, METALLIC));
    public static Material NickelZincFerrite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nickel_zinc_ferrite", 0x3c3c3c, ROUGH));
    public static Material NiobiumTitanium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "niobium_titanium", 0x1d1d29, DULL));
    public static Material Osmiridium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "osmiridium", 0x6464ff, METALLIC));
    public static Material RedAlloy = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "red_alloy", 0xc80000, DULL)).setMassMultiplierAndDivider(5, 4);
    public static Material RedSteel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "red_steel", 0x8c6464, METALLIC));
    public static Material RoseGold = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "rose_gold", 0xffe61e, SHINY));
    public static Material SolderingAlloy = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "soldering_alloy", 0xdcdce6, DULL));
    public static Material Steel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steel", 0x808080, METALLIC)).setMassMultiplierAndDivider(51, 50);
    public static Material SteelMagnetic = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnetic_steel", 0x808080, MAGNETIC)).setMassMultiplierAndDivider(51, 50);
    public static Material SterlingSilver = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sterling_silver", 0xfadce1, SHINY));
    public static Material StainlessSteel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "stainless_steel", 0xc8c8dc, SHINY));
    public static Material TinAlloy = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tin_alloy", 0x9fadbb, NONE));
    public static Material Tritanium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tritanium", 0xffffff, SHINY));
    public static Material TungstenCarbide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tungsten_carbide", 0x330066, METALLIC));
    public static Material TungstenSteel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tungstensteel", 0x6464a0, METALLIC));
    public static Material Ultimet = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ultimet", 0xb4b4e6, SHINY));
    public static Material VanadiumGallium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "vanadium_gallium", 0x80808c, SHINY));
    public static Material VanadiumSteel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "vanadium_steel", 0xc0c0c0, METALLIC));
    public static Material Vibranium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "vibranium", 0x00ffff, SHINY));
    public static Material WroughtIron = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "wrought_iron", 0xc8b4b4, METALLIC));
    public static Material YttriumBariumCuprate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "yttrium_barium_cuprate", 0x504046, METALLIC));

    /**
     ** Dusts
     **/

    public static Material AluminiumHydroxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID,"aluminium_hydroxide", 0xbebec8, DULL));
    public static Material AluminiumTrichloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "aluminium_trichloride", 0xf0d77d, FINE));
    public static Material Aluminosilicate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "aluminosilicate", 0xbfbdb0, FINE));
    public static Material AmmoniumChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ammonium_chloride", 0xffffff, DULL));
    public static Material AntimonyTrioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "antimony_trioxide", 0xe6e6f0, DULL));
    public static Material ArsenicTrioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "arsenic_trioxide", 0xffffff, SHINY));
    public static Material Ash = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ash", 0x969696, DULL));
    public static Material BenzoylChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "benzoyl_chloride", 0xf7f5eb, NONE));
    public static Material Biotite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "biotite", 0x141e14, METALLIC));
    public static Material Bitumen = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bitumen", 0x585863, ROUGH));
    public static Material BorosilicateGlass = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "borosilicate_glass", 0xfafafa, NONE));
    public static Material Brick = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "brick", 0x9b5643, ROUGH));
    public static Material Calcite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "calcite", 0xfae6dc, DULL));
    public static Material CalciumChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "calcium_chloride", 0xebebfa, DULL));
    public static Material CalciumSulfate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "calcium_sulfate", 0xf0dcd2, DULL));
    public static Material Clay = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "clay", 0xc8c8dc, ROUGH));
    public static Material CobaltOxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cobalt_oxide", 0x668000, DULL));
    public static Material Concrete = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "concrete", 0x646464, ROUGH));
    public static Material CupricOxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cupric_oxide", 0x0f0f0f, DULL));
    public static Material DarkAsh = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dark_ash", 0x323232, DULL)).setMassMultiplierAndDivider(2, 1);
    public static Material DialuminiumTrioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dialuminium_trioxide", 0xfaf6e6, FINE));
    public static Material Dibenzene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dibenzene", 0xfaf0c8, FINE));
    public static Material DibenzoylPeroxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dibenzoyl_peroxide", 0xf7f5eb, FINE));
    public static Material Dichloroethane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dichloroethane", 0xf8f6fc, NONE));
    public static Material Energium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "energium", 0xe81e21, NONE));
    public static Material FerricChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ferric_chloride", 0xb4b478, METALLIC));
    public static Material FerriteMixture = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ferrite_mixture", 0xb4b4b4, METALLIC));
    public static Material Ferrosilite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ferrosilite", 0x97632a, DULL));
    public static Material Fireclay = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fireclay", 0xada09b, ROUGH));
    public static Material Fluorite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fluorite", 0xFFB98C, NONE));
    public static Material GelledToluene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "gelled_toluene", 0xeeeeee, NONE));
    public static Material Graphene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "graphene", 0x808080, DULL));
    public static Material IndiumGalliumPhosphide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "indium_gallium_phosphide", 0x570b79, NONE));
    public static Material IridiumSodiumOxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "iridium_sodium_oxide", 0xffffff, NONE));
    public static Material Lapotronium = GTCoreMaterials.Lapotronium;
    public static Material LithiumChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lithium_chloride", 0xdedefa, DULL));
    public static Material MagnesiumCarbonate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnesium_carbonate", 0xF0E6E6, DULL));
    public static Material MagnesiumChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnesium_chloride", 0xd40d5c, DULL));
    public static Material Massicot = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "massicot", 0xffdd55, DULL));
    public static Material Obsidian = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "obsidian", 0x503264, DULL));
    public static Material PhosphorousPentoxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "phosphorous_pentoxide", 0xdcdc00, NONE));
    public static Material PlatinumGroupSludge = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "platinum_group_sludge", 0x001e00, NONE));
    public static Material Polydimethylsiloxane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "polydimethylsiloxane", 0xf5f5f5, NONE));
    public static Material PotassiumBisulfate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "potassium_bisulfate", 0xf0f0ff, NONE));
    public static Material PotassiumFeldspar = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "potassium_feldspar", 0x782828, FINE));
    public static Material Quicklime = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "quicklime", 0xf0f0f0, DULL));
    public static Material RareEarth = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "rare_earth", 0x808064, FINE));
    public static Material RawRubber = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "raw_rubber", 0xccc789, DULL));
    public static Material RawStyreneButadieneRubber = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "raw_styrene_butadiene_rubber", 0x54403d, SHINY));
    public static Material ReactionCatalyst = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "reaction_catalyst", 0x43ab43, NONE));
    public static Material Rubber = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "rubber", 0x141414, SHINY));
    public static Material SiliconDioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "silicon_dioxide", 0xc8c8c8, QUARTZ));
    public static Material SodaAsh = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "soda_ash", 0xdcdcff, DULL));
    public static Material SodiumAluminate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodium_aluminate", 0xE6E6FA, NONE));
    public static Material SodiumBisulfate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodium_bisulfate", 0x004455, NONE));
    public static Material SodiumPersulfate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodium_persulfate", 0x82b4fa, NONE));
    public static Material SodiumHydroxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodium_hydroxide", 0x003380, DULL));
    public static Material SodiumSulfate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodium_sulfate", 0x004455, NONE));
    public static Material SodiumSulfide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodium_sulfide", 0xffe680, NONE));
    public static Material TricalciumPhosphate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tricalcium_phosphate", 0xffff00, DULL));
    public static Material TungsticAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tungstic_acid", 0xb4c800, SHINY));
    public static Material TungstenTrioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tungsten_trioxide", 0xc7d300, DULL));
    public static Material Wollastonite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "wollastonite", 0xf0f0f0, DULL));

    /**
     ** Ores
     **/

    public static Material Almandine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "almandine", 0xff0000, ROUGH));
    public static Material Alumina = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "alumina", 0x78C3EB, METALLIC));
    public static Material Andradite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "andradite", 0x967800, ROUGH));
    public static Material Hematite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hematite", 0x915a5a, DULL));
    public static Material Barite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "barite", 0xe6ebff, DULL));
    public static Material Bastnasite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bastnasite", 0xc86e2d, FINE));
    public static Material Bentonite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bentonite", 0xf5d7d2, ROUGH)); // TODO: Ore Gen
    public static Material BrownLimonite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "brown_limonite", 0xc86400, METALLIC));
    public static Material Cassiterite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cassiterite", 0xdcdcdc, METALLIC)).setMassMultiplierAndDivider(3, 1);
    public static Material Chalcopyrite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chalcopyrite", 0xa07828, DULL));
    public static Material Chromite = AntimatterAPI.register(Material.class, new Material(Ref.ID, "chromite", 0x23140F, DULL));
    public static Material Cobaltite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cobaltite", 0x5050fa, METALLIC));
    public static Material Galena = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "galena", 0x643c64, DULL));
    public static Material Garnierite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "garnierite", 0x32c846, METALLIC));
    public static Material Glauconite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "glauconite", 0x82b43c, DULL)); // TODO: Ore Gen;
    public static Material Graphite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "graphite", 0x808080, DULL));
    public static Material Grossular = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "grossular", 0xc86400, ROUGH));
    public static Material Ilmenite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ilmenite", 0x463732, METALLIC)).setMassMultiplierAndDivider(2, 1);
    public static Material Lepidolite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lepidolite", 0xf0328c, FINE)); // TODO: Ore Gen;
    public static Material Magnesite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnesite", 0xfafab4, METALLIC));
    public static Material Magnetite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "magnetite", 0x1e1e1e, METALLIC));
    public static Material Malachite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "malachite", 0x055f05, DULL));
    public static Material Molybdenite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "molybdenite", 0x91919, METALLIC));
    public static Material Pentlandite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "pentlandite", 0xa59605, DULL));
    public static Material Phosphate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "phosphate", 0xffff00, DULL));
    public static Material Pitchblende = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "pitchblende", 0xc8d200, DULL));
    public static Material Powellite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "powellite", 0xffff00, DULL));
    public static Material Pyrite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "pyrite", 0x967828, ROUGH));
    // public static Material Pyrochlore = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "pyrochlore", 0x2b1100,METALLIC));
    public static Material Pyrolusite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "pyrolusite", 0x9696aa, DULL));
    public static Material Pyrope = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "pyrope", 0x783264, METALLIC));
    public static Material Rutile = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "rutile", 0xd40d5c, GEM_H)).setMassMultiplierAndDivider(2, 1);
    public static Material Saltpeter = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "saltpeter", 0xe6e6e6, FINE));
    public static Material Scheelite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "scheelite", 0xc88c14, DULL));
    public static Material Sheldonite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sheldonite", 0xffffc8, METALLIC));
    public static Material Soapstone = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "soapstone", 0x5f915f, DULL)); // TODO: Ore Gen;
    public static Material Sperrylite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sperrylite", 0x696969, SHINY));
    public static Material Spessartine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "spessartine", 0xff6464, DULL));
    public static Material Sphalerite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sphalerite", 0xdede00, DULL));
    public static Material Spodumene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "spodumene", 0xbeaaaa, DULL));
    public static Material Stibnite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "stibnite", 0x464646, METALLIC));
    public static Material Talc = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "talc", 0x5ab45a, DULL));
    public static Material Tantalite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tantalite", 0x915028, METALLIC));
    public static Material Tetrahedrite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tetrahedrite", 0xc82000, DULL));
    public static Material Wulfenite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "wulfenite", 0xff8000, DULL));
    public static Material VanadiumMagnetite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "vanadium_magnetite", 0x23233c, METALLIC));
    public static Material Tungstate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tungstate", 0x373223, DULL));
    public static Material Uraninite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uraninite", 0x232323, METALLIC));
    public static Material Uvarovite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uvarovite", 0xb4ffb4, DIAMOND));
    public static Material YellowLimonite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "yellow_limonite", 0xc8c800, METALLIC));
    public static Material Zircon = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "zircon", 0x63181d, DIAMOND));

    /**
     ** Ore Stones
     **/

    public static Material Bauxite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bauxite", 0xc86400, DULL));
    public static Material Lignite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lignite_coal", 0x644646, LIGNITE));
    public static Material OilShale = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "oil_shale", 0x32323c, NONE));
    public static Material Salt = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "salt", 0xfafafa, FINE));
    public static Material RockSalt = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "rock_salt", 0xf0c8c8, FINE));

    /**
     ** Gems
     **/

    public static Material Apatite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "apatite", 0x78B4FA, DIAMOND));
    public static Material Amber = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "amber", 0xFFB400, RUBY));
    public static Material Amethyst = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "amethyst", 0xd232d2, RUBY));
    public static Material Sapphire = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sapphire", 0x6464c8, GEM_V));
    public static Material BlueTopaz = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "blue_topaz", 0x0000ff, GEM_H));
    public static Material MilkyQuartz = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "milky_quartz", 0xd2d2d2, QUARTZ));
    public static Material CertusQuartz = AntimatterAPI.register(Material.class,new Material(GT5RRef.ID, "certus_quartz", 0xd2d2e6, QUARTZ, Ref.MOD_AE));
    public static Material ChargedCertusQuartz = AntimatterAPI.register(Material.class,new Material(GT5RRef.ID, "charged_certus_quartz", 0xd2d2e6, QUARTZ, Ref.MOD_AE));
    public static Material Fluix = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fluix", 0x78468C, QUARTZ, Ref.MOD_AE));
    public static Material CoalCoke = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "coal_coke", 0x8c8caa, LIGNITE));
    public static Material Glass = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "glass", 0xfafafa, SHINY));
    public static Material GreenSapphire = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "green_sapphire", 0x64c882, GEM_H));
    public static Material Jade = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "jade", 0x64ff7d, LAPIS));
    public static Material Lazurite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lazurite", 0x6478ff, LAPIS));
    public static Material LigniteCoke = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lignite_coke", 0x8c6464, LIGNITE));
    public static Material Monazite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "monazite", 0x324632, DIAMOND));
    public static Material Olivine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "olivine", 0x96ff96, RUBY));
    public static Material Opal = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "opal", 0x0000ff, RUBY));
    public static Material RedGarnet = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "red_garnet", 0xc85050, GARNET));
    public static Material Ruby = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ruby", 0xff6464, RUBY));
    public static Material Sodalite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodalite", 0x1414ff, LAPIS));
    public static Material Tanzanite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tanzanite", 0x4000c8, GEM_V));
    public static Material Topaz = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "topaz", 0xff8000, GEM_H));
    public static Material YellowGarnet = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "yellow_garnet", 0xc8c850, GARNET));

    public static Material PetroleumCoke = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "petroleum_coke", 0x9696b4, LIGNITE));

    /**
     ** Plastic
     **/

    public static Material EpoxyResin = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "epoxy_resin", 0xc88c14, DULL));
    public static Material FiberReinforcedEpoxyResin = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fiber_reinforced_epoxy_resin", 0xa07010, DULL));
    public static Material Plastic = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "plastic", 0xc8c8c8, DULL)).setDisplayNameString("Plastic (Polyethylene)");
    public static Material Polystyrene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "polystyrene", 0xbeb4aa, DULL)).setDisplayNameString("Plastic (Polystyrene)");
    public static Material Polytetrafluoroethylene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "polytetrafluoroethylene", 0x646464, DULL)).setDisplayNameString("Teflon (Polytetrafluoroethylene)");
    public static Material PolyvinylChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "polyvinyl_chloride", 0xd7e6e6, NONE)).setDisplayNameString("Plastic (Polyvinyl Chloride)");
    public static Material SiliconeRubber = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "silicone_rubber", 0x9fadbb, NONE));
    public static Material StyreneButadieneRubber = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "styrene_butadiene_rubber", 0x211a18, SHINY));

    /**
     ** Stones
     **/

    public static Material BlackGranite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "black_granite", 0x0a0a0a, ROUGH));
    public static Material BlueSchist = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "blue_schist", 0x0569be, NONE));
    public static Material GreenSchist = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "green_schist", 0x69be69, NONE));
    public static Material Kimberlite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "kimberlite", 0x64460a, NONE));
    public static Material Komatiite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "komatiite", 0xbebe69, NONE));
    public static Material Limestone = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "limestone", 0xe6c882, NONE));
    public static Material Marble = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "marble", 0xc8c8c8, NONE));
    public static Material Quartzite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "quartzite", 0xe6cdcd, QUARTZ));
    public static Material RedGranite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "red_granite", 0xff0080, ROUGH));
    public static Material Shale = AntimatterAPI.register(Material.class, new Material(GTCore.ID, "shale", 0x8E8EA8, NONE));
    public static Material Slate = AntimatterAPI.register(Material.class, new Material(GTCore.ID, "slate", 0x94979C, NONE));

    /**
     ** Misc
     **/

    public static Material Cinnabar = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cinnabar", 0x960000, ROUGH));
    public static Material GalliumArsenide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "gallium_arsenide", 0xa0a0a0, DULL));
    public static Material HighPressure = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "high_pressure", 0xc80000, NONE));
    public static Material HighCapacity = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "high_capacity", 0xb00b69, NONE));
    public static Material PlasmaContainment = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "plasma_containment", 0xffff00, NONE));
    public static Material Superconductor = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "superconductor", 0xffffff, NONE));

    /**
     *** Fluids
     **/

    /**
     * Dye Fluids
     */
    public static Material WaterMixedWhiteDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_white_dye", GT5RData.getColorFromDyeColor(DyeColor.WHITE), NONE));
    public static Material WaterMixedOrangeDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_orange_dye", GT5RData.getColorFromDyeColor(DyeColor.ORANGE), NONE));
    public static Material WaterMixedMagentaDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_magenta_dye", GT5RData.getColorFromDyeColor(DyeColor.MAGENTA), NONE));
    public static Material WaterMixedLightBlueDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_light_blue_dye", GT5RData.getColorFromDyeColor(DyeColor.LIGHT_BLUE), NONE));
    public static Material WaterMixedYellowDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_yellow_dye", GT5RData.getColorFromDyeColor(DyeColor.YELLOW), NONE));
    public static Material WaterMixedLimeDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_lime_dye", GT5RData.getColorFromDyeColor(DyeColor.LIME), NONE));
    public static Material WaterMixedPinkDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_pink_dye", GT5RData.getColorFromDyeColor(DyeColor.PINK), NONE));
    public static Material WaterMixedGrayDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_gray_dye", GT5RData.getColorFromDyeColor(DyeColor.GRAY), NONE));
    public static Material WaterMixedLightGrayDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_light_gray_dye", GT5RData.getColorFromDyeColor(DyeColor.LIGHT_GRAY), NONE));
    public static Material WaterMixedCyanDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_cyan_dye", GT5RData.getColorFromDyeColor(DyeColor.CYAN), NONE));
    public static Material WaterMixedPurpleDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_purple_dye", GT5RData.getColorFromDyeColor(DyeColor.PURPLE), NONE));
    public static Material WaterMixedBlueDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_blue_dye", GT5RData.getColorFromDyeColor(DyeColor.BLUE), NONE));
    public static Material WaterMixedBrownDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_brown_dye", GT5RData.getColorFromDyeColor(DyeColor.BROWN), NONE));
    public static Material WaterMixedGreenDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_green_dye", GT5RData.getColorFromDyeColor(DyeColor.GREEN), NONE));
    public static Material WaterMixedRedDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_red_dye", GT5RData.getColorFromDyeColor(DyeColor.RED), NONE));
    public static Material WaterMixedBlackDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "water_mixed_black_dye", GT5RData.getColorFromDyeColor(DyeColor.BLACK), NONE));

    public static Material ChemicalWhiteDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_white_dye", GT5RData.getColorFromDyeColor(DyeColor.WHITE), NONE));
    public static Material ChemicalOrangeDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_orange_dye", GT5RData.getColorFromDyeColor(DyeColor.ORANGE), NONE));
    public static Material ChemicalMagentaDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_magenta_dye", GT5RData.getColorFromDyeColor(DyeColor.MAGENTA), NONE));
    public static Material ChemicalLightBlueDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_light_blue_dye", GT5RData.getColorFromDyeColor(DyeColor.LIGHT_BLUE), NONE));
    public static Material ChemicalYellowDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_yellow_dye", GT5RData.getColorFromDyeColor(DyeColor.YELLOW), NONE));
    public static Material ChemicalLimeDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_lime_dye", GT5RData.getColorFromDyeColor(DyeColor.LIME), NONE));
    public static Material ChemicalPinkDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_pink_dye", GT5RData.getColorFromDyeColor(DyeColor.PINK), NONE));
    public static Material ChemicalGrayDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_gray_dye", GT5RData.getColorFromDyeColor(DyeColor.GRAY), NONE));
    public static Material ChemicalLightGrayDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_light_gray_dye", GT5RData.getColorFromDyeColor(DyeColor.LIGHT_GRAY), NONE));
    public static Material ChemicalCyanDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_cyan_dye", GT5RData.getColorFromDyeColor(DyeColor.CYAN), NONE));
    public static Material ChemicalPurpleDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_purple_dye", GT5RData.getColorFromDyeColor(DyeColor.PURPLE), NONE));
    public static Material ChemicalBlueDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_blue_dye", GT5RData.getColorFromDyeColor(DyeColor.BLUE), NONE));
    public static Material ChemicalBrownDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_brown_dye", GT5RData.getColorFromDyeColor(DyeColor.BROWN), NONE));
    public static Material ChemicalGreenDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_green_dye", GT5RData.getColorFromDyeColor(DyeColor.GREEN), NONE));
    public static Material ChemicalRedDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_red_dye", GT5RData.getColorFromDyeColor(DyeColor.RED), NONE));
    public static Material ChemicalBlackDye = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chemical_black_dye", GT5RData.getColorFromDyeColor(DyeColor.BLACK), NONE));

    /**
     ** Organic
     **/

    public static Material AceticAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "acetic_acid", 0xc8b4a0, NONE));
    public static Material Acetone = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "acetone", 0xafafaf, NONE));
    public static Material AllylChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "allyl_chloride", 0x87deaa, NONE));
    public static Material Benzaldehyde = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "benzaldehyde", 0xf7dea3, NONE));
    public static Material Benzene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "benzene", 0x1a1a1a, NONE));
    public static Material Biomass = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "biomass", 0x00ff00, NONE));
    public static Material BisphenolA = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bisphenol_a", 0xd4b300, NONE));
    public static Material Butanediol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "butanediol", 0xff8000, NONE));
    public static Material Butanol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "butanol", 0xff8000, NONE));
    public static Material Butenol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "butenol", 0xff8000, NONE));
    public static Material CharcoalByproducts = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "charcoal_byproducts", 0x784421, NONE));
    public static Material Chloramine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chloramine", 0x3f9f80, NONE));
    public static Material Chloroform = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chloroform", 0x892ca0, NONE));
    public static Material Cumene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cumene", 0x552200, NONE));
    public static Material Chlorobenzene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chlorobenzene", 0x004455, NONE));
    public static Material Dimethyldichlorosilane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dimethyldichlorosilane", 0x441650, NONE));
    public static Material Dimethylhydrazine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dimethylhydrazine", 0x000055, NONE));
    public static Material Epichlorohydrin = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "epichlorohydrin", 0x501d05, NONE));
    public static Material Ethanediol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ethanediol", 0xff8000, NONE));
    public static Material Ethanol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ethanol", 0xff8000, NONE));
    public static Material Ethenol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ethenol", 0xff8000, NONE));
    public static Material FermentedBiomass = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fermented_biomass", 0x09964a, NONE));
    public static Material Glue = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "glue", 0xc8c400, NONE));
    public static Material Heptanol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "heptanol", 0xff8000, NONE));
    public static Material Honey = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "honey", 0xd2c800, NONE)); // TODO: Only when Forestry's present;
    public static Material Isoprene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "isoprene", 0x141414, NONE));
    public static Material Methanol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "methanol", 0xaa8800, NONE));
    public static Material MethylAcetate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "methyl_acetate", 0xeec6af, NONE));
    public static Material Naphtha = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "naphtha", 0xffff00, NONE));
    public static Material Phenol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "phenol", 0x784421, NONE));
    public static Material PhosphoricAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "phosphoric_acid", 0xdcdc00, NONE));
    public static Material PolyvinylAcetate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "polyvinyl_acetate", 0xff9955, NONE));
    public static Material Propanediol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "propanediol", 0xff8000, NONE));
    public static Material Propenol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "propenol", 0xff8000, NONE));
    public static Material Propanol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "propanol", 0xff8000, NONE));
    public static Material SquidInk = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "squid_ink", 0x000000, NONE));
    public static Material Styrene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "styrene", 0xd2c8be, NONE));
    public static Material SulfuricNaphtha = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sulfuric_naphtha", 0xffff00, NONE));
    public static Material Toluene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "toluene", 0x501d05, NONE));
    public static Material VinylAcetate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "vinyl_acetate", 0xffb380, NONE));
    public static Material WoodTar = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "wood_tar", 0x28170b, NONE));
    public static Material WoodVinegar = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "wood_vinegar", 0xd45500, NONE));

    /**
     ** Inorganic
     **/

    public static Material AluminiumFluoride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "aluminium_fluoride", 0xc8bebe, NONE));
    public static Material Antimatter = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "anti_matter", 0x8000c4, NONE));
    public static Material BlueVitriol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "blue_vitriol", 0x4242DE, NONE));
    public static Material GreenVitriol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "green_vitriol", 0x42de42, NONE));
    public static Material RedVitriol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "red_vitriol", 0xde4242, NONE));
    public static Material PinkVitriol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "pink_vitriol", 0xde6f6f, NONE));
    public static Material CyanVitriol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cyan_vitriol", 0x6fdede, NONE));
    public static Material WhiteVitriol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "white_vitriol", 0xdedede, NONE));
    public static Material GrayVitriol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "gray_vitriol", 0x6f6f6f, NONE));
    public static Material MartianVitriol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "martian_vitriol", 0xde42de, NONE));
    public static Material VitriolOfClay = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "vitriol_of_clay", 0x42dede, NONE));


    public static Material AquaRegia = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "aqua_regia", 0x40ff40, NONE));
    public static Material CalciumAcetateSolution = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "calcium_acetate_solution", 0xDCC8B4, NONE));
    public static Material ChloroplatinicAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chloroplatinic_acid", 0xff4646, NONE));
    public static Material Coolant = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "coolant", 0x0506be, NONE));
    public static Material Cryolite = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "cryolite", 0xc8bebe, NONE));
    public static Material DilutedHydrochloricAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "diluted_hydrochloric_acid", 0x99a7a3, NONE));
    public static Material DilutedSulfuricAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "diluted_sulfuric_acid", 0xc07820, NONE));
    public static Material DistilledWater = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "distilled_water", 0x5C5CFF, NONE));
    public static Material DrillingFluid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "drilling_fluid", 0xffffff, NONE)); // TODO: Perhaps for a bedrock drill;
    public static Material HexafluorosilicicAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hexafluorosilicic_acid", 0xbec8be, NONE));
    public static Material HotCoolant = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_coolant", 0x7a111a, NONE));
    public static Material HeavyWater = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "heavy_water", 0xffff64, NONE));
    public static Material SemiheavyWater = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "semiheavy_water", 0xc8c89b, NONE));
    public static Material TritiatedWater = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tritiated_water", 0xff6464, NONE));
    public static Material HotHeavyWater = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_heavy_water", 0xffff64, NONE));
    public static Material HotSemiheavyWater = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_semiheavy_water", 0xc8c89b, NONE));
    public static Material HotTritiatedWater = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_tritiated_water", 0xff6464, NONE));
    public static Material HotMoltenSodium = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_molten_sodium", 0x000096, NONE, S));
    public static Material HotMoltenTin = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_molten_tin", 0xdcdcdc, NONE, Sn));
    public static Material HotMoltenLithiumChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_molten_lithium_chloride", 0xdedefa, NONE));
    public static Material HydrochloricAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydrochloric_acid", 0x6f8a91, NONE));
    public static Material HydrofluoricAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydrofluoric_acid", 0x0088aa, NONE));
    public static Material HydrogenFluoride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydrogen_fluoride", 0x00f0f0, NONE));
    public static Material HydrogenPeroxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydrogen_peroxide", 0xf8efb4, NONE));
    public static Material HypochlorousAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hypochlorous_acid", 0x6f8a91, NONE));
    public static Material Lubricant = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lubricant", 0xffc400, NONE));
    public static Material NickelSulfate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nickel_sulfate", 0xffffff, NONE));
    public static Material NitricAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nitric_acid", 0xe6e2ab, NONE));
    public static Material PeroxydisulfuricAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "peroxydisulfuricacid", 0xff9000, NONE));
    public static Material SaltWater = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "salt_water", 0x0760b9, NONE));
    public static Material SodiumPersulfateSolution = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sodium_perfulate_solution", 0x006646, NONE));
    public static Material SulfuricAcid = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sulfuric_acid", 0xff8000, NONE));
    public static Material Tar = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tar", 0x0a0a0a, NONE));
    public static Material ThoriumSalt = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "thorium_salt", 0x001e00, NONE));
    public static Material TitaniumTetrachloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "titanium_tetrachloride", 0xd40d5c, NONE));
    public static Material UUAmplifier = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uu_amplifier", 0x600080, NONE));
    public static Material UUMatter = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uu_matter", 0x8000c4, NONE));

    /**
     ** Fuels
     **/

    public static Material BioDiesel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "bio_diesel", 0xff8000, NONE));
    public static Material Creosote = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "creosote", 0x804000, NONE));
    public static Material Diesel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "diesel", 0xffff00, NONE));
    public static Material FishOil = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fish_oil", 0xffc400, NONE));
    public static Material NitroDiesel = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nitro_diesel", 0xc8ff00, NONE));
    public static Material Kerosene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "kerosene", 0x0000FF, NONE));
    public static Material FuelOil = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "fuel", 0xffff00, NONE)).setDisplayNameString("Fuel Oil");
    public static Material Gasoline = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "gasoline", 0xFFA500, NONE));
    public static Material Oil = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "oil", 0x0a0a0a, NONE));
    public static Material OilHeavy = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "heavy_oil", 0x0a0a0a, NONE));
    public static Material OilLight = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "light_oil", 0x0a0a0a, NONE));
    public static Material SeedOil = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "seed_oil", 0xc4ff00, NONE));
    public static Material Glycerol = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "glycerol", 0x87de87, NONE));
    public static Material GlycerylTrinitrate = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "glyceryl_trinitrate", 0x87de87, NONE)).setDisplayNameString("Nitroglycerin (Glyceryl Trinitrate)");

    /**
     *** Gases/Plasmas
     **/

    /**
     ** Organic
     **/

    public static Material Butane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "butane", 0xb6371e, NONE));
    public static Material Butadiene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "butadiene", 0xe86900, NONE));
    public static Material Butene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "butene", 0xcf5005, NONE));
    public static Material HotCarbonDioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hot_carbon_dioxide", 0xa9d0f5, NONE));
    public static Material CarbonDioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "carbon_dioxide", 0xa9d0f5, NONE));
    public static Material CarbonMonoxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "carbon_monoxide", 0x0e4880, NONE));
    public static Material Chloromethane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "chloromethane", 0xc82ca0, NONE));
    public static Material Dimethylamine = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dimethylamine", 0x554469, NONE));
    public static Material Ethane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ethane", 0xc8c8ff, NONE));
    public static Material Ethylene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ethylene", 0xe1e1e1, NONE));
    public static Material LPG = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "lpg", 0xffff00, NONE)).setDisplayNameString("LPG");
    public static Material Methane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "methane", 0xffffff, NONE));
    public static Material NaturalGas = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "natural_gas", 0xffffff, NONE));
    public static Material Propane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "propane", 0xfae250, NONE));
    public static Material Propene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "propene", 0xffdd55, NONE));
    public static Material RefineryGas = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "refinery_gas", 0xffffff, NONE));
    public static Material SulfuricGas = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sulfuric_gas", 0xffffff, NONE));
    public static Material Tetrafluoroethylene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "tetrafluoroethylene", 0x7d7d7d, NONE));
    public static Material VinylChloride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "vinyl_chloride", 0xfff0f0, NONE));
    public static Material WoodGas = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "wood_gas", 0xdecd87, NONE));

    /**
     ** Inorganic
     **/

    public static Material Air = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "air", 0xc9e3fc, NONE));
    public static Material Ammonia = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "ammonia", 0x3f3480, NONE));
    public static Material DinitrogenTetroxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "dinitrogen_tetroxide", 0x004184, NONE));
    public static Material HydrogenSulfide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydrogen_sulfide", 0xffffff, NONE));
    public static Material NitrogenMonoxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nitrogen_monoxide", 0x7dc8f0, NONE));
    public static Material NitrogenDioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "nitrogen_dioxide", 0x64afff, NONE));
    public static Material NobleGases = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "noble_gases", 0xc9e3fc, NONE));
    public static Material Steam = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam", 0xa0a0a0, NONE));
    public static Material SuperheatedSteam = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "superheated_steam", 0xa0a0a0, NONE));
    public static Material SulfurDioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sulfur_dioxide", 0xc8c819, NONE));
    public static Material SulfurTrioxide = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "sulfur_trioxide", 0xa0a014, NONE));

    /**
     ** Cracked
     **/

    public static Material HydroCrackedEthane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_ethane", 0xc8c8ff, NONE));
    //public static Material HydroCrackedEthylene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_ethylene", 0xe1e1e1, NONE));
    public static Material HydroCrackedPropane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_propane", 0xfae250, NONE));
    //public static Material HydroCrackedPropene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_propene", 0xffdd55, NONE));
    public static Material HydroCrackedButane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_butane", 0xb6371e, NONE));
    //public static Material HydroCrackedButene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_butene", 0xcf5005, NONE));
    //public static Material HydroCrackedButadiene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_butadiene", 0xe86900, NONE));
    public static Material HydroCrackedNaphtha = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_naphtha", 0xffff00, NONE));
    public static Material HydroCrackedRefineryGas = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "hydro_cracked_refinery_gas", 0xffffff, NONE));
    public static Material SteamCrackedEthane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_ethane", 0xc8c8ff, NONE));
    //public static Material SteamCrackedEthylene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_ethylene", 0xe1e1e1, NONE));
    public static Material SteamCrackedPropane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_propane", 0xfae250, NONE));
    //public static Material SteamCrackedPropene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_propene", 0xffdd55, NONE));
    public static Material SteamCrackedButane = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_butane", 0xb6371e, NONE));
    //public static Material SteamCrackedButene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_butene", 0xcf5005, NONE));
    //public static Material SteamCrackedButadiene = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_butadiene", 0xe86900, NONE));
    public static Material SteamCrackedNaphtha = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_naphtha", 0xffff00, NONE));
    public static Material SteamCrackedRefineryGas = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "steam_cracked_refinery_gas", 0xffffff, NONE));

    /**
     *  Tetrafluorides
     **/
    public static Material UraniumTetrafluoride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium_tetrafluoride", 0x21d921, NONE));
    public static Material Uranium238Tetrafluoride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium_238_tetrafluoride", 0x21d921, NONE));
    public static Material Uranium235Tetrafluoride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium_235_tetrafluoride", 0x21d921, NONE));

    /**
     *  Hexafluorides
     **/
    public static Material UraniumHexafluoride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium_hexafluoride", 0x10c810, NONE));
    public static Material Uranium235Hexafluoride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium_235_hexafluoride", 0x10c810, NONE));
    public static Material Uranium238Hexafluoride = AntimatterAPI.register(Material.class, new Material(GT5RRef.ID, "uranium_238_hexafluoride", 0x10c810, NONE));

    // TODO go through the GT_Loader_Item_Block_And_Fluid and make sure all explicitly added fluids have the LIQUID tag
    public static void init() {
        // TODO assign correct handle materials
        // for (Material material : generated) {
        // if (material == Blaze) {
        // material.handleMaterial = "blaze";
        // } /*else if (aMaterial.contains(SubTag.MAGICAL) &&
        // aMaterial.contains(SubTag.CRYSTAL) && Utils.isModLoaded(MOD_ID_TC)) {
        // aMaterial.mHandleMaterial = Thaumium;
        // }*/ else if (material.getMass() > Element.Tc.getMass() * 2) {
        // material.handleMaterial = Tungstensteel.;
        // } else if (material.getMass() > Element.Tc.getMass()) {
        // material.handleMaterial = Steel;
        // } else {
        // material.handleMaterial = Wood;
        // }
        // }
        // If using small ore markers, every normal ore needs a small version. This
        // greatly increases block usage
    }
}
