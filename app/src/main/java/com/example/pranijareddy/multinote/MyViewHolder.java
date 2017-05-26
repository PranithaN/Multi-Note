package com.example.pranijareddy.multinote;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Pranijareddy on 2/19/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView date;
    public TextView description;

    public MyViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.note_title);
        date= (TextView) view.findViewById(R.id.savedate);
        description = (TextView) view.findViewById(R.id.shortdesc);
    }
}
