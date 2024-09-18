package com.heliwr.appbanvexemphim.Model;

import java.io.Serializable;

public class HISTORY implements Serializable {
    public int iddonhang, idphim, iduser;
    public String timestartwatching, dateview, namerap, namefilm, name, email, seats, combofood, totalmoney;

    public HISTORY(int iddonhang, int idphim, int iduser, String timestartwatching, String dateview, String namerap, String namefilm, String name, String email, String seats, String combofood, String totalmoney) {
        this.iddonhang = iddonhang;
        this.idphim = idphim;
        this.iduser = iduser;
        this.timestartwatching = timestartwatching;
        this.dateview = dateview;
        this.namerap = namerap;
        this.namefilm = namefilm;
        this.name = name;
        this.email = email;
        this.seats = seats;
        this.combofood = combofood;
        this.totalmoney = totalmoney;
    }

    public HISTORY() {
    }
}
