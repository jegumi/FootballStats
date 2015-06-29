package com.jegumi.footballdata.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jegumi.footballdata.R;
import com.jegumi.footballdata.adapters.LeagueTableArrayAdapter;
import com.jegumi.footballdata.model.Competition;
import com.jegumi.footballdata.model.LeagueTable;
import com.jegumi.footballdata.model.Standing;
import com.jegumi.footballdata.network.Api;
import com.jegumi.footballdata.network.GsonRequest;
import com.jegumi.footballdata.network.VolleySingleton;

import java.util.Arrays;

public class CompetitionFragment extends Fragment {

    private static final String TAG = CompetitionFragment.class.getName();

    private ListView leagueTableListView;

    public static CompetitionFragment newInstance(Competition competition) {
        CompetitionFragment competitionFragment = new CompetitionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ListCompetitionsActivity.EXTRA_COMPETITION, competition);
        competitionFragment.setArguments(bundle);
        return competitionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.competition_detail, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) getActivity()).setActionBar(false);

        leagueTableListView = (ListView) view.findViewById(R.id.leagueTableListView);

        Bundle arguments = getArguments();
        if (arguments != null) {
            Competition competition = (Competition) arguments.getSerializable(ListCompetitionsActivity.EXTRA_COMPETITION);
            initFields(competition);
        }
    }

    private void initFields(Competition competition) {
        getActivity().setTitle(competition.caption);
        loadAdapter(competition);
    }

    private void loadAdapter(Competition competition) {
        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        GsonRequest<LeagueTable> jsObjRequest = new GsonRequest<LeagueTable>(
                Request.Method.GET,
                competition._links.leagueTable.href,
                LeagueTable.class, Api.getHeaders(),
                new Response.Listener<LeagueTable>() {
                    @Override
                    public void onResponse(LeagueTable leagueTable) {
                        setAdapter(leagueTable);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, "Error loading league table", volleyError);
                    }
                }
        );

        jsObjRequest.setTag(TAG);
        queue.add(jsObjRequest);
    }

    private void setAdapter(LeagueTable leagueTable) {
        Activity parentActivity = getActivity();
        if (parentActivity != null) {
            leagueTableListView.setAdapter(new LeagueTableArrayAdapter(parentActivity, Arrays.asList(leagueTable.standing)));
            leagueTableListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Standing standing = (Standing) parent.getItemAtPosition(position);
                    openTeam(standing);
                }
            });
        }
    }

    private void openTeam(Standing standing) {
        Intent intent = new Intent(getActivity(), TeamActivity.class);
        intent.putExtra(TeamActivity.EXTRA_TEAM, standing._links.team.href);
        startActivity(intent);
    }
}
