package com.jegumi.footballdata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jegumi.footballdata.R;
import com.jegumi.footballdata.model.Competition;
import com.jegumi.footballdata.model.Standing;

import java.util.List;

public class LeagueTableArrayAdapter extends ArrayAdapter<Standing> {

    public static class ViewHolder {
        private TextView positionTextView;
        private TextView teamNameTextView;
        private TextView playedGamesTextView;
        private TextView pointsTextView;
        private TextView goalsTextView;
        private TextView goalsAgainstsTextView;
        private TextView goalsDifferenceTextView;
    }

    private Context context;

    public LeagueTableArrayAdapter(Context context, List<Standing> objects) {
        super(context, R.layout.league_table_item, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Standing competition = getItem(position);
        if (convertView == null) {
            convertView = inflateView();
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        holder.positionTextView.setText(String.valueOf(competition.position));
        holder.teamNameTextView.setText(competition.teamName);
        holder.playedGamesTextView.setText(String.valueOf(competition.playedGames));
        holder.pointsTextView.setText(String.valueOf(competition.points));
        holder.goalsTextView.setText(String.valueOf(competition.goals));
        holder.goalsAgainstsTextView.setText(String.valueOf(competition.goalsAgainst));
        holder.goalsDifferenceTextView.setText(String.valueOf(competition.goalsDifference));

        return convertView;
    }

    private View inflateView() {
        final LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vi.inflate(R.layout.league_table_item, null);

        ViewHolder holder = new ViewHolder();
        holder.positionTextView = (TextView) view.findViewById(R.id.position_text_view);
        holder.teamNameTextView = (TextView) view.findViewById(R.id.team_name_text_view);
        holder.playedGamesTextView = (TextView) view.findViewById(R.id.played_games_text_view);
        holder.pointsTextView = (TextView) view.findViewById(R.id.points_teams_text_view);
        holder.goalsTextView = (TextView) view.findViewById(R.id.goals_text_view);
        holder.goalsAgainstsTextView = (TextView) view.findViewById(R.id.goals_against_text_view);
        holder.goalsDifferenceTextView = (TextView) view.findViewById(R.id.goals_difference_text_view);
        view.setTag(holder);

        return view;
    }
}

