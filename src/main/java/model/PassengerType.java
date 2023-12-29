package model;

public enum PassengerType {
    ADULT("ADULT",200) ,
    SENIOR_CITIZEN("SENIOR_CITIZEN",100) ,
    KIDS ("KIDS",50);

    private int price ;
    private String name ;
    PassengerType(String name, int price) {
        this.name = name ;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
