package at.researchstudio.sat.merkmalservice.model.mapping.feature.featuretype;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.mapping.MappingExecutionValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumFeatureType extends FeatureType {
    private List<OptionValue> options;
    private Boolean allowMultiple;

    public EnumFeatureType() {
        super(Types.EnumerationValue.name());
    }

    public EnumFeatureType(List<OptionValue> options, Boolean allowMultiple) {
        super(Types.EnumerationValue.name());
        this.options = options;
        this.allowMultiple = allowMultiple;
    }

    public List<OptionValue> getOptions() {
        return Collections.unmodifiableList(this.options);
    }

    public Boolean isAllowMultiple() {
        return allowMultiple;
    }

    public static Builder<?> enumTypeBuilder() {
        return new Builder<>();
    }

    public static <PARENT extends BuilderScaffold<?, PARENT>> Builder<PARENT> enumTypeBuilder(
            PARENT parent) {
        return new Builder<>(parent);
    }

    public static class Builder<PARENT extends BuilderScaffold<?, PARENT>>
            extends MyBuilderScaffold<Builder<PARENT>, PARENT> {
        Builder(PARENT parent) {
            super(parent);
        }

        Builder() {}
    }

    abstract static class MyBuilderScaffold<
                    THIS extends MyBuilderScaffold<THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<EnumFeatureType, THIS, PARENT> {

        private OptionValue.ListBuilder<THIS> optionValueListBuilder =
                OptionValue.listBuilder((THIS) this);

        private EnumFeatureType product = new EnumFeatureType();

        MyBuilderScaffold(PARENT parent) {
            super(parent);
        }

        MyBuilderScaffold() {}

        public EnumFeatureType build() {
            prepareOptionValueList();
            product.options.addAll(optionValueListBuilder.build());
            return product;
        }

        private THIS option(OptionValue optionValue) {
            prepareOptionValueList();
            product.options.add(optionValue);
            return (THIS) this;
        }

        public OptionValue.Builder<THIS> option() {
            return optionValueListBuilder.newBuilder();
        }

        private void prepareOptionValueList() {
            if (product.options == null) {
                product.options = new ArrayList<>();
            }
        }

        public THIS id(String id) {
            this.product.id = id;
            return (THIS) this;
        }

        public THIS option(MappingExecutionValue value) {
            prepareOptionValueList();
            product.options.add(new OptionValue(null, value, null));
            return (THIS) this;
        }

        public THIS allowMultiple(boolean allowMultiple) {
            product.allowMultiple = allowMultiple;
            return (THIS) this;
        }
    }
}
