package com.heliwr.appbanvexemphim.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.heliwr.appbanvexemphim.Fragment.FragmentDate.PhimDate1Fragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentDate.PhimDate2Fragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentDate.PhimDate3Fragment;
import com.heliwr.appbanvexemphim.Fragment.FragmentDate.PhimDate4Fragment;
import com.heliwr.appbanvexemphim.MainActivity;
import com.heliwr.appbanvexemphim.MainActivity_ChiTietPhim;
import com.heliwr.appbanvexemphim.MainActivity_DatVe;
import com.heliwr.appbanvexemphim.MainActivity_Login;
import com.heliwr.appbanvexemphim.MainActivity_PhimCuaRap;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;
import com.heliwr.appbanvexemphim.Model.UserManager;
import com.heliwr.appbanvexemphim.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RV_ADAPTER_DANGCHIEU extends RecyclerView.Adapter<VIEWHOLDER_DANGCHIEU> {
    Context context;
    ArrayList<PHIMDANGCHIEU> data;
    LinearLayout containerDialofLayoutAccoount;

    public RV_ADAPTER_DANGCHIEU(Context context, ArrayList<PHIMDANGCHIEU> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public VIEWHOLDER_DANGCHIEU onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_1dong_phimdangchieu, null);
        return new VIEWHOLDER_DANGCHIEU(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VIEWHOLDER_DANGCHIEU holder, @SuppressLint("RecyclerView") int position) {
        PHIMDANGCHIEU phimdangchieu = data.get(position);
        holder.texttenphim.setText(phimdangchieu.tenphim);
        holder.texttheloai.setText(phimdangchieu.theloai);
        holder.textthoiluong.setText(phimdangchieu.thoiluong);
        holder.textkhoichieu.setText(phimdangchieu.khoichieu);
        holder.imgphim.setImageResource(R.drawable.ic_launcher_background);
        Picasso.get().load(SERVER.urrImgAllPhim + phimdangchieu.imgphim).into(holder.imgphim);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean UserLoggedIn = UserManager.getuserloggedin(context);
                if (context instanceof MainActivity) {
                    Intent intent = new Intent(context, MainActivity_ChiTietPhim.class);
                    intent.putExtra("phimdangchieu", phimdangchieu);
                    context.startActivity(intent);
                } else {
                    if (UserLoggedIn){
                        if (MainActivity_PhimCuaRap.setTime()==null){
                            Toast.makeText(context, "Vui lòng chọn ngày", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent=new Intent(context, MainActivity_DatVe.class);
                        // Truyền giá trị của biến fromFragmentDate
                        intent.putExtra("fromFragmentDate", true);
                        if (MainActivity_PhimCuaRap.isTabSelected(0)){
                            intent.putExtra("date",PhimDate1Fragment.date1);
                        } else if (MainActivity_PhimCuaRap.isTabSelected(1)) {
                            intent.putExtra("date", PhimDate2Fragment.date2);
                        } else if (MainActivity_PhimCuaRap.isTabSelected(2)) {
                            intent.putExtra("date", PhimDate3Fragment.date3);
                        } else if (MainActivity_PhimCuaRap.isTabSelected(3)) {
                            intent.putExtra("date", PhimDate4Fragment.date4);
                        }
                        intent.putExtra("idphim", String.valueOf(phimdangchieu.maphim));
                        intent.putExtra("tenphim", phimdangchieu.tenphim);
                        intent.putExtra("giave", phimdangchieu.giaphim);
                        context.startActivity(intent);
                    }else {
                        showLoginDialogAccount(v);
                    }

                }
            }
        });
    }
    private void showLoginDialogAccount(View view) {
        containerDialofLayoutAccoount = view.findViewById(R.id.containerDialogLayoutAccount);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        view = LayoutInflater.from(context).inflate(R.layout.dialog_login, containerDialofLayoutAccoount);
        builder.setView(view);
        TextView tvTitle1 = view.findViewById(R.id.tvTitle1);
        TextView tvTitle2 = view.findViewById(R.id.tvTitle2);
        AppCompatButton btnYES = view.findViewById(R.id.btnYES);
        AppCompatButton btnNO = view.findViewById(R.id.btnNO);
        AlertDialog alertDialog = builder.create();
        tvTitle1.setText("BẠN CHƯA ĐĂNG NHẬP");
        tvTitle2.setText("Bạn chưa đăng nhập, đăng nhập để có thể đặt vé xem phim");
        btnYES.setText("YES");
        btnNO.setText("NO");
        if (alertDialog.getWindow() != null)
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btnYES.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity_Login.class);
                context.startActivity(intent);
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
    @Override
    public int getItemCount() {
        return data.size();
    }
}

class VIEWHOLDER_DANGCHIEU extends RecyclerView.ViewHolder {
    ImageView imgphim;
    TextView texttenphim, texttheloai, textthoiluong, textkhoichieu, tvtenRap;

    public VIEWHOLDER_DANGCHIEU(@NonNull View itemView) {
        super(itemView);
        imgphim = itemView.findViewById(R.id.imgphimdangchieu);
        texttenphim = itemView.findViewById(R.id.texttenphimdangchieu);
        texttheloai = itemView.findViewById(R.id.texttheloaidangchieu);
        textthoiluong = itemView.findViewById(R.id.textthoiluongdangchieu);
        textkhoichieu = itemView.findViewById(R.id.textkhoichieudangchieu);
        tvtenRap=itemView.findViewById(R.id.tvTenRap);
    }
}
