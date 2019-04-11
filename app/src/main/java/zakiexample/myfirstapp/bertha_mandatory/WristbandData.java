package zakiexample.myfirstapp.bertha_mandatory;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WristbandData implements Serializable {

    @SerializedName("deviceId") // Name of JSON attribute. Used for GSON de-serialization
    private int deviceId;
    @SerializedName("pm25")
    private double pm25;
    @SerializedName("pm10")
    private double pm10;
    @SerializedName("co2")
    private int co2;
    @SerializedName("o3")
    private int o3;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("humidity")
    private double humidity;

    @SerializedName("utc")
    private long utc;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("noise")
    private long noise;
    @SerializedName("userId")
    private String userId;



    public WristbandData() {
    }

    public WristbandData(int deviceId, double pm25, double pm10, int co2, int o3, double pressure, double temperature, double humidity, long utc, double latitude, double longitude, long noise, String userId) {
        this.deviceId = deviceId;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.co2 = co2;
        this.o3 = o3;
        this.pressure = pressure;
        this.temperature = temperature;
        this.humidity = humidity;
        this.utc = utc;
        this.latitude = latitude;
        this.longitude = longitude;
        this.noise = noise;
        this.userId = userId;
    }

    public int getdeviceId() {
        return deviceId;
    }

    public double getPm25() {
        return pm25;
    }

    public double getPm10() {
        return pm10;
    }

    public int getCo2() {
        return co2;
    }

    public int getO3() { return o3; }

    public double getPressure() { return pressure; }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() { return humidity; }

    public long getUtc() { return utc; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public String getUserId() { return userId; }


    public void setdeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public void setO3(int o3) {
        this.o3 = o3;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setUtc(long utc) { this.utc = utc; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public void setNoise(long noise) { this.noise = noise; }

    public void setUserId(String userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "WRISTBANDDATA" +
                "\n\n DEVICE ID = " + deviceId +
                ",\n PM25 = " + pm25 +
                ",\n PM10 = " + pm10 +
                ",\n CO2 = " + co2 +
                ",\n O3 = " + o3 +
                ",\n PRESSURE = " + pressure +
                ",\n TEMPERATURE = " + temperature +
                ",\n HUMIDITY = " + humidity +
                ",\n UTC = " + utc +
                ",\n LATITUDE = " + latitude +
                ",\n LONGITUDE = " + longitude +
                ",\n NOISE = " + noise +
                ",\n USER ID = " + userId;

    }
}
