package service;

import repository.PassengerBalanceRepo;

import java.util.Map;

public class PassengerService {

    public void addBalance(String metroCardNo , int price ){
        Map<String, Integer> passengerBalanceMap = PassengerBalanceRepo.getAllPassengerBalance();
        if(passengerBalanceMap.containsKey(metroCardNo))
        {
            int previousPrice = passengerBalanceMap.get(metroCardNo);
            int newPrice = previousPrice + price;
            passengerBalanceMap.put(metroCardNo,newPrice);
        }
        else{
            passengerBalanceMap.put(metroCardNo , price);
        }
    }
}
