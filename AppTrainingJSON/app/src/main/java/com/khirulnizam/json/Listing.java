package com.khirulnizam.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


public class Listing extends AppCompatActivity {
    private String TAG = Listing.class.getSimpleName();
    private ProgressDialog pDialog;
    ListView lv;
    ArrayList<HashMap<String , String>> traininglist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        //define UI & binding
        lv = (ListView)findViewById(R.id.list);

        //arraylist
        traininglist = new ArrayList<>();
        new GetTraining().execute();//invoke AsynTask

    }//end onCreate

    //GetTraining
    private class GetTraining extends AsyncTask<Void, Void, Void> {
        //Log.e(url2);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Listing.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            //set the URL address of the JSON service page here
            String url2 = "http://khirulnizam.com/training/search.php";
            Log.e(TAG,url2);
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url2);
            Log.e(TAG, "Response from url: " + jsonStr);
            //if respon null
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node name
                    JSONArray contacts = jsonObj.getJSONArray("traininglist");
                    // looping through All Contacts
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        String id = c.getString("id");
                        String trainingname = c.getString("trainingname");
                        String website = c.getString("website");
                        // tmp hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();
                        // adding each child node to HashMap key => value
                        contact.put("id", id);
                        contact.put("trainingname", trainingname);
                        contact.put("website", website);
                        // adding contact to contact list
                        traininglist.add(contact);
                    }
                    //if JSON format error
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                // if JSON service server not responding
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    Listing.this, traininglist,
                    R.layout.list_item, //list_item.xml
                    new String[]{"id", "trainingname", "website"},//array list
                    new int[]{R.id.id, R.id.trainingname, R.id.website}//the UIs in list_item.xml

            );
            Log.e("generating list adapter", traininglist.toString());
            lv.setAdapter(adapter);
        }
    }

}//end Listing class