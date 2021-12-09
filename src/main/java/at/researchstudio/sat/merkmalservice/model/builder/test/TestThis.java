package at.researchstudio.sat.merkmalservice.model.builder.test;

public class TestThis {
    public static void main(String[] args) {
        Foo foo = new FooBuilder().name("thename").bar().message("theMessage").end().build();
    }
}
