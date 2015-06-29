package com.jegumi.footballdata.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.jegumi.footballdata.R;
import com.jegumi.footballdata.model.Team;
import com.jegumi.footballdata.network.Api;
import com.jegumi.footballdata.network.GsonRequest;
import com.jegumi.footballdata.network.ImageCacheManager;
import com.jegumi.footballdata.network.VolleySingleton;

public class TeamFragment extends Fragment {

    private static final String TAG = TeamFragment.class.getName();

    private TextView nameTextView;
    private TextView codeTextView;
    private TextView shortNameTextView;
    private TextView squadMarketTextView;
    private NetworkImageView crestImageView;

    public static TeamFragment newInstance(String teamLink) {
        TeamFragment teamFragment = new TeamFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TeamActivity.EXTRA_TEAM, teamLink);
        teamFragment.setArguments(bundle);
        return teamFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.team, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((BaseActivity) getActivity()).setActionBar(false);

        nameTextView = (TextView) view.findViewById(R.id.name_text_view);
        codeTextView = (TextView) view.findViewById(R.id.code_text_view);
        shortNameTextView = (TextView) view.findViewById(R.id.shortname_text_view);
        squadMarketTextView = (TextView) view.findViewById(R.id.squeadmarket_text_view);
        crestImageView = (NetworkImageView) view.findViewById(R.id.crest_image_view);

        Bundle arguments = getArguments();
        if (arguments != null) {
            String teamLink = arguments.getString(TeamActivity.EXTRA_TEAM);
            loadTeam(teamLink);
        }
    }

    private void initFields(Team team) {
        nameTextView.setText(team.name);
        codeTextView.setText(getString(R.string.code_name, team.code));
        shortNameTextView.setText(getString(R.string.shortname, team.shortName));
        squadMarketTextView.setText(getString(R.string.squadvalue, team.squadMarketValue));

        // Load the image is not working because the image received is a svn. I could use an svn
        // lib to manage it, but I suppose for the propose of this test is pointless, and perhaps
        // more interested just to show how to proceed is a regular scenario
        crestImageView.setImageUrl(team.crestUrl, ImageCacheManager.getInstance().getImageLoader());

    }

    private void loadTeam(String teamLink) {
        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        GsonRequest<Team> jsObjRequest = new GsonRequest<Team>(
                Request.Method.GET,
                teamLink,
                Team.class, Api.getHeaders(),
                new Response.Listener<Team>() {
                    @Override
                    public void onResponse(Team team) {
                        initFields(team);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, "Error loading team", volleyError);
                    }
                }
        );

        jsObjRequest.setTag(TAG);
        queue.add(jsObjRequest);
    }
}
