package com.cs3398royal.remindme.remindme;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    private EditText dateText;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //handles the Calendar pop up when the user presses the Due date box
        dateText = (EditText) findViewById(R.id.editText2);
        dateText.setInputType(InputType.TYPE_NULL);
        Calendar newCalendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("E, MMM dd, yyyy", Locale.US);
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateText.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        Spinner spinner = (Spinner)findViewById(R.id.list_dropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lists_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_task_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        EditText text = (EditText) findViewById(R.id.editText);
        String task = text.getText().toString();
        if (task.equals(""))
            return false;
        switch (id) {
            case R.id.action_add:
                Intent i = new Intent(AddTaskActivity.this, MainActivity.class);
                i.putExtra("taskName", task);
                //ends the first main activty only if a new task has been added
                MainActivity.mainReference.finish();
                startActivity(i);
                finish();
                return true;
        }
        return false;
    }


}