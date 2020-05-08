package com.dmkk.coronatracker.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dmkk.coronatracker.R;
import com.dmkk.coronatracker.ui.home.HomeViewModel;

import org.json.JSONException;
import org.json.JSONObject;

public class DashboardFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private RequestQueue queue;
    TextView total,newP,hospitalized,unsure,dead,recoverd,upTime;
    ImageView refresh;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        upTime = (TextView) root.findViewById(R.id.upTime);
        total = (TextView) root.findViewById(R.id.total);
        newP = root.findViewById(R.id.newP);
        hospitalized = root.findViewById(R.id.hospitalized);
        recoverd = root.findViewById(R.id.recoverd);
        unsure = root.findViewById(R.id.unsure);
        dead = root.findViewById(R.id.dead);

        refresh = root.findViewById(R.id.refresh);

        jsonParse();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });



        return root;
    }
    private void jsonParse() {

        String url = "https://www.hpb.health.gov.lk/api/get-current-statistical";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject api = response.getJSONObject("data");

                            upTime.setText("Last Update: "+api.getString("update_date_time"));
                            total.setText(api.getString("global_total_cases"));
                            newP.setText(api.getString("global_new_cases"));
                            hospitalized.setText(api.getString("global_new_deaths"));
                            recoverd.setText(api.getString("global_recovered"));
                            // unsure.setText(api.getString("global_new_deaths"));
                            dead.setText(api.getString("global_deaths"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);
    }
}
