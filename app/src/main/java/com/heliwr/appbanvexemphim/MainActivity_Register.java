package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.heliwr.appbanvexemphim.Model.SERVER;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity_Register extends AppCompatActivity {
    AppCompatButton btnregisterok;
    TextInputEditText edtemail, edtpassword, edtusername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        btnregisterok = findViewById(R.id.btnregisterok);
        edtemail = findViewById(R.id.edtEMRegister);
        edtpassword=findViewById(R.id.edtPWRegister);
        edtusername=findViewById(R.id.edtUSRegister);

        btnregisterok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {registerWithVolley();
            }
        });
    }
    private void registerWithVolley() {
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", edtemail.getText().toString());
            jsonBody.put("name", edtusername.getText().toString());
            jsonBody.put("pass", edtpassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, SERVER.urlRegister, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean insert = response.getBoolean("insert");
                            Toast.makeText(MainActivity_Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            String name = edtusername.getText().toString();
                            String password = edtpassword.getText().toString();
                            String email=edtemail.getText().toString();

                            Intent intent = new Intent(MainActivity_Register.this, MainActivity_Login.class);
                            intent.putExtra("USERNAME", name);
                            intent.putExtra("PASSWORD", password);
                            intent.putExtra("email",email);
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
                        Toast.makeText(MainActivity_Register.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}