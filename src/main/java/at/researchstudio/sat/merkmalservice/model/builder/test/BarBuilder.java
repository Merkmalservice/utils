package at.researchstudio.sat.merkmalservice.model.builder.test;

public class BarBuilder<PARENT extends BuilderBase<?, PARENT, ?>>
        extends BarBuilderBase<BarBuilder<PARENT>, PARENT> {
    public BarBuilder() {}

    public BarBuilder(PARENT parent) {
        super(parent);
    }

    public PARENT end() {
        return parent;
    }
}
