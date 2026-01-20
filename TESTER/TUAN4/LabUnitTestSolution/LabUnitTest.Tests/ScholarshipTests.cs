using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Linq;
using LabUnitTest;

namespace LabUnitTest.Tests
{
    [TestClass]
    public class ScholarshipTests
    {
        [TestMethod]
        public void Eligible_WhenAvgAtLeast8_AndNoSubjectBelow5()
        {
            var hv = new HocVien("001", "A", "HCM", 8, 8, 8);
            Assert.IsTrue(hv.DuDieuKienHocBong());
        }

        [TestMethod]
        public void Eligible_Boundary_AvgExactly8()
        {
            // (8 + 8 + 8)/3 = 8
            var hv = new HocVien("002", "B", "HN", 8, 8, 8);
            Assert.IsTrue(hv.DuDieuKienHocBong());
        }

        [TestMethod]
        public void NotEligible_WhenAvgBelow8()
        {
            var hv = new HocVien("003", "C", "DN", 7.5, 8, 8);
            Assert.IsFalse(hv.DuDieuKienHocBong());
        }

        [TestMethod]
        public void NotEligible_WhenAnySubjectBelow5()
        {
            var hv = new HocVien("004", "D", "CT", 10, 10, 4.99);
            Assert.IsFalse(hv.DuDieuKienHocBong());
        }

        [TestMethod]
        public void Eligible_Boundary_SubjectExactly5()
        {
            // không có môn < 5, môn = 5 vẫn hợp lệ nếu DTB >= 8
            var hv = new HocVien("005", "E", "HCM", 9, 10, 5);
            Assert.IsTrue(hv.DuDieuKienHocBong());
        }

        [TestMethod]
        public void FilterScholarshipList_Works()
        {
            var ds = new[]
            {
                new HocVien("001","A","HCM", 8,8,8),      // eligible
                new HocVien("002","B","HN", 9,9,4),       // not (one <5)
                new HocVien("003","C","DN", 7,8,9),       // not (avg <8)
                new HocVien("004","D","CT", 8,9,9)        // eligible
            };

            var hb = TrungTamGiaSu.DanhSachHocBong(ds);
            Assert.AreEqual(2, hb.Count);
            CollectionAssert.AreEquivalent(new[] { "001", "004" }, hb.Select(x => x.MaSo).ToList());
        }

        [TestMethod]
        [ExpectedException(typeof(ArgumentOutOfRangeException))]
        public void ScoreOutOfRange_Throws()
        {
            _ = new HocVien("006", "F", "HCM", 11, 8, 8);
        }
    }
}
