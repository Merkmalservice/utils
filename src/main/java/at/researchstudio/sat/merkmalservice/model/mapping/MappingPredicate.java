package at.researchstudio.sat.merkmalservice.model.mapping;

public enum MappingPredicate {
    NOT,
    EQUALS,
    CONTAINS,
    CONTAINS_NOT,
    MATCHES,
    PRESENT(true),
    NOT_PRESENT(true),
    HAS,
    HAS_NOT,
    LESS_THAN,
    LESS_OR_EQUALS,
    GREATER_THAN,
    GREATER_OR_EQUALS;

    private boolean valueless;

    MappingPredicate() {
        this.valueless = false;
    }

    MappingPredicate(boolean valueless) {
        this.valueless = valueless;
    }

    public boolean isValueless() {
        return valueless;
    }
}
