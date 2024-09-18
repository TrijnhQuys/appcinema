package com.heliwr.appbanvexemphim.Model;

import java.io.Serializable;

public class PHIMSAPCHIEU implements Serializable {
    public int maphim;
    public String tenphim, giaphim, mota,imgphim,theloai, matheloai, dienvien,daodien, nhasanxuat, quocgia, khoichieu, ketthuc, thoiluong, videotrailer;


    public PHIMSAPCHIEU() {
    }

    public PHIMSAPCHIEU(int maphim, String tenphim, String giaphim, String mota, String imgphim, String theloai, String matheloai, String dienvien, String daodien, String nhasanxuat, String quocgia, String khoicheu, String ketthuc, String thoiluong, String videotrailer) {
        this.maphim = maphim;
        this.tenphim = tenphim;
        this.giaphim = giaphim;
        this.mota = mota;
        this.imgphim = imgphim;
        this.theloai = theloai;
        this.matheloai = matheloai;
        this.dienvien = dienvien;
        this.daodien = daodien;
        this.nhasanxuat = nhasanxuat;
        this.quocgia = quocgia;
        this.khoichieu = khoicheu;
        this.ketthuc=ketthuc;
        this.thoiluong = thoiluong;
        this.videotrailer=videotrailer;
    }

    public String getKhoichieu() {
        return khoichieu;
    }
}
