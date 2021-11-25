package at.researchstudio.sat.merkmalservice.model;

import java.util.List;
import java.util.Objects;

public class Project {
    private final String name;
    private final String id;
    private final String description;
    private final List<Standard> standards;

    public Project(String id, String name, String description, List<Standard> standards) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(id);
        Objects.requireNonNull(standards);
        this.name = name;
        this.id = id;
        this.standards = standards;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Standard> getStandards() {
        return standards;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
