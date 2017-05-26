package com.example.pranijareddy.multinote;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Pranijareddy on 2/19/2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Note> noteList;
    private MainActivity mainAct;

    public NotesAdapter(List<Note> nList, MainActivity ma) {
        this.noteList = nList;
        mainAct = ma;
    }
    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyle_note, parent, false);

        itemView.setOnClickListener(mainAct);
        itemView.setOnLongClickListener(mainAct);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = noteList.get(position);

        String shortdes=note.getDescription();
        holder.title.setText(note.getTitle());
        holder.date.setText(note.getDate());
        if(shortdes.length()>80){
        holder.description.setText(shortdes.substring(0,80).concat(" ..."));}
        else{holder.description.setText(shortdes);}
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}
