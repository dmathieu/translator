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
        
        
        ((Spinner) findViewById(R.id.languages_from)).setAdapter(Translator.getSpinnerAdapter(this, true));
        ((Spinner) findViewById(R.id.languages_to)).setAdapter(Translator.getSpinnerAdapter(this, false));
		
		findViewById(R.id.translate_button).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String text = ((EditText) findViewById(R.id.text_to_translate)).getText().toString();
				String translatedText = Translator.translate(text);
				((TextView) findViewById(R.id.translated_text)).setText(translatedText);
			}
		});
    }
    
    public static ArrayAdapter getSpinnerAdapter(Activity context, Boolean has_guess) {
    	ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	Translator.fillSpinner(adapter, has_guess);
        return adapter;
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
	
	public static void fillSpinner(ArrayAdapter adapter, Boolean has_guess) {
		adapter.clear();
		
		Integer i = 0;
		for (Language l : Language.values()) {
			if (i > 0 || has_guess == true) {
				adapter.add(l.toString());
			}			
			i += 1;
		}
	}
}
