package task1.webAccess;

import java.io.Serializable;

/**
 * Created by User on 02.10.2016.
 */
public class ClientData implements Serializable {

    private int ordinalNumber;
    private  int startPoint;
    private  int endPoint;

    public ClientData() {
    }

    public ClientData(int ordinalNumber, int startPoint, int endPoint) {
        this.ordinalNumber = ordinalNumber;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public int getOrdinalNumber() {
        return ordinalNumber;
    }

    public void setOrdinalNumber(int ordinalNumber) {
        this.ordinalNumber = ordinalNumber;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(int startPoint) {
        this.startPoint = startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(int endPoint) {
        this.endPoint = endPoint;
    }
}
