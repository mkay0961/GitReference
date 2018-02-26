package com.example.matthewkay.gitreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView jsonview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        jsonview = (ListView) findViewById(R.id.ListView1);

        ArrayList<GitReference> items = populateWithData("gitReference.json");


        JAdapter adapter = new JAdapter(this , items);


        jsonview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"hello" , Toast.LENGTH_SHORT).show();
            }
        });


        jsonview.setAdapter(adapter);









    }

    public ArrayList<GitReference> populateWithData(String fileName){
        ArrayList<String> returnList = new ArrayList<>();

        String jsonString = processData(fileName);

        Log.i("JSON",jsonString );

        ArrayList<GitReference> jsonreferences = JsonUtils.populateGitReferences(jsonString);

//        for (GitReference g:jsonreferences){
//            returnList.add(g.getCommand());
//        }
        return jsonreferences;
    }


//    public ArrayList<String> populateWithData(String fileName){
//        ArrayList<String> returnList = new ArrayList<>();
//
//        String jsonString = processData(fileName);
//
//        Log.i("JSON",jsonString );
//
//        ArrayList<GitReference> jsonreferences = JsonUtils.populateGitReferences(jsonString);
//
//        for (GitReference g:jsonreferences){
//            returnList.add(g.getCommand());
//        }
//        return returnList;
//    }


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
