package com.example.bai_3;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class Bai_3 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Event> event =new ArrayList<>();;

    private final Adapter3 adapter = new Adapter3(event);

    public static final String INTENT_EVENT_ACTION = "INTENT_EVENT_ACTION";
    public static final String INTENT_EVENT_ID = "EVENT_ID";

    public static final String INTENT_NEW_EVENT_ACTION = "NEW";
    public static final String INTENT_EDIT_EVENT_ACTION = "EDIT";
    private EventDatabaseHelper mDbHelper;
    private Switch switchEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        initView();
        //addData();
        setAdapter();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bar_action, menu);

       // Switch switchEvent= findViewById(R.id.sw_event);
//        switchEvent.setChecked(false);

//        switchEvent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                showAllEventsFromDatabase();
//            }
//        });
       //showAllEventsFromDatabase();
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.removeAll) {
            showDialog();
        } else if (item.getItemId() == R.id.about) {
            showAboutDialog();
        } else if (item.getItemId() == R.id.add) {
            openCreateNewEventActivity();
        }
        return super.onOptionsItemSelected(item);

    }

//    private void clickDeleteAll(){
//
//      //  event.clear();
//        adapter.notifyDataSetChanged();
//    }
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure to delete all item?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDbHelper.deleteAllEvents();
                showAllEventsFromDatabase();
                Toast.makeText(Bai_3.this, "The selected item is deleted!", Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog confirmDialog = builder.create();
        confirmDialog.show();
    }


    private void openCreateNewEventActivity() {
        // create intent to call NewEventActivity
        Intent intent = new Intent(Bai_3.this, AddEvent.class);
        intent.putExtra(INTENT_EVENT_ACTION, INTENT_NEW_EVENT_ACTION);
        intent.putExtra(INTENT_EVENT_ACTION, INTENT_EDIT_EVENT_ACTION);
        launcherActivity.launch(intent);

    }
    private void showAllEventsFromDatabase() {
        event  = mDbHelper.getAllEvents(switchEvent.isChecked());
        setAdapter();
    }

    final ActivityResultLauncher<Intent> launcherActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
//
                    if(result.getResultCode() == Activity.RESULT_OK) {
                       // showAllEventsFromDatabase();
                        Intent intent = result.getData();
                        Bundle returnedBundle = intent.getExtras();
                        String title = returnedBundle.getString(AddEvent.TITLE);
                        String place = returnedBundle.getString(AddEvent.PLACE);
                        String date = returnedBundle.getString(AddEvent.DATE);
                        String time = returnedBundle.getString(AddEvent.TIME);
                        Event newEvent = new Event(title, place, date, time);
                        event.add(newEvent);
                        adapter.notifyDataSetChanged();
                    }
                }
            });

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About");
        builder.setMessage("The application is developed by KD!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Bai_3.this, "GOOD LUCK!", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void initView(){
        recyclerView = findViewById(R.id.listView2);
        mDbHelper = new EventDatabaseHelper(Bai_3.this);
        mDbHelper.createDefaultEvents();
    }

    private void setAdapter(){

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }
//    private void addData(){
//
//        event.add(new Event("Sinh hoat chu nhiem", "c120", "09/03/2020", "04:43"));
//        event.add(new Event("Huong dan luan van", "c120", "09/03/2020", "04:43"));
//
//    }

}