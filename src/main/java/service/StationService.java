package service;

import exceptions.PassengerNotFound;
import exceptions.StationNotFound;
import model.PassengerType;
import model.PassengerWithCount;
import model.Station;
import repository.PassengerBalanceRepo;
import repository.StationRepo;
import java.util.*;

public class StationService {

    public void addStation(Station stationName){
        Map<String , List<PassengerWithCount>> stationPassengerMap = StationRepo.getStationPassengerMap();
        Map<String , Integer[]>  stationCollectionMap = StationRepo.getStationCollectionMap();
        stationPassengerMap.put(stationName.getName() , new ArrayList<>());
        stationCollectionMap.put(stationName.getName() , new Integer [] {0, 0});
    }

    public void CheckInPassenger(String metroCardNumber , PassengerType passengerType , String station) throws Exception {
        Map<String, Integer> passengerBalanceMap = PassengerBalanceRepo.getAllPassengerBalance();
        if(!passengerBalanceMap.containsKey(metroCardNumber))
        {
            throw new PassengerNotFound(String.format("Passenger with  metroCardNumber %s MetroCardNumber doesn't exsits", metroCardNumber));

        }
        Map<String , Integer[]>  stationCollectionMap = StationRepo.getStationCollectionMap();
        if(!stationCollectionMap.containsKey(station)){
            throw new StationNotFound( String.format("Station with name % s does not exsits", station));
        }
        double price =  calculatePrice( metroCardNumber , passengerType , station);
        Map<String , List<PassengerWithCount>> stationPassengerMap = StationRepo.getStationPassengerMap();
        List<PassengerWithCount> passengerWithCounts = stationPassengerMap.get(station);
        boolean addCount = false ;
        for(int i = 0 ; i < passengerWithCounts.size() ; i++){

            if(passengerWithCounts.get(i).getPassengerType().name().equals(passengerType.getName())){
                int currentCount = passengerWithCounts.get(i).getCount() ;
                passengerWithCounts.get(i).setCount(currentCount+1);
                addCount = true;
                break ;
            }
        }
        if(!addCount)
        {
            PassengerWithCount passengerWithCount = new PassengerWithCount(passengerType , 1);
            passengerWithCounts.add(passengerWithCount);
            stationPassengerMap.put( station ,passengerWithCounts);
        }

    }

    public void printSummary(){
        Map<String , List<PassengerWithCount>> stationPassengerMap = StationRepo.getStationPassengerMap();
        Map<String , Integer[]>  stationCollectionMap = StationRepo.getStationCollectionMap();
        for (Map.Entry<String , Integer[]> station  : stationCollectionMap.entrySet()){
            System.out.println("TOTAL_COLLECTION "+ station.getKey() +" "+ station.getValue()[0] +" "+station.getValue()[1]);
            Collections.sort( stationPassengerMap.get(station.getKey()), (a, b) -> b.getCount() - a.getCount());
            System.out.println("PASSENGER_TYPE_SUMMARY");
            for(PassengerWithCount passenger : stationPassengerMap.get(station.getKey())){
                System.out.println(passenger.getPassengerType() +" "+passenger.getCount());
            }
        }
    }

    private double calculatePrice(String metroCardNumber , PassengerType passengerType , String station ){
        Map<String, Integer> passengerBalanceMap = PassengerBalanceRepo.getAllPassengerBalance();
        Map<String , Integer[]>  stationCollectionMap = StationRepo.getStationCollectionMap();
         Set<String> visitedPassenger = PassengerBalanceRepo.getVisitedPassenger();
        int  price = passengerType.getPrice();
        int passengerBalance  = passengerBalanceMap.get(metroCardNumber);
        int discountedPrice = 0;
        if(visitedPassenger.contains(metroCardNumber)){
            price = price/2 ;
            discountedPrice = price;
            visitedPassenger.remove(metroCardNumber);
        }
        else
        {
            visitedPassenger.add(metroCardNumber);
        }
        int serviceCharge = passengerBalance < price ?  (2*(price - passengerBalance)/100) : 0 ;
        int passengerPrice = price + serviceCharge ;
        int updatedPrice = passengerBalance < price ? 0 : passengerBalance - price ;
        passengerBalanceMap.put(metroCardNumber , updatedPrice );
        Integer [] totalPrice =  stationCollectionMap.get(station);
        totalPrice[0] = totalPrice[0]+ passengerPrice;
        totalPrice[1] = totalPrice[1] + discountedPrice;
        stationCollectionMap.put(station , totalPrice);
        return passengerPrice ;
    }
}
