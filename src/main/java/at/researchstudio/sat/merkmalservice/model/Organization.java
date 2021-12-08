package at.researchstudio.sat.merkmalservice.model;

import java.util.Objects;

public class Organization {
    private String id;
    private String name;

    public Organization() {}

    public Organization(String id, String name) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements IBuilder<Organization> {
        private Organization product;

        public Builder() {
            this.product = new Organization();
        }

        public Organization build() {
            return product;
        }

        public Builder id(String id) {
            product.id = id;
            return this;
        }

        public Builder name(String name) {
            product.name = name;
            return this;
        }
    }
}
