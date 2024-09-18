package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.heliwr.appbanvexemphim.Model.HISTORY;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;

public class MainActivityChiTietHistory extends AppCompatActivity {
    HISTORY history;
    TextView tvtenphimhistory, tvtenraphistory, tvngayxemhistory, tvgioxemhistory, tvcombofoodhistory, tvghehistory, tvtongtienhistory;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_history);
        anhxa();
        Intent intent = getIntent();
        history = (HISTORY) intent.getSerializableExtra("history");
        if (history != null) {
            tvtenphimhistory.setText(history.namefilm);
            tvtenraphistory.setText(history.namerap);
            tvgioxemhistory.setText(history.timestartwatching);
            tvngayxemhistory.setText(history.dateview);
            tvcombofoodhistory.setText(history.combofood);
            tvghehistory.setText(history.seats);
            tvtongtienhistory.setText(history.totalmoney);
        }
    }
    public void anhxa(){
        tvtenphimhistory=findViewById(R.id.tvTenPhimHistory);
        tvtenraphistory=findViewById(R.id.tvtenraphistory);
        tvngayxemhistory=findViewById(R.id.tvngaychieuhistory);
        tvgioxemhistory=findViewById(R.id.tvgioxemhistory);
        tvghehistory=findViewById(R.id.tvghehistory);
        tvcombofoodhistory=findViewById(R.id.tvcombofood);
        tvtongtienhistory=findViewById(R.id.tvtongtienhistory);
    }
}