package com.heliwr.appbanvexemphim.Fragment.FragmentChiTietPhim;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.R;


public class MoTaFragment extends Fragment {
    ALLPHIM allphim;
    PHIMSAPCHIEU phimsapchieu;
    PHIMDANGCHIEU phimdangchieu;
    TextView tvmota;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mo_ta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhxa(view);
        Intent intent=getActivity().getIntent();
        allphim = (ALLPHIM) intent.getSerializableExtra("phim");
        phimdangchieu=(PHIMDANGCHIEU) intent.getSerializableExtra("phimdangchieu");
        phimsapchieu=(PHIMSAPCHIEU) intent.getSerializableExtra("phimsapchieu");
        if (allphim != null) {
            tvmota.setText(allphim.mota);
        }
        if (phimdangchieu != null) {
            tvmota.setText(phimdangchieu.mota);
        }
        if (phimsapchieu != null) {
            tvmota.setText(phimsapchieu.mota);
        }
    }
    public void anhxa(View view){
        tvmota=view.findViewById(R.id.tvmota);
    }
}