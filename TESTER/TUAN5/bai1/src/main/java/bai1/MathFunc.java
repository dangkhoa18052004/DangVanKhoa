package bai1;

public class MathFunc {
    private int calls = 0;

    public int getCalls() {
        return calls;
    }

    public long factorial(int number) {
        calls++;
        if (number < 0)
            throw new IllegalArgumentException("number must be >= 0");
        long result = 1;
        for (int i = 1; i <= number; i++)
            result *= i;
        return result;
    }

    public long plus(int a, int b) {
        calls++;
        return (long) a + b;
    }
}
