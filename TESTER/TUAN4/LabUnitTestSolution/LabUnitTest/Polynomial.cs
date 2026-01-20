using System;
using System.Collections.Generic;

namespace LabUnitTest
{
    public class Polynomial
    {
        private readonly int n;
        private readonly List<int> a;

        public Polynomial(int n, List<int> a)
        {
            // Điều kiện hợp lệ: n >= 0 và đủ n+1 hệ số
            if (n < 0 || a == null || a.Count != n + 1)
                throw new ArgumentException("Invalid Data");

            this.n = n;
            this.a = a;
        }

        // Tính P(x) = a0 + a1*x + ... + an*x^n
        public int Cal(double x)
        {
            int result = 0;
            for (int i = 0; i <= n; i++)
            {
                result += (int)(a[i] * Math.Pow(x, i));
            }
            return result;
        }
    }
}
