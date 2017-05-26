package com.example.pranijareddy.multinote;

import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Pranijareddy on 2/20/2017.
 */

public class AsyncNoteLoader extends AsyncTask<String, String, String> {
    private MainActivity mainActivity;
    private int count;
    ArrayList<JSONObject> json=new ArrayList<JSONObject>();
    JSONObject obj;
    public AsyncNoteLoader(MainActivity ma) {
        mainActivity = ma;
    }
    @Override
    protected void onPreExecute() {
        Toast.makeText(mainActivity, "Loading Data...", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPostExecute(String s) {
      ArrayList<Note> NoteList = parseJSON(s);
        mainActivity.onResult(NoteList);
    }
    @Override
    protected String doInBackground(String... params) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream in = mainActivity.getApplicationContext().openFileInput("note.json");
            InputStreamReader is = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(is);
            String line=null;
            ArrayList<JSONObject> contentsAsJsonObjects = new ArrayList<JSONObject>();
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString();
        }
    private ArrayList<Note> parseJSON(String s) {
        ArrayList<Note> NoteList = new ArrayList<>();
        try {
            JSONArray jObjMain = new JSONArray(s);
            for (int i = 0; i < jObjMain.length(); i++) {
                JSONObject jCountry = (JSONObject) jObjMain.get(i);
                int id=jCountry.getInt("id");
                String title = jCountry.getString("title");
                String date = jCountry.getString("date");
                String desc = jCountry.getString("description");
                NoteList.add(new Note(title,date,desc));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NoteList;
    }
}
