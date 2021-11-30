package at.researchstudio.sat.merkmalservice.model.mapping;

public abstract class BaseAction implements Action {
    private String id;

    public BaseAction() {}

    public BaseAction(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
