package com.jegumi.footballdata.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.jegumi.footballdata.R;

public class ListCompetitionsActivity extends BaseActivity {
    private static final String TAG = ListCompetitionsActivity.class.getName();
    public static final String EXTRA_COMPETITION = "com.jegumi.footballdata.competition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isTablet()) {
            setContentView(R.layout.main_layout);
        }
        loadFragment();
    }

    private void loadFragment() {
        int layoutDestination = isTablet() ? R.id.left_layout : android.R.id.content;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ListCompetitionsFragment startFragment = ListCompetitionsFragment.newInstance();
        ft.replace(layoutDestination, startFragment, TAG);
        ft.commit();
    }

    // Public for testing
    public boolean isTablet() {
        return getResources().getBoolean(R.bool.is_tablet);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PreferenceWithHeaders.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}