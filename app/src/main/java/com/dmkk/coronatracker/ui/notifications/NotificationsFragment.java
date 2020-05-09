package com.dmkk.coronatracker.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class NotificationsFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private RequestQueue queue;
    TextView textView2,newP,hospitalized,unsure,dead,recoverd,upTime;
    ListView list;
    ImageView refresh;

    private ArrayList<Hospital> hospitalList = new ArrayList<>();
    private ArrayList<String> stringlist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        list = root.findViewById(R.id.listV);

        onStart();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Hospital hosN = (Hospital) hospitalList.get(position);
                Intent intent = new Intent(getContext(), SelectHospital.class);

                intent.putExtra("hospital_id",hosN.id);
                intent.putExtra("cumulative_foreign",hosN.cumulative_foreign);
                intent.putExtra("cumulative_local",hosN.cumulative_local);
                intent.putExtra("cumulative_total",hosN.cumulative_total);
                intent.putExtra("treatment_foreign",hosN.treatment_foreign);
                intent.putExtra("treatment_local", hosN.treatment_local);
                intent.putExtra("treatment_total",hosN.treatment_total);
                intent.putExtra("created_at",hosN.update);
                intent.putExtra("name",hosN.name);
                intent.putExtra("name_si",hosN.namesi);

                Toast.makeText(getContext(),"Name: "+hospitalList.get(position).namesi+" Selected",Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });
        return root;
    }

    public void onStart() {

        super.onStart();
        jsonParse();

    }

    private void jsonParse() {

        String url = "https://www.hpb.health.gov.lk/api/get-current-statistical";
        stringlist.clear();
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, stringlist);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject jsonobject = response.getJSONObject("data");
                          JSONArray jsonArray = jsonobject.getJSONArray ("hospital_data");


                              for (int i = 0; i < jsonArray.length(); i++) {
                             JSONObject main = jsonArray.getJSONObject(i);
                                  Hospital hos = new Hospital();
                             hos.setId(main.getString("hospital_id"));
                             hos.setCumulative_foreign(main.getString("cumulative_foreign"));
                             hos.setCumulative_local(main.getString("cumulative_local"));
                             hos.setCumulative_total(main.getString("cumulative_total"));
                             hos.setTreatment_foreign(main.getString("treatment_foreign"));
                             hos.setTreatment_local(main.getString("treatment_local"));
                             hos.setTreatment_total(main.getString("treatment_total"));
                             hos.setUpdate(main.getString("created_at"));

                            JSONObject hosObject = main.getJSONObject("hospital");
                            hos.setName(hosObject.getString("name"));
                            hos.setNamesi(hosObject.getString("name_si"));

                            hospitalList.add(hos);
                            String details = "id: "+hos.getId()+" \nName: "+ hos.getNamesi();
                            stringlist.add(details);

                             }

                            list.setAdapter(arrayAdapter);
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
