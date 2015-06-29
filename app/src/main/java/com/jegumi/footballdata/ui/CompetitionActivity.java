package com.jegumi.footballdata.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jegumi.footballdata.R;
import com.jegumi.footballdata.model.Competition;

public class CompetitionActivity extends BaseActivity {
    private static final String TAG = CompetitionActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Competition competition = (Competition) getIntent().getSerializableExtra(ListCompetitionsActivity.EXTRA_COMPETITION);
        loadFragment(competition);
    }

    private void loadFragment(Competition competition) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        CompetitionFragment startFragment = CompetitionFragment.newInstance(competition);
        ft.replace(android.R.id.content, startFragment, TAG);
        ft.commit();
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
}