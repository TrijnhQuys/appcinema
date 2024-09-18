package com.heliwr.appbanvexemphim.Model;

import java.io.Serializable;

public class ALLNEWS implements Serializable {
    public int matin;
    public String tentin, tomtat, noidung, img, matheloaitin;

    public ALLNEWS(int matin, String tentin, String tomtat, String noidung, String img, String matheloaitin) {
        this.matin = matin;
        this.tentin = tentin;
        this.tomtat = tomtat;
        this.noidung = noidung;
        this.img = img;
        this.matheloaitin = matheloaitin;
    }

    public ALLNEWS() {
    }
}
