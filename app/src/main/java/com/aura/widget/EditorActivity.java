package com.aura.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.appwidget.AppWidgetManager;
import java.io.FileOutputStream;

public class EditorActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        EditText editText = findViewById(R.id.editTextContent);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String userText = editText.getText().toString();
            // We will save this text to a local file
            saveWidgetData(userText);
            finish();
        });
    }

    private void saveWidgetData(String text) {
        try {
            FileOutputStream fos = openFileOutput("widget_data.txt", MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
