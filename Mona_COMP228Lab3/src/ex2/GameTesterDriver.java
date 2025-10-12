package ex2;

import java.util.Scanner;

public class GameTesterDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Your Name: ");
        String name = input.nextLine();
        System.out.print("Enter Game Tester Type(full/part):  ");
        String type = input.nextLine().toLowerCase();

        GameTester tester;
        if (type.equals("full")) {
            tester = new FullTimeGameTester(name);}
            else {
                System.out.print("Enter number of hours worked: ");
                double hours = input.nextDouble();
                tester = new PartTimeGameTester(name, hours);
        }
        System.out.print("\n ***** Tester Information *****");
        System.out.println(tester);

        input.close();
    }
}
