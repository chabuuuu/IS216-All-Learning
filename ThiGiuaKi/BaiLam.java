import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

abstract class ChuyenBay {
    protected String maChuyen;
    protected String tenChuyen;
    protected String soHieuMB;
    protected String loaiMB;
    protected String ngKH;
    protected String gioKH;
    protected String noiDi;
    protected String noiDen;
    protected float quangDuong;
    protected float doanhThu;
    protected  String loaiChuyenBay;

    public ChuyenBay() {
        maChuyen = "";
        tenChuyen = "";
        soHieuMB = "";
        loaiMB = "";
        ngKH = "";
        gioKH = "";
        noiDi = "";
        noiDen = "";
        quangDuong = 0;
        doanhThu = 0;
    }

    public ChuyenBay(String maChuyen, String tenChuyen, String soHieuMB, String loaiMB, String ngKH, String gioKH,
                     String noiDi, String noiDen, float quangDuong, float doanhThu) {
        this.maChuyen = maChuyen;
        this.tenChuyen = tenChuyen;
        this.soHieuMB = soHieuMB;
        this.loaiMB = loaiMB;
        this.ngKH = ngKH;
        this.gioKH = gioKH;
        this.noiDi = noiDi;
        this.noiDen = noiDen;
        this.quangDuong = quangDuong;
        this.doanhThu = doanhThu;
    }

    public void xuatThongTin(){
        System.out.println("Mã chuyến bay: " + maChuyen);
        System.out.println("Tên chuyến bay: " + tenChuyen);
        System.out.println("Số hiệu máy bay: " + soHieuMB);
        System.out.println("Loại máy bay: " + loaiMB);
        System.out.println("Ngày khởi hành: " + ngKH);
        System.out.println("Giờ khởi hành: " + gioKH );
        System.out.println("Nơi đi: " + noiDi);
        System.out.println("Nơi đến: " + noiDen);
        System.out.println("Quãng đuường: " + quangDuong);
        System.out.println("Doanh thu: " + doanhThu);
    }

    public String getSoHieuMB (){
        return soHieuMB;
    }

    public float getQuangDuong(){
        return quangDuong;
    }
    public String getLoaiChuyenBay() {
        return loaiChuyenBay;
    }
    public float getDoanhThu(){
        return doanhThu;
    }
}

class ChuyenBayChoKhachQuocNoi extends ChuyenBay {
    private int soChoDaDat;
    public ChuyenBayChoKhachQuocNoi(){
        super();
        this.loaiChuyenBay = "Chuyến bay chở khách quốc nội";
    }
    public ChuyenBayChoKhachQuocNoi(String maChuyen, String tenChuyen, String soHieuMB, String loaiMB, String ngKH, String gioKH,
                             String noiDi, String noiDen, float quangDuong, float doanhThu, int soChoDaDat){
        super(maChuyen, tenChuyen, soHieuMB, loaiMB, ngKH, gioKH, noiDi, noiDen, quangDuong, doanhThu);
        this.soChoDaDat = soChoDaDat;
        this.loaiChuyenBay = "Chuyến bay chở khách quốc nội";
    }
    public void xuatThongTin (){
        super.xuatThongTin();
        System.out.println("Số chỗ đã đặt: " + soChoDaDat);
    }
}

class ChuyenBayChoKhachQuocTe extends ChuyenBay {
    private String noiQuaCanh;
    private  int soChoDaDat;

    public ChuyenBayChoKhachQuocTe(){
        super();
        this.loaiChuyenBay = "Chuyến bay chở khách quốc tế";
        noiQuaCanh = "";
        soChoDaDat = 0;
    }
    public ChuyenBayChoKhachQuocTe(String maChuyen, String tenChuyen, String soHieuMB, String loaiMB, String ngKH, String gioKH,
                                   String noiDi, String noiDen, float quangDuong, float doanhThu, String noiQuaCanh, int soChoDaDat){
        super(maChuyen, tenChuyen, soHieuMB, loaiMB, ngKH, gioKH, noiDi, noiDen, quangDuong, doanhThu);
        this.noiQuaCanh = noiQuaCanh;
        this.soChoDaDat = soChoDaDat;
        this.loaiChuyenBay = "Chuyến bay chở khách quốc tế";
    }

