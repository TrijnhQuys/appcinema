package com.heliwr.appbanvexemphim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.heliwr.appbanvexemphim.Fragment.FRAGMENT_ACCOUNT;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.UserManager;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity_Login extends AppCompatActivity {
    AppCompatButton btnLogin, btnRegister;
    EditText edtUsername, edtPassword;
    public static final String PREFS_NAME = "UserData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btnLogin=findViewById(R.id.btnsignin);
        btnRegister=findViewById(R.id.btnregister);
        edtUsername=findViewById(R.id.edtUsername);
        edtPassword=findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtUsername.getText().length()>0&&edtPassword.getText().length()>0){
                    Login(edtUsername.getText().toString(), edtPassword.getText().toString());
                }else{

                    Toast.makeText(MainActivity_Login.this, "Vui lòng nhập tên tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity_Login.this, MainActivity_Register.class);
                startActivity(intent);
            }
        });
    }
    public void Login(String us, String pa){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JSONObject data = new JSONObject();
        try {
            data.put("name", us);
            data.put("pass", pa);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, SERVER.urlLogin, data, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean log = response.getBoolean("log");
                    boolean userLoggedIn;
                    if (log) {
                        JSONObject infoObject = response.getJSONObject("info");
                        String email = infoObject.getString("EMAIL");
                        String iduser=infoObject.getString("IDUSER");

                        userLoggedIn = true;

                        UserManager.saveUserData(MainActivity_Login.this, us, email, iduser, userLoggedIn);

                        Intent intent = new Intent(MainActivity_Login.this, MainActivity.class);
                        intent.putExtra("userLoggedIn", userLoggedIn);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity_Login.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_Login.this,"lỗi:"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);
    }
}