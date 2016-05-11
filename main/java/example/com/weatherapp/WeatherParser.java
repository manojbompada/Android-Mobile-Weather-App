/*
    Cole Howell, Manoj Bompada
    WeatherParser.java
    ITCS 4180
 */

package example.com.weatherapp;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by colehowell on 3/5/16.
 */
public class WeatherParser {

    static public class WeatherPullParser{

        static ArrayList<Weather> parseWeather (InputStream in) throws XmlPullParserException, IOException {

            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            Weather weather = null;
            ArrayList<Weather> weatherList = new ArrayList<Weather>();
            int event = parser.getEventType();
            boolean tempflag = false;
            boolean dewpointflag = false;
            boolean wspdflag = false;
            boolean wdirflag = false;
            boolean feelslikeflag = false;
            boolean mslpflag = false;

            while (event != XmlPullParser.END_DOCUMENT){
                switch (event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("forecast")){
                            weather = new Weather();
                        }
                        else if (parser.getName().equals("civil")){
                            weather.setTime(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("temp")){
                            tempflag = true;
                        }
                        else if (parser.getName().equals("dewpoint")){
                            dewpointflag = true;
                        }
                        else if (parser.getName().equals("condition")){
                            weather.setClouds(parser.nextText().trim());
                        }else if (parser.getName().equals("icon_url")){
                            weather.setIconURL(parser.nextText().trim());
                        }else if (parser.getName().equals("wspd")){
                            wspdflag = true;
                        }
                        else if (parser.getName().equals("wdir")){
                            wdirflag = true;
                        }
                        else if (parser.getName().equals("wx")){
                            weather.setClimateType(parser.nextText().trim());
                        }
                        else if (parser.getName().equals("humidity")){
                            weather.setHumidity(parser.nextText().trim());
                        }

                        else if (parser.getName().equals("mslp")){
                            mslpflag = true;
                        }
                        else if (parser.getName().equals("feelslike")){
                            feelslikeflag = true;
                        }
                        else if(parser.getName().equals("english")){

                            if(tempflag == true){
                                tempflag = false;
                                weather.setTemperature(parser.nextText().trim());
                            }
                            if(dewpointflag == true){
                                dewpointflag = false;
                                weather.setDewPoint(parser.nextText().trim());
                            }
                            if(wspdflag == true){
                                wspdflag = false;
                                weather.setWindSpeed(parser.nextText().trim());
                            }

                            if(feelslikeflag == true){

                                feelslikeflag = false;
                                weather.setFeelsLike(parser.nextText().trim());
                            }
                        }
                        else if(parser.getName().equals("dir")) {
                            if(wdirflag == true){
                                weather.setWindDirection(parser.nextText().trim());
                            }
                        }
                        else if(parser.getName().equals("metric")) {
                            if(mslpflag == true){
                                weather.setPressure(parser.nextText().trim());
                            }
                        }
                        else if(parser.getName().equals("degrees")) {
                                weather.setDegrees(parser.nextText().trim());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("forecast")){
                            tempflag = false;
                            dewpointflag = false;
                            wspdflag = false;
                            wdirflag = false;
                            feelslikeflag = false;
                            mslpflag = false;

                            weatherList.add(weather);
                        }
                        break;
                    default:
                        break;
                }

                event = parser.next();
            }

            return weatherList;
        }
    }
}
