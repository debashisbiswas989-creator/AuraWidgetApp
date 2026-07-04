package com.aura.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AppWidgetConfigureActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Launch the system app picker
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivityForResult(mainIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            // Here you would eventually save the selected app's package name
            Toast.makeText(this, "App Selected: " + data.getComponent().getPackageName(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            finish();
        }
    }
}
