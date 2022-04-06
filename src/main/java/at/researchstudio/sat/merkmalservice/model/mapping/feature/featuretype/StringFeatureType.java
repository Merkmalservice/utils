package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.IBuilder;

public class StringFeatureType extends FeatureType {
    public StringFeatureType() {
        super(Types.StringType.name());
    }

    public static class Builder implements IBuilder<StringFeatureType> {
        public Builder() {}

        public StringFeatureType build() {
            return new StringFeatureType();
        }
    }
}
