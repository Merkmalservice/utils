package at.researchstudio.sat.merkmalservice.model;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.Values;

public class PropertySet {
    private IRI id;
    private String name;
    private String description;

    private PropertySet() {}

    public PropertySet(IRI id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public IRI getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> builder(
            PARENT parent) {
        return new Builder<>(parent);
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> ListBuilder<PARENT> listBuilder(
            PARENT parent) {
        return new ListBuilder<>(parent);
    }

    public static class ListBuilder<PARENT extends BuilderScaffold<?, PARENT>>
            extends ListBuilderScaffold<PropertySet, Builder<PARENT>, PARENT> {
        private ListBuilder(PARENT parent) {
            super(() -> PropertySet.builder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        Builder() {}

        Builder(PARENT parent) {
            super(parent);
        }
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<PropertySet, THIS, PARENT> {

        private PropertySet product = new PropertySet();

        MyBuilderScaffold() {}

        MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public PropertySet build() {
            return product;
        }

        public THIS name(String name) {
            product.name = name;
            return (THIS) this;
        }

        public THIS description(String description) {
            product.description = description;
            return (THIS) this;
        }

        public THIS id(IRI iri) {
            product.id = iri;
            return (THIS) this;
        }

        public THIS id(String iri) {
            product.id = Values.iri(iri);
            return (THIS) this;
        }
    }
}
