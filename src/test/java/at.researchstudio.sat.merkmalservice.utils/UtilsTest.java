package at.researchstudio.sat.merkmalservice.utils;

import at.researchstudio.sat.merkmalservice.model.*;
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
}
