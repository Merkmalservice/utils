package at.researchstudio.sat.merkmalservice.model.qudt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DerivedUnitTest {

    @Test
    public void testSingleFactorUnit() {
        DerivedUnit du = new DerivedUnit(Qudt.Units.PER__M.getIri());
        FactorUnit fu = new FactorUnit(Qudt.Units.PER__M.getIri(), Qudt.Units.M.getIri(), -1);
        du.addFactorUnit(fu);
        assertTrue(du.matches(Qudt.Units.M, -1));
        assertFalse(du.matches(Qudt.Units.M, -1, Qudt.Units.KiloGM, 1));
        assertFalse(du.matches(Qudt.Units.KiloGM, -1));
        assertFalse(du.matches(Qudt.Units.SEC, -2));
    }

    @Test
    public void testTwoFactorUnit() {
        DerivedUnit du = new DerivedUnit(Qudt.Units.KiloGM__PER__M2.getIri());
        FactorUnit fu =
                new FactorUnit(Qudt.Units.KiloGM__PER__M2.getIri(), Qudt.Units.M.getIri(), -2);
        FactorUnit fu2 =
                new FactorUnit(Qudt.Units.KiloGM__PER__M2.getIri(), Qudt.Units.KiloGM.getIri(), 1);
        du.addFactorUnit(fu);
        du.addFactorUnit(fu2);
        assertTrue(du.matches(Qudt.Units.M, -2, Qudt.Units.KiloGM, 1));
        assertTrue(du.matches(Qudt.Units.KiloGM, 1, Qudt.Units.M, -2));
        assertFalse(du.matches(Qudt.Units.M, -2, Qudt.Units.KiloGM, 2));
        assertFalse(du.matches(Qudt.Units.M, -2));
        assertFalse(du.matches(Qudt.Units.KiloGM, 1));
    }

    @Test
    public void testDeepFactorUnit() {
        DerivedUnit du = new DerivedUnit(Qudt.Units.N__PER__KiloGM.getIri());
        FactorUnit fu1 =
                new FactorUnit(Qudt.Units.N__PER__KiloGM.getIri(), Qudt.Units.N.getIri(), 1);
        FactorUnit fu2 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.KiloGM.getIri(), 1);
        FactorUnit fu3 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.M.getIri(), 1);
        FactorUnit fu4 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.SEC.getIri(), -2);
        FactorUnit fu5 =
                new FactorUnit(Qudt.Units.N__PER__KiloGM.getIri(), Qudt.Units.KiloGM.getIri(), -1);
        du.addFactorUnit(fu1);
        du.addFactorUnit(fu2);
        du.addFactorUnit(fu3);
        du.addFactorUnit(fu4);
        du.addFactorUnit(fu5);
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
        DerivedUnit du = new DerivedUnit(Qudt.Units.N__M__PER__KiloGM.getIri());
        FactorUnit fu1 =
                new FactorUnit(Qudt.Units.N__M__PER__KiloGM.getIri(), Qudt.Units.N.getIri(), 1);
        FactorUnit fu2 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.KiloGM.getIri(), 1);
        FactorUnit fu3 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.M.getIri(), 1);
        FactorUnit fu4 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.SEC.getIri(), -2);
        FactorUnit fu5 =
                new FactorUnit(Qudt.Units.N__M__PER__KiloGM.getIri(), Qudt.Units.M.getIri(), 1);
        FactorUnit fu6 =
                new FactorUnit(
                        Qudt.Units.N__M__PER__KiloGM.getIri(), Qudt.Units.KiloGM.getIri(), -1);
        du.addFactorUnit(fu1);
        du.addFactorUnit(fu2);
        du.addFactorUnit(fu3);
        du.addFactorUnit(fu4);
        du.addFactorUnit(fu5);
        du.addFactorUnit(fu6);
        assertTrue(du.matches(Qudt.Units.N, 1, Qudt.Units.KiloGM, -1, Qudt.Units.M, 1));
        assertFalse(du.matches(Qudt.Units.N, 1, Qudt.Units.KiloGM, -1));
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
    }

    @Test
    public void
            testDeepFactorUnitWithDuplicateUnitExponentCombination_matchWithAggregatedExpression() {
        DerivedUnit du = new DerivedUnit(Qudt.Units.N__M__PER__KiloGM.getIri());
        FactorUnit fu1 =
                new FactorUnit(Qudt.Units.N__M__PER__KiloGM.getIri(), Qudt.Units.N.getIri(), 1);
        FactorUnit fu2 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.KiloGM.getIri(), 1);
        FactorUnit fu3 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.M.getIri(), 1);
        FactorUnit fu4 = new FactorUnit(Qudt.Units.N.getIri(), Qudt.Units.SEC.getIri(), -2);
        FactorUnit fu5 =
                new FactorUnit(Qudt.Units.N__M__PER__KiloGM.getIri(), Qudt.Units.M.getIri(), 1);
        FactorUnit fu6 =
                new FactorUnit(
                        Qudt.Units.N__M__PER__KiloGM.getIri(), Qudt.Units.KiloGM.getIri(), -1);
        du.addFactorUnit(fu1);
        du.addFactorUnit(fu2);
        du.addFactorUnit(fu3);
        du.addFactorUnit(fu4);
        du.addFactorUnit(fu5);
        du.addFactorUnit(fu6);

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
}
