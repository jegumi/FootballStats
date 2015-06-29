package com.jegumi.footballdata.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.jegumi.footballdata.adapters.CompetitionsArrayAdapter;
import com.jegumi.footballdata.model.Competition;
import com.jegumi.footballdata.network.Api;
import com.jegumi.footballdata.network.GsonRequest;
import com.jegumi.footballdata.network.VolleySingleton;

import java.util.Arrays;

public class ListCompetitionsFragment extends ListFragment {

    private static final String TAG = ListCompetitionsFragment.class.getName();

    public static ListCompetitionsFragment newInstance() {
        return new ListCompetitionsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.jegumi.footballdata.R.layout.competition_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadAdapter();
        ((BaseActivity) getActivity()).setActionBar(true);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Competition competition = (Competition) l.getItemAtPosition(position);
        openCompetition(competition);
    }

    private void loadAdapter() {
        RequestQueue queue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        String uri = Api.getCompetitionsEndPoint();
        GsonRequest<Competition[]> jsObjRequest = new GsonRequest<Competition[]>(
                Request.Method.GET,
                uri,
                Competition[].class, Api.getHeaders(),
                new Response.Listener<Competition[]>() {
                    @Override
                    public void onResponse(Competition[] competitions) {
                        setAdapter(competitions);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG, "Error loading competition", volleyError);
                    }
                }
        );

        jsObjRequest.setTag(TAG);
        queue.add(jsObjRequest);
    }

    private void setAdapter(Competition[] competition) {
        Activity parentActivity = getActivity();
        if (parentActivity != null) {
            getListView().setAdapter(new CompetitionsArrayAdapter(parentActivity, Arrays.asList(competition)));
        }
    }

    private void openCompetition(Competition competition) {
        Intent intent = new Intent(getActivity(), CompetitionActivity.class);
        intent.putExtra(ListCompetitionsActivity.EXTRA_COMPETITION, competition);
        startActivity(intent);
    }
}
