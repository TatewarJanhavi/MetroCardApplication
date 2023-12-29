package model;

public class PassengerWithCount {
    private PassengerType passengerType;
    private int count;

    public PassengerWithCount(PassengerType passengerType, int count) {
        this.passengerType = passengerType;
        this.count = count;
    }

    public PassengerType getPassengerType() {
        return passengerType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
