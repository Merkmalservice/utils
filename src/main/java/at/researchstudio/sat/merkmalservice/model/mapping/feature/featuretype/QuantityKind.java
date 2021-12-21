package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

public class QuantityKind {
    private String id;
    private String label;

    public QuantityKind(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public QuantityKind() {}

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
