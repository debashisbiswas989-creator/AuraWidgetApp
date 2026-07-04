package com.aura.widget;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class AppWidgetConfigureActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            finish(); 
        });
    }
}
