package at.researchstudio.sat.merkmalservice.model;

import at.researchstudio.sat.merkmalservice.model.mapping.Mapping;
import java.util.List;
import java.util.Objects;

public class Project {
    private String name;
    private String id;
    private String description;
    private List<Standard> standards;
    private List<Mapping> mappings;

    public Project() {}

    public Project(
            String id,
            String name,
            String description,
            List<Standard> standards,
            List<Mapping> mappings) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(id);
        Objects.requireNonNull(standards);
        Objects.requireNonNull(mappings);
        this.name = name;
        this.id = id;
        this.standards = standards;
        this.description = description;
        this.mappings = mappings;
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

    public List<Mapping> getMappings() {
        return mappings;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Project product;

        Builder() {
            this.product = new Project();
        }

        public Project build() {
            return product;
        }
    }
}
