/*
    Cole Howell, Manoj Bompada
    WeatherAdapter.java
    ITCS 4180
 */

package example.com.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Manoj on 3/6/2016.
 */
public class WeatherAdapter extends ArrayAdapter {
    Context mContext;
    int mResource;
    ArrayList<Weather> mObjects;

    public WeatherAdapter(Context context, int resource, ArrayList objects) {

        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mObjects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new Holder();
            holder.time = (TextView) convertView.findViewById(R.id.time1);
            holder.cond = (TextView) convertView.findViewById(R.id.condition);
            holder.temp = (TextView) convertView.findViewById(R.id.temptxt);
            holder.txtimage = (ImageView) convertView.findViewById(R.id.adapterimageView);

            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        TextView time = holder.time;
        TextView cond = holder.cond;
        TextView temp = holder.temp;
        ImageView txtimage = holder.txtimage;
        time.setText(mObjects.get(position).getTime());
        cond.setText(mObjects.get(position).getClimateType());
        temp.setText(mObjects.get(position).getTemperature()+" F");

        Picasso.with(mContext).load(mObjects.get(position).getIconURL()).into(txtimage);
        return convertView;
    }

    private class Holder {
        TextView time;
        TextView cond;
        TextView temp;
        ImageView txtimage;

    }
}
