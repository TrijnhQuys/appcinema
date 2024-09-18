package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbanvexemphim.Adapter.RV_ADAPTER_CHONTHUCAN;
import com.heliwr.appbanvexemphim.EventBus.TinhTongEvent;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.THUCAN;
import com.heliwr.appbanvexemphim.ZaloPay.Api.CreateOrder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class MainActivity_ChonThucAn extends AppCompatActivity {
    TextView tvTongTien, tvTongghe, tvtongtientatca, tvtongtienthucan, tvTenThucAn, tvsoluongthucan, tvx, tvTenPhim;
    AppCompatButton btndat;
    PHIMDANGCHIEU phimdangchieu;
    PHIMSAPCHIEU phimsapchieu;
    ALLPHIM allphim;
    RecyclerView rvChonthucan;
    ArrayList<THUCAN> ChonthucanData=new ArrayList();
    RV_ADAPTER_CHONTHUCAN rv_adapter_chonthucan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chon_thuc_an);

        btndat = findViewById(R.id.btndat);
        tvTongTien=findViewById(R.id.tvtongtien);
        tvTongghe=findViewById(R.id.tvtongghe);
        tvtongtientatca=findViewById(R.id.tvtongtientatca);
        tvtongtienthucan=findViewById(R.id.tvtongtienthucan);
        rvChonthucan=findViewById(R.id.rvChonthucan);
        tvTenThucAn=findViewById(R.id.tvTenThucAn);
        tvsoluongthucan=findViewById(R.id.tvsoluongcombo);
        tvx=findViewById(R.id.x);
        tvTenPhim=findViewById(R.id.tvTenPhimDatVe);



        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX);


        Intent intent = getIntent();
        phimdangchieu = (PHIMDANGCHIEU) intent.getSerializableExtra("datphimdangchieu");
        allphim = (ALLPHIM) intent.getSerializableExtra("datallphim");
        phimsapchieu = (PHIMSAPCHIEU) intent.getSerializableExtra("datphimsapchieu");


        String tongGhe=intent.getExtras().getString("tongghe","");
        String tenphim = getIntent().getStringExtra("tenphim");
        String tongTien=intent.getExtras().getString("tongtien","");
        tvTenPhim.setText(tenphim);

        if (ChonthucanData.size()==0){
            loadThucAn();
            rv_adapter_chonthucan=new RV_ADAPTER_CHONTHUCAN(this, ChonthucanData);
            rvChonthucan.setAdapter(rv_adapter_chonthucan);
        }
        LinearLayoutManager layoutManagerThuAn=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvChonthucan.setLayoutManager(layoutManagerThuAn);
        rvChonthucan.setHasFixedSize(true);

        tvTongghe.setText(tongGhe);
        tinhTongTien();
        btndat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(tvtongtientatca.getText().toString());
                    String code = data.getString("return_code");
                    if (code.equals("1")) {
                        String token = data.getString("zp_trans_token");
                        ZaloPaySDK.getInstance().payOrder(MainActivity_ChonThucAn.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {
                                Datve();
                            }
                            @Override
                            public void onPaymentCanceled(String s, String s1) {
                                Intent intent1=new Intent(MainActivity_ChonThucAn.this, MainActivityPaymentNotification.class);
                                intent1.putExtra("result","Huỷ thanh toán");
                                startActivity(intent);
                            }
                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                Intent intent1=new Intent(MainActivity_ChonThucAn.this, MainActivityPaymentNotification.class);
                                intent1.putExtra("result","Lỗi thanh toán");
                                startActivity(intent);
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    public void loadThucAn() {
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject dangchieuObject = (JSONObject) response.get(i);

                        THUCAN thucan = new THUCAN(
                                dangchieuObject.getInt("idfood"),
                                dangchieuObject.getString("namefood"),
                                dangchieuObject.getLong("pricefood"),
                                dangchieuObject.getString("description"),
                                dangchieuObject.getString("imgfood")
                        );
                        ChonthucanData.add(thucan);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                //thông báo cho RecyclerView biết rằng dữ liệu đã thay đổi và cần cập nhật giao diện người dùng
                if (rv_adapter_chonthucan != null) {
                    rv_adapter_chonthucan.notifyDataSetChanged();
                }
            }
        };
        Response.ErrorListener failure = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_ChonThucAn.this, "Failure: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urllaythucan, success, failure);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private void Datve() {
        //dữ liệu gửi lên service
        JSONObject jsonBody = new JSONObject();
        //đầu tiên mình sẽ đặt giá trị khi nhập vào đối tượng trước
        try {
            Intent intent = getIntent();
            String namerap = intent.getExtras().getString("tenrap", "");
            String timewatching = intent.getExtras().getString("time", "");
            String date=intent.getExtras().getString("dateview","");
            String iduser = intent.getExtras().getString("iduser", "");
            String user = intent.getExtras().getString("name", "");
            String email = intent.getExtras().getString("email", "");
            String idphim = getIntent().getStringExtra("idphim");
            String tenphim =getIntent().getExtras().getString("tenphim");

            jsonBody.put("namefilm", tenphim);
            jsonBody.put("timestartwatching", timewatching);
            jsonBody.put("dateview", date);
            jsonBody.put("namerap", namerap);
            jsonBody.put("name", user);
            jsonBody.put("email", email);

            jsonBody.put("iduser", iduser);
            jsonBody.put("idphim", idphim);
            jsonBody.put("totalmoney", tvTongTien.getText().toString());
            jsonBody.put("combofood", tvsoluongthucan.getText().toString());
            String layghe = String.valueOf(intent.getStringArrayListExtra("ghedachon"));
            JSONArray seatsArray = new JSONArray(layghe);
            jsonBody.put("seats", seatsArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        sử dụng để gửi yêu cầu HTTP và nhận phản hồi dưới dạng đối tượng JSON từ máy chủ.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, SERVER.urlDatve, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean insert = response.getBoolean("insert");
                            Toast.makeText(MainActivity_ChonThucAn.this, "Thành công", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MainActivity_ChonThucAn.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity_ChonThucAn.this, "Không thành công", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
    public void tinhTongTien() {
        long tongtienthucan = 0;
        int tongsl=0;
        if (ChonthucanData != null && ChonthucanData.size() > 0) {
            for (int i = 0; i < ChonthucanData.size(); i++) {
                tongtienthucan += (ChonthucanData.get(i).getGia() * ChonthucanData.get(i).getSoluong());
                tongsl+=ChonthucanData.get(i).getSoluong();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        Intent intent=getIntent();
        String tongTien=intent.getExtras().getString("tongtien","");
        long tongtatca= Long.parseLong(tongTien)+tongtienthucan;
        String formattedPrice = decimalFormat.format(tongtatca);
        tvTongTien.setText(formattedPrice);
        tvTenThucAn.setText("Combo Thức Ăn");
        tvsoluongthucan.setText(String.valueOf(tongsl));
        tvtongtientatca.setText(String.valueOf(tongtatca));
        if (tongtienthucan <= 0) {
            tvsoluongthucan.setText("");
            tvTenThucAn.setText("");
            tvx.setText("");
        }else {
            tvx.setText("X");
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhtong(TinhTongEvent event) {
        if (event != null) {
            tinhTongTien();
        }
    }
}