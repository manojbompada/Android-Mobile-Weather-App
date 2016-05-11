/*
    Cole Howell, Manoj Bompada
    MainActivity.java
    ITCS 4180
 */

package example.com.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String CITYLIST = "citylist";
    public static final String CITYNAME = "cityname";
    public static final String STATE = "state";
    String city,state;
    ArrayList<City> citiesList = new ArrayList<City>();
    ListView lv;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Homework 5");

        if(getIntent().getExtras()!= null){
            if(getIntent().getExtras().containsKey(HourlyDataActivty.HOURKEY)){
                city = (String) getIntent().getExtras().getString(HourlyDataActivty.HOURCTY);
                state = (String) getIntent().getExtras().getString(HourlyDataActivty.HOURST);
                citiesList = (ArrayList<City>) getIntent().getExtras().getSerializable(HourlyDataActivty.CTYLST);
            }
            else{
                city = (String) getIntent().getExtras().getString(AddCityActivity.CITY);
                state = (String) getIntent().getExtras().getString(AddCityActivity.STATE);
                citiesList = (ArrayList<City>) getIntent().getExtras().getSerializable(AddCityActivity.CLST);
                City city_mem = new City();
                city_mem.setCityName(city);
                city_mem.setState(state);
                citiesList.add(city_mem);

            }
        }

        lv = (ListView) findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView);

        if(citiesList.size() == 0 )
        {
            textView.setText("There are no cities. Add using the '+' button from the menu.");
        }
        else
        {
            final CityAdapter adapter = new CityAdapter(MainActivity.this,R.layout.city_layout,citiesList);
            lv.setAdapter(adapter);
            adapter.setNotifyOnChange(true);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("demo", "Position : " + position + " value: " + citiesList.get(position).toString());

                    Intent intent = new Intent(MainActivity.this, HourlyDataActivty.class);
                    intent.putExtra(CITYNAME, citiesList.get(position).getCityName());
                    intent.putExtra(STATE, citiesList.get(position).getState());
                    intent.putExtra(CITYLIST, citiesList);
                    startActivity(intent);
                }
            });

            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    citiesList.remove(position);
                    if(citiesList.size() == 0 )
                    {
                        textView.setText("There are no cities. Add using the '+' button from the menu.");

                    }
                    adapter.notifyDataSetChanged();
                    return true;
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                Intent intent = new Intent(MainActivity.this, AddCityActivity.class);
                intent.putExtra(CITYLIST, citiesList);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
