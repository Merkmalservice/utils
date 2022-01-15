package at.researchstudio.sat.merkmalservice.qudtifc;

import at.researchstudio.sat.merkmalservice.model.ifc.IfcDerivedUnit;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcSIUnit;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcUnit;
import at.researchstudio.sat.merkmalservice.model.qudt.Qudt;
import at.researchstudio.sat.merkmalservice.model.qudt.Unit;
import at.researchstudio.sat.merkmalservice.model.qudt.exception.NotFoundException;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasure;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasurePrefix;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class QudtIfcMapperTest {
    private static final IfcSIUnit kg =
            new IfcSIUnit(
                    1, IfcUnitType.MASSUNIT, IfcUnitMeasure.GRAM, IfcUnitMeasurePrefix.KILO, false);
    private static final IfcSIUnit g =
            new IfcSIUnit(
                    1, IfcUnitType.MASSUNIT, IfcUnitMeasure.GRAM, IfcUnitMeasurePrefix.NONE, false);
    private static final IfcSIUnit sec =
            new IfcSIUnit(
                    2,
                    IfcUnitType.TIMEUNIT,
                    IfcUnitMeasure.SECOND,
                    IfcUnitMeasurePrefix.NONE,
                    false);
    private static final IfcSIUnit m =
            new IfcSIUnit(
                    3,
                    IfcUnitType.LENGTHUNIT,
                    IfcUnitMeasure.METRE,
                    IfcUnitMeasurePrefix.NONE,
                    false);
    private static final IfcSIUnit cm =
                    new IfcSIUnit(
                                    3,
                                    IfcUnitType.LENGTHUNIT,
                                    IfcUnitMeasure.METRE,
                                    IfcUnitMeasurePrefix.CENTI,
                                    false);

    @Test
    public void testBasicFunctionality() {
        IfcUnit unit = QudtIfcMapper.mapQudtUnitToIfcUnit(Qudt.Units.M);
        assertEquals(IfcUnitType.LENGTHUNIT, unit.getType());
    }

    @Test
    public void testEnergy() {
        IfcDerivedUnit joule =
                new IfcDerivedUnit(100, IfcUnitType.ENERGYUNIT, Map.of(kg, 1, sec, -2, m, 2), false);
        Set<Unit> mappedUnits = QudtIfcMapper.mapIfcUnitToQudtUnit(joule);
        assertTrue(mappedUnits.contains(Qudt.Units.J));
        IfcDerivedUnit mJoule =
                new IfcDerivedUnit(100, IfcUnitType.ENERGYUNIT, Map.of(g, 1, sec, -2, m, 2), false);
        mappedUnits = QudtIfcMapper.mapIfcUnitToQudtUnit(mJoule);
        assertTrue(mappedUnits.contains(Qudt.Units.MilliJ));
        assertTrue(mappedUnits.contains(Qudt.Units.MilliN__M));
    }

    @Test
    public void testMassDensity() {
        IfcDerivedUnit kgPerM3 =
                        new IfcDerivedUnit(101, IfcUnitType.MASSDENSITYUNIT, Map.of(kg, 1, m, -3), false);
        Set<Unit> mappedUnits = QudtIfcMapper.mapIfcUnitToQudtUnit(kgPerM3);
        assertTrue(mappedUnits.contains(Qudt.Units.KiloGM__PER__M3));
        IfcDerivedUnit gPerCm3 =
                        new IfcDerivedUnit(101, IfcUnitType.MASSDENSITYUNIT, Map.of(kg, 1, cm, -3), false);
        mappedUnits = QudtIfcMapper.mapIfcUnitToQudtUnit(gPerCm3);
        assertTrue(mappedUnits.contains(Qudt.Units.GM__PER__CentiM3));
    }


    @Test
    public final void TestQudtUnitTransformation() {
        IfcDerivedUnit du1 = new IfcDerivedUnit(1, IfcUnitType.LINEARFORCEUNIT, false);
        du1.addDerivedUnitElement(
                new IfcSIUnit(
                        2,
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                1);
        du1.addDerivedUnitElement(
                new IfcSIUnit(
                        3,
                        IfcUnitType.TIMEUNIT,
                        IfcUnitMeasure.SECOND,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -2);
        du1.addDerivedUnitElement(
                new IfcSIUnit(
                        4,
                        IfcUnitType.MASSUNIT,
                        IfcUnitMeasure.GRAM,
                        IfcUnitMeasurePrefix.KILO,
                        false),
                1);
        Assertions.assertTrue(QudtIfcMapper.mapIfcUnitToQudtUnit(du1).contains(Qudt.Units.N));
        IfcDerivedUnit du2 = new IfcDerivedUnit(5, IfcUnitType.MOMENTOFINERTIAUNIT, false);
        du2.addDerivedUnitElement(
                new IfcSIUnit(
                        6,
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                4);
        Assertions.assertTrue(QudtIfcMapper.mapIfcUnitToQudtUnit(du2).contains(Qudt.Units.M4));
        IfcDerivedUnit du3 = new IfcDerivedUnit(7, IfcUnitType.PLANARFORCEUNIT, false);
        du3.addDerivedUnitElement(
                new IfcSIUnit(
                        8,
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                1);
        du3.addDerivedUnitElement(
                new IfcSIUnit(
                        9,
                        IfcUnitType.TIMEUNIT,
                        IfcUnitMeasure.SECOND,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -2);
        du3.addDerivedUnitElement(
                new IfcSIUnit(
                        10,
                        IfcUnitType.MASSUNIT,
                        IfcUnitMeasure.GRAM,
                        IfcUnitMeasurePrefix.KILO,
                        false),
                1);
        du3.addDerivedUnitElement(
                new IfcSIUnit(
                        11,
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -2);
        Assertions.assertTrue(
                QudtIfcMapper.mapIfcUnitToQudtUnit(du3).contains(Qudt.Units.N__PER__M2));
        IfcDerivedUnit du4 = new IfcDerivedUnit(12, IfcUnitType.MASSDENSITYUNIT, false);
        du4.addDerivedUnitElement(
                new IfcSIUnit(
                        13,
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -3);
        du4.addDerivedUnitElement(
                new IfcSIUnit(
                        3,
                        IfcUnitType.MASSUNIT,
                        IfcUnitMeasure.GRAM,
                        IfcUnitMeasurePrefix.KILO,
                        false),
                1);
        Assertions.assertTrue(
                QudtIfcMapper.mapIfcUnitToQudtUnit(du4).contains(Qudt.Units.KiloGM__PER__M3));
        IfcDerivedUnit du5 = new IfcDerivedUnit(15, IfcUnitType.THERMALTRANSMITTANCEUNIT, false);
        du5.addDerivedUnitElement(
                new IfcSIUnit(
                        2,
                        IfcUnitType.TIMEUNIT,
                        IfcUnitMeasure.SECOND,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -3);
        du5.addDerivedUnitElement(
                new IfcSIUnit(
                        1,
                        IfcUnitType.THERMODYNAMICTEMPERATUREUNIT,
                        IfcUnitMeasure.KELVIN,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -1);
        du5.addDerivedUnitElement(
                new IfcSIUnit(
                        3,
                        IfcUnitType.MASSUNIT,
                        IfcUnitMeasure.GRAM,
                        IfcUnitMeasurePrefix.KILO,
                        false),
                1);
        assertThrows(
                NotFoundException.class,
                () -> QudtIfcMapper.mapIfcUnitToQudtUnit(du5)); // unit not in qudt
        IfcDerivedUnit du6 = new IfcDerivedUnit(19, IfcUnitType.VOLUMETRICFLOWRATEUNIT, false);
        du6.addDerivedUnitElement(
                new IfcSIUnit(
                        2,
                        IfcUnitType.TIMEUNIT,
                        IfcUnitMeasure.SECOND,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                3);
        du6.addDerivedUnitElement(
                new IfcSIUnit(
                        1,
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -1);
        // Assertions.assertTrue(QudtIfcMapper.mapIfcUnitToQudtUnit(du4).contains(Qudt.Units.SEC3__PER_M));
        assertThrows(
                NotFoundException.class,
                () -> QudtIfcMapper.mapIfcUnitToQudtUnit(du6)); // unit not in qudt
    }
}
