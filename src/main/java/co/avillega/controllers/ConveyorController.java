package co.avillega.controllers;

import co.avillega.services.ConveyorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andres Villegas on 2017-03-22.
 * Automata Project
 */
@RestController
@RequestMapping("/conveyor")
public class ConveyorController {

    @Autowired
    private ConveyorService conveyorService;

    //La velocidad double debe ir con punto
    @RequestMapping(method = RequestMethod.POST, value = "/speed")
    public String changeSpeed(@RequestBody double speed){
        return conveyorService.changeSpeed(speed);
    }

    @RequestMapping("/stop")
    public String stopWhile(@PathVariable int seconds) {
        return conveyorService.stop();
    }

    @RequestMapping("/emergency")
    public String emergencyStop(){
        return conveyorService.emergencyStop();
    }
}
