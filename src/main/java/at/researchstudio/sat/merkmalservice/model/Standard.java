package at.researchstudio.sat.merkmalservice.model;

import java.util.Objects;

public class Standard {
    private final String id;
    private final Organization organization;

    public Standard(String id, Organization organization) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(organization);
        this.id = id;
        this.organization = organization;
    }

    public String getId() {
        return id;
    }

    public Organization getOrganization() {
        return organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Standard standard = (Standard) o;
        return Objects.equals(id, standard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
