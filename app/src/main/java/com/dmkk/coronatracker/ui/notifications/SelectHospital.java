package com.dmkk.coronatracker.ui.notifications;

import androidx.appcompat.app.AppCompatActivity;
import com.dmkk.coronatracker.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectHospital extends AppCompatActivity {
    private ArrayList<String> stringlist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hospital);

        final Bundle extras = getIntent().getExtras();
        final String hospital_id = extras.getString("hospital_id");
        final String cumulative_foreign = extras.getString("cumulative_foreign");
        final String cumulative_local = extras.getString("cumulative_local");
        final String cumulative_total = extras.getString("cumulative_total");
        final String treatment_foreign = extras.getString("treatment_foreign");
        final String treatment_local = extras.getString("treatment_local");
        final String treatment_total = extras.getString("treatment_total");
        final String created_at = extras.getString("created_at");
        final String name = extras.getString("name");
        final String name_si = extras.getString("name_si");

        list = findViewById(R.id.listSc);

        stringlist.add("ID: "+hospital_id);
        stringlist.add("නම: "+name_si);
        stringlist.add("ප්‍රතිකාර ලැබූ මුලු රෝගීන් පිලිබද විස්තර");
        stringlist.add("Last Updated:   "+created_at);
        stringlist.add("මුලු රෝගීන් ගණන: "+cumulative_total);
        stringlist.add("මුලු ලාංකික රෝගීන් ගණන: "+cumulative_local);
        stringlist.add("මුලු පිටරට රෝගීන් ගණන:   "+cumulative_foreign);
        stringlist.add("දැනට ප්‍රතිකාර ලබන රෝගීන් පිලිබද විස්තර");
        stringlist.add("ප්‍රතිකාර ලබන මුලු රෝගීන් ගණන: "+treatment_total);
        stringlist.add("ප්‍රතිකාර ලබන ලාංකික රෝගීන් ගණන: "+treatment_local);
        stringlist.add("ප්‍රතිකාර ලබන පිටරට රෝගීන් ගණන:   "+treatment_foreign);



        arrayAdapter = new ArrayAdapter<String>(SelectHospital.this, android.R.layout.simple_list_item_1, stringlist);
        list.setAdapter(arrayAdapter);


    }
}
