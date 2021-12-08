package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.IBuilder;

public class ReferenceFeatureType extends FeatureType {
    public ReferenceFeatureType() {
        super("REFERENCE");
    }

    public static class Builder implements IBuilder<ReferenceFeatureType> {
        public Builder() {}

        public ReferenceFeatureType build() {
            return new ReferenceFeatureType();
        }
    }
}
