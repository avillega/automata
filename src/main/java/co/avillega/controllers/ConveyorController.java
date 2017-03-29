package co.avillega.controllers;

import co.avillega.services.ConveyorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andres Villegas on 2017-03-22.
 * Automata Project
 */
@RestController
public class ConveyorController {

    @Autowired
    private ConveyorService conveyorService;

    //La velocidad double debe ir con punto
    @RequestMapping(method = RequestMethod.POST, value= "/conveyor/speed")
    public String changeSpeed(@RequestBody double speed){
        return conveyorService.changeSpeed(speed);
    }

    @RequestMapping("/conveyor/stop/{millis}")
    public String stopWhile(@PathVariable int seconds) {
        return conveyorService.stop(seconds);
    }

    @RequestMapping("/conveyor/emergency")
    public String emergencyStop(){
        return conveyorService.emergencyStop();
    }
}
