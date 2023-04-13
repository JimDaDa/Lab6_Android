package com.example.bai_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddEvent extends AppCompatActivity {
    public static final String TITLE = "TITLE";
    public static final String PLACE = "PLACE";
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    private EditText etName, etPlace,etDate,etTime;
    private String[] places = new String[] {"C201", "C202", "C203", "C204"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        initView();
        choosePlace();
        chooseDate();
        chooseTime();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addevent, menu);
        return true;
    }

    private void initView(){
        etName = findViewById(R.id.et_name);
        etPlace = findViewById(R.id.et_place);
        etDate = findViewById(R.id.et_date);
        etTime = findViewById(R.id.et_time);
    }

    private void choosePlace(){
        etPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPlaceSelectionDialog();
            }
        });
    }

    private void chooseDate(){
        showDateSelectionDialog();
    }

    private void chooseTime(){
        showTimeSelectionDialog();
    }
    private void showTimeSelectionDialog() {
        // get current time
        int mHourOfDay = 0;
        int mMinute = 0;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                etTime.setText(hourOfDay + ":" + minute);
            }
        }, mHourOfDay, mMinute, false);
        timePickerDialog.show();
    }
    private void  showDateSelectionDialog(){
        Calendar c = Calendar.getInstance();
        int startYear = c.get(Calendar.YEAR);
        int startMonth = c.get(Calendar.MONTH);
        int startDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                etDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
            }
        }, startYear, startMonth, startDay);
        datePickerDialog.show();
    }
    private void showPlaceSelectionDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select place");
        builder.setSingleChoiceItems(places, 2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                etPlace.setText(places[i]);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:
                saveData();
        }
        return super.onOptionsItemSelected(item);
    }
    private void saveData(){
        Intent action = new Intent();

        Bundle bundle = new Bundle();
        bundle.putString(TITLE, etName.getText().toString());
        bundle.putString(PLACE, etPlace.getText().toString());
        bundle.putString(DATE, etDate.getText().toString());
        bundle.putString(TIME, etTime.getText().toString());

        action.putExtras(bundle);

        setResult(Activity.RESULT_OK, action);
        finish();
    }
}