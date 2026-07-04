package com.aura.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class AppWidgetConfigureActivity extends Activity {
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
        Intent mainIntent = new Intent(Intent.ACTION_MAIN);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        startActivityForResult(mainIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            Toast.makeText(this, "Widget Configured", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            setResult(RESULT_CANCELED);
            finish();
        }
    }
}
