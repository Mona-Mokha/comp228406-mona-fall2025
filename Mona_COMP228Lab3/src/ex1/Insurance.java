package ex1;

public abstract class Insurance {

    protected String typeOfInsurance;
    protected double monthlyCost;

    public Insurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }
    public double getMonthlyCost() {
        return monthlyCost;
    }

    public abstract void setInsuranceCost(double monthlyCost);
     public abstract void displayInfo() ;
}



