using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using LabUnitTest;

namespace LabUnitTest.Tests
{
    [TestClass]
    public class RadixTests
    {
        [TestMethod]
        public void Convert_Base2_Works()
        {
            Assert.AreEqual("1010", new Radix(10).ConvertDecimalToAnother(2));
            Assert.AreEqual("11111111", new Radix(255).ConvertDecimalToAnother(2));
        }

        [TestMethod]
        public void Convert_Base16_Works()
        {
            Assert.AreEqual("F", new Radix(15).ConvertDecimalToAnother(16));
            Assert.AreEqual("1F", new Radix(31).ConvertDecimalToAnother(16));
            Assert.AreEqual("FF", new Radix(255).ConvertDecimalToAnother(16));
        }

        [TestMethod]
        public void Convert_NumberZero_ReturnsZero()
        {
            Assert.AreEqual("0", new Radix(0).ConvertDecimalToAnother(2));
            Assert.AreEqual("0", new Radix(0).ConvertDecimalToAnother(16));
        }

        [TestMethod]
        public void Constructor_NegativeNumber_ThrowsIncorrectValue()
        {
            var ex = Assert.ThrowsException<ArgumentException>(() => new Radix(-1));
            Assert.AreEqual("Incorrect Value", ex.Message);
        }

        [TestMethod]
        public void Convert_InvalidRadix_ThrowsInvalidRadix()
        {
            var ex = Assert.ThrowsException<ArgumentException>(() => new Radix(10).ConvertDecimalToAnother(1));
            Assert.AreEqual("Invalid Radix", ex.Message);

            ex = Assert.ThrowsException<ArgumentException>(() => new Radix(10).ConvertDecimalToAnother(17));
            Assert.AreEqual("Invalid Radix", ex.Message);
        }

        [TestMethod]
        public void Convert_Base8_Works()
        {
            Assert.AreEqual("12", new Radix(10).ConvertDecimalToAnother(8));
        }
    }
}
