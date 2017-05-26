package com.example.pranijareddy.multinote;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Pranijareddy on 2/26/2017.
 */

public class About extends AppCompatActivity {
    private TextView textView;
    private  TextView textView2 ;
    private   TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3= (TextView) findViewById(R.id.textView3);
        Intent intent = getIntent();
        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            //String text = intent.getStringExtra(Intent.EXTRA_TEXT);

        }

    }
    @Override
    public void onPause()
    {
        super.onPause();

    }
}
