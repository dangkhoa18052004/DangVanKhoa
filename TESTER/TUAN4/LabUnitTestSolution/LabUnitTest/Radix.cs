using System;
using System.Collections.Generic;

namespace LabUnitTest
{
    public class Radix
    {
        private readonly int number;

        public Radix(int number)
        {
            if (number < 0) throw new ArgumentException("Incorrect Value");
            this.number = number;
        }

        public string ConvertDecimalToAnother(int radix = 2)
        {
            if (radix < 2 || radix > 16) throw new ArgumentException("Invalid Radix");

            int n = this.number;
            if (n == 0) return "0";

            List<char> result = new List<char>();
            while (n > 0)
            {
                int value = n % radix;
                char digit = (value < 10) ? (char)('0' + value) : (char)('A' + (value - 10));
                result.Add(digit);
                n /= radix;
            }

            result.Reverse();
            return new string(result.ToArray());
        }
    }
}
