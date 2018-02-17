package com.example.kanika.smartcitytraveller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Questionnaire extends Activity {

    public static final String chosen_places = "chosenPlacesKey";
    public static final String mypreference = "mypref";
    SharedPreferences sharedpreferences;
    ListView lv;
    Button b1;
    TextView tv;
    public int i = 0;
    String[] resultArr;

    public ArrayList<String> finalSelectedItems = new ArrayList<String>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        sharedpreferences = getSharedPreferences(mypreference,Context.MODE_PRIVATE);
        Bundle b = getIntent().getExtras();
        resultArr = b.getStringArray("selectedItems");
        lv = (ListView) findViewById(R.id.outputList);
        b1 = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.textView2);
        ArrayAdapter<String> adapter;
        initialize();
        //addToList();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i < resultArr.length) {


                    addToList();
                    initialize();

                } else {
                    addToList();
                    goToNext();
                }

            }
        });


    }

    public void initialize() {

        String[] historical = getResources().getStringArray(R.array.historial_array);
        String[] shopping = getResources().getStringArray(R.array.shopping_array);
        String[] religious = getResources().getStringArray(R.array.Religious_array);
        String[] kids = getResources().getStringArray(R.array.kids_array);
        String[] nature = getResources().getStringArray(R.array.nature_array);
        String[] art = getResources().getStringArray(R.array.art_array);
        String[] adventure = getResources().getStringArray(R.array.adventure_array);
        String[] outdoor = getResources().getStringArray(R.array.outdoor_array);

        if (resultArr.length == 0) {
            b1.setText("Nothing Selected");

        } else {
            if (resultArr.length == 1 || i == resultArr.length - 1) {
                b1.setText("Submit");
            } else {
                b1.setText("Next");
            }


            switch (resultArr[i++]) {
                case "Historical Places": {
                    tv.setText("What kind of historial places would you like to visit?");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_multiple_choice, historical);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);
                    break;


                }
                case "Places to Shop": {
                    tv.setText("Would you rather visit malls to shop or explore local markets");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_multiple_choice, shopping);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);

                    break;
                }
                case "Religious Places": {
                    tv.setText("Which religious place would you like to visit?");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_multiple_choice, religious);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);
                    break;

                }
                case "For Nature and Animal Lovers": {
                    tv.setText("Hey Nature and Animal Lovers out there! Where would you like to go?");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_multiple_choice, nature);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);
                    break;

                }
                case "Kids Friendly": {
                    tv.setText("What would your kids like to explore?");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_multiple_choice, kids);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);
                    break;
                }
                case "Culture and Art Lovers": {
                    tv.setText("Hey Art Lovers! Where would you like to go?");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_multiple_choice, art);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);

                    break;
                }
                case "Amusement and Adventure activities": {
                    tv.setText("Hey you adventure lovers! Whats your next stop?");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_multiple_choice, adventure);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);
                    break;
                }
                default: {
                    tv.setText("For outdoor fun, what would you prefer?");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_list_item_multiple_choice, outdoor);
                    lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    lv.setAdapter(adapter);
                    break;

                }
            }


        }

    }


    public void addToList()
    {
        SparseBooleanArray checked = lv.getCheckedItemPositions();
        for (int i = 0; i < checked.size(); i++) {

            int position = checked.keyAt(i);

            if (checked.valueAt(i))

                finalSelectedItems.add((String)lv.getAdapter().getItem(position));
        }
    }



    public void goToNext()
    {
        String[] outputStrArr = new String[finalSelectedItems.size()];

        for (int i = 0; i < finalSelectedItems.size(); i++) {
            outputStrArr[i] = finalSelectedItems.get(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < outputStrArr.length; i++) {
            sb.append(outputStrArr[i]).append(",");
        }
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(chosen_places, sb.toString());

        editor.commit();


        //b1.setText(outputStrArr[0]);

        Intent intent = new Intent(getApplicationContext(),
                Chosen_Places.class);

        // Create a bundle object
        //Bundle b = new Bundle();
        //b.putStringArray("finalSelectedItems", outputStrArr);

        // Add the bundle to the intent.
        //intent.putExtras(b);

        // start the ResultActivity
        startActivity(intent);

    }


}











