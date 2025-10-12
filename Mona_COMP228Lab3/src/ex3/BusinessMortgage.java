package ex3;

public class BusinessMortgage extends Mortgage {
    public BusinessMortgage (int mortgageNumber,  String customerName, double mortgageAmount, double primeRate, int term) {

    super(mortgageNumber, customerName, mortgageAmount, primeRate + 1.0, term);

    }

}
