/*
    Cole Howell, Manoj Bompada
    DetailsActivity.java
    ITCS 4180
 */

package example.com.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    ArrayList<Weather> weatherArrayList = new ArrayList<Weather>();
    ImageView txtimage;
    TextView place;
    int position;
    String city;
    String state;
    TextView temp;
    TextView weathertype,feel,humid,dew,pressure,cloud,wind,maxtemp,mintemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txtimage = (ImageView) findViewById(R.id.weatherimage);
        place = (TextView) findViewById(R.id.placetxt);
        temp = (TextView) findViewById(R.id.tempdet);
        weathertype = (TextView) findViewById(R.id.weathertype);
        feel = (TextView) findViewById(R.id.feeltxt);
        humid = (TextView) findViewById(R.id.humidtxt);
        dew = (TextView) findViewById(R.id.dewtxt);
        pressure = (TextView) findViewById(R.id.presstxt);
        cloud = (TextView) findViewById(R.id.cloudtxt);
        wind = (TextView) findViewById(R.id.windtxt);
        maxtemp = (TextView) findViewById(R.id.maxtemptxt);
        mintemp = (TextView) findViewById(R.id.mintemptxt);

        if(getIntent().getExtras()!= null){

            weatherArrayList = (ArrayList<Weather>) getIntent().getExtras().getSerializable(HourlyDataActivty.WEATHERLST);
            position = getIntent().getExtras().getInt(HourlyDataActivty.POSITION);
            city = (String) getIntent().getExtras().getString(HourlyDataActivty.HOURCTY);
            state = (String) getIntent().getExtras().getString(HourlyDataActivty.HOURST);
        }

        setView();

        findViewById(R.id.imageButtonprev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position >0) {
                    position = position - 1;
                    setView();
                } else {
                    Toast.makeText(DetailsActivity.this, "First record", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.imageButtonnxt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position < (weatherArrayList.size()-1)){
                    position = position+1;
                    setView();
                }
                else{
                    Toast.makeText(DetailsActivity.this,"Last record",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void setView(){
        Picasso.with(DetailsActivity.this).load(weatherArrayList.get(position).getIconURL()).into(txtimage);
        place.setText(city + ", " + state + " " + "(" + weatherArrayList.get(position).getTime() + ")");
        temp.setText(weatherArrayList.get(position).getTemperature() + " °F");
        weathertype.setText(weatherArrayList.get(position).getClimateType());
        feel.setText(weatherArrayList.get(position).getFeelsLike() + " Fahrenheit");
        humid.setText(weatherArrayList.get(position).getHumidity() + "%");
        dew.setText(weatherArrayList.get(position).getDewPoint() + " Fahrenheit");
        pressure.setText(weatherArrayList.get(position).getPressure() + " hPa");
        cloud.setText(weatherArrayList.get(position).getClouds());
        wind.setText(weatherArrayList.get(position).getWindSpeed() + " mph, " + weatherArrayList.get(position).getDegrees() + "° " + weatherArrayList.get(position).getWindDirection());
        maxtemp.setText(weatherArrayList.get(position).getMaximunTemp() +" Fahrenheit");
        mintemp.setText(weatherArrayList.get(position).getMinimunTemp() +" Fahrenheit");
    }
}
