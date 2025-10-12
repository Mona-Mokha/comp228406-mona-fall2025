package ex2;

public class FullTimeGameTester extends GameTester  {
    public FullTimeGameTester(String name) {
        super(name, true);

    }
    @Override
    public double salary() {
        return 3000.0;
    }
    @Override
    public String toString() {
        return "Full Time Game Tester: "  + name + ", Salary: $" + salary();
    }
}

