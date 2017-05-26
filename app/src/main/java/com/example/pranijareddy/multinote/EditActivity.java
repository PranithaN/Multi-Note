package com.example.pranijareddy.multinote;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Pranijareddy on 2/19/2017.
 */

public class EditActivity extends AppCompatActivity {
   private EditText titl;
    private EditText desc;
    private Calendar c;
    private SimpleDateFormat df;
    private String formattedDate;
    private int pos ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note);
        titl=(EditText) findViewById(R.id.note_title) ;
        desc=(EditText) findViewById(R.id.description);
        desc.setMovementMethod(new ScrollingMovementMethod());
        desc.setTextIsSelectable(true);
        c = Calendar.getInstance();
        df = new SimpleDateFormat("E MMM dd yyyy, hh:mm aaa ");
        formattedDate = df.format(c.getTime());
        Intent intent = getIntent();
        if (intent.hasExtra("Note")) {
            Note c = (Note) intent.getSerializableExtra("Note");
            titl.setText(c.getTitle());
            desc.setText(c.getDescription());
            pos=c.getId();
           // pos=Integer.parseInt(intent.getStringExtra("position"));
            //titl.setEnabled(false);
        }
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save:
              save();
                finish();
               return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void save(){
        if(titl.getText().toString().equalsIgnoreCase("")){}
        else{
        c = Calendar.getInstance();
        formattedDate = df.format(c.getTime());
        Intent data = new Intent();
            data.putExtra("id",pos);
        data.putExtra("Title", titl.getText().toString());
        data.putExtra("Description",desc.getText().toString());
        data.putExtra("Date",formattedDate.toString());
          //  data.putExtra("position",pos);
        setResult(EditActivity.RESULT_OK, data);}
    }

    @Override
    public void onBackPressed(){
       // super.onBackPressed();
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id) {
                save();
                finish();
            }
        });
        build.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        build.setMessage("Save this note "+titl.getText()+"?");
        build.setTitle("Note is not saved!!!");
        AlertDialog dia = build.create();
        dia.show();

        //super.onBackPressed();
    }
    @Override
    public void onPause(){
        super.onPause();
       // save();
    }
@Override
    public void onResume() {
        super.onResume();
    }
}
