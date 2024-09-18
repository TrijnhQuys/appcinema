package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.UserManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity_DatVe extends AppCompatActivity {


    TextView tvTenPhimDatVe, tvTongTien, tvTongghe,tvTongTien2;

    TextView tvUser, tvEmail, tvDate, tvTenRap, tvIduser, tvIdphim, tvTimewatching, tvGiave;
    PHIMDANGCHIEU phimdangchieu;
    PHIMSAPCHIEU phimsapchieu;
    ALLPHIM allphim;
    private ArrayList<String> GheDaChonServer = new ArrayList<>();
    private ArrayList<String> GheDaChon = new ArrayList<>();

    AppCompatButton btnDatVe;
    GridLayout gridLayout;
    static  int giaVe = 0;
    LinearLayout containerDialofLayoutAccoount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dat_ve);
        anhxa();

        Intent intent = getIntent();
        phimdangchieu = (PHIMDANGCHIEU) intent.getSerializableExtra("datphimdangchieu");
        allphim = (ALLPHIM) intent.getSerializableExtra("datallphim");
        phimsapchieu = (PHIMSAPCHIEU) intent.getSerializableExtra("datphimsapchieu");
        boolean chuyenDoi = getIntent().getBooleanExtra("chuyenDoi", false);

        if (chuyenDoi) {
            if (allphim != null) {
                tvTenPhimDatVe.setText(allphim.tenphim);
                tvIdphim.setText(String.valueOf(allphim.maphim));
            } else if (phimdangchieu != null) {
                tvTenPhimDatVe.setText(phimdangchieu.tenphim);
                tvIdphim.setText(String.valueOf(phimdangchieu.maphim));
            } else if (phimsapchieu != null) {
                tvTenPhimDatVe.setText(phimsapchieu.tenphim);
                tvIdphim.setText(String.valueOf(phimsapchieu.maphim));
            }
            if (phimdangchieu != null) {
                giaVe = Integer.parseInt(phimdangchieu.giaphim);
            }
            if (phimsapchieu != null) {
                giaVe = Integer.parseInt(phimsapchieu.giaphim);
            }
            if (allphim != null) {
                giaVe = Integer.parseInt(allphim.giaphim);
            }
            String namerap = intent.getExtras().getString("tenrap", "");
            tvTenRap.setText(namerap);
            String timewatching = intent.getExtras().getString("time", "");
            tvTimewatching.setText(timewatching);

        } else {
            String tenphim = intent.getExtras().getString("tenphim", "");
            tvTenPhimDatVe.setText(tenphim);
            tvTenRap.setText(MainActivity_PhimCuaRap.setTenRap());
            String idphim = intent.getExtras().getString("idphim", "");
            tvIdphim.setText(idphim);
            tvTimewatching.setText(MainActivity_PhimCuaRap.setTime());
            String giaphim=intent.getExtras().getString("giave","");
            tvGiave.setText(giaphim);
            giaVe = Integer.parseInt(tvGiave.getText().toString());
            Toast.makeText(MainActivity_DatVe.this, "time: "+tvGiave.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        String date = intent.getExtras().getString("date", "");
        tvDate.setText(date);

        String username = UserManager.getUsername(MainActivity_DatVe.this);
        String email = UserManager.getEmail(MainActivity_DatVe.this);
        String iduser = UserManager.getIduser(MainActivity_DatVe.this);
        // Lấy tên người dùng từ intent
        if (username != null && email != null) {
            tvUser.setText(username);
            tvEmail.setText(email);
            tvIduser.setText(iduser);
        }

        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(tvTongghe.getText().toString())==0){
                    Toast.makeText(MainActivity_DatVe.this, "Vui lòng chọn ghế", Toast.LENGTH_SHORT).show();
                    return;
                }
                showLoginDialogAccount();
            }
        });
        loadGheDaChonServer();
    }
    private void showLoginDialogAccount() {
        containerDialofLayoutAccoount = findViewById(R.id.containerDialogLayoutÌnomation);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_DatVe.this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_infomation_datve, containerDialofLayoutAccoount);
        builder.setView(view);
        TextView tvTitle1 = view.findViewById(R.id.tvTitle1);
        TextView tvTitle2 = view.findViewById(R.id.tvTitle2);
        AppCompatButton btnYES = view.findViewById(R.id.btnYES);
        AppCompatButton btnNO = view.findViewById(R.id.btnNO);
        AlertDialog alertDialog = builder.create();
        tvTitle1.setText("Thông tin vé");
        tvTitle2.setText("Tôi xác nhận mua vé cho người xem từ đủ 13 tuổi trở lên và đồng ý cung cấp giấy tờ tùy thân để xác thực độ tuổi người xem, tham khảo quy định của Bộ Văn Hóa, Thể Thao và Du Lịch, CGV không được phép phục vụ khách hàng dưới 16 tuổi cho các suất chiếu kết thúc sau 23:00. CGV sẽ không hoàn tiền nếu người xem không đáp ứng đủ điều kiện.");
        btnYES.setText("Đồng ý");
        btnNO.setText("Huỷ");
        if (alertDialog.getWindow() != null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_DatVe.this, MainActivity_ChonThucAn.class);
                intent.putStringArrayListExtra("ghedachon", GheDaChon);
                intent.putExtra("name", tvUser.getText().toString());
                intent.putExtra("email", tvEmail.getText().toString());
                intent.putExtra("iduser", tvIduser.getText().toString());
                intent.putExtra("dateview", tvDate.getText().toString());
                intent.putExtra("time", tvTimewatching.getText().toString());

                intent.putExtra("tenphim", tvTenPhimDatVe.getText().toString());
                intent.putExtra("tenrap", tvTenRap.getText().toString());
                intent.putExtra("idphim",tvIdphim.getText().toString());

                intent.putExtra("tongtien", tvTongTien2.getText().toString());
                intent.putExtra("tongghe", tvTongghe.getText().toString());
                startActivity(intent);
            }
        });
        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void loadGheDaChonServer() {
        // Tạo một đối tượng JSON để gửi yêu cầu
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("idphim",tvIdphim.getText().toString());
            requestBody.put("timestartwatching", tvTimewatching.getText().toString());
            requestBody.put("namerap", tvTenRap.getText().toString());
            requestBody.put("dateview", tvDate.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Tạo một yêu cầu JSON
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, SERVER.urlLoadGhe, requestBody, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    GheDaChonServer.clear();
                    // Phân tích phản hồi và lấy thông tin các ghế đã đặt
                    JSONArray responseJSONArray = response.getJSONArray("selectedSeats");
                    for (int i = 0; i < responseJSONArray.length(); i++) {
                        String seat = responseJSONArray.getString(i);
                        GheDaChonServer.add(seat);
                    }
                    // Gọi một phương thức để cập nhật giao diện chọn ghế
                    updateSeatSelectionUI();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_DatVe.this, "Lỗi khi lấy thông tin các ghế đã đặt", Toast.LENGTH_SHORT).show();
            }
        });

        // Thêm yêu cầu vào hàng đợi yêu cầu
        Volley.newRequestQueue(this).add(request);
    }

    private void updateSeatSelectionUI() {
        gridLayout = findViewById(R.id.gridLayout);

        // Lặp qua danh sách các ghế trong layout và kiểm tra xem chúng có trong danh sách ghế đã chọn từ server hay không
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            View seatView = gridLayout.getChildAt(i);
            if (seatView instanceof Button) {
                Button seatButton = (Button) seatView;
                String numberSeatSV = seatButton.getText().toString();

                // Kiểm tra xem ghế đã chọn có trong danh sách ghế đã đặt từ server hay không
                if (GheDaChonServer.contains(numberSeatSV)) {
                    seatButton.setBackground(ContextCompat.getDrawable(MainActivity_DatVe.this, R.drawable.bg_seats_selected_server));

                    seatButton.setEnabled(false);
                } else {
                    seatButton.setBackground(ContextCompat.getDrawable(MainActivity_DatVe.this, R.drawable.bg_seats_noselected));
                    seatButton.setEnabled(true);
                }
            }
        }
    }

    public void onSeatSelected(View view) {
        Button selectedSeat = (Button) view;
        String seatNumber = selectedSeat.getText().toString();
        // Kiểm tra xem ghế đã chọn có trong danh sách hay không
        if (GheDaChon.contains(seatNumber)) {
            // Nếu đã chọn rồi, hủy bỏ việc chọn ghế
            GheDaChon.remove(seatNumber);
            // Kiểm tra xem ghế đã bị đặt chưa
            if (GheDaChonServer.contains(seatNumber)) {
                Toast.makeText(MainActivity_DatVe.this, "Ghế này đã được đặt", Toast.LENGTH_SHORT).show();
                return;
            }
            selectedSeat.setBackground(ContextCompat.getDrawable(MainActivity_DatVe.this, R.drawable.bg_seats_noselected));
            // Đặt lại màu nền cho ghế
        } else {
            // Nếu chưa chọn, kiểm tra xem ghế có khả dụng hay không
            if (isSeatAvailable(seatNumber)) {
                GheDaChon.add(seatNumber);
                selectedSeat.setBackground(ContextCompat.getDrawable(MainActivity_DatVe.this, R.drawable.bg_seats_select));
                // Đặt màu nền khác cho ghế đã chọn
            } else {
                Toast.makeText(MainActivity_DatVe.this, "Ghế này đã được đặt", Toast.LENGTH_SHORT).show();
            }
        }
        // Cộng giá vé vào tổng tiền
        int tongTien = GheDaChon.size() * giaVe;
        int tongghe = GheDaChon.size();
        // Cập nhật hiển thị tổng tiền trên TextView
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedPrice = decimalFormat.format(Long.parseLong(String.valueOf(tongTien)));
        tvTongTien.setText(formattedPrice);
        tvTongTien2.setText(String.valueOf(tongTien));
        tvTongghe.setText(String.valueOf(tongghe));
    }

    private boolean isSeatAvailable(String seatNumber) {
        return !GheDaChonServer.contains(seatNumber) && !GheDaChon.contains(seatNumber);
    }

    public void anhxa() {
        tvTenPhimDatVe = findViewById(R.id.tvTenPhimDatVe);
        tvTongTien = findViewById(R.id.tvtongtien);
        btnDatVe = findViewById(R.id.btnDatGhe);
        tvUser = findViewById(R.id.tvUser);
        tvEmail = findViewById(R.id.tvEmail);
        tvDate = findViewById(R.id.tvDate);
        tvTenRap = findViewById(R.id.tvTenRap);
        tvTongghe = findViewById(R.id.tvtongghe);
        tvIduser = findViewById(R.id.tvIduser);
        tvIdphim = findViewById(R.id.tvIdphim);
        tvTimewatching=findViewById(R.id.tvTimewahtching);
        tvGiave=findViewById(R.id.tvGiave);
        tvTongTien2=findViewById(R.id.tvtongtien2);
    }
}