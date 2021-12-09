package at.researchstudio.sat.merkmalservice.model.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class ListBuilderScaffold<
        T, B extends TerminalBuilderScaffold<T, B, P>, P extends BuilderScaffold<P, ?>> {
    private List<B> builders = new ArrayList<>();
    private Supplier<B> builderSupplier;

    public ListBuilderScaffold(Supplier<B> builderSupplier) {
        this.builderSupplier = builderSupplier;
    }

    public B newBuilder() {
        B newBuilder = builderSupplier.get();
        builders.add(newBuilder);
        return newBuilder;
    }

    public List<T> build() {
        return builders.stream().map(b -> b.build()).collect(Collectors.toList());
    }
}
