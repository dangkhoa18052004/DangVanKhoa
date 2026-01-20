using System;
using System.Collections.Generic;
using System.Linq;

namespace LabUnitTest
{
    public class HocVien
    {
        public string MaSo { get; }
        public string HoTen { get; }
        public string QueQuan { get; }

        public double Mon1 { get; }
        public double Mon2 { get; }
        public double Mon3 { get; }

        public HocVien(string maSo, string hoTen, string queQuan, double mon1, double mon2, double mon3)
        {
            MaSo = maSo ?? throw new ArgumentNullException(nameof(maSo));
            HoTen = hoTen ?? throw new ArgumentNullException(nameof(hoTen));
            QueQuan = queQuan ?? throw new ArgumentNullException(nameof(queQuan));

            ValidateScore(mon1);
            ValidateScore(mon2);
            ValidateScore(mon3);

            Mon1 = mon1;
            Mon2 = mon2;
            Mon3 = mon3;
        }

        private static void ValidateScore(double s)
        {
            if (s < 0 || s > 10)
                throw new ArgumentOutOfRangeException(nameof(s), "Score must be in [0..10]");
        }

        public double DiemTrungBinh => (Mon1 + Mon2 + Mon3) / 3.0;

        // Điều kiện học bổng:
        // - DTB >= 8.0
        // - Không có môn nào < 5
        public bool DuDieuKienHocBong()
        {
            return DiemTrungBinh >= 8.0 && Mon1 >= 5.0 && Mon2 >= 5.0 && Mon3 >= 5.0;
        }
    }

    public static class TrungTamGiaSu
    {
        // Lọc danh sách học viên đủ điều kiện học bổng
        public static List<HocVien> DanhSachHocBong(IEnumerable<HocVien> ds)
        {
            if (ds == null) throw new ArgumentNullException(nameof(ds));
            return ds.Where(hv => hv.DuDieuKienHocBong()).ToList();
        }
    }
}
