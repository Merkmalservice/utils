package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "__typename")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "BooleanValue", value = BooleanFeatureType.class),
    @JsonSubTypes.Type(name = "StringValue", value = StringFeatureType.class),
    @JsonSubTypes.Type(name = "ReferenceValue", value = ReferenceFeatureType.class),
    @JsonSubTypes.Type(name = "NumericValue", value = NumericFeatureType.class),
    @JsonSubTypes.Type(name = "EnumerationValue", value = EnumFeatureType.class),
})
public abstract class FeatureType {
    @JsonIgnore private String type;

    public FeatureType() {}

    public FeatureType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
