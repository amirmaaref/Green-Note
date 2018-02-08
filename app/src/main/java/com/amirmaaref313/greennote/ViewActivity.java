package com.amirmaaref313.greennote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import DataConnection.SimpleActions;
import ImageHandler.Movaghat;

public class ViewActivity extends AppCompatActivity {

    FloatingActionButton delete;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView) findViewById(R.id.VImageView);

        textView = (TextView) findViewById(R.id.viewTextView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.editFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEditActivity();
            }
        });

        delete = (FloatingActionButton) findViewById(R.id.deleteFabButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        setTitle(Movaghat.getTitle());
        textView.setText(Movaghat.getText());
        imageView.setImageBitmap(Movaghat.getBitmap());
    }

    @Override
    public void onResume(){
        super.onResume();
        imageView.setImageBitmap(Movaghat.getBitmap());
        setTitle(Movaghat.getTitle());
        textView.setText(Movaghat.getText());
    }
    public void startEditActivity(){
        startActivity(new Intent(this , EditActivity.class));
    }

    private void deleteData(){
        SimpleActions.Delete(this , Movaghat.getNote());
        super.onBackPressed();
    }
}
