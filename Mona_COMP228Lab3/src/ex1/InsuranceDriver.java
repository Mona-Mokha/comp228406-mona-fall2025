package ex1;

import java.util.Scanner;

public class InsuranceDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Insurance[] policies = new Insurance[3];

        for (int i = 0; i < policies.length; i++) {
            System.out.print("Enter insurance type (health/life): ");
            String type = sc.nextLine().trim().toLowerCase();

            Insurance policy;

            if (type.equals("health")) {
                policy = new Health();
            } else if (type.equals("life")) {
                policy = new Life();
            } else {
                System.out.println("Invalid type entered. Exiting program.");
                sc.close();
                return; // exit
            }

            System.out.print("Enter the monthly fee: ");
            double monthlyCost = sc.nextDouble();
            sc.nextLine(); // consume leftover newline

            policy.setInsuranceCost(monthlyCost);
            policies[i] = policy;
        }

        System.out.println("\n====Insurance Information:====");
        for (Insurance ins : policies) {
            ins.displayInfo();
        }


    }
}
