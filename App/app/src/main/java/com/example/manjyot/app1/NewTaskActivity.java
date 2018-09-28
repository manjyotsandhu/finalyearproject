package com.example.manjyot.app1;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    TextView dateChosen;
    TextView priorityChosen;
    DatePickerDialog datePickerDialog;


    boolean dateSelected = false;
    boolean prioritySelected = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        ImageButton cancelNewTaskBtn = (ImageButton) findViewById(R.id.cancel_new_task_btn);
        FloatingActionButton confirmTask = (FloatingActionButton) findViewById(R.id.confirm_new_task_btn);
        final EditText taskTitleInput = (EditText) findViewById(R.id.new_task_title_input);

        dateChosen = (TextView) findViewById(R.id.new_task_date_choice);
        priorityChosen = (TextView) findViewById(R.id.new_task_priority_choice);


        LinearLayout dueDateLayout = (LinearLayout)findViewById(R.id.dueDateLayout);
        LinearLayout priorityLayout = (LinearLayout)findViewById(R.id.priorityLayout);
        LinearLayout tagLayout = (LinearLayout)findViewById(R.id.tagLayout);

        //If cancel button clicked, take back to main page
        cancelNewTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewTaskActivity.this, NavigationActivity.class));
            }
        });

        //Custom dialog for an alert if a date has not been selected
        final AlertDialog.Builder dateAlertDialog = new AlertDialog.Builder(this);
        dateAlertDialog.setMessage("Please select a due date for this task ");
        dateAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        final AlertDialog selectDateAlertDialog = dateAlertDialog.create();

        final AlertDialog.Builder priorityAlertDialog = new AlertDialog.Builder(this);
        priorityAlertDialog.setMessage("Please select a priority for this task");
        priorityAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        final AlertDialog selectPriorityAlertDialog = priorityAlertDialog.create();

        confirmTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = taskTitleInput.getText().toString();
                if (TextUtils.isEmpty(title)) { //If no title entered
                    taskTitleInput.setError("Please enter task title");
                } else if (dateSelected == false) { //If no dateChosen chosen
                    selectDateAlertDialog.show();
                } else if (prioritySelected == false) { //If no priority chosen
                    selectPriorityAlertDialog.show();
                } else { //If all conditions are met, return to main page
                    startActivity(new Intent(NewTaskActivity.this, NavigationActivity.class));
                }
            }
        });

        dueDateLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Getting current dateChosen
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                //Setting up dialog
                datePickerDialog = new DatePickerDialog(NewTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateChosen.setText(day + "/" + month + 1 + "/" + year);
                        dateSelected = true;
                    }
                }, year, month, day);

                datePickerDialog.show();
            }
        });

        final AlertDialog.Builder priorityPickerDialog = new AlertDialog.Builder(NewTaskActivity.this);
        priorityPickerDialog.setTitle("Select priority");
        priorityPickerDialog.setIcon(R.drawable.priorityicon);

        priorityPickerDialog.setItems(R.array.priorityArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int selectedChoice) {
                switch(selectedChoice) {
                    case 0:
                        priorityChosen.setText("Low");
                        break;
                    case 1:
                        priorityChosen.setText("Medium");
                        break;
                    case 2:
                        priorityChosen.setText("High");
                        break;
                }
                prioritySelected = true;
            }
        });

        final AlertDialog selectPriorityDialog = priorityPickerDialog.create();

        priorityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPriorityDialog.show();
            }
        });


        final AlertDialog.Builder tagAlertDialog = new AlertDialog.Builder(this);
        tagAlertDialog.setTitle("Unimplemented feature!");
        tagAlertDialog.setMessage("In an actual full implementation of this application where back end coding is the focus, a complex tag system would be put in place.");
        tagAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        final AlertDialog tagDialog = tagAlertDialog.create();

        tagLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagDialog.show();
            }
        });


    }

}
