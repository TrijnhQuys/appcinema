package com.heliwr.appbanvexemphim.Fragment.FragmentDate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_DANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PhimDate3Fragment extends Fragment {
    RecyclerView rv_PhimDate3;
    ArrayList<PHIMDANGCHIEU> phimdate3Data = new ArrayList<>();
    RV_ADAPTER_DANGCHIEU rv_Adapter_Dangchieudate3;
    public static String date3="2024-05-12";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        rv_Adapter_Dangchieudate3=new RV_ADAPTER_DANGCHIEU(getContext(), phimdate3Data);
        rv_PhimDate3.setAdapter(rv_Adapter_Dangchieudate3);
        LinearLayoutManager layoutManagerDate3=new GridLayoutManager(getContext(), 2);
        rv_PhimDate3.setLayoutManager(layoutManagerDate3);
        rv_PhimDate3.setHasFixedSize(true);

        loadphimdate3();
    }

    private void anhxa(View view) {
        rv_PhimDate3=view.findViewById(R.id.rvPhimData3);
    }
    private void loadphimdate3() {
        Response.Listener<String>thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);

                JSONArray jsonArray = null;

                try {
                    jsonArray = new JSONArray(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                for (int i =0;i<jsonArray.length();i++){

                    JSONObject date1Object = null;
                    try {
                        date1Object = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    PHIMDANGCHIEU phimdangchieu = null;
                    try {
                        phimdangchieu = new PHIMDANGCHIEU( date1Object.getInt("id"),
                                date1Object.getString("title"),
                                date1Object.getString("price"),
                                date1Object.getString("description"),
                                date1Object.getString("images"),
                                date1Object.getString("category"),
                                date1Object.getString("idcategory"),
                                date1Object.getString("actor"),
                                date1Object.getString("director"),
                                date1Object.getString("producer"),
                                date1Object.getString("nation"),
                                date1Object.getString("showtime"),
                                date1Object.getString("endshowtime"),
                                date1Object.getString("duration"),
                                date1Object.getString("videotrailer")
                        );
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    phimdate3Data.add(phimdangchieu);
                }
                //báo adapter load dữ liệu lên
                rv_Adapter_Dangchieudate3.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi that bai: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest
                = new StringRequest(Request.Method.POST, SERVER.urlLayPhimDangChieu,thanhcong, thatbai)
        {
            protected Map<String, String> getParams()throws AuthFailureError {
                HashMap params = new HashMap();
                params.put("ngaymuon", date3);
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(stringRequest);

    }
}