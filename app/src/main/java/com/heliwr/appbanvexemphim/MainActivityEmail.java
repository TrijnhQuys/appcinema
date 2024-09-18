package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityEmail extends AppCompatActivity {
    EditText edtTitle, edtContent;
    AppCompatButton btnSendEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_email);
        edtTitle=findViewById(R.id.edtTitle);
        edtContent=findViewById(R.id.edtContent);
        btnSendEmail=findViewById(R.id.btnSendEmail);

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title, content, to_email="trinhquy@gmail.com";
                title=edtTitle.getText().toString();
                content=edtContent.getText().toString();
                if (title.equals("")&&content.equals("")&&to_email.equals("")){
                    Toast.makeText(MainActivityEmail.this, "Vui lòng điền thoong tin", Toast.LENGTH_SHORT).show();
                }else {
                    sendEmail(title, content, to_email);
                }
            }
        });
    }
    public void sendEmail(String title, String content, String to_email){
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Chọn ứng dụng Email: "));
    }
}