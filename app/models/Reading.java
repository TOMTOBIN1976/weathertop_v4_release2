package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity

public class Reading extends Model {
    public int code;
    public double temperature;
    public float windSpeed;
    public int pressure;
    public int windDirection;

    public Reading(int code, double temperature, float windSpeed, int pressure, int windDirection) {
        this.code = code;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.windDirection = windDirection;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getWindDirection() {
        return windDirection;
    }

}
