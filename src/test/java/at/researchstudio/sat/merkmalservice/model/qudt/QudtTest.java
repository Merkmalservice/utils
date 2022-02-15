package at.researchstudio.sat.merkmalservice.model.qudt;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class QudtTest {
    @Test
    public void testReadUnit() {
        Unit metre = Qudt.loadUnit("M");
        assertNotNull(metre);
    }

    @Test
    public void testConvertCelsiusFahrenheit() {
        QuantityValue celsius100 = new QuantityValue(100, Qudt.unitFromLocalname("DEG_C"));
        QuantityValue fahrenheit = Qudt.convert(celsius100, Qudt.unitIriFromLocalname("DEG_F"));
        assertNotNull(fahrenheit);
        assertEquals(212.00039299999997, fahrenheit.getValue());
        assertEquals(Qudt.unitIriFromLocalname("DEG_F"), fahrenheit.getUnit().getIri());
    }

    @Test
    public void testConvertCelsiusFahrenheit2() {
        assertEquals(212.00039299999997, Qudt.convert(100, Units.DEG_C, Units.DEG_F).getValue());
    }

    @Test
    public void testPrefix() {
        Prefix kilo = Qudt.Prefixes.Kilo;
        assertNotNull(kilo);
        assertEquals(1000, kilo.getMultiplier());
        assertEquals(Qudt.prefix(kilo.getIri()), kilo);
        assertEquals(Qudt.prefix(kilo.getIri().toString()), kilo);
    }

    @Test
    public void testUnit() {
        Unit metre = Qudt.Units.M;
        assertTrue(metre.hasLabel("Metre"));
        assertTrue(metre.hasLabel("Meter"));
        assertEquals("Metre", metre.getLabelForLanguageTag("en").get().getString());
        assertEquals(Qudt.unit(metre.getIri()), metre);
        assertEquals(Qudt.unit(metre.getIri().toString()), metre);
    }

    @Test
    public void testQuantityKind() {
        QuantityKind length = Qudt.QuantityKinds.Length;
        assertTrue(length.hasLabel("Length"));
        assertEquals(Qudt.quantityKind(length.getIri()), length);
        assertEquals(Qudt.quantityKind(length.getIri().toString()), length);
    }

    @Test
    public void testQuantityKindForUnit() {
        Unit unit = Qudt.unitFromLabel("Newton Meter");
        Set<QuantityKind> broad = Qudt.quantityKinds(unit);
        assertTrue(broad.contains(Qudt.quantityKindFromLocalname("Torque")));
        assertTrue(broad.contains(Qudt.quantityKindFromLocalname("MomentOfForce")));
        unit = Qudt.Units.PA__PER__BAR;
        broad = Qudt.quantityKindsBroad(unit);
        assertTrue(broad.contains(Qudt.QuantityKinds.PressureRatio));
        assertTrue(broad.contains(Qudt.QuantityKinds.DimensionlessRatio));
    }

    @Test
    public void testDerivedUnitFromMap() {
        assertTrue(
                Qudt.derivedUnit(List.of(Map.entry(Qudt.Units.M, -3)))
                        .contains(Qudt.Units.PER__M3));
        assertTrue(
                Qudt.derivedUnit(Qudt.Units.MilliA, 1, Qudt.Units.IN, -1)
                        .contains(Qudt.Units.MilliA__PER__IN));
        assertTrue(
                Qudt.derivedUnit(Qudt.Units.MOL, 1, Qudt.Units.M, -2, Qudt.Units.SEC, -1)
                        .contains(Qudt.Units.MOL__PER__M2__SEC));
    }

    @Test
    public void testUnitFromLabel() {
        assertEquals(Qudt.Units.N, Qudt.unitFromLabel("Newton"));
        assertEquals(Qudt.Units.M, Qudt.unitFromLabel("Metre"));
        assertEquals(Qudt.Units.M2, Qudt.unitFromLabel("SQUARE_METRE"));
        assertEquals(Qudt.Units.M2, Qudt.unitFromLabel("SQUARE METRE"));
        assertEquals(Qudt.Units.M3, Qudt.unitFromLabel("Cubic Metre"));
        assertEquals(Qudt.Units.GM, Qudt.unitFromLabel("Gram"));
        assertEquals(Qudt.Units.SEC, Qudt.unitFromLabel("second"));
        assertEquals(Qudt.Units.HZ, Qudt.unitFromLabel("Hertz"));
        assertEquals(Qudt.Units.DEG_C, Qudt.unitFromLabel("degree celsius"));
        assertEquals(Qudt.Units.DEG_F, Qudt.unitFromLabel("degree fahrenheit"));
        assertEquals(Qudt.Units.A, Qudt.unitFromLabel("ampere"));
        assertEquals(Qudt.Units.V, Qudt.unitFromLabel("volt"));
        assertEquals(Qudt.Units.W, Qudt.unitFromLabel("Watt"));
        assertEquals(Qudt.Units.LUX, Qudt.unitFromLabel("Lux"));
        assertEquals(Qudt.Units.LM, Qudt.unitFromLabel("Lumen"));
        assertEquals(Qudt.Units.CD, Qudt.unitFromLabel("Candela"));
        assertEquals(Qudt.Units.PA, Qudt.unitFromLabel("Pascal"));
        assertEquals(Qudt.Units.RAD, Qudt.unitFromLabel("Radian"));
        assertEquals(Qudt.Units.J, Qudt.unitFromLabel("Joule"));
        assertEquals(Qudt.Units.K, Qudt.unitFromLabel("Kelvin"));
        assertEquals(Qudt.Units.SR, Qudt.unitFromLabel("Steradian"));
    }

    @Test
    public void testUnitFromFactors() {
        assertThrows(
                IllegalArgumentException.class, () -> Qudt.derivedUnitFromFactors(Qudt.Units.M));
        Set<Unit> units = Qudt.derivedUnitFromFactors(Qudt.Units.M, 3);
        assertTrue(units.contains(Qudt.Units.M3));
        units = Qudt.derivedUnitFromFactors(Qudt.Units.KiloGM, 1, Qudt.Units.M, -3);
        assertTrue(units.contains(Qudt.Units.KiloGM__PER__M3));
        units = Qudt.derivedUnit(Qudt.Units.MOL, 1, Qudt.Units.M, -2, Qudt.Units.SEC, -1);
        assertTrue(units.contains(Qudt.Units.MOL__PER__M2__SEC));
        units =
                Qudt.derivedUnit(
                        Qudt.Units.K,
                        1,
                        Qudt.Units.M,
                        2,
                        Qudt.Units.KiloGM,
                        -1,
                        Qudt.Units.SEC,
                        -1);
        assertTrue(units.contains(Qudt.Units.K__M2__PER__KiloGM__SEC));
        units =
                Qudt.derivedUnit(
                        Qudt.Units.BTU_IT,
                        1,
                        Qudt.Units.FT,
                        1,
                        Qudt.Units.FT,
                        -2,
                        Qudt.Units.HR,
                        -1,
                        Qudt.Units.DEG_F,
                        -1);
        assertTrue(units.contains(Qudt.Units.BTU_IT__FT__PER__FT2__HR__DEG_F));
    }

    @Test
    public void testDerivedUnit1() {
        Set<Unit> units = Qudt.derivedUnit(Qudt.Units.M, 3);
        assertTrue(units.contains(Qudt.Units.M3));
        units = Qudt.derivedUnit(Qudt.Units.M, 2);
        assertTrue(units.contains(Qudt.Units.M2));
        units = Qudt.derivedUnit(Qudt.Units.K, -1);
        assertTrue(units.contains(Qudt.Units.PER__K));
        units = Qudt.derivedUnit(Qudt.Units.M, -2);
        assertTrue(units.contains(Qudt.Units.PER__M2));
    }

    @Test
    public void testDerivedUnitByIri1() {
        Set<Unit> units = Qudt.derivedUnit(Qudt.Units.M.getIri(), 3);
        assertTrue(units.contains(Qudt.Units.M3));
        units = Qudt.derivedUnit(Qudt.Units.M.getIri(), 2);
        assertTrue(units.contains(Qudt.Units.M2));
        units = Qudt.derivedUnit(Qudt.Units.K.getIri(), -1);
        assertTrue(units.contains(Qudt.Units.PER__K));
        units = Qudt.derivedUnit(Qudt.Units.M.getIri(), -2);
        assertTrue(units.contains(Qudt.Units.PER__M2));
    }

    @Test
    public void testDerivedUnit2() {
        Set<Unit> units = Qudt.derivedUnit(Qudt.Units.KiloGM, 1, Qudt.Units.M, -3);
        assertTrue(units.contains(Qudt.Units.KiloGM__PER__M3));
        units = Qudt.derivedUnit(Qudt.scaledUnit("Kilo", "Gram"), 1, Qudt.Units.M, -3);
        assertTrue(units.contains(Qudt.Units.KiloGM__PER__M3));
        units = Qudt.derivedUnit(Qudt.Units.N, 1, Qudt.Units.M, -2);
        assertTrue(units.contains(Qudt.Units.N__PER__M2));
    }

    @Test
    public void testDerivedUnit3() {
        Set<Unit> units = Qudt.derivedUnit(Qudt.Units.MOL, 1, Qudt.Units.M, -2, Qudt.Units.SEC, -1);
        assertTrue(units.contains(Qudt.Units.MOL__PER__M2__SEC));
    }

    @Test
    public void testDerivedUnit4() {
        Set<Unit> units =
                Qudt.derivedUnit(
                        Qudt.Units.K,
                        1,
                        Qudt.Units.M,
                        2,
                        Qudt.Units.KiloGM,
                        -1,
                        Qudt.Units.SEC,
                        -1);
        assertTrue(units.contains(Qudt.Units.K__M2__PER__KiloGM__SEC));
        units =
                Qudt.derivedUnit(
                        Qudt.Units.M,
                        1,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2,
                        Qudt.Units.M,
                        -2);
        assertTrue(units.contains(Qudt.Units.N__PER__M2));
    }

    @Test
    public void testDerivedUnit5() {
        Set<Unit> units =
                Qudt.derivedUnit(
                        Qudt.Units.BTU_IT,
                        1,
                        Qudt.Units.FT,
                        1,
                        Qudt.Units.FT,
                        -2,
                        Qudt.Units.HR,
                        -1,
                        Qudt.Units.DEG_F,
                        -1);
        assertTrue(units.contains(Qudt.Units.BTU_IT__FT__PER__FT2__HR__DEG_F));
        units =
                Qudt.derivedUnit(
                        Qudt.Units.M,
                        1,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2,
                        Qudt.Units.M,
                        -2,
                        Qudt.Units.M,
                        1);
        assertTrue(units.contains(Qudt.Units.N__M__PER__M2));
        units =
                Qudt.derivedUnit(
                        Qudt.Units.M,
                        2,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2,
                        Qudt.Units.M,
                        -2);
        assertTrue(units.contains(Qudt.Units.N__M__PER__M2));
    }

    @Test
    public void testScaledUnit() {
        Unit unit = Qudt.scaledUnit("Nano", "Meter");
        assertEquals(Qudt.Units.NanoM, unit);
        unit = Qudt.scaledUnit("Giga", "Hertz");
        assertEquals(Qudt.Units.GigaHZ, unit);
        unit = Qudt.scaledUnit("Kilo", "Gram");
        assertEquals(Qudt.Units.KiloGM, unit);
        unit = Qudt.scaledUnit(Qudt.Prefixes.Nano, Qudt.Units.M);
        assertEquals(Qudt.Units.NanoM, unit);
    }

    @Test
    public void testGetUnitFactors() {
        Unit unit = Qudt.unitFromLabel("newton meter");
        List<FactorUnit> unitFactors = Qudt.factorUnits(unit);
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.unitFromLabel("meter"), 2)));
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.unitFromLabel("kilogram"), 1)));
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.unitFromLabel("second"), -2)));
        unit = Qudt.unitFromLabel("newton meter per square meter");
        unitFactors = Qudt.factorUnits(unit);
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.unitFromLabel("meter"), 2)));
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.unitFromLabel("meter"), -2)));
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.unitFromLabel("kilogram"), 1)));
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.unitFromLabel("second"), -2)));
        unit = Qudt.Units.KiloN__M;
        unitFactors = Qudt.factorUnits(unit);
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.Units.KiloN, 1)));
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.Units.M, 1)));
    }

    @Test
    public void testGetUnitFactorsUnscaled() {
        Unit unit = Qudt.Units.KiloN__M;
        List<FactorUnit> unitFactors = Qudt.factorUnits(unit);
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.Units.KiloN, 1)));
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.Units.M, 1)));
        unitFactors = Qudt.unscaleFactorUnits(unitFactors);
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.Units.N, 1)));
        assertTrue(unitFactors.contains(new FactorUnit(Qudt.Units.M, 1)));
    }

    @Test
    public void testUnitless() {
        assertEquals(
                new QuantityValue(1.1234, Qudt.Units.UNITLESS),
                Qudt.convert(1.1234, Qudt.Units.KiloGM__PER__M3, Qudt.Units.UNITLESS));
        assertEquals(
                new QuantityValue(1.1234, Qudt.Units.KiloGM__PER__M3),
                Qudt.convert(1.1234, Qudt.Units.UNITLESS, Qudt.Units.KiloGM__PER__M3));
        assertEquals(
                new QuantityValue(1.1234, Qudt.Units.UNITLESS),
                Qudt.convert(1.1234, Qudt.Units.UNITLESS, Qudt.Units.UNITLESS));
    }
}
