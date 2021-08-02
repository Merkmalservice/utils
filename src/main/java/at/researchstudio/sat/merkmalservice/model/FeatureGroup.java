package at.researchstudio.sat.merkmalservice.model;

public class FeatureGroup {
    private final String name;
    private final String description;

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
}
