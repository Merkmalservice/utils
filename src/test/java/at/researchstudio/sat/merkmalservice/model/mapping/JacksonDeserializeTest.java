package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.graphql.DataResult;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.Feature;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype.StringFeatureType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.Test;

public class JacksonDeserializeTest {

    private static final Path inputFolder =
            Path.of("src/test/resources/at/researchstudio/sat/merkmalservice/model/mapping");

    @Test
    public void testDeserializeMapping_noActions() throws IOException {
        String json =
                Files.readString(inputFolder.resolve(Path.of("gql-result-mapping-noactions.json")));
        Mapping mapping =
                new ObjectMapper().readValue(json, DataResult.class).getData().getMapping();
    }

    @Test
    public void testDeserializeMapping_full() throws IOException {
        String json =
                Files.readString(inputFolder.resolve(Path.of("gql-result-mapping-full.json")));
        Mapping mapping =
                new ObjectMapper().readValue(json, DataResult.class).getData().getMapping();
    }

    @Test
    public void testSerializeFeature() throws IOException {
        Feature f =
                new Feature(
                        "1234-abcd",
                        "aStringFeature",
                        "description",
                        List.of(),
                        new StringFeatureType());
        StringWriter w = new StringWriter();
        new ObjectMapper().writer().writeValue(w, f);
        System.out.println(w.toString());
    }
}
