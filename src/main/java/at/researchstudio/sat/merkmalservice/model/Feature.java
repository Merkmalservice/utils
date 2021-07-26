package at.researchstudio.sat.merkmalservice.model;

import java.util.ArrayList;
import java.util.Set;

public abstract class Feature {
    private final String name;
    private String description;
    private ArrayList<FeatureGroup> featureGroups;

    public Feature(String name) {
        this.name = name;
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Feature(String name, ArrayList<FeatureGroup> featureGroups) {
        this.name = name;
        this.featureGroups = featureGroups;
    }

    public Feature(String name, String description, ArrayList<FeatureGroup> featureGroups) {
        this.name = name;
        this.description = description;
        this.featureGroups = featureGroups;
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
        setDescriptionFromUniqueValues(uniqueValues, "### Extracted Values:\n- ", "\n- ", null);
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

    public ArrayList<FeatureGroup> getFeatureGroups() {
        return featureGroups;
    }

    public void setFeatureGroups(ArrayList<FeatureGroup> featureGroups) {
        this.featureGroups = featureGroups;
    }
}
