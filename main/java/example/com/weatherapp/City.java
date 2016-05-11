/*
    Cole Howell, Manoj Bompada
    City.java
    ITCS 4180
 */

package example.com.weatherapp;

import java.io.Serializable;

/**
 * Created by colehowell on 3/2/16.
 */
public class City implements Serializable {

    String cityName, state;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "city{" +
                "cityName='" + cityName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
