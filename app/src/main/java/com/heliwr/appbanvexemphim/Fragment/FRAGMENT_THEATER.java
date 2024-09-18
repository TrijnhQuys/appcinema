package com.heliwr.appbanvexemphim.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_ALLRAP;
import com.heliwr.appbanvexemphim.Model.ALLRAP;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FRAGMENT_THEATER extends Fragment {
    RecyclerView rv_DanhSachRap;
    ArrayList<ALLRAP> rapdata = new ArrayList<>();
    RV_ADAPTER_ALLRAP rv_adapter_allrap;
    String[] item_tinhthanh = {"Hồ Chí Minh", "Hà Nội", "Bến Tre", "Đà Nẵng", "Đồng Nai", "Cần Thơ"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItemTinhThanh;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_layout_theater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        rv_adapter_allrap = new RV_ADAPTER_ALLRAP(getContext(), rapdata);
        rv_DanhSachRap.setAdapter(rv_adapter_allrap);
        LinearLayoutManager layoutManagerAllRap = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rv_DanhSachRap.setLayoutManager(layoutManagerAllRap);

        loadDanhSachRap();

        adapterItemTinhThanh = new ArrayAdapter<String>(getContext(), R.layout.list_item_tinh_thanh, item_tinhthanh);
        autoCompleteTextView.setAdapter(adapterItemTinhThanh);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Tỉnh: " + item, Toast.LENGTH_SHORT).show();
                searchList(item);
            }
        });

    }

    private void capnhatOptionTinhThanh() {
        ArrayAdapter<String> adapterItemTinhThanh = new ArrayAdapter<String>(getContext(), R.layout.list_item_tinh_thanh, item_tinhthanh);
        autoCompleteTextView.setAdapter(adapterItemTinhThanh);
        autoCompleteTextView.setText("");
    }

    public void searchList(String text) {
        ArrayList<ALLRAP> filteredList = new ArrayList<>();
        for (ALLRAP allrap : rapdata) {
            if (allrap.diachi.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(allrap);
            }
        }
        rv_adapter_allrap.searchDatList(filteredList);
    }

    public void loadDanhSachRap() {
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject allrapObject = (JSONObject) response.get(i);

                        ALLRAP allrap = new ALLRAP(
                                allrapObject.getInt("idrap"),
                                allrapObject.getString("namerap"),
                                allrapObject.getString("adress"),
                                allrapObject.getString("phone")
                        );
                        rapdata.add(allrap);
                        Log.d("FRAGMENT_THEATER", "Response" + response.toString());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                rv_adapter_allrap.notifyDataSetChanged();
            }
        };
        Response.ErrorListener failure = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Failure: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlLayAllRap, success, failure);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }

    public void anhxa(View view) {
        rv_DanhSachRap = view.findViewById(R.id.rvDanhSachRap);
        autoCompleteTextView = view.findViewById(R.id.auto_complete_txt);
//        searchViewRap=view.findViewById(R.id.searchViewRap);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rapdata.clear();
        Log.d("FRAGMENT_THEATER", "onCreate()");
    }

    @Override
    public void onResume() {
        super.onResume();
        capnhatOptionTinhThanh();
        autoCompleteTextView.clearFocus();
        Log.d("FRAGMENT_THEATER", "onResume()");
    }
}
