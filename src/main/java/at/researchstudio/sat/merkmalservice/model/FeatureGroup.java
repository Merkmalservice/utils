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
}
