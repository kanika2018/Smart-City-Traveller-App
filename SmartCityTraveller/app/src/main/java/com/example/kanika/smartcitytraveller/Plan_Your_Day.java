package com.example.kanika.smartcitytraveller;

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

public class Plan_Your_Day extends Activity implements OnClickListener {

    Button button;
    ListView listView;
    ArrayAdapter<String> adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan__your__day);

        findViewsById();

        String[] preference = getResources().getStringArray(R.array.preference_array);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, preference);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);



        button.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                if (pos == 0) {
                    Intent intent = new Intent(getApplicationContext(), Historical_Plaes.class);
                    startActivity(intent);

                }
                else if (pos == 1) {
                    Intent intent = new Intent(getApplicationContext(), Shopping_Places.class);
                    startActivity(intent);

                }
                else if (pos==2)
                {
                    Intent intent = new Intent(getApplicationContext(), For_Nature_and_Animal_Lovers.class);
                    startActivity(intent);
                }
                else if(pos==3)
                {
                    Intent intent = new Intent(getApplicationContext(), Kids.class);
                    startActivity(intent);
                }

                else if(pos==4)
                {
                    Intent intent = new Intent(getApplicationContext(), Culture.class);
                    startActivity(intent);
                }

                else if(pos==5)
                {
                    Intent intent = new Intent(getApplicationContext(), Adventure.class);
                    startActivity(intent);
                }
                else if(pos==6)
                {
                    Intent intent = new Intent(getApplicationContext(), Outdoors.class);
                    startActivity(intent);
                }


            }
            });



    }


    private void findViewsById() {
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.testbutton);
    }


    public void onClick(View v) {
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        ArrayList<String> selectedItems = new ArrayList<String>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String[] outputStrArr = new String[selectedItems.size()];

        for (int i = 0; i < selectedItems.size(); i++) {
            outputStrArr[i] = selectedItems.get(i);
        }

        Intent intent = new Intent(getApplicationContext(),
                Questionnaire.class);

        // Create a bundle object
        Bundle b = new Bundle();
        b.putStringArray("selectedItems", outputStrArr);

        // Add the bundle to the intent.
        intent.putExtras(b);

        // start the ResultActivity
        startActivity(intent);
    }
}
