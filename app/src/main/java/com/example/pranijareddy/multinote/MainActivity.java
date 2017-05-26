package com.example.pranijareddy.multinote;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonWriter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener   {
    private static final int AddNote = 1;
    private static final int UpdateNote = 2;
    private Note note;
    private int id;
    private boolean activitySwitchFlag;
    private List<Note> noteList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotesAdapter nAdapter;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        nAdapter = new NotesAdapter(noteList, this);
        recyclerView.setAdapter(nAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new AsyncNoteLoader(MainActivity.this).execute();
      activitySwitchFlag=true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }
    @Override
    public void onClick(View v) {
        activitySwitchFlag=true;
        int pos = recyclerView.getChildLayoutPosition(v);
        Note n = noteList.get(pos);
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra("Note",n);
        //intent.putExtra("position", pos);
        startActivityForResult(intent, UpdateNote);
    }


    @Override
    public boolean onLongClick(final View v) {
        activitySwitchFlag=true;
        final int pos = recyclerView.getChildLayoutPosition(v);
        Note c = noteList.get(pos);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                noteList.remove(pos);
                nAdapter.notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Deleted ", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        builder.setMessage("Delete Note " + noteList.get(pos).getTitle() + "?");
        builder.setTitle("Delete Note");
        AlertDialog dialog = builder.create();
        dialog.show();
        activitySwitchFlag=false;
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
               //activitySwitchFlag=true;
                Intent inten = new Intent(MainActivity.this, About.class);
                inten.putExtra(Intent.EXTRA_TEXT, MainActivity.class);
                startActivity(inten);
                //activitySwitchFlag=false;
                return true;
            case R.id.addNote:
                activitySwitchFlag = true;
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, MainActivity.class.getSimpleName());
                startActivityForResult(intent, AddNote);
                //activitySwitchFlag=false;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AddNote) {
            if (resultCode == RESULT_OK) {

                String title = data.getStringExtra("Title");
                String desc = data.getStringExtra("Description");
                String date = data.getStringExtra("Date");
                int id=data.getIntExtra("id",0);
                note = new Note(title, date, desc);
                note.setTitle(title.toString());
                note.setDate(date.toString());
                note.setDescription(desc.toString());
                noteList.add(note);
                nAdapter.notifyDataSetChanged();
            } else {
            }

        } else {

        }
        if (requestCode == UpdateNote) {
            if (resultCode == RESULT_OK) {
                if (resultCode == RESULT_OK) {
                    String title = data.getStringExtra("Title");
                    String desc = data.getStringExtra("Description");
                    String date = data.getStringExtra("Date");
                    int id=data.getIntExtra("id",0);
                    if(title!=null){
                    note = new Note(id,title, date, desc);
                    note.setTitle(title.toString());
                    note.setDate(date.toString());
                            noteList.remove(id);
                noteList.add(note);
                nAdapter.notifyDataSetChanged();}
            } else {
            }

        } else {

        }
    }}

    @Override
    protected void onPause() {
        super.onPause();
        //super.onKeyDown(keyCode, event);
        if(activitySwitchFlag==false){
            saveNote();
            //flag=false;
            }

    }

    private void saveNote() {
        try {
            FileOutputStream fos = getApplicationContext().openFileOutput(getString(R.string.file_name), Context.MODE_PRIVATE);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(fos, getString(R.string.encoding)));
            writer.beginArray();
            for(Note note: noteList) {
    writer.beginObject();
                writer.name("id");
                writer.value(note.getId());
    writer.name("title");
                writer.value(note.getTitle());
    writer.name("date");
                writer.value(note.getDate());
    writer.name("description");
                writer.value(note.getDescription());
    writer.endObject();
}
           writer.endArray();
            writer.close();
            Toast.makeText(this, getString(R.string.saved), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
       // nAdapter.notify();
if(noteList.isEmpty()){
            //new AsyncNoteLoader(MainActivity.this).execute();
     }

else{activitySwitchFlag=false;}
    }
    public void onResult(ArrayList<Note> nList) {
        noteList.addAll(nList);
        if (noteList.isEmpty()){ Toast.makeText(this, getString(R.string.no_Data), Toast.LENGTH_SHORT).show();}
        nAdapter.notifyDataSetChanged();
    }



}
