package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.Standard;
import at.researchstudio.sat.merkmalservice.model.mapping.condition.SingleCondition;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BuilderTests {
    @Test
    public void testFeatureBuilder() {
        Feature feature = Feature.builder().booleanType().name("boolFeature").build();
        Assertions.assertEquals("boolFeature", feature.getName());
    }

    @Test
    public void testSingleConditionBuilder() {
        SingleCondition c =
                SingleCondition.builder()
                        .feature()
                        /**/ .name("someFeature")
                        /*--*/ .featureGroup()
                        /*----*/ .name("fg")
                        /*----*/ .description("desc")
                        /*----*/ .end()
                        /*--*/ .enumType()
                        /*----*/ .option()
                        /*------*/ .value("one")
                        /*------*/ .end()
                        /*----*/ .option()
                        /*------*/ .value("two")
                        /*------*/ .end()
                        /*----*/ .option()
                        /*------*/ .id("abc-234")
                        /*------*/ .description("third option")
                        /*------*/ .value("three")
                        /*------*/ .end()
                        /*----*/ .end()
                        /*--*/ .end()
                        /**/ .valuePresent()
                        .build();
        Assertions.assertEquals(MappingPredicate.PRESENT, c.getPredicate());
        Assertions.assertEquals("someFeature", c.getFeature().getName());
        Assertions.assertEquals("EnumerationValue", c.getFeature().getType().getType());
    }

    @Test
    public void testMappingBuilder() {
        Mapping mapping =
                Mapping.builder()
                        /**/ .id("mappingId")
                        /**/ .name("mappingName")
                        /**/ .project()
                        /*--*/ .name("myproject")
                        /*--*/ .description("some project")
                        /*--*/ .end()
                        /**/ .featureSet(new Standard())
                        /**/ .anyMatch()
                        /*--*/ .matches()
                        /*----*/ .id("singleConditionId")
                        /*----*/ .feature()
                        /*------*/ .stringType()
                        /*------*/ .name("string feature")
                        /*------*/ .end()
                        /*----*/ .valueMatches("[A-Z]+[0-9]")
                        /*----*/ .end()
                        /*--*/ .matches()
                        /*----*/ .feature()
                        /*------*/ .booleanType()
                        /*------*/ .name("boolean feature")
                        /*------*/ .end()
                        /*----*/ .valueEquals(true)
                        /*----*/ .end()
                        /*--*/ .end()
                        /*--*/ .addActionGroup()
                        /*----*/ .addAction()
                        /*------*/ .feature()
                        /*--------*/ .name("newBoolFeature")
                        /*--------*/ .booleanType()
                        /*--------*/ .end()
                        /*------*/ .value(true)
                        /*------*/ .end()
                        /*----*/ .end()
                        /**/ .build();
        Assertions.assertNotNull(mapping.getProject());
        Assertions.assertNotNull(mapping.getCondition());
        Assertions.assertNotNull(mapping.getActionGroups());
        Assertions.assertEquals(1, mapping.getActionGroups().size());
    }
}
