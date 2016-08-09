package com.example.pedro.marsensing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ConfigsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Intent configList = new Intent(getApplicationContext(),configsList);
        //startActivity(configList);
        configsList();
    }

    public void configsList(){
            ListView list = (ListView)findViewById(R.id.listView);
            final List<String> configs = new ArrayList<String>();
            DBHandler db = new DBHandler(this);
            List<Config> dbConfigs = db.getAllConfigs();
            for(int i=0;i<dbConfigs.size();i++){
                configs.add(dbConfigs.get(i).getName());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,configs);
            list.setAdapter(arrayAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), SeeConfigActivity.class);
                String name = configs.get(position);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

}
