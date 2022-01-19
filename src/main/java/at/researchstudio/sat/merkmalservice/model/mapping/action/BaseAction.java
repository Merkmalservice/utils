package at.researchstudio.sat.merkmalservice.model.mapping.action;

import at.researchstudio.sat.merkmalservice.model.builder.BuilderScaffold;
import at.researchstudio.sat.merkmalservice.model.builder.SubBuilderScaffold;

public abstract class BaseAction implements Action {
    protected String id;

    public BaseAction() {}

    public BaseAction(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    protected abstract static class MyBuilderScaffold<
                    T extends BaseAction,
                    THIS extends MyBuilderScaffold<T, THIS, PARENT>,
                    PARENT extends BuilderScaffold<?, PARENT>>
            extends SubBuilderScaffold<T, THIS, PARENT> {

        protected T product;

        protected MyBuilderScaffold(T product) {
            this.product = product;
        }

        public MyBuilderScaffold(PARENT parent, T product) {
            super(parent);
            this.product = product;
        }

        public THIS id(String id) {
            product.id = id;
            return (THIS) this;
        }

        public T build() {
            return product;
        }
    }
}
