package ImageHandler;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amirmaaref313.greennote.R;

/**
 * Created by amirhossein on 2/2/18.
 */

public class CustomListLoader extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final Bitmap[] imgid;

    public CustomListLoader(Activity context, String[] itemname, Bitmap[] bitmaps) {
        super(context, R.layout.list_row, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=bitmaps;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_row, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.LTextView);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.LImageView);

        txtTitle.setText(itemname[position]);
        imageView.setImageBitmap(imgid[position]);
        return rowView;

    };
}
