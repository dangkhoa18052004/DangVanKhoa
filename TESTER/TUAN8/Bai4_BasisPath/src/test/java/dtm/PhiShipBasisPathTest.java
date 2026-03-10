package dtm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PhiShipBasisPathTest {

    @Test(description = "Path 1: Trong luong khong hop le")
    public void testPath1_InvalidWeight() {
        Assert.assertThrows(IllegalArgumentException.class,
                () -> PhiShip.tinhPhiShip(-1, "noi_thanh", false));
    }

    @Test(description = "Path 2: Noi thanh, <= 5kg, khong member")
    public void testPath2_NoiThanhNheKhongMember() {
        double expected = 15000;
        Assert.assertEquals(PhiShip.tinhPhiShip(3, "noi_thanh", false), expected, 0.01);
    }

    @Test(description = "Path 3: Noi thanh, > 5kg, khong member")
    public void testPath3_NoiThanhNangKhongMember() {
        double expected = 15000 + (7 - 5) * 2000; // 19000
        Assert.assertEquals(PhiShip.tinhPhiShip(7, "noi_thanh", false), expected, 0.01);
    }

    @Test(description = "Path 4: Ngoai thanh, <= 3kg, khong member")
    public void testPath4_NgoaiThanhNheKhongMember() {
        double expected = 25000;
        Assert.assertEquals(PhiShip.tinhPhiShip(2, "ngoai_thanh", false), expected, 0.01);
    }

    @Test(description = "Path 5: Ngoai thanh, > 3kg, khong member")
    public void testPath5_NgoaiThanhNangKhongMember() {
        double expected = 25000 + (5 - 3) * 3000; // 31000
        Assert.assertEquals(PhiShip.tinhPhiShip(5, "ngoai_thanh", false), expected, 0.01);
    }

    @Test(description = "Path 6: Tinh khac, <= 2kg, khong member")
    public void testPath6_TinhKhacNheKhongMember() {
        double expected = 50000;
        Assert.assertEquals(PhiShip.tinhPhiShip(2, "tinh_khac", false), expected, 0.01);
    }

    @Test(description = "Path 7: Tinh khac, > 2kg, khong member")
    public void testPath7_TinhKhacNangKhongMember() {
        double expected = 50000 + (4 - 2) * 5000; // 60000
        Assert.assertEquals(PhiShip.tinhPhiShip(4, "tinh_khac", false), expected, 0.01);
    }

    @Test(description = "Path 8: Noi thanh, <= 5kg, co member")
    public void testPath8_NoiThanhNheCoMember() {
        double expected = 15000 * 0.9; // 13500
        Assert.assertEquals(PhiShip.tinhPhiShip(3, "noi_thanh", true), expected, 0.01);
    }
}