package ImageHandler;

import android.graphics.Bitmap;

import DataConnection.Note;

/**
 * Created by amirhossein on 2/2/18.
 */

public class Movaghat {
    private static Bitmap bitmap;
    private static Note note;
    private static String imgIdForDelete;

    public static String getImgIdForDelete() {
        return imgIdForDelete;
    }

    public static void setImgIdForDelete(String imgIdForDelete) {
        Movaghat.imgIdForDelete = imgIdForDelete;
    }

    public static void setNote(Note note) {
        Movaghat.note = note;
    }

    public static Note getNote() {
        return note;
    }


    public static void setTitle(String title) {
        note.setTitle(title);
    }

    public static void setText(String text) {
        note.setText(text);
    }

    public static void setBitmap(Bitmap bitmap) {
        Movaghat.bitmap = bitmap;
    }

    public static void setIMG(String img){
        note.setImg(img);
    }

    public static String getIMG(){
        return note.getImg();
    }

    public static String getTitle() {

        return note.getTitle();
    }

    public static String getText() {
        return note.getText();
    }

    public static Bitmap getBitmap() {
        return bitmap;
    }

    public static int getID(){
        return note.getNid();
    }
}
