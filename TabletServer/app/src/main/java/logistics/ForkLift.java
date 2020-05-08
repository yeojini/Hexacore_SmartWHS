package logistics;

import java.io.Serializable;

import msg.Task;

public class ForkLift {
    String name;
    int currentX;
    int currentY;

    String currentTask;
    msg.Task task;
    int status;

    int temparature;
    int battery;

    public ForkLift() {
    }
    public ForkLift(String name) {
        this.name = name;
        this.status = 1;
        this.temparature = 20;
        this.battery = 999;
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTemparature() {
        return temparature;
    }

    public void setTemparature(int temparature) {
        this.temparature = temparature;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    public Task getTask() { return task; }

    public void setTask(Task task) { this.task = task;};
}
