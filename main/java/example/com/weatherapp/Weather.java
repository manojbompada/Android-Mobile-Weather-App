/*
    Cole Howell, Manoj Bompada
    Weather.java
    ITCS 4180
 */

package example.com.weatherapp;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by colehowell on 3/2/16.
 */
public class Weather implements Serializable, Comparable<Weather>{

    String time = "";
    String temperature = "";
    String dewPoint = "";
    String clouds = "";
    String iconURL = "";
    String windSpeed = "";
    String windDirection = "";
    String climateType = "";
    String humidity = "";
    String feelsLike = "";
    String maximunTemp = "";
    String minimunTemp = "";
    String pressure = "";
    String degrees = "";

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {

        switch (windDirection){
            case("N"):{
                return windDirection = "North";
            }
            case("S"):{
                return windDirection = "South";
            }
            case("E"):{
                return windDirection = "East";
            }
            case("W"):{
                return windDirection = "West";
            }
            case("NW"):{
                return windDirection = "North West";
            }
            case("NE"):{
                return windDirection = "North East";
            }
            case("SE"):{
                return windDirection = "South East";
            }
            case("SW"):{
                return windDirection = "South West";
            }
            case("SSW"):{
                return windDirection = "South-Southwest";
            }
            case("SSE"):{
                return windDirection = "South-Southeast";
            }
            case("ENE"):{
                return windDirection = "East-Northeast";
            }
            case("ESE"):{
                return windDirection = "East-Southeast";
            }
            case("NNW"):{
                return windDirection = "North-Northwest";
            }
            case("NNE"):{
                return windDirection = "North-Northeast";
            }
            case("WNW"):{
                return windDirection = "West-Northwest";
            }
            case("WSW"):{
                return windDirection = "West-Southwest";
            }
            default:{
                return windDirection;
            }
        }
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getClimateType() {
        return climateType;
    }

    public void setClimateType(String climateType) {
        this.climateType = climateType;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(String feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getMaximunTemp() {
        return maximunTemp;
    }

    public void setMaximunTemp(String maximunTemp) {
        this.maximunTemp = maximunTemp;
    }

    public String getMinimunTemp() {
        return minimunTemp;
    }

    public void setMinimunTemp(String minimunTemp) {
        this.minimunTemp = minimunTemp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", temperature='" + temperature + '\'' +
                ", dewPoint='" + dewPoint + '\'' +
                ", clouds='" + clouds + '\'' +
                ", iconURL='" + iconURL + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", climateType='" + climateType + '\'' +
                ", humidity='" + humidity + '\'' +
                ", feelsLike='" + feelsLike + '\'' +
                ", maximunTemp='" + maximunTemp + '\'' +
                ", minimunTemp='" + minimunTemp + '\'' +
                ", pressure='" + pressure + '\'' +
                '}';
    }

    @Override
    public int compareTo(Weather another) {
        if(Integer.parseInt(this.temperature) > Integer.parseInt(another.temperature)){
            return 1;
        } else{
            return -1;
        }
    }
}
