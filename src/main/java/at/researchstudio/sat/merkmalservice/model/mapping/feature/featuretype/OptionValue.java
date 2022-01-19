package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.ListBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OptionValue {
    private String id;
    private MappingExecutionValue value;
    private String description;

    public OptionValue() {}

    public OptionValue(String id, MappingExecutionValue value, String description) {
        Objects.requireNonNull(value);
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public MappingExecutionValue getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionValue that = (OptionValue) o;
        return value.equals(that.value) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, description);
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
            extends ListBuilderScaffold<OptionValue, Builder<PARENT>, PARENT> {
        ListBuilder(PARENT parent) {
            super(() -> OptionValue.builder(parent));
        }
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        Builder() {}

        Builder(PARENT parent) {
            super(parent);
        }
    }

    public abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<OptionValue, THIS, PARENT> {
        OptionValue product = new OptionValue();

        public MyBuilderScaffold() {
            super();
        }

        public MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        @Override
        public OptionValue build() {
            return product;
        }

        public THIS value(MappingExecutionValue value) {
            product.value = value;
            return (THIS) this;
        }

        public THIS value(Double value) {
            product.value = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS value(Integer value) {
            product.value = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS value(Boolean value) {
            product.value = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS value(String value) {
            product.value = new MappingExecutionValue(value);
            return (THIS) this;
        }

        public THIS idValue(String id, String graphqlType) {
            product.value = new MappingExecutionValue(id, graphqlType);
            return (THIS) this;
        }

        public THIS idValue(String id) {
            product.value = new MappingExecutionValue(id, "UnknownGrqaphQlType");
            return (THIS) this;
        }

        public THIS description(String description) {
            product.description = description;
            return (THIS) this;
        }

        public THIS id(String id) {
            product.id = id;
            return (THIS) this;
        }
    }
}
