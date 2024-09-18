package com.heliwr.appbanvexemphim.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_THELOAI;
import com.heliwr.appbanvexemphim.Adapter.ViewPagerAdapter.ViewPagerAdapterHome;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.THELOAIPHIM;
import com.heliwr.appbanvexemphim.Model.UserManager;
import com.heliwr.appbanvexemphim.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FRAGMENT_HOME extends Fragment {
    ViewFlipper viewFlipper;
    RecyclerView rv_TheLoai;
    ArrayList<THELOAIPHIM> TheLoaiData = new ArrayList<>();


    RV_ADAPTER_THELOAI rv_Adapter_Home;


    ViewPager2 viewPager2;
    TabLayout tabLayout;
    ViewPagerAdapterHome viewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_layout_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        boolean UserLoggedIn = UserManager.getuserloggedin(getContext());
//        Toast.makeText(getContext(), "Login:"+UserLoggedIn, Toast.LENGTH_SHORT).show();
        anhxa(view);
        LoadViewFlipper();
        rv_Adapter_Home = new RV_ADAPTER_THELOAI(getContext(), TheLoaiData);
        rv_TheLoai.setAdapter(rv_Adapter_Home);
        LinearLayoutManager layoutManagerTheLoai = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_TheLoai.setLayoutManager(layoutManagerTheLoai);
        rv_TheLoai.setHasFixedSize(true);

        loadTheLoai();

        viewPagerAdapter = new ViewPagerAdapterHome(this);
        viewPager2.setAdapter(viewPagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }
    public void loadTheLoai() {
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject theloaiObject = (JSONObject) response.get(i);
                        //các biến trong class server
                        THELOAIPHIM theloaiphim = new THELOAIPHIM(theloaiObject.getString("idcategory"), theloaiObject.getString("namecategory"), theloaiObject.getString("imgcategory"));
                        TheLoaiData.add(theloaiphim);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Failure erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        throw new RuntimeException(e);
                    }
                }
                rv_Adapter_Home.notifyDataSetChanged();
            }
        };
        Response.ErrorListener failure = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Failure erro:" + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlLayTheLoai, success, failure);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    private void anhxa(View view) {
        viewFlipper = view.findViewById(R.id.viewFlipper);
        rv_TheLoai = view.findViewById(R.id.rvTheLoaiPhim);
        viewPager2 = view.findViewById(R.id.viewPager2);
        tabLayout = view.findViewById(R.id.tabLayout);

    }
    public void LoadViewFlipper() {
        ArrayList<String> slide = new ArrayList<>();
        slide.add(SERVER.urlImgSlide + "slide1.jpg");
        slide.add(SERVER.urlImgSlide + "slide2.jpg");
        slide.add(SERVER.urlImgSlide + "slide3.jpg");
        slide.add(SERVER.urlImgSlide + "slide4.png");
        // Thêm mã để hiển thị slide trên viewFlipper ở đây
        for (int i = 0; i < slide.size(); i++) {
            ImageView img = new ImageView(getContext());
            Picasso.get().load(slide.get(i)).into(img);
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(img);
        }
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TheLoaiData.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewPager2.setAdapter(null);
    }

}
