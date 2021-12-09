package at.researchstudio.sat.merkmalservice.model.builder.test;

public abstract class BarBuilderBase<
                THIS extends BarBuilderBase<THIS, PARENT>, PARENT extends BuilderBase<?, PARENT, ?>>
        extends BuilderBase<Bar, THIS, PARENT> {
    Bar product = new Bar();

    public BarBuilderBase() {}

    public BarBuilderBase(PARENT parent) {
        super(parent);
    }

    public THIS message(String message) {
        product.message = message;
        return toThis();
    }

    @Override
    public Bar build() {
        return product;
    }
}
