package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int a) {
        return a - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int a) {
        Calculator calculator = new Calculator();
        return sum(a) + calculator.multiply(a) + minus(a) + calculator.divide(a);
    }

    public static void main(String[] args) {
        int result = Calculator.sum(10);
        System.out.println(result);
        result = Calculator.minus(10);
        System.out.println(result);
        Calculator calculator = new Calculator();
        result = calculator.divide(10);
        System.out.println(result);
        result = calculator.multiply(10);
        System.out.println(result);
        result = calculator.sumAllOperation(10);
        System.out.println(result);
    }
}
