package com.heliwr.appbanvexemphim.Fragment.FragmentChiTietPhim;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.R;


public class TrailerFragment extends Fragment {
    ALLPHIM allphim;
    PHIMSAPCHIEU phimsapchieu;
    PHIMDANGCHIEU phimdangchieu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trailer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebView webViewTrailer=view.findViewById(R.id.webViewTrailer);
        Intent intent=getActivity().getIntent();
        allphim = (ALLPHIM) intent.getSerializableExtra("phim");
        phimsapchieu=(PHIMSAPCHIEU)intent.getSerializableExtra("phimsapchieu");
        phimdangchieu=(PHIMDANGCHIEU)intent.getSerializableExtra("phimdangchieu");

        if (allphim !=null){
            String videotrailerallphim=allphim.videotrailer;
            webViewTrailer.loadData(videotrailerallphim, "text/html", "utf-8");
        }

        if (phimsapchieu !=null){
            String videotrailerphimsapchieu=phimsapchieu.videotrailer;
            webViewTrailer.loadData(videotrailerphimsapchieu, "text/html", "utf-8");
        }

        if (phimdangchieu !=null){
            String videotrailerphimdangchieu=phimdangchieu.videotrailer;
            webViewTrailer.loadData(videotrailerphimdangchieu, "text/html", "utf-8");
        }
        webViewTrailer.getSettings().setJavaScriptEnabled(true);
        webViewTrailer.setWebChromeClient(new WebChromeClient());
    }

}