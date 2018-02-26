package com.example.matthewkay.gitreference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MatthewKay on 2/25/18.
 */

public class Adapter extends BaseAdapter{

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList mDataSource;

    public Adapter(Context context, ArrayList<GitReference> commands){
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = mInflater.inflate(android.R.layout.simple_expandable_list_item_2, viewGroup, false);

        TextView commandView = rowView.findViewById(android.R.id.text1);
        TextView exampleView = rowView.findViewById(android.R.id.text2);
        //TextView explanationView = rowView.findViewById(android.R.id.text3);
        //TextView sectionView = rowView.findViewById(android.R.id.text4);


        GitReference command = (GitReference) getItem(position);
        commandView.setText(command.getCommand());
        exampleView.setText(command.getExample());
        //explanationView.setText(command.getExplanation());
        //sectionView.setText(command.getSection());



        return rowView;
    }
}
