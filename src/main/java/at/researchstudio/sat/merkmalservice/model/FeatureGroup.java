package at.researchstudio.sat.merkmalservice.model;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;

public class FeatureGroup {
    private String name;
    private String description;

    public FeatureGroup() {}

    public FeatureGroup(String name) {
        this.name = name;
        this.description = "";
    }

    public FeatureGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static Builder<?> builder() {
        return new Builder<>();
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
            extends ListBuilderScaffold<FeatureGroup, Builder<PARENT>, PARENT> {
        private ListBuilder(PARENT parent) {
            super(() -> FeatureGroup.builder(parent));
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
            extends SubBuilderScaffold<FeatureGroup, THIS, PARENT> {
        private FeatureGroup product = new FeatureGroup();

        MyBuilderScaffold() {}

        MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        public FeatureGroup build() {
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
    }
}
