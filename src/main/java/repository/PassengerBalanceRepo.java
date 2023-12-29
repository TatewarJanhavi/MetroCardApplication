package repository;

import model.Station;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PassengerBalanceRepo {
    private static Map<String , Integer> passengerBalance ;
    private static Set<String> visitedPassenger;

    public PassengerBalanceRepo() {
        passengerBalance = new HashMap<>();
        visitedPassenger = new HashSet<>();
    }

    public static Map<String , Integer>  getAllPassengerBalance(){
        return passengerBalance;
    }

    public static  Set<String> getVisitedPassenger() {
        return visitedPassenger;
    }
}
