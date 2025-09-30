package ex3;

public class OverloadMethodDrive {
    public static void main(String[] args) {
        System.out.println("Integer Sum: " + OverloadMethod.adding(2,6));
        System.out.println("Double Sum: " + OverloadMethod.adding(3.4,6.3, 7.0));
        System.out.println("String concatenate: " + OverloadMethod.adding("Hello ", "Mona"));

    }
}
