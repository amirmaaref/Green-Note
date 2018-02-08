package DataConnection;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by amirhossein on 1/29/18.
 */
@Entity
public class Note {

    public Note(){

    }

    public Note(String title,String text,String img){
        setText(text);
        setTitle(title);
        setImg(img);
    }

    public Note(String title,String text){
        setText(text);
        setTitle(title);
        setImg("");
    }

    public Note(int nid, String title, String text, String img) {
        this.nid = nid;
        this.title = title;
        this.text = text;
        this.img = img;
    }

    @PrimaryKey(autoGenerate = true)
    private int nid;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo
    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    // but they're required for Room to work.
    public void setTitle(String title1){
        this.title=title1;
    }
    public void setText(String text1){
        this.text=text1;
    }
    public void setNid(int nid1){nid=nid1;};

    public String getTitle(){
        return title;
    }
    public String getText(){
        return text;
    }
    public int getNid(){
        return nid;
    }
}
