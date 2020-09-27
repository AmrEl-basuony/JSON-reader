package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String JSON_STRING = "{\"size\":1,\"parse_time_nanoseconds\":18092,\"object_or_array\":\"object\",\"validate\":true,\"empty\":false}";
    int size,parse_time_nanoseconds;
    String object_or_array;
    boolean validate,empty;
    TextView tv1,tv2,tv3,tv4,tv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);



        try {

            // get JSONObject from JSON file

            JSONObject obj = new JSONObject(JSON_STRING);

            // get JSON Elements

            size = obj.getInt("size");
            parse_time_nanoseconds = obj.getInt("parse_time_nanoseconds");
            object_or_array = obj.getString("object_or_array");
            validate = obj.getBoolean("validate");
            empty = obj.getBoolean("empty");

            tv1.setText("size: "+size);
            tv2.setText("parse_time_nanoseconds: "+parse_time_nanoseconds);
            tv3.setText("object_or_array: "+object_or_array);
            tv4.setText("validate: "+validate);
            tv5.setText("empty: "+empty);

        } catch (JSONException e) {
            Log.i("crazy","something wrong i can feel it" + "\n" + e);
        }
    }
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();
            String url = "http://validate.jsontest.com/?json=%7B%22key%22:%22value%22%7D";
            String jsonStr = sh.makeServiceCall(url);
            Log.i("crazy", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    // get JSONObject from JSON file

                    JSONObject obj = new JSONObject(JSON_STRING);

                    // get JSON Elements

                    size = obj.getInt("size");
                    parse_time_nanoseconds = obj.getInt("parse_time_nanoseconds");
                    object_or_array = obj.getString("object_or_array");
                    validate = obj.getBoolean("validate");
                    empty = obj.getBoolean("empty");

                    tv1.setText("size: "+size);
                    tv2.setText("parse_time_nanoseconds: "+parse_time_nanoseconds);
                    tv3.setText("object_or_array: "+object_or_array);
                    tv4.setText("validate: "+validate);
                    tv5.setText("empty: "+empty);

                } catch (JSONException e) {
                    Log.i("crazy","something wrong i can feel it" + "\n" + e);
                }

            } else {
                Log.i("crazy", "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}