package com.example.kanika.smartcitytraveller;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static com.example.kanika.smartcitytraveller.Questionnaire.chosen_places;

public class Chosen_Places extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    ListView lv;
    String[] resultArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen__places);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        String result=sharedpreferences.getString(chosen_places, "");
        resultArr=result.split(",");
        //Bundle b = getIntent().getExtras();
        //resultArr = b.getStringArray("finalSelectedItems");
        lv = (ListView) findViewById(R.id.outputList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_single_choice, resultArr);
        lv.setAdapter(adapter);
    }
}
