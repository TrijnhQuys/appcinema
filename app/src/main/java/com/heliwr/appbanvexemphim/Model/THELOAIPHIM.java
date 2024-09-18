package com.heliwr.appbanvexemphim.Model;

import java.io.Serializable;

public class THELOAIPHIM implements Serializable {
    public String matheloai, tentheloai, hinhtheloai;

    public THELOAIPHIM() {
    }

    public THELOAIPHIM(String matheloai, String tentheloai, String hinhtheloai) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
        this.hinhtheloai = hinhtheloai;
    }
}
