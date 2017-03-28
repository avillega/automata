package co.avillega.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Andres Villegas on 2017-03-22.
 */
@Service
public class ConveyorService {
    private static final Logger logger = LoggerFactory.getLogger(ConveyorService.class);
    private RestTemplate restTemplate = new RestTemplate();


    public String changeSpeed(double speed){
        //restTemplate.getForEntity(String.format("http://192.168.100.12/mailbox/%d", (int) speed), String.class);
        logger.info(String.format("Speed Set to %f", speed));
        return speed+"";
    }

    public String stop(long milliseconds){
        logger.info(String.format("Stopped for %d", milliseconds));
        return "stopped";
    }

    public String emergencyStop(){
        logger.info("Emergency stop activated");
        return "Emergency stop activated";
    }



}
