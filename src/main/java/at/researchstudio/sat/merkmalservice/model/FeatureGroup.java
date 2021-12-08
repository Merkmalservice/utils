package at.researchstudio.sat.merkmalservice.model;

public class FeatureGroup {
    private String name;
    private String description;

    public FeatureGroup() {}

    public FeatureGroup(String name) {
        this.name = name;
        this.description = "";
    }

    public FeatureGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FeatureGroup product;

        public Builder() {
            this.product = new FeatureGroup();
        }

        public FeatureGroup build() {
            return product;
        }

        public Builder name(String name) {
            product.name = name;
            return this;
        }

        public Builder description(String description) {
            product.description = description;
            return this;
        }
    }
}
