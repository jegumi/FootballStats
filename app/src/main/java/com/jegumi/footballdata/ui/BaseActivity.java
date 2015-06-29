package com.jegumi.footballdata.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jegumi.footballdata.R;

public class BaseActivity extends ActionBarActivity {

    public void setActionBar(boolean isHome) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(!isHome);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, PreferenceWithHeaders.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Public for testing
    public boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }
}