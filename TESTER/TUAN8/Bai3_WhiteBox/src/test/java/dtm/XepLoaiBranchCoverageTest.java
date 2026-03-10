package dtm;

import org.testng.Assert;
import org.testng.annotations.Test;

public class XepLoaiBranchCoverageTest {

    @Test(description = "N1-True: diem khong hop le")
    public void testInvalidScore() {
        Assert.assertEquals(XepLoai.xepLoai(-1, false), "Diem khong hop le");
    }

    @Test(description = "N2-True: xep loai Gioi")
    public void testGioi() {
        Assert.assertEquals(XepLoai.xepLoai(9, false), "Gioi");
    }

    @Test(description = "N3-True: xep loai Kha")
    public void testKha() {
        Assert.assertEquals(XepLoai.xepLoai(7, false), "Kha");
    }

    @Test(description = "N4-True: xep loai Trung Binh")
    public void testTrungBinh() {
        Assert.assertEquals(XepLoai.xepLoai(6, false), "Trung Binh");
    }

    @Test(description = "N5-True: Thi lai")
    public void testThiLai() {
        Assert.assertEquals(XepLoai.xepLoai(4, true), "Thi lai");
    }

    @Test(description = "N5-False: Yeu - Hoc lai")
    public void testHocLai() {
        Assert.assertEquals(XepLoai.xepLoai(4, false), "Yeu - Hoc lai");
    }
}