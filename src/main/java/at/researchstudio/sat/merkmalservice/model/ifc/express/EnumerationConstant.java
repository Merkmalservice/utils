package at.researchstudio.sat.merkmalservice.model.ifc.express;

import java.util.Objects;

public class EnumerationConstant {
    private String name;

    public EnumerationConstant(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnumerationConstant that = (EnumerationConstant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "." + name + ".";
    }
}
