package at.researchstudio.sat.merkmalservice.model.qudt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

public class DerivedUnitTest {

    @Test
    public void testSingleFactorUnit() {
        Unit du = Qudt.Units.PER__M;
        assertTrue(du.matches(Qudt.Units.M, -1));
        assertFalse(du.matches(Qudt.Units.M, -1, Qudt.Units.KiloGM, 1));
        assertFalse(du.matches(Qudt.Units.KiloGM, -1));
        assertFalse(du.matches(Qudt.Units.SEC, -2));
    }

    @Test
    public void testTwoFactorUnit() {
        Unit du = Qudt.Units.KiloGM__PER__M2;
        assertTrue(du.matches(Qudt.Units.M, -2, Qudt.Units.KiloGM, 1));
        assertTrue(du.matches(Qudt.Units.KiloGM, 1, Qudt.Units.M, -2));
        assertFalse(du.matches(Qudt.Units.M, -2, Qudt.Units.KiloGM, 2));
        assertFalse(du.matches(Qudt.Units.M, -2));
        assertFalse(du.matches(Qudt.Units.KiloGM, 1));
    }

    @Test
    public void testDeepFactorUnit() {
        Unit du = Qudt.Units.N__PER__KiloGM;
        assertTrue(
                du.matches(
                        Qudt.Units.KiloGM,
                        -1,
                        Qudt.Units.M,
                        1,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2));
        assertTrue(du.matches(Qudt.Units.N, 1, Qudt.Units.KiloGM, -1));
        assertTrue(
                du.matches(
                        Qudt.Units.KiloGM,
                        -1,
                        Qudt.Units.M,
                        1,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2,
                        Qudt.Units.N,
                        1));
        assertFalse(
                du.matches(
                        Qudt.Units.KiloGM,
                        -1,
                        Qudt.Units.M,
                        1,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2,
                        Qudt.Units.N,
                        1,
                        Qudt.Units.KiloGM,
                        -1));
        assertFalse(du.matches(Qudt.Units.M, -2, Qudt.Units.KiloGM, 2));
        assertFalse(du.matches(Qudt.Units.M, -2));
        assertFalse(du.matches(Qudt.Units.KiloGM, 1));
    }

    @Test
    public void testDeepFactorUnitWithDuplicateUnitExponentCombination() {
        Unit du = Qudt.Units.N__M__PER__KiloGM;
        boolean matches = du.matches(Qudt.Units.N, 1, Qudt.Units.KiloGM, -1, Qudt.Units.M, 1);
        assertTrue(matches);
        assertTrue(
                du.matches(
                        Qudt.Units.KiloGM,
                        -1,
                        Qudt.Units.M,
                        1,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2,
                        Qudt.Units.M,
                        1,
                        Qudt.Units.N,
                        1));
        assertTrue(
                du.matches(
                        Qudt.Units.KiloGM,
                        -1,
                        Qudt.Units.M,
                        1,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2,
                        Qudt.Units.N,
                        1));
        assertFalse(
                du.matches(
                        Qudt.Units.KiloGM,
                        -1,
                        Qudt.Units.M,
                        1,
                        Qudt.Units.KiloGM,
                        1,
                        Qudt.Units.SEC,
                        -2,
                        Qudt.Units.N,
                        1,
                        Qudt.Units.KiloGM,
                        -1));
        assertTrue(
                du.matches(
                        Qudt.Units.KiloGM, 1,
                        Qudt.Units.M, 1,
                        Qudt.Units.SEC, -2,
                        Qudt.Units.M, 1,
                        Qudt.Units.KiloGM, -1));
        assertFalse(du.matches(Qudt.Units.M, -2, Qudt.Units.KiloGM, 2));
        assertFalse(du.matches(Qudt.Units.M, -2));
        assertFalse(du.matches(Qudt.Units.KiloGM, 1));
        assertFalse(du.matches(Qudt.Units.N, 1, Qudt.Units.KiloGM, -1));
    }

    @Test
    public void
            testDeepFactorUnitWithDuplicateUnitExponentCombination_matchWithAggregatedExpression() {
        Unit du = Qudt.Units.N__M__PER__KiloGM;

        // now simplify: aggregate the M^1, M^1 to M^2: should still work.
        assertTrue(
                du.matches(
                        Qudt.Units.KiloGM, 1,
                        Qudt.Units.M, 2,
                        Qudt.Units.SEC, -2,
                        Qudt.Units.KiloGM, -1));
        // now simplify: wrongly aggregate the KiloGM^1, KiloGM^-1 to KiloGM^0: should not work
        assertFalse(
                du.matches(
                        Qudt.Units.M, 2,
                        Qudt.Units.SEC, -2,
                        Qudt.Units.KiloGM, 0));
    }

    @Test
    public void testScaledFactors() {

        // mJoule =
        //               new IfcDerivedUnit(100, IfcUnitType.ENERGYUNIT, Map.of(kg, 1, sec, -2, km,
        // 2), false);
        Object[] factors =
                new Object[] {
                    Qudt.Units.SEC, -2, Qudt.Units.KiloGM, 1, Qudt.Units.M, 1, Qudt.Units.KiloM, 1
                };
        assertTrue(Qudt.Units.KiloN__M.matches(factors));
        factors =
                new Object[] {
                    Qudt.Units.KiloGM, 1, Qudt.Units.SEC, -2, Qudt.Units.M, 1, Qudt.Units.KiloM, 1
                };
        assertTrue(Qudt.Units.KiloN__M.matches(factors));
        assertTrue(Qudt.Units.KiloJ.matches(factors));
        assertFalse(Qudt.Units.MilliOHM.matches(factors));
        assertFalse(Qudt.Units.MilliS.matches(factors));

        factors = new Object[] {Qudt.Units.KiloGM, 1, Qudt.Units.K, -1, Qudt.Units.SEC, -3};
        assertFalse(Qudt.Units.W__PER__K.matches(factors));
        assertFalse(Qudt.Units.V__PER__K.matches(factors));
    }

