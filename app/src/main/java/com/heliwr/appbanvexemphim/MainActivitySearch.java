package com.heliwr.appbanvexemphim;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.MaterialToolbar;
import com.heliwr.appbanvexemphim.Adapter.RVSEARCH_ADAPTER;
import com.heliwr.appbanvexemphim.Model.ALLPHIM;
import com.heliwr.appbanvexemphim.Model.PHIMDANGCHIEU;
import com.heliwr.appbanvexemphim.Model.PHIMSAPCHIEU;
import com.heliwr.appbanvexemphim.Model.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivitySearch extends AppCompatActivity {
    RecyclerView rvsearch;
    SearchView searchView;
    ArrayList<ALLPHIM> searchdata = new ArrayList<>();
    RVSEARCH_ADAPTER rvsearch_adapter;
    MaterialToolbar toolbarsanphamSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search);
        toolbarsanphamSearch = findViewById(R.id.toolbarsanphamSearch);
        rvsearch = findViewById(R.id.rvSearch);
        searchView = findViewById(R.id.search_mainsanphamSearch);
        setSupportActionBar(toolbarsanphamSearch);
        toolbarsanphamSearch.setNavigationIcon(R.drawable.baseline_arrow_back_24);
        rvsearch_adapter = new RVSEARCH_ADAPTER(MainActivitySearch.this, searchdata);
        rvsearch.setAdapter(rvsearch_adapter);
        GridLayoutManager allsanphamLayoutM=new GridLayoutManager(MainActivitySearch.this, 2 );
        rvsearch.setLayoutManager(allsanphamLayoutM);
        loadPhimSapChieu();
        toolbarsanphamSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                rvsearch_adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
    public void loadPhimSapChieu() {
        Response.Listener<JSONArray> success = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject sapchieuObject = (JSONObject) response.get(i);

                        ALLPHIM allphim = new ALLPHIM( sapchieuObject.getInt("id"),
                                sapchieuObject.getString("title"),
                                sapchieuObject.getString("price"),
                                sapchieuObject.getString("description"),
                                sapchieuObject.getString("images"),
                                sapchieuObject.getString("category"),
                                sapchieuObject.getString("idcategory"),
                                sapchieuObject.getString("actor"),
                                sapchieuObject.getString("director"),
                                sapchieuObject.getString("producer"),
                                sapchieuObject.getString("nation"),
                                sapchieuObject.getString("showtime"),
                                sapchieuObject.getString("endshowtime"),
                                sapchieuObject.getString("duration"),
                                sapchieuObject.getString("videotrailer")
                        );
                        searchdata.add(allphim);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                rvsearch_adapter.notifyDataSetChanged();
            }
        };
        Response.ErrorListener failure = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivitySearch.this, "Failure: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.urlLayAllPhim, success, failure);
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivitySearch.this);
        requestQueue.add(jsonArrayRequest);
    }
}