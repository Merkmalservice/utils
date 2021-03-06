package at.researchstudio.sat.merkmalservice.model;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Standard {
    private String id;
    private String description;
    private String name;
    private boolean isPublic;
    private Organization organization;
    private List<PropertySet> propertySets;

    public Standard() {}

    public Standard(
            String id,
            String name,
            String description,
            boolean isPublic,
            Organization organization,
            List<PropertySet> propertySets) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(organization);
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.description = description;
        this.isPublic = isPublic;
        this.propertySets = propertySets;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public Organization getOrganization() {
        return organization;
    }

    public List<PropertySet> getPropertySets() {
        return propertySets;
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

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> ListBuilder<PARENT> listBuilder(
            PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<Standard, Builder<PARENT>, PARENT> {
        private ListBuilder(PARENT parent) {
            super(() -> Standard.builder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {

        public Builder() {}

        public Builder(PARENT parent) {
            super(parent);
        }
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<Standard, THIS, PARENT> {
        private Standard product;
        private PropertySet.ListBuilder<THIS> propertySetListBuilder =
                PropertySet.listBuilder((THIS) this);

        public MyBuilderScaffold() {
            super();
            this.product = new Standard();
        }

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
            this.product = product;
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

        public THIS isPublic(boolean isPublic) {
            product.isPublic = isPublic;
            return (THIS) this;
        }

        public THIS organization(Organization organization) {
            product.organization = organization;
            return (THIS) this;
        }

        public PropertySet.Builder propertySet() {
            return propertySetListBuilder.newBuilder();
        }

        @Override
        public Standard build() {
            return product;
        }

        public THIS organization(Consumer<Organization.Builder> organizationConfigurer) {
            Organization.Builder builder = Organization.builder();
            organizationConfigurer.accept(builder);
            return organization(builder.build());
        }
    }
}
