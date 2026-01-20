using System;

namespace LabUnitTest
{
    // Điểm (x, y)
    public class Diem
    {
        public double X { get; }
        public double Y { get; }

        public Diem(double x, double y)
        {
            X = x;
            Y = y;
        }
    }

    // Hình chữ nhật xác định bởi TopLeft và BottomRight
    public class HinhChuNhat
    {
        public Diem TopLeft { get; }
        public Diem BottomRight { get; }

        public double Left => TopLeft.X;
        public double Right => BottomRight.X;
        public double Top => TopLeft.Y;
        public double Bottom => BottomRight.Y;

        public HinhChuNhat(Diem topLeft, Diem bottomRight)
        {
            TopLeft = topLeft ?? throw new ArgumentNullException(nameof(topLeft));
            BottomRight = bottomRight ?? throw new ArgumentNullException(nameof(bottomRight));

            // Quy ước chuẩn: TopLeft ở trên-trái, BottomRight ở dưới-phải
            // => Left <= Right và Top >= Bottom
            if (Left > Right || Top < Bottom)
                throw new ArgumentException("Invalid Rectangle");
        }

        // Diện tích = (Right-Left) * (Top-Bottom)
        public double DienTich()
        {
            return (Right - Left) * (Top - Bottom);
        }

        // Kiểm tra giao nhau: mặc định chạm cạnh/góc cũng tính là giao nhau
        public bool GiaoNhau(HinhChuNhat other)
        {
            if (other == null) throw new ArgumentNullException(nameof(other));

            // Không giao nhau khi một hình nằm hoàn toàn bên trái/phải/trên/dưới hình kia
            return !(other.Left > Right ||
                     other.Right < Left ||
                     other.Bottom > Top ||
                     other.Top < Bottom);
        }
    }
}
