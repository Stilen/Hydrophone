package com.example.pedro.marsensing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class SeeConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name = getIntent().getExtras().getString("name");
        DBHandler db = new DBHandler(this);
        Config config = db.getConfigByName(name);

        TextView configName= (TextView) findViewById(R.id.configName);
        configName.setText(config.getName());

        TextView configGain= (TextView) findViewById(R.id.editGain);
        configGain.setText(Double.toString(config.getGain()));
        TextView configInterval= (TextView) findViewById(R.id.configInterval);
        configInterval.setText(Integer.toString(config.getInterval()));
        TextView configMode= (TextView) findViewById(R.id.configMode);
        configMode.setText(Integer.toString(config.getMode()));

        TextView configDuration= (TextView) findViewById(R.id.configDuration);
        configDuration.setText(Integer.toString(config.getDuration()));
        TextView configAdc= (TextView) findViewById(R.id.configADC);
        configAdc.setText(Integer.toString(config.getAdc()));
        TextView configBits= (TextView) findViewById(R.id.configBits);
        configBits.setText(Integer.toString(config.getBits()));
        TextView configFiles= (TextView) findViewById(R.id.configNfiles);
        configFiles.setText(Integer.toString(config.getNfiles()));

        setTitle(config.getName());

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

}
