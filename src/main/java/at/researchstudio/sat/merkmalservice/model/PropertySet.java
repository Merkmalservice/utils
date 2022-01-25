package at.researchstudio.sat.merkmalservice.model;

public class PropertySet {
    private String id;
    private String name;
    private String description;

    public PropertySet() {}

    public PropertySet(String id, String name) {
        this.id = id;
        this.name = name;
        this.description = "";
    }

    public PropertySet(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
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

    public static class Builder implements IBuilder<PropertySet> {
        private PropertySet product;

        public Builder() {
            this.product = new PropertySet();
        }

        public PropertySet build() {
            return product;
        }

        public PropertySet.Builder id(String id) {
            product.id = id;
            return this;
        }

        public PropertySet.Builder name(String name) {
            product.name = name;
            return this;
        }
    }
}
