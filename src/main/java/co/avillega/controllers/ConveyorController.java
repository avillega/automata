package co.avillega.controllers;

import co.avillega.services.ConveyorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andres Villegas on 2017-03-22.
 */
@RestController
public class ConveyorController {

    @Autowired
    private ConveyorService conveyorService;

    //La velocidad double debe ir con coma
    @RequestMapping(method = RequestMethod.POST, value= "/conveyor/speed")
    public String changeSpeed(@RequestBody double speed){
        return conveyorService.changeSpeed(speed);
    }

    @RequestMapping("/conveyor/stop/{millis}")
    public String stopWhile(@PathVariable long millis){
        return conveyorService.stop(millis);
    }

    @RequestMapping("/conveyor/stop/emergency")
    public String emergencyStop(){
        return conveyorService.emergencyStop();
    }



}
