package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.Standard;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.SingleCondition;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuilderTests {
    @Test
    public void testFeatureBuilder() {
        Feature feature =
                Feature.builder().featureType().booleanType().end().name("boolFeature").build();
        Assertions.assertEquals("boolFeature", feature.getName());
    }

    @Test
    public void testSingleConditionBuilder() {
        SingleCondition.Builder<?> builder = SingleCondition.builder();
        SingleCondition c =
                builder.feature()
                        .featureGroup()
                        .name("fg")
                        .description("desc")
                        .end()
                        .name("someFeature")
                        .featureType()
                        .enumType()
                        .option("one", "first option")
                        .option("two", "second option")
                        .option("three", "third option")
                        .end()
                        .end()
                        .end()
                        .predicate(MappingPredicate.PRESENT)
                        .build();
        Assertions.assertEquals(MappingPredicate.PRESENT, c.getPredicate());
        Assertions.assertEquals("someFeature", c.getFeature().getName());
        Assertions.assertEquals("EnumerationValue", c.getFeature().getType().getType());
    }

    @Test
    public void testMappingBuilder() {
        // @spotless:off
        Mapping mapping =
                Mapping.builder()
                        .id("mappingId")
                        .name("mappingName")
                        .project()
                        .name("myproject")
                        .description("some project")
                        .end()
                        .featureSet(new Standard())
                        .anyMatch()
                        .matches()
                        .id("singleConditionId")
                        .feature()
                        .featureType()
                        .stringType()
                        .end()
                        .name("string feature")
                        .end()
                        .predicate(MappingPredicate.MATCHES)
                        .value("[A-Z]+[0-9]")
                        .end()
                        .matches()
                        .feature()
                        .featureType()
                        .booleanType()
                        .end()
                        .name("boolean feature")
                        .end()
                        .predicate(MappingPredicate.EQUALS)
                        .value(true)
                        .end()
                        .end()
                        .build();
        // @spotless:on
    }
}
