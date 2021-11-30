package at.researchstudio.sat.merkmalservice.model.mapping;

import java.util.List;

public class ConditionGroup implements Condition {
    private List<Condition> conditions;
    private Connective connective;
    private String id;

    public ConditionGroup() {}

    public ConditionGroup(String id, List<Condition> conditions, Connective connective) {
        this.conditions = conditions;
        this.connective = connective;
        this.id = id;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Connective getConnective() {
        return connective;
    }

    public String getId() {
        return id;
    }
}
