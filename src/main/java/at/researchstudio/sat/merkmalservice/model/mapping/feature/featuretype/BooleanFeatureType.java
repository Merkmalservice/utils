package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.IBuilder;

public class BooleanFeatureType extends FeatureType {
    public BooleanFeatureType() {
        super(Types.BooleanValue.name());
    }

    public static class Builder implements IBuilder<BooleanFeatureType> {
        public Builder() {}

        public BooleanFeatureType build() {
            return new BooleanFeatureType();
        }
    }
}
