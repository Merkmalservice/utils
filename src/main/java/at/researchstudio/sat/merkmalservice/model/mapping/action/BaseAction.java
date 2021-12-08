package at.researchstudio.sat.merkmalservice.model.mapping.action;

import at.researchstudio.sat.merkmalservice.model.IBuilder;

public abstract class BaseAction implements Action {
    protected String id;

    public BaseAction() {}

    public BaseAction(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static class Builder<T extends BaseAction> implements IBuilder<T> {

        protected T product;

        protected Builder(T product) {
            this.product = product;
        }

        public Builder id(String id) {
            product.id = id;
            return this;
        }

        public T build() {
            return product;
        }
    }
}
