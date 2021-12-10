package at.researchstudio.sat.merkmalservice.model.builder;

public abstract class SubBuilderScaffold<
                T,
                THIS extends SubBuilderScaffold<T, THIS, PARENT>,
                PARENT extends BuilderScaffold<?, PARENT>>
        extends BuilderScaffold<T, THIS> {

    protected PARENT parent = null;

    public SubBuilderScaffold(PARENT parent) {
        this.parent = parent;
    }

    public SubBuilderScaffold() {}

    public PARENT end() {
        if (parent == null) {
            throw new IllegalStateException(
                    String.format(
                            "%s is not used as a sub-builder, cannot use end()",
                            this.getClass().getName()));
        }
        return parent;
    }
}
