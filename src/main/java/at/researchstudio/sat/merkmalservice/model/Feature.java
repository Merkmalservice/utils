package at.researchstudio.sat.merkmalservice.model;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class Feature {
    private String name;
    private String description;
    private List<FeatureGroup> featureGroups;
    private Set<PropertySet> propertySets;
    private transient Set<String> uniqueValues;

    public Feature() {}

    public Feature(String name) {
        this.name = name;
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Feature(String name, String description, Set<PropertySet> propertySets) {
        this.name = name;
        this.description = description;
        this.propertySets = propertySets;
    }

    public Feature(String name, List<FeatureGroup> featureGroups) {
        this.name = name;
        this.featureGroups = featureGroups;
    }

    public Feature(String name, Set<PropertySet> propertySets) {
        this.name = name;
        this.propertySets = propertySets;
    }

    public Feature(String name, List<FeatureGroup> featureGroups, Set<PropertySet> propertySets) {
        this.name = name;
        this.featureGroups = featureGroups;
        this.propertySets = propertySets;
    }

    public Feature(String name, String description, List<FeatureGroup> featureGroups) {
        this.name = name;
        this.description = description;
        this.featureGroups = featureGroups;
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

    public Set<PropertySet> getPropertySets() {
        return propertySets;
    }

    public void setPropertySets(Set<PropertySet> propertySets) {
        this.propertySets = propertySets;
    }
}
