package ex1;

public class Life extends Insurance {

    public Life() {
        super("Life Insurance");
    }

@Override
public void setInsuranceCost(double monthlyCost){
    this.monthlyCost = monthlyCost;
}

@Override
public void displayInfo() {
    System.out.println("Insurance Type: " + getTypeOfInsurance() + ",Monthly Cost: $" + getMonthlyCost());
}

}