    @Test
    public void testScaledFactors_negExpFirst() {
        // mJoule =
        //               new IfcDerivedUnit(100, IfcUnitType.ENERGYUNIT, Map.of(kg, 1, sec, -2, km,
        // 2), false);
        Object[] factors =
                new Object[] {
                    Qudt.Units.SEC, -2, Qudt.Units.KiloGM, 1, Qudt.Units.M, 1, Qudt.Units.KiloM, 1
                };
        assertTrue(Qudt.Units.KiloN__M.matches(factors));
        factors =
                new Object[] {
                    Qudt.Units.KiloGM, 1, Qudt.Units.SEC, -2, Qudt.Units.M, 1, Qudt.Units.KiloM, 1
                };
        assertTrue(Qudt.Units.KiloN__M.matches(factors));
    }

    @Test
    public void test_squareInNominator() {
        Object[] factors = new Object[] {Qudt.Units.MilliM, 2, Qudt.Units.SEC, -1};
        assertTrue(Qudt.Units.MilliM2__PER__SEC.matches(factors));
        factors = new Object[] {Qudt.Units.KiloGM, 2, Qudt.Units.SEC, -2};
        assertTrue(Qudt.Units.KiloGM2__PER__SEC2.matches(factors));
    }

    @Test
    public void test_squareInDenominator() {
        Object[] factors =
                new Object[] {
                    Qudt.Units.KiloGM, 1, Qudt.Units.M, 1, Qudt.Units.M, -2, Qudt.Units.SEC, -2
                };
        assertTrue(Qudt.Units.N__PER__M2.matches(factors));
        factors =
                new Object[] {
                    Qudt.Units.M, -2, Qudt.Units.SEC, -2, Qudt.Units.KiloGM, 1, Qudt.Units.M, 1
                };
        assertTrue(Qudt.Units.N__PER__M2.matches(factors));
    }

    @Test
    public void testScale_squareInDenominator1() {
        Object[] factors =
                new Object[] {
                    Qudt.Units.KiloGM,
                    1,
                    Qudt.Units.M,
                    1,
                    Qudt.Units.MilliM,
                    -2,
                    Qudt.Units.KiloSEC,
                    -2
                };
        assertTrue(Qudt.Units.N__PER__M2.matches(factors));
    }

    @Test
    public void testScale_squareInDenominator2() {
        Object[] factors =
                new Object[] {
                    Qudt.Units.GM,
                    1,
                    Qudt.Units.MilliM,
                    1,
                    Qudt.Units.M,
                    -2,
                    Qudt.Units.MilliSEC,
                    -2
                };
        assertTrue(Qudt.Units.N__PER__M2.matches(factors));
    }

    @Test
    public void testScaledFactorsWithChangingOrder() {
        // mJoule =
        //               new IfcDerivedUnit(100, IfcUnitType.ENERGYUNIT, Map.of(kg, 1, sec, -2, km,
        // 2), false);
        Object[] factors =
                new Object[] {
                    Qudt.Units.KiloGM, 1, Qudt.Units.SEC, -2, Qudt.Units.M, 1, Qudt.Units.KiloM, 1
                };
        List<List<FactorUnit>> successfulFor = new ArrayList<>();
        List<FactorUnit> factorUnits = new ArrayList<>();
        for (int i = 0; i < factors.length; i += 2) {
            factorUnits.add(new FactorUnit((Unit) factors[i], (Integer) factors[i + 1]));
        }

        try {
            for (int i = 0; i < 20; i++) {
                Collections.shuffle(factorUnits);
                factors =
                        factorUnits.stream()
                                .flatMap(fu -> Stream.of(fu.getUnit(), fu.getExponent()))
                                .toArray();
                assertTrue(Qudt.Units.KiloN__M.matches(factors), () -> "failed for " + factorUnits);
                successfulFor.add(new ArrayList<>(factorUnits));
            }
        } catch (AssertionFailedError e) {
            System.err.println("test succeeded for: ");
            successfulFor.forEach(s -> System.err.println(s));
            System.err.println("test failed for:");
            System.err.println(factorUnits);
            throw e;
        }
        assertTrue(Qudt.Units.KiloN__M.matches(factors), () -> "failed for " + factorUnits);
        assertTrue(Qudt.Units.KiloJ.matches(factors), () -> "failed for " + factorUnits);
        assertFalse(Qudt.Units.MilliOHM.matches(factors), () -> "failed for " + factorUnits);
        assertFalse(Qudt.Units.MilliS.matches(factors), () -> "failed for " + factorUnits);
        factors = new Object[] {Qudt.Units.KiloGM, 1, Qudt.Units.K, -1, Qudt.Units.SEC, -3};
        assertFalse(Qudt.Units.W__PER__K.matches(factors));
        assertFalse(Qudt.Units.V__PER__K.matches(factors));
    }

    @Test
    public void testMilliJ() {
        Object[] factors =
                new Object[] {
                    Qudt.Units.KiloGM, 1, Qudt.Units.SEC, -2, Qudt.Units.M, 1, Qudt.Units.MilliM, 1
                };
        assertTrue(Qudt.Units.MilliN__M.matches(factors));
        assertFalse(Qudt.Units.MilliH__PER__KiloOHM.matches(factors));
        assertTrue(Qudt.Units.MilliJ.matches(factors));
    }
}
