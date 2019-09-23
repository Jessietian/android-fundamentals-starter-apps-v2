package com.example.android.hellosharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Arrays;

public class SettingActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.hellosharedprefs";

    private Context mContext;

    // Key for current count
    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";

    // Current count
    private int mCount;

    // Current background color
    private int mColor;

    private String[] colorNames = { "Default", "Red", "Blue", "Green" };
    private int[] colors = {
        R.color.default_background,
        R.color.red_background,
        R.color.blue_background,
        R.color.green_background
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mContext = this;
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        mCount = mPreferences.getInt(COUNT_KEY, 0);

        final Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final TextView count_view = findViewById(R.id.current_count);
        count_view.setText(String.format("%s", mCount));

        Button count = findViewById(R.id.button_count);
        count.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mCount;
                count_view.setText(String.format("%s", mCount));
            }
        });




        Button save = findViewById(R.id.save);
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mPreferences.edit();
                int selected = spinner.getSelectedItemPosition();
                editor.putInt(COLOR_KEY, ContextCompat.getColor(mContext, colors[selected]));
                editor.putInt(COUNT_KEY, mCount);
                editor.apply();
            }
        });

        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putInt(COLOR_KEY, ContextCompat.getColor(mContext, colors[0]));
                editor.putInt(COUNT_KEY, 0);
                count_view.setText(String.format("%s", '0'));
                mCount = 0;
                editor.apply();
            }
        });





    }
}
