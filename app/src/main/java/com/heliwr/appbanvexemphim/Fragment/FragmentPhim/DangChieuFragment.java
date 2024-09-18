package com.heliwr.appbanvexemphim.Fragment.FragmentPhim;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_DANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DangChieuFragment extends Fragment {
    RecyclerView rv_PhimDangChieu;
    ArrayList<PHIMDANGCHIEU> DangChieuData = new ArrayList<>();
    RV_ADAPTER_DANGCHIEU rv_Adapter_Dangchieu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dang_chieu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);

        rv_Adapter_Dangchieu = new RV_ADAPTER_DANGCHIEU(getContext(), DangChieuData);
        rv_PhimDangChieu.setAdapter(rv_Adapter_Dangchieu);
        LinearLayoutManager layoutManagerDangChieu = new GridLayoutManager(getContext(), 2);
        rv_PhimDangChieu.setLayoutManager(layoutManagerDangChieu);
        rv_PhimDangChieu.setHasFixedSize(true);

        loadPhimDangChieu();
    }

    public void anhxa(View view){
        rv_PhimDangChieu=view.findViewById(R.id.rvPhimDangChieu);
    }
    public void loadPhimDangChieu() {
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject dangchieuObject = (JSONObject) response.get(i);

                        PHIMDANGCHIEU phimdangchieu = new PHIMDANGCHIEU(
                                dangchieuObject.getInt("id"),
                                dangchieuObject.getString("title"),
                                dangchieuObject.getString("price"),
                                dangchieuObject.getString("description"),
                                dangchieuObject.getString("images"),
                                dangchieuObject.getString("category"),
                                dangchieuObject.getString("idcategory"),
                                dangchieuObject.getString("actor"),
                                dangchieuObject.getString("director"),
                                dangchieuObject.getString("producer"),
                                dangchieuObject.getString("nation"),
                                dangchieuObject.getString("showtime"),
                                dangchieuObject.getString("endshowtime"),
                                dangchieuObject.getString("duration"),
                                dangchieuObject.getString("videotrailer")
                                );
                        DangChieuData.add(phimdangchieu);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                rv_Adapter_Dangchieu.notifyDataSetChanged();
            }
        };
        Response.ErrorListener failure = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Failure: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlLayPhimDangChieu, success, failure);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
}