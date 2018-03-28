package com.amirmaaref313.greennote;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        Movaghat.setImgIdForDelete(Movaghat.getNote().getImg());
    }
    private void deleteData(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
// Add the buttons
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                deleteNote();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });
                builder.setIcon(R.drawable.ic_delete_forever_black_24dp);
                builder.setMessage("Delete This Note?");
                builder.show();
    }
    private void deleteNote(){
        deleteImgBytId(Movaghat.getNote().getImg());
        SimpleActions.Delete(this , Movaghat.getNote());
        super.onBackPressed();
    }
    private void deleteImgBytId(String imgId){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, imgId + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            mypath.delete();
           // bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
