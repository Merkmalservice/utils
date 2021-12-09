package at.researchstudio.sat.merkmalservice.model.builder.test;

public class Foo {
    String name;
    Bar bar;

    public Foo(String name, Bar bar) {
        this.name = name;
        this.bar = bar;
    }

    public Foo() {}

    public String getName() {
        return name;
    }

    public Bar getBar() {
        return bar;
    }
}
