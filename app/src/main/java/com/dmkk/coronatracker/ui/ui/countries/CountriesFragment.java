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
    int s = 0;

    private ArrayList<countries> countryList = new ArrayList<>();
    private ArrayList<countries> searchcountryList = new ArrayList<>();
    private ArrayList<String> stringlist = new ArrayList<>();
    private ArrayList<String> emptylist = new ArrayList<>();
    private ArrayList<String> searchstringlist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter, searcharrayAdapter, emptyadapter;
    private NotificationsViewModel notificationsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.countries_fragment, container, false);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        editText = root.findViewById(R.id.editText);
        Button button = root.findViewById(R.id.button);
        list = root.findViewById(R.id.listSc);
        emptylist.clear();
        emptyadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, emptylist);
        searcharrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, searchstringlist);

        onStart();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editText.getText().toString();
                searchstringlist.clear();
                searcharrayAdapter.notifyDataSetChanged();

                searchcountryList.clear();

                searcharrayAdapter.notifyDataSetChanged();


                 if(search.length() >= 3 ){
                     searchstringlist.clear();
                     searcharrayAdapter.notifyDataSetChanged();
                    for(int i = 0; i < countryList.size(); i++){

                   /*     if(search.equalsIgnoreCase(countryList.get(i).country)){
                            countries country = countryList.get(i);
                            // searchcountryList.add(country);

                            String details = "Name: "+country.getCountry()+" \nTotal Cases: "+ country.getTotalConfirmed()+"\nTotal Recovered: "+country.getTotalRecoverd()+"\nTotal Dead: "+country.getTotalDeaths();
                            searchstringlist.add(details);
                            return;

                        }
                        else*/
                            search3(search,countryList,i);
                    }

                }
                else if(search.length() == 2){
                     searchstringlist.clear();
                     searcharrayAdapter.notifyDataSetChanged();
                    for(int i = 0; i < countryList.size(); i++){
            /*            if(search.equalsIgnoreCase(countryList.get(i).countrycode)||search.equalsIgnoreCase(countryList.get(i).country)){

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
                        else{*/
                            search2(search,countryList,i);
                    //    }

                    }
                }else if(search.length() == 1){
                     searchstringlist.clear();
                     searcharrayAdapter.notifyDataSetChanged();
                    for(int i = 0; i < countryList.size(); i++){

                            search1(search,countryList,i);


                    }
                }


                if(!searchstringlist.isEmpty())
                {
                    s = 1;
                    list.setAdapter(emptyadapter);
                    list.setAdapter(searcharrayAdapter);

                }
                else {
                    //searchstringlist.clear();
                    searcharrayAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "Country Not Found, Check your spellings", Toast.LENGTH_SHORT).show();
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               if(s == 0) {
                    countries con = (countries) countryList.get(position);


                    Intent intent = new Intent(getContext(), selectcountry.class);
                    intent.putExtra("Country", countryList.get(position).country);
                    intent.putExtra("CountryCode", countryList.get(position).countrycode);
                    intent.putExtra("NewConfirmed", countryList.get(position).NewConfirmed);
                    intent.putExtra("TotalConfirmed", countryList.get(position).TotalConfirmed);
                    intent.putExtra("NewDeaths", countryList.get(position).NewDeaths);
                    intent.putExtra("TotalDeaths", countryList.get(position).TotalDeaths);
                    intent.putExtra("NewRecovered", countryList.get(position).NewRecovered);
                    intent.putExtra("TotalRecovered", countryList.get(position).TotalRecoverd);
                    intent.putExtra("Date", countryList.get(position).Date);
                    startActivity(intent);

                    Toast.makeText(getContext(), "Name: " + countryList.get(position).country + " Selected", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                }
                else{
                    countries con = (countries) searchcountryList.get(position);


                    Intent intent = new Intent(getContext(), selectcountry.class);
                    intent.putExtra("Country", searchcountryList.get(position).country);
                    intent.putExtra("CountryCode", searchcountryList.get(position).countrycode);
                    intent.putExtra("NewConfirmed", searchcountryList.get(position).NewConfirmed);
                    intent.putExtra("TotalConfirmed", searchcountryList.get(position).TotalConfirmed);
                    intent.putExtra("NewDeaths", searchcountryList.get(position).NewDeaths);
                    intent.putExtra("TotalDeaths", searchcountryList.get(position).TotalDeaths);
                    intent.putExtra("NewRecovered", searchcountryList.get(position).NewRecovered);
                    intent.putExtra("TotalRecovered", searchcountryList.get(position).TotalRecoverd);
                    intent.putExtra("Date", searchcountryList.get(position).Date);
                    startActivity(intent);

                    Toast.makeText(getContext(), "Name: " + searchcountryList.get(position).country + " Selected", Toast.LENGTH_SHORT).show();

                    startActivity(intent);
               }
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
                            arrayAdapter.notifyDataSetChanged();
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

    private void search3(String search, ArrayList<countries> countryList, int i){
        if(search.substring(0,3).equalsIgnoreCase(countryList.get(i).country.substring(0,3))){
            countries country = countryList.get(i);
           searchcountryList.add(country);

            String details = "Name: "+country.getCountry()+" \nTotal Cases: "+ country.getTotalConfirmed()+"\nTotal Recovered: "+country.getTotalRecoverd()+"\nTotal Dead: "+country.getTotalDeaths();
            searchstringlist.add(details);
            return;


        }
    }
    private void search2(String search, ArrayList<countries> countryList, int i){
        if(search.substring(0,2).equalsIgnoreCase(countryList.get(i).country.substring(0,2))){
            countries country = countryList.get(i);
            searchcountryList.add(country);

            String details = "Name: "+country.getCountry()+" \nTotal Cases: "+ country.getTotalConfirmed()+"\nTotal Recovered: "+country.getTotalRecoverd()+"\nTotal Dead: "+country.getTotalDeaths();
            searchstringlist.add(details);
            return;
        }
    }
    private void search1(String search, ArrayList<countries> countryList, int i){
        if(search.substring(0,1).equalsIgnoreCase(countryList.get(i).country.substring(0,1))){
            countries country = countryList.get(i);
            searchcountryList.add(country);

            String details = "Name: "+country.getCountry()+" \nTotal Cases: "+ country.getTotalConfirmed()+"\nTotal Recovered: "+country.getTotalRecoverd()+"\nTotal Dead: "+country.getTotalDeaths();
            searchstringlist.add(details);
            return;

        }
    }

    private void searchFull(String search, ArrayList<countries> countryList, int i){
        if(search.equalsIgnoreCase(countryList.get(i).country)){
            countries country = countryList.get(i);
             searchcountryList.add(country);

            String details = "Name: "+country.getCountry()+" \nTotal Cases: "+ country.getTotalConfirmed()+"\nTotal Recovered: "+country.getTotalRecoverd()+"\nTotal Dead: "+country.getTotalDeaths();
            searchstringlist.add(details);
            return;

    }

}}
