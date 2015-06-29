package com.jegumi.footballdata.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jegumi.footballdata.R;

import java.util.List;

public class PreferenceWithHeaders extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();
    }

    private void setToolbar() {
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        LinearLayout content = (LinearLayout) root.getChildAt(0);
        LinearLayout toolbarContainer = (LinearLayout) View.inflate(this, R.layout.settings, null);

        root.removeAllViews();
        toolbarContainer.addView(content);
        root.addView(toolbarContainer);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(com.jegumi.footballdata.R.xml.preference_header, target);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}