package com.heliwr.appbanvexemphim.Fragment.FragmentNews;

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
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_ALLNEWS;
import com.heliwr.appbanvexemphim.Model.ALLNEWS;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienVienFragment extends Fragment {
    RecyclerView rv_DienVien;
    ArrayList<ALLNEWS>dienvienData=new ArrayList<>();
    RV_ADAPTER_ALLNEWS rvAdapterAllnews;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dien_vien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_DienVien=view.findViewById(R.id.rvDienVien);

        rvAdapterAllnews=new RV_ADAPTER_ALLNEWS(getContext(), dienvienData);
        rv_DienVien.setAdapter(rvAdapterAllnews);

        LinearLayoutManager layoutManagerDienVien=new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_DienVien.setLayoutManager(layoutManagerDienVien);
        rv_DienVien.setHasFixedSize(true);

        loadnewDienVien();

    }
    private void loadnewDienVien() {
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

                    JSONObject dienvienObject = null;
                    try {
                        dienvienObject = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    ALLNEWS allnews = null;
                    try {
                        allnews = new ALLNEWS( dienvienObject.getInt("id"),
                                dienvienObject.getString("title"),
                                dienvienObject.getString("summary"),
                                dienvienObject.getString("content"),
                                dienvienObject.getString("images"),
                                dienvienObject.getString("idcatenew"));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    dienvienData.add(allnews);
                }
                //báo adapter load dữ liệu lên
                rvAdapterAllnews.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "loi that bai: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest
                = new StringRequest(Request.Method.POST, SERVER.urllayNews,thanhcong, thatbai)
        {
            protected Map<String, String> getParams()throws AuthFailureError {
                HashMap params = new HashMap();
                params.put("idnew", "3");
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(stringRequest);

    }
}