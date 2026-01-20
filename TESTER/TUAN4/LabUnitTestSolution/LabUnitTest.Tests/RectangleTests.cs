using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using LabUnitTest;

namespace LabUnitTest.Tests
{
    [TestClass]
    public class RectangleTests
    {
        [TestMethod]
        public void Area_Works()
        {
            // TopLeft(0,10), BottomRight(5,0) => rộng 5, cao 10 => 50
            var r = new HinhChuNhat(new Diem(0, 10), new Diem(5, 0));
            Assert.AreEqual(50.0, r.DienTich(), 1e-12);
        }

        [TestMethod]
        public void Intersects_Overlapping_ReturnsTrue()
        {
            var r1 = new HinhChuNhat(new Diem(0, 10), new Diem(5, 0));
            var r2 = new HinhChuNhat(new Diem(3, 8), new Diem(8, -1));
            Assert.IsTrue(r1.GiaoNhau(r2));
        }

        [TestMethod]
        public void Intersects_Separated_ReturnsFalse()
        {
            var r1 = new HinhChuNhat(new Diem(0, 10), new Diem(5, 0));
            var r2 = new HinhChuNhat(new Diem(6, 10), new Diem(9, 0));
            Assert.IsFalse(r1.GiaoNhau(r2));
        }

        [TestMethod]
        public void Intersects_TouchingEdge_ReturnsTrue()
        {
            // r2 chạm cạnh phải của r1 tại x=5
            var r1 = new HinhChuNhat(new Diem(0, 10), new Diem(5, 0));
            var r2 = new HinhChuNhat(new Diem(5, 9), new Diem(7, 1));
            Assert.IsTrue(r1.GiaoNhau(r2));
        }

        [TestMethod]
        public void Constructor_InvalidRectangle_Throws()
        {
            // TopLeft phải có X <= BottomRight.X và Y >= BottomRight.Y
            Assert.ThrowsException<ArgumentException>(() =>
                new HinhChuNhat(new Diem(5, 0), new Diem(0, 10))
            );
        }

        [TestMethod]
        public void Constructor_NullPoint_Throws()
        {
            Assert.ThrowsException<ArgumentNullException>(() =>
                new HinhChuNhat(null, new Diem(1, 0))
            );
        }
    }
}
