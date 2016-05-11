/*
    Cole Howell, Manoj Bompada
    CityAdapter.java
    ITCS 4180
 */

package example.com.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Manoj on 3/5/2016.
 */
public class CityAdapter extends ArrayAdapter {

    Context mContext;
    int mResource;
    ArrayList<City> mObjects;

    public CityAdapter(Context context, int resource, ArrayList objects) {

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
            holder.city = (TextView) convertView.findViewById(R.id.cityname);

            convertView.setTag(holder);
        }
        holder = (Holder) convertView.getTag();
        TextView city = holder.city;
        city.setText(mObjects.get(position).getCityName() + ", " + mObjects.get(position).getState());
        return convertView;
    }

    private class Holder {
        TextView city;
    }
}
