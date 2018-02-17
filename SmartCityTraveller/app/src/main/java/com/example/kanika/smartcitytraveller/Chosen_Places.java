package com.example.kanika.smartcitytraveller;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.kanika.smartcitytraveller.Location.latitude;
import static com.example.kanika.smartcitytraveller.Location.location1;
import static com.example.kanika.smartcitytraveller.Location.location2;
import static com.example.kanika.smartcitytraveller.Location.location3;
import static com.example.kanika.smartcitytraveller.Location.location4;


import static com.example.kanika.smartcitytraveller.Location.longitude;
import static com.example.kanika.smartcitytraveller.Questionnaire.chosen_places;

public class Chosen_Places extends ListActivity {
    String CLIENT_ID = "";
    String CLIENT_SECRET = "";
    String CAT_ID = "";
    ArrayList<FoursquareVenue> venuesList;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public String lat="28.70";
    public String lng="77.10";

    public static ArrayList<FoursquareVenue> temp = new ArrayList<FoursquareVenue>();
    String[] resultArr;  //stores chosen places of the questionnaire
    ArrayAdapter myAdapter;

    public HashMap<String, String> hm = new HashMap<>(); //hashmap ontaining all the ategories and their ids

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen__places);
        temp.clear();
        hm.put("History museum", "4bf58dd8d48988d190941735");
        hm.put("Monument","4bf58dd8d48988d12d941735");
        hm.put("Shopping Mall", "4bf58dd8d48988d1fd941735");
        hm.put("Flea Market", "4bf58dd8d48988d1f7941735");
        hm.put("Temples(Jain/Hindu/Buddhist)", "4bf58dd8d48988d13a941735");
        hm.put("Church", "4bf58dd8d48988d132941735");
        hm.put("Mosque", "4bf58dd8d48988d138941735");
        hm.put("Synagogue", "4bf58dd8d48988d139941735");
        hm.put("National Park", "52e81612bcbc57f1066b7a21");
        hm.put("Zoos", "52e81612bcbc57f1066b7a21");
        hm.put("Aquariums", "4fceea171983d5d06c3e9823");
        hm.put("Theme Parks", "5109983191d435c0d71c2bb1");
        hm.put("Museums", "4bf58dd8d48988d181941735");
        hm.put("Cinema", "4bf58dd8d48988d17f941735");
        hm.put("Water parks", "4bf58dd8d48988d193941735");
        hm.put("Art Galleries", "4bf58dd8d48988d181941735");
        hm.put("Fairs", "5267e4d8e4b0ec79466e48c5");
        hm.put("Art Museums", "4bf58dd8d48988d18f941735");
        hm.put("Indie Movie Theatre", "4bf58dd8d48988d17e941735");
        hm.put("Gaming centres", "4bf58dd8d48988d18d941735");
        hm.put("Casinos", "4bf58dd8d48988d17c941735");
        hm.put("Trails and hikes", "56aa371be4b08b9a8d57355e");
        hm.put("Camping grounds", "4bf58dd8d48988d1e4941735");
        hm.put("Stadiums", "4bf58dd8d48988d184941735");

        //ArrayList<FoursquareVenue> temp = new ArrayList<FoursquareVenue>();
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        String result = sharedpreferences.getString(chosen_places, "");

        resultArr = result.split(",");

        new Chosen_Places.fourquare().execute();

    }

    private class fourquare extends AsyncTask<View, Void, String> {

        String temp;

        @Override
        protected String doInBackground(View... urls) {
            // make Call to the url

            for(int i=0;i<resultArr.length-1;i++)
            {
                if (hm.containsKey(resultArr[i])) {
                    CAT_ID += hm.get(resultArr[i]);
                    CAT_ID+=",";
                }


            }
            if (hm.containsKey(resultArr[resultArr.length-1]))
            {
                CAT_ID+=hm.get(resultArr[resultArr.length-1]);
            }

           lat = sharedpreferences.getString(location1, "");
            lng = sharedpreferences.getString(location2, "");

                temp = makeCall("https://api.foursquare.com/v2/venues/search?&radius=10000&limit=50&categoryId=" + CAT_ID + "&client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&v=20130815&ll=" + lat + "," +lng);
                return "";

        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(String result) {
            if (temp == null) {
                // we have an error to the call
        // we can also stop the progress bar
        }

        // on execution
        else {

        // parseFoursquare venues search result
        venuesList = (ArrayList<FoursquareVenue>) parseFoursquare(temp);

        List<String> listTitle = new ArrayList<String>();

        for (int i = 0; i < venuesList.size(); i++) {
            // make a list of the venus that are loaded in the list.
            // show the name, the category and the city
            listTitle.add(i, venuesList.get(i).getName() + ", " + venuesList.get(i).getCategory() + "" + venuesList.get(i).getCity());
        }

        // set the results to the list
        // and show them in the xml
        myAdapter = new ArrayAdapter<String>(Chosen_Places.this, R.layout.row_layout, R.id.listText, listTitle);
        setListAdapter(myAdapter);
    }
        }
    }

    public static String makeCall(String url) {

        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // trim the whitespaces
        return replyString.trim();
    }

    private static ArrayList<FoursquareVenue> parseFoursquare(final String response) {

        //i have declared this temp arraylist as public so that temp.add() keeps on adding the result for all categories
        //ArrayList<FoursquareVenue> temp = new ArrayList<FoursquareVenue>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("response")) {
                if (jsonObject.getJSONObject("response").has("venues")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("venues");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        FoursquareVenue poi = new FoursquareVenue();
                        if (jsonArray.getJSONObject(i).has("name")) {
                            poi.setName(jsonArray.getJSONObject(i).getString("name"));

                            if (jsonArray.getJSONObject(i).has("location")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("location").has("address")) {
                                    if (jsonArray.getJSONObject(i).getJSONObject("location").has("city")) {
                                        poi.setCity(jsonArray.getJSONObject(i).getJSONObject("location").getString("city"));
                                    }
                                    if (jsonArray.getJSONObject(i).has("categories")) {
                                        if (jsonArray.getJSONObject(i).getJSONArray("categories").length() > 0) {
                                            if (jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).has("icon")) {
                                                poi.setCategory(jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("name"));
                                            }
                                        }
                                    }
                                    temp.add(poi);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<FoursquareVenue>();
        }
        return temp;

    }
}


