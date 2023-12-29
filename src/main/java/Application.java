import exceptions.InvalidCommand;
import model.PassengerType;
import model.Station;
import service.InMemoryService;
import service.PassengerService;
import service.StationService;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        InMemoryService inMemoryService = new InMemoryService();
        inMemoryService.intializeDatabase();
        StationService stationService = new StationService();
        stationService.addStation(Station.AIRPORT);
        stationService.addStation(Station.CENTRAL);
        PassengerService passengerService = new PassengerService();
        while(true){
            String inp = scanner.nextLine();
            inp=inp.trim();
            String [] input = inp.split(" ");
            try {
                switch (input[0]) {
                    case "BALANCE" : {
                        passengerService.addBalance(input[1], Integer.valueOf(input[2]));
                        break ;
                    }
                    case "CHECK_IN" :{
                        PassengerType passengerType  = null;
                        switch (input[2]){
                            case "ADULT" :
                                passengerType = PassengerType.ADULT ;
                                break ;
                            case "SENIOR_CITIZEN":
                                passengerType = PassengerType.SENIOR_CITIZEN ;
                                break ;
                            case "KID" :
                                passengerType = PassengerType.KIDS ;
                                break ;
                        }
                        stationService.CheckInPassenger(input[1] , passengerType , input[3]);
                        break ;
                    }
                    case "PRINT_SUMMARY" :
                        stationService.printSummary();
                        break ;
                    default:
                        throw new InvalidCommand("Invalid Command");

                }
                }
            catch (Exception exception){
                System.out.println(exception);
            }
        }

    }

}
