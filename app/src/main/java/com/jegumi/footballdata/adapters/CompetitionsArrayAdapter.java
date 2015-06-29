package com.jegumi.footballdata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jegumi.footballdata.R;
import com.jegumi.footballdata.model.Competition;

import java.util.List;

public class CompetitionsArrayAdapter extends ArrayAdapter<Competition> {

    public static class ViewHolder {
        private TextView captionTextView;
        private TextView leagueTextView;
        private TextView yearTextView;
        private TextView numberTeamsTextView;
        private TextView numberGamesTextView;
    }

    private Context context;

    public CompetitionsArrayAdapter(Context context, List<Competition> objects) {
        super(context, R.layout.competitions_item, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Competition competition = getItem(position);
        if (convertView == null) {
            convertView = inflateView();
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.captionTextView.setText(competition.caption);
        holder.leagueTextView.setText(context.getString(R.string.shortname, competition.league));
        holder.yearTextView.setText(context.getString(R.string.yeear, competition.year));
        holder.numberTeamsTextView.setText(context.getString(R.string.number_teams, competition.numberOfTeams));
        holder.numberGamesTextView.setText(context.getString(R.string.number_games, competition.numberOfGames));

        return convertView;
    }

    private View inflateView() {
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.competitions_item, null);

        ViewHolder holder = new ViewHolder();
        holder.captionTextView = (TextView) view.findViewById(R.id.caption_text_view);
        holder.leagueTextView = (TextView) view.findViewById(R.id.league_text_view);
        holder.yearTextView = (TextView) view.findViewById(R.id.year_text_view);
        holder.numberTeamsTextView = (TextView) view.findViewById(R.id.number_teams_text_view);
        holder.numberGamesTextView = (TextView) view.findViewById(R.id.number_games_text_view);
        view.setTag(holder);

        return view;
    }
}

