package com.example.kanika.smartcitytraveller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Chosen_Places extends AppCompatActivity {

    ListView lv;
    String[] resultArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen__places);
        Bundle b = getIntent().getExtras();
        resultArr = b.getStringArray("finalSelectedItems");
        lv = (ListView) findViewById(R.id.outputList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, resultArr);
        lv.setAdapter(adapter);
    }
}
