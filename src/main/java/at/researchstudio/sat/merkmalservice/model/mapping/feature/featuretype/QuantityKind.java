package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import java.util.Objects;

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

    /**
     * Compare only id.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuantityKind that = (QuantityKind) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
