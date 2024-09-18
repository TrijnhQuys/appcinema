package com.heliwr.appbanvexemphim.Model;

public class THUCAN {
    public int mathucan, soluong;
    public String  tenthucan, mota,imgthucan;
    long gia;
    public THUCAN() {
    }

    public THUCAN(int mathucan, String tenthucan, long gia, String mota, String imgthucan) {
        this.mathucan = mathucan;
        this.tenthucan = tenthucan;
        this.gia = gia;
        this.mota = mota;
        this.imgthucan = imgthucan;
    }

    public int getSoluong() {
        return soluong;
    }

    public String getTenthucan() {
        return tenthucan;
    }

    public void setTenthucan(String tenthucan) {
        this.tenthucan = tenthucan;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public long getGia() {
        return gia;
    }

    public void setGia(long gia) {
        this.gia = gia;
    }
}
