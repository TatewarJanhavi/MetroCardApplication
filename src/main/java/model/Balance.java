package model;

public class Balance {
    String MetroCardNo ;
    double balance ;

    public Balance(String metroCardNo, double balance) {
        MetroCardNo = metroCardNo;
        this.balance = balance;
    }

    public String getMetroCardNo() {
        return MetroCardNo;
    }

    public void setMetroCardNo(String metroCardNo) {
        MetroCardNo = metroCardNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
