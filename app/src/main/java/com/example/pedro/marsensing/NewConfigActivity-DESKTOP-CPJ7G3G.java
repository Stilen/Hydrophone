package com.example.pedro.marsensing;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class NewConfigActivity extends AppCompatActivity implements View.OnClickListener{


    Button applyButton;
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText txtDate, txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_config);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Spinner adcSpinner = (Spinner) findViewById(R.id.editADC);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adcAdapter = ArrayAdapter.createFromResource(this,
                R.array.freq_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        adcSpinner.setAdapter(adcAdapter);

        Spinner gainSpinner = (Spinner) findViewById(R.id.editGain);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> gainAdapter = ArrayAdapter.createFromResource(this,
                R.array.gain_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        gainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        gainSpinner.setAdapter(gainAdapter);

        Spinner modeSpinner = (Spinner) findViewById(R.id.editMode);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> modeAdapter = ArrayAdapter.createFromResource(this,
                R.array.mode_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        modeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        modeSpinner.setAdapter(modeAdapter);

        Spinner bitsSpinner = (Spinner) findViewById(R.id.editBits);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> bitsAdapter = ArrayAdapter.createFromResource(this,
                R.array.bits_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        bitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        bitsSpinner.setAdapter(bitsAdapter);

        modeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Spinner mode = (Spinner)findViewById(R.id.editMode);
                String m = mode.getSelectedItem().toString();
                if(m.equals("Scheduled")){
                    View interval = findViewById(R.id.textView8);
                    interval.setVisibility(View.VISIBLE);
                    View setInterval = findViewById(R.id.editInterval);
                    setInterval.setVisibility(View.VISIBLE);
                    View nfiles = findViewById(R.id.textView16);
                    nfiles.setVisibility(View.VISIBLE);
                    View setNfiles = findViewById(R.id.editNfiles);
                    setNfiles.setVisibility(View.VISIBLE);
                    //View duration = findViewById(R.id.textView10);
                    //duration.setVisibility(View.VISIBLE);
                    //View setDuration = findViewById(R.id.editDuration);
                    //setDuration.setVisibility(View.VISIBLE);
                }
                else {
                    View interval = findViewById(R.id.textView8);
                    interval.setVisibility(View.INVISIBLE);
                    View setInterval = findViewById(R.id.editInterval);
                    setInterval.setVisibility(View.INVISIBLE);
                    View nfiles = findViewById(R.id.textView16);
                    nfiles.setVisibility(View.INVISIBLE);
                    View setNfiles = findViewById(R.id.editNfiles);
                    setNfiles.setVisibility(View.INVISIBLE);
                    //View duration = findViewById(R.id.textView10);
                    //duration.setVisibility(View.INVISIBLE);
                    //View setDuration = findViewById(R.id.editDuration);
                    //setDuration.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        applyButton = (Button) findViewById(R.id.applyButton);


        txtDate = (EditText) findViewById(R.id.txtDate);
        txtTime = (EditText) findViewById(R.id.txtTime);

        applyButton.setOnClickListener(this);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        View duration = findViewById(R.id.textView10);
        duration.setVisibility(View.INVISIBLE);
        View setDuration = findViewById(R.id.editDuration);
        setDuration.setVisibility(View.INVISIBLE);
        switch (item.getItemId()) {
            case R.id.editMode:
                Spinner mode = (Spinner)findViewById(R.id.editMode);
                String m = mode.getSelectedItem().toString();
                if(m.equals("Scheduled")){

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void clickSave(View view) {
        DBHandler db = new DBHandler(this);
        Config config = new Config();
        EditText name = (EditText)findViewById(R.id.editName);
        config.setName(name.getText().toString().trim());
        Spinner gain = (Spinner)findViewById(R.id.editGain);
        config.setGain(gain.getSelectedItemPosition()+1);
        Spinner mode = (Spinner)findViewById(R.id.editMode);
        config.setMode(mode.getSelectedItemPosition());
        EditText dur = (EditText) findViewById(R.id.editDuration);
        config.setDuration(Integer.parseInt(dur.getText().toString().trim()));
        if(mode.getSelectedItemPosition()==2) {
            EditText nfiles = (EditText) findViewById(R.id.editNfiles);
            config.setInterval(Integer.parseInt(nfiles.getText().toString().trim()));
            EditText interval = (EditText)findViewById(R.id.editInterval);
            config.setInterval(Integer.parseInt(interval.getText().toString().trim()));
        }
        Spinner adc = (Spinner) findViewById(R.id.editADC);
        config.setAdc(adc.getSelectedItemPosition());
        Spinner bits = (Spinner)findViewById(R.id.editBits);
        config.setBits(Integer.parseInt(bits.getSelectedItem().toString()));

        db.addConfig(config);

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        if (v == applyButton) {

            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            final Context context = this;
            DatePickerDialog dpd = new DatePickerDialog(context,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            txtDate.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);

                            final Calendar c = Calendar.getInstance();
                            mHour = c.get(Calendar.HOUR_OF_DAY);
                            mMinute = c.get(Calendar.MINUTE);

                            // Launch Time Picker Dialog
                            TimePickerDialog tpd = new TimePickerDialog(context,
                                    new TimePickerDialog.OnTimeSetListener() {

                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay,
                                                              int minute) {
                                            // Display Selected time in textbox
                                            txtTime.setText(hourOfDay + ":" + minute);
                                        }
                                    }, mHour, mMinute, false);
                            tpd.show();

                        }
                    }, mYear, mMonth, mDay);
            dpd.setButton(Dialog.BUTTON_POSITIVE, "Next", dpd);

            dpd.show();
        }
        /*if (v == btnTimePicker) {

            // Process to get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            // Display Selected time in textbox
                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }*/
    }
}
