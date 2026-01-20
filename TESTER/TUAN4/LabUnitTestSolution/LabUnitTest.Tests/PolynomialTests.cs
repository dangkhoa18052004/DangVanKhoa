using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using LabUnitTest;

namespace LabUnitTest.Tests
{
    [TestClass]
    public class PolynomialTests
    {
        [TestMethod]
        public void Cal_ValidPolynomial_ReturnsCorrectValue()
        {
            // P(x) = 1 + 2x + 3x^2, x=2 => 17
            var p = new Polynomial(2, new List<int> { 1, 2, 3 });
            Assert.AreEqual(17, p.Cal(2));
        }

        [TestMethod]
        public void Cal_WithNegativeCoefficients_Works()
        {
            // P(x) = -1 + 0*x + 2*x^2, x=3 => 17
            var p = new Polynomial(2, new List<int> { -1, 0, 2 });
            Assert.AreEqual(17, p.Cal(3));
        }

        [TestMethod]
        public void Constructor_WrongCoefficientCount_ThrowsInvalidData()
        {
            // n=2 cần 3 hệ số, nhưng chỉ đưa 2 hệ số
            var ex = Assert.ThrowsException<ArgumentException>(() =>
                new Polynomial(2, new List<int> { 1, 2 })
            );
            Assert.AreEqual("Invalid Data", ex.Message);
        }

        [TestMethod]
        public void Constructor_NegativeDegree_ThrowsInvalidData()
        {
            var ex = Assert.ThrowsException<ArgumentException>(() =>
                new Polynomial(-1, new List<int> { 1 })
            );
            Assert.AreEqual("Invalid Data", ex.Message);
        }

        [TestMethod]
        public void Constructor_NullList_ThrowsInvalidData()
        {
            var ex = Assert.ThrowsException<ArgumentException>(() =>
                new Polynomial(1, null)
            );
            Assert.AreEqual("Invalid Data", ex.Message);
        }
    }
}
