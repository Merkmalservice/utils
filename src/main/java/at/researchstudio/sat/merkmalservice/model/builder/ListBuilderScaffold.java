package at.researchstudio.sat.merkmalservice.model.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class ListBuilderScaffold<
        PRODUCT,
        BUILDER extends SubBuilderScaffold<PRODUCT, BUILDER, PARENT>,
        PARENT extends BuilderScaffold<?, PARENT>> {
    private List<BUILDER> builders = new ArrayList<>();
    private Supplier<BUILDER> builderSupplier;

    public ListBuilderScaffold(Supplier<BUILDER> builderSupplier) {
        this.builderSupplier = builderSupplier;
    }

    public BUILDER newBuilder() {
        BUILDER newBuilder = builderSupplier.get();
        builders.add(newBuilder);
        return newBuilder;
    }

    public List<PRODUCT> build() {
        return builders.stream().map(b -> b.build()).collect(Collectors.toList());
    }
}
