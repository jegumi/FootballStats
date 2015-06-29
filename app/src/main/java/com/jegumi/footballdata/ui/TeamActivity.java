package com.jegumi.footballdata.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class TeamActivity extends BaseActivity {
    private static final String TAG = TeamActivity.class.getName();
    public static final String EXTRA_TEAM = "com.jegumi.footballdata.team";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFragment(getIntent().getStringExtra(EXTRA_TEAM));
    }

    private void loadFragment(String teamLink) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        TeamFragment startFragment = TeamFragment.newInstance(teamLink);
        ft.replace(android.R.id.content, startFragment, TAG);
        ft.commit();
    }
}