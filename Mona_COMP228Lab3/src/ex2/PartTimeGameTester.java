package ex2;

public class PartTimeGameTester extends GameTester {

    private double hoursWorked;

    public PartTimeGameTester(String name, double hoursWorked) {

        super(name,false);
        this.hoursWorked = hoursWorked;
    }
    @Override
    public double salary() {
        return hoursWorked * 20.0;
    }
    @Override
    public String toString() {
        return "Part Time Game Tester: " + name + ", Salary: $" + salary();
    }

}
