package mekanism.common.registries;

import mekanism.api.gas.Gas;
import mekanism.api.gas.GasAttributes;
import mekanism.api.gas.Slurry;
import mekanism.common.ChemicalConstants;
import mekanism.common.Mekanism;
import mekanism.common.Resource;
import mekanism.common.registration.impl.GasDeferredRegister;
import mekanism.common.registration.impl.GasRegistryObject;
import mekanism.common.registration.impl.SlurryRegistryObject;

//TODO: Move things that are only used by mekanism generators to that module
public class MekanismGases {

    //TODO: Pass something like FluidAttributes
    public static final GasDeferredRegister GASES = new GasDeferredRegister(Mekanism.MODID);

    public static final GasRegistryObject<Gas> HYDROGEN = GASES.register(ChemicalConstants.HYDROGEN);
    public static final GasRegistryObject<Gas> OXYGEN = GASES.register(ChemicalConstants.OXYGEN);
    //TODO: Figure out how we want to handle the existence of steam and water vapor
    public static final GasRegistryObject<Gas> STEAM = GASES.register("steam", () -> new Gas(GasAttributes.builder(Mekanism.rl("block/liquid/liquid_steam"))));
    public static final GasRegistryObject<Gas> WATER_VAPOR = GASES.register("water_vapor", () -> new Gas(GasAttributes.builder(Mekanism.rl("block/liquid/liquid_steam"))));
    public static final GasRegistryObject<Gas> CHLORINE = GASES.register(ChemicalConstants.CHLORINE);
    public static final GasRegistryObject<Gas> SULFUR_DIOXIDE = GASES.register(ChemicalConstants.SULFUR_DIOXIDE);
    public static final GasRegistryObject<Gas> SULFUR_TRIOXIDE = GASES.register(ChemicalConstants.SULFUR_TRIOXIDE);
    public static final GasRegistryObject<Gas> SULFURIC_ACID = GASES.register(ChemicalConstants.SULFURIC_ACID);
    public static final GasRegistryObject<Gas> HYDROGEN_CHLORIDE = GASES.register(ChemicalConstants.HYDROGEN_CHLORIDE);
    //Internal gases
    public static final GasRegistryObject<Gas> ETHENE = GASES.register(ChemicalConstants.ETHENE);
    public static final GasRegistryObject<Gas> SODIUM = GASES.register(ChemicalConstants.SODIUM);
    public static final GasRegistryObject<Gas> BRINE = GASES.register("brine", 0xFEEF9C);
    public static final GasRegistryObject<Gas> DEUTERIUM = GASES.register(ChemicalConstants.DEUTERIUM);
    public static final GasRegistryObject<Gas> TRITIUM = GASES.register("tritium", 0x64FF70);
    public static final GasRegistryObject<Gas> FUSION_FUEL = GASES.register("fusion_fuel", 0x7E007D);
    public static final GasRegistryObject<Gas> LITHIUM = GASES.register(ChemicalConstants.LITHIUM);
    //TODO: Rename liquid osmium? Also make it not visible again in JEI and the like?
    public static final GasRegistryObject<Gas> LIQUID_OSMIUM = GASES.register("liquid_osmium", 0x52BDCA);

    public static final SlurryRegistryObject<Slurry, Slurry> IRON_SLURRY = GASES.registerSlurry(Resource.IRON);
    public static final SlurryRegistryObject<Slurry, Slurry> GOLD_SLURRY = GASES.registerSlurry(Resource.GOLD);
    public static final SlurryRegistryObject<Slurry, Slurry> OSMIUM_SLURRY = GASES.registerSlurry(Resource.OSMIUM);
    public static final SlurryRegistryObject<Slurry, Slurry> COPPER_SLURRY = GASES.registerSlurry(Resource.COPPER);
    public static final SlurryRegistryObject<Slurry, Slurry> TIN_SLURRY = GASES.registerSlurry(Resource.TIN);
}