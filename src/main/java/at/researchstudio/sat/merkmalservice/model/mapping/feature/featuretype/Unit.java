package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

public class Unit {
    private String id;
    private String label;

    public Unit() {}

    public Unit(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
