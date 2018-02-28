package com.example.matthewkay.gitreference;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MatthewKay on 2/25/18.
 */

public class JAdapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList mDataSource;




    public JAdapter(Context context, ArrayList<GitReference> commands){
        mContext = context;
        mDataSource = commands;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(R.layout.custom_row_view, viewGroup, false);

        TextView commandView = rowView.findViewById(R.id.command);
        TextView exampleView = rowView.findViewById(R.id.example);
        TextView explanationView = rowView.findViewById(R.id.explanation);
        TextView sectionView = rowView.findViewById(R.id.section);



        GitReference command = (GitReference) mDataSource.get(position);


        commandView.setText(mContext.getResources().getString(R.string.commandtag) +command.getCommand());

        exampleView.setText(mContext.getResources().getString(R.string.exampletag)+ command.getExample());

        explanationView.setText(mContext.getResources().getString(R.string.explanationtag)+ command.getExplanation());

        sectionView.setText(mContext.getResources().getString(R.string.sectiontag)+ command.getSection());

        return rowView;
    }


}
