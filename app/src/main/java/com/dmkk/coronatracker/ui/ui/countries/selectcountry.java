package com.dmkk.coronatracker.ui.ui.countries;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.dmkk.coronatracker.R;
import com.dmkk.coronatracker.ui.notifications.SelectHospital;

import java.util.ArrayList;

public class selectcountry extends AppCompatActivity {
    private ArrayList<String> stringlist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView list;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectcountry);

        final Bundle extras = getIntent().getExtras();
        final String Country = extras.getString("Country");
        final String CountryCode = extras.getString("CountryCode");
        final String NewConfirmed = extras.getString("NewConfirmed");
        final String TotalConfirmed = extras.getString("TotalConfirmed");
        final String NewDeaths = extras.getString("NewDeaths");
        final String TotalDeaths = extras.getString("TotalDeaths");
        final String NewRecovered = extras.getString("NewRecovered");
        final String TotalRecovered = extras.getString("TotalRecovered");
        final String Date = extras.getString("Date");

        list = findViewById(R.id.listSc);
        title = findViewById(R.id.title);
        //Button button2 = findViewById(R.id.button2);

        title.setText(Country+" Details");
        stringlist.add("Country: "+Country);
        stringlist.add("Country Code: "+CountryCode);
        stringlist.add("Last Updated:  "+Date.substring(0,10)+" "+ Date.substring(11,19));
        stringlist.add("Total Confirmed: "+TotalConfirmed);
        stringlist.add("New Confirmed: "+NewConfirmed);
        stringlist.add("New Recovered:   "+NewRecovered);
        stringlist.add("Total Recovered:   "+TotalRecovered);
        stringlist.add("New Deaths: "+NewDeaths);
        stringlist.add("Total Deaths: "+TotalDeaths);

    /*    button2.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Fragment mFragment = null;
                mFragment = new CountriesFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().
                        .replace(R.id.frameLayout, mFragment).commit();
          }
        });*/


        arrayAdapter = new ArrayAdapter<String>(selectcountry.this, android.R.layout.simple_list_item_1, stringlist);
        list.setAdapter(arrayAdapter);
    }
}
