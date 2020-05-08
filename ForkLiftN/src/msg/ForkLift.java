package msg;



import java.io.Serializable;



public class ForkLift implements Serializable{



    private static final long serialVersionUID = 1L;



    int status;

    int locX;

    int locY;

    int battery;

    int temperature;

    int distance;



    public ForkLift() {}



    public ForkLift(int status, int locX, int locY, int battery, int temperature, int distance) {

        super();

        this.status = status;

        this.locX = locX;

        this.locY = locY;

        this.battery = battery;

        this.temperature = temperature;

        this.distance = distance;

    }



    public int getStatus() {

        return status;

    }





    public void setStatus(int status) {

        this.status = status;

    }





    public int getBattery() {

        return battery;

    }





    public void setBattery(int battery) {

        this.battery = battery;

    }





    public int getLocX() {

        return locX;

    }





    public void setLocX(int locX) {

        this.locX = locX;

    }





    public int getLocY() {

        return locY;

    }





    public void setLocY(int locY) {

        this.locY = locY;

    }



    public int getTemperature() {

        return temperature;

    }



    public void setTemperature(int temperature) {

        this.temperature = temperature;

    }



    public int getDistance() {

        return distance;

    }



    public void setDistance(int distance) {

        this.distance = distance;

    }

    @Override

    public String toString() {

        return "ItemMapper [status=" + status + ", locX=" + locX + ", locY=" + locY + ", battery="

                + battery + ", temperature=" + temperature + ", distance=" + distance + "]";

    }

}