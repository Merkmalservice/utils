package at.researchstudio.sat.merkmalservice.model.builder.test;

public abstract class BuilderBase<
        T, THIS extends BuilderBase<T, THIS, PARENT>, PARENT extends BuilderBase<?, PARENT, ?>> {
    PARENT parent = null;

    public BuilderBase() {}

    public BuilderBase(PARENT parent) {
        this.parent = parent;
    }

    public PARENT end() {
        return parent;
    }

    public THIS getThis() {
        return (THIS) this;
    }

    public abstract T build();

    protected THIS toThis() {
        return (THIS) this;
    }
}
