package com.cdm.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

public class MainActivity extends Activity {
    private AlertDialog loginDialog;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void button1OnClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("Alert Dialog");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("Alert dialog click no which " + which);
            }

        });
        builder.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setTitle("Alert dialog click cancel which " + which);
                    }
                });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("Alert dialig click yes which " + which);
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void button2OnClick(View v) {
        ProgressDialog dialog = new ProgressDialog(this);
        //dialog.setTitle("Title");
        dialog.setMessage("Waiting...");
//		dialog.setCancelable(false);
        dialog.show();
    }

    public void button3OnClick(View v) {
        DatePickerDialog dialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        setTitle("Date picker dialog select " + year + " "
                                + monthOfYear + " " + dayOfMonth);
                    }

                }, 2011, 7, 1);

        dialog.show();
    }

    public void button4OnClick(View v) {
        TimePickerDialog dialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        setTitle("Time picker dialog select " + hourOfDay + " "
                                + minute);
                    }

                }, 22, 22, true);

        dialog.show();
    }

    public void button5OnClick(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.loading, null);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        view.setLayoutParams(params);


        // Button loginButton = (Button) view.findViewById(R.id.loginButton);
        // loginButton.setOnClickListener(new View.OnClickListener() {
        //
        // @Override
        // public void onClick(View v) {
        // loginDialog.dismiss();
        // setTitle("Login");
        // }
        // });

        builder.setView(view);

        loginDialog = builder.create();
        loginDialog.show();
    }

    public void button6OnClick(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final String[] cities = new String[]{"西安", "咸阳", "大荔", "延安", "河南", "西安", "咸阳", "大荔", "延安", "河南", "西安", "咸阳", "大荔", "延安", "河南"};

        builder.setItems(cities, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                setTitle(cities[which]);
            }
        });


        AlertDialog dialog = builder.create();
        dialog.show();
    }
}