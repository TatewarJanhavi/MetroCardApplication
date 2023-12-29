package repository;

import model.PassengerType;
import model.PassengerWithCount;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StationRepo {
    private static Map<String , List<PassengerWithCount>> stationPassengerMap ;
    private static Map<String ,Integer[]>  stationCollectionMap ;

    public StationRepo() {
        stationPassengerMap = new HashMap<>();
        stationCollectionMap = new HashMap<>();
    }

    public static Map<String, List<PassengerWithCount>> getStationPassengerMap() {
        return stationPassengerMap;
    }

    public static Map<String, Integer[]> getStationCollectionMap() {
        return stationCollectionMap;
    }
}
