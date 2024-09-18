package com.heliwr.appbanvexemphim.Model;

import java.util.ArrayList;
import java.util.List;

public class SERVER {
    public static String path = "http://192.168.245.193:8080/apivexemphim/";
    public static String urlImgSlide = path + "imgslide/";
    public static String urlImgTheLoai = path + "imgtheloaiphim/";
    public static String urlLayTheLoai = path + "getcategory.php";
    public static String urrImgAllPhim = path + "imgallphim/";
    public static String urlLayAllPhim = path + "getallfilm.php";

    public static String urlLayPhimDangChieu = path + "getfilmplaying.php";
    public static String urlLayPhimSapChieu = path + "getfilmupcoming.php";
    public static String urlLogin ="http://192.168.245.193:3000/dang-nhap";
    public static String urlRegister = "http://192.168.245.193:3000/dang-ky";
    public static String urlDatve = "http://192.168.245.193:3000/dat-ve";
    public static String urlLoadGhe = "http://192.168.245.193:3000/load-ghe";

    public static String urlLayAllRap = path + "getallrap.php";

    public static String urlImgNews=path+"imgallnews/";
    public static String urllayNews=path+"getnews.php";

    public static String urlImgThucAn= path + "imgthucan/";
    public static String urllaythucan=path+"getfood.php";

    public static String urlLayHistory = path + "gethistory.php";
}
