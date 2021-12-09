package at.researchstudio.sat.merkmalservice.model.mapping.feature;

import at.researchstudio.sat.merkmalservice.model.FeatureGroup;
import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.TerminalBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype.FeatureType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class Feature {
    private String id;
    private String name;
    private String description;
    private List<FeatureGroup> featureGroups;
    private FeatureType type;

    private transient Set<String> uniqueValues;

    public Feature() {}

    public Feature(String name) {
        this.name = name;
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Feature(String name, List<FeatureGroup> featureGroups) {
        this.name = name;
        this.featureGroups = featureGroups;
    }

    public Feature(String name, String description, List<FeatureGroup> featureGroups) {
        this.name = name;
        this.description = description;
        this.featureGroups = featureGroups;
    }

    public Feature(
            String id,
            String name,
            String description,
            List<FeatureGroup> featureGroups,
            FeatureType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.featureGroups = featureGroups;
        this.type = type;
    }

    public Set<String> getUniqueValues() {
        return uniqueValues;
    }

    public void setUniqueValues(Set<String> uniqueValues) {
        this.uniqueValues = uniqueValues;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescriptionFromUniqueValues(Set<String> uniqueValues) {
        setDescriptionFromUniqueValues(uniqueValues, "### Extracted Values:\n- `", "`\n- `", "`");
    }

    public void setDescriptionFromUniqueValues(
            Set<String> uniqueValues, String prefix, String delimiter, String postfix) {
        if (uniqueValues != null && uniqueValues.size() > 0) {
            this.description =
                    prefix
                            + String.join(delimiter, uniqueValues)
                            + ((postfix == null ? "" : postfix));
        }
    }

    public List<FeatureGroup> getFeatureGroups() {
        return featureGroups;
    }

    public void setFeatureGroups(List<FeatureGroup> featureGroups) {
        this.featureGroups = featureGroups;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FeatureType getType() {
        return type;
    }

    public void setType(FeatureType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return name.equals(feature.name)
                && Objects.equals(description, feature.description)
                && Objects.equals(featureGroups, feature.featureGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, featureGroups);
    }

    public static <PARENT extends BuilderScaffold<PARENT, ?>> Builder<PARENT> builder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<PARENT, ?>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder<>(parent);
    }

    public static class Builder<PARENT extends BuilderScaffold<PARENT, ?>>
            extends TerminalBuilderScaffold<Feature, Builder<PARENT>, PARENT> {
        private Feature product;

        Builder() {
            super();
            this.product = new Feature();
        }

        Builder(PARENT parent) {
            super(parent);
            this.product = new Feature();
        }

        public Feature build() {
            return product;
        }

        public PARENT endFeature() {
            return end();
        }

        public Builder featureGroup(FeatureGroup featureGroup) {
            if (product.featureGroups == null) {
                product.featureGroups = new ArrayList<>();
            }
            product.featureGroups.add(featureGroup);
            return this;
        }

        public Builder featureGroup(Consumer<FeatureGroup.Builder> featureGroupConfigurer) {
            FeatureGroup.Builder builder = FeatureGroup.builder();
            featureGroupConfigurer.accept(builder);
            return featureGroup(builder.build());
        }

        public Builder name(String name) {
            product.name = name;
            return this;
        }

        public Builder description(String descrption) {
            product.description = descrption;
            return this;
        }

        public Builder id(String id) {
            product.id = id;
            return this;
        }

        public Builder featureType(FeatureType featureType) {
            product.type = featureType;
            return this;
        }

        public Builder featureType(Consumer<FeatureType.Builder> featureTypeConfigurer) {
            FeatureType.Builder builder = FeatureType.builder();
            featureTypeConfigurer.accept(builder);
            product.type = builder.build();
            return this;
        }
    }
}
