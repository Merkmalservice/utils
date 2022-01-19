package at.researchstudio.sat.merkmalservice.model.qudt;

class ScaleFactor {
    private final double value;

    public ScaleFactor(double value) {
        this.value = value;
    }

    public ScaleFactor() {
        this(1);
    }

    public double getValue() {
        return value;
    }

    public ScaleFactor multiplyBy(double by) {
        return new ScaleFactor(this.value * by);
    }

    public ScaleFactor copy() {
        return new ScaleFactor(this.value);
    }

    @Override
    public String toString() {
        return "SF{" + value + '}';
    }
}
