package service;

import repository.PassengerBalanceRepo;
import repository.StationRepo;

public class InMemoryService {

    public static PassengerBalanceRepo passengerBalanceRepo;
    public static StationRepo stationRepo;

    public void intializeDatabase(){
        passengerBalanceRepo = new PassengerBalanceRepo();
        stationRepo = new StationRepo();
    }
}
