package com.example.tmt.showorhide;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ListViewAdapter extends ArrayAdapter {

    private Activity activity;

    public ListViewAdapter(Activity activity, int resource, String[] objects) {
        super(activity, resource, objects);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

        //load image from url by Picasso library
        Picasso.with(activity).load(String.valueOf(getItem(position))).into(holder.page);

        return convertView;
    }

    private class ViewHolder {
        private ImageView page;

        public ViewHolder(View v) {
            page = (ImageView) v.findViewById(R.id.image);
        }
    }
}