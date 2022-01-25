package at.researchstudio.sat.merkmalservice.utils;

import at.researchstudio.sat.merkmalservice.model.*;
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
        Utils.writeFeaturesToJson("temp.json", features, true);
        List<Feature> readFeatures = Utils.readFeaturesFromJson(new File("temp.json"));
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
}
