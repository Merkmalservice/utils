package at.researchstudio.sat.merkmalservice.model.builder.test;

public class FooBuilder<PARENT extends BuilderBase<?, PARENT, ?>>
        extends FooBuilderBase<FooBuilder<PARENT>, PARENT> {
    public FooBuilder() {}

    public FooBuilder(PARENT parent) {
        super(parent);
    }

    public BarBuilder<FooBuilder<PARENT>> bar() {
        this.barBuilder = new BarBuilder<>(toThis());
        return this.barBuilder;
    }
}
