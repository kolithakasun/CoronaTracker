package com.dmkk.coronatracker.ui.ui.countries;

import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dmkk.coronatracker.R;
import com.dmkk.coronatracker.ui.home.HomeViewModel;
import com.dmkk.coronatracker.ui.notifications.Hospital;
import com.dmkk.coronatracker.ui.notifications.NotificationsViewModel;
import com.dmkk.coronatracker.ui.notifications.SelectHospital;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CountriesFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RequestQueue queue;
    TextView editText;
    ListView list;
    ImageView refresh;

    private ArrayList<countries> countryList = new ArrayList<>();
    private ArrayList<String> stringlist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private NotificationsViewModel notificationsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.countries_fragment, container, false);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        editText = root.findViewById(R.id.editText);
        Button button = root.findViewById(R.id.button);
        list = root.findViewById(R.id.list);

        onStart();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                countries con = (countries) countryList.get(position);


                Intent intent = new Intent(getContext(),selectcountry.class);
                intent.putExtra("Country",countryList.get(position).country);
                intent.putExtra("CountryCode",countryList.get(position).countrycode);
                intent.putExtra("NewConfirmed",countryList.get(position).NewConfirmed);
                intent.putExtra("TotalConfirmed",countryList.get(position).TotalConfirmed);
                intent.putExtra("NewDeaths",countryList.get(position).NewDeaths);
                intent.putExtra("TotalDeaths", countryList.get(position).TotalDeaths);
                intent.putExtra("NewRecovered",countryList.get(position).NewRecovered);
                intent.putExtra("TotalRecovered", countryList.get(position).TotalRecoverd);
                intent.putExtra("Date", countryList.get(position).Date);
                startActivity(intent);

                Toast.makeText(getContext(),"Name: "+countryList.get(position).country+" Selected",Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editText.getText().toString();

                for(int i = 0; i < countryList.size(); i++){
                    if(search.equalsIgnoreCase(countryList.get(i).countrycode)||search.equalsIgnoreCase(countryList.get(i).country)){

                        Intent intent = new Intent(getContext(),selectcountry.class);
                        intent.putExtra("Country",countryList.get(i).country);
                        intent.putExtra("CountryCode",countryList.get(i).countrycode);
                        intent.putExtra("NewConfirmed",countryList.get(i).NewConfirmed);
                        intent.putExtra("TotalConfirmed",countryList.get(i).TotalConfirmed);
                        intent.putExtra("NewDeaths",countryList.get(i).NewDeaths);
                        intent.putExtra("TotalDeaths", countryList.get(i).TotalDeaths);
                        intent.putExtra("NewRecovered",countryList.get(i).NewRecovered);
                        intent.putExtra("TotalRecovered", countryList.get(i).TotalRecoverd);
                        intent.putExtra("Date", countryList.get(i).Date);
                        startActivity(intent);

                    }
                }
                Toast.makeText(getContext(),"Country Not Found, Check your spellings",Toast.LENGTH_SHORT).show();
            }
        });



        return root;
    }
    public void onStart() {

        super.onStart();
        jsonParse();

    }

    private void jsonParse() {

        String url = "https://api.covid19api.com/summary";
        stringlist.clear();
        arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, stringlist);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                           // JSONObject jsonobject = response.getJSONObject("data");
                            JSONArray jsonArray = response.getJSONArray ("Countries");


                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject main = jsonArray.getJSONObject(i);
                                countries country = new countries();
                                country.setCountry(main.getString("Country"));
                                country.setCountrycode(main.getString("CountryCode"));
                                country.setNewConfirmed(main.getString("NewConfirmed"));
                                country.setTotalConfirmed(main.getString("TotalConfirmed"));
                                country.setNewDeaths(main.getString("NewDeaths"));
                                country.setTotalDeaths(main.getString("TotalDeaths"));
                                country.setNewRecovered(main.getString("NewRecovered"));
                                country.setTotalRecoverd(main.getString("TotalRecovered"));
                                country.setDate(main.getString("Date"));


                                countryList.add(country);
                                    String details = "Name: "+country.getCountry()+" \nTotal Cases: "+ country.getTotalConfirmed()+"\nTotal Recovered: "+country.getTotalRecoverd()+"\nTotal Dead: "+country.getTotalDeaths();
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
