package com.example.matthewkay.gitreference;

import android.content.Context;
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


    public JAdapter(Context context, ArrayList<String> commands){
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
        View rowView = mInflater.inflate(R.layout.custom_row_view, viewGroup, false);
        Log.i("HELL", "JEsadasdEJ");
        TextView commandView = rowView.findViewById(R.id.command);
        TextView exampleView = rowView.findViewById(R.id.example);
        TextView explanationView = rowView.findViewById(R.id.explanation);
        TextView sectionView = rowView.findViewById(R.id.section);
        Log.i("test", "broke84");

        GitReference command = (GitReference) getItem(i);
        Log.i("test", "broke8" );
        commandView.setText(command.getCommand());
        Log.i("test", "broke9" );
        exampleView.setText(command.getExample());
        Log.i("test", "broke99" );
        explanationView.setText(command.getExplanation());
        Log.i("test", "broke90" );
        sectionView.setText(command.getSection());


        return rowView;
    }
}
