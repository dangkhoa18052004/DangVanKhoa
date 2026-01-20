using System;

namespace LabUnitTest
{
    public static class PowerCalculator
    {
        public static double Power(double x, int n)
        {
            if (n == 0) return 1.0;

            if (x == 0.0 && n < 0)
                throw new DivideByZeroException("x = 0 and n < 0 is invalid.");

            if (n > 0)
                return x * Power(x, n - 1);

            return Power(x, n + 1) / x;
        }
    }
}
