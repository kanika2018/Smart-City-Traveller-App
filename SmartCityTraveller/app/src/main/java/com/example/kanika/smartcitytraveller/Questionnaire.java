package com.example.kanika.smartcitytraveller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
public class Questionnaire extends Activity {
    ListView lv;
    Button b1;
    public int i=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        Bundle b = getIntent().getExtras();
        String[] resultArr = b.getStringArray("selectedItems");
        lv = (ListView) findViewById(R.id.outputList);
        b1 = (Button) findViewById(R.id.button);



//        while(i<resultArr.length)
//        {
//            //resultArr = b.getStringArray("selectedItems");
//
//            String[] historical = getResources().getStringArray(R.array.historial_array);
//            String[] shopping = getResources().getStringArray(R.array.shopping_array);
//            String[] religious = getResources().getStringArray(R.array.Religious_array);
//            String[] kids = getResources().getStringArray(R.array.kids_array);
//            String[] nature = getResources().getStringArray(R.array.nature_array);
//            String[] art = getResources().getStringArray(R.array.art_array);
//            String[] adventure = getResources().getStringArray(R.array.adventure_array);
//            String[] outdoor = getResources().getStringArray(R.array.outdoor_array);
//
//            if(i==resultArr.length-1)
//            {
//                b1.setText("Submit");
//            }
//
//            if (resultArr[i].equals("Historical Places")) {
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questionnaire.this,
//                        android.R.layout.simple_list_item_1, historical);
//                lv.setAdapter(adapter);
//            } else if (resultArr[0].equals("Places to Shop")) {
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questionnaire.this,
//                        android.R.layout.simple_list_item_1, shopping);
//                lv.setAdapter(adapter);
//            } else if (resultArr[0].equals("Religious Places")) {
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questionnaire.this,
//                        android.R.layout.simple_list_item_1, religious);
//                lv.setAdapter(adapter);
//            } else if (resultArr[0].equals("For Nature and Animal Lovers")) {
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questionnaire.this,
//                        android.R.layout.simple_list_item_1, nature);
//                lv.setAdapter(adapter);
//            }
//            else if (resultArr[0].equals("Kids Friendly")) {
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questionnaire.this,
//                        android.R.layout.simple_list_item_1, kids);
//                lv.setAdapter(adapter);
//            }
//            else if (resultArr[0].equals("Culture and Art Lovers")) {
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questionnaire.this,
//                        android.R.layout.simple_list_item_1, art);
//                lv.setAdapter(adapter);
//            }
//            else if (resultArr[0].equals("Amusement and Adventure activities")) {
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questionnaire.this,
//                        android.R.layout.simple_list_item_1, adventure);
//                lv.setAdapter(adapter);
//            } else {
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(Questionnaire.this,
//                        android.R.layout.simple_list_item_1, outdoor);
//                lv.setAdapter(adapter);
//            }
//
//            b1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    //Bundle b = getIntent().getExtras();
//
//
//                    i++;
//                }
//            });
//
//        }

        initialize(resultArr);


    }

    public void initialize(String[] resultArr) {

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
            if (resultArr.length == 1) {
                b1.setText("Submit");
            }
            if (resultArr.length > 1) {
                b1.setText("Next");
            }

            if (resultArr[0].equals("Historical Places")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, historical);
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
            } else if (resultArr[0].equals("Places to Shop")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, shopping);
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
            } else if (resultArr[0].equals("Religious Places")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, religious);
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
            } else if (resultArr[0].equals("For Nature and Animal Lovers")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, nature);
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
            }
            else if (resultArr[0].equals("Kids Friendly")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, kids);
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
            }
            else if (resultArr[0].equals("Culture and Art Lovers")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, art);
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
            }
            else if (resultArr[0].equals("Amusement and Adventure activities")) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, adventure);
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
            } else {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, outdoor);
                lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                lv.setAdapter(adapter);
            }


        }
    }


}











