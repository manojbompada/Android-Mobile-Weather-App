/*
    Cole Howell, Manoj Bompada
    HourlyDataActivity.java
    ITCS 4180
 */

package example.com.weatherapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HourlyDataActivty extends AppCompatActivity {
    public static final String WEATHERLST ="wthrlst" ;
    public static final String POSITION ="pos" ;
    public static final String HOURCTY ="city" ;
    public static final String HOURST ="state" ;
    public static final String CTYLST ="ctylist" ;
    public static final String HOURKEY ="hourkey" ;
    String city,state;
    ProgressDialog progressDialog;
    ListView weatherlv;
    TextView ctytxt;
    ArrayList<City> ctylst = new ArrayList<City>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_data_activty);

        weatherlv = (ListView) findViewById(R.id.weatherlistView);
        ctytxt = (TextView) findViewById(R.id.locationtxt);

        if(getIntent().getExtras()!= null){
           city = (String) getIntent().getExtras().getString(MainActivity.CITYNAME);
           state = (String) getIntent().getExtras().getString(MainActivity.STATE);
           ctylst = (ArrayList<City>) getIntent().getExtras().getSerializable(MainActivity.CITYLIST);

        }

        String mcity = city.replaceAll(" ","_");

        String API_URL = "http://api.wunderground.com/api/041713a953f1014f/hourly/q/"+state+"/"+mcity+".xml";
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(API_URL );
        if(m.matches()){
            Log.d("demo","URL MATCHES");
            new GetWeatherAsyncTask().execute(API_URL);

        }
        else{
            Toast.makeText(HourlyDataActivty.this,"no cities match your query",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(HourlyDataActivty.this, MainActivity.class);
            intent.putExtra(CTYLST, ctylst);
            intent.putExtra(HOURCTY,city);
            intent.putExtra(HOURST,state);
            intent.putExtra(HOURKEY,"hrkey");
            startActivity(intent);
            finish();

        }


    }

    class GetWeatherAsyncTask extends AsyncTask<String, Void, ArrayList<Weather>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(HourlyDataActivty.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading Hourly Data");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<Weather> result) {
            super.onPostExecute(result);
            final ArrayList<Weather> weatherlst = result;
            if (result != null) {
                progressDialog.dismiss();
                ctytxt.setText(city + ", " + state);

                //grab maximum and minimum temperatures
                String maxTemp = (Collections.max(weatherlst)).getTemperature();
                String minTemp = (Collections.min(weatherlst)).getTemperature();

                for(Weather x : result){
                    Log.d("demo",x.getClimateType()+", "+x.getTemperature()+", "+x.getTime()+","+ x.getDewPoint()+","+x.getFeelsLike()
                    +","+x.getPressure());

                    x.setMinimunTemp(minTemp);
                    x.setMaximunTemp(maxTemp);
                }

                WeatherAdapter adapter = new WeatherAdapter(HourlyDataActivty.this,R.layout.weather_customlistview,result);
                weatherlv.setAdapter(adapter);
                adapter.setNotifyOnChange(true);

                weatherlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("demo", "Position : " + position + " value: " + weatherlst.get(position).toString());

                        Intent intent = new Intent(HourlyDataActivty.this, DetailsActivity.class);
                        intent.putExtra(WEATHERLST, weatherlst);
                        intent.putExtra(POSITION, position);
                        intent.putExtra(HOURCTY,city);
                        intent.putExtra(HOURST,state);
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        protected ArrayList<Weather> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int statusCode = connection.getResponseCode();
                if (statusCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();

                    return WeatherParser.WeatherPullParser.parseWeather(inputStream);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}


