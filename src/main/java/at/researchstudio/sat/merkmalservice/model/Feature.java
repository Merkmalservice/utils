package at.researchstudio.sat.merkmalservice.model;

public abstract class Feature {
    private final String name;

    public Feature(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
