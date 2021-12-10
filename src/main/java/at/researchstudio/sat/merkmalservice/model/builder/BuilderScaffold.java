package at.researchstudio.sat.merkmalservice.model.builder;

public abstract class BuilderScaffold<T, THIS extends BuilderScaffold<T, THIS>> {
    public BuilderScaffold() {}

    public abstract T build();
}
