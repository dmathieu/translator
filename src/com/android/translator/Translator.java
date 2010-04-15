package com.android.translator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.api.translate.Language;
import com.google.api.translate.Translate;


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
		
		Button translate = (Button) findViewById(R.id.translate_button);
		translate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				EditText edit_text = (EditText) findViewById(R.id.text_to_translate);
				String text = edit_text.getText().toString();
				
				String translatedText = Translator.translate(text);
				TextView translated_text = (TextView) findViewById(R.id.translated_text);
				translated_text.setText(translatedText);
			}
		});
    }
	
	public static String translate(String text) {
		try {
			Translate.setHttpReferrer("http//www.dmathieu.com");
			String translatedText = Translate.execute(text, Language.ENGLISH, Language.FRENCH);
			return translatedText;
		} catch(Exception e) {
			return "An error occured. Can't translate.";
		}
	}
}
