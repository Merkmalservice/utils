package at.researchstudio.sat.merkmalservice.model.builder.test;

public abstract class FooBuilderBase<
                THIS extends FooBuilderBase<THIS, PARENT>, PARENT extends BuilderBase<?, PARENT, ?>>
        extends BuilderBase<Foo, THIS, PARENT> {
    protected Foo product = new Foo();
    protected BarBuilder<THIS> barBuilder = null;

    public FooBuilderBase() {}

    public FooBuilderBase(PARENT parent) {
        super(parent);
    }

    public THIS name(String name) {
        product.name = name;
        return toThis();
    }

    public abstract BarBuilderBase<?, THIS> bar();

    @Override
    public Foo build() {
        if (barBuilder != null) {
            product.bar = barBuilder.build();
        }
        return product;
    }
}