    public void xuatThongTin(){
        super.xuatThongTin();
        System.out.println("Nơi quá cảnh: " + noiQuaCanh);
        System.out.println("Số chỗ đã đặt: " + soChoDaDat);
    }
}

class ChuyenBayChoHangQuocTe extends ChuyenBay {
    private  float khoiLuongHangHoa;
    private int soKienHang;

    public ChuyenBayChoHangQuocTe(){
        super();
        khoiLuongHangHoa = 0;
        soKienHang = 0;
        loaiChuyenBay = "Chuyến bay chở hàng quốc tế";
    }
    public ChuyenBayChoHangQuocTe(String maChuyen, String tenChuyen, String soHieuMB, String loaiMB, String ngKH, String gioKH,
                                  String noiDi, String noiDen, float quangDuong, float doanhThu, float khoiLuongHangHoa, int soKienHang){
        super(maChuyen, tenChuyen, soHieuMB, loaiMB, ngKH, gioKH, noiDi, noiDen, quangDuong, doanhThu);
        this.soKienHang = soKienHang;
        this.khoiLuongHangHoa = khoiLuongHangHoa;
        loaiChuyenBay = "Chuyến bay chở hàng quốc tế";
    }
    public void xuatThongTin(){
        super.xuatThongTin();
        System.out.println("Số kiện hàng: " + soKienHang);
        System.out.println("Khối lượng hàng hóa: " + khoiLuongHangHoa);
    }
}

class QuanLyChuyenBay {
    private int soChuyenBay;
    private ArrayList<ChuyenBay> dsCB;

    public QuanLyChuyenBay(int soChuyenBay){
        this.soChuyenBay = soChuyenBay;
        dsCB = new ArrayList<ChuyenBay>(soChuyenBay);
    }

    public void nhapChuyenBay(){
        System.out.println("Chọn loại chuyến bay: 1. Chuyến bay chở khách quốc nội, 2. Chuyến bay chở Khách quốc tế, 3. Chuyến bay chở hàng quốc nội");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        Scanner scanner_new = new Scanner(System.in);

        System.out.println("Nhập mã chuyến bay");
        String maChuyen = scanner_new.nextLine();
        System.out.println("Nhập tên chuyến bay");
        String tenChuyen = scanner_new.nextLine();
        System.out.println("Nhập số hiệu máy bay");
        String soHieuMB = scanner_new.nextLine();
        System.out.println("Nhập loaại máy bay");
        String loaiMB = scanner_new.nextLine();
        System.out.println("Nhập ngày khởi hành");
        String ngKH = scanner_new.nextLine();
        System.out.println("Nhập giờ khởi hành");
        String gioKH = scanner_new.nextLine();
        System.out.println("Nhập nơi đi");
        String noiDi = scanner_new.nextLine();
        System.out.println("Nhập noơi đến");
        String noiDen = scanner_new.nextLine();
        System.out.println("Nhập quãng đường");
        float quangDuong = scanner_new.nextFloat();
        System.out.println("Nhập doanh thu");
        float doanhThu = scanner_new.nextFloat();
        switch (type){
            case 1:
                System.out.println("Nhập số chỗ đã đặt");
                int soChoDaDat = scanner_new.nextInt();
                dsCB.add(new ChuyenBayChoKhachQuocNoi(maChuyen, tenChuyen, soHieuMB, loaiMB, ngKH, gioKH, noiDi, noiDen, quangDuong, doanhThu, soChoDaDat));
                break;
                case 2:
                System.out.println("Nhập số chỗ đã đặt");
                int soChoDaDat_new = scanner_new.nextInt();
                System.out.println("Nhập nơi quá cảnh");
                String noiQuaCanh = scanner_new.nextLine();
                dsCB.add(new ChuyenBayChoKhachQuocTe(maChuyen, tenChuyen, soHieuMB, loaiMB, ngKH, gioKH, noiDi, noiDen, quangDuong, doanhThu, noiQuaCanh, soChoDaDat_new));
                break;
                case 3:
                System.out.println("Nhập khối luượng hàng hóa: ");
                float khoiLuongHangHoa = scanner_new.nextFloat();
                System.out.println("Nhập số kiện hàng");
                int soKienHang = scanner_new.nextInt();
                dsCB.add(new ChuyenBayChoHangQuocTe(maChuyen, tenChuyen, soHieuMB, loaiMB, ngKH, gioKH, noiDi, noiDen, quangDuong, doanhThu, khoiLuongHangHoa, soKienHang));
                break;
        }
    }

