package co.avillega.controllers;

import co.avillega.entities.Command;
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
@RequestMapping("/api/routines")
public class RoutineControler {

    private static final Logger logger = LoggerFactory.getLogger(RoutineControler.class);

    @Autowired
    private RoutineService routineService;


    @RequestMapping(method = RequestMethod.POST)
    public Routine addRoutine(@RequestBody Routine routine) {
        logger.info(routine.toString());
        routineService.addRoutine(routine);
        return routine;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Routine> getRoutines() {
        return routineService.getRoutines();
    }

    @RequestMapping("/{id}/start")
    public void startRoutine(@PathVariable String id) {
        routineService.startRoutine(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteRoutine(@PathVariable String id) {
        routineService.deleteRoutine(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/commands")
    public Routine setCommandsToRoutine(@PathVariable String id, @RequestBody List<Command> commands) {
        return routineService.setCommandsToRoutine(id, commands);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/commands")
    public List<Command> setCommandsToRoutine(@PathVariable String id) {
        return routineService.getCommandsByRoutineId(id);
    }

}
