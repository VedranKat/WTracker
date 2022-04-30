package com.example.wtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView weekAndYear;
    SQLiteDatabase myDb;
    LocalDate ld;
    TemporalField woy;
    int weekNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weekAndYear = findViewById(R.id.textView_WeekYear);

        ld = LocalDate.now();
        woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
        weekNumber = ld.get(woy);

        myDb = this.openOrCreateDatabase("Vaga", MODE_PRIVATE, null);
        myDb.execSQL("CREATE TABLE IF NOT EXISTS weeks(week TINYINT, " +
                "year SMALLINT, monday decimal, tuesday decimal, wednesday decimal," +
                " thursday decimal, friday decimal, saturday decimal, sunday decimal, average decimal)");



        Log.d("Dayz", String.valueOf(weekNumber));
        Log.d("Day", String.valueOf(ld.getDayOfWeek().getValue()));


    }
}