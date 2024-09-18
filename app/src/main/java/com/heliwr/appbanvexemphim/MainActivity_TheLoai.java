package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_ALLPHIM;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.THELOAIPHIM;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_TheLoai extends AppCompatActivity {
    MaterialToolbar materialToolbar;
    RecyclerView rv_TheLoai;

    THELOAIPHIM theloaiphim;
    RV_ADAPTER_ALLPHIM rvAdapterAllphim;

    ArrayList<ALLPHIM> theloai_data =new ArrayList<>();
    TextView tvTenTheLoai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_the_loai);
        materialToolbar = findViewById(R.id.toolbartheloai);
        rv_TheLoai=findViewById(R.id.rvtheloaiphim);
        tvTenTheLoai=findViewById(R.id.tvTenTheLoai);

        setSupportActionBar(materialToolbar);
        Intent intent =getIntent();
        theloaiphim=(THELOAIPHIM)intent.getSerializableExtra("theloaiObject");
        tvTenTheLoai.setText(theloaiphim.tentheloai);
        rvAdapterAllphim=new RV_ADAPTER_ALLPHIM(this, theloai_data);
        rv_TheLoai.setAdapter(rvAdapterAllphim);
        rv_TheLoai.setLayoutManager(new GridLayoutManager(this, 2));
        materialToolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loadtheloai();
    }
    private void loadtheloai() {
        Response.Listener<String>thanhcong = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;

                try {
                    jsonArray = new JSONArray(response);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                for (int i =0;i<jsonArray.length();i++){

                    JSONObject theloaiObject = null;
                    try {
                        theloaiObject = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    ALLPHIM allphim = null;
                    try {
                        allphim = new ALLPHIM( theloaiObject.getInt("id"),
                                theloaiObject.getString("title"),
                                theloaiObject.getString("price"),
                                theloaiObject.getString("description"),
                                theloaiObject.getString("images"),
                                theloaiObject.getString("category"),
                                theloaiObject.getString("idcategory"),
                                theloaiObject.getString("actor"),
                                theloaiObject.getString("director"),
                                theloaiObject.getString("producer"),
                                theloaiObject.getString("nation"),
                                theloaiObject.getString("showtime"),
                                theloaiObject.getString("endshowtime"),
                                theloaiObject.getString("duration"),
                                theloaiObject.getString("videotrailer")
                                );
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    theloai_data.add(allphim);
                }
                rvAdapterAllphim.notifyDataSetChanged();
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_TheLoai.this, "loi that bai: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest
                = new StringRequest(Request.Method.POST, SERVER.urlLayAllPhim,thanhcong, thatbai)
        {
            protected Map<String, String> getParams()throws AuthFailureError {
                HashMap params = new HashMap();
                params.put("matheloai", theloaiphim.matheloai);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}