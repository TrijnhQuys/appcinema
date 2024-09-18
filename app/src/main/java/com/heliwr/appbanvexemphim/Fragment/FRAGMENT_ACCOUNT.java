package com.heliwr.appbanvexemphim.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.heliwr.appbanvexemphim.MainActivity;
import com.heliwr.appbanvexemphim.MainActivityEmail;
import com.heliwr.appbanvexemphim.MainActivityHistory;
import com.heliwr.appbanvexemphim.MainActivity_ChiTietPhim;
import com.heliwr.appbanvexemphim.MainActivity_DatVe;
import com.heliwr.appbanvexemphim.MainActivity_Login;
import com.heliwr.appbanvexemphim.Model.UserManager;
import com.heliwr.appbanvexemphim.R;

public class FRAGMENT_ACCOUNT extends Fragment {
    Toolbar toolbar;
    TextView tvUser, tvEmail;
    AppCompatButton btnCall, btnGuiEmail, btnLogin, btndangxuat, btnlichsudatve;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_layout_account, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity_Login.class);
                getContext().startActivity(intent);
            }
        });
        boolean UserLoggedIn = UserManager.getuserloggedin(getContext());

        if (!UserLoggedIn){
            btndangxuat.setVisibility(View.GONE);
            btnlichsudatve.setVisibility(View.GONE);
        }else {
            String username = UserManager.getUsername(getContext());
            String email = UserManager.getEmail(getContext());
            tvUser.setText(username);
            tvEmail.setText(email);
            tvEmail.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
            btndangxuat.setVisibility(View.VISIBLE);
        }
        btnlichsudatve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(getContext(), MainActivityHistory.class);
            startActivity(intent);
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0704480927"));

                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(Intent.createChooser(intent, "Chọn ứng dụng gọi:  "));
                } else {
                    Toast.makeText(getActivity(), "Không có ứng dụng có sẵn để thực hiện cuộc gọi điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnGuiEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivityEmail.class);
                getContext().startActivity(intent);
            }
        });
        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager.clearUserData(getContext());
                Intent intent = new Intent(getContext(), MainActivity.class);
//                yêu cầu Intent khởi chạy một Activity mới và xóa tất cả các Activity hiện có
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void anhxa(View view) {
        toolbar = view.findViewById(R.id.toolbarrap);
        tvUser = view.findViewById(R.id.tvUsername);
        tvEmail = view.findViewById(R.id.tvEmail);
        btnCall=view.findViewById(R.id.btnCall);
        btnGuiEmail=view.findViewById(R.id.btnGuiEmail);
        btnLogin=view.findViewById(R.id.btnLogin);
        btndangxuat=view.findViewById(R.id.btndangxuat);
        btnlichsudatve=view.findViewById(R.id.btnlichsudatve);
    }
}
