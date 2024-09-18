package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivityChonRap extends AppCompatActivity {
    private boolean isRapPhimSelected = false;
    TextView tvTenPhimDatVe;
    String[] item_rapphim ;
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItemRapPhim;
    ALLPHIM allphim;
    PHIMDANGCHIEU phimdangchieu;
    PHIMSAPCHIEU phimsapchieu;
    AppCompatButton btntime1, btntime2, btntime3, btntime4;
    RadioButton btndate1, btndate2, btndate3, btndate4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chon_rap);
        anhxa();

        Intent intent = getIntent();
        phimdangchieu = (PHIMDANGCHIEU) intent.getSerializableExtra("datphimdangchieu");
        allphim = (ALLPHIM) intent.getSerializableExtra("datallphim");
        phimsapchieu = (PHIMSAPCHIEU) intent.getSerializableExtra("datphimsapchieu");
        if (allphim != null) {
            tvTenPhimDatVe.setText(allphim.tenphim);
        }
        if (phimdangchieu != null) {
            tvTenPhimDatVe.setText(phimdangchieu.tenphim);

        }
        if (phimsapchieu != null) {
            tvTenPhimDatVe.setText(phimsapchieu.tenphim);
        }
        loadRapPhimFromServer();
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isRapPhimSelected = true;
            }
        });
        String date1=btndate1.getText().toString();
        String date2=btndate2.getText().toString();
        String date3=btndate3.getText().toString();
        String date4=btndate4.getText().toString();
            btntime1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tenrap = autoCompleteTextView.getText().toString();
                    String time1 = btntime1.getText().toString();

                    if (!isRapPhimSelected) {
                        Toast.makeText(MainActivityChonRap.this, "Vui lòng chọn rạp phim", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!btndate1.isChecked() && !btndate2.isChecked() && !btndate3.isChecked() && !btndate4.isChecked()) {
                        Toast.makeText(MainActivityChonRap.this, "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent=new Intent(MainActivityChonRap.this, MainActivity_DatVe.class);
                    intent.putExtra("time", time1);
                    if (btndate1.isChecked()) {
                        intent.putExtra("date", date1);
                    } else if (btndate2.isChecked()) {
                        intent.putExtra("date", date2);
                    } else if (btndate3.isChecked()) {
                        intent.putExtra("date", date3);
                    } else if (btndate4.isChecked()) {
                        intent.putExtra("date", date4);
                    }
                    intent.putExtra("tenrap", tenrap);
                    intent.putExtra("datallphim", allphim);
                    intent.putExtra("datphimdangchieu", phimdangchieu);
                    intent.putExtra("datphimsapchieu", phimsapchieu);
                    intent.putExtra("chuyenDoi", true);
                    startActivity(intent);
                }

            });


        btntime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenrap = autoCompleteTextView.getText().toString();
                String time2 = btntime2.getText().toString();

                if (!isRapPhimSelected) {
                    Toast.makeText(MainActivityChonRap.this, "Vui lòng chọn rạp phim", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!btndate1.isChecked() && !btndate2.isChecked() && !btndate3.isChecked() && !btndate4.isChecked()) {
                    Toast.makeText(MainActivityChonRap.this, "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(MainActivityChonRap.this, MainActivity_DatVe.class);
                intent.putExtra("time", time2);
                if (btndate1.isChecked()) {
                    intent.putExtra("date", date1);
                } else if (btndate2.isChecked()) {
                    intent.putExtra("date", date2);
                } else if (btndate3.isChecked()) {
                    intent.putExtra("date", date3);
                } else if (btndate4.isChecked()) {
                    intent.putExtra("date", date4);
                }
                intent.putExtra("tenrap", tenrap);
                intent.putExtra("datallphim", allphim);
                intent.putExtra("datphimdangchieu", phimdangchieu);
                intent.putExtra("datphimsapchieu", phimsapchieu);
                intent.putExtra("chuyenDoi", true);
                startActivity(intent);
            }
        });
        btntime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenrap = autoCompleteTextView.getText().toString();
                String time3 = btntime3.getText().toString();

                if (!isRapPhimSelected) {
                    Toast.makeText(MainActivityChonRap.this, "Vui lòng chọn rạp phim", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!btndate1.isChecked() && !btndate2.isChecked() && !btndate3.isChecked() && !btndate4.isChecked()) {
                    Toast.makeText(MainActivityChonRap.this, "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(MainActivityChonRap.this, MainActivity_DatVe.class);
                intent.putExtra("time", time3);
                if (btndate1.isChecked()) {
                    intent.putExtra("date", date1);
                } else if (btndate2.isChecked()) {
                    intent.putExtra("date", date2);
                } else if (btndate3.isChecked()) {
                    intent.putExtra("date", date3);
                } else if (btndate4.isChecked()) {
                    intent.putExtra("date", date4);
                }
                intent.putExtra("tenrap", tenrap);
                intent.putExtra("datallphim", allphim);
                intent.putExtra("datphimdangchieu", phimdangchieu);
                intent.putExtra("datphimsapchieu", phimsapchieu);
                intent.putExtra("chuyenDoi", true);
                startActivity(intent);
            }
        });
        btntime4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenrap = autoCompleteTextView.getText().toString();
                String time4 = btntime4.getText().toString();
                if (!isRapPhimSelected) {
                    Toast.makeText(MainActivityChonRap.this, "Vui lòng chọn rạp phim", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!btndate1.isChecked() && !btndate2.isChecked() && !btndate3.isChecked() && !btndate4.isChecked()) {
                    Toast.makeText(MainActivityChonRap.this, "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(MainActivityChonRap.this, MainActivity_DatVe.class);
                intent.putExtra("time", time4);
                if (btndate1.isChecked()) {
                    intent.putExtra("date", date1);
                } else if (btndate2.isChecked()) {
                    intent.putExtra("date", date2);
                } else if (btndate3.isChecked()) {
                    intent.putExtra("date", date3);
                } else if (btndate4.isChecked()) {
                    intent.putExtra("date", date4);
                }
                intent.putExtra("tenrap", tenrap);
                intent.putExtra("datallphim", allphim);
                intent.putExtra("datphimdangchieu", phimdangchieu);
                intent.putExtra("datphimsapchieu", phimsapchieu);
                intent.putExtra("chuyenDoi", true);
                startActivity(intent);
            }
        });
    }
    public void loadDateFromServer(){

    }
    private void loadRapPhimFromServer() {
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    item_rapphim = new String[response.length()];
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject rapPhimObject = response.getJSONObject(i);
                        String rapPhim = rapPhimObject.getString("namerap");
                        item_rapphim[i] = rapPhim;
                    }
                    // Khởi tạo adapter và đặt nó cho autoCompleteTextView
                    adapterItemRapPhim = new ArrayAdapter<String>(MainActivityChonRap.this, R.layout.list_item_tinh_thanh, item_rapphim);
                    autoCompleteTextView.setAdapter(adapterItemRapPhim);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Response.ErrorListener failure = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivityChonRap.this, "Failure: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, SERVER.urlLayAllRap, null, success, failure);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private boolean isRapPhimSelected() {
        return isRapPhimSelected;
    }
    public void anhxa(){
        autoCompleteTextView = findViewById(R.id.auto_complete_rap);
        tvTenPhimDatVe=findViewById(R.id.tvTenPhimDatVe);
        btntime1=findViewById(R.id.btntime1);
        btntime2=findViewById(R.id.btntime2);
        btntime3=findViewById(R.id.btntime3);
        btntime4=findViewById(R.id.btntime4);

        btndate1=findViewById(R.id.btndate1);
        btndate2=findViewById(R.id.btndate2);
        btndate3=findViewById(R.id.btndate3);
        btndate4=findViewById(R.id.btndate4);
    }
}