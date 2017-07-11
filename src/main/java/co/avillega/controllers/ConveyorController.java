package co.avillega.controllers;

import co.avillega.services.ConveyorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/speed")
    public String changeSpeed(@RequestParam double speed) {
        return conveyorService.changeSpeed(speed);
    }

    @RequestMapping("/stop")
    public String stop() {
        return conveyorService.stop();
    }


    @RequestMapping("/emergency")
    public String emergencyStop(){
        return conveyorService.emergencyStop();
    }

    @RequestMapping("/start")
    public String start() {
        return conveyorService.start();
    }

    @RequestMapping("/wait")
    public String wait(@PathVariable double seconds) {
        return conveyorService.waitInst(seconds);
    }
}
