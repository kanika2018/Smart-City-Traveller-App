package com.example.kanika.smartcitytraveller;
import android.widget.ListView;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Shopping_Places extends Activity implements View.OnClickListener {

    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping__places);

        findViewsById();

        String[] shoppingArray = getResources().getStringArray(R.array.shopping_array);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, shoppingArray);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);


        button.setOnClickListener(this);
    }

    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.testbutton);
    }

    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(),
                Plan_Your_Day.class);
        startActivity(intent);


    }
}
