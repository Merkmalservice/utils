package at.researchstudio.sat.merkmalservice.model;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.Mapping;
import java.util.ArrayList;
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

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder(parent);
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        public Builder(PARENT parent) {
            super(parent);
        }

        public Builder() {}
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<Project, THIS, PARENT> {
        private Project product;
        private Standard.ListBuilder<THIS> standardListBuilder = Standard.listBuilder((THIS) this);
        private Mapping.ListBuilder<THIS> mappingListBuilder = Mapping.listBuilder((THIS) this);

        MyBuilderScaffold(PARENT parent) {
            super(parent);
            this.product = new Project();
        }

        public MyBuilderScaffold() {
            super();
        }

        public THIS id(String id) {
            product.id = id;
            return (THIS) this;
        }

        public THIS name(String name) {
            product.name = name;
            return (THIS) this;
        }

        public THIS description(String description) {
            product.description = description;
            return (THIS) this;
        }

        public THIS standard(Standard standard) {
            initializeStandardsList();
            product.standards.add(standard);
            return (THIS) this;
        }

        private void initializeStandardsList() {
            if (product.standards == null) {
                product.standards = new ArrayList<>();
            }
        }

        public Standard.Builder standard() {
            return standardListBuilder.newBuilder();
        }

        public THIS mapping(Mapping mapping) {
            initializeMappingsList();
            product.mappings.add(mapping);
            return (THIS) this;
        }

        public Mapping.Builder mapping() {
            return mappingListBuilder.newBuilder();
        }

        private void initializeMappingsList() {
            if (product.mappings == null) {
                product.mappings = new ArrayList<>();
            }
        }

        public Project build() {
            initializeStandardsList();
            product.standards.addAll(standardListBuilder.build());
            initializeMappingsList();
            product.mappings.addAll(mappingListBuilder.build());
            return product;
        }

        public PARENT endProject() {
            return end();
        }
    }
}
