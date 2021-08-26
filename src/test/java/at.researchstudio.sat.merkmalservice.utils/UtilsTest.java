package at.researchstudio.sat.merkmalservice.utils;

import at.researchstudio.sat.merkmalservice.model.*;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcDerivedUnit;
import at.researchstudio.sat.merkmalservice.model.ifc.IfcSIUnit;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasure;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitMeasurePrefix;
import at.researchstudio.sat.merkmalservice.vocab.ifc.IfcUnitType;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtQuantityKind;
import at.researchstudio.sat.merkmalservice.vocab.qudt.QudtUnit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {
    @Test
    public final void TestReadWrite() throws IOException {
        List<Feature> features = new ArrayList<>();

        StringFeature s = new StringFeature("string");
        features.add(s);
        NumericFeature n1 =
                new NumericFeature("numeric1", QudtQuantityKind.HEIGHT, QudtUnit.CENTIMETRE);
        NumericFeature n2 = new NumericFeature("numeric2", QudtQuantityKind.WIDTH, QudtUnit.METRE);
        features.add(n1);
        features.add(n2);
        BooleanFeature b = new BooleanFeature("bool");
        features.add(b);

        List<EnumFeature.OptionValue> options =
                Collections.singletonList(new EnumFeature.MEStringValue("x2", null));
        EnumFeature e = new EnumFeature("enum1", options, false);
        features.add(e);

        Utils.writeToJson("temp.json", features, true);
        List<Feature> readFeatures = Utils.readFromJson(new File("temp.json"));

        Assert.assertEquals(
                features.size(), readFeatures.size()); // TODO: THIS CHECK ALONE DOES NOT SUFFICE

        for (Feature f : features) {
            String featureName = f.getName();
            switch (featureName) {
                case "string":
                    Assert.assertEquals(s, f);
                    break;
                case "numeric1":
                    Assert.assertEquals(n1, f);
                    break;
                case "numeric2":
                    Assert.assertEquals(n2, f);
                    break;
                case "enum1":
                    Assert.assertEquals(e, f);
                    break;
            }
        }
    }

    @Test
    public final void TestQudtUnitTransformation() {
        IfcDerivedUnit du1 = new IfcDerivedUnit("xyz", IfcUnitType.LINEARFORCEUNIT, false);
        du1.addDerivedUnitElement(
                new IfcSIUnit(
                        "1",
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                1);
        du1.addDerivedUnitElement(
                new IfcSIUnit(
                        "2",
                        IfcUnitType.TIMEUNIT,
                        IfcUnitMeasure.SECOND,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -2);
        du1.addDerivedUnitElement(
                new IfcSIUnit(
                        "3",
                        IfcUnitType.MASSUNIT,
                        IfcUnitMeasure.GRAM,
                        IfcUnitMeasurePrefix.KILO,
                        false),
                1);

        Assert.assertEquals(QudtUnit.NEWTON, QudtUnit.extractUnitFromIfcUnit(du1));

        IfcDerivedUnit du2 = new IfcDerivedUnit("xyz", IfcUnitType.MOMENTOFINERTIAUNIT, false);
        du2.addDerivedUnitElement(
                new IfcSIUnit(
                        "1",
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                4);

        Assert.assertEquals(QudtUnit.QUARTIC_METRE, QudtUnit.extractUnitFromIfcUnit(du2));

        IfcDerivedUnit du3 = new IfcDerivedUnit("xyz", IfcUnitType.PLANARFORCEUNIT, false);
        du3.addDerivedUnitElement(
                new IfcSIUnit(
                        "1",
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                1);
        du3.addDerivedUnitElement(
                new IfcSIUnit(
                        "2",
                        IfcUnitType.TIMEUNIT,
                        IfcUnitMeasure.SECOND,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -2);
        du3.addDerivedUnitElement(
                new IfcSIUnit(
                        "3",
                        IfcUnitType.MASSUNIT,
                        IfcUnitMeasure.GRAM,
                        IfcUnitMeasurePrefix.KILO,
                        false),
                1);

        Assert.assertEquals(QudtUnit.NEWTON, QudtUnit.extractUnitFromIfcUnit(du3));

        IfcDerivedUnit du4 = new IfcDerivedUnit("xyz", IfcUnitType.MASSDENSITYUNIT, false);
        du4.addDerivedUnitElement(
                new IfcSIUnit(
                        "1",
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -3);
        du4.addDerivedUnitElement(
                new IfcSIUnit(
                        "3",
                        IfcUnitType.MASSUNIT,
                        IfcUnitMeasure.GRAM,
                        IfcUnitMeasurePrefix.KILO,
                        false),
                1);

        Assert.assertEquals(QudtUnit.KILOGRAMPERCUBICMETER, QudtUnit.extractUnitFromIfcUnit(du4));

        IfcDerivedUnit du5 = new IfcDerivedUnit("xyz", IfcUnitType.THERMALTRANSMITTANCEUNIT, false);
        du5.addDerivedUnitElement(
                new IfcSIUnit(
                        "2",
                        IfcUnitType.TIMEUNIT,
                        IfcUnitMeasure.SECOND,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -3);
        du5.addDerivedUnitElement(
                new IfcSIUnit(
                        "1",
                        IfcUnitType.THERMODYNAMICTEMPERATUREUNIT,
                        IfcUnitMeasure.KELVIN,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -1);
        du5.addDerivedUnitElement(
                new IfcSIUnit(
                        "3",
                        IfcUnitType.MASSUNIT,
                        IfcUnitMeasure.GRAM,
                        IfcUnitMeasurePrefix.KILO,
                        false),
                1);

        // Assert.assertEquals(null, QudtUnit.extractUnitFromIfcUnit(du5)); //TODO FIX TEST

        IfcDerivedUnit du6 = new IfcDerivedUnit("xyz", IfcUnitType.VOLUMETRICFLOWRATEUNIT, false);
        du6.addDerivedUnitElement(
                new IfcSIUnit(
                        "2",
                        IfcUnitType.TIMEUNIT,
                        IfcUnitMeasure.SECOND,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                3);
        du6.addDerivedUnitElement(
                new IfcSIUnit(
                        "1",
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                -1);

        // Assert.assertEquals(null, QudtUnit.extractUnitFromIfcUnit(du6)); //TODO FIX TEST
    }
}
