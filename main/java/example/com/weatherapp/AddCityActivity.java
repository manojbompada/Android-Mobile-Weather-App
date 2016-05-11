/*
    Cole Howell, Manoj Bompada
    AddCityActivity.java
    ITCS 4180
 */

package example.com.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;

public class AddCityActivity extends AppCompatActivity {
    public static final String CLST ="clst" ;
    ArrayList<City> clst = new ArrayList<City>();
    public static final String CITY ="city" ;
    public static final String STATE ="state" ;
    TextView city,state;
    InputStream in;
    ArrayList<String> lst;
    ArrayList<String> citylst= new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        setTitle("Add city Activity");

        city = (TextView) findViewById(R.id.city);
        state = (TextView) findViewById(R.id.state);
        Button saveCity = (Button) findViewById(R.id.saveCity);

        new GetCityAsyncTask().execute(R.raw.city);

        if(getIntent().getExtras()!= null){
            clst = (ArrayList<City>) getIntent().getExtras().getSerializable(MainActivity.CITYLIST);
        }

        city.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });

        state.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });

        saveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean citymatch = false;

                String cty = city.getText().toString();
                String st = state.getText().toString();
                String match = cty + "," + st;

                for (String x : citylst) {

                    if (x.equals(match)) {
                        Log.d("demo", x + " matching  " + match);

                        citymatch = true;
                    }

                }

                if(citymatch == true){
                    Intent intent = new Intent(AddCityActivity.this, MainActivity.class);
                    intent.putExtra(CITY, city.getText().toString());
                    intent.putExtra(STATE, state.getText().toString());
                    intent.putExtra(CLST, clst);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(AddCityActivity.this,"Please enter correct city/state",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    class GetCityAsyncTask extends AsyncTask<Integer, Void, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            citylst = result;

        }

        @Override
        protected ArrayList<String> doInBackground(Integer... params) {
            in = AddCityActivity.this.getResources().openRawResource(params[0]);
            BufferedReader br= null;
            try {
                br = new BufferedReader(new InputStreamReader(in));
                String line =  null;
                lst = new ArrayList<String>();
                while((line=br.readLine())!=null){
                    lst.add(line);
                }
                return lst;

            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }

}
