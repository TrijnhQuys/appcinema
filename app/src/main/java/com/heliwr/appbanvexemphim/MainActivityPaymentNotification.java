package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityPaymentNotification extends AppCompatActivity {
    TextView tvnotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_payment_notification);
        tvnotification=findViewById(R.id.tvnotification);
        Intent intent=getIntent();
        tvnotification.setText(intent.getStringExtra("result"));
    }
}