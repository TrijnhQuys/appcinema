package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_ALLPHIM;
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_HISTORY;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.HISTORY;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.UserManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivityHistory extends AppCompatActivity {
    HISTORY history;
    RecyclerView rv_History;
    RV_ADAPTER_HISTORY rvAdapterHistory;

    ArrayList<HISTORY> histories_data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_history);
        rv_History=findViewById(R.id.rv_History);

        rvAdapterHistory=new RV_ADAPTER_HISTORY(this, histories_data);
        rv_History.setAdapter(rvAdapterHistory);
        LinearLayoutManager layoutManagerHistory = new LinearLayoutManager(MainActivityHistory.this, LinearLayoutManager.VERTICAL, false);
        rv_History.setLayoutManager(layoutManagerHistory);
        loadhistory();
    }
    private void loadhistory() {
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

                    JSONObject historyObject = null;
                    try {
                        historyObject = jsonArray.getJSONObject(i);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    HISTORY history = null;
                    try {
                        history = new HISTORY( historyObject.getInt("iddonhang"),
                                historyObject.getInt("idphim"),
                                historyObject.getInt("iduser"),
                                historyObject.getString("timestartwatching"),
                                historyObject.getString("dateview"),
                                historyObject.getString("namerap"),
                                historyObject.getString("namefilm"),
                                historyObject.getString("name"),
                                historyObject.getString("email"),
                                historyObject.getString("seats"),
                                historyObject.getString("combofood"),
                                historyObject.getString("totalmoney")
                        );
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    histories_data.add(history);
                }
                //báo adapter load dữ liệu lên
                rvAdapterHistory.notifyDataSetChanged();
            }
        };

        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityHistory.this, "loi that bai: "+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        StringRequest stringRequest
                = new StringRequest(Request.Method.POST, SERVER.urlLayHistory,thanhcong, thatbai)
        {
            protected Map<String, String> getParams()throws AuthFailureError {
                String iduser= UserManager.getIduser(MainActivityHistory.this);
                HashMap params = new HashMap();
                params.put("iduser", iduser);
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Thêm đoạn mã dưới đây để khởi tạo rvchudeAdapter nếu nó chưa được khởi tạo
        requestQueue.add(stringRequest);

    }
}