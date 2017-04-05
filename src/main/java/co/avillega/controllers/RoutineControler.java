package co.avillega.controllers;

import co.avillega.entities.Routine;
import co.avillega.services.RoutineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andres Villegas on 2017-03-28.
 * for automata project
 */
@RestController
public class RoutineControler {

    private static final Logger logger = LoggerFactory.getLogger(RoutineControler.class);

    @Autowired
    private RoutineService routineService;


    @RequestMapping(method = RequestMethod.POST, value = "/routines")
    public Routine addRoutine(@RequestBody Routine routine) {
        logger.info(routine.toString());
        routineService.addRoutine(routine);
        return routine;
    }

    @RequestMapping("/routines")
    public List<Routine> getRoutines() {
        return routineService.getRoutines();
    }

    @RequestMapping("/routines/{id}/play")
    public void startRoutine(@PathVariable("id") String id) {
        Routine routine = routineService.getRoutine(id);
        routine.executeRoutine();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/routines/{id}")
    public void deleteRoutine(String id) {
        routineService.deleteRoutine(id);
    }

}
