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
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_SAPCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SapChieuFragment extends Fragment {
    ArrayList<PHIMSAPCHIEU> SapChieuData = new ArrayList<>();
    RV_ADAPTER_SAPCHIEU rv_Adapter_Sapchieu;
    RecyclerView rv_PhimSapChieu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sap_chieu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);

        rv_Adapter_Sapchieu=new RV_ADAPTER_SAPCHIEU(getContext(), SapChieuData);
        rv_PhimSapChieu.setAdapter(rv_Adapter_Sapchieu);
        LinearLayoutManager layoutManagerSapChieu=new GridLayoutManager(getContext(), 2);
        rv_PhimSapChieu.setLayoutManager(layoutManagerSapChieu);
        rv_PhimSapChieu.setHasFixedSize(true);

        loadPhimSapChieu();
    }
    public void loadPhimSapChieu() {
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject sapchieuObject = (JSONObject) response.get(i);

                        PHIMSAPCHIEU phimsapchieu = new PHIMSAPCHIEU(
                                sapchieuObject.getInt("id"),
                                sapchieuObject.getString("title"),
                                sapchieuObject.getString("price"),
                                sapchieuObject.getString("description"),
                                sapchieuObject.getString("images"),
                                sapchieuObject.getString("category"),
                                sapchieuObject.getString("idcategory"),
                                sapchieuObject.getString("actor"),
                                sapchieuObject.getString("director"),
                                sapchieuObject.getString("producer"),
                                sapchieuObject.getString("nation"),
                                sapchieuObject.getString("showtime"),
                                sapchieuObject.getString("endshowtime"),
                                sapchieuObject.getString("duration"),
                                sapchieuObject.getString("videotrailer")
                                );
                        SapChieuData.add(phimsapchieu);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                rv_Adapter_Sapchieu.notifyDataSetChanged();
            }
        };
        Response.ErrorListener failure = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Failure: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlLayPhimSapChieu, success, failure);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
    public void anhxa(View view){
        rv_PhimSapChieu=view.findViewById(R.id.rvPhimSapChieu);
    }
}