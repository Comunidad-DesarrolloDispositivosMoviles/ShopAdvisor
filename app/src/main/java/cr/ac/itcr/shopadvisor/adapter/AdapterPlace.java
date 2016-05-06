package cr.ac.itcr.shopadvisor.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cr.ac.itcr.shopadvisor.R;
import cr.ac.itcr.shopadvisor.entity.Place;

/**
 * Created by Efren on 15/3/2016.
 */
public class AdapterPlace extends BaseAdapter {

    // Declare Variables
    Context context;
    String[] titulos;
    int[] imagenes;
    LayoutInflater inflater;

        protected Activity activity;
        protected ArrayList<Place> items;
    public AdapterPlace (Context context, ArrayList<Place> items) {
        this.context = context;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Place> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Declare Variables
        TextView txtTitle;
        ImageView imgImg;

        //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.item_place, parent, false);

        // Locate the TextViews in listview_item.xml
        txtTitle = (TextView) itemView.findViewById(R.id.tituloLista);
        imgImg = (ImageView) itemView.findViewById(R.id.iconLista);

        // Capture position and set to the TextViews
        txtTitle.setText(items.get(position).getName());

      /*  Picasso.with(itemView.getContext())
                .load("http://3.bp.blogspot.com/-JHKA0FV3pug/Vh_jEN6M0FI/AAAAAAAAL7g/i_qp76G644M/s1600/download-button.png")
                .resize(200, 150)
                .into(imgImg);

        imgImg.setImageResource(imagenes[0]);*/

        return itemView;
    }
}
