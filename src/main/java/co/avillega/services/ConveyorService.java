package co.avillega.services;

import org.springframework.stereotype.Service;



/**
 * Created by Andres Villegas on 2017-03-22.
 */
@Service
public class ConveyorService {


    public String changeSpeed(double speed){
        System.out.println(String.format("Speed Set to %f", speed));
        return speed+"";
    }

    public String stop(long milliseconds){
        System.out.println(String.format("Stopped for %d", milliseconds));
        return "stopped";
    }

    public String emergencyStop(){
        System.out.println("Emergency stop activated");
        return "Emergency stop activated";
    }



}
