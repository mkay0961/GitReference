package com.example.matthewkay.gitreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView jsonview;


    private SearchView search;

    private JAdapter adapter, mAdapter;
    ArrayList<GitReference> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        jsonview = (ListView) findViewById(R.id.ListView1);

        search = (SearchView) findViewById(R.id.searchid);

        items = populateWithData("gitReference.json");

        adapter = new JAdapter(getApplicationContext() , items);

        jsonview.setAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                int textlength = s.length();
                ArrayList<GitReference> tempArrayList = new ArrayList<GitReference>();
                for(GitReference c: items){
                    if (textlength <= c.getCommand().length()) {
                        if (c.getCommand().toLowerCase().contains(s.toString().toLowerCase())) {
                            tempArrayList.add(c);
                        }
                    }
                }
                mAdapter = new JAdapter(getApplicationContext(), tempArrayList);
                jsonview.setAdapter(mAdapter);
                return false;
            }
        });

    }

    public ArrayList<GitReference> populateWithData(String fileName){
        String jsonString = processData(fileName);

        Log.i("JSON",jsonString );

        ArrayList<GitReference> jsonreferences = JsonUtils.populateGitReferences(jsonString);
        return jsonreferences;
    }



    public String processData(String filename) {
        String jsonString = "";
        boolean isFilePresent = JsonUtils.isFilePresent(this, filename);

        if (isFilePresent) {
            jsonString = JsonUtils.read(this, filename);

            Log.i("JSON", "JSON was present");

        } else {
            Log.i("JSON", "JSON file not present. Creating......");
            InputStream inputStream = null;

            try {
                inputStream = getApplicationContext().getAssets().open("gitReference.json");
            } catch (Exception ex) {
                System.out.println("Exception Occurred");
            }
            jsonString = JsonUtils.parseJsonToString(inputStream);
            boolean isFileCreated = JsonUtils.create(this, filename, jsonString);
            if (isFileCreated) {
                Log.i("JSON", "Created the filesystem JSON");
            } else {

            }
        }
        return jsonString;
    }

}
