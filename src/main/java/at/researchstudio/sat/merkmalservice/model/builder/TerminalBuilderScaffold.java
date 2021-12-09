package at.researchstudio.sat.merkmalservice.model.builder;

public abstract class TerminalBuilderScaffold<
                T,
                THIS extends TerminalBuilderScaffold<T, THIS, PARENT>,
                PARENT extends BuilderScaffold<PARENT, ?>>
        extends BuilderScaffold<THIS, PARENT> {
    public TerminalBuilderScaffold(PARENT parent) {
        super(parent);
    }

    public TerminalBuilderScaffold() {}

    public abstract T build();
}
