package com.amirmaaref313.greennote;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import DataConnection.Note;
import DataConnection.SimpleActions;
import ImageHandler.Movaghat;

public class EditActivity extends AppCompatActivity {

    EditText t1,t2;
    ImageView imageView;
    boolean isImageChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        t1 = (EditText) findViewById(R.id.editText4);
        t2 = (EditText) findViewById(R.id.editText5);
        imageView = (ImageView)findViewById(R.id.EditImageView);
        setTitle("Edit Note..."+ Movaghat.getTitle());
        imageView.setImageBitmap(Movaghat.getBitmap());
        t1.setText(Movaghat.getTitle());
        t2.setText(Movaghat.getText());
        isImageChange = false;
    }

    public void saveClick(View view){
        if(isImageChange){
            Calendar c = Calendar.getInstance();
            String sDate = String.valueOf(c.get(Calendar.YEAR))
                    + String.valueOf(c.get(Calendar.MONTH))
                    + String.valueOf(c.get(Calendar.DAY_OF_MONTH))
                    + String.valueOf(c.get(Calendar.HOUR_OF_DAY))
                    + String.valueOf(c.get(Calendar.MINUTE))
                    + String.valueOf(c.get(Calendar.SECOND));
            String SavedImage = saveToInternalStorage(((BitmapDrawable) imageView.getDrawable()).getBitmap(), sDate);

            Note nn = new Note(Movaghat.getID(),String.valueOf(t1.getText())
            ,String.valueOf(t2.getText()),
                    SavedImage);

            Movaghat.setNote(nn);
            setTitle("Editing...Please Wait...");
            SimpleActions.Update(this,nn);
            setTitle("Edit Note...");
            deleteImgBytId(Movaghat.getImgIdForDelete());
            super.onBackPressed();
        }
        else
        {
            Note nn = new Note(Movaghat.getID(),String.valueOf(t1.getText())
                    ,String.valueOf(t2.getText()),
                    Movaghat.getIMG());

            Movaghat.setNote(nn);
            setTitle("Editing...Please Wait...");
            SimpleActions.Update(this,nn);
            setTitle("Edit Note...");
            super.onBackPressed();
        }
    }

    public void imageClick(View view){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        int RESULT_LOAD_IMG=0;
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
        isImageChange = true;
    }

    private String saveToInternalStorage(Bitmap bitmapImage, String FileName) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, FileName + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Movaghat.setBitmap(bitmapImage);
        return FileName;
    }

    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
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
