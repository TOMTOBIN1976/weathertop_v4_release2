package controllers;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

import java.util.HashMap; // import the HashMap class

import utils.StationAnalytics;

import java.util.List;

public class StationCtrl extends Controller {
    public static void index(Long id) {
        Station station = Station.findById(id);
//===================================================================================
// Check if there is any readings (prevent null pointers!), if not just render

        List<Reading> readings = station.readings;
        if (readings.size() == 0) {
            String message = "No entries in the arraylist";
            render("stationlist.html", station);
        }
//====================================================================================

        Logger.info("StationCtrl controller invoked. Station = " + id);

        int lastReadingPressure = StationAnalytics.getlastReadingPressure(station.readings);
        int lastWeathercode = StationAnalytics.getWeatherCode(station.readings);
        double lastReadingTemperature = StationAnalytics.getlastReadingTemperature(station.readings);
        double fahrenheitTemperature = StationAnalytics.celciusToFarenheit((float) lastReadingTemperature);
        float lastReadingWind = StationAnalytics.getlastReadingWind(station.readings);
        String weatherConditions = StationAnalytics.weatherDescription(lastWeathercode);
        float windBeaufort = StationAnalytics.windDescription(lastReadingWind);
        double lastReadingWindChill = StationAnalytics.windChillCalculator(lastReadingTemperature, lastReadingWind);
        float lastReadingWindDirection = StationAnalytics.getlastReadingWindDirection(station.readings);
        String windDirectionCompass = StationAnalytics.windDirectionCompass(lastReadingWindDirection);
        double stationlatitude = station.getLat();
        double stationlongitude = station.getLng();
        double minimumReadingTemperature = StationAnalytics.getMinReadingTemperature(station.readings);
        double maximumReadingTemperature = StationAnalytics.getMaxReadingTemperature(station.readings);
        int minimumReadingPressure = StationAnalytics.getMinReadingPressure(station.readings);
        int maximumReadingPressure = StationAnalytics.getMaxReadingPressure(station.readings);
        float minimumWindSpeed = StationAnalytics.getMinWindSpeed(station.readings);
        float maximumWindSpeed = StationAnalytics.getMaxWindSpeed(station.readings);
        String weatherCodeIcon = StationAnalytics.weatherCodeIcon(lastWeathercode);
        render("stationlist.html", station, lastReadingPressure, lastWeathercode, lastReadingWind, lastReadingTemperature, weatherConditions, windBeaufort, fahrenheitTemperature, lastReadingWindChill, windDirectionCompass, stationlatitude, stationlongitude, minimumReadingTemperature, maximumReadingTemperature, maximumReadingPressure, minimumReadingPressure, minimumWindSpeed, maximumWindSpeed, weatherCodeIcon);
    }

    public static void addReading(Long id, int code, float temperature, float windSpeed, int pressure, int windDirection) {
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/station/" + id);
    }


    public HashMap<String, String> getWindDirection() {
        HashMap<String, String> windDirection = new HashMap<String, String>();
        windDirection.put("N", "348.75 - 11.25");
        windDirection.put("NNE", "11.25 - 33.75");
        windDirection.put("NE", "33.75 - 56.25");
        windDirection.put("ENE", "56.25 - 78.75");
        windDirection.put("E", "78.75 - 101.25");
        windDirection.put("ESE", "101.25 - 123.75");
        windDirection.put("SE", "123.75 - 146.25");
        windDirection.put("S", "168.75 - 191.25");
        windDirection.put("SSW", "191.25 - 213.75");
        windDirection.put("SW", "213.75 - 236.25");
        windDirection.put("WSW", "236.25 - 258.75");
        windDirection.put("W", "258.75 - 281.25");
        windDirection.put("WNW", "281.25 - 303.75");
        windDirection.put("NW", "303.75 - 326.25");
        windDirection.put("NNW", "326.25 - 348.75");
        return windDirection;
    }

}



