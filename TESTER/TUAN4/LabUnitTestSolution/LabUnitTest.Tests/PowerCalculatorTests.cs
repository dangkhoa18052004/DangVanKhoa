using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using LabUnitTest;

namespace LabUnitTest.Tests
{
    [TestClass]
    public class PowerCalculatorTests
    {
        [TestMethod]
        public void Power_N_Equals_0_Returns_1()
        {
            Assert.AreEqual(1.0, PowerCalculator.Power(5.5, 0), 1e-12);
        }

        [TestMethod]
        public void Power_PositiveN_Works()
        {
            Assert.AreEqual(8.0, PowerCalculator.Power(2, 3), 1e-12);
            Assert.AreEqual(16.0, PowerCalculator.Power(-2, 4), 1e-12);
            Assert.AreEqual(-8.0, PowerCalculator.Power(-2, 3), 1e-12);
        }

        [TestMethod]
        public void Power_NegativeN_Works()
        {
            Assert.AreEqual(0.25, PowerCalculator.Power(2, -2), 1e-12);
            Assert.AreEqual(-0.125, PowerCalculator.Power(-2, -3), 1e-12);
        }

        [TestMethod]
        public void Power_X_Is_Zero_PositiveN_Returns_0()
        {
            Assert.AreEqual(0.0, PowerCalculator.Power(0, 5), 1e-12);
        }

        [TestMethod]
        [ExpectedException(typeof(DivideByZeroException))]
        public void Power_X_Is_Zero_NegativeN_Throws()
        {
            PowerCalculator.Power(0, -1);
        }
    }
}
