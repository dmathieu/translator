package com.android.translator;

import android.app.Activity;
import android.os.Bundle;

import android.widget.Spinner;
import android.widget.ArrayAdapter;


public class Translator extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.languages_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        Spinner languages_from = (Spinner) findViewById(R.id.languages_from);
        languages_from.setAdapter(adapter);
        
        Spinner languages_to = (Spinner) findViewById(R.id.languages_to);
        languages_to.setAdapter(adapter);
    }
}
