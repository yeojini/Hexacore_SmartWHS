package logistics;

import android.util.Log;

public class Warehouse {
    int cellCountWidth;
    int cellCountHeight;
    public float[] axisX;
    public float[] axisY;
    int screenWidth;
    int screenHeight;
    float cellWidth;
    float cellHeight;

    public Warehouse() {

    }
    public Warehouse(int cellCountWidth, int cellCountHeight) {
        this.cellCountWidth = cellCountWidth;
        this.cellCountHeight = cellCountHeight;
        axisX = new float[cellCountWidth];
        axisY = new float[cellCountHeight];
    }

    public void setScreenSize(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        cellWidth = (float)screenWidth/cellCountWidth;
        cellHeight = (float)screenHeight/cellCountHeight;
        for(int i = 0; i< cellCountWidth; i++) {
            axisX[i] = i*cellWidth;
        }
        for(int j = 0; j< cellCountHeight; j++) {
            axisY[j] = j*cellHeight;
        }

    }
}
