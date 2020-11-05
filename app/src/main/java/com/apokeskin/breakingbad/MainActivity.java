package com.apokeskin.breakingbad;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<RecyclerModel> breakingBadList;
    EditText editText;
    ArrayList<RecyclerModel> filteredList;

    RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        editText=findViewById(R.id.editText);
        breakingBadList=new ArrayList<>();
        filteredList=new ArrayList<>();
        Fresco.initialize(this);


        requestQueue= Volley.newRequestQueue(this);

        String url="https://breakingbadapi.com/api/characters";



        JsonArrayRequest jsonObjectRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {


                try {


                    for (int i=0;i<response.length();i++){

                            JSONObject jsonObject= response.getJSONObject(i);
                        String name=jsonObject.getString("name");
                        String nickName=jsonObject.getString("nickname");
                        String uri=jsonObject.getString("img");
                        String replaced=uri.replace("\\\\","");
                        System.out.println("aaaa" +nickName);
                        System.out.println("bbbb" +name);
                        System.out.println("cccc" +replaced);

                        breakingBadList.add(new RecyclerModel(replaced,name,nickName));

                    }


                    RecyclerAdapter recyclerAdapter=new RecyclerAdapter(breakingBadList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerView.setHasFixedSize(true);


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"OLMADI",Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                error.printStackTrace();

            }
        });

        requestQueue.add(jsonObjectRequest);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filteredList.clear();
                 for (int j=0;j<breakingBadList.size();j++){
                     if (breakingBadList.get(j).getCharacterName().contains(s)){
                         filteredList.add(new RecyclerModel(breakingBadList.get(j).getImage(),breakingBadList.get(j).getCharacterName(),breakingBadList.get(j).getCharacterNickName()
                         ));
                     }


                 }
                RecyclerAdapter recyclerAdapter=new RecyclerAdapter(filteredList);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setHasFixedSize(true);



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}