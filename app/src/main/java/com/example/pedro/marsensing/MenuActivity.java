package com.example.pedro.marsensing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        DBHandler db = new DBHandler(this);

        // Inserting Shop/Rows
        Log.d("Insert: ", "Inserting ..");
        /*db.addConfig(new Config(1, "Ria Formosa", 10.25, 0.5, 10, 1, "10:25", 50));
        db.addConfig(new Config(2, "Sagres", 10.25,0.5,10,1,"10:25",50));
        db.addConfig(new Config(3, "Albufeira", 10.25,0.5,10,1,"10:25",50));
        db.addConfig(new Config(4, "Quarteira", 10.25,0.5,10,1,"10:25",50));
        db.addConfig(new Config(5, "Olhão", 10.25,0.5,10,1,"10:25",50));
        db.addConfig(new Config(6, "Faro", 10.25,0.5,10,1,"10:25",50));
        db.addConfig(new Config(7, "Zambujeira do Mar", 10.25,0.5,10,1,"10:25",50));
        db.addConfig(new Config(8, "Tavira", 10.25,0.5,10,1,"10:25",50));
        db.addConfig(new Config(9, "Portimão", 10.25,0.5,10,1,"10:25",50));*/

        // Reading all shops
        Log.d("Reading: ", "Reading all shops..");
        List<Config> configs = db.getAllConfigs();

        for (Config config : configs) {
            String log = "Id: " + config.getId() + " ,Name: " + config.getName();
            // Writing configs to log
            Log.d("Config: : ", log);
        }
        configsList();

        ListView lv = (ListView)findViewById(R.id.listView);
        TextView emptyText = (TextView)findViewById(R.id.emptyList);
        lv.setEmptyView(emptyText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    public void goToNewConfig(View view) {
        Intent intent = new Intent(this, NewConfigActivity.class);
        startActivity(intent);
    }

    public void goToList(View view) {
        Intent intent = new Intent(this, ConfigsActivity.class);
        startActivity(intent);
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
