package ex3;

import java.util.Scanner;

public class ProcessMortgage {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter current interest rate: ");
        double interestRate = scan.nextDouble();

        Mortgage[] mortgages = new Mortgage[3];

        for(int i = 0; i < mortgages.length; i++) {
            System.out.println("Mortgage Entry number " + (i + 1) + ": ");
            System.out.print("Enter Mortgage Type(business/personal): ");
            String type = scan.next().toLowerCase();

            System.out.print("Enter Mortgage ID Number: ");
            int number = scan.nextInt();
            scan.nextLine();

            System.out.print("Enter customer name: ");
            String name = scan.nextLine();

            System.out.print("Enter Mortgage Amount: ");
            double mortgageAmount = scan.nextDouble();

            System.out.println("Enter Term number:");
            System.out.print("Long-term(5 years) Enter number 5\nMedium-term(3 years) Enter number 3 \nShort-term(1 year) Enter number 1");
            System.out.print("\nYour choice: ");
            int term = scan.nextInt();

            if (type.equals("business")) {
                mortgages[i] = new BusinessMortgage(number, name, mortgageAmount, interestRate, term);
            } else {
                mortgages[i] = new PersonalMortgage(number, name, mortgageAmount, interestRate, term);

            }

        }
        System.out.println("\n******* Mortgages Information *******");
        for( Mortgage m : mortgages) {
            System.out.println(m.getMortgageInfo());
            System.out.println("_________________________");
        }



        }
    }