    public void xuatChuyenBay (){
        for (ChuyenBay chuyenBay : dsCB){
            chuyenBay.xuatThongTin();
        }
    }

    public void tinhSoChangBay(String soHieuMB){
        int soChangBay = 0;
        for (ChuyenBay chuyenBay : dsCB){
            if (soHieuMB.equals(chuyenBay.getSoHieuMB())){
                soChangBay ++;
            }
        }
        System.out.println("Số chặng bay là: " + soChangBay);
    }
    public void tinhQuangDuongBay(String soHieuMB){
        float quangDuongBay = 0;
        for (ChuyenBay chuyenBay : dsCB){
            if (chuyenBay.getSoHieuMB() == soHieuMB){
                quangDuongBay = quangDuongBay + chuyenBay.getQuangDuong();
            }
        }
        System.out.println("Tổng quãng đường bay là: " + quangDuongBay);
    }
    public void tinhTongDoanhThu(){
        float doanhThuChoHangQuocNoi = 0;
        float doanhThuChoKhachQuocTe = 0;
        float doanhThuChoKhachQuocNoi = 0;
        ChuyenBay max_choKhangQuocNoi = new ChuyenBayChoKhachQuocNoi();
        ChuyenBay max_choKhangQuocTe = new ChuyenBayChoKhachQuocTe();
        ChuyenBay max_choHangQuocNoi = new ChuyenBayChoHangQuocTe();
        for (ChuyenBay chuyenBay : dsCB){
            if (Objects.equals(chuyenBay.getLoaiChuyenBay(), "Chuyến bay chở khách quốc nội")){
                doanhThuChoKhachQuocNoi += chuyenBay.getDoanhThu();
                if (chuyenBay.getDoanhThu() > max_choKhangQuocNoi.getDoanhThu()){
                    max_choHangQuocNoi = chuyenBay;
                }
                continue;
            }
            if (Objects.equals(chuyenBay.getLoaiChuyenBay(), "Chuyến bay chở khách quốc tế")){
                doanhThuChoKhachQuocTe += chuyenBay.getDoanhThu();
                if (chuyenBay.getDoanhThu() > max_choKhangQuocTe.getDoanhThu()){
                    max_choKhangQuocTe = chuyenBay;
                }
                continue;
            }
            if (Objects.equals(chuyenBay.getLoaiChuyenBay(), "Chuyến bay chở hàng quốc tế")){
                doanhThuChoHangQuocNoi += chuyenBay.getDoanhThu();
                if (chuyenBay.getDoanhThu() > max_choHangQuocNoi.getDoanhThu()){
                    max_choHangQuocNoi = chuyenBay;
                }
                continue;
            }
        }
        System.out.println("Tổng doanh thu: ");
        System.out.println("Chở khách quốc tế: " + doanhThuChoKhachQuocTe);
        System.out.println("Chở khách quốc nội: " + doanhThuChoKhachQuocNoi);
        System.out.println("Chở hàng quốc nội: " + doanhThuChoHangQuocNoi);

        System.out.println("Doanh thu cao nhất từng loại: ");
        System.out.println("Chở khách quốc tế: ");
        max_choKhangQuocTe.xuatThongTin();
        System.out.println("Chở khách quốc nội: ");
        max_choKhangQuocNoi.xuatThongTin();
        System.out.println("Chở hàng quốc nội: ");
        max_choHangQuocNoi.xuatThongTin();
    }
}


public class BaiLam {
    public static void main(String[] args) {
        System.out.println("Nhập số lượng chuyến bay");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        QuanLyChuyenBay quanLy = new QuanLyChuyenBay(count);
        for (int i = 0; i < count; i++){
            quanLy.nhapChuyenBay();
        }
        System.out.println("Nhập số hiệu máy bay muốn tính số chặng bay");
        Scanner scanner_sohieu = new Scanner(System.in);
        String sohieu = scanner_sohieu.nextLine();
        quanLy.tinhSoChangBay(sohieu);
        quanLy.tinhTongDoanhThu();
    }
}
