package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.Standard;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.SingleCondition;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype.BooleanFeatureType;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuilderTests {
    @Test
    public void testFeatureBuilder() {
        Feature feature =
                Feature.builder()
                        .featureType(b -> b.booleanFeatureType())
                        .name("boolFeature")
                        .build();
        Assertions.assertEquals("boolFeature", feature.getName());
    }

    @Test
    public void testSingleConditionBuilder() {
        SingleCondition.Builder builder = new SingleCondition.Builder();
        SingleCondition c =
                builder.feature(
                                Feature.builder()
                                        .featureType(x -> x.stringFeatureType())
                                        .name("someFeature")
                                        .build())
                        .predicate(MappingPredicate.PRESENT)
                        .build();
        Assertions.assertEquals(MappingPredicate.PRESENT, c.getPredicate());
        Assertions.assertEquals("someFeature", c.getFeature().getName());
        Assertions.assertEquals("StringValue", c.getFeature().getType().getType());
    }

    @Test
    public void testMappingBuilder() {
        Mapping mapping =
                Mapping.builder()
                        .id("mappingId")
                        .name("mappingName")
                        .featureSet(new Standard())
                        .ifIs()
                        .id("singleConditionId")
                        .feature(
                                new Feature(
                                        "featureId",
                                        "featureName",
                                        "featureDescription",
                                        List.of(),
                                        new BooleanFeatureType()))
                        .end()
                        .build();
    }
}
