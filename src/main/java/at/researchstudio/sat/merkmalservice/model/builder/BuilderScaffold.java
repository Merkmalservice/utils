package at.researchstudio.sat.merkmalservice.model.builder;

public abstract class BuilderScaffold<
        THIS extends BuilderScaffold<THIS, PARENT>, PARENT extends BuilderScaffold<PARENT, ?>> {
    protected PARENT parent = null;

    public BuilderScaffold(PARENT parent) {
        this.parent = parent;
    }

    public PARENT end() {
        return parent;
    }

    public BuilderScaffold() {}
}
