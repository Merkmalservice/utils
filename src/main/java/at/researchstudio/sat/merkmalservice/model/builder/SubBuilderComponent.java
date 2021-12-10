package at.researchstudio.sat.merkmalservice.model.builder;

import java.util.Objects;

public class SubBuilderComponent<PARENT> implements SubBuilder<PARENT> {
    private PARENT parent;

    public SubBuilderComponent(PARENT parent) {
        Objects.requireNonNull(parent);
        this.parent = parent;
    }

    @Override
    public PARENT end() {
        return parent;
    }
}
