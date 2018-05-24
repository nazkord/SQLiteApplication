package com.example.nazar.sqliteapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText input_name;
    EditText input_age;
    EditText input_colorEye;
    EditText input_tmpr;
    TextView text;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createToolbar();

        input_name = (EditText) findViewById(R.id.input_name);
        input_age = (EditText) findViewById(R.id.input_age);
        input_colorEye = (EditText) findViewById(R.id.input_colorEye);
        input_tmpr = (EditText) findViewById(R.id.input_tmpr);
        text = (TextView) findViewById(R.id.textView);
        dbHandler = new MyDBHandler(this, null, null, 1);
        printDatabase();
    }

    public void addButtonClicked(View view) {
        if(!input_name.getText().toString().matches("") && !input_colorEye.getText().toString().matches("")
                && !input_tmpr.getText().toString().matches("") && Integer.parseInt(input_age.getText().toString())>0) {
            Person person = new Person(input_name.getText().toString(),
                    Integer.parseInt(input_age.getText().toString()),
                    input_colorEye.getText().toString(),
                    input_tmpr.getText().toString());
            dbHandler.addPerson(person);
            printDatabase();
        } else {
            createAddErrorDialog();
        }
    }

    private void createAddErrorDialog() {
        AlertDialog.Builder PreBuilder = new AlertDialog.Builder(MainActivity.this);
        PreBuilder.setIcon(android.R.drawable.alert_dark_frame);
        PreBuilder.setTitle("Error");
        PreBuilder.setMessage("You have at least one blank line");
        PreBuilder.setCancelable(false);
        PreBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = PreBuilder.create();
        alertDialog.show();
    }

    private void createDeleteErrorDialog() {
        AlertDialog.Builder PreBuilder = new AlertDialog.Builder(MainActivity.this);
        PreBuilder.setIcon(android.R.drawable.alert_dark_frame);
        PreBuilder.setTitle("Error");
        PreBuilder.setMessage("You didn't type the person name");
        PreBuilder.setCancelable(false);
        PreBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = PreBuilder.create();
        alertDialog.show();
    }

    public void deleteButtonClicked(View view) {
        if (!input_name.getText().toString().matches("")) {
            dbHandler.deletePerson(input_name.getText().toString());
            printDatabase();
        } else {
            createDeleteErrorDialog();
        }
    }

    public void printDatabase() {
        String dbString = dbHandler.databaseToString();
        text.setText(dbString);
        input_name.setText("");
        input_age.setText("");
        input_colorEye.setText("");
        input_tmpr.setText("");
    }


    private void createToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null)
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
    }
}
