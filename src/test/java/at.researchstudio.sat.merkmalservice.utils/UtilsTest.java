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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public final void TestIfcUtf8Conversion() {
        String s1 = "1-Fl\\X2\\00FC\\X0\\gelfenster 23";
        String s2 = "Zugeh\\X2\\00F6\\X0\\riger Raumname";
        String s3 = "\\X2\\00DC\\X0\\berobjekt-ID";

        Assertions.assertEquals("1-Flügelfenster 23", Utils.convertIFCStringToUtf8(s1));
        Assertions.assertEquals("Zugehöriger Raumname", Utils.convertIFCStringToUtf8(s2));
        Assertions.assertEquals("Überobjekt-ID", Utils.convertIFCStringToUtf8(s3));

        Assertions.assertEquals(s1, Utils.convertUtf8ToIFCString(Utils.convertIFCStringToUtf8(s1)));
        Assertions.assertEquals(s2, Utils.convertUtf8ToIFCString(Utils.convertIFCStringToUtf8(s2)));
        Assertions.assertEquals(s3, Utils.convertUtf8ToIFCString(Utils.convertIFCStringToUtf8(s3)));
    }

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

        Assertions.assertEquals(
                features.size(), readFeatures.size()); // TODO: THIS CHECK ALONE DOES NOT SUFFICE

        for (Feature f : features) {
            String featureName = f.getName();
            switch (featureName) {
                case "string":
                    Assertions.assertEquals(s, f);
                    break;
                case "numeric1":
                    Assertions.assertEquals(n1, f);
                    break;
                case "numeric2":
                    Assertions.assertEquals(n2, f);
                    break;
                case "enum1":
                    Assertions.assertEquals(e, f);
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

        Assertions.assertEquals(QudtUnit.NEWTON, QudtUnit.extractUnitFromIfcUnit(du1));

        IfcDerivedUnit du2 = new IfcDerivedUnit("xyz", IfcUnitType.MOMENTOFINERTIAUNIT, false);
        du2.addDerivedUnitElement(
                new IfcSIUnit(
                        "1",
                        IfcUnitType.LENGTHUNIT,
                        IfcUnitMeasure.METRE,
                        IfcUnitMeasurePrefix.NONE,
                        false),
                4);

        Assertions.assertEquals(QudtUnit.QUARTIC_METRE, QudtUnit.extractUnitFromIfcUnit(du2));

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

        Assertions.assertEquals(QudtUnit.NEWTON, QudtUnit.extractUnitFromIfcUnit(du3));

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

        Assertions.assertEquals(
                QudtUnit.KILOGRAMPERCUBICMETER, QudtUnit.extractUnitFromIfcUnit(du4));

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

        // Assertions.assertEquals(null, QudtUnit.extractUnitFromIfcUnit(du5)); //TODO FIX TEST

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

        // Assertions.assertEquals(null, QudtUnit.extractUnitFromIfcUnit(du6)); //TODO FIX TEST
    }
}
