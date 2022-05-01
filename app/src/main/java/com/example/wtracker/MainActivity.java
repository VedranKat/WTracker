package com.example.wtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView weekAndYear;
    SQLiteDatabase myDb;
    LocalDate ld;
    TemporalField woy;
    int weekNumber;
    Button monday, tuesday, wednesday, thursday, friday, saturday, sunday, back, forward;
    int weightToDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monday = findViewById(R.id.buttonMonday);
        monday.setOnClickListener(this::onClick);
        tuesday = findViewById(R.id.buttonTuesday);
        tuesday.setOnClickListener(this::onClick);
        wednesday = findViewById(R.id.buttonWednesday);
        wednesday.setOnClickListener(this::onClick);
        thursday = findViewById(R.id.buttonThursday);
        thursday.setOnClickListener(this::onClick);
        friday = findViewById(R.id.buttonFriday);
        friday.setOnClickListener(this::onClick);
        saturday = findViewById(R.id.buttonSaturday);
        saturday.setOnClickListener(this::onClick);
        sunday = findViewById(R.id.buttonSunday);
        sunday.setOnClickListener(this::onClick);
        back = findViewById(R.id.buttonBack);
        back.setOnClickListener(this::onClick);
        forward = findViewById(R.id.buttonForward);
        forward.setOnClickListener(this::onClick);

        weekAndYear = findViewById(R.id.textView_WeekYear);

        ld = LocalDate.now();
        woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        weekNumber = ld.get(woy);

        myDb = this.openOrCreateDatabase("Vaga", MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS weeks(week TINYINT, " +
                "year SMALLINT, monday decimal, tuesday decimal, wednesday decimal," +
                " thursday decimal, friday decimal, saturday decimal, sunday decimal, average decimal)");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonMonday:
                alert("monday");
                break;
            case R.id.buttonTuesday:
                alert("tuesday");
                break;
            case R.id.buttonWednesday:
                alert("wednesday");
                break;
            case R.id.buttonThursday:
                alert("thursday");
                break;
            case R.id.buttonFriday:
                alert("friday");
                break;
            case R.id.buttonSaturday:
                alert("saturday");
                break;
            case R.id.buttonSunday:
                alert("sunday");
                break;
            case R.id.buttonBack:
                // do your code
                break;
            case R.id.buttonForward:
                // do your code
                break;
            default:
                break;
        }
    }

    public void alert(String day){
        AlertDialog.Builder myDia = new AlertDialog.Builder(MainActivity.this);
        myDia.setTitle("Body weight in Kilograms?");

        final EditText weightInput = new EditText(MainActivity.this);
        weightInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        myDia.setView(weightInput);

        myDia.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                weightToDatabase = Integer.parseInt(weightInput.getText().toString());
            }
        });

        myDia.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        myDia.show();

    }

}