package ex2;

import java.util.Random;

public class Lotto {

    private int[] numbers =  new int[3];
    private Random random = new Random();


    public Lotto() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(9) + 1;  //from 1 to 9

        }

    }
    public int[] getNumbers() {
        return numbers;
    }
    public int getSum() {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];

        }
        return sum;
    }

}
