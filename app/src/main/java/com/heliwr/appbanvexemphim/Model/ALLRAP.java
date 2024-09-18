package com.heliwr.appbanvexemphim.Model;

import java.io.Serializable;

public class ALLRAP implements Serializable {
    public int marap;
    public String tenrap, diachi, phone;

    public ALLRAP() {
    }

    public ALLRAP(int marap, String tenrap, String diachi, String phone) {
        this.marap = marap;
        this.tenrap = tenrap;
        this.diachi = diachi;
        this.phone = phone;
    }
}
