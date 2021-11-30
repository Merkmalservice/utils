package at.researchstudio.sat.merkmalservice.model.mapping;

import at.researchstudio.sat.merkmalservice.model.Project;
import at.researchstudio.sat.merkmalservice.model.Standard;
import java.util.List;

public class Mapping {
    private String id;
    private String name;
    private Project project;
    private List<Standard> featureSets;
    private Condition condition;
    private List<ActionGroup<? extends Action>> actionGroups;

    public Mapping() {}

    public Mapping(
            String id,
            String name,
            Project project,
            List<Standard> featureSets,
            Condition condition,
            List<ActionGroup<? extends Action>> actionGroups) {
        this.id = id;
        this.name = name;
        this.project = project;
        this.featureSets = featureSets;
        this.condition = condition;
        this.actionGroups = actionGroups;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Project getProject() {
        return project;
    }

    public List<Standard> getFeatureSets() {
        return featureSets;
    }

    public Condition getCondition() {
        return condition;
    }

    public List<ActionGroup<? extends Action>> getActionGroups() {
        return actionGroups;
    }
}
