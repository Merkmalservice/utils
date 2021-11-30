package at.researchstudio.sat.merkmalservice.model;

import java.util.Objects;

public class Standard {
    private String id;
    private String description;
    private boolean isPublic;
    private Organization organization;

    public Standard() {}

    public Standard(String id, String description, boolean isPublic, Organization organization) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(organization);
        this.id = id;
        this.organization = organization;
        this.description = description;
        this.isPublic = isPublic;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPublic() {
        return isPublic;
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
