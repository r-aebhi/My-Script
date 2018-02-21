package samplerecv.aebhi.addictech.samplerecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import samplerecv.aebhi.addictech.samplerecyclerview.Adapters.RecViewAdapter;
import samplerecv.aebhi.addictech.samplerecyclerview.Adapters.VolleyAdapter;
import samplerecv.aebhi.addictech.samplerecyclerview.Models.RecViewData;

public class SampleRecyclerView extends AppCompatActivity {
    RecyclerView recView;
    RecViewAdapter adapter;
    ArrayList <RecViewData> dataFeed;
    private String dataUrl ="https://randomuser.me/api/?inc=email,picture&results=50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen1);

        dataFeed = new ArrayList<>();//RecViewData.DataDump(25);//making a list of 25 items
        recView = (RecyclerView) findViewById(R.id.recV);
        recView.setLayoutManager(new LinearLayoutManager(this));

        loadVolleyData();
    }

   public void loadVolleyData(){
       JsonObjectRequest jsObjRequest = new JsonObjectRequest
               (Request.Method.GET, dataUrl, null, new Response.Listener<JSONObject>() {

                   @Override
                   public void onResponse(JSONObject response) {
                       try{
                           JSONArray j1 = response.getJSONArray("results");
                           for (int i =0;i<j1.length();i++){
                               JSONObject j2 = j1.getJSONObject(i);
                               String email = j2.getString("email");
                               JSONObject j3 = j2.getJSONObject("picture");
                               String ImgUrl =j3.get("medium").toString();
                               RecViewData d = new RecViewData(email,i+1,ImgUrl);
                               dataFeed.add(d);
                           }

                           adapter = new RecViewAdapter(getApplicationContext(),dataFeed);
                           recView.setAdapter(adapter);

                       }
                       catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }
               }, new Response.ErrorListener() {

                   @Override
                   public void onErrorResponse(VolleyError error) {
                   }
               });

       VolleyAdapter.getInstance(this).addToRequestQueue(jsObjRequest);
   }
}
